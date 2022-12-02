package com.mg105.user_interface;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameLoseWindow extends JFrame {
    int state = 0;

//    Scene deadScene = new Scene(new VBox());
    Image background = Toolkit.getDefaultToolkit().getImage("imgs/background2.jpg");
    public void launch() {
        setVisible(true);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("You Die");
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1){
                    state = 1;
                    repaint();
                }

            }
        });

    }

    @Override
    public void paint(Graphics g){
        if(state == 0) {
            g.drawImage(background, 0, 0, null);
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", Font.BOLD, 10));
            g.drawString("you know there's no achievement for operating the controller with you feet right?", 130, 100);
            g.drawString("Left click for replaying the game", 230, 300);
        }
        if(state == 1)
        {
            g.drawImage(background, 0, 0, null);
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", Font.BOLD, 10));
            g.drawString("Game Replay Success", 130, 300);
        }
    }

    public static void main(String[] args) {
        GameLoseWindow gameLoseWindow = new GameLoseWindow();
        gameLoseWindow.launch();

    }
}
