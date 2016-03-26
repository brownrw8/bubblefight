package com.brownrw8.games.environment;

import com.badlogic.gdx.Gdx;

import java.awt.*;


public class Boundary {
    private static Boundary ourInstance = new Boundary();
    private Point max = new Point(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    private Point min = new Point(0,0);

    public static Boundary getInstance() {
        return ourInstance;
    }

    private Boundary() {
    }

    public Point getMin(){
        return min;
    }

    public Point getMax(){
        return max;
    }

    public int getMinX(){
        return min.x;
    }

    public int getMaxX(){
        return max.x;
    }

    public int getMinY(){
        return min.y;
    }

    public int getMaxY(){
        return max.y;
    }
}
