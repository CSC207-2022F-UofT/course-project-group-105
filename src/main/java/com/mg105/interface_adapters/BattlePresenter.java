package com.mg105.interface_adapters;

import com.mg105.presenter_interfaces.BattlePresenterInterface;
import com.mg105.use_cases.BattleInteractor;

import java.util.ArrayList;

/**
 * Class communicating between BattleMenu view and game entities.
 * Follows MVP pattern; contains a reference to the view and interacts with Battle and related classes.
 */
public class BattlePresenter implements BattlePresenterInterface {

    private final BattleInteractor interactor;

    private BattleMenuInterface view;

    public BattlePresenter(BattleInteractor interactor) {
        this.interactor = interactor;
        this.interactor.setPresenter(this);
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
     *
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
        if (this.view == null) { //Used for unit tests. Never executed during regular running of the application.
            return;
        }

        this.view.setNames(partyNames, opponentNames);
    }

    /**
     * Call on the BattleMenuInterface to update the displayed information for the given character.
     * @param targetName the name String of the character whose information displayed on the view needs to be updated.
     */
    @Override
    public void updateViewCharacter(String targetName) {
        if (this.view == null) { //Used for unit tests. Never executed during regular running of the application.
            return;
        }

        this.view.updateCharacter(targetName);
    }

    /**
     * UNIMPLEMENTED
     */
    public void endBattle() {
        interactor.endBattle();
    }

}
