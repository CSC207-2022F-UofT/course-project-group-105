package com.mg105.user_interface;

import javax.swing.*;
import java.awt.*;

public class GameStartWindow extends JFrame {

    public static final String[] MENUS = {"Start Game",
        "Game Help",
        "About Us",
        "Quit"};

    public void launch() {
        setVisible(true);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("Game Start Window");
    }

    private void drawMenu(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 1000, 600);

        g.setColor(Color.WHITE);
        for (int i = 0; i < MENUS.length; i++) {

        }
    }
}
