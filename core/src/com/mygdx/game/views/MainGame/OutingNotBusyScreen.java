package com.mygdx.game.views.MainGame;

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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;
import com.mygdx.game.manager.OutingManager;

import java.util.ArrayList;

import sun.java2d.pipe.AAShapePipe;

public class OutingNotBusyScreen implements Screen {
    private MyGdxGame parent;
    private GameMainScreen main;
    private OutingManager manager;
    private OrthographicCamera cam;
    private Skin skin;
    private Table playerTable;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private ArrayList<Image> buttons;//массив кнопок
    private ArrayList<Label> counts;
    private Label choiceLevel;//выбранный уровень
    private Skin skint;
    //EASY
    private Image rockI;
    private Image foodI;
    private Image waterI;
    private TextButton easyBt;
    private Label findEasy;
    private Label easyL;
    private Image easyFoodI;
    private Label easyFoodL;
    private Image easyWaterI;
    private Label easyWaterL;
    private Image easyTimeI;
    private Label easyTimeL;
    //EASY

    //MEDIUM
    private Label mediumL;
    private Label findMedium;
    private Label mediumFoodL;
    private Image mediumFoodI;
    private Label mediumWaterL;
    private Image mediumWaterI;
    private Image mediumTimeI;
    private Label mediumTimeL;
    private Image microboardM;
    private Image foodM;
    private Image waterM;
    private TextButton mediumBt;
    //MEDIUM

    //HARD
    private Label hardL;
    private Label findHard;
    private Image hardFoodI;
    private Label hardFoodL;
    private Image hardWaterI;
    private Label hardWaterL;
    private Image hardTimeI;
    private Label hardTimeL;
    private TextButton hardBt;
    private Image microboardH;
    private Image rockIH;
    private Image antenna;
    private Image foodAll;
    private Image waterAll;
    private Image rockAll;
    private Label foodAllLabel;
    private Label waterAllLabel;
    private Label rockAllLabel;
    //HARD

    public OutingNotBusyScreen(MyGdxGame parent, GameMainScreen main, float k, SpriteBatch batch) {
        this.parent = parent;
        this.main = main;
        this.manager = new OutingManager(main.getPlayers().size(), this, parent);
        this.k = k;
        this.w = MyGdxGame.w;
        skint = new Skin(Gdx.files.internal("skin/Clean_Crispy_UI_Skin//cleancrispyui/clean-crispy-ui.json"));
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        this.batch = batch;
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        playerTable = new Table();
        buttons = new ArrayList<Image>();
        counts = new ArrayList<Label>();
    }
    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        buttons.clear();
        counts.clear();
        foodAll = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        waterAll = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        rockAll = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        foodAllLabel = new Label(String.valueOf(main.getMainManager().getFood()), skin, "title");
        foodAllLabel.setFontScale(1.2f * k);
        waterAllLabel = new Label(String.valueOf(main.getMainManager().getWater()), skin, "title");
        waterAllLabel.setFontScale(1.2f * k);
        rockAllLabel = new Label(String.valueOf(main.getMainManager().getRock()), skin, "title");
        rockAllLabel.setFontScale(1.2f * k);
        foodAll.setBounds(5 * w, 665 * k, 50 * w, 50 * k);
        waterAll.setBounds(5 * w, 595 * k, 50 * w, 50 * k);
        rockAll.setBounds(5 * w, 515 * k , 50 * w, 55 * k );
        foodAllLabel.setPosition(70 * w, 665 * k);
        waterAllLabel.setPosition(70 * w, 595 * k);
        rockAllLabel.setPosition(70  * w, 515 * k);
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
                Gdx.app.log("Building", "player");
                temp = new Image(A2DAssetManager.manager.get("Image/" + main.getPlayers().get(i).getLinks() + "Work.png", Texture.class));
            }
            else {
                temp = new Image(A2DAssetManager.manager.get("Image/" + main.getPlayers().get(i).getName() + "S.png", Texture.class));
                final int finalI1 = i;
                temp.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent in, float x, float y) {
                        Gdx.app.log("Building", "press");
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
        choiceLevel = new Label("Levels :", skin, "title");
        choiceLevel.setFontScale(1.3f * k);
        choiceLevel.setPosition(520 * w ,485 * k);
        easyL = new Label("Easy", skin, "title");
        easyL.setFontScale(1.2f * k);
        easyL.setPosition(10 * w, 400 * k);
        findEasy = new Label("you can find: ", skin, "title");
        findEasy.setFontScale(0.7f * k);
        findEasy.setPosition(170 * w, 395 * k);
        rockI = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        rockI.setBounds(410 * w, 380 * k, 70 * w, 70 * k);
        waterI = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        waterI.setBounds(485 * w, 380 * k, 60 * w, 70 * k);
        foodI = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        foodI.setBounds(550 * w, 380 * k, 70 * w, 70 * k);
        easyFoodI = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        easyFoodI.setBounds(680 * w, 380 * k , 70 * w ,70 * k);
        easyFoodL = new Label("1", skin, "title");
        easyFoodL.setFontScale(1f * k);
        easyFoodL.setPosition(770 * w, 390 * k);
        easyWaterI = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        easyWaterI.setBounds(855 * w, 380 * k, 60 * w , 70 * k);
        easyWaterL = new Label("2", skin, "title");
        easyWaterL.setFontScale(1f * k);
        easyWaterL.setPosition(940 * w, 390 * k);
        easyTimeI = new Image(A2DAssetManager.manager.get(A2DAssetManager.clock, Texture.class));
        easyTimeI.setBounds(1000 * w, 380 * k, 70 * w, 70 * k);
        easyTimeL = new Label("3", skin, "title");
        easyTimeL.setFontScale(1f * k);
        easyTimeL.setPosition(1090 * w, 390 * k);
        easyBt = new TextButton("To send", skin);
        easyBt.getLabel().setFontScale(1f * k);
        easyBt.setBounds(1140 * w, 390 * k, 140 * w, 70 * k);
        ClickListener easyListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if(Integer.valueOf(String.valueOf(easyFoodL.getText())) <= main.getMainManager().getFood() && Integer.valueOf(String.valueOf(easyWaterL.getText())) <= main.getMainManager().getWater()) {
                    manager.setImage(new Image(A2DAssetManager.manager.get("Image/" + main.getPlayers().get(manager.getIsPressed()).getName() + "S.png", Texture.class)));
                    manager.setDays(Integer.valueOf(String.valueOf(easyTimeL.getText())));
                    manager.setWhatBisy("easy");
                    manager.setBusy(true);
                    main.getPlayers().get(manager.getIsPressed()).setIsBusy(true);
                    main.getMainManager().setMinusFood(Integer.valueOf(String.valueOf(easyFoodL.getText())));
                    main.getMainManager().setMinusWater(Integer.valueOf(String.valueOf(easyWaterL.getText())));
                    manager.setOuting(true);
                    manager.setWhoBisy(main.getPlayers().get(manager.getIsPressed()).getName());
                    playerTable.clear();
                    parent.changeScreen(MyGdxGame.OUTCHOCE);
                    Gdx.app.log("WORKED", "weg");

                }
                else{
                    BuildingBuildScreen.NoResource dialog = new BuildingBuildScreen.NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        easyBt.addListener(easyListener);
        mediumL = new Label("Medium", skin, "title");
        mediumL.setFontScaleX(0.75f * k);
        mediumL.setFontScaleY(1.2f * k);
        mediumL.setPosition(10 * w, 215 * k);
        findMedium = new Label("you can find: ", skin, "title");
        findMedium.setFontScale(0.7f * k);
        findMedium.setPosition(170 * w, 210 * k);
        mediumFoodI = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        mediumFoodI.setBounds(680 * w, 195 * k , 70 * w ,70 * k);
        mediumFoodL = new Label("1", skin, "title");
        mediumFoodL.setFontScale(1f * k);
        mediumFoodL.setPosition(770 * w, 205 * k);
        mediumWaterI = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        mediumWaterI.setBounds(855 * w, 195 * k, 60 * w , 70 * k);
        mediumWaterL = new Label("2", skin, "title");
        mediumWaterL.setFontScale(1f * k);
        mediumWaterL.setPosition(940 * w, 205 * k);
        mediumTimeI = new Image(A2DAssetManager.manager.get(A2DAssetManager.clock, Texture.class));
        mediumTimeI.setBounds(1000 * w, 195 * k, 70 * w, 70 * k);
        mediumTimeL = new Label("3", skin, "title");
        mediumTimeL.setFontScale(1f * k);
        mediumTimeL.setPosition(1090 * w, 205 * k);
        mediumBt = new TextButton("To send", skin);
        mediumBt.getLabel().setFontScale(1f * k);
        mediumBt.setBounds(1140 * w, 205 * k, 140 * w, 70 * k);
        microboardM = new Image(A2DAssetManager.manager.get(A2DAssetManager.microboard, Texture.class));
        microboardM.setBounds(570 * w, 215 * k, 50 * w, 50 * k);
        foodM = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        foodM.setBounds(485 * w, 210 * k, 70 * w, 70 * k);
        waterM = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        waterM.setBounds(410 * w, 210 * k, 60 * w, 70 * k);
        ClickListener mediumListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if(Integer.valueOf(String.valueOf(mediumFoodL.getText())) <= main.getMainManager().getFood() && Integer.valueOf(String.valueOf(mediumWaterL.getText())) <= main.getMainManager().getWater()) {
                    manager.setImage(new Image(A2DAssetManager.manager.get("Image/" + main.getPlayers().get(manager.getIsPressed()).getName() + "S.png", Texture.class)));
                    manager.setDays(Integer.valueOf(String.valueOf(mediumTimeL.getText())));
                    manager.setWhatBisy("medium");
                    manager.setWhoBisy(main.getPlayers().get(manager.getIsPressed()).getName());
                    main.getPlayers().get(manager.getIsPressed()).setIsBusy(true);
                    manager.setBusy(true);
                    main.getMainManager().setMinusFood(Integer.valueOf(String.valueOf(mediumFoodL.getText())));
                    main.getMainManager().setMinusWater(Integer.valueOf(String.valueOf(mediumWaterL.getText())));
                    manager.setOuting(true);
                    playerTable.clear();
                    parent.changeScreen(MyGdxGame.OUTCHOCE);
                }
                else{
                    BuildingBuildScreen.NoResource dialog = new BuildingBuildScreen.NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        mediumBt.addListener(mediumListener);
        hardL = new Label("Hard", skin, "title");
        hardL.setFontScale(1.2f * k);
        hardL.setPosition(10 * w, 30 * k);
        findHard = new Label("you can find: ", skin, "title");
        findHard.setFontScale(0.7f * k);
        findHard.setPosition(170 * w, 25 * k);
        hardFoodI = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        hardFoodI.setBounds(680 * w, 10* k , 70 * w ,70 * k);
        hardFoodL = new Label("1", skin, "title");
        hardFoodL.setFontScale(1f * k);
        hardFoodL.setPosition(780 * w, 20 * k);
        hardWaterI = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        hardWaterI.setBounds(855 * w, 10 * k, 60 * w , 70 * k);
        hardWaterL = new Label("2", skin, "title");
        hardWaterL.setFontScale(1f * k);
        hardWaterL.setPosition(940 * w, 20 * k);
        hardTimeI = new Image(A2DAssetManager.manager.get(A2DAssetManager.clock, Texture.class));
        hardTimeI.setBounds(1000 * w, 10 * k, 70 * w, 70 * k);
        hardTimeL = new Label("3", skin, "title");
        hardTimeL.setFontScale(1f * k);
        hardTimeL.setPosition(1080 * w, 20 * k);
        hardBt = new TextButton("To send", skin);
        hardBt.getLabel().setFontScale(1f * k);
        hardBt.setBounds(1140 * w, 10 * k, 140 * w, 70 * k);
        antenna = new Image(A2DAssetManager.manager.get(A2DAssetManager.antenna, Texture.class));
        antenna.setBounds(550 * w, 10 * k, 70 * w, 70 * k);
        rockIH = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        rockIH.setBounds(410 * w, 10 * k, 70 * w, 70 * k);
        microboardH = new Image(A2DAssetManager.manager.get(A2DAssetManager.microboard, Texture.class));
        microboardH.setBounds(485 * w, 15 * k, 50 * w, 50 * k);
        ClickListener hardListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if(Integer.valueOf(String.valueOf(hardFoodL.getText())) <= main.getMainManager().getFood() && Integer.valueOf(String.valueOf(hardWaterL.getText())) <= main.getMainManager().getWater()) {
                    manager.setImage(new Image(A2DAssetManager.manager.get("Image/" + main.getPlayers().get(manager.getIsPressed()).getName() + "S.png", Texture.class)));
                    manager.setDays(Integer.valueOf(String.valueOf(hardTimeL.getText())));
                    main.getPlayers().get(manager.getIsPressed()).setIsBusy(true);
                    manager.setBusy(true);
                    manager.setWhatBisy("hard");
                    manager.setWhoBisy(main.getPlayers().get(manager.getIsPressed()).getName());
                    Gdx.app.log("what",String.valueOf(manager.getWhoBisy()));
                    main.getMainManager().setMinusFood(Integer.valueOf(String.valueOf(hardFoodL.getText())));
                    main.getMainManager().setMinusWater(Integer.valueOf(String.valueOf(hardWaterL.getText())));
                    manager.setOuting(true);
                    playerTable.clear();
                    main.getMainManager().setPlusCheckdayhard();
                    parent.changeScreen(MyGdxGame.OUTCHOCE);
                }
                else{
                    BuildingBuildScreen.NoResource dialog = new BuildingBuildScreen.NoResource("", skint);
                    dialog.show(stage);
                    dialog.setPosition(500 * w, 200 * k);
                }
            }
        };
        hardBt.addListener(hardListener);
        counts.add(easyFoodL);
        counts.add(easyWaterL);
        counts.add(easyTimeL);
        counts.add(mediumFoodL);
        counts.add(mediumWaterL);
        counts.add(mediumTimeL);
        counts.add(hardFoodL);
        counts.add(hardWaterL);
        counts.add(hardTimeL);
        updateChoiсeFalse();
        playerTable.addActor(waterAll);
        playerTable.addActor(foodAll);
        playerTable.addActor(rockAll);
        playerTable.addActor(foodAllLabel);
        playerTable.addActor(waterAllLabel);
        playerTable.addActor(rockAllLabel);
        playerTable.addActor(choiceLevel);
        playerTable.addActor(easyL);
        playerTable.addActor(findEasy);
        playerTable.addActor(rockI);
        playerTable.addActor(easyFoodI);
        playerTable.addActor(easyFoodL);
        playerTable.addActor(easyWaterI);
        playerTable.addActor(easyWaterL);
        playerTable.addActor(easyTimeI);
        playerTable.addActor(easyTimeL);
        playerTable.addActor(easyBt);
        playerTable.addActor(mediumL);
        playerTable.addActor(findMedium);
        playerTable.addActor(waterI);
        playerTable.addActor(mediumFoodI);
        playerTable.addActor(mediumFoodL);
        playerTable.addActor(mediumWaterI);
        playerTable.addActor(mediumWaterL);
        playerTable.addActor(mediumTimeI);
        playerTable.addActor(mediumTimeL);
        playerTable.addActor(mediumBt);
        playerTable.addActor(hardL);
        playerTable.addActor(findHard);
        playerTable.addActor(foodI);
        playerTable.addActor(hardFoodI);
        playerTable.addActor(hardFoodL);
        playerTable.addActor(hardWaterI);
        playerTable.addActor(hardWaterL);
        playerTable.addActor(hardTimeI);
        playerTable.addActor(hardTimeL);
        playerTable.addActor(hardBt);
        playerTable.addActor(antenna);
        playerTable.addActor(rockIH);
        playerTable.addActor(microboardH);
        playerTable.addActor(microboardM);
        playerTable.addActor(waterM);
        playerTable.addActor(foodM);
        stage.addActor(playerTable);
    }
    public void update(boolean check, int ind){//обновление кнопок
        if(check){
            buttons.get(ind).setDrawable(new TextureRegionDrawable(new TextureRegion(A2DAssetManager.manager.get("Image/" + main.getPlayers().get(ind).getLinks() + "Work.png", Texture.class))));
            buttons.get(ind).getListeners().clear();
        }
        /*else{
            foodFactoryDevelopment.clearListeners();
            transferResourcesFoodFactory.clearListeners();
            makeFoodFactory.clearListeners();
            waterFactoryDevelopment.clearListeners();
            transferResourcesWaterFactory.clearListeners();
            makeWaterFactory.clearListeners();
            rockFactoryDevelopment.clearListeners();
            transferResourcesRockFactory.clearListeners();
            makeRockFactory.clearListeners();
        }*/
        for (int i = 0; i < buttons.size(); i++){
            if (!main.getPlayers().get(i).getIsBusy()) {
                if (manager.whatPressed(i)) {
                    buttons.get(i).setColor(Color.GRAY);

                }
                else {
                    buttons.get(i).setColor(Color.WHITE);
                }
            }
        }
    }
    public void updateResource(int ind){//обновление labels, характеризующие ресурсы
        int levelPos = 0;
        for (int i = 0 ; i < counts.size(); i++){
            if ((i + 1) % 3 == 0){
                counts.get(i).setText(manager.getWorkTime(main.getPlayers().get(ind), levelPos));
                levelPos++;
            }
            if((i + 1) % 3 == 1){
                counts.get(i).setText(manager.getFood(main.getPlayers().get(ind), levelPos));
            }
            if ((i + 1) % 3 == 2){
                counts.get(i).setText(manager.getWater(main.getPlayers().get(ind), levelPos));
            }
        }
    }
    public void updateChoiсeTrue(){//обнрвление видимости labels (видно)
        choiceLevel.setVisible(true);
        easyL.setVisible(true);
        findEasy.setVisible(true);
        rockI.setVisible(true);
        easyFoodI.setVisible(true);
        easyWaterI.setVisible(true);
        easyTimeI.setVisible(true);
        easyBt.setVisible(true);

        mediumL.setVisible(true);
        findMedium.setVisible(true);
        waterI.setVisible(true);
        mediumFoodI.setVisible(true);
        mediumWaterI.setVisible(true);
        mediumTimeI.setVisible(true);
        mediumBt.setVisible(true);

        hardL.setVisible(true);
        findHard.setVisible(true);
        foodI.setVisible(true);
        hardFoodI.setVisible(true);
        hardWaterI.setVisible(true);
        hardTimeI.setVisible(true);
        hardBt.setVisible(true);
        antenna.setVisible(true);
        rockIH.setVisible(true);
        microboardH.setVisible(true);
        microboardM.setVisible(true);
        foodM.setVisible(true);
        waterM.setVisible(true);
        for (int i = 0; i < counts.size(); i++){
            counts.get(i).setVisible(true);
        }
    }
    public void updateChoiсeFalse() {//обнрвление видимости labels (не видно)
        choiceLevel.setVisible(false);
        easyL.setVisible(false);
        findEasy.setVisible(false);
        rockI.setVisible(false);
        easyFoodI.setVisible(false);
        easyWaterI.setVisible(false);
        easyTimeI.setVisible(false);
        easyBt.setVisible(false);
        mediumL.setVisible(false);
        findMedium.setVisible(false);
        waterI.setVisible(false);
        mediumFoodI.setVisible(false);
        mediumWaterI.setVisible(false);
        mediumTimeI.setVisible(false);
        mediumBt.setVisible(false);
        hardL.setVisible(false);
        findHard.setVisible(false);
        foodI.setVisible(false);
        hardFoodI.setVisible(false);
        hardWaterI.setVisible(false);
        hardTimeI.setVisible(false);
        hardBt.setVisible(false);
        antenna.setVisible(false);
        rockIH.setVisible(false);
        microboardH.setVisible(false);
        microboardM.setVisible(false);
        foodM.setVisible(false);
        waterM.setVisible(false);
        for (int i = 0; i < counts.size(); i++){
            counts.get(i).setVisible(false);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            manager.first();
            manager.setFirst(true);
            playerTable.clear();
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
    public OutingManager getManager(){
        return manager;
    }
    public static class NoResource extends Dialog {
        float k = MyGdxGame.k;
        public NoResource(String title, Skin skin) {
            super(title, skin);
        }
        {
            text("Not enough resources").setScale(2.2f * k);
            button("Ok").setScale(2.2f * k);
        }
    }
}
