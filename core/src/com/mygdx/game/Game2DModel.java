package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Game2DModel {
    public World world;

    public Game2DModel() {
        world = new World(new Vector2(0, -10f), true);
    }
    public void stepLogic (float delta){
        world.step(delta, 3, 3);
    }
}
