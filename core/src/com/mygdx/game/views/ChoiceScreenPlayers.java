package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;
import com.mygdx.game.manager.DialogGame;
import com.mygdx.game.views.MainGame.BuildingBuildScreen;

import java.util.ArrayList;

public class ChoiceScreenPlayers implements Screen {
    private MyGdxGame parent;
    private MenuScreen menu;
    Stage stage;
    private TextureRegionDrawable minerDrawable;
    private TextureRegionDrawable engineerDrawable;
    private TextureRegionDrawable biologyDrawable;
    private TextureRegionDrawable strengthDrawable ;
    private static TextureRegionDrawable gunDrawable;
    private static TextureRegionDrawable plusDrawable;
    private static TextureRegionDrawable minusDrawable;
    private static Image waterI;
    private static Image foodI;
    private Texture backgroundTexture;
    private DialogGame dialogGame;
    private Image rockI;
    private SpriteBatch batch;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Image background;
    private Skin skint;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)

    private Label countRock;
    private Label countFood;
    private Label countWater;
    private Label next;
    private Label count;

    private ImageButton plusRock;
    private ImageButton minusRock;

    private ImageButton plusFood;
    private ImageButton minusFood;

    private ImageButton plusWater;
    private ImageButton minusWater;
    private Label backToMenu;
    private Skin skin;
    private float pressedTime;//время, прошедщее со старта активности

    public ChoiceScreenPlayers(MyGdxGame myGdxGame, MenuScreen menu, SpriteBatch batch) {
        parent = myGdxGame;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        this.menu = menu;
        this.batch = batch;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        dialogGame = new DialogGame();
        backgroundTexture = A2DAssetManager.manager.get(A2DAssetManager.background);
        background = new Image(backgroundTexture);
        background.setSize(1280 * w, 720 * k);
        background.setPosition(0f,0f);
        minerDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.MinerS, Texture.class));
        engineerDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.EngineerS, Texture.class));
        biologyDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.BiologyS, Texture.class));
        strengthDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.StrengthS, Texture.class));
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        backToMenu = new Label("Back to menu", skin, "title");
        backToMenu.setFontScale(1.2f * k);
        skint = new Skin(Gdx.files.internal("skin/Clean_Crispy_UI_Skin//cleancrispyui/clean-crispy-ui.json"));
        pressedTime = 0;
    }
    public DialogGame getDialogGame(){
        return this.dialogGame;
    }
    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        rockI = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        foodI = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        waterI = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        gunDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.gun, Texture.class));
        plusDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.plus, Texture.class));
        minusDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.minus, Texture.class));
        Table table = new Table();
        table.setFillParent(true);
       // table.setDebug(true);
        table.addActor(background);
//        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        if (!dialogGame.minerButtonPressed){
            minerDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.MinerS, Texture.class));
        }
        if (dialogGame.minerButtonPressed){
            minerDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.minerF, Texture.class));
        }

        if (!dialogGame.engineerButtonPressed) {
            engineerDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.EngineerS, Texture.class));
        }
        if (dialogGame.engineerButtonPressed){
            engineerDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.engineerF, Texture.class));
        }
        if (!dialogGame.biologyButtonPressed) {
             biologyDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.BiologyS, Texture.class));
        }
        if (dialogGame.biologyButtonPressed) {
            biologyDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.biologyF, Texture.class));
        }
        if (!dialogGame.strengthButtonPressed) {
            strengthDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.StrengthS, Texture.class));
        }
        if (dialogGame.strengthButtonPressed){
            strengthDrawable = new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.strengthF, Texture.class));
        }
        waterI.setBounds(771 * w,660 * k, 40 * w,60 * k);
        foodI.setBounds(433 * w,665 * k,50 * w,50 * k);
        rockI.setBounds(120 * w,660 * k, 40 * w,60 * k);
        backToMenu.setBounds(430 * w, 20 * k, 400 * w, 100 * k);
        count = new Label(dialogGame.getWeight() + "/" + dialogGame.getMAX_WEIGHT(), skin, "title");
        count.setFontScale(1f * k);
        count.setPosition(1090 * w,630 * k);
        countRock = new Label(String.valueOf(dialogGame.getRock()), skin, "title");
        countRock.setFontScale(0.8f * k);
        countRock.setPosition(130 * k,620 * k);
        countFood = new Label(String.valueOf(dialogGame.getFood()), skin, "title");
        countFood.setPosition(450 * w,620 * k);
        countFood.setFontScale(0.8f * k);
        countWater = new Label(String.valueOf(dialogGame.getWater()), skin, "title");
        countWater.setFontScale(0.8f * k);
        countWater.setPosition(780 * w,620 * k);
        ImageButton minerButton = new ImageButton(minerDrawable);
        minerButton.setBounds(15 * w,125 * k,300 * w,470 * k);
        ImageButton engineerButton = new ImageButton(engineerDrawable);
        engineerButton.setBounds(335 * w,125 * k, 300 * w,470 * k);
        ImageButton biologyButton = new ImageButton(biologyDrawable);
        biologyButton.setBounds(635 * w,125 * k,300 * w,470 * k);
        ImageButton strengthButton = new ImageButton(strengthDrawable);
        strengthButton.setBounds(975 * w,125 * k,300 * w,470 * k);
        ImageButton gunButton = new ImageButton(gunDrawable);
        gunButton.setBounds(20 * w,10 * k, 100 * w,100 * k);
        next = new Label("Next", skin, "title");
        next.setFontScale(1.2f * k);
        next.setBounds(1060 * w,20 * k, 100 * w, 100 * k);
        plusRock = new ImageButton(plusDrawable);
        plusRock.setBounds(25 * w, 605 * k,70 * w,70 * k);
        minusRock = new ImageButton(minusDrawable);
        minusRock.setBounds(185 * w,605 * k,70 * w,70 * k);
        plusFood = new ImageButton(plusDrawable);
        plusFood.setBounds(515 * w, 605 * k,70 * w,70 * k);
        minusFood = new ImageButton(minusDrawable);
        minusFood.setBounds(355 * w, 605 * k,70 * w,70 * k);
        plusWater = new ImageButton(plusDrawable);
        plusWater.setBounds(845 * w, 605 * k,70 * w,70 * k);
        minusWater = new ImageButton(minusDrawable);
        minusWater.setBounds(685 * w,605 * k,70 * w,70 * k);
        if (!dialogGame.gunButtonPressed){
            gunButton.getImage().setColor(Color.WHITE);
        }
        if (dialogGame.gunButtonPressed){
            gunButton.getImage().setColor(Color.GRAY);
        }
        ClickListener minerListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                parent.getDialogScreen().setWho("Miner");
                dialogGame.setPutWeight(dialogGame.getMinerWeight());
                pressedTime = 0;
                dialogGame.setName("Miner");

                parent.changeScreen(MyGdxGame.DIALOG);
            }
        };
        minerButton.addListener(minerListener);
        table.addActor(minerButton);
        ClickListener engineerListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y) {
                parent.getDialogScreen().setWho("Engineer");
                dialogGame.setPutWeight(dialogGame.getEngineerWeight());
                dialogGame.setName("Engineer");
                pressedTime = 0;
                parent.changeScreen(MyGdxGame.DIALOG);
            }
        };
        engineerButton.addListener(engineerListener);
        table.addActor(engineerButton);
        ClickListener biologyListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                parent.getDialogScreen().setWho("Biology");
                dialogGame.setPutWeight(dialogGame.getBiologyWeight());
                dialogGame.setName("Biology");
                pressedTime = 0;
                parent.changeScreen(MyGdxGame.DIALOG);
            }
        };
        biologyButton.addListener(biologyListener);
        table.addActor(biologyButton);
        ClickListener strengthListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                parent.getDialogScreen().setWho("Strength");
                dialogGame.setPutWeight(dialogGame.getStrengthWeight());
                dialogGame.setName("Strength");
                pressedTime = 0;
                parent.changeScreen(MyGdxGame.DIALOG);
            }
        };
        strengthButton.addListener(strengthListener);
        table.addActor(strengthButton);
        ClickListener gunListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                parent.getDialogScreen().setWho("Gun");
                pressedTime = 0;
                dialogGame.setPutWeight(dialogGame.getGunWeight());
                dialogGame.setName("Gun");
                parent.changeScreen(MyGdxGame.DIALOG);
            }
        };
        gunButton.addListener(gunListener);
        table.addActor(gunButton);
        ClickListener nextListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (dialogGame.players.size() > 0) {
                    menu.setC(true);
                    dialogGame.setFalse();
                    parent.changeScreen(MyGdxGame.GAMEPLAY);
                }
                else{
                    NoPlayers dialogpl = new NoPlayers("uyfdt", skint);
                    dialogpl.show(stage);
                    dialogpl.setPosition(400 * w, 300 * k);
                }
            }
        };
        next.addListener(nextListener);
        ClickListener plusRockListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (dialogGame.check(dialogGame.getRockWeight())){
                    dialogGame.setPlusWeight(dialogGame.getRockWeight());
                    dialogGame.setRock(1);
                    countRock.setText(String.valueOf(dialogGame.getRock()));
                    count.setText(dialogGame.getWeight() + "/" + dialogGame.getMAX_WEIGHT());

                }
            }
        };
        ClickListener backToMenuListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                BackToMenu back = new BackToMenu("", skint);
                back.show(stage);
                back.setPosition(250 * w, 300 * k);

            }
        };
        backToMenu.addListener(backToMenuListener);
        plusRock.addListener(plusRockListener);
        table.addActor(plusRock);
        ClickListener minusRockListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y){
                if (dialogGame.getRock() != 0){
                    dialogGame.setMinusWeight(dialogGame.getRockWeight());
                    dialogGame.setRock(-1);
                    countRock.setText(String.valueOf(dialogGame.getRock()));
                    count.setText(dialogGame.getWeight() + "/" + dialogGame.getMAX_WEIGHT());

                }
            }
        };
        minusRock.addListener(minusRockListener);
        table.addActor(minusRock);
        ClickListener plusWaterListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (dialogGame.check(dialogGame.getWaterWeight())){
                    dialogGame.setPlusWeight(dialogGame.getWaterWeight());
                    dialogGame.setWater(1);
                    countWater.setText(String.valueOf(dialogGame.getWater()));
                    count.setText(dialogGame.getWeight() + "/" + dialogGame.getMAX_WEIGHT());

                }
            }
        };
        plusWater.addListener(plusWaterListener);
        table.addActor(plusWater);
        ClickListener minusWaterListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (dialogGame.getWater() != 0){
                    dialogGame.setMinusWeight(dialogGame.getWaterWeight());
                    dialogGame.setWater(-1);
                    countWater.setText(String.valueOf(dialogGame.getWater()));
                    count.setText(dialogGame.getWeight() + "/" + dialogGame.getMAX_WEIGHT());

                }
            }
        };
        minusWater.addListener(minusWaterListener);
        table.addActor(minusWater);
        /*ClickListener plusFoodListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (dialogGame.check(dialogGame.getFoodWeight())){
                    dialogGame.setPlusWeight(dialogGame.getFoodWeight());
                    dialogGame.setFood(1);
                    countFood.setText(String.valueOf(dialogGame.getFood()));
                    count.setText(String.valueOf(dialogGame.getWeight()) + "/" + String.valueOf(dialogGame.getMAX_WEIGHT()));

                }
            }
        };
        plusFood.addListener(plusFoodListener);*/
        plusFood.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (dialogGame.check(dialogGame.getFoodWeight())) {
                    dialogGame.setPlusWeight(dialogGame.getFoodWeight());
                    dialogGame.setFood(1);
                    countFood.setText(String.valueOf(dialogGame.getFood()));
                    count.setText(dialogGame.getWeight() + "/" + dialogGame.getMAX_WEIGHT());
                }
            }
        });
        table.addActor(plusFood);
        ClickListener minusFoodListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (dialogGame.getFood() != 0){
                    dialogGame.setMinusWeight(dialogGame.getFoodWeight());
                    dialogGame.setFood(-1);
                    countFood.setText(String.valueOf(dialogGame.getFood()));
                    count.setText(dialogGame.getWeight() + "/" + dialogGame.getMAX_WEIGHT());

                }
            }
        };
        minusFood.addListener(minusFoodListener);
        table.addActor(backToMenu);
        table.addActor(minusFood);
        table.addActor(countWater);
        table.addActor(countRock);
        table.addActor(countFood);
        table.addActor(count);
        table.addActor(rockI);
        table.addActor(waterI);
        table.addActor(foodI);
        table.addActor(next);
        stage.addActor(table);
    }
    public ArrayList<String> getArray(){
        return dialogGame.players;
    }
    public int getFood(){
        return dialogGame.getFood();
    }
    public int getRock(){
        return dialogGame.getRock();
    }
    public int getWater(){
        return dialogGame.getWater();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        pressedTime += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && pressedTime >= 0.4f) {
            BackToMenu back = new BackToMenu("", skint);
            back.show(stage);
            back.setPosition(250 * w, 300 * k);
        }

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
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
        //stage.dispose();
        //parent.dispose();
        //batch.dispose();
        backgroundTexture.dispose();

    }
    public static class NoPlayers extends Dialog {
        float k = MyGdxGame.k;
        public NoPlayers(String title, Skin skin) {
            super(title, skin);
        }

        public NoPlayers(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }

        public NoPlayers(String title, WindowStyle windowStyle) {
            super(title, windowStyle);
        }
        {
            text("Please select players").setScale(2.2f * k);
            button("Ok").setScale(2.2f * k);
        }
    }
    public static class BackToMenu extends Dialog {
        private float k = MyGdxGame.k;
        float w = MyGdxGame.w;
        private MyGdxGame parent = MenuScreen.parent;
        public BackToMenu(String title, Skin skin) {
            super(title, skin);
        }

        public BackToMenu(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }

        public BackToMenu(String title, WindowStyle windowStyle) {
            super(title, windowStyle);
        }
        {
            text ("Are you sure you want to go to the menu?").setScale(2.2f * k, 2.2f * k);
            button("Yes", "Yes").setScale(2.2f * w, 2.2f * k);
            button("No", "No").setScale(2.2f * w, 2.2f * k);
        }
        @Override
        protected void result(Object object) {
            if ("Yes".equals(object.toString())){
                parent.getChoiceScreenPlayers().getDialogGame().setNull();
                parent.getChoiceScreenPlayers().setTimeNull();
                parent.changeScreen(MyGdxGame.MENU);
            }
        }
    }

//    public static class player extends Dialog {
//        float k = BuildingBuildScreen.k;
//        public player(String title, Skin skin) {
//            super(title, skin);
//        }
//        {
//            text("Please select players").setScale(2.2f * k);
//            button("Ok").setScale(2.2f * k);
//        }
//    }
    public void setTimeNull(){//обнуление активного время
        pressedTime = 0;
    }
}

