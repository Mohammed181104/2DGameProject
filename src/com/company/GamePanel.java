package com.company;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //216x216 tile
    final int scale = 3;

    final int tileSize = originalTileSize*scale;
    final int maxScreenCol = 30;
    final int maxScreenRow = 30;
    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeight = tileSize*maxScreenRow;
    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
       gameThread = new Thread(this);
       gameThread.start();
    }

    @Override
    public void run() {

        while(gameThread != null){

            //System.out.println("This is working");
            // 1 update = info such as character position
            update();
            //2 Draw = draw screen with updated position



        }

    }
    public void update(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }
}
