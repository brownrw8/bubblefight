package com.brownrw8.games.objects.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.brownrw8.games.environment.Boundary;
import com.brownrw8.games.environment.Settings;
import com.sun.prism.TextureMap;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public abstract class ASprite implements ISprite{

    protected java.util.HashMap<String,TextureAtlas> textureAtlases;
    private int currentFrame = 1;
    private String currentAtlasKey;
    protected Animation animation;
    protected Boundary boundary;

    protected static String ROOT_PATH =
                            Settings.CORE_ROOT +
                            Settings.SEPARATOR +
                            Settings.ASSET_ROOT +
                            Settings.SEPARATOR +
                            Settings.SPRITE_ROOT +
                            Settings.SEPARATOR;

    protected static String EXT = Settings.SPRITE_EXT;

    protected int size = 1;
    protected Point position;
    protected String name;
    protected int speed = 1;
    protected float fps = 60f;

    public ASprite(final String name, int size, Point position, int speed){
        textureAtlases = new HashMap<String, TextureAtlas>();
        this.boundary = Boundary.getInstance();
        this.size = size;
        this.speed = speed;
        this.position = position;
        this.name = name;
        create();
    }

    protected void create(){
        if(!textureAtlases.containsKey(ROOT_PATH + name + EXT)){
            textureAtlases.put(ROOT_PATH + name + EXT, new TextureAtlas(Gdx.files.internal( ROOT_PATH + name + EXT )));
        }
        animation = new Animation(1/fps, textureAtlases.get(ROOT_PATH + name + EXT).getRegions());
    }

    protected abstract void updatePosition();

    public Point getPosition(){
        return this.position;
    }

    public boolean getIsCollision(ASprite sprite){
        return position.x < sprite.position.x + sprite.size && position.x + size > sprite.position.x && position.y < sprite.position.y + sprite.size && position.y + size > sprite.position.y;
    }

    protected float getRadius(){
        return size/2;
    }

    public int getSize(){
        return this.size;
    }

    public void shrink(){
        this.size-=1;
    }

    public void steal(ISprite victim){
        victim.shrink();
    }

    public void draw(SpriteBatch batch, float elapsedTime){
        updatePosition();
        batch.draw(animation.getKeyFrame(elapsedTime, true), position.x, position.y, size, size);
    }

    public void dispose(){
        Iterator it = textureAtlases.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            TextureAtlas textureAtlas = (TextureAtlas) pair.getValue();
            textureAtlas.dispose();
            it.remove();
        }
    }

}
