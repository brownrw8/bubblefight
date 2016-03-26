package com.brownrw8.games.objects.worlds;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.brownrw8.games.environment.GameWorld;
import com.brownrw8.games.environment.Gameplay;
import com.brownrw8.games.environment.Settings;
import com.brownrw8.games.objects.sprites.ISprite;
import com.brownrw8.games.environment.Boundary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AWorld implements IWorld{
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected Boundary boundary;
    protected Gameplay game;
    protected GameWorld world;
    protected float elapsedTime = 0;

    protected static String ROOT_PATH =
            Settings.CORE_ROOT +
                    Settings.SEPARATOR +
                    Settings.ASSET_ROOT +
                    Settings.SEPARATOR +
                    Settings.SPRITE_ROOT +
                    Settings.SEPARATOR;

    protected BitmapFont font;

    public void create(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
        game = Gameplay.getInstance();
        world = GameWorld.getInstance();
        world.setBackground(new Texture(Gdx.files.internal( ROOT_PATH + "background.png" )));
        boundary = Boundary.getInstance();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        drawWorld();
    }

    public void render(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(world.getBackground(), 0, 0);
        if(game.getIsRunning()){
            elapsedTime += Gdx.graphics.getDeltaTime();
            world.getPlayer().draw(batch,elapsedTime);
            world.getCompetitor().draw(batch,elapsedTime);
            List<ISprite> destroyed = new ArrayList<ISprite>();
            for(ISprite sprite : world.getSprites()){
                sprite.draw(batch, elapsedTime);
                if(sprite.getIsCollision(world.getPlayer())){
                    if(elapsedTime<10){
                        continue;
                    }
                    game.addPlayerPoints(0.1);
                    world.getPlayer().steal(sprite);
                    if(sprite.getSize()<5){
                        destroyed.add(sprite);
                    }
                }
                if(sprite.getIsCollision(world.getCompetitor())){
                    if(elapsedTime<10){
                        continue;
                    }
                    game.addCompetitorPoints(0.1);
                    world.getCompetitor().steal(sprite);
                    if(sprite.getSize()<8){
                        destroyed.add(sprite);
                    }
                }
            }
            for(ISprite sprite : destroyed){
                world.removeSprite(sprite);
            }
            if(world.getSprites().isEmpty()){
                game.setState(Gameplay.GAME_OVER);
            }
        }
        else if(game.getIsPaused()){
            font.draw(batch,"Game Paused",boundary.getMaxX()/2,boundary.getMaxY()/2);
            font.draw(batch,"Hit P Key to Resume",boundary.getMinX(),boundary.getMinY());
        }else if(game.getIsGameOver()){
            font.draw(batch,game.getWinner(),boundary.getMaxX()/2,boundary.getMaxY()/2);
            font.draw(batch,"Hit Enter Key to Play Again",boundary.getMinX()+10,boundary.getMinY()+10);
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){

            }
        }
        font.draw(batch,"P1 Score: " + game.getDisplayedPlayerPoints(),boundary.getMinX(),boundary.getMaxY());
        font.draw(batch,"P2 Score: " + game.getDisplayedCompetitorPoints(),boundary.getMaxX()/2,boundary.getMaxY());
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        for(ISprite sprite : world.getSprites()){
            sprite.dispose();
        }
        world.getPlayer().dispose();
        world.getCompetitor().dispose();
        font.dispose();
    }

    protected abstract void drawWorld();
}
