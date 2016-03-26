package com.brownrw8.games.objects.sprites.impl;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.brownrw8.games.objects.sprites.ASprite;

import java.awt.*;


public class BubbleSprite extends ASprite {

    public BubbleSprite(final String name, int size, Point position, int speed){
        super(name,size,position,speed);
    }

    protected void create(){
        if(!textureAtlases.containsKey(ROOT_PATH + this.name + "_bubble" + EXT )){
            textureAtlases.put(ROOT_PATH + this.name + "_bubble" + EXT , new TextureAtlas(Gdx.files.internal( ROOT_PATH + this.name + "_bubble" + EXT  )));
        }
        animation = new Animation(1/(fps/4), textureAtlases.get(ROOT_PATH + this.name + "_bubble" + EXT ).getRegions());
    }

    protected void updatePosition(){
        int stepX = -speed + (int)(Math.random() * ((speed - -speed) + 1));
        int stepY = -speed + (int)(Math.random() * ((speed - -speed) + 1));
        int chanceY = -speed + (int)(Math.random() * ((speed - -speed) + 1));
        Point newPosition = new Point(stepX + position.x,stepY + position.y);

        if(newPosition.x >= boundary.getMaxX()-size || newPosition.x <= boundary.getMinX()){
            position.x -= stepX;
        }else{
            position.x += stepX;
        }
        if(chanceY>0){
            if(newPosition.y >= boundary.getMaxY()-size || newPosition.y <= boundary.getMinY()){
                position.y -= stepY;
            }else{
                position.y += stepY;
            }
        }
    }

}
