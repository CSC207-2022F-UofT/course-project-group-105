package com.mg105.use_cases.battle;

import com.mg105.entities.Battle;
import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Move;
import com.mg105.use_cases.inventory.InventoryInteractor;
import com.mg105.use_cases.save.Saver;

import java.util.ArrayList;
import java.util.Random;

import static com.mg105.utils.ItemConstants.UPGRADE_TOKEN_NAME;

/**
 * This class interacts with the Battle entity.
 * <p>
 * It is responsible for modifying Battle related entities and for the processing needed during combat encounters.
 */
public class BattleInteractor {

    private final static int MAX_UPGRADE_TOKEN_REWARDED = 3;
    private final GameState state;
    private final InventoryInteractor inventoryInteractor;

    private final Saver saver;
    private BattlePresenterInterface presenter;


    /**
     * Creates a new BattleInteractor with a reference to the GameState.
     *
     * @param state               the GameState to be referred to.
     * @param inventoryInteractor the inventoryInteractor to be referred to.
     * @param saver               an instance of Saver used to save data.
     */
    public BattleInteractor(GameState state, InventoryInteractor inventoryInteractor, Saver saver) {

        this.state = state;
        this.inventoryInteractor = inventoryInteractor;
        this.saver = saver;
    }

    /**
     * Sets the presenter attribute.
     *
     * @param presenter the BattlePresenterInterface instance to set the presenter attribute to.
     */
    public void setPresenter(BattlePresenterInterface presenter) {
        this.presenter = presenter;
    }

    /**
     * Creates a new encounter with random opponents and sets it as the current encounter in GameState.
     */
    public void createEncounter() {
        /* OLD RANDOMLY GENERATED OPPONENTS CODE
        Random rand = new Random();

        ArrayList<BattleCharacter> opponents = new ArrayList<>();

        for (int i = 0; i < 4; ++i){
            int charHealth = rand.nextInt(5, 41);
            int charDmg = rand.nextInt(1, 11);
            int charSpeed = rand.nextInt(3, 16);
            Move m1 = new Move(-rand.nextInt(1, 8), 0, "first", false);
            Move m2 = new Move(rand.nextInt(1, 4), 0, "second", true);
            BattleCharacter character = new BattleCharacter(charHealth, "Opponent " + i,
                charDmg, charSpeed, true, m1, m2);
            opponents.add(character);
        }
         */
        ArrayList<BattleCharacter> opponents = new ArrayList<>(state.getCurrOpponent().getOpponents());
        ArrayList<BattleCharacter> party = this.state.getParty();
        Battle b = new Battle(opponents, party);
        this.state.setCurrEncounter(b);

        String[] partyNames = new String[party.size()];
        String[] opponentNames = new String[opponents.size()];

        for (int i = 0; i < party.size(); ++i) {
            partyNames[i] = party.get(i).getName();
        }

        for (int i = 0; i < opponents.size(); ++i) {
            opponentNames[i] = opponents.get(i).getName();
        }

        presenter.setViewNames(partyNames, opponentNames);
    }

    /**
     * Returns whether the given name is associated with a fainted character.
     * Assumes that the inputted name corresponds to a character who was in the encounter at some point.
     *
     * @param name the name of the character being checked.
     * @return whether the given name corresponds to a fainted character.
     */
    public boolean isCharacterFainted(String name) {
        for (BattleCharacter c : state.getCurrEncounter().getPlayerCharacters()) {
            if (c.getName().equals(name)) {
                return false;
            }
        }

        for (BattleCharacter c : state.getCurrEncounter().getOpponents()) {
            if (c.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the current health of the BattleCharacter with the given name.
     *
     * @param name the name of the character.
     * @return the desired BattleCharacter's current health.
     */
    public int getCharacterHealth(String name) {
        return state.getCurrEncounter().getCharacter(name).getHp();
    }

    /**
     * Returns the current damage stat of the BattleCharacter with the given name.
     *
     * @param name the name of the character.
     * @return the desired BattleCharacter's current damage.
     */
    public int getCharacterDamage(String name) {
        return state.getCurrEncounter().getCharacter(name).getDmg();
    }

    /**
     * Returns the stats of the BattleCharacter with the given name's moves.
     * Stats order: Move1 health change, Move1 damage change, Move2 health change, Move2 damage change.
     *
     * @param name the name of the character.
     * @return the desired BattleCharacter's move stats.
     */
    public int[] getCharacterMoveStats(String name) {
        BattleCharacter caster = state.getCurrEncounter().getCharacter(name);
        return new int[]{caster.getMoveOne().getHealthChange(), caster.getMoveOne().getDamageChange(),
            caster.getMoveTwo().getHealthChange(), caster.getMoveTwo().getDamageChange()};
    }

    /**
     * Returns the names of the BattleCharacter with the given name's moves.
     * Stats order: Move1 name, Move2 name.
     *
     * @param name the name of the character.
     * @return the desired BattleCharacter's move names.
     */
    public String[] getCharacterMoveNames(String name) {
        BattleCharacter caster = state.getCurrEncounter().getCharacter(name);
        return new String[]{caster.getMoveOne().getName(), caster.getMoveTwo().getName()};
    }

    /**
     * Starts a round of the encounter. If battle ended last round, end the encounter/the run depending on the result.
     * If encounter is still in progress, get the next moving character.
     * If opponent is moving, choose a random move and random target and use it.
     * returns null iff the battle has ended
     *
     * @return a String of the name of the moving character
     */
    public String roundStart() {
        int status = getBattleStatus();
        if (Math.abs(status) == 1) {
            //Player either lost or won the battle
            return null;
        } else { //Battle is ongoing
            BattleCharacter moving = state.getCurrEncounter().getMovingCharacter();

            if (moving.isOpponent()) { //Opponent character is moving
                Random rand = new Random();
                int moveNumber = rand.nextInt(2);

                Move chosenMove;
                if (moveNumber == 0) {
                    chosenMove = moving.getMoveOne();
                } else {
                    chosenMove = moving.getMoveTwo();
                }

                if (!chosenMove.isFriendly()) {
                    ArrayList<BattleCharacter> players = state.getCurrEncounter().getPlayerCharacters();

                    //Choose a random player character to attack
                    int target = rand.nextInt(players.size());

                    this._useMove(chosenMove, moving, players.get(target));
                } else {
                    ArrayList<BattleCharacter> opponents = state.getCurrEncounter().getOpponents();

                    //Choose a random opponent to use the friendly move on
                    int target = rand.nextInt(opponents.size());

                    this._useMove(chosenMove, moving, opponents.get(target));
                }
            }

            //No matter what, the moving BattleCharacter's name is returned
            return moving.getName();
        }
    }

    /**
     * Returns the name of every BattleCharacter which can be targeted by the given move.
     * Note: Function should only be called from view when caster is friendly, so method does not accommodate for case
     * where caster is an opponent.
     *
     * @param moveNum    integer representing which of the two Moves is being used.
     * @param casterName String representing the name of the given moving BattleCharacter.
     * @return ArrayList of Strings representing the names of every possible target BattleCharacter.
     */
    public ArrayList<String> retrieveTargets(int moveNum, String casterName) {
        //Retrieve the moving BattleCharacter
        BattleCharacter caster = state.getCurrEncounter().getCharacter(casterName);

        //Retrieve the Move being used
        Move m;
        if (moveNum == 1) {
            m = caster.getMoveOne();
        } else { //moveNum == 2
            m = caster.getMoveTwo();
        }

        ArrayList<String> targets = new ArrayList<>();

        ArrayList<BattleCharacter> targetCharacters;

        //Retrieve possible target BattleCharacters
        if (m.isFriendly()) { //caster is a player character, so targets are player characters
            targetCharacters = state.getCurrEncounter().getPlayerCharacters();
        } else { //caster is a player character, so targets are opponent characters
            targetCharacters = state.getCurrEncounter().getOpponents();
        }

        //Add character names to ArrayList
        for (BattleCharacter c : targetCharacters) {
            targets.add(c.getName());
        }

        return targets;
    }

    /**
     * Use one of (given) the given caster's moves on the given target.
     *
     * @param moveNum    integer representing which of the two moves is being used.
     * @param casterName String representing the name of the caster BattleCharacter.
     * @param targetName String representing the name of the target BattleCharacter.
     */
    public void executeTurn(int moveNum, String casterName, String targetName) {
        BattleCharacter caster = state.getCurrEncounter().getCharacter(casterName);
        BattleCharacter target = state.getCurrEncounter().getCharacter(targetName);
        if (moveNum == 1) {
            this._useMove(caster.getMoveOne(), caster, target);
        } else { //moveNum == 2
            this._useMove(caster.getMoveTwo(), caster, target);
        }
    }

    /**
     * Applies the given move used by given caster onto the given target.
     * If the target faints and is a player character, remove it from the list of active players in state,
     * and add it to the list of fainted characters.
     * Calls on the view to update the impacted character.
     */
    private void _useMove(Move m, BattleCharacter caster, BattleCharacter target) {
        target.modifyHealth(m.getHealthChange());

        //If the move is to be used on a character on the other team, and the move is meant to deal health damage,
        // then increase the health damage applied by the move based on the caster's damage stat.
        if (!m.isFriendly() && m.getHealthChange() < 0) {
            target.modifyHealth(-caster.getDmg());
        }

        //If the move is to be used on a character on the same team, and the move is meant to heal, then increase the
        // healing effect applied by the move based on the caster's damage stat.
        if (m.isFriendly() && m.getHealthChange() > 0) {
            target.modifyHealth(Math.floorDiv(caster.getDmg(), 2));
        }

        if (target.getHp() == 0) {
            state.getCurrEncounter().removeChar(target);
            if (!target.isOpponent()) {
                this.state.getParty().remove(target);
                this.state.getFainted().add(target);
            }
        }

        target.modifyDamage(m.getDamageChange());

        presenter.updateViewCharacter(target.getName());
    }

    /**
     * Method makes changes that represent an ended battle in the state of the game (won or lost)
     * The method also save the game for user
     *
     * @return true iff the battle was won
     */
    public boolean endBattle() {
        this.saver.save();
        int status = getBattleStatus();
        state.removeCurrEncounter();
        if (status == 1) {
            addReward();
            return true;
        } else if (status == -1) {
            state.getParty().addAll(state.getFainted());
            state.getFainted().removeAll(state.getParty());
        }

        // since at this point status != 0, status must == -1 (battle was lost)
        return false;
    }

    /**
     * Adds the appropriate number of upgrade tokens to the inventory
     */
    private void addReward() {
        int dif = Math.max(0, inventoryInteractor.getInventoryLimit() - inventoryInteractor.getNumOfItem());
        if (dif == 0) {
            return;
        }

        int reward = getRandomInt(Math.min(MAX_UPGRADE_TOKEN_REWARDED, dif));

        for (int i = 1; i <= reward; i++) {
            inventoryInteractor.addItem(UPGRADE_TOKEN_NAME);
        }
    }

    /**
     * Returns a random integer from 1 to max
     *
     * @param max the maximum value of the random integer
     * @return a random integer from 1 to max
     */

    private int getRandomInt(int max) {
        return (int) Math.floor(Math.random() * (max - 1 + 1) + 1);
    }

    /**
     * Returns the battle status of the current battle
     * Precondition there is currently is a battle
     *
     * @return the battle status of the current battle
     */
    private int getBattleStatus() {
        return state.getCurrEncounter().getBattleStatus();

    }
}
