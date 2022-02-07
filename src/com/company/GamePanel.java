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

    //FPS
    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = tileSize;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
       gameThread = new Thread(this);
       gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 6000/fps;
        double delta = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;
        while(gameThread != null){
            currentTime = System.currentTimeMillis();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }

    }
    // 1 update = info such as character position
    public void update(){
        if (keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        if (keyH.downPressed == true){
            playerY += playerSpeed;
        }
        if (keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        if (keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }
    //2 Draw = draw screen with updated position
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
    }
}
