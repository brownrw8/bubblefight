package com.brownrw8.games.environment;

import com.badlogic.gdx.graphics.Texture;
import com.brownrw8.games.objects.sprites.ISprite;
import com.brownrw8.games.objects.sprites.impl.CompetitorBubbleSprite;
import com.brownrw8.games.objects.sprites.impl.PlayerBubbleSprite;

import java.util.ArrayList;
import java.util.List;


public class GameWorld {
    private static GameWorld ourInstance = new GameWorld();

    public static GameWorld getInstance() {
        return ourInstance;
    }

    protected Texture background;

    protected List<ISprite> sprites;

    protected PlayerBubbleSprite player;

    protected CompetitorBubbleSprite competitor;

    public PlayerBubbleSprite getPlayer() {
        return player;
    }

    public void setPlayer(PlayerBubbleSprite player) {
        this.player = player;
    }

    public CompetitorBubbleSprite getCompetitor() {
        return competitor;
    }

    public void setCompetitor(CompetitorBubbleSprite competitor) {
        this.competitor = competitor;
    }

    public List<ISprite> getSprites() {
        return sprites;
    }

    public void addSprite(ISprite sprite) {
        this.sprites.add(sprite);
    }

    public void removeSprite(ISprite sprite) {
        this.sprites.remove(sprite);
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    private GameWorld() {
        sprites = new ArrayList<ISprite>();
    }
}
