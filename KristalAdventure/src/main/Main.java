package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //main frame settings
        JFrame main=new JFrame();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setResizable(false);
        main.setTitle("Kristal Adventures");
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        main.setUndecorated(false);

        //adding the main game panel to the frame and centrating it
        GamePanel gamePanel=new GamePanel();
        main.add(gamePanel);
        main.pack();

        main.setLocationRelativeTo(null);//centrate the screen
        main.setVisible(true);

    }
}