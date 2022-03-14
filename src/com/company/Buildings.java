package com.company;

public class Buildings { // all need to use up resources and other requirements
    private static Resources myResources = EndTurnSytems.getResources();

    public static void placeHouse(int x, int y) {
        Tiles myTile = GameBoard.searchGrid(x, y);
        int stone = myResources.getStone();
        stone = stone - 10; // the building cost is 10 stone
        boolean valid = true;
        if (stone < 0){
            valid = false;
        }
        if (myTile.getType() == 0 && myTile.isLight() && valid) {
            myTile.setType(2);
            myResources.setStone(stone);
            EndTurnSytems.setResources();

        } else {
            System.out.println("cannot place");
        }
    }

    public static void placeLight(int x, int y) {
        Tiles myTile = GameBoard.searchGrid(x, y);
        int NumGens = GameBoard.getNumTiles(3);
        int NumLights = GameBoard.getNumTiles(3);
        int stone = myResources.getStone();
        stone = stone - 10; // the building cost is 10 stone
        boolean valid = true;
        if (stone < 0){
            valid = false;
        }
        if (NumGens >= NumLights){
            System.out.println("cannot place, not enough generators");
        }
        else if (myTile.getType() == 0 && myTile.isLight() && valid) {
            myTile.setType(5);
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 4; j++){
                    Tiles myTile1 = GameBoard.searchGrid(x+i, y+j);
                    Tiles myTile2 = GameBoard.searchGrid(x-i, y+j);
                    Tiles myTile3 = GameBoard.searchGrid(x+i, y-j);
                    Tiles myTile4 = GameBoard.searchGrid(x-i, y-j);
                    myTile1.setLight(true);
                    myTile2.setLight(true);
                    myTile3.setLight(true);
                    myTile4.setLight(true);
                    myResources.setStone(stone);
                    EndTurnSytems.setResources();
                }
            }
        } else {
            System.out.println("cannot place");
        }
    }

    public static void placeGen(int x, int y) {
        Tiles myTile = GameBoard.searchGrid(x, y);
        Tiles myTile1 = GameBoard.searchGrid(x + 1, y);
        int stone = myResources.getStone();
        stone = stone - 10; // the building cost is 10 stone
        boolean valid = true;
        int coal = myResources.getCoal();
        coal = coal - 2; // generators use 2 coal a turn
        if (stone < 0 || coal < 0){
            valid = false;
        }
        if (myTile.getType() == 0 && myTile.isLight() && myTile1.getType() == 0 && myTile1.isLight() && valid) {
            myTile.setType(3);
            myTile1.setType(3);
        } else {
            System.out.println("cannot place");
        }
    }

    public static void placeMine(int x, int y) {
        Tiles myTile = GameBoard.searchGrid(x, y);
        Tiles myTile1 = GameBoard.searchGrid(x + 1, y);
        Tiles myTile2 = GameBoard.searchGrid(x, y + 1);
        Tiles myTile3 = GameBoard.searchGrid(x + 1, y + 1);
        int stone = myResources.getStone();
        stone = stone - 10; // the building cost is 10 stone
        boolean valid = true;
        if (stone < 0){
            valid = false;
        }
        if (myTile.getType() == 0 && myTile.isLight() && myTile1.getType() == 0 && myTile1.isLight() && myTile2.getType() == 0 && myTile2.isLight() && myTile3.getType() == 0 && myTile3.isLight() && valid) {
            myTile.setType(6);
            myTile1.setType(6);
            myTile2.setType(6);
            myTile3.setType(6);
            myResources.setStone(stone);
            EndTurnSytems.setResources();
        } else {
            System.out.println("cannot place");
        }
    }

    public static void placeFarm(int x, int y) {
        Tiles myTile = GameBoard.searchGrid(x, y);
        Tiles myTile1 = GameBoard.searchGrid(x + 1, y);
        Tiles myTile2 = GameBoard.searchGrid(x, y + 1);
        Tiles myTile3 = GameBoard.searchGrid(x + 1, y + 1);
        Tiles myTile4 = GameBoard.searchGrid(x - 1, y);
        Tiles myTile5 = GameBoard.searchGrid(x, y - 1);
        Tiles myTile6 = GameBoard.searchGrid(x - 1, y - 1);
        Tiles myTile7 = GameBoard.searchGrid(x + 1, y - 1);
        Tiles myTile8 = GameBoard.searchGrid(x - 1, y + 1);
        int stone = myResources.getStone();
        stone = stone - 10; // the building cost is 10 stone
        boolean valid = true;
        if (stone < 0){
            valid = false;
        }
        if (myTile.getType() == 0 && myTile.isLight() && myTile1.getType() == 0 && myTile1.isLight() && myTile2.getType() == 0 && myTile2.isLight() && myTile3.getType() == 0 && myTile3.isLight() && myTile4.getType() == 0 && myTile4.isLight() && myTile5.getType() == 0 && myTile5.isLight() && myTile6.getType() == 0 && myTile6.isLight() && myTile7.getType() == 0 && myTile7.isLight() && myTile8.getType() == 0 && myTile8.isLight() && valid) {
            myTile.setType(4);
            myTile1.setType(4);
            myTile2.setType(4);
            myTile3.setType(4);
            myTile4.setType(4);
            myTile5.setType(4);
            myTile6.setType(4);
            myTile7.setType(4);
            myTile8.setType(4);
            myResources.setStone(stone);
            EndTurnSytems.setResources();
        } else {
            System.out.println("cannot place");
        }
    }

    public static Resources getResources(){
        return myResources;
    }
}