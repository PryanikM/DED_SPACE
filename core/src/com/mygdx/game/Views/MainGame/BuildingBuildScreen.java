package com.mygdx.game.Views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Loader.A2DAssetManager;
import com.mygdx.game.Managers.BuildingManager;

import java.util.ArrayList;

public class BuildingBuildScreen implements Screen {
    private MyGdxGame parent;
    private GameMainScreen main;
    private BuildingManager manager;
    private FitViewport viewport;
    private Stage stage;
    private Table table;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    //public ArrayList <Players> players;
    private ArrayList <Image> buttons;
//    private ImageButton minerButton;
//    private ImageButton engineerButton;
//    private ImageButton biologyButton;
//    private ImageButton strengthButton;
//    private Image waterFactory;
//    private Image foodFactory;
//    private Image rockFactory;
    private Table playerTable;//для отображения игроков
    private Skin skin;
    private Skin skint;
    private Texture waterFactoryTexture;
    private Texture foodFactoryTexture;
    private Texture rockFactoryTexture;
    /*private Texture wateFfactoryMakeTexture;
    private Texture foodFactoryMakeTexture;
    private Texture rockFactoryMakeTexture;*/
    private Table scrollTable;//дял предметов, которые можно сделать
    private Image foodI;
    private Image waterI;
    private Image rockI;

    //кнопки для того, чтобы создать предмет
    private TextButton makeWaterFactory;
    private TextButton waterFactoryDevelopment;
    private TextButton transferResourcesWaterFactory;

    private TextButton rockFactoryDevelopment;
    private TextButton transferResourcesRockFactory;
    private TextButton makeRockFactory;

    private TextButton foodFactoryDevelopment;
    private TextButton transferResourcesFoodFactory;
    private TextButton makeFoodFactory;


    private TextButton makeGun;
    private TextButton makeShovel;
    private TextButton makeFirstAid;
    private TextButton makeHammer;



    //для отображения требуемых ресурсов, для создания предметов
    private Label foodGun;
    private Label waterGun;
    private Label rockGun;
    private Label timeGun;
    private Label gunDay;

    private Label foodShovel;
    private Label waterShovel;
    private Label rockShovel;
    private Label timeShovel;
    private Label shovelDay;

    private Label foodFirstAid;
    private Label waterFirstAid;
    private Label timeFirstAid;
    private Label firstAidDay;

    private Label foodHammer;
    private Label waterHammer;
    private Label rockHammer;
    private Label timeHammer;
    private Label hammerDay;

    private Label foodCountWater;
    private Label waterCountWater;
    private Label timeCountWater;
    private Label rockCountWater;


    private Label foodCountFood;
    private Label waterCountFood;
    private Label timeCountFood;
    private Label rockCountFood;

    private Label foodCountRock;
    private Label waterCountRock;
    private Label timeCountRock;
    private Label rockCountRock;

    private Label foodLabel;
    private Label waterLabel;
    private Label rockLabel;


    //слушатели для кнопок
    private ClickListener foodFactoryDevelopmentListener;
    private ClickListener waterFactoryDevelopmentListener;
    private ClickListener transferResourcesWaterFactoryListener;
    private ClickListener makeWaterFactoryListener;
    private ClickListener rockFactoryDevelopmentListener;
    private ClickListener transferResourcesRockFactoryListener;
    private ClickListener makeRockFactoryListener;
    private ClickListener transferResourcesFoodFactoryListener;
    private ClickListener makeFoodFactoryListener;
    private ClickListener makeGunListener;
    private ClickListener makeShovelListener;
    private ClickListener makeFirstAidListener;
    private ClickListener makeHammerListener;


    //для отобрадения остаточного количесвта ресурсов
    private Label foodDay;
    private Label waterDay;
    private Label rockDay;
    /*private Drawable wateFfactoryDrawable;
    private Drawable foodFactoryDrawable;
    private Drawable rockFactoryDrawable;*/
//    final private static Texture waterTexture = A2DAssetManager.manager.get(A2DAssetManager.water);
//    final private static Image waterI = new Image(waterTexture);
//    final private static Texture foodTexture = A2DAssetManager.manager.get(A2DAssetManager.food);
//    final private static Image foodI = new Image(foodTexture);
//    final private static Image foodII = new Image(foodTexture);
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    public BuildingBuildScreen(MyGdxGame myGdxGame, GameMainScreen main, SpriteBatch batch) {
        this.parent = myGdxGame;
        this.main = main;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        rockFactoryTexture = A2DAssetManager.manager.get(A2DAssetManager.rockFactory);
        rockFactoryTexture = A2DAssetManager.manager.get(A2DAssetManager.rockFactory);
        waterFactoryTexture = A2DAssetManager.manager.get(A2DAssetManager.waterFactory);
        foodFactoryTexture = A2DAssetManager.manager.get(A2DAssetManager.foodFactory);
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        this.batch = batch;
//        players = new ArrayList<Players>(main.getPlayers());
        buttons = new ArrayList<Image>();
        manager = new BuildingManager(main.getPlayers().size(), this);
//        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        playerTable = new Table();
        skint = new Skin(Gdx.files.internal("skin/Clean_Crispy_UI_Skin//cleancrispyui/clean-crispy-ui.json"));
//        show();
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setDebug(false);
        scrollTable = new Table();
        scrollTable.setBounds(0,0, 1280 * w, 495 * k);
        table.defaults().expandX().fill().space(30f * k);
        table.pad(10f * k);
        Label factory = new Label("Factory: ", skin, "title");
        factory.setFontScale(1f * k);
        table.add(factory).center();
        table.row().height(150f * k);
        //WATER FACTORY
        final Image waterFactory = new Image(waterFactoryTexture);
        if (manager.getWaterFactory() == 3){
            waterFactory.setDrawable(new TextureRegionDrawable(new TextureRegion(A2DAssetManager.manager.get(A2DAssetManager.waterMake, Texture.class))));
        }
        waterFactoryDevelopment = new TextButton("development", skin);
        waterFactoryDevelopment.getLabel().setFontScale(0.9f * k);
        waterFactoryDevelopment.setColor(Color.GRAY);
        waterFactoryDevelopment.getLabel().setColor(Color.GRAY);
        waterFactoryDevelopment.getListeners().clear();
        transferResourcesWaterFactory = new TextButton("Transfer resources", skin);
        transferResourcesWaterFactory.getLabel().setFontScale(0.9f * k);
        transferResourcesWaterFactory.setColor(Color.GRAY);
        transferResourcesWaterFactory.getLabel().setColor(Color.GRAY);
        transferResourcesWaterFactory.getListeners().clear();
        makeWaterFactory = new TextButton("To build", skin);
        makeWaterFactory.getLabel().setFontScale(0.9f * k);
        makeWaterFactory.setColor(Color.GRAY);
        makeWaterFactory.getLabel().setColor(Color.GRAY);
        makeWaterFactory.getListeners().clear();
        waterDay = new Label("Day: " + (manager.getWaterFacoryTimeBisy()), skin, "title");
        if (!manager.isWaterIsBusy()) {
            waterDay.setVisible(false);
        }
        waterDay.setFontScale(1f * k);

        waterFactoryDevelopmentListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.developmentWaterFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        transferResourcesWaterFactoryListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.transferWaterFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        makeWaterFactoryListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.makeWaterFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        /*Label foodL = new Label("9", skin, "big");
        Label foodLa = new Label("9", skin, "big");
        Label waterL = new Label("9", skin, "big");
        Label day = new Label("Days: ", skin, "big");
        Label dayCount = new Label("2", skin, "big");
        */
        table.add(waterFactory).height(250 * k).width(300 * w);
        table.add(waterFactoryDevelopment).width(200 * w).height(80 * k);
        table.add(transferResourcesWaterFactory).width(200 * w).height(80 * k);
        table.add(makeWaterFactory).width(200 * w).height(80 * k);
        table.add(waterDay).center();
        table.row().space(5f * k).expandX();
        /*table.add(foodI, foodL);
        table.add(waterI, waterL);
        table.add(day, dayCount);
        table.add(foodLa, foodII);*/
//        table.add(waterL).left().width(80);
//        table.add(day);
//        table.add(dayCount);
        //table.row();
        table.add();
        foodCountWater = new Label("Food: 1", skin, "title");
        foodCountWater.setFontScale(0.9f * k);
        table.add(foodCountWater).space(5f * k).bottom();
        waterCountWater = new Label("Water: 2", skin, "title");
        waterCountWater.setFontScale(0.9f * k);
        table.add(waterCountWater).left().space(5f * k).bottom();
        timeCountWater = new Label("Time: 3", skin, "title");
        timeCountWater.setFontScale(0.9f * k);
        table.add(timeCountWater).space(5f * k).expandX();
        rockCountWater = new Label("Rock: 4", skin, "title");
        rockCountWater.setFontScale(0.9f * k);
        table.add(rockCountWater);
        table.row();
        //WATER FACTORY


        //ROCK FACTORY
        Image rockFactory = new Image(rockFactoryTexture);
        if (manager.getRockFactory() == 3){
            rockFactory.setDrawable(new TextureRegionDrawable(new TextureRegion(A2DAssetManager.manager.get(A2DAssetManager.rockMake, Texture.class))));
        }
        rockFactoryDevelopment = new TextButton("development", skin);
        rockFactoryDevelopment.getLabel().setFontScale(0.9f * k);
        rockFactoryDevelopment.setColor(Color.GRAY);
        rockFactoryDevelopment.getLabel().setColor(Color.GRAY);
        rockFactoryDevelopment.getListeners().clear();
        transferResourcesRockFactory = new TextButton("Transfer resources", skin);
        transferResourcesRockFactory.getLabel().setFontScale(0.9f * k);
        transferResourcesRockFactory.setColor(Color.GRAY);
        transferResourcesRockFactory.getLabel().setColor(Color.GRAY);
        transferResourcesRockFactory.getListeners().clear();
        makeRockFactory = new TextButton("To build", skin);
        makeRockFactory.getLabel().setFontScale(0.9f * k);
        makeRockFactory.setColor(Color.GRAY);
        makeRockFactory.getLabel().setColor(Color.GRAY);
        makeRockFactory.getListeners().clear();
        rockDay = new Label("Day: " + (manager.getRockFacoryTimeBisy()), skin, "title");
        if (!manager.isRockIsBusy()) {
            rockDay.setVisible(false);
        }
        rockDay.setFontScale(1f * k);
        rockFactoryDevelopmentListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.developmentRockFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        transferResourcesRockFactoryListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.transferRockFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        makeRockFactoryListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.makeRockFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        table.add(rockFactory).height(350 * k).width(300 * w);
        table.add(rockFactoryDevelopment).width(200 * w).height(80 * k);
        table.add(transferResourcesRockFactory).width(200 * w).height(80 * k);
        table.add(makeRockFactory).width(200 * w).height(80 * k);
        table.add(rockDay);
        table.row().space(5f * k).expandX();

        table.add();
        foodCountRock = new Label("Food: 1", skin, "title");
        foodCountRock.setFontScale(0.9f * k);
        table.add(foodCountRock).space(5f * k).bottom();
        waterCountRock = new Label("Water: 2", skin, "title");
        waterCountRock.setFontScale(0.9f * k);
        table.add(waterCountRock).left().space(5f * k).bottom();
        timeCountRock = new Label("Time: 3", skin, "title");
        timeCountRock.setFontScale(0.9f * k);
        table.add(timeCountRock).space(5f * k).expandX();
        rockCountRock = new Label("Rock: 4", skin, "title");
        rockCountRock.setFontScale(0.9f * k);
        table.add(rockCountRock);
        table.row();
        //ROCK FACTORY


        //FOOD FACTORY
        Image foodFactory  = new Image(foodFactoryTexture);
        if (manager.getFoodFactory() == 3  ){
            foodFactory.setDrawable(new TextureRegionDrawable(new TextureRegion(A2DAssetManager.manager.get(A2DAssetManager.foodMake, Texture.class))));
        }
        foodFactoryDevelopment = new TextButton("development", skin);
        foodFactoryDevelopment.getLabel().setFontScale(0.9f * k);
        foodFactoryDevelopment.setColor(Color.GRAY);
        foodFactoryDevelopment.getLabel().setColor(Color.GRAY);
        foodFactoryDevelopment.getListeners().clear();
        transferResourcesFoodFactory = new TextButton("Transfer resources", skin);
        transferResourcesFoodFactory.getLabel().setFontScale(0.9f * k);
        transferResourcesFoodFactory.setColor(Color.GRAY);
        transferResourcesFoodFactory.getLabel().setColor(Color.GRAY);
        transferResourcesFoodFactory.getListeners().clear();
        makeFoodFactory = new TextButton("To build", skin);
        makeFoodFactory.getLabel().setFontScale(0.9f * k);
        makeFoodFactory.setColor(Color.GRAY);
        makeFoodFactory.getLabel().setColor(Color.GRAY);
        makeFoodFactory.getListeners().clear();
        foodDay = new Label("Day: " + manager.getFoodFacoryTimeBisy(), skin, "title");
        if (!manager.isFoodIsBusy()) {
            foodDay.setVisible(false);
        }
        foodDay.setFontScale(1f * k);
        foodFactoryDevelopmentListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.developmentFoodFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        transferResourcesFoodFactoryListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.transferFoodFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        makeFoodFactoryListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (!manager.makeFoodFactoryPressed()){
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        table.add(foodFactory).height(300 * k).width(300 * w);
        table.add(foodFactoryDevelopment).width(200 * w).height(80 * k);
        table.add(transferResourcesFoodFactory).width(200 * w).height(80 * k);
        table.add(makeFoodFactory).width(200 * w).height(80 * k);
        table.add(foodDay).center();
        table.row().space(5f * k).expandX();
        table.add();
        foodCountFood = new Label("Food: 1", skin, "title");
        foodCountFood.setFontScale(0.9f * k);
        table.add(foodCountFood).space(5f * k).bottom();
        waterCountFood = new Label("Water: 2", skin, "title");
        waterCountFood.setFontScale(0.9f * k);
        table.add(waterCountFood).left().space(5f * k).bottom();
        timeCountFood = new Label("Time: 3", skin, "title");
        timeCountFood.setFontScale(0.9f * k);
        table.add(timeCountFood).space(5f * k).expandX();
        rockCountFood = new Label("Rock: 4", skin, "title");
        rockCountFood.setFontScale(0.9f * k);
        table.add(rockCountFood);
        table.row().pad(50f * k);

        //FOOD FACTORY
        Label equipment = new Label("Equipment: ", skin, "title");
        equipment.setScale(1f * k);
        table.add(equipment);
        table.row();

        Image transmitter = new Image(A2DAssetManager.manager.get(A2DAssetManager.transmitter, Texture.class));
        table.add(transmitter).width(175 * w).height(216 * k);

        table.row();
        Image gun = new Image(A2DAssetManager.manager.get(A2DAssetManager.gun, Texture.class));
        table.add(gun).width(216 * w).height(175 * k);
        gunDay = new Label("1", skin, "title");
        gunDay.setFontScale(1.2f * k);
        gunDay.setVisible(false);

        makeGun = new TextButton("Make", skin);
        makeGun.getLabel().setFontScale(0.9f * k);
        makeGun.setColor(Color.GRAY);
        makeGun.getLabel().setColor(Color.GRAY);
        makeGun.getListeners().clear();
        makeGunListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                boolean check = false;
                for (int i = 0; i < main.getMainManager().getInv().size(); i++){
                    if (main.getMainManager().getInv().get(i).getName().equals("Gun")){
                        check = true;
                    }
                }
                if (!check) {
                    if (!manager.gunPressed()) {
                        NoResource dialog = new NoResource("", skint);
                        dialog.show(stage);
                        dialog.setPosition(500 * w, 200 * k);
                    }
                    else{
                        setLabelFalse();
                    }
                }
                else{
                    Have dialog = new Have("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        table.add(makeGun).width(200 * w).height(80 * k);
        table.add(gunDay).center();
        table.row().space(5f * k).expandX();
        table.add();
        foodGun = new Label("1", skin, "title");
        foodGun.setFontScale(0.9f * k);
        table.add(foodGun).space(5f * k).bottom();
        waterGun = new Label("1", skin, "title");
        waterGun.setFontScale(0.9f * k);
        table.add(waterGun).space(5f * k).bottom();
        rockGun = new Label("1", skin, "title");
        rockGun.setFontScale(0.9f * k);
        table.add(rockGun).space(5f * k).bottom();
        timeGun = new Label("1", skin, "title");
        timeGun.setFontScale(0.9f * k);
        table.add(timeGun).space(5f * k).bottom();

        table.row();
        Image shovel = new Image(A2DAssetManager.manager.get(A2DAssetManager.shovel, Texture.class));
        table.add(shovel).width(216 * w).height(200 * k);
        shovelDay = new Label("1", skin, "title");
        shovelDay.setVisible(false);
        makeShovel = new TextButton("Make", skin);
        makeShovel.getLabel().setFontScale(0.9f * k);
        makeShovel.setColor(Color.GRAY);
        makeShovel.getLabel().setColor(Color.GRAY);
        makeShovel.getListeners().clear();
        makeShovelListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                boolean check = false;
                for (int i = 0; i < main.getMainManager().getInv().size(); i++){
                    if (main.getMainManager().getInv().get(i).getName().equals("Shovel")){
                        check = true;
                    }
                }
                if (!check) {
                    if (!manager.shovelPressed()) {
                        NoResource dialog = new NoResource("", skint);
                        dialog.show(stage);
                        dialog.setPosition(500 * w, 200 * k);
                    }
                    else{
                        setLabelFalse();
                    }
                }
                else{
                    Have dialog = new Have("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        table.add(makeShovel).width(200 * w).height(80 * k);
        table.add(shovelDay).center();
        table.row().space(5f * k).expandX();
        table.add();
        foodShovel = new Label("1", skin, "title");
        foodShovel.setFontScale(0.9f * k);
        table.add(foodShovel).space(5f * k).bottom();
        waterShovel = new Label("1", skin, "title");
        waterShovel.setFontScale(0.9f * k);
        table.add(waterShovel).space(5f * k).bottom();
        rockShovel = new Label("1", skin, "title");
        rockShovel.setFontScale(0.9f * k);
        table.add(rockShovel).space(5f * k).bottom();
        timeShovel = new Label("1", skin, "title");
        timeShovel.setFontScale(0.9f * k);
        table.add(timeShovel).space(5f * k).bottom();


        table.row();
        Image firstAid = new Image(A2DAssetManager.manager.get(A2DAssetManager.firstAid, Texture.class));
        table.add(firstAid).width(216 * w).height(200 * k);
        firstAidDay = new Label("1", skin, "title");
        firstAidDay.setVisible(false);
        makeFirstAid = new TextButton("Make", skin);
        foodFirstAid = new Label("1", skin, "title");
        foodFirstAid.setFontScale(0.9f * k);
        waterFirstAid = new Label("1", skin, "title");
        waterFirstAid.setFontScale(0.9f * k);
        timeFirstAid = new Label("1", skin, "title");
        timeFirstAid.setFontScale(0.9f * k);
        makeFirstAid.getLabel().setFontScale(0.9f * k);
        makeFirstAid.setColor(Color.GRAY);
        makeFirstAid.getLabel().setColor(Color.GRAY);
        makeFirstAid.getListeners().clear();
        makeFirstAidListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (!manager.firstAidPressed()) {
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
                else {
                    setLabelFalse();
                }
            }
        };
        table.add(makeFirstAid).width(200 * w).height(80 * k);
        table.add(firstAidDay).center();
        table.row();
        table.add();
        table.add(foodFirstAid).space(5f * k).bottom();
        table.add(waterFirstAid).space(5f * k).bottom();
        table.add(timeFirstAid).space(5f * k).bottom();

        table.row();
        Image hammer = new Image(A2DAssetManager.manager.get(A2DAssetManager.hammer, Texture.class));
        table.add(hammer).width(216 * w).height(200 * k);
        hammerDay = new Label("1", skin, "title");
        hammerDay.setVisible(false);
        makeHammer = new TextButton("Make", skin);
        foodHammer = new Label("1", skin, "title");
        foodHammer.setFontScale(0.9f * k);
        waterHammer = new Label("1", skin, "title");
        waterHammer.setFontScale(0.9f * k);
        rockHammer = new Label("1", skin, "title");
        rockHammer.setFontScale(0.9f * k);
        timeHammer = new Label("1", skin, "title");
        timeHammer.setFontScale(0.9f * k);
        makeHammer.getLabel().setFontScale(0.9f * k);
        makeHammer.setColor(Color.GRAY);
        makeHammer.getLabel().setColor(Color.GRAY);
        makeHammer.getListeners().clear();
        makeHammerListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (!manager.hammerPressed()) {
                    NoResource dialog = new NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
                else {
                    setLabelFalse();
                }
            }
        };
        table.add(makeHammer).width(200 * w).height(80 * k);
        table.add(hammerDay).center();
        table.row();
        table.add();
        table.add(foodHammer).space(5f * k).bottom();
        table.add(waterHammer).space(5f * k).bottom();
        table.add(rockHammer).space(5f * k).bottom();
        table.add(timeHammer).space(5f * k).bottom();


        ScrollPane scroll = new ScrollPane(table);
        scroll.setScrollingDisabled(true, false);
        scrollTable.add(scroll).fill();
        buttons.clear();
        foodI = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        waterI = new Image (A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        rockI = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        foodI.setBounds(5 * w, 665 * k, 50 * w, 50 * k);
        waterI.setBounds(5 * w, 595 * k, 50 * w, 50 * k);
        rockI.setBounds(5 * w, 515 * k , 50 * w, 55 * k );

        waterLabel = new Label(String.valueOf(main.getMainManager().getWater()), skin, "title");
        waterLabel.setFontScale(1.2f * k);
        waterLabel.setPosition(70 * w, 595 * k);
        rockLabel = new Label(String.valueOf(main.getMainManager().getRock()), skin, "title");
        rockLabel.setFontScale(1.2f * k);
        rockLabel.setPosition(70 * w, 515 * k);
        foodLabel = new Label(String.valueOf(main.getMainManager().getFood()), skin, "title");
        foodLabel.setFontScale(1.2f * k);
        foodLabel.setPosition(70 * w, 665 * k);
        playerTable.addActor(foodI);
        playerTable.addActor(waterI);
        playerTable.addActor(rockI);
        playerTable.addActor(foodLabel);
        playerTable.addActor(waterLabel);
        playerTable.addActor(rockLabel);
        TextButton backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1f * k);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                manager.first();
                manager.setFirst(true);
                playerTable.clear();
                parent.changeScreen(MyGdxGame.GAMEPLAY);
            }
        });
        backButton.setBounds(1020 * w, 615 * k, 250 * w, 100 * k);
        playerTable.addActor(backButton);
        for (int i = 0 ; i < main.getPlayers().size(); i++){
            final Image temp;
            if (main.getPlayers().get(i).getIsBusy()) {
                temp = new Image(A2DAssetManager.manager.get("Image/players/" + main.getPlayers().get(i).getLinks() + "Work.png", Texture.class));
            }
            else {
                temp = new Image(A2DAssetManager.manager.get("Image/players/" + main.getPlayers().get(i).getName() + "S.png", Texture.class));
                final int finalI1 = i;
                temp.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent in, float x, float y) {
                        manager.setPressed(finalI1);

                    }
                });
            }
            buttons.add(temp);
        }
        if (buttons.size() == 1){
            buttons.get(0).setBounds(565 * w, 565 * k, 93 * w, 150 * k);
            playerTable.addActor(buttons.get(0));
        }
        else {
            int size_pl = buttons.size();
            for (int i = 0; i <= size_pl / 2; i++) {

                if (size_pl % 2 != 0){
                    if (i == 0){
                        buttons.get(size_pl / 2).setBounds(565 * w, 565 * k, 93 * w, 150 * k);
                        playerTable.addActor(buttons.get(size_pl / 2));
                    }
                    i++;
                    buttons.get(size_pl / 2 + i).setBounds((565 + (170 * i)) * w, 565 * k, 93 * w, 150 * k);
                    playerTable.addActor(buttons.get(size_pl / 2 + i));
                    buttons.get(size_pl / 2 - i).setBounds((565 - (170 * i)) * w, 565 * k, 93 * w, 150 * k);
                    playerTable.addActor(buttons.get(size_pl / 2 - i));
                }
                else{
                    if (i == 0) {
                        buttons.get(size_pl / 2 - 1).setBounds(480 * w, 565 * k, 93 * w, 150 * k);
                        playerTable.addActor(buttons.get(size_pl / 2 - 1));
                        buttons.get(size_pl / 2 ).setBounds(650 * w, 565 * k, 93 * w, 150 * k);
                        playerTable.addActor(buttons.get(size_pl / 2));
                        i++;
                    }
                    else {
                        buttons.get(size_pl / 2 - 2).setBounds(310  * w, 565 * k, 93 * w, 150 * k);
                        playerTable.addActor(buttons.get(size_pl / 2 - 1 - 1));
                        buttons.get(size_pl / 2 + 1).setBounds(820  * w, 565 * k, 93 * w, 150 * k);
                        playerTable.addActor(buttons.get(size_pl / 2 + 1));
                    }
                }
            }
        }
        setLabelFalse();
        stage.addActor(playerTable);
        stage.addActor(scrollTable);

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
    public void update(boolean check, int ind){
        if(check){
            buttons.get(ind).setDrawable(new TextureRegionDrawable(new TextureRegion(A2DAssetManager.manager.get("Image/players/" + main.getPlayers().get(ind).getLinks() + "Work.png", Texture.class))));
            buttons.get(ind).getListeners().clear();
        }
        else{
            foodFactoryDevelopment.clearListeners();
            transferResourcesFoodFactory.clearListeners();
            makeFoodFactory.clearListeners();
            waterFactoryDevelopment.clearListeners();
            transferResourcesWaterFactory.clearListeners();
            makeWaterFactory.clearListeners();
            rockFactoryDevelopment.clearListeners();
            transferResourcesRockFactory.clearListeners();
            makeRockFactory.clearListeners();
            makeGun.clearListeners();
            makeFirstAid.clearListeners();
            makeHammer.clearListeners();
            makeShovel.clearListeners();
        }
        for (int i = 0; i < buttons.size(); i++){
            if (!main.getPlayers().get(i).getIsBusy()) {
                if (manager.whatPressed(i)) {
                    buttons.get(i).setColor(Color.GRAY);
                    if(!manager.isFoodIsBusy() && manager.getFoodFactory() == 0){
                        foodFactoryDevelopment.addListener(foodFactoryDevelopmentListener);
                    }
                    if(!manager.isFoodIsBusy() && manager.getFoodFactory() == 1){
                        transferResourcesFoodFactory.addListener(transferResourcesFoodFactoryListener);
                    }
                    if (!manager.isFoodIsBusy() && manager.getFoodFactory() == 2){
                        makeFoodFactory.addListener(makeFoodFactoryListener);
                    }
                    if (!manager.isWaterIsBusy() && manager.getWaterFactory() == 0 ) {
                        waterFactoryDevelopment.addListener(waterFactoryDevelopmentListener);
                    }
                    if(!manager.isWaterIsBusy() && manager.getWaterFactory() == 1){
                        transferResourcesWaterFactory.addListener(transferResourcesWaterFactoryListener);
                    }
                    if(!manager.isWaterIsBusy() && manager.getWaterFactory() == 2){
                        makeWaterFactory.addListener(makeWaterFactoryListener);
                    }
                    if(!manager.isRockIsBusy() && manager.getRockFactory() == 0){
                        rockFactoryDevelopment.addListener(rockFactoryDevelopmentListener);
                    }
                    if (!manager.isRockIsBusy() && manager.getRockFactory() == 1){
                        transferResourcesRockFactory.addListener(transferResourcesRockFactoryListener);
                    }
                    if (!manager.isRockIsBusy() && manager.getRockFactory() == 2){
                        makeRockFactory.addListener(makeRockFactoryListener);
                    }
                    if (manager.getGunTime() == 0) {
                        makeGun.addListener(makeGunListener);
                    }
                    if (manager.getShovelTime() == 0){
                        makeShovel.addListener(makeShovelListener);
                    }
                    if (manager.getFirstAidTime() == 0){
                        makeFirstAid.addListener(makeFirstAidListener);
                    }
                    if (manager.getHammerTime() == 0){
                        makeHammer.addListener(makeHammerListener);
                    }
                }
                else {
                    buttons.get(i).setColor(Color.WHITE);

                }
            }
        }
    }
    public void updateChoiсeTrue(){//обновление кнопок. Делает их активными
        if (!manager.isFoodIsBusy()) {
            switch (manager.getFoodFactory()) {
                case 0:
                    foodFactoryDevelopment.getLabel().setColor(Color.WHITE);
                    foodFactoryDevelopment.setColor(Color.WHITE);
                    break;
                case 1:
                    foodFactoryDevelopment.getLabel().setColor(Color.GRAY);
                    foodFactoryDevelopment.setColor(Color.GRAY);
                    transferResourcesFoodFactory.getLabel().setColor(Color.WHITE);
                    transferResourcesFoodFactory.setColor(Color.WHITE);
                    break;
                case 2:
                    transferResourcesFoodFactory.getLabel().setColor(Color.GRAY);
                    transferResourcesFoodFactory.setColor(Color.GRAY);
                    makeFoodFactory.getLabel().setColor(Color.WHITE);
                    makeFoodFactory.setColor(Color.WHITE);
                    break;
            }
        }
        else{
            foodFactoryDevelopment.setColor(Color.GRAY);
            foodFactoryDevelopment.getLabel().setColor(Color.GRAY);
            transferResourcesFoodFactory.setColor(Color.GRAY);
            transferResourcesFoodFactory.getLabel().setColor(Color.GRAY);
            makeFoodFactory.setColor(Color.GRAY);
            makeFoodFactory.getLabel().setColor(Color.GRAY);
        }
        if (!manager.isWaterIsBusy()) {
            switch (manager.getWaterFactory()) {
                case 0:
                    waterFactoryDevelopment.getLabel().setColor(Color.WHITE);
                    waterFactoryDevelopment.setColor(Color.WHITE);
                    break;
                case 1:
                    waterFactoryDevelopment.getLabel().setColor(Color.GRAY);
                    waterFactoryDevelopment.setColor(Color.GRAY);
                    transferResourcesWaterFactory.getLabel().setColor(Color.WHITE);
                    transferResourcesWaterFactory.setColor(Color.WHITE);
                    break;
                case 2:
                    transferResourcesWaterFactory.getLabel().setColor(Color.GRAY);
                    transferResourcesWaterFactory.setColor(Color.GRAY);
                    makeWaterFactory.getLabel().setColor(Color.WHITE);
                    makeWaterFactory.setColor(Color.WHITE);
                    break;
            }
        }
        else {
            waterFactoryDevelopment.setColor(Color.GRAY);
            waterFactoryDevelopment.getLabel().setColor(Color.GRAY);
            transferResourcesWaterFactory.setColor(Color.GRAY);
            transferResourcesWaterFactory.getLabel().setColor(Color.GRAY);
            makeWaterFactory.setColor(Color.GRAY);
            makeWaterFactory.getLabel().setColor(Color.GRAY);
        }
        if(!manager.isRockIsBusy()) {
            switch (manager.getRockFactory()) {
                case 0:
                    rockFactoryDevelopment.getLabel().setColor(Color.WHITE);
                    rockFactoryDevelopment.setColor(Color.WHITE);
                    break;
                case 1:
                    rockFactoryDevelopment.getLabel().setColor(Color.GRAY);
                    rockFactoryDevelopment.setColor(Color.GRAY);
                    transferResourcesRockFactory.getLabel().setColor(Color.WHITE);
                    transferResourcesRockFactory.setColor(Color.WHITE);
                    break;
                case 2:
                    transferResourcesRockFactory.getLabel().setColor(Color.GRAY);
                    transferResourcesRockFactory.setColor(Color.GRAY);
                    makeRockFactory.getLabel().setColor(Color.WHITE);
                    makeRockFactory.setColor(Color.WHITE);
                    break;
            }
        }
        else{
            rockFactoryDevelopment.setColor(Color.GRAY);
            rockFactoryDevelopment.getLabel().setColor(Color.GRAY);
            transferResourcesRockFactory.setColor(Color.GRAY);
            transferResourcesRockFactory.getLabel().setColor(Color.GRAY);
            makeRockFactory.setColor(Color.GRAY);
            makeRockFactory.getLabel().setColor(Color.GRAY);
        }
        if (manager.getGunTime() == 0) {
            makeGun.setColor(Color.WHITE);
            makeGun.getLabel().setColor(Color.WHITE);
        }
        if (manager.getShovelTime() == 0){
            makeShovel.setColor(Color.WHITE);
            makeShovel.getLabel().setColor(Color.WHITE);
        }
        if (manager.getFirstAidTime() == 0) {
            makeFirstAid.setColor(Color.WHITE);
            makeFirstAid.getLabel().setColor(Color.WHITE);
        }
        if (manager.getHammerTime() == 0){
            makeHammer.setColor(Color.WHITE);
            makeHammer.getLabel().setColor(Color.WHITE);
        }

    }
    public void updateChoiсeFalse(){//обновление кнопок. Делает их не активными
        foodFactoryDevelopment.getLabel().setColor(Color.GRAY);
        foodFactoryDevelopment.setColor(Color.GRAY);
        makeFoodFactory.getLabel().setColor(Color.GRAY);
        makeFoodFactory.setColor(Color.GRAY);
        transferResourcesFoodFactory.getLabel().setColor(Color.GRAY);
        transferResourcesFoodFactory.setColor(Color.GRAY);

        transferResourcesWaterFactory.getLabel().setColor(Color.GRAY);
        transferResourcesWaterFactory.setColor(Color.GRAY);
        waterFactoryDevelopment.getLabel().setColor(Color.GRAY);
        waterFactoryDevelopment.setColor(Color.GRAY);
        makeWaterFactory.getLabel().setColor(Color.GRAY);
        makeWaterFactory.setColor(Color.GRAY);


        makeRockFactory.getLabel().setColor(Color.GRAY);
        makeRockFactory.setColor(Color.GRAY);
        rockFactoryDevelopment.getLabel().setColor(Color.GRAY);
        rockFactoryDevelopment.setColor(Color.GRAY);
        transferResourcesRockFactory.getLabel().setColor(Color.GRAY);
        transferResourcesRockFactory.setColor(Color.GRAY);

        makeGun.getLabel().setColor(Color.GRAY);
        makeGun.setColor(Color.GRAY);

        makeShovel.getLabel().setColor(Color.GRAY);
        makeShovel.setColor(Color.GRAY);

        makeFirstAid.getLabel().setColor(Color.GRAY);
        makeFirstAid.setColor(Color.GRAY);

        makeHammer.getLabel().setColor(Color.GRAY);
        makeHammer.setColor(Color.GRAY);
    }
    public GameMainScreen getMain(){
        return main;
    }
    public BuildingManager getManager() {
        return manager;
    }
    public void makeWaterDayTrue(){//устанавливает дни
        waterDay.setText("Day: " + (manager.getWaterFacoryTimeBisy()));
        waterDay.setVisible(true);
    }
    public void makeWaterDayFalse(){//делает текст не видимым
        waterDay.setVisible(false);
    }
    public void makeFoodDayTrue(){//устанавливает дни
        foodDay.setText("Day: " + (manager.getFoodFacoryTimeBisy()));
        foodDay.setVisible(true);
    }
    public void makeFoodDayFalse(){//делает тест не видимым
        foodDay.setVisible(false);
    }
    public void makeRockDayTrue(){//устанавливает дни
        rockDay.setText("Day: " + (manager.getRockFacoryTimeBisy()));
        rockDay.setVisible(true);
    }
    public void makeRockDayFalse(){//делает текст не видимым
        rockDay.setVisible(false);
    }
    public void makeGunDayTrue(){//устанавливает дни
        gunDay.setText("Day: " + (manager.getGunTime()));
        gunDay.setVisible(true);
    }
    public void makeShovelDayTrue(){//устанавливает дни
        shovelDay.setText("Day: " + (manager.getShovelTime()));
        shovelDay.setVisible(true);
    }
    public void makeFirstAidDayTrue(){//устанавливает дни
        firstAidDay.setText("Day: " + (manager.getFirstAidTime()));
        firstAidDay.setVisible(true);
    }
    public void makeHammerDayTrue(){//устанавливает дни
        hammerDay.setText("Day: " + (manager.getHammerTime()));
        hammerDay.setVisible(true);
    }
    public void makeFirstAidDayFalse(){//делает текст не видимым
        firstAidDay.setVisible(false);
    }
    public void makeHammerDayFalse(){//делает текст не видимым
        hammerDay.setVisible(false);
    }
    public void makeGunDayFalse(){//делает текст не видимым
        gunDay.setVisible(false);
    }
    public void makeShovelDayFalse(){//делает текст не видимым
        shovelDay.setVisible(false);
    }
    public MyGdxGame getParent(){
        return parent;
    }
    public void setLabelFalse(){//делает текст не видимым
        foodCountWater.setVisible(false);
        waterCountWater.setVisible(false);
        timeCountWater.setVisible(false);
        rockCountWater.setVisible(false);
        foodCountFood.setVisible(false);
        waterCountFood.setVisible(false);
        timeCountFood.setVisible(false);
        rockCountFood.setVisible(false);
        foodCountRock.setVisible(false);
        waterCountRock.setVisible(false);
        timeCountRock.setVisible(false);
        rockCountRock.setVisible(false);
        foodGun.setVisible(false);
        waterGun.setVisible(false);
        rockGun.setVisible(false);
        timeGun.setVisible(false);
        foodShovel.setVisible(false);
        waterShovel.setVisible(false);
        rockShovel.setVisible(false);
        timeShovel.setVisible(false);
        foodFirstAid.setVisible(false);
        waterFirstAid.setVisible(false);
        timeFirstAid.setVisible(false);
        foodHammer.setVisible(false);
        waterHammer.setVisible(false);
        rockHammer.setVisible(false);
        timeHammer.setVisible(false);
    }
    public void setLabelText() {//утсанавливает текст, для label
        if (manager.getWaterFacoryTimeBisy() == 0) {
            waterCountWater.setText("Water: " + manager.getWaterFactoryWater());
            foodCountWater.setText("Food: " + manager.getFoodFactoryWater());
            timeCountWater.setText("Time: " + manager.getTimeFactoryWater());
            if (manager.getWaterFactory() == 1) {
                rockCountWater.setText("Rock: " + manager.getRockFactoryWater());
            }
        }
        else {
            waterCountWater.setVisible(false);
            foodCountWater.setVisible(false);
            timeCountWater.setVisible(false);
            if (manager.getWaterFactory() == 1) {
                rockCountWater.setVisible(false);
            }
        }

        if (manager.getFoodFacoryTimeBisy() == 0) {
            waterCountFood.setText("Water: " + manager.getWaterFactoryFood());
            foodCountFood.setText("Food: " + manager.getFoodFactoryFood());
            timeCountFood.setText("Time: " + manager.getTimeFactoryFood());
            if (manager.getFoodFactory() == 1) {
                rockCountFood.setText("Rock: " + manager.getRockFactoryFood());
            }
        }
        else {
            waterCountFood.setVisible(false);
            foodCountFood.setVisible(false);
            timeCountFood.setVisible(false);
            if (manager.getFoodFactory() == 1) {
                rockCountFood.setVisible(false);

            }
        }


        if (manager.getRockFacoryTimeBisy() == 0) {
            waterCountRock.setText("Water: " + manager.getWaterFactoryRock());
            foodCountRock.setText("Food: " + manager.getFoodFactoryRock());
            timeCountRock.setText("Time: " + manager.getTimeFactoryRock());
            if (manager.getRockFactory() == 1) {
                rockCountRock.setText("Rock: " + manager.getRockFactoryRock());
            }
        }
        else {
            waterCountRock.setVisible(false);
            foodCountRock.setVisible(false);
            timeCountRock.setVisible(false);
            if (manager.getRockFactory() == 1) {
                rockCountRock.setVisible(false);

            }
        }
        if (manager.getGunTime() == 0){
            foodGun.setText("Food: " + manager.getFoodGun());
            waterGun.setText("Water: " + manager.getWaterGun());
            rockGun.setText("Rock: " + manager.getRockGun());
            timeGun.setText("Time: " + manager.getTimeGun());
        }
        else {
            foodGun.setVisible(false);
            waterGun.setVisible(false);
            rockGun.setVisible(false);
            timeGun.setVisible(false);

        }

        if (manager.getShovelTime() == 0){
            foodShovel.setText("Food: " + manager.getFoodShovel());
            waterShovel.setText("Water: " + manager.getWaterShovel());
            rockShovel.setText("Rock: " + manager.getRockShovel());
            timeShovel.setText("Time: " + manager.getTimeShovel());
        }
        else {
            foodShovel.setVisible(false);
            waterShovel.setVisible(false);
            rockShovel.setVisible(false);
            timeShovel.setVisible(false);

        }


        if (manager.getFirstAidTime() == 0){
            foodFirstAid.setText("Food: " + manager.getFoodFirstAid());
            waterFirstAid.setText("Water: " + manager.getWaterFirstAid());
            timeFirstAid.setText("Time: " + manager.getTimeFirstAid());
        }
        else {
            foodFirstAid.setVisible(false);
            waterFirstAid.setVisible(false);
            timeFirstAid.setVisible(false);

        }
        if (manager.getHammerTime() == 0){
            foodHammer.setText("Food: " + manager.getFoodHammer());
            waterHammer.setText("Water: " + manager.getWaterHammer());
            rockHammer.setText("Rock: " + manager.getRockHammer());
            timeHammer.setText("Time: " + manager.getTimeHammer());
        }
        else{
            foodHammer.setVisible(false);
            waterHammer.setVisible(false);
            rockHammer.setVisible(false);
            timeHammer.setVisible(false);
        }

    }
    public void setLabelVisible(){//делает текст видимым
        if (manager.getWaterFacoryTimeBisy() == 0) {
            waterCountWater.setVisible(true);
            foodCountWater.setVisible(true);
            timeCountWater.setVisible(true);
            if (manager.getWaterFactory() == 1){
                rockCountWater.setVisible(true);
            }
        }
        if (manager.getFoodFacoryTimeBisy() == 0) {
            waterCountFood.setVisible(true);
            foodCountFood.setVisible(true);
            timeCountFood.setVisible(true);
            if (manager.getFoodFactory() == 1){
                rockCountFood.setVisible(true);
            }
        }
        if (manager.getRockFacoryTimeBisy() == 0) {
            waterCountRock.setVisible(true);
            foodCountRock.setVisible(true);
            timeCountRock.setVisible(true);
            if (manager.getRockFactory() == 1){
                rockCountRock.setVisible(true);
            }
        }
        if (manager.getGunTime() == 0){
            foodGun.setVisible(true);
            waterGun.setVisible(true);
            rockGun.setVisible(true);
            timeGun.setVisible(true);
        }
        if (manager.getShovelTime() == 0){
            foodShovel.setVisible(true);
            waterShovel.setVisible(true);
            rockShovel.setVisible(true);
            timeShovel.setVisible(true);
        }
        if (manager.getFirstAidTime() == 0){
            foodFirstAid.setVisible(true);
            waterFirstAid.setVisible(true);
            timeFirstAid.setVisible(true);
        }
        if (manager.getHammerTime() == 0){
            foodHammer.setVisible(true);
            waterHammer.setVisible(true);
            rockHammer.setVisible(true);
            timeHammer.setVisible(true);
        }
    }
    public void updateLabel(){//обновляет label, в которых хранится общее кол-во ресурсов
        rockLabel.setText(main.getMainManager().getRock());
        foodLabel.setText(main.getMainManager().getFood());
        waterLabel.setText(main.getMainManager().getWater());
    }
    public static class NoResource extends Dialog {//вложенный класс, для всплывающего окна
        float k = MyGdxGame.k;
        public NoResource(String title, Skin skin) {
            super(title, skin);
        }
        {
            text("Not enough resources").setScale(2.2f * k);
            button("Ok").setScale(2.2f * k);
        }
    }
    public static class Have extends Dialog {
        float k = MyGdxGame.k;
        public Have(String title, Skin skin) {
            super(title, skin);
        }
        {
            text("You already have this equipment.").setScale(2.2f * k);
            button("Ok").setScale(2.2f * k);
        }
    }
}
