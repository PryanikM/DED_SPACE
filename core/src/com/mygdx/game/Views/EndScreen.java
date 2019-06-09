package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Loader.A2DAssetManager;

public class EndScreen implements Screen {
    private MyGdxGame parent;
    private Sprite endSprite;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private Label days;
    private float time;
    private float maxTime;
    private  FreeTypeFontGenerator generator;
//    private Skin skin;
    private int daysC;
    private BitmapFont font;
    public EndScreen(MyGdxGame myGdxGame, int daysC, SpriteBatch batch) {
        parent = myGdxGame;
        this.batch = batch;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25 * (int)MyGdxGame.k;
        cam = new OrthographicCamera();
        this.daysC = daysC;
        font = generator.generateFont(parameter);
        cam.setToOrtho(false, 500 * MyGdxGame.w, 281 * MyGdxGame.k);
//        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
//        batch = new SpriteBatch();
        time = 0;
        maxTime = 4f; }

    @Override
    public void show() {
        endSprite = new Sprite(A2DAssetManager.manager.get(A2DAssetManager.end, Texture.class));
        endSprite.setSize(500 * MyGdxGame.w, 281 * MyGdxGame.k);
        endSprite.setPosition(0,0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        check(delta);
        endSprite.draw(batch);
        font.draw(batch, "lived days:  " + daysC, 175 * MyGdxGame.w, 40 * MyGdxGame.k);
        batch.end();
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
        generator.dispose();
    }
    private void check(float delta){//очистка ресурсов, после смерти
        if (time >= maxTime){
            parent.setMenu(false);
//            parent.getGameScreen().setNull();
//            parent.getGameScreen().getMainManager().setCountDay(1);
//            parent.getGameScreen().getMainManager().setNullFood();
//            parent.getGameScreen().getMainManager().setNullWater();
            parent.destroy();
            parent.changeScreen(MyGdxGame.MENU);
        }
        else{
            time += delta;
        }
    }
}
