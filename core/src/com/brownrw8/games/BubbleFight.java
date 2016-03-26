package com.brownrw8.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.brownrw8.games.objects.worlds.IWorld;
import com.brownrw8.games.objects.worlds.impl.BasicWorld;

public class BubbleFight extends ApplicationAdapter {
	IWorld world;
	
	@Override
	public void create () {
		world = new BasicWorld();
		world.create();
	}

	@Override
	public void dispose() {
		world.dispose();
	}

		@Override
	public void render () {
		world.render();
	}
}
