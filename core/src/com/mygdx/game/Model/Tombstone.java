package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Loader.A2DAssetManager;

public class Tombstone {

    private float height,width;
    private float x, y;
    private Sprite tombstone;
    private String who;//кто из персонажей
    public Tombstone(float x, float y,float height, float width, String who) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.who = who;
        tombstone = new Sprite(A2DAssetManager.manager.get("Image/tombstone/tombstone" + who + ".png", Texture.class));
        tombstone.setSize(height, width);
        tombstone.setPosition(x, y);
    }
    public Sprite getSprite(){
        return tombstone;
    }
    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public String getWho(){
        return who;
    }
}
