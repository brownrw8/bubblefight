package com.brownrw8.games.objects.sprites.impl;


import com.brownrw8.games.objects.sprites.ASprite;

import java.awt.Point;


public class BasicSprite extends ASprite {

    public BasicSprite(final String name, int size, Point position, int speed){
        super(name,size,position,speed);
    }

    protected void updatePosition(){

    }
}
