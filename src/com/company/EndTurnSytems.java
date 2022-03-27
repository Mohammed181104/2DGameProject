package com.company;

public class EndTurnSytems {
    public static Resources myResources = new Resources(0,0,0,0,40,false);
    private static boolean gameEnd = false;
    private static GamePanel gp3 = new GamePanel();
    private static int[][] mapTemp = gp3.tileM.mapNum;

    public static void addStone(int numOfMines){
        int total = 0;
        for (int i = 0; i < numOfMines; i++) {
            total += IORsystems.RandomInt(4,1);
        }
        int stone = myResources.getStone() + total/5;
        myResources.setStone(stone);

    }

    public static void addCoal(int numOfMines){
        int total = 0;
        for (int i = 0; i < numOfMines; i++){
            total = total + 10;
        }
        int coal = myResources.getCoal() + total;
        myResources.setCoal(coal);
    }

    public static void addFood(int numOfMines){
        int total = 0;
        for (int i = 0; i < numOfMines; i++){
            total = total + IORsystems.RandomInt(50,10);
        }
        int food = myResources.getFood() + total;
        myResources.setFood(food);
    }

    public static void updateHoused(){
        int numHouses = GameBoard.getNumTiles(2);
        numHouses = numHouses * 10;
        int pop = myResources.getPopulation();
        if (pop > numHouses){
            myResources.setHoused(numHouses);
        }
        else{
            myResources.setHoused(pop);
        }
    }

    public static void addPop(){
        int housed = myResources.getHoused();
        double popAdd = housed * 1.2;
        int popAdd1 = (int) popAdd;
        int pop = myResources.getPopulation();
        pop = pop + popAdd1;
        myResources.setPopulation(pop);
    }

    public static void updateStarving(){
        int food = myResources.getFood();
        int pop = myResources.getPopulation();
        if (pop > food){
            myResources.setStarving(true);
            myResources.setFood(0);
        }
        else{
            food = food - pop;
            myResources.setFood(food);
            myResources.setStarving(false);
        }
    }

    public static void subCoal(){
        int numGen = GameBoard.getNumTiles(3);
        int coal = myResources.getCoal();
        coal = coal - numGen;
        myResources.setCoal(coal);
    }

    public static void subPop(){
        if (myResources.isStarving()){
            int away = IORsystems.RandomInt(20,5);
            int pop = myResources.getPopulation();
            pop = pop - away;
            if (pop <= 0){
                gameEnd = true;
            }
            else{
                myResources.setPopulation(pop);
            }
        }
    }

    public static void gameOver(){
        if (gameEnd){
            //game over
        }
    }
    public static Resources getResources(){
        return myResources;
    }
    public static void setResources(){
        myResources = Buildings.getResources();
    }
}
