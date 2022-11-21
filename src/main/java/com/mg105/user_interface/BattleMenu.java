package com.mg105.user_interface;

import com.mg105.interface_adapters.BattlePresenter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BattleMenu extends Application implements EventHandler<ActionEvent> {

    private static BattlePresenter presenter;
    private String[] playerNames = new String[4];
    private int[] playerHealth = new int[4];
    private int[] playerDmg = new int[4];
    private String[] opponentNames = new String[4];
    private int[] opponentHealth = new int[4];
    private int[] opponentDmg = new int[4];

    private Label p0;
    private Label p1;
    private Label p2;
    private Label p3;

    private Label o0;
    private Label o1;
    private Label o2;
    private Label o3;

    private GridPane grid;


    public BattleMenu() {
        presenter.setView(this);
        presenter.createEncounter();
    }

    public void setNames(String[] playerNames, String[] opponentNames) {
        this.playerNames = playerNames;
        this.opponentNames = opponentNames;

        for (int i = 0; i < playerNames.length; ++i) {
            this.playerHealth[i] = this.presenter.givenCharacterHealth(this.playerNames[i]);
            this.playerDmg[i] = this.presenter.givenCharacterDamage(this.playerNames[i]);

            this.opponentHealth[i] = this.presenter.givenCharacterHealth(this.opponentNames[i]);
            this.opponentDmg[i] = this.presenter.givenCharacterDamage(this.opponentNames[i]);
        }
    }

    /**
     * Set the BattlePresenter attribute for every BattleMenu.
     *
     * @param pres the BattlePresenter instance to set the attribute to.
     */
    public static void setPresenter(BattlePresenter pres) {
        presenter = pres;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Battle");

        grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        Button nextRound = new Button("Next Round");
        nextRound.setId("Next Round");
        nextRound.setOnAction(this);
        grid.add(nextRound, 10, 30);

        p0 = new Label(playerNames[0] + "\n Hp: " + Integer.toString(playerHealth[0]) + ", Dmg: " +
            Integer.toString(playerDmg[0]));
        p1 = new Label(playerNames[1] + "\n Hp: " + Integer.toString(playerHealth[1]) + ", Dmg: " +
            Integer.toString(playerDmg[1]));
        p2 = new Label(playerNames[2] + "\n Hp: " + Integer.toString(playerHealth[2]) + ", Dmg: " +
            Integer.toString(playerDmg[2]));
        p3 = new Label(playerNames[3] + "\n Hp: " + Integer.toString(playerHealth[3]) + ", Dmg: " +
            Integer.toString(playerDmg[3]));

        o0 = new Label(opponentNames[0] + "\n Hp: " + Integer.toString(opponentHealth[0]) + ", Dmg: " +
            Integer.toString(opponentDmg[0]));
        o1 = new Label(opponentNames[1] + "\n Hp: " + Integer.toString(opponentHealth[1]) + ", Dmg: " +
            Integer.toString(opponentDmg[1]));
        o2 = new Label(opponentNames[2] + "\n Hp: " + Integer.toString(opponentHealth[2]) + ", Dmg: " +
            Integer.toString(opponentDmg[2]));
        o3 = new Label(opponentNames[3] + "\n Hp: " + Integer.toString(opponentHealth[3]) + ", Dmg: " +
            Integer.toString(opponentDmg[3]));

        grid.add(p0, 1, 4);
        grid.add(p1, 1, 8);
        grid.add(p2, 1, 12);
        grid.add(p3, 1, 16);

        grid.add(o0, 18, 4);
        grid.add(o1, 18, 8);
        grid.add(o2, 18, 12);
        grid.add(o3, 18, 16);

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof Button) {
            if (((Button) source).getId().equals("Next Round")) {
                presenter.roundStart();
            }
        }
    }

    /**
     * Very messy method to modify the display of a character's status on the UI.
     *
     * @param character the character who needs to be updated on the screen.
     */
    public void updateCharacter(String character){
        if (playerNames[0].equals(character)) {
            if (presenter.givenCharacterFainted(character)) {
                p0.setText("FAINTED");
            }
            playerHealth[0] = presenter.givenCharacterHealth(character);
            playerDmg[0] = presenter.givenCharacterDamage(character);
            p0.setText(playerNames[0] + "\n Hp: " + Integer.toString(playerHealth[0]) + ", Dmg: " +
                Integer.toString(playerDmg[0]));
        } else if (playerNames[1].equals(character)) {
            if (presenter.givenCharacterFainted(character)) {
                p1.setText("FAINTED");
            }
            playerHealth[1] = presenter.givenCharacterHealth(character);
            playerDmg[1] = presenter.givenCharacterDamage(character);
            p1.setText(playerNames[1] + "\n Hp: " + Integer.toString(playerHealth[1]) + ", Dmg: " +
                Integer.toString(playerDmg[1]));
        } else if (playerNames[2].equals(character)) {
            if (presenter.givenCharacterFainted(character)) {
                p2.setText("FAINTED");
            }
            playerHealth[2] = presenter.givenCharacterHealth(character);
            playerDmg[2] = presenter.givenCharacterDamage(character);
            p2.setText(playerNames[2] + "\n Hp: " + Integer.toString(playerHealth[2]) + ", Dmg: " +
                Integer.toString(playerDmg[2]));
        } else if (playerNames[3].equals(character)) {
            if (presenter.givenCharacterFainted(character)) {
                p3.setText("FAINTED");
            }
            playerHealth[3] = presenter.givenCharacterHealth(character);
            playerDmg[3] = presenter.givenCharacterDamage(character);
            p3.setText(playerNames[3] + "\n Hp: " + Integer.toString(playerHealth[3]) + ", Dmg: " +
                Integer.toString(playerDmg[3]));
        } else if (opponentNames[0].equals(character)) {
            if (presenter.givenCharacterFainted(character)) {
                o0.setText("FAINTED");
            }
            opponentHealth[0] = presenter.givenCharacterHealth(character);
            opponentDmg[0] = presenter.givenCharacterDamage(character);
            o0.setText(opponentNames[0] + "\n Hp: " + Integer.toString(opponentHealth[0]) + ", Dmg: " +
                Integer.toString(opponentDmg[0]));
        } else if (opponentNames[1].equals(character)) {
            if (presenter.givenCharacterFainted(character)) {
                o1.setText("FAINTED");
            }
            opponentHealth[1] = presenter.givenCharacterHealth(character);
            opponentDmg[1] = presenter.givenCharacterDamage(character);
            o1.setText(opponentNames[1] + "\n Hp: " + Integer.toString(opponentHealth[1]) + ", Dmg: " +
                Integer.toString(opponentDmg[1]));
        } else if (opponentNames[2].equals(character)) {
            if (presenter.givenCharacterFainted(character)) {
                o2.setText("FAINTED");
            }
            opponentHealth[2] = presenter.givenCharacterHealth(character);
            opponentDmg[2] = presenter.givenCharacterDamage(character);
            o2.setText(opponentNames[2] + "\n Hp: " + Integer.toString(opponentHealth[2]) + ", Dmg: " +
                Integer.toString(opponentDmg[2]));
        } else if (opponentNames[3].equals(character)) {
            if (presenter.givenCharacterFainted(character)) {
                o3.setText("FAINTED");
            }
            opponentHealth[3] = presenter.givenCharacterHealth(character);
            opponentDmg[3] = presenter.givenCharacterDamage(character);
            o3.setText(opponentNames[3] + "\n Hp: " + Integer.toString(opponentHealth[3]) + ", Dmg: " +
                Integer.toString(opponentDmg[3]));
        }
    }
}
