package com.mygdx.game.views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;

public class InvScreen implements Screen {
    private MyGdxGame parent;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Table table;
    private Stage stage;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private Skin skin;
    private Image gun;
    private Image shovel;
    private Label equipment;
    private Label things;
    private Image microboard;
    private Image antenna;
    private Image battery;
    private Image hammer;

    public InvScreen(MyGdxGame parent, SpriteBatch batch) {
        this.parent = parent;
        this.batch = batch;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        gun = new Image(A2DAssetManager.manager.get(A2DAssetManager.gun, Texture.class));
        shovel = new Image(A2DAssetManager.manager.get(A2DAssetManager.shovel, Texture.class));
        microboard = new Image(A2DAssetManager.manager.get(A2DAssetManager.microboard, Texture.class));
        antenna = new Image(A2DAssetManager.manager.get(A2DAssetManager.antenna, Texture.class));
        battery = new Image(A2DAssetManager.manager.get(A2DAssetManager.battery, Texture.class));
        hammer = new Image(A2DAssetManager.manager.get(A2DAssetManager.hammer, Texture.class));
        equipment = new Label("Equipment: ", skin, "title");
        equipment.setFontScale(1.2f * k);
        things = new Label("Things: ", skin, "title");
        things.setFontScale(1.2f * k);
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        equipment.setPosition(5 * w, 620 * k);
        things.setPosition(1000 * w, 620 * k);
        for (int i = 0; i < parent.getGameScreen().getMainManager().getThings().size(); i++){
            if (parent.getGameScreen().getMainManager().getThings().get(i) == 3){
                microboard.setBounds(1120 * w, (500 - (i * 120)) * k, 100 * w, 100 * k);
                table.addActor(microboard);
            }
            if (parent.getGameScreen().getMainManager().getThings().get(i) == 4){
                antenna.setBounds(1120 * w, (500 - (i * 120)) * k, 100 * w, 100 * k);
                table.addActor(antenna);
            }
            if (parent.getGameScreen().getMainManager().getThings().get(i) == 5){
                battery.setBounds(1120 * w, (500 - (i * 120)) * k, 100 * w, 100 * k);
                table.addActor(battery);
            }
            if (parent.getGameScreen().getMainManager().getThings().get(i) == 7){
                hammer.setBounds(1120 * w, (500 - (i * 120)) * k, 100 * w, 100 * k);
                table.addActor(hammer);
            }
        }
        for (int i = 0; i < parent.getGameScreen().getMainManager().getInv().size(); i++){
            if (parent.getGameScreen().getMainManager().getInv().get(i).getName().equals("Gun")){
                gun.setBounds(10 * w, (370 - (i * 170)) * k,300 * w, 200 * k);
                for (int g = 0; g < parent.getGameScreen().getMainManager().getInv().get(i).getHealth(); g++){
                    Image heart = new Image(A2DAssetManager.manager.get(A2DAssetManager.heart, Texture.class));
                    heart.setBounds((330 + (120 * g)) * w , (440 - (i * 170)) * k, 100 * w, 100 * k);
                    table.addActor(heart);
                }
                for (int g = parent.getGameScreen().getMainManager().getInv().get(i).getHealth(); g < 2; g++){
                    Image heartF = new Image(A2DAssetManager.manager.get(A2DAssetManager.heartF, Texture.class));
                    heartF.setBounds((330 + (120 * g)) * w , (440 - (i * 170)) * k, 100 * w, 100 * k);
                    table.addActor(heartF);
                }
                table.addActor(gun);
            }
            if (parent.getGameScreen().getMainManager().getInv().get(i).getName().equals("Shovel")){
                shovel.setBounds(10 * w,(370 - (i * 300)) * k,300 * w, 200 * k);
                for (int g = 0; g < parent.getGameScreen().getMainManager().getInv().get(i).getHealth(); g++){
                    Image heart = new Image(A2DAssetManager.manager.get(A2DAssetManager.heart, Texture.class));
                    heart.setBounds((330 + (120 * g)) * w , (440 - (i * 300)) * k, 100 * w, 100 * k);
                    table.addActor(heart);
                }
                for (int g = parent.getGameScreen().getMainManager().getInv().get(i).getHealth(); g < 2; g++){
                    Image heartF = new Image(A2DAssetManager.manager.get(A2DAssetManager.heartF, Texture.class));
                    heartF.setBounds((330 + (120 * g)) * w , (440 - (i * 300)) * k, 100 * w, 100 * k);
                    table.addActor(heartF);
                }
                table.addActor(shovel);
            }
        }
        TextButton backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1f * k);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.GAMEPLAY);
            }
        });
        backButton.setBounds(515 * w, 615 * k, 250 * w, 100 * k);
        table.addActor(backButton);
        table.addActor(equipment);
        table.addActor(things);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
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
