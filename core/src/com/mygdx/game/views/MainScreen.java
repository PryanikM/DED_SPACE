package com.mygdx.game.views;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Game2DModel;
import com.mygdx.game.MyGdxGame;

public class MainScreen implements Screen {
    private MyGdxGame parent;
    private Game2DModel model2D;
    private OrthographicCamera camera;
    public MainScreen(MyGdxGame myGdxGame) {
        parent = myGdxGame;
        model2D = new Game2DModel();
        camera = new OrthographicCamera(32, 24);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
