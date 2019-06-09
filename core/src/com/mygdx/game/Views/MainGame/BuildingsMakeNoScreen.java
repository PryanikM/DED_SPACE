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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Loader.A2DAssetManager;
import com.mygdx.game.Managers.BuildingsMakeManager;

import java.util.ArrayList;

public class BuildingsMakeNoScreen implements Screen {
    private Stage stage;
    private Table playerTable;
    private Viewport viewport;
    private Skin skint;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private MyGdxGame parent;
    private GameMainScreen main;
    private Skin skin;
    private ArrayList<Image> buttons;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private String what;//какое здантие
    private BuildingsMakeManager manager;
    private Image foodImage;
    private Image rockImage;
    private Image waterImage;
    private Image timeImage;
    private Label waterLabel;
    private Label chance;
    private Image foodI;
    private Image waterI;
    private Image rockI;
    private Label foodLabel;
    private Label timeLabel;
    private Label rockLabel;
    private TextButton work;
    private Label foodLabelR;
    private Label waterLabelR;
    private Label rockLabelR;
    private Label chanceL1;
    private Label chanceL2;
    private Label chanceL3;
    private Label chanceL4;
    private Image chanceI1;
    private Image chanceI2;
    private Image chanceI3;
    private Image chanceI4;
    private Label chanceProcent1;
    private Label chanceProcent2;
    private Label chanceProcent3;
    private Label chanceProcent4;
    public BuildingsMakeNoScreen(MyGdxGame parent, GameMainScreen main, SpriteBatch batch) {
        this.parent = parent;
        this.main = main;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        this.batch = batch;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        manager = new BuildingsMakeManager(main.getPlayers().size(), this, parent);
        buttons = new ArrayList<Image>();
        foodImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        timeImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.clock, Texture.class));
        rockImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        waterImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        foodLabel = new Label ("1", skin, "title");
        timeLabel = new Label("1", skin, "title");
        rockLabel = new Label("1", skin, "title");
        waterLabel = new Label("1", skin, "title");
        chance = new Label("Chance:", skin, "title");
        chance.setFontScale(1.2f * k);
        chance.setPosition(980 * w, 500 * k);
        chanceL1 = new Label("1", skin, "title");
        chanceL2 = new Label("2", skin, "title");
        chanceL3 = new Label("3", skin, "title");
        chanceL4 = new Label("4", skin, "title");
        chanceL1.setFontScale(1.2f * k);
        chanceL2.setFontScale(1.2f * k);
        chanceL3.setFontScale(1.2f * k);
        chanceL4.setFontScale(1.2f * k);
        chanceL1.setPosition(950 * w, 420 * k);
        chanceL2.setPosition(950 * w, 290 * k);
        chanceL3.setPosition(950 * w, 160 * k);
        chanceL4.setPosition(950 * w, 30 * k);
        skint = new Skin(Gdx.files.internal("skin/Clean_Crispy_UI_Skin//cleancrispyui/clean-crispy-ui.json"));
    }
    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        playerTable = new Table();
        what = main.getWhatBuildingPressed();
        buttons.clear();
        rockLabelR = new Label(String.valueOf(main.getMainManager().getRock()), skin, "title");
        waterLabelR = new Label(String.valueOf(main.getMainManager().getWater()), skin, "title");
        foodLabelR = new Label(String.valueOf(main.getMainManager().getFood()), skin, "title");
        waterLabelR.setFontScale(1.2f * k);
        waterLabelR.setPosition(70 * w, 595 * k);
        rockLabelR.setFontScale(1.2f * k);
        rockLabelR.setPosition(70 * w, 515 * k);
        foodLabelR.setFontScale(1.2f * k);
        foodLabelR.setPosition(70 * w, 665 * k);
        TextButton backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1f * k);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                manager.first();
                manager.setFirst(true);
//                playerTable.clear();
                buttons.clear();
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
        work = new TextButton("Work", skin);
        work.getLabel().setFontScale(1f * k);
        work.setBounds(10 * w , 180 * k , 200 * w ,110 * k);
        ClickListener workListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (what.equals("water")){
                    if((parent.getGameScreen().getMainManager().getFood() - Integer.valueOf(String.valueOf(foodLabel.getText())) >= 0) &&
                            (parent.getGameScreen().getMainManager().getRock() - Integer.valueOf(String.valueOf(rockLabel.getText())) >= 0)){
                        manager.setWhoWater();
                        manager.setWaterFactoryWorkDay(Integer.valueOf(String.valueOf(timeLabel.getText())));
                        parent.getGameScreen().getMainManager().setMinusFood(Integer.valueOf(String.valueOf(foodLabel.getText())));
                        parent.getGameScreen().getMainManager().setMinusRock(Integer.valueOf(String.valueOf(rockLabel.getText())));
                        manager.setWaterBusyTrue();
                        for (int i = 0; i < main.getMainManager().getBuildings().size(); i++) {
                            if (main.getMainManager().getBuildings().get(i).getResourse().equals("water")) {
                                main.getMainManager().getBuildings().get(i).setBusy(true);
                                i = 5;
                            }
                        }
                        updateChoiсeFalse();
                        manager.setFirst(true);
                        parent.changeScreen(MyGdxGame.BUILDINGMAKEYES);
                    }
                    else {
                        BuildingBuildScreen.NoResource dialog = new BuildingBuildScreen.NoResource("", skint);
                        dialog.show(stage);
                        dialog.setPosition(500 * w, 200 * k);
                    }
                }
                if (what.equals("food")){
                    if((parent.getGameScreen().getMainManager().getWater() - Integer.valueOf(String.valueOf(waterLabel.getText())) >= 0) &&
                            (parent.getGameScreen().getMainManager().getRock() - Integer.valueOf(String.valueOf(rockLabel.getText())) >= 0)) {
                        manager.setWhoFood();
                        manager.setFoodFactoryWorkDay(Integer.valueOf(String.valueOf(timeLabel.getText())));
                        parent.getGameScreen().getMainManager().setMinusWater(Integer.valueOf(String.valueOf(waterLabel.getText())));
                        parent.getGameScreen().getMainManager().setMinusRock(Integer.valueOf(String.valueOf(rockLabel.getText())));
                        manager.setFoodBusyTrue();
                        for (int i = 0; i < main.getMainManager().getBuildings().size(); i++) {
                            if (main.getMainManager().getBuildings().get(i).getResourse().equals("food")) {
                                main.getMainManager().getBuildings().get(i).setBusy(true);
                                i = 5;
                            }
                        }
                        updateChoiсeFalse();
                        manager.setFirst(true);
                        parent.changeScreen(MyGdxGame.BUILDINGMAKEYES);
                    }
                    else{
                        BuildingBuildScreen.NoResource dialog = new BuildingBuildScreen.NoResource("", skint);
                        dialog.show(stage);
                        dialog.setPosition(500 * w, 200 * k);
                    }
                }
                if (what.equals("rock")){
                    if((parent.getGameScreen().getMainManager().getWater() - Integer.valueOf(String.valueOf(waterLabel.getText())) >= 0) &&
                            (parent.getGameScreen().getMainManager().getFood() - Integer.valueOf(String.valueOf(foodLabel.getText())) >= 0)) {
                        manager.setWhoRock();
                        parent.getGameScreen().getMainManager().setMinusFood(Integer.valueOf(String.valueOf(foodLabel.getText())));
                        parent.getGameScreen().getMainManager().setMinusWater(Integer.valueOf(String.valueOf(waterLabel.getText())));
                        manager.setRockFactoryWorkDay(Integer.valueOf(String.valueOf(timeLabel.getText())));
                        manager.setRockBusyTrue();
                        for (int i = 0; i < main.getMainManager().getBuildings().size(); i++) {
                            if (main.getMainManager().getBuildings().get(i).getResourse().equals("rock")) {
                                main.getMainManager().getBuildings().get(i).setBusy(true);
                                i = 5;
                            }
                        }
                        updateChoiсeFalse();
                        manager.setFirst(true);
                        parent.changeScreen(MyGdxGame.BUILDINGMAKEYES);
                    }
                    else{
                        BuildingBuildScreen.NoResource dialog = new BuildingBuildScreen.NoResource("", skint);
                        dialog.show(stage);
                        dialog.setPosition(500 * w, 200 * k);
                    }
                }
            }

        };
        work.addListener(workListener);
        work.setVisible(false);
        if (what.equals("water")){
            foodImage.setBounds(350 * w , 350 * k , 110 * w , 110 * k);
            rockImage.setBounds(350 * w , 180 * k , 110 * w ,110 * k);
            timeImage.setBounds(350 * w , 10 * k , 110 * w ,110 * k);
            foodLabel.setPosition(600 * w, 400 * k);
            rockLabel.setPosition(600 * w, 230 * k);
            timeLabel.setPosition(600 * w, 60 * k);
            foodLabel.setFontScale(1.5f * k);
            rockLabel.setFontScale(1.5f * k);
            timeLabel.setFontScale(1.5f * k);
            chanceI1 = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
            chanceI2 = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
            chanceI3 = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
            chanceI4 = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
            chanceI1.setBounds(1000 * w, 410 * k, 80 * w, 70 * k);
            chanceI2.setBounds(1000 * w, 280 * k, 80 * w, 70 * k);
            chanceI3.setBounds(1000 * w, 150 * k, 80 * w, 70 * k);
            chanceI4.setBounds(1000 * w, 20 * k, 88 * w, 70 * k);
            chanceProcent1 = new Label( " ", skin, "title");
            chanceProcent1.setFontScale(1.2f * k);
            chanceProcent1.setPosition(1110 * w, 420 * k);
            chanceProcent2 = new Label(" ", skin, "title");
            chanceProcent2.setFontScale(1.2f * k);
            chanceProcent2.setPosition(1110 * w, 290 * k);
            chanceProcent3 = new Label(" ", skin, "title");
            chanceProcent3.setFontScale(1.2f * k);
            chanceProcent3.setPosition(1110 * w, 160 * k);
            chanceProcent4 = new Label(" ", skin, "title");
            chanceProcent4.setFontScale(1.2f * k);
            chanceProcent4.setPosition(1110 * w, 30 * k);
            playerTable.addActor(chanceI1);
            playerTable.addActor(chanceI2);
            playerTable.addActor(chanceI3);
            playerTable.addActor(chanceI4);
            playerTable.addActor(chanceProcent1);
            playerTable.addActor(chanceProcent2);
            playerTable.addActor(chanceProcent3);
            playerTable.addActor(chanceProcent4);
            playerTable.addActor(foodImage);
            playerTable.addActor(rockImage);
            playerTable.addActor(timeImage);
            playerTable.addActor(foodLabel);
            playerTable.addActor(rockLabel);
            playerTable.addActor(timeLabel);
        }
        if (what.equals("food")){
            rockImage.setBounds(350 * w , 350 * k , 110 * w , 110 * k);
            waterImage.setBounds(364 * w , 180 * k , 96 * w ,110 * k);
            timeImage.setBounds(350 * w , 10 * k , 110 * w ,110 * k);
            waterLabel.setPosition(600 * w, 230 * w);
            rockLabel.setPosition(600 * w, 400 * w);
            timeLabel.setPosition(600 * w , 60 * w);
            rockLabel.setFontScale(1.5f * k);
            waterLabel.setFontScale(1.5f * k);
            timeLabel.setFontScale(1.5f * k);
            chanceI1 = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
            chanceI2 = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
            chanceI3 = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
            chanceI4 = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
            chanceI1.setBounds(1000 * w, 410 * k, 80 * w, 80 * k);
            chanceI2.setBounds(1000 * w, 280 * k, 80 * w, 80 * k);
            chanceI3.setBounds(1000 * w, 150 * k, 80 * w, 80 * k);
            chanceI4.setBounds(1000 * w, 20 * k, 88 * w, 80 * k);
            chanceProcent1 = new Label( " ", skin, "title");
            chanceProcent1.setFontScale(1.2f * k);
            chanceProcent1.setPosition(1110 * w, 420 * k);
            chanceProcent2 = new Label(" ", skin, "title");
            chanceProcent2.setFontScale(1.2f * k);
            chanceProcent2.setPosition(1110 * w, 290 * k);
            chanceProcent3 = new Label(" ", skin, "title");
            chanceProcent3.setFontScale(1.2f * k);
            chanceProcent3.setPosition(1110 * w, 160 * k);
            chanceProcent4 = new Label(" ", skin, "title");
            chanceProcent4.setFontScale(1.2f * k);
            chanceProcent4.setPosition(1110 * w, 30 * k);
            playerTable.addActor(chanceI1);
            playerTable.addActor(chanceI2);
            playerTable.addActor(chanceI3);
            playerTable.addActor(chanceI4);
            playerTable.addActor(chanceProcent1);
            playerTable.addActor(chanceProcent2);
            playerTable.addActor(chanceProcent3);
            playerTable.addActor(chanceProcent4);
            playerTable.addActor(rockImage);
            playerTable.addActor(waterImage);
            playerTable.addActor(timeImage);
            playerTable.addActor(waterLabel);
            playerTable.addActor(rockLabel);
            playerTable.addActor(timeLabel);
        }
        if (what.equals("rock")){
            foodImage.setBounds(350 * w , 350 * k , 110 * w , 110 * k);
            waterImage.setBounds(364 * w , 180 * k , 96 * w ,110 * k);
            timeImage.setBounds(350 * w , 10 * k , 110 * w ,110 * k);
            foodLabel.setPosition(600 * w, 230 * k);
            waterLabel.setPosition(600 * w, 400 * k);
            timeLabel.setPosition(600 * w, 40 * k);
            foodLabel.setFontScale(1.5f * k);
            waterLabel.setFontScale(1.5f * k);
            timeLabel.setFontScale(1.5f * k);
            chanceI1 = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
            chanceI2 = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
            chanceI3 = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
            chanceI4 = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
            chanceI1.setBounds(1000 * w, 410 * k, 80 * w, 80 * k);
            chanceI2.setBounds(1000 * w, 280 * k, 80 * w, 80 * k);
            chanceI3.setBounds(1000 * w, 150 * k, 80 * w, 80 * k);
            chanceI4.setBounds(1000 * w, 20 * k, 88 * w, 80 * k);
            chanceProcent1 = new Label( " ", skin, "title");
            chanceProcent1.setFontScale(1.2f * k);
            chanceProcent1.setPosition(1110 * w, 420 * k);
            chanceProcent2 = new Label(" ", skin, "title");
            chanceProcent2.setFontScale(1.2f * k);
            chanceProcent2.setPosition(1110 * w, 290 * k);
            chanceProcent3 = new Label(" ", skin, "title");
            chanceProcent3.setFontScale(1.2f * k);
            chanceProcent3.setPosition(1110 * w, 160 * k);
            chanceProcent4 = new Label(" ", skin, "title");
            chanceProcent4.setFontScale(1.2f * k);
            chanceProcent4.setPosition(1110 * w, 30 * k);
            playerTable.addActor(chanceI1);
            playerTable.addActor(chanceI2);
            playerTable.addActor(chanceI3);
            playerTable.addActor(chanceI4);
            playerTable.addActor(chanceProcent1);
            playerTable.addActor(chanceProcent2);
            playerTable.addActor(chanceProcent3);
            playerTable.addActor(chanceProcent4);
            playerTable.addActor(foodImage);
            playerTable.addActor(waterImage);
            playerTable.addActor(timeImage);
            playerTable.addActor(foodLabel);
            playerTable.addActor(waterLabel);
            playerTable.addActor(timeLabel);
        }
        foodI = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        waterI = new Image (A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        rockI = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        foodI.setBounds(5 * w, 665 * k, 50 * w, 50 * k);
        waterI.setBounds(5 * w, 595 * k, 50 * w, 50 * k);
        rockI.setBounds(5 * w, 515 * k , 50 * w, 55 * k );
        playerTable.addActor(chanceL1);
        playerTable.addActor(chanceL2);
        playerTable.addActor(chanceL3);
        playerTable.addActor(chanceL4);
        playerTable.addActor(chance);
        playerTable.addActor(rockI);
        playerTable.addActor(waterI);
        playerTable.addActor(foodI);
        playerTable.addActor(work);
        playerTable.addActor(foodLabelR);
        playerTable.addActor(waterLabelR);
        playerTable.addActor(rockLabelR);
        updateChoiсeFalse();
        stage.addActor(playerTable);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            manager.first();
            manager.setFirst(true);
            buttons.clear();
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
    public void update(boolean check, int ind){//обнавляет кнопки
        if(check){
            buttons.get(ind).setDrawable(new TextureRegionDrawable(new TextureRegion(A2DAssetManager.manager.get("Image/players/" + main.getPlayers().get(ind).getLinks() + "Work.png", Texture.class))));
            buttons.get(ind).getListeners().clear();
        }
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
    public void updateChoiсeTrue(){//делает labels видимыми
        if (what.equals("water")) {
            foodImage.setVisible(true);
            rockImage.setVisible(true);
            timeImage.setVisible(true);
            timeLabel.setVisible(true);
            rockLabel.setVisible(true);
            foodLabel.setVisible(true);
        }
        if (what.equals("food")) {
            rockImage.setVisible(true);
            waterImage.setVisible(true);
            timeImage.setVisible(true);
            timeLabel.setVisible(true);
            waterLabel.setVisible(true);
            rockLabel.setVisible(true);
        }
        if (what.equals("rock")) {
            foodImage.setVisible(true);
            waterImage.setVisible(true);
            timeImage.setVisible(true);
            timeLabel.setVisible(true);
            waterLabel.setVisible(true);
            foodLabel.setVisible(true);
        }
        work.setVisible(true);
        setVisibleProcentTrue();
    }
    public void updateChoiсeFalse() {//делает Labels невидимыми
        if (what.equals("water")) {
            foodImage.setVisible(false);
            rockImage.setVisible(false);
            timeImage.setVisible(false);
            timeLabel.setVisible(false);
            rockLabel.setVisible(false);
            foodLabel.setVisible(false);

        }
        if (what.equals("food")) {
            rockImage.setVisible(false);
            waterImage.setVisible(false);
            timeImage.setVisible(false);
            timeLabel.setVisible(false);
            waterLabel.setVisible(false);
            rockLabel.setVisible(false);
        }
        if (what.equals("rock")) {
            foodImage.setVisible(false);
            waterImage.setVisible(false);
            timeImage.setVisible(false);
            timeLabel.setVisible(false);
            waterLabel.setVisible(false);
            foodLabel.setVisible(false);
        }
        work.setVisible(false);
        setVisibleProcentFalse();
    }
    public void setResVisibleTrue(int sum){//заполнить labels и сделать их видимыми
        if (what.equals("water")) {
            foodLabel.setText(manager.foodFactoryW);
            rockLabel.setText(manager.rockFactoryW);
            timeLabel.setText(manager.timeFactoryW);
            foodLabel.setVisible(true);
            rockLabel.setVisible(true);
            timeLabel.setVisible(true);
        }
        if (what.equals("food")) {
            waterLabel.setText(manager.waterFactoryF);
            rockLabel.setText(manager.rockFactoryF);
            timeLabel.setText(manager.timeFactoryF);
            waterLabel.setVisible(true);
            rockLabel.setVisible(true);
            timeLabel.setVisible(true);
        }
        if (what.equals("rock")) {
            foodLabel.setText(manager.foodFactoryR);
            waterLabel.setText(manager.waterFactoryR);
            timeLabel.setText(manager.timeFactoryR);
            foodLabel.setVisible(true);
            waterLabel.setVisible(true);
            timeLabel.setVisible(true);
        }
    }
    public void setLabel(){//сзаполнить labels
        if (what.equals("water")) {
            foodLabel.setText(manager.getFoodForWaterFactory());
            timeLabel.setText(manager.getTimeForWaterFactory());
            rockLabel.setText(manager.getRockForWaterFactory());
        }
        if (what.equals("food")) {
            waterLabel.setText(manager.getWaterForFoodFactory());
            timeLabel.setText(manager.getTimeForFoodFactory());
            rockLabel.setText(manager.getRockForFoodFactory());

        }
        if (what.equals("rock")) {
            foodLabel.setText(manager.getFoodForRockFactory());
            timeLabel.setText(manager.getTimeForRockFactory());
            waterLabel.setText(manager.getWaterForRockFactory());
        }
        setTextProcent();

    }
    public BuildingsMakeManager getManager(){
        return manager;
    }
    private void setVisibleProcentFalse(){//сделать не видимыми шансы выпадения
        chance.setVisible(false);
        chanceI1.setVisible(false);
        chanceI2.setVisible(false);
        chanceI3.setVisible(false);
        chanceI4.setVisible(false);
        chanceProcent1.setVisible(false);
        chanceProcent2.setVisible(false);
        chanceProcent3.setVisible(false);
        chanceProcent4.setVisible(false);
        chanceL1.setVisible(false);
        chanceL2.setVisible(false);
        chanceL3.setVisible(false);
        chanceL4.setVisible(false);

    }
    private void setVisibleProcentTrue(){//сделать видимыми шансы выпадения
        setTextProcent();
        chance.setVisible(true);
        chanceI1.setVisible(true);
        chanceI2.setVisible(true);
        chanceI3.setVisible(true);
        chanceI4.setVisible(true);
        chanceProcent1.setVisible(true);
        chanceProcent2.setVisible(true);
        chanceProcent3.setVisible(true);
        chanceProcent4.setVisible(true);
        chanceL1.setVisible(true);
        chanceL2.setVisible(true);
        chanceL3.setVisible(true);
        chanceL4.setVisible(true);
    }
    private void setTextProcent(){//установить пройенты шанса
        if (what.equals("water")) {
            chanceProcent1.setText(" = " + manager.getWaterProcent1() + "%");
            chanceProcent2.setText(" = " + manager.getWaterProcent2() + "%");
            chanceProcent3.setText(" = " + manager.getWaterProcent3() + "%");
            chanceProcent4.setText(" = " + manager.getWaterProcent4() + "%");
        }
        if (what.equals("food")) {
            chanceProcent1.setText(" = " + manager.getFoodProcent1() + "%");
            chanceProcent2.setText(" = " + manager.getFoodProcent2() + "%");
            chanceProcent3.setText(" = " + manager.getFoodProcent3() + "%");
            chanceProcent4.setText(" = " + manager.getFoodProcent4() + "%");
        }
        if (what.equals("rock")) {
            chanceProcent1.setText(" = " + manager.getRockProcent1() + "%");
            chanceProcent2.setText(" = " + manager.getRockProcent2() + "%");
            chanceProcent3.setText(" = " + manager.getRockProcent3() + "%");
            chanceProcent4.setText(" = " + manager.getRockProcent4() + "%");
        }
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
