package com.mygdx.game.Views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Views.MenuScreen;

public class GameMenuScreen implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Table table;
    private Stage stage;
    private Skin skint;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private MyGdxGame parent;
    private Skin skin;
    private TextButton continueB;
    private TextButton preferencesB;
    private TextButton menuB;
    private Label pause;
    private ClickListener continueListener;
    private ClickListener preferencesListener;
    private ClickListener menuListener;
    private float pressedTime;//время работы активности
    public GameMenuScreen(MyGdxGame parent, SpriteBatch batch) {
        this.batch = batch;
        this.parent = parent;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        skint = new Skin(Gdx.files.internal("skin/Clean_Crispy_UI_Skin//cleancrispyui/clean-crispy-ui.json"));
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        pause = new Label("Pause", skin, "title");
        pause.setFontScale(1.2f * k);
        pause.setPosition(550 * w, 640 * k);
        continueB = new TextButton("Continue", skin);
        continueB.getLabel().setFontScale(1.2f * k);
        continueB.setBounds(300 * w, 380 * k, 680 * w, 130 * k);
        preferencesB = new TextButton("Preferences", skin);
        preferencesB.getLabel().setFontScale(1.2f * k);
        preferencesB.setBounds(300 * w, 225 * k, 680 * w, 130 * k);
        menuB = new TextButton("Menu", skin);
        menuB.getLabel().setFontScale(1.2f * k);
        pressedTime = 0;
        menuB.setBounds(300 * w, 70 * k, 680 * w, 130 * k);
        continueListener =  new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                parent.changeScreen(MyGdxGame.GAMEPLAY);
            }
        };
        preferencesListener =  new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                parent.setPref(true);
                parent.changeScreen(MyGdxGame.PREFERENCES);
                viewport = new FitViewport(1280 * w, 720 * k, cam);
                cam.setToOrtho(false, 1280 * w, 720 * k);
            }
        };
        menuListener =  new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                ExitDialog exitDia = new ExitDialog("Confirm exit", skint);
                exitDia.show(stage);
                exitDia.setPosition(400 * w, 100 * k);
            }
        };
        continueB.addListener(continueListener);
        preferencesB.addListener(preferencesListener);
        menuB.addListener(menuListener);
        table.addActor(continueB);
        table.addActor(preferencesB);
        table.addActor(menuB);
        table.addActor(pause);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        pressedTime += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && pressedTime >= 0.4f) {
            pressedTime = 0;
            parent.changeScreen(MyGdxGame.GAMEPLAY);
        }
        batch.begin();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    }
    public static class ExitDialog extends Dialog {
        private Skin skin;
        private float k = MyGdxGame.k;
        private float w = MyGdxGame.w;
        private MyGdxGame parent = MenuScreen.parent;

        public ExitDialog(String title, Skin skin) {
            super(title, skin);
        }

        {
            text("Do you really want to leave?").setScale(2.2f * w, 2.2f * k);
            button("Yes", "Yes").setScale(2.2f * w, 2.2f * k);
            button("No", "No").setScale(2.2f * w, 2.2f * k);
        }

        @Override
        protected void result(Object object) {
            if ("Yes".equals(object.toString())) {
                parent.getGameScreen().saveFirst();
                parent.destroy();
                parent.changeScreen(MyGdxGame.MENU);
            }
        }
    }
}
