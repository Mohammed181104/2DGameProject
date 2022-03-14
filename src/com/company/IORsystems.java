package com.company;

import java.util.Random;

public class IORsystems { //input output random systems
    public static int RandomInt(int max, int min){
        Random r = new Random();
        return r.nextInt(max-min+1)+min;
    }
}
