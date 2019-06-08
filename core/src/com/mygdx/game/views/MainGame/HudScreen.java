package com.mygdx.game.views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;
import com.mygdx.game.manager.GameMainManager;

public class HudScreen implements Disposable {
    private FitViewport viewport;
    public Stage stage;
    public Camera cam;
    private Skin skin;
    protected Image pause;
    private ImageButton factoryButton;
    private Label foodLabel;
    private Label waterLabel;
    private Label rockLabel;
    private ImageButton backPack;
    protected Label nextDay;
    private Image rock;
    protected ImageButton buildingCheck;
    private Label countDay;
//    private MyGdxGame parent;
    private GameMainManager gameManager;
    private Image water;
    private Image food;
//    final private static Texture cosmonautTexture = A2DAssetManager.manager.get(A2DAssetManager.cosmonaut);
//    final private static  TextureRegionDrawable cosmonautDrawable = new TextureRegionDrawable(cosmonautTexture);
//    final private static Texture factoryTexture = A2DAssetManager.manager.get(A2DAssetManager.factory);
//    final private static TextureRegionDrawable factoryDrawable = new TextureRegionDrawable(factoryTexture);
//    final private static Texture buildingCheckT = A2DAssetManager.manager.get(A2DAssetManager.buildingCheck);
//    final private static TextureRegionDrawable buildingCheckDrawable = new TextureRegionDrawable(buildingCheckT);
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    public HudScreen(SpriteBatch sb, MyGdxGame parent, GameMainManager gameManagerF) {
//        this.parent = parent;
        this.gameManager = gameManagerF;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        pause = new Image(A2DAssetManager.manager.get(A2DAssetManager.pause, Texture.class));
        water = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        rock = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        food = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        stage = new Stage(viewport, sb);
        foodLabel = new Label(String.valueOf(gameManager.getFood()), skin,  "big");
        foodLabel.setFontScale(0.7f * k);
        foodLabel.setColor(Color.MAROON);
        foodLabel.setPosition(850 * w, 650 * k);
        food.setBounds(780 * w, 670 * k, 50 * w, 50 * k);
        nextDay = new Label("Next day", skin, "big");
        nextDay.setFontScale(0.7f * k);
        nextDay.setColor(Color.MAGENTA);
        nextDay.setBounds(570 * w, 650 * k, 150 * w, 40 * k);
        waterLabel = new Label(String.valueOf(gameManager.getWater()), skin, "big");
        waterLabel.setFontScale(0.7f * k);
        waterLabel.setColor(Color.MAROON);
        waterLabel.setPosition(990 * w, 650 * k);
        water.setBounds(920 * w, 670 * k, 50 * w, 50 * k);
        rockLabel = new Label(String.valueOf(gameManager.getRock()), skin, "big");
        rockLabel.setFontScale(0.7f * k);
        rockLabel.setColor(Color.MAROON);
        rockLabel.setPosition(1130 * w, 650 * k);
        rock.setBounds(1060 * w, 660 * k, 60 * w, 60 * k);
        Table table = new Table();
        ImageButton cosmonautButton = new ImageButton(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.cosmonaut, Texture.class)));
        cosmonautButton.setBounds(40 * w, 615 * k, 100 * w, 100 * k);
        countDay = new Label(String.valueOf(gameManager.getCountDay()), skin, "big");
        countDay.setFontScale(0.7f * k);
        countDay.setColor(Color.MAROON);
        pause.setBounds(1180 * w, 630 * k, 100 * w, 100 * k);
        backPack = new ImageButton(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.backPack, Texture.class)));
        countDay.setPosition(490 * w, 655 * k);
        ClickListener nextDayClick = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                Gdx.app.log("myTag", "NextDay");
            }
        };
        nextDay.addListener(nextDayClick);

        factoryButton = new ImageButton(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.factory, Texture.class)));
        factoryButton.setBounds(180 * w, 615 * k, 100 * w, 100 * k);
        buildingCheck = new ImageButton(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.buildingCheck, Texture.class)));
        buildingCheck.setBounds(320 * w, 615 * k, 100 * w , 100 * k );
        buildingCheck.setVisible(false);
        backPack.setBounds(40 * w, 465 * k, 100 * w, 100 * k);
        table.addActor(backPack);
        table.addActor(buildingCheck);
        table.addActor(cosmonautButton);
        table.addActor(foodLabel);
        table.addActor(food);
        table.addActor(nextDay);
        table.addActor(factoryButton);
        table.addActor(waterLabel);
        table.addActor(water);
        table.addActor(countDay);
        table.addActor(rock);
        table.addActor(rockLabel);
        table.addActor(pause);
        stage.addActor(table);
    }
    public void updateFoodLabel(){//установка label
        foodLabel.setText(String.valueOf(gameManager.getFood()));
    }
    public void update(float dt) {

    }
    public void updateWaterLabel(){//установка label
        waterLabel.setText(String.valueOf(gameManager.getWater()));
    }
    public void updateRockLabel(){//установка label
        rockLabel.setText(String.valueOf(gameManager.getRock()));
    }
    @Override
    public void dispose() {

    }
    public void updateCountDay() {//установка label
       countDay.setText(gameManager.getCountDay());
    }
    public void updateBuildingCheck() {//видна ли кнопка здаия
        if (gameManager.getBuildings().size() != 0) {
            buildingCheck.setVisible(true);
        }
    }
}
