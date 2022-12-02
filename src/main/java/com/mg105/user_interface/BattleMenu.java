package com.mg105.user_interface;

import com.mg105.interface_adapters.BattleMenuInterface;
import com.mg105.interface_adapters.BattlePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * This class uses JavaFX and is displayed during an active battle.
 */
public class BattleMenu implements EventHandler<ActionEvent>, BattleMenuInterface, Toggleable{

    private final BattlePresenter presenter;
    private final String[] playerNames = new String[4];
    private final int[] playerHealth = new int[4];
    private final int[] playerDmg = new int[4];
    private final String[] opponentNames = new String[4];
    private final int[] opponentHealth = new int[4];
    private final int[] opponentDmg = new int[4];

    private final Label p0 = new Label();
    private final Label p1 = new Label();
    private final Label p2 = new Label();
    private final Label p3 = new Label();

    private final Label o0 = new Label();
    private final Label o1 = new Label();
    private final Label o2 = new Label();
    private final Label o3 = new Label();

    private final Button nextRound;
    private final Button moveOne;
    private final Button moveTwo;

    private final Button targetP0;
    private final Button targetP1;
    private final Button targetP2;
    private final Button targetP3;
    private final Button targetO0;
    private final Button targetO1;
    private final Button targetO2;
    private final Button targetO3;
    private final GridPane grid;
    private final Scene scene;

    //Used in Button handle event
    private String moving;
    private int moveNum;


    public BattleMenu(BattlePresenter battlePres) {
        this.presenter = battlePres;
        presenter.setView(this);

        grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        nextRound = new Button("Next Round");
        nextRound.setId("Next Round");
        nextRound.setOnAction(this);
        grid.add(nextRound, 10, 30);

        grid.add(p0, 1, 4);
        grid.add(p1, 1, 8);
        grid.add(p2, 1, 12);
        grid.add(p3, 1, 16);

        grid.add(o0, 18, 4);
        grid.add(o1, 18, 8);
        grid.add(o2, 18, 12);
        grid.add(o3, 18, 16);

        targetP0 = new Button("TARGET");
        targetP0.setOnAction(this);
        targetP0.setVisible(false);
        grid.add(targetP0, 0, 4);

        targetP1 = new Button("TARGET");
        targetP1.setOnAction(this);
        targetP1.setVisible(false);
        grid.add(targetP1, 0, 8);

        targetP2 = new Button("TARGET");
        targetP2.setOnAction(this);
        targetP2.setVisible(false);
        grid.add(targetP2, 0, 12);

        targetP3 = new Button("TARGET");
        targetP3.setOnAction(this);
        targetP3.setVisible(false);
        grid.add(targetP3, 0, 16);

        targetO0 = new Button("TARGET");
        targetO0.setOnAction(this);
        grid.add(targetO0, 19, 4);
        targetO0.setVisible(false);

        targetO1 = new Button("TARGET");
        targetO1.setOnAction(this);
        targetO1.setVisible(false);
        grid.add(targetO1, 19, 8);

        targetO2 = new Button("TARGET");
        targetO2.setOnAction(this);
        targetO2.setVisible(false);
        grid.add(targetO2, 19, 12);

        targetO3 = new Button("TARGET");
        targetO3.setOnAction(this);
        targetO3.setVisible(false);
        grid.add(targetO3, 19, 16);

        moveOne = new Button("");
        moveOne.setOnAction(this);
        moveOne.setVisible(false);

        moveTwo = new Button("");
        moveTwo.setOnAction(this);
        moveTwo.setVisible(false);

        scene = new Scene(grid, 800, 800);
    }

    /**
     * Sets the names of the player and opponent characters participating in the active battle.
     *
     * @param playerNames array of name Strings representing player characters.
     * @param opponentNames array of name Strings representing opponents.
     */
    @Override
    public void setNames(String[] playerNames, String[] opponentNames) {
        for (int i = 0; i < 4; ++i) {
            //Check if any player characters have fainted. Opponent set will always contain four characters initially.
            if (playerNames.length <= i) {
                this.playerNames[i] = "FAINTED";
                this.playerHealth[i] = 0;
                this.playerDmg[i] = 0;
            } else {
                this.playerNames[i] = playerNames[i];
                this.playerHealth[i] = presenter.givenCharacterHealth(this.playerNames[i]);
                this.playerDmg[i] = presenter.givenCharacterDamage(this.playerNames[i]);
            }

            this.opponentNames[i] = opponentNames[i];
            this.opponentHealth[i] = presenter.givenCharacterHealth(this.opponentNames[i]);
            this.opponentDmg[i] = presenter.givenCharacterDamage(this.opponentNames[i]);
        }
    }

    /**
     * Updates the display corresponding to the given affected character.
     *
     * @param character the character who needs to be updated on the screen.
     */
    @Override
    public void updateCharacter(String character){
        if (playerNames[0].equals(character)) {
            updateCharacterData(character, 0, p0, false);
        } else if (playerNames[1].equals(character)) {
            updateCharacterData(character, 1, p1, false);
        } else if (playerNames[2].equals(character)) {
            updateCharacterData(character, 2, p2, false);
        } else if (playerNames[3].equals(character)) {
            updateCharacterData(character, 3, p3, false);
        } else if (opponentNames[0].equals(character)) {
            updateCharacterData(character, 0, o0, true);
        } else if (opponentNames[1].equals(character)) {
            updateCharacterData(character, 1, o1, true);
        } else if (opponentNames[2].equals(character)) {
            updateCharacterData(character, 2, o2, true);
        } else if (opponentNames[3].equals(character)) {
            updateCharacterData(character, 3, o3, true);
        }
    }

    /**
     * Get the scene of this toggleable object.  It is this scene that will be displayed.
     *
     * @return the scene to be displayed.
     */
    @Override
    public @NotNull Scene getScene() {
        return this.scene;
    }

    /**
     * Set the visibility of this component.
     *
     * @param isVisible true if the Toggleable is now visible, false otherwise.  If false the Toggleable is expected
     *                  to do nothing on ANY user inputs.
     */
    @Override
    public void toggle(boolean isVisible) {
        if (isVisible) {
            presenter.startBattle(); //Will call setNames and update character data.

            setupCharacterLabel(p0, 0, false);
            setupCharacterLabel(p1, 1, false);
            setupCharacterLabel(p2, 2, false);
            setupCharacterLabel(p3, 3, false);

            setupCharacterLabel(o0, 0, true);
            setupCharacterLabel(o1, 1, true);
            setupCharacterLabel(o2, 2, true);
            setupCharacterLabel(o3, 3, true);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        Object source = event.getSource();
        if (source.equals(nextRound)) {
            nextRound.setDisable(true);

            moving = presenter.roundStart();

            if (moving == null) { //Battle ended
                presenter.endBattle();
            }

            //moving != null
            if (playerNames[0].equals(moving)) {
                int[] moveStats = presenter.givenCharacterMoveStats(moving);
                String[] moveNames = presenter.givenCharacterMoveNames(moving);

                grid.add(moveOne, 2, 4);
                moveOne.setVisible(true);
                grid.add(moveTwo, 3, 4);
                moveTwo.setVisible(true);

                moveOne.setText(moveNames[0] + "\n Hp: " + moveStats[0] + ", Dmg: " + moveStats[1]);
                moveTwo.setText(moveNames[1] + "\n Hp: " + moveStats[2] + ", Dmg: " + moveStats[3]);
            } else if (playerNames[1].equals(moving)) {
                int[] moveStats = presenter.givenCharacterMoveStats(moving);
                String[] moveNames = presenter.givenCharacterMoveNames(moving);

                grid.add(moveOne, 2, 8);
                moveOne.setVisible(true);
                grid.add(moveTwo, 3, 8);
                moveTwo.setVisible(true);

                moveOne.setText(moveNames[0] + "\n Hp: " + moveStats[0] + ", Dmg: " + moveStats[1]);
                moveTwo.setText(moveNames[1] + "\n Hp: " + moveStats[2] + ", Dmg: " + moveStats[3]);
            } else if (playerNames[2].equals(moving)) {
                int[] moveStats = presenter.givenCharacterMoveStats(moving);
                String[] moveNames = presenter.givenCharacterMoveNames(moving);

                grid.add(moveOne, 2, 12);
                moveOne.setVisible(true);
                grid.add(moveTwo, 3, 12);
                moveTwo.setVisible(true);

                moveOne.setText(moveNames[0] + "\n Hp: " + moveStats[0] + ", Dmg: " + moveStats[1]);
                moveTwo.setText(moveNames[1] + "\n Hp: " + moveStats[2] + ", Dmg: " + moveStats[3]);
            } else if (playerNames[3].equals(moving)) {
                int[] moveStats = presenter.givenCharacterMoveStats(moving);
                String[] moveNames = presenter.givenCharacterMoveNames(moving);

                grid.add(moveOne, 2, 16);
                moveOne.setVisible(true);
                grid.add(moveTwo, 3, 16);
                moveTwo.setVisible(true);

                moveOne.setText(moveNames[0] + "\n Hp: " + moveStats[0] + ", Dmg: " + moveStats[1]);
                moveTwo.setText(moveNames[1] + "\n Hp: " + moveStats[2] + ", Dmg: " + moveStats[3]);
            } else if(opponentNames[0].equals(moving) || opponentNames[1].equals(moving) ||
                opponentNames[2].equals(moving) || opponentNames[3].equals(moving)) {
                //If opponent moved, re-enable nextRound button
                nextRound.setDisable(false);
            }

        } else if(source.equals(moveOne)) {
            moveNum = 1;
            displayTargets();
        } else if(source.equals(moveTwo)) {
            moveNum = 2;
            displayTargets();
        } else if(source.equals(targetP0)) {
            targetP0.setVisible(false);
            targetP1.setVisible(false);
            targetP2.setVisible(false);
            targetP3.setVisible(false);

            presenter.executeTurn(moveNum, moving, playerNames[0]);

            //Re-enable nextRound button after a move has been made.
            nextRound.setDisable(false);
        } else if(source.equals(targetP1)) {
            targetP0.setVisible(false);
            targetP1.setVisible(false);
            targetP2.setVisible(false);
            targetP3.setVisible(false);

            presenter.executeTurn(moveNum, moving, playerNames[1]);

            //Re-enable nextRound button after a move has been made.
            nextRound.setDisable(false);
        } else if(source.equals(targetP2)) {
            targetP0.setVisible(false);
            targetP1.setVisible(false);
            targetP2.setVisible(false);
            targetP3.setVisible(false);

            presenter.executeTurn(moveNum, moving, playerNames[2]);

            //Re-enable nextRound button after a move has been made.
            nextRound.setDisable(false);
        } else if(source.equals(targetP3)) {
            targetP0.setVisible(false);
            targetP1.setVisible(false);
            targetP2.setVisible(false);
            targetP3.setVisible(false);

            presenter.executeTurn(moveNum, moving, playerNames[3]);

            //Re-enable nextRound button after a move has been made.
            nextRound.setDisable(false);
        } else if(source.equals(targetO0)) {
            targetO0.setVisible(false);
            targetO1.setVisible(false);
            targetO2.setVisible(false);
            targetO3.setVisible(false);

            presenter.executeTurn(moveNum, moving, opponentNames[0]);

            //Re-enable nextRound button after a move has been made.
            nextRound.setDisable(false);
        } else if(source.equals(targetO1)) {
            targetO0.setVisible(false);
            targetO1.setVisible(false);
            targetO2.setVisible(false);
            targetO3.setVisible(false);

            presenter.executeTurn(moveNum, moving, opponentNames[1]);

            //Re-enable nextRound button after a move has been made.
            nextRound.setDisable(false);
        } else if(source.equals(targetO2)) {
            targetO0.setVisible(false);
            targetO1.setVisible(false);
            targetO2.setVisible(false);
            targetO3.setVisible(false);

            presenter.executeTurn(moveNum, moving, opponentNames[2]);

            //Re-enable nextRound button after a move has been made.
            nextRound.setDisable(false);
        } else if(source.equals(targetO3)) {
            targetO0.setVisible(false);
            targetO1.setVisible(false);
            targetO2.setVisible(false);
            targetO3.setVisible(false);

            presenter.executeTurn(moveNum, moving, opponentNames[3]);

            //Re-enable nextRound button after a move has been made.
            nextRound.setDisable(false);
        }
    }

    /**
     * Helper function for updateCharacter.
     * @param character name String of the character to be updated.
     * @param position index of the character's data in the data arrays.
     * @param lbl Label object to be updated.
     * @param isOpponent boolean for whether the character is an opponent or not.
     */
    private void updateCharacterData(String character, int position, Label lbl, boolean isOpponent) {
        if (presenter.givenCharacterFainted(character)) {
            lbl.setText("FAINTED");
            return;
        }
        int[] hpData;
        int[] dmgData;
        String[] nameData;

        if (isOpponent) {
            hpData = opponentHealth;
            dmgData = opponentDmg;
            nameData = opponentNames;
        } else {
            hpData = playerHealth;
            dmgData = playerDmg;
            nameData = playerNames;
        }
        hpData[position] = presenter.givenCharacterHealth(character);
        dmgData[position] = presenter.givenCharacterDamage(character);
        lbl.setText(nameData[position] + "\n Hp: " + hpData[position] + ", Dmg: " + dmgData[position]);
    }

    /**
     * Helper function for moveOne/moveTwo button click events.
     * Removes the two buttons, retrieves targets based on the chosen move, displays respective target buttons.
     */
    private void displayTargets() {
        moveOne.setVisible(false);
        moveTwo.setVisible(false);

        grid.getChildren().remove(moveOne);
        grid.getChildren().remove(moveTwo);

        ArrayList<String> targets = presenter.retrieveTargets(moveNum, moving);

        for (String s : targets) {
            if (playerNames[0].equals(s)) {
                targetP0.setVisible(true);
            } else if (playerNames[1].equals(s)) {
                targetP1.setVisible(true);
            } else if (playerNames[2].equals(s)) {
                targetP2.setVisible(true);
            } else if (playerNames[3].equals(s)) {
                targetP3.setVisible(true);
            } else if (opponentNames[0].equals(s)) {
                targetO0.setVisible(true);
            } else if (opponentNames[1].equals(s)) {
                targetO1.setVisible(true);
            } else if (opponentNames[2].equals(s)) {
                targetO2.setVisible(true);
            } else if (opponentNames[3].equals(s)) {
                targetO3.setVisible(true);
            }
        }
    }

    /**
     * Helper function to initialize the character labels.
     * @param lbl Label object corresponding to the character.
     * @param position Integer representing index of character's data in data arrays.
     * @param isOpponent Boolean of whether the character is an opponent.
     */
    private void setupCharacterLabel(Label lbl, int position, boolean isOpponent) {
        if (isOpponent) {
            lbl.setText(opponentNames[position] + "\n Hp: " + opponentHealth[position] + ", Dmg: " +
                opponentDmg[position]);
        } else {
            if (playerNames[position].equals("FAINTED")) {
                lbl.setText("FAINTED");
            } else {
                lbl.setText(playerNames[position] + "\n Hp: " + playerHealth[position] + ", Dmg: " +
                    playerDmg[position]);
            }
        }
    }
}
