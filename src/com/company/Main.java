package com.company;


import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame scene = new JFrame();
        scene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scene.setResizable(false);
        scene.setTitle("2DThing");


        GamePanel gamePanel = new GamePanel();
        scene.add(gamePanel);
        scene.pack();

        scene.setLocationRelativeTo(null);
        scene.setVisible(true);

        gamePanel.startGameThread();

    }
}
