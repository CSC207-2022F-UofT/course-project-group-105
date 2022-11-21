package com.mg105.interface_adapters;

import com.mg105.entities.Battle;
import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Move;
import com.mg105.user_interface.BattleMenu;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class communicating between BattleMenu view and game entities.
 *
 * Follows MVP pattern; contains a reference to the view and interacts with Battle and related classes.
 */
public class BattlePresenter {

    private GameState state;
    private Battle encounter;

    private BattleMenu view;

    public BattlePresenter(GameState state) {
        this.state = state;
        this.encounter = state.getCurrEncounter();
        BattleMenu.setPresenter(this);
    }

    /**
     * Sets the view attribute.
     *
     * @param view the BattleMenu instance to set the view attribute to.
     */
    public void setView(BattleMenu view) {
        this.view = view;
    }
    /**
     * Creates a new encounter with random opponents and sets it as the current encounter in GameState
     */
    public void createEncounter() {
        Random rand = new Random();

        ArrayList<BattleCharacter> opponents = new ArrayList<BattleCharacter>();

        for (int i = 0; i < 4; ++i){
            int charHealth = rand.nextInt(5, 41);
            int charDmg = rand.nextInt(1, 11);
            int charSpeed = rand.nextInt(3, 16);
            Move m1 = new Move(-rand.nextInt(1, 8), 0, "first", false);
            Move m2 = new Move(rand.nextInt(1, 4), 0, "second", true);
            BattleCharacter character = new BattleCharacter(charHealth, "Opponent " + Integer.toString(i),
                charDmg, charSpeed, true, m1, m2);
            opponents.add(character);
        }
        ArrayList<BattleCharacter> party = this.state.getParty();
        Battle b = new Battle(opponents, party);
        this.state.setCurrEncounter(b);
        this.encounter = b;

        String[] partyNames = new String[party.size()];
        String[] opponentNames = new String[opponents.size()];

        for (int i = 0; i < party.size(); ++i) {
            partyNames[i] = party.get(i).getName();
            opponentNames[i] = opponents.get(i).getName();
        }

        this.view.setNames(partyNames, opponentNames);
    }

    /**
     * Returns whether the given name is associated with a fainted character.
     * Assumes that the inputted name corresponds to a character who was in the encounter at some point.
     *
     * @param name the name of the character being checked.
     * @return whether the given name corresponds to a fainted character.
     */
    public boolean givenCharacterFainted(String name) {
        for (BattleCharacter c : encounter.getPlayerCharacters()) {
            if (c.getName().equals(name)) {
                return false;
            }
        }

        for (BattleCharacter c : encounter.getOpponents()) {
            if (c.getName().equals(name)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the current health of the BattleCharacter with the given name.
     *
     * @return the desired BattleCharacter's current health.
     */
    public int givenCharacterHealth(String name) {
        return this.encounter.getCharacter(name).getHp();
    }

    /**
     * Returns the maximum health of the BattleCharacter with the given name.
     *
     * @return the desired BattleCharacter's max health.
     */
    public int givenCharacterMaxHealth(String name) {
        return this.encounter.getCharacter(name).getMaxHp();
    }

    /**
     * Returns the current damage stat of the BattleCharacter with the given name.
     *
     * @return the desired BattleCharacter's current damage.
     */
    public int givenCharacterDamage(String name) {
        return this.encounter.getCharacter(name).getDmg();
    }

    /**
     * Returns the speed of the BattleCharacter with the given name.
     *
     * @return the desired BattleCharacter's speed.
     */
    public int givenCharacterSpeed(String name) {
        return this.encounter.getCharacter(name).getSpeed();
    }

    /**
     * Returns the stats of the BattleCharacter with the given name's moves.
     * Stats order: Move1 health change, Move1 damage change, Move2 health change, Move2 damage change.
     *
     * @return the desired BattleCharacter's move stats.
     */
    public int[] givenCharacterMoveStats(String name) {
        BattleCharacter caster = this.encounter.getCharacter(name);
        int[] moveStats = {caster.getMoveOne().getHealthChange(), caster.getMoveOne().getDamageChange(),
                           caster.getMoveTwo().getHealthChange(), caster.getMoveTwo().getDamageChange()};

        return moveStats;
    }

    /**
     * Returns the names of the BattleCharacter with the given name's moves.
     * Stats order: Move1 name, Move2 name.
     *
     * @return the desired BattleCharacter's move names.
     */
    public String[] givenCharacterMoveNames(String name) {
        BattleCharacter caster = this.encounter.getCharacter(name);
        String[] moveNames = {caster.getMoveOne().getName(), caster.getMoveTwo().getName()};

        return moveNames;
    }

    /**
     * Starts a round of the encounter. If battle ended last round, end the encounter/the run depending on the result.
     * If encounter is still in progress, get the next moving character.
     * If opponent is moving, choose a random move and random target and use it.
     *
     * @return a String of the name of the moving character
     */
    public String roundStart() {

        int status = this.encounter.getBattleStatus();
        if (status == -1) { //Player lost last round
            //Something with ReplayGenerator
            return null;
        } else if (status == 1) { //Player won last round
            this.endBattle();
            return null;
        } else { //Battle is ongoing
            BattleCharacter moving = this.encounter.getMovingCharacter();
            boolean isOpponentMoving = moving.isOpponent();

            if (isOpponentMoving) { //Opponent character is moving
                Random rand = new Random();
                int moveNumber = rand.nextInt(2);

                Move chosenMove;
                if (moveNumber == 0) {
                    chosenMove = moving.getMoveOne();
                } else {
                    chosenMove = moving.getMoveTwo();
                }

                if (!chosenMove.isFriendly()) {
                    ArrayList<BattleCharacter> players = this.encounter.getPlayerCharacters();

                    //Choose a random player character to attack
                    int target = rand.nextInt(players.size());

                    this._useMove(chosenMove, moving, players.get(target));
                } else {
                    ArrayList<BattleCharacter> opponents = this.encounter.getOpponents();

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
     * @param moveNum integer representing which of the two Moves is being used.
     * @param casterName String representing the name of the given moving BattleCharacter.
     * @return ArrayList of Strings representing the names of every possible target BattleCharacter.
     */
    public ArrayList<String> retrieveTargets(int moveNum, String casterName) {
        //Retrieve the moving BattleCharacter
        BattleCharacter caster = this.encounter.getCharacter(casterName);

        //Retrieve the Move being used
        Move m;
        if (moveNum == 1) {
            m = caster.getMoveOne();
        } else { //moveNum == 2
            m = caster.getMoveTwo();
        }

        ArrayList<String> targets = new ArrayList<String>();

        ArrayList<BattleCharacter> targetCharacters;

        //Retrieve possible target BattleCharacters
        if (m.isFriendly()) { //caster is a player character, so targets are player characters
            targetCharacters = this.encounter.getPlayerCharacters();
        } else { //caster is a player character, so targets are opponent characters
            targetCharacters = this.encounter.getOpponents();
        }

        //Add character names to ArrayList
        for (BattleCharacter c : targetCharacters) {
            targets.add(c.getName());
        }

        return targets;
    }

    /**
     * Use one of (given) the given caster's moves on the given target.
     * @param moveNum integer representing which of the two moves is being used.
     * @param casterName String representing the name of the caster BattleCharacter.
     * @param targetName String representing the name of the target BattleCharacter.
     */
    public void executeTurn(int moveNum, String casterName, String targetName) {
        BattleCharacter caster = this.encounter.getCharacter(casterName);
        BattleCharacter target = this.encounter.getCharacter(targetName);
        if (moveNum == 1) {
            this._useMove(caster.getMoveOne(), caster, target);
        } else { //moveNum == 2
            this._useMove(caster.getMoveTwo(), caster, target);
        }
    }

    public void endBattle() {
        //unimplemented, includes _removeActiveBattle and _addReward()
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
        // then increase the health damage applied by the move based on the character's damage stat.
        if (!m.isFriendly() && m.getHealthChange() < 0) {
            target.modifyHealth(-caster.getDmg());
        }

        if (target.getHp() == 0) {
            this.encounter.removeChar(target);
            if (!target.isOpponent()) {
                this.state.getParty().remove(target);
                this.state.getFainted().add(target);
            }
        }

        target.modifyDamage(m.getDamageChange());

        //Currently unimplemented, will notify the view to  update the impacted character
        this.view.updateCharacter(target.getName());
    }

    private void _addReward() {
        //unimplemented
    }

    private void _removeActiveBattle() {
        //unimplemented
    }

}
