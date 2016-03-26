package com.brownrw8.games.objects.sprites.impl;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.awt.*;


public class CompetitorBubbleSprite extends BubbleSprite {

    public CompetitorBubbleSprite(int size, Point position, int speed){
        super(null,size,position,speed);
    }

    protected void create(){
        if(!textureAtlases.containsKey(ROOT_PATH + "competitor_bubble" + EXT )){
            textureAtlases.put(ROOT_PATH + "competitor_bubble" + EXT , new TextureAtlas(Gdx.files.internal( ROOT_PATH + "competitor_bubble" + EXT  )));
        }
        animation = new Animation(1/(fps/8), textureAtlases.get(ROOT_PATH + "competitor_bubble" + EXT ).getRegions());
    }

    protected void updatePosition(){
        int stepX = 0;
        int stepY = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            stepX = -speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            stepX = speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            stepY = speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            stepY = -speed;
        }

        Point newPosition = new Point(stepX + position.x,stepY + position.y);

        if(newPosition.x >= boundary.getMaxX()-size || newPosition.x <= boundary.getMinX()){
            position.x -= stepX;
        }else{
            position.x += stepX;
        }
        if(newPosition.y >= boundary.getMaxY()-size || newPosition.y <= boundary.getMinY()){
            position.y -= stepY;
        }else{
            position.y += stepY;
        }

    }

}
