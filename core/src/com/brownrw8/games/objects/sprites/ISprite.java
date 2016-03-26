package com.brownrw8.games.objects.sprites;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public interface ISprite {

    void dispose();
    void draw(SpriteBatch batch, float elapsedTime);
    Point getPosition();
    boolean getIsCollision(ASprite sprite);
    void steal(ISprite victim);
    void shrink();
    int getSize();

}
