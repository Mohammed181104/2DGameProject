package com.company;

public class Resources {
    private int population;
    private int housed;
    private int coal;
    private int food;
    private int stone;
    private boolean starving;

    public Resources(int population, int housed, int coal, int food, int stone, boolean starving) {
        this.population = population;
        this.housed = housed;
        this.coal = coal;
        this.food = food;
        this.stone = stone;
        this.starving = starving;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "population=" + population +
                ", housed=" + housed +
                ", coal=" + coal +
                ", food=" + food +
                ", stone=" + stone +
                ", starving=" + starving +
                '}';
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getHoused() {
        return housed;
    }

    public void setHoused(int housed) {
        this.housed = housed;
    }

    public int getCoal() {
        return coal;
    }

    public void setCoal(int coal) {
        this.coal = coal;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getStone() {
        return stone;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public boolean isStarving() {
        return starving;
    }

    public void setStarving(boolean starving) {
        this.starving = starving;
    }

}