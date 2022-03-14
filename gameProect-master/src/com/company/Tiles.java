package com.company;

public class Tiles {
    private boolean light;
    private int type; // e.g. "wall" - 1, "house" - 2, "generator" - 3, "empty" - 0, "farm" - 4, "light" - 5, "mine" - 6
    private int x;
    private int y;

    public Tiles(boolean light, int type, int x, int y) {
        this.light = light;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Tiles{" +
                "light=" + light +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

