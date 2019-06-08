package com.mygdx.game.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class assetsScreen implements Screen {
    private MyGdxGame parent;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Texture rocketTexture;
    private Sprite rocketSprite;
    private float time = 0;
    private float speed;
    private Boolean check;
    private float height;
    public assetsScreen(MyGdxGame parent, SpriteBatch batch) {
        this.parent = parent;
        this.batch = batch;
        cam = new OrthographicCamera();
        height = 0;
        speed = 15f * MyGdxGame.k;
        check = false;
        cam.setToOrtho(false, 1280 * MyGdxGame.w , 720 * MyGdxGame.k);
        A2DAssetManager.load();
    }

    @Override
    public void show() {
        rocketTexture = new Texture("Image/rocketLoad.png");
        rocketSprite = new Sprite(rocketTexture);
        rocketSprite.setBounds(615f * MyGdxGame.w, -54f * MyGdxGame.k, 50f * MyGdxGame.w, 300f * MyGdxGame.k);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (A2DAssetManager.manager.update()) {
            playAnim();
            time += Gdx.graphics.getDeltaTime();
            if (time >= 0.1f) {
                parent.setMusic();
                parent.changeScreen(MyGdxGame.MENU);
            }
            rocketSprite.setPosition(615f * MyGdxGame.w, height);
            rocketSprite.draw(batch);
            batch.end();
        }
        else {
            batch.setProjectionMatrix(cam.combined);
            playAnim();
            rocketSprite.setPosition(615f * MyGdxGame.w, height);
            rocketSprite.draw(batch);
            batch.end();
        }

    }
    public void playAnim(){
        if (height < 900 * MyGdxGame.k){
            height += speed;
        }
        else{
            height = -300 * MyGdxGame.k;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
