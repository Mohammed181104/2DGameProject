package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, onePressed, twoPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_1){
            onePressed = true;
        }
        if(code == KeyEvent.VK_2){
            twoPressed = true;
            delay();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
        if(code == KeyEvent.VK_1){
            onePressed = false;
        }
        if(code == KeyEvent.VK_2){
            twoPressed = false;
        }
    }
    public void delay(){
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
