package com.mygdx.game.Views;

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
import com.mygdx.game.Managers.DialogGame;

public class DialogScreen implements Screen {
    private MyGdxGame parent;
    private Stage stage;
    private DialogGame dialogGame;
    private TextButton back;
    private TextButton choice;
    private int choiceInt;
    private FitViewport viewport;
    private Label call;

    //переменные для отображения характеристик персонажей
    private int S;
    private int M;
    private int B;
    private int E;


    private Label engineer;
    private Label miner;
    private Label biology;
    private Label strength;
    private Skin skin;
    private OrthographicCamera cam;
    private String who;
    private SpriteBatch batch;
    private Table table;
    private Label skills;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
//    private Image biologySkills;
//    private Image minerSkills;
//    private Image strengthSkills;
//    private Image engineerSkills;
    public DialogScreen(MyGdxGame myGdxGame, DialogGame dialogGame, SpriteBatch batch) {
        this.dialogGame = dialogGame;
        this.parent = myGdxGame;
        this.batch = batch;
        cam = new OrthographicCamera();
        table = new Table();
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        engineer = new Label("Engineering skill: ", skin, "title");
        biology = new Label("Biological skill: ", skin, "title");
        strength = new Label("Strength skill: ", skin, "title");
        miner = new Label("Miner skill: ", skin, "title");
        engineer.setScale(1.2f * k);
        biology.setScale(1.2f * k);
        miner.setScale(1.2f * k);
        strength.setScale(1.2f * k);
        call = new Label("", skin, "title");
        call.setFontScale(2f * k);
        call.setPosition(350 * w, 500 * k);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
//        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        if (!dialogGame.getButtonPressed()){
            choiceInt = 0;
            choice = new TextButton("Take", skin);
        }
        if ((dialogGame.getButtonPressed())){
            choiceInt = 1;
            choice = new TextButton("Return", skin);
        }
        choice.getLabel().setFontScale(1.5f * k);
        choice.setBounds(335 * w,610 * k,300 * w,100 * k);
        back = new TextButton("Back", skin);
        back.getLabel().setFontScale(1.5f * k);
        back.setBounds(645 * w,610 * k, 300 * w,100 * k);
        ClickListener backListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y) {
                table.clear();
                stage.clear();
                parent.changeScreen(MyGdxGame.CHOICEPLAYER);
            }
        };
        back.addListener(backListener);
        table.addActor(back);
        ClickListener choiseListener = new ClickListener() {
            @Override
            public void clicked(InputEvent in, float x, float y) {
                if (!(!dialogGame.check(dialogGame.getPutWeight()) && !dialogGame.getButtonPressed())) {
                    dialogGame.setButtonPressed();
                    if (choiceInt == 0) {
                        dialogGame.setPlusWeight(dialogGame.getPutWeight());
                            dialogGame.addPlayer();
                    }
                    if (choiceInt == 1) {
                        dialogGame.setMinusWeight(dialogGame.getPutWeight());
                            dialogGame.deletePlayer();
                    }
                    table.clear();
                    stage.clear();
                    parent.changeScreen(MyGdxGame.CHOICEPLAYER);
                }
            }
        };
        choice.addListener(choiseListener);
        table.addActor(choice);
        skills = new Label("Skills", skin, "title");
        skills.setFontScale(1.5f * k);
        skills.setPosition(530 * w, 380 * k);
        table.addActor(call);
        table.addActor(skills);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            table.clear();
            stage.clear();
            parent.changeScreen(MyGdxGame.CHOICEPLAYER);
        }
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,true);
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
        stage.dispose();
        batch.dispose();
    }
    public void setWho(String name){//установка отображения умений конкретного персонажа
        who = name;
        Image temp1 = new Image();
        engineer.setVisible(true);
        biology.setVisible(true);
        miner.setVisible(true);
        strength.setVisible(true);
        if (who.equals("Miner")){
            call.setText("Call:  Croft");
            S = 2;
            M = 5;
            B = 3;
            E = 3;
            temp1.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.MinerS, Texture.class)));
        }
        if (who.equals("Strength")){
            call.setText("Call: Tekken");
            S = 4;
            M = 3;
            B = 1;
            E = 2;
            temp1.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.strength, Texture.class)));
        }
        if (who.equals("Biology")){
            call.setText("Call:   Ded");
            S = 1;
            M = 1;
            B = 5;
            E = 1;
            temp1.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.biology, Texture.class)));
        }
        if (who.equals("Engineer")){
            call.setText("Call: Scooter");
            S = 3;
            M = 2;
            B = 2;
            E = 4;
            temp1.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.engineer, Texture.class)));
        }
        if (who.equals("Gun")){
            S = 0;
            M = 0;
            B = 0;
            E = 0;
            engineer.setVisible(false);
            biology.setVisible(false);
            miner.setVisible(false);
            strength.setVisible(false);
        }
        temp1.setBounds(10 * w, 420 * k, 100 * w, 156 * k);
        table.addActor(temp1);
        strength.setPosition(10 * w, 230 * k);
        table.addActor(strength);
        for (int i = 0; i < S; i++){
            Image temp = new Image(A2DAssetManager.manager.get(A2DAssetManager.strengthSkills, Texture.class));
            temp.setBounds((450 + (100 * i)) * w , 230 * k, 50 * w, 50 * k);
            table.addActor(temp);
        }
        miner.setPosition(10 * w, 160 * k);
        table.addActor(miner);
        for (int i = 0; i < M; i++){
            Image temp = new Image(A2DAssetManager.manager.get(A2DAssetManager.minerSkills, Texture.class));
            temp.setBounds((450 + (100 * i)) * w , 160 * k, 50 * w, 50 * k);
            table.addActor(temp);
        }
        biology.setPosition(10 * w, 90 * k);
        table.addActor(biology);
        for (int i = 0; i < B; i++){
            Image temp = new Image(A2DAssetManager.manager.get(A2DAssetManager.biologySkills, Texture.class));

            temp.setBounds((450 + (100 * i)) * w , 90 * k, 50 * w, 50 * k);
            table.addActor(temp);
        }
        engineer.setPosition(10 * w, 20 * k);
        table.addActor(engineer);
        for (int i = 0; i < E; i++){
            Image temp = new Image(A2DAssetManager.manager.get(A2DAssetManager.engineerSkills, Texture.class));

            temp.setBounds((450 + (100 * i)) * w , 20 * k, 50 * w, 50 * k);
            table.addActor(temp);
        }
    }
}
