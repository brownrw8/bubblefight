package com.brownrw8.games.objects.worlds.impl;

import com.brownrw8.games.objects.sprites.impl.BubbleSprite;
import com.brownrw8.games.objects.sprites.impl.CompetitorBubbleSprite;
import com.brownrw8.games.objects.sprites.impl.PlayerBubbleSprite;
import com.brownrw8.games.objects.worlds.AWorld;

import java.awt.*;
import java.util.ArrayList;


public class BasicWorld extends AWorld {

    protected ArrayList<String> colors = new ArrayList<String>() {{
        add("yellow");
        add("purple");
        add("white");
    }};

    protected void drawWorld(){
        addBubbles(800,30,3);
        int defaultSize = 50;
        Point player = new Point(
                boundary.getMinX() + (int)(Math.random() * ((boundary.getMaxX()-defaultSize - boundary.getMinX()) + 1)),
                boundary.getMinY() + (int)(Math.random() * ((boundary.getMaxY()-defaultSize - boundary.getMinY()) + 1))
        );
        world.setPlayer(new PlayerBubbleSprite(50,player,4));
        Point competitor = new Point(
                boundary.getMinX() + (int)(Math.random() * ((boundary.getMaxX()-defaultSize - boundary.getMinX()) + 1)),
                boundary.getMinY() + (int)(Math.random() * ((boundary.getMaxY()-defaultSize - boundary.getMinY()) + 1))
        );
        world.setCompetitor(new CompetitorBubbleSprite(50,competitor,4));
    }

    private void addBubbles(int count,int size,int speed){
        int i = 0;
        while (i<count) {
            int index = (int)(Math.random() * ((2) + 1));
            int x = boundary.getMinX() + (int)(Math.random() * ((boundary.getMaxX()-size - boundary.getMinX()) + 1));
            int y = boundary.getMinY() + (int)(Math.random() * ((boundary.getMaxY()-size - boundary.getMinY()) + 1));
            String color = colors.get(index);
            Point position = new Point(x,y);
            BubbleSprite sprite = new BubbleSprite(color,size,position,speed);
            world.addSprite(sprite);
            i++;
        }
    }

}
