package com.brownrw8.games.objects.sprites.impl;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.brownrw8.games.objects.sprites.ASprite;

import java.awt.*;


public class PlayerBubbleSprite extends BubbleSprite {

    public PlayerBubbleSprite(int size, Point position, int speed){
        super(null,size,position,speed);
    }

    protected void create(){
        if(!textureAtlases.containsKey(ROOT_PATH + "player_bubble" + EXT )){
            textureAtlases.put(ROOT_PATH + "player_bubble" + EXT , new TextureAtlas(Gdx.files.internal( ROOT_PATH + "player_bubble" + EXT  )));
        }
        animation = new Animation(1/(fps/8), textureAtlases.get(ROOT_PATH + "player_bubble" + EXT ).getRegions());
    }

    protected void updatePosition(){
        int stepX = 0;
        int stepY = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            stepX = -speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            stepX = speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            stepY = speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
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
