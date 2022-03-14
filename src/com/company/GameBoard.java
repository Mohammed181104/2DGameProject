package com.company;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private static ArrayList<Tiles> grid = new ArrayList<>();
    private static int gridSize = 128;

    public static void createGrid(){
        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                Tiles myTile = new Tiles(false, 0, i, j);
                grid.add(myTile);
            }
        }
    }
    public static void createBorder(){
        int size = grid.size();
        for (int i = 0; i < size; i++){
            Tiles myTile = grid.get(i);
            if (myTile.getX() == 0 || myTile.getX() == 127){
                myTile.setType(1);
            }
            else if (myTile.getY() == 0 || myTile.getY() == 127){
                myTile.setType(1);
            }
        }
    }
    public static void createRocks(int size){
        int xCoord = IORsystems.RandomInt(gridSize-2,1);
        int yCoord = IORsystems.RandomInt(gridSize-2,1);
        Tiles myTile = searchGrid(xCoord,yCoord);
        if (size == 1){
            myTile.setType(1);
        }
        else if (size == 2){
            myTile.setType(1);
            Tiles myTile1 = searchGrid(xCoord+1,yCoord);
            Tiles myTile2 = searchGrid(xCoord,yCoord+1);
            Tiles myTile3 = searchGrid(xCoord+1,yCoord+1);
            myTile1.setType(1);
            myTile2.setType(1);
            myTile3.setType(1);
        }
        else{
            myTile.setType(1);
            Tiles myTile1 = searchGrid(xCoord+1,yCoord);
            Tiles myTile2 = searchGrid(xCoord,yCoord+1);
            Tiles myTile3 = searchGrid(xCoord+1,yCoord+1);
            Tiles myTile4 = searchGrid(xCoord-1,yCoord);
            Tiles myTile5 = searchGrid(xCoord,yCoord-1);
            Tiles myTile6 = searchGrid(xCoord-1,yCoord-1);
            Tiles myTile7 = searchGrid(xCoord-1,yCoord+1);
            Tiles myTile8 = searchGrid(xCoord+1,yCoord-1);
            myTile1.setType(1);
            myTile2.setType(1);
            myTile3.setType(1);
            myTile4.setType(1);
            myTile5.setType(1);
            myTile6.setType(1);
            myTile7.setType(1);
            myTile8.setType(1);
        }
    }

    public static Tiles searchGrid(int x, int y){
        int size = grid.size();
        boolean found = false;
        int count = 0;
        Tiles outTile = new Tiles(false,-1,-1,-1);
        while (!found && count < size){
            Tiles myTile = grid.get(count);
            if (myTile.getX() == x && myTile.getY() == y){
                found = true;
                outTile = myTile;
            }
            count++;
        }
        return outTile;
    }

    public static int getNumTiles(int type){
        int size = grid.size();
        int count = 0;
        for (int i = 0; i < size; i++){
            Tiles myTile = grid.get(count);
            if (myTile.getType() == type){
                count++;
            }
        }
        return count;
    }

    public static void generateRocks(){
        int sRocks = IORsystems.RandomInt(30,15); // maximum and minimum number of rocks
        int mRocks = IORsystems.RandomInt(20,10);
        int lRocks = IORsystems.RandomInt(10,5);
        for (int i = 0; i < sRocks; i++){
            createRocks(1);
        }
        for (int i = 0; i < mRocks; i++){
            createRocks(2);
        }
        for (int i = 0; i < lRocks; i++){
            createRocks(3);
        }
    }

    public static void generateStartingZone(){ // creates an 8 x 8 area of light
        int xCoord = IORsystems.RandomInt(gridSize-9,1);
        int yCoord = IORsystems.RandomInt(gridSize-9,1);
        for (int i = 0; i < 8; i++){
            Tiles myTile = searchGrid(xCoord, yCoord+i);
            Tiles myTile1 = searchGrid(xCoord+1, yCoord+i);
            Tiles myTile2 = searchGrid(xCoord+2, yCoord+i);
            Tiles myTile3 = searchGrid(xCoord+3, yCoord+i);
            Tiles myTile4 = searchGrid(xCoord+4, yCoord+i);
            Tiles myTile5 = searchGrid(xCoord+5, yCoord+i);
            Tiles myTile6 = searchGrid(xCoord+6, yCoord+i);
            Tiles myTile7 = searchGrid(xCoord+7, yCoord+i);
            myTile.setLight(true);
            myTile1.setLight(true);
            myTile2.setLight(true);
            myTile3.setLight(true);
            myTile4.setLight(true);
            myTile5.setLight(true);
            myTile6.setLight(true);
            myTile7.setLight(true);
        }
    }
}
