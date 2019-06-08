package com.mygdx.game.views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;
import com.mygdx.game.manager.OutingManager;

public class OutingYesBusyScreen implements Screen {
    private MyGdxGame parent;
//    private GameMainScreen main;
    private OrthographicCamera cam;
    private Skin skin;
    private Table playerTable;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private OutingManager manager;
    private Image find1;
    private Image find2;
    private Image find3;
    private Label take;
    private Image gunI;
    private Image shovelI;
    public OutingYesBusyScreen(MyGdxGame parent, float k, OutingManager manager, SpriteBatch batch) {
        this.parent = parent;
        this.manager = manager;
        this.k = k;
        this.w = MyGdxGame.w;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        this.batch = batch;
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        playerTable = new Table();
        take = new Label("You gave him" + "\n" +"with you", skin, "title");
        take.setFontScale(1f * k);
        gunI = new Image(A2DAssetManager.manager.get(A2DAssetManager.gun, Texture.class));
        shovelI = new Image(A2DAssetManager.manager.get(A2DAssetManager.shovel, Texture.class));
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        take.setPosition(900 * w, 410 * k);
        TextButton backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1f * k);
        if (!manager.getIsGun() && !manager.getIsShovel()){
            take.setVisible(false);
            gunI.setVisible(false);
            shovelI.setVisible(false);
        }
        if (manager.getIsGun() && manager.getIsShovel()){
            gunI.setBounds(900 * w, 210 * k, 200 * w, 150 * k);
            shovelI.setBounds(900 * w, 40 * k, 200 * w, 150 * k);
            gunI.setVisible(true);
            shovelI.setVisible(true);
        }
        if ((!manager.getIsGun() && manager.getIsShovel()) || (manager.getIsGun() && !manager.getIsShovel())){
            if (manager.getIsGun()){
                gunI.setBounds(900 * w, 210 * k, 200 * w, 150 * k);
                gunI.setVisible(true);
                shovelI.setVisible(false);
            }
            else{
                shovelI.setBounds(900 * w, 210 * k, 200 * w, 150 * k);
                shovelI.setVisible(true);
                gunI.setVisible(false);
            }
        }
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                manager.first();
                manager.setFirst(true);
                playerTable.clear();
                parent.changeScreen(MyGdxGame.GAMEPLAY);
            }
        });
        if (manager.getWhatBisy().equals("hard")){
            find1 = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
            find2 = new Image(A2DAssetManager.manager.get(A2DAssetManager.microboard, Texture.class));
            find3 = new Image(A2DAssetManager.manager.get(A2DAssetManager.antenna, Texture.class));
        }
        if (manager.getWhatBisy().equals("easy")){
            find1 = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
            find2 = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
            find3 = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        }
        if (manager.getWhatBisy().equals("medium")) {
            find1 = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
            find2 = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
            find3 = new Image(A2DAssetManager.manager.get(A2DAssetManager.microboard, Texture.class));
        }
        find1.setBounds(400 * w, 250 * k, 50 * w, 50 * k);
        find2.setBounds(470 * w, 250 * k, 50 * w, 50 * k);
        find3.setBounds(540 * w, 250 * k, 50 * w, 50 * k);
        backButton.setBounds(1020 * w, 615 * k, 250 * w, 100 * k);
        playerTable.addActor(backButton);
        Image player = manager.getImage();
        player.setBounds(590 * w, 558 * k, 100 * w, 142 * k);
        Label whatChoice = new Label("You choosed level: " + manager.getWhatBisy(), skin, "title");
        whatChoice.setFontScale(1f * k);
        whatChoice.setPosition(35 * w, 450 * k);
        Label find = new Label("you can find: ", skin, "title");
        find.setFontScale(1f * k);
        find.setPosition(30 * w, 250 * k);
        Label time = new Label(manager.getDays() + " days left", skin, "title");
        time.setPosition(450 * w, 100 * k);
        time.setFontScale(1f * k);
        playerTable.addActor(take);
        playerTable.addActor(shovelI);
        playerTable.addActor(gunI);
        playerTable.addActor(find1);
        playerTable.addActor(find2);
        playerTable.addActor(find3);
        playerTable.addActor(time);
        playerTable.addActor(player);
        playerTable.addActor(whatChoice);
        playerTable.addActor(find);
        stage.addActor(playerTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            manager.first();
            manager.setFirst(true);
            playerTable.clear();
            parent.changeScreen(MyGdxGame.GAMEPLAY);
        }
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
}
