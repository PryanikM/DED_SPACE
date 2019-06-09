package com.mygdx.game.Views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Loader.A2DAssetManager;

public class BuildingsMakeYesScreen implements Screen {
    private Image whoWorkImage;//картинка персонажа, который рабоатет
    private Image time;//время, которое ему осатлось работать
    private Image resource;//какой ресурс он добывает
    private Label what;
    private Label timeLabel;
    private MyGdxGame parent;
    private SpriteBatch batch;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Table table;
    private TextButton backButton;
    private Stage stage;
    private Skin skin;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    public BuildingsMakeYesScreen(MyGdxGame parent, SpriteBatch batch) {
        this.parent = parent;
        this.batch = batch;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        time = new Image(A2DAssetManager.manager.get(A2DAssetManager.clock, Texture.class));
        whoWorkImage = new Image();
        resource = new Image();
        timeLabel = new Label("", skin, "title");
        timeLabel.setFontScale(1.2f * k);
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        what = new Label("Now manufactured", skin, "title");
        what.setFontScale(1.2f * k);
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1f * k);
        backButton.setBounds(1020 * w, 615 * k, 250 * w, 100 * k);
        ClickListener backButtonListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                parent.changeScreen(MyGdxGame.GAMEPLAY);
            }
        };
        backButton.addListener(backButtonListener);
        if (parent.getGameScreen().getWhatBuildingPressed().equals("water")){
            whoWorkImage.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get("Image/players/" + parent.getBuildingsMakeNoScreen().getManager().getWhoWater() + "S.png", Texture.class)));
            resource.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class)));
            timeLabel.setText(parent.getBuildingsMakeNoScreen().getManager().getWaterFactoryWorkDay());
        }
        if (parent.getGameScreen().getWhatBuildingPressed().equals("food")){
            whoWorkImage.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get("Image/players/" + parent.getBuildingsMakeNoScreen().getManager().getWhoFood() + "S.png", Texture.class)));
            resource.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class)));
            timeLabel.setText(parent.getBuildingsMakeNoScreen().getManager().getFoodFactoryWorkDay());
        }
        if (parent.getGameScreen().getWhatBuildingPressed().equals("rock")){
            whoWorkImage.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get("Image/players/" + parent.getBuildingsMakeNoScreen().getManager().getWhoRock() + "S.png", Texture.class)));
            resource.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class)));
            timeLabel.setText(parent.getBuildingsMakeNoScreen().getManager().getRockFactoryWorkDay());
        }
        whoWorkImage.setBounds(540 * w, 416 * k ,200 * w, 284 * k);
        what.setPosition(200 * w, 316 * k);
        resource.setBounds(800 * w, 300 * k, 80 * w, 80 * k);
        time.setBounds(570 * w, 50 * k, 80 * w, 80 * k);
        timeLabel.setPosition(660 * w, 93 * k);
        table.addActor(whoWorkImage);
        table.addActor(what);
        table.addActor(resource);
        table.addActor(time);
        table.addActor(timeLabel);
        stage.addActor(table);
        table.addActor(backButton);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
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
