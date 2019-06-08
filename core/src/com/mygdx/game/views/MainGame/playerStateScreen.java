package com.mygdx.game.views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;
import com.mygdx.game.manager.PlayerStateManager;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class playerStateScreen implements Screen {
    private MyGdxGame parent;
    private GameMainScreen main;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Stage stage;
    private Batch batch;
    private Label foodCount;
    private Label waterCount;
    private ArrayList<TextButton> buttons;
    private PlayerStateManager manager;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private Skin skin;
    private Image foodI;
    private  Image waterI;
    private Image firstAidI;
    private Label firstAidCount;
    private Array<Image> health;
    private ShapeRenderer shapeRenderer;
    public playerStateScreen(MyGdxGame parent, GameMainScreen main, SpriteBatch batch) {
        this.parent = parent;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        this.main = main;
        buttons = new ArrayList<TextButton>();
        this.batch = batch;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        shapeRenderer = new ShapeRenderer();
        health = new Array<Image>();
        stage = new Stage(viewport);
//        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        manager = new PlayerStateManager(main);
        waterI = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        foodI = new Image (A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        firstAidI = new Image(A2DAssetManager.manager.get(A2DAssetManager.firstAid, Texture.class));
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        TextButton backButton = new TextButton("Back", skin);
        backButton.setBounds(1065 * w, 660 * k,200 * w,60 * k);
        backButton.getLabel().setFontScale(0.8f * k);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                buttons.clear();
                health.clear();
                stage.clear();
                parent.changeScreen(MyGdxGame.GAMEPLAY);
            }
        });
        table.addActor(backButton);
        foodCount = new Label(valueOf(main.gameManager.getFood()), skin, "title");
        foodCount.setBounds(200 * w, 680 * k,1 * w,1 * k);
        foodCount.setFontScale(1f * k);
        foodI.setBounds(140 * w, 660 * k, 50 * w, 50 * k);
        table.addActor(foodI);
        table.addActor(foodCount);
        waterCount = new Label(valueOf(main.gameManager.getWater()), skin, "title");
        waterCount.setBounds(500 * w, 680 * k,1 * w,1 * k);
        waterCount.setFontScale(1f * k);
        waterI.setBounds(440 * w, 660 * k, 50 * w, 50 * k);
        firstAidI.setBounds(740 * w, 660 * k, 50 * w, 50 * k);
        firstAidCount = new Label(valueOf(main.getMainManager().getCountFirstAid()), skin, "title");
        firstAidCount.setBounds(800 * w, 680 * k,1 * w,1 * k);
        firstAidCount.setFontScale(1 * k);
        table.addActor(firstAidI);
        table.addActor(firstAidCount);
        table.addActor(waterI);
        table.addActor(waterCount);
        for (int i = 0; i < main.getPlayers().size(); i++){
            final int finalI = i;
            Image temp;
            if (main.getPlayers().get(i).getIsBusy()){
                temp = new Image(A2DAssetManager.manager.get("Image/" + main.getPlayers().get(i).getLinks() + "Work.png", Texture.class));
            }
            else {
                temp = new Image(A2DAssetManager.manager.get("Image/" + main.getPlayers().get(i).getName() + "S.png", Texture.class));
            }
            temp.setBounds(10 * w, (528 - (i * 142)) * k, 86 * w, 122 * k);
            table.addActor(temp);
            for (int g = 0; g < main.getPlayers().get(i).getHealth(); g++){
                Image heartImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.heart, Texture.class));
                heartImage.setBounds((100 + (g * 55)) * w, (518 - (i * 142) + 41) * k, 50 * w, 50 * k);
                health.add(heartImage);
            }
            for (int g = main.getPlayers().get(i).getMax_health(); g > main.getPlayers().get(i).getHealth(); g--){
                Image heartImageF = new Image(A2DAssetManager.manager.get(A2DAssetManager.heartF, Texture.class));
                heartImageF.setBounds((100 + ((g - 1) * 55)) * w, (518 - (i * 142) + 41) * k, 50 * w, 50 * k);
                health.add(heartImageF);
            }
            final Label countFood = new Label(valueOf(main.getPlayers().get(i).getMaxDayWithoutFood() - main.getPlayers().get(i).getDayWithoutFood()), skin, "title");
            final Label food = new Label("Food : ", skin, "title");
            food.setBounds(360 * w, (583 - (i * 142)) * k, 50 * w,50 * k);
            countFood.setBounds(451 * w, (583 - (i * 142)) * k, 50 * w, 50 * k);
            food.setFontScale(0.6f * k);
            countFood.setFontScale(0.6f * k);
            final Label stateFood = new Label(manager.setStateFood(finalI), skin, "title");
            stateFood.setFontScale(0.6f * k);
            if (stateFood.getText().length() > 10) {
                stateFood.setBounds(320 * w, (533 - (i * 142)) * k, 50 * w, 50 * k);
            }
            else if (stateFood.getText().length() == 6){
                stateFood.setBounds(360 * w, (533 - (i * 142)) * k, 50 * w,50 * k);
            }
            else{
                stateFood.setBounds(390 * w, (533 - (i * 142)) * k, 50 * w,50 * k);
            }
            final Label countWater = new Label(valueOf(main.getPlayers().get(i).getMaxDayWithoutWater() - main.getPlayers().get(i).getDayWithoutWater()), skin, "title");
            Label water = new Label("Water : ", skin, "title");
            countWater.setFontScale(0.6f * k);
            water.setFontScale(0.6f * k);
            water.setBounds(565 * w,(583 - (i * 142)) * k, 50 * w, 50 * k);
            countWater.setBounds(675 * w,(583 - (i * 142)) * k, 50 * w, 50 * k);
            final Label stateWater = new Label(manager.setStateWater(finalI), skin, "title");
            stateWater.setFontScale(0.6f * k);
            stateWater.setBounds(540 * w,(533 - (i * 142)) * k, 50 * w, 50 * k);
            table.addActor(food);
            table.addActor(countFood);
            table.addActor(stateFood);
            table.addActor(water);
            table.addActor(countWater);
            table.addActor(stateWater);
            final TextButton giveFood = new TextButton("Give food", skin);
            giveFood.setBounds(775 * w, (558 - (i * 142)) * k, 130 * w, 50 * k);
            giveFood.getLabel().setFontScale(0.8f * k);
            buttons.add(giveFood);
            if (main.getPlayers().get(i).getDayWithoutFood() > 0 && main.getMainManager().getFood() > 0) {
                giveFood.setColor(Color.WHITE);
                giveFood.getLabel().setColor(Color.WHITE);
                giveFood.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent in, float x, float y) {
                        countFood.setText(valueOf(manager.feed(finalI)));
                        if (parent.getGameScreen().getMainManager().getFood() > 0){
                            giveFood.getLabel().setColor(Color.GRAY);
                            giveFood.setColor(Color.GRAY);
                            giveFood.getListeners().clear();
                        }
                        else{
                            for (int i = 0; i < buttons.size(); i++){
                                buttons.get(i).getLabel().setColor(Color.GRAY);
                                buttons.get(i).setColor(Color.GRAY);
                            }
                        }

                        stateFood.setText(manager.setStateFood(finalI));
                        if (stateFood.getText().length() > 10) {
                            stateFood.setBounds(320 * w, (533 - (finalI * 142)) * k, 50 * w, 50 * k);
                        }
                        else if (stateFood.getText().length() == 6){
                            stateFood.setBounds(360 * w, (533 - (finalI * 142)) * k, 50 * w,50 * k);
                        }
                        else{
                            stateFood.setBounds(390 * w, (533 - (finalI * 142)) * k, 50 * w,50 * k);
                        }
                    }
                });
                giveFood.setDisabled(false);
            }
            else{
                giveFood.getLabel().setColor(Color.GRAY);
                giveFood.setColor(Color.GRAY);
                giveFood.getListeners().clear();
            }
            table.addActor(giveFood);
            final TextButton giveWater = new TextButton("Give water", skin);
            giveWater.setBounds(955 * w, (518 - (i * 142) + 41) * k, 130 * w, 50 * k);
            giveWater.getLabel().setFontScale(0.8f * k);
            buttons.add(giveWater);
            if (main.getPlayers().get(i).getDayWithoutWater() > 0 && main.getMainManager().getWater() >  0 ) {
                giveWater.setColor(Color.WHITE);
                giveWater.getLabel().setColor(Color.WHITE);
                giveWater.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent in, float x, float y) {
                        countWater.setText(valueOf(manager.water(finalI)));
                        if (main.getMainManager().getWater() > 0) {
                            giveWater.getListeners().clear();
                            giveWater.setColor(Color.GRAY);
                            giveWater.getLabel().setColor(Color.GRAY);
                            stateWater.setText(manager.setStateWater(finalI));
                        }
                        else {
                            for (int i = 0 ; i < buttons.size(); i++){
                                buttons.get(i).getLabel().setColor(Color.GRAY);
                                buttons.get(i).setColor(Color.GRAY);
                            }
                        }
                    }
                });
                giveWater.setDisabled(false);
            }
            else{
                giveWater.getListeners().clear();
                giveWater.setColor(Color.GRAY);
                giveWater.getLabel().setColor(Color.GRAY);
            }
            table.addActor(giveWater);
            final TextButton giveHealth = new TextButton("Give health", skin);
            giveHealth.setBounds(1135 * w, (518 - (i * 142) + 41) * k, 130 * w, 50 * k);
            giveHealth.getLabel().setFontScale(0.8f * k);
            buttons.add(giveWater);
            if (main.getPlayers().get(i).getHealth() < main.getPlayers().get(i).getMax_health() && main.getMainManager().getCountFirstAid() > 0) {
                giveHealth.setColor(Color.WHITE);
                giveHealth.getLabel().setColor(Color.WHITE);
                final int finalI1 = i;
                giveHealth.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent in, float x, float y) {
                        if (main.getMainManager().getCountFirstAid() != 0) {
                            int m = 0;
                            for (int g = 0; g < finalI1; g++) {
                                m += main.getPlayers().get(g).getMax_health();
                            }
//                        if (m != 0){
//                            m--;
//                        }
                            for (int g = m; g < m + main.getPlayers().get(finalI1).getMax_health(); g++) {
                                Gdx.app.log("ARRAY", "1");
                                health.get(g).setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.heart, Texture.class)));
                            }
                            giveHealth.getListeners().clear();
                            giveHealth.setColor(Color.GRAY);
                            giveHealth.getLabel().setColor(Color.GRAY);
                            main.getMainManager().setMinusFirstAid();
                            main.getPlayers().get(finalI1).setHealth(main.getPlayers().get(finalI1).getMax_health());
                            updateFirstAid();
                        } else {
                            giveHealth.getListeners().clear();
                            giveHealth.setColor(Color.GRAY);
                            giveHealth.getLabel().setColor(Color.GRAY);
                        }
                    }
                });
                giveHealth.setDisabled(false);
            }
            else{
                giveHealth.getListeners().clear();
                giveHealth.setColor(Color.GRAY);
                giveHealth.getLabel().setColor(Color.GRAY);
            }
            table.addActor(giveHealth);
            if(main.getPlayers().get(i).getIsBusy()){
                giveFood.setColor(Color.GRAY);
                giveFood.getLabel().setColor(Color.GRAY);
                giveWater.setColor(Color.GRAY);
                giveWater.getLabel().setColor(Color.GRAY);
                giveHealth.setColor(Color.GRAY);
                giveHealth.getLabel().setColor(Color.GRAY);
                Label work = new Label("At work", skin, "title");
                work.setScale(2f * k);
                work.setPosition(950 * w, (530 - (i * 142) + 41) * k);
                work.setColor(Color.RED);
                table.addActor(work);
            }

        }
        for (int i = 0; i < health.size; i++){
            table.addActor(health.get(i));
        }
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            buttons.clear();
            health.clear();
            stage.clear();
            parent.changeScreen(MyGdxGame.GAMEPLAY);
        }
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(0, 650 * k, 1280 * w, 650 * k);
        for (int i = 0; i < main.getPlayers().size(); i++){
            shapeRenderer.setColor(Color.MAGENTA);
            shapeRenderer.line(0 * w, (523 - (i * 142)) * k, 1280 * w, (523 - (i * 142)) * k);
        }
        shapeRenderer.end();
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
        shapeRenderer.dispose();
    }
    public void updateFood(){//обновление label
        foodCount.setText(valueOf(main.gameManager.getFood()));
    }
    public void updateWater(){//обновление label
        waterCount.setText(valueOf(main.gameManager.getWater()));
    }
    public void updateFirstAid (){//обновлени label
        firstAidCount.setText(valueOf(main.gameManager.getCountFirstAid()));
    }
}
