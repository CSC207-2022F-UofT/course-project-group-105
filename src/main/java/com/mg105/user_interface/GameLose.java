package com.mg105.user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameLose extends JFrame {
    int state = 0;
    Image background = Toolkit.getDefaultToolkit().getImage("imgs/gamelose.jpg");
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
            g.drawString("test", 130, 300);
        }
    }

    public static void main(String[] args) {
        GameLose gameLose = new GameLose();
        gameLose.launch();

    }
}
