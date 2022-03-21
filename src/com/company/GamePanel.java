package com.company;

import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //216x216 tile
    final int scale = 3;
    private Random random = new Random();
    private int xRandStart = random.nextInt(20)+5;
    private int yRandStart = random.nextInt(10)+5;
    public final int tileSize = originalTileSize*scale;
    public final int maxScreenCol = 30;
    public final int maxScreenRow = 20;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;
    public int locationX = 0;
    public int locationY = 0;
    public int tempLocationX = 0;
    public int tempLocationY = 0;
    public int mineNum = 0;

    //FPS
    int fps = 60;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set players default position
    int playerX = 0;
    int playerY = 0;
    public int playerSpeed = tileSize;

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
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null){
            currentTime = System.nanoTime();
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
        tempLocationY = locationY;
        tempLocationX = locationX;
        if (keyH.upPressed){
            delay();
            playerY -= playerSpeed;
            locationY --;
        }
        if (keyH.downPressed){
            delay();
            playerY += playerSpeed;
            locationY ++;
        }
        if (keyH.leftPressed){
            delay();
            playerX -= playerSpeed;
            locationX --;
        }
        if (keyH.rightPressed){
            delay();
            playerX += playerSpeed;
            locationX ++;
        }
        if(tempLocationY!=locationY || tempLocationX!=locationX){
            System.out.println(locationX+","+locationY);
            System.out.println(EndTurnSytems.myResources.toString());
            EndTurnSytems.addStone();
        }
        if(keyH.twoPressed){
            mineNum++;
        }



    }
    //2 Draw = draw screen with updated position
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.generateStartingZone(g2,xRandStart,yRandStart);
        tileM.draw(g2,locationX,locationY);
        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,tileSize,tileSize);
    }

    //Adds delay to movement
    public void delay(){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
