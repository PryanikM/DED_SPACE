package com.mygdx.game.views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;
import com.mygdx.game.manager.OutingManager;

import javax.swing.text.View;

public class ChoiseAuxiliaryToolOutingScreen implements Screen {
    private Image gun;
    private Image shovel;
    private CheckBox gunCheck;//выбор пистолета
    private CheckBox shovelCheck;//выбор лопаты
    private String who;//кто рабоате
    private Image whoI;//фотография персонажа
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Table table;
    private Stage stage;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private MyGdxGame parent;
    private Skin skin;
    private Label text;
    private OutingManager manager;
    private TextButton continueButton;
    private Label gunLabel;
    private Label shovelLabel;
    public ChoiseAuxiliaryToolOutingScreen(MyGdxGame parent, SpriteBatch batch, OutingManager manager) {
        this.parent = parent;
        this.batch = batch;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        this.manager = manager;
        cam = new OrthographicCamera();
        viewport = new FitViewport(500 * w, 250 * k, cam);
        cam.setToOrtho(false, 500 * w, 250 * k);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        gunLabel = new Label("Increases chance" + "\n" +  "of survival", skin, "title");
        gunLabel.setFontScale(0.3f * k);
        shovelLabel = new Label("Increases the chance" + "\n" + "of finding resources", skin, "title");
        shovelLabel.setFontScale(0.3f * k);
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        cam.setToOrtho(false, 500 * w, 250 * k);
        continueButton = new TextButton("Continue", skin);
        continueButton.getLabel().setFontScale(1f * k);
        continueButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                table.clear();
                if (gunCheck.isChecked()){
                    manager.setIsGun(true);
                }
                if (shovelCheck.isChecked()){
                    manager.setIsShowel(true);
                }
                cam.setToOrtho(false, 1280 * w, 720 * k);
                parent.changeScreen(MyGdxGame.OUTINGT);
            }
        });
        continueButton.setBounds(10 * w, 50 * k, 100  * w, 40 * k);
        table = new Table();
        text = new Label("You can give inventory", skin, "title");
        text.setFontScale(0.3f * k);
        text.setPosition(200 * w, 135 * k);
        gun = new Image(A2DAssetManager.manager.get(A2DAssetManager.gun, Texture.class));
        shovel = new Image(A2DAssetManager.manager.get(A2DAssetManager.shovel, Texture.class));
        shovelCheck = new CheckBox(null, skin);
        gunCheck = new CheckBox(null, skin);
        if (!parent.getGameScreen().getMainManager().findInv("Gun")){
            gun.setColor(Color.GRAY);
            gunCheck.clearListeners();
        }
        if (!parent.getGameScreen().getMainManager().findInv("Shovel")){
            shovel.setColor(Color.GRAY);
            shovelCheck.clearListeners();
        }
        gunLabel.setPosition(345 * w, 70  * k);
        shovelLabel.setPosition(345 * w, 0 * k);
        whoI = new Image(A2DAssetManager.manager.get("Image/" + manager.getWhoBisy() + "S.png", Texture.class));
        whoI.setBounds(229 * w, 175 * k, 51 * w, 80 * k);
        gun.setBounds(265 * w, 80 * k, 68 * w, 45 * k);
        shovel.setBounds(265 * w, 5 * k, 68 * w, 45 * k);
        gunCheck.setPosition(210 * w, 90 * k);
        shovelCheck.setPosition(210 * w, 15);
        table.addActor(gunLabel);
        table.addActor(shovelLabel);
        table.addActor(continueButton);
        table.addActor(text);
        table.addActor(whoI);
        table.addActor(gun);
        table.addActor(shovel);
        table.addActor(gunCheck);
        table.addActor(shovelCheck);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
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
    public  void setWho(String who){
        this.who = who;
    }
}
