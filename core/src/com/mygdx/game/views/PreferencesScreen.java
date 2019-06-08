package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

public class PreferencesScreen implements Screen {
    private MyGdxGame parent;
    private Stage stage;
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private float pressedTime;//время, прошедшее с начала работы экрана
    private FitViewport viewport;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    public PreferencesScreen(MyGdxGame myGdxGame, SpriteBatch batch) {
        this.parent = myGdxGame;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        this.batch = batch;
        cam = new OrthographicCamera();
        viewport = new FitViewport(300, 300, cam);
        cam.setToOrtho(false, 300, 300);
        stage = new Stage(viewport);
        pressedTime = 0;
    }
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(this.stage);
//        cam.setToOrtho(false, 300, 300);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
//        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Skin skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        final Slider volumeMusicSlider = new Slider(0.0F, 1.0F, 0.1F, false, skin);
//        volumeMusicSlider.setBounds(660 * k, 370 * k,200 * k,100 * k);
        volumeMusicSlider.setValue(this.parent.getPreferences().getMusicVolume());
        volumeMusicSlider.addListener(new EventListener() {
            public boolean handle(Event event) {
                PreferencesScreen.this.parent.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                return false;
            }
        });
        final Slider soundMusicSlider = new Slider(0.0F, 1.0F, 0.1F, false, skin);
        soundMusicSlider.setValue(this.parent.getPreferences().getSoundsVolume());
        soundMusicSlider.addListener(new EventListener() {
            public boolean handle(Event event) {
                PreferencesScreen.this.parent.getPreferences().setSoundsVolume(soundMusicSlider.getValue());
                return false;
            }
        });
        final CheckBox musicCheckbox = new CheckBox(null, skin);
//        musicCheckbox.setBounds(760 * k,330 * k,100 * k,100 * k);
        musicCheckbox.setChecked(this.parent.getPreferences().isMusicEnable());
        musicCheckbox.addListener(new EventListener() {
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                PreferencesScreen.this.parent.getPreferences().setMusicEnable(enabled);
                return false;
            }
        });
        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.setChecked(this.parent.getPreferences().isSoundEffectsEnable());
        soundEffectsCheckbox.addListener(new EventListener() {
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                PreferencesScreen.this.parent.getPreferences().setSoundEffectsEnable(enabled);
                return false;
            }
        });
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                if (!parent.getPref()) {
                    parent.changeScreen(MyGdxGame.MENU);
                }
                else {
                    cam.setToOrtho(false, 1280 * w, 720 * k);
                    parent.changeScreen(MyGdxGame.GAMEMENU);
                }
            }
        });
        this.titleLabel = new Label("Preferences", skin);
        this.volumeMusicLabel = new Label("Music Volume", skin);
        this.volumeSoundLabel = new Label("Sound Volume", skin);
        this.musicOnOffLabel = new Label("Music", skin);
        this.soundOnOffLabel = new Label("Sound Effect", skin);

        table.add(this.titleLabel).colspan(2);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);
        table.add(this.volumeMusicLabel).left();
        table.add(volumeMusicSlider);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);
        table.add(this.musicOnOffLabel).left();
        table.add(musicCheckbox);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);
        table.add(this.volumeSoundLabel).left();
        table.add(soundMusicSlider);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);
        table.add(this.soundOnOffLabel).left();
        table.add(soundEffectsCheckbox);
        table.row().pad(10.0F, 0.0F, 0.0F, 10.0F);
        table.add(backButton).colspan(2);

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        pressedTime += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && pressedTime >= 0.4f) {
            pressedTime = 0;
            if (!parent.getPref()) {
                parent.changeScreen(MyGdxGame.MENU);
            }
            else {
                cam.setToOrtho(false, 1280 * w, 720 * k);
                parent.changeScreen(MyGdxGame.GAMEMENU);
            }

        }
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.end();
    }

    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {

    }
}
