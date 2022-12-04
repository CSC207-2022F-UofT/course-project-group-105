package com.mg105.interface_adapters.battle;

import com.mg105.interface_adapters.Toggler;
import com.mg105.use_cases.battle.BattlePresenterInterface;
import com.mg105.use_cases.battle.BattleInteractor;

import java.util.ArrayList;

import static com.mg105.interface_adapters.Toggler.ToggleableComponent.BATTLE;
import static com.mg105.interface_adapters.Toggler.ToggleableComponent.LOSE_MENU;

/**
 * Class communicating between BattleMenuInterface view and BattleInteractor.
 * Follows MVP pattern; contains a reference to the view and interacts with BattleInteractor.
 */
public class BattlePresenter implements BattlePresenterInterface {

    private final BattleInteractor interactor;

    private final Toggler toggler;

    private BattleMenuInterface view;

    /**
     * Creates a new BattlePresenter with reference to a given BattleInteractor, and sets the interactor to refer to
     * this instance.
     * @param interactor the BattleInteractor to be referred to.
     */
    public BattlePresenter(BattleInteractor interactor, Toggler toggler) {
        this.interactor = interactor;
        this.interactor.setPresenter(this);
        this.toggler = toggler;
    }

    /**
     * Sets the view attribute.
     *
     * @param view the BattleMenuInterface instance to set the view attribute to.
     */
    public void setView(BattleMenuInterface view) {
        this.view = view;
    }

    /**
     * Creates a new encounter with random opponents and sets it as the current encounter in GameState
     */
    public void startBattle() {
        interactor.createEncounter();
    }

    /**
     * Returns whether the given name is associated with a fainted character.
     * Assumes that the inputted name corresponds to a character who was in the encounter at some point.
     *
     * @param name the name of the character being checked.
     * @return whether the given name corresponds to a fainted character.
     */
    public boolean givenCharacterFainted(String name) {
        return interactor.isCharacterFainted(name);
    }

    /**
     * Returns the current health of the BattleCharacter with the given name.
     *
     * @return the desired BattleCharacter's current health.
     */
    public int givenCharacterHealth(String name) {
        return interactor.getCharacterHealth(name);
    }

    /**
     * Returns the current damage stat of the BattleCharacter with the given name.
     *
     * @return the desired BattleCharacter's current damage.
     */
    public int givenCharacterDamage(String name) {
        return interactor.getCharacterDamage(name);
    }

    /**
     * Returns the stats of the BattleCharacter with the given name's moves.
     * Stats order: Move1 health change, Move1 damage change, Move2 health change, Move2 damage change.
     *
     * @return the desired BattleCharacter's move stats.
     */
    public int[] givenCharacterMoveStats(String name) {
        return interactor.getCharacterMoveStats(name);
    }

    /**
     * Returns the names of the BattleCharacter with the given name's moves.
     * Stats order: Move1 name, Move2 name.
     *
     * @return the desired BattleCharacter's move names.
     */
    public String[] givenCharacterMoveNames(String name) {
        return interactor.getCharacterMoveNames(name);
    }

    /**
     * Starts a round of the encounter. If battle ended last round, end the encounter/the run depending on the result.
     * If encounter is still in progress, get the next moving character.
     * If opponent is moving, choose a random move and random target and use it.
     * returns null iff the battle has ended
     * @return a String of the name of the moving character
     */
    public String roundStart() {
        return interactor.roundStart();
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
        return interactor.retrieveTargets(moveNum, casterName);
    }

    /**
     * Use one of (given) the given caster's moves on the given target.
     * @param moveNum integer representing which of the two moves is being used.
     * @param casterName String representing the name of the caster BattleCharacter.
     * @param targetName String representing the name of the target BattleCharacter.
     */
    public void executeTurn(int moveNum, String casterName, String targetName) {
        interactor.executeTurn(moveNum, casterName, targetName);
    }

    /**
     * Set the characters in the view.
     * @param partyNames the name strings of the party characters.
     * @param opponentNames the name strings of the opponent characters.
     */
    @Override
    public void setViewNames(String[] partyNames, String[] opponentNames) {
        this.view.setNames(partyNames, opponentNames);
    }

    /**
     * Call on the BattleMenuInterface to update the displayed information for the given character.
     * @param targetName the name String of the character whose information displayed on the view needs to be updated.
     */
    @Override
    public void updateViewCharacter(String targetName) {
        this.view.updateCharacter(targetName);
    }


    /**
     * Makes the appropriate calls to change the state in the case of a battle being lost or won and then
     * Makes the appropriate updates to change the view in the of a battle being won or lost
     */
    public void endBattle() {
        boolean hasWon = interactor.endBattle();
        if(hasWon){
            toggler.toggle(BATTLE);
            return;
        }

        toggler.toggle(LOSE_MENU);

    }

}
