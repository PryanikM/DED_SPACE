package com.mygdx.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Loader.A2DAssetManager;
import com.mygdx.game.Managers.ContinueManager;

import java.util.ArrayList;

public class MenuScreen implements Screen {
    public static MyGdxGame parent;
    private Stage stage;
    private Preferences prefs;//для сохранения настроек
    private SpriteBatch batch;
    public static boolean pressed;
    private final static String PREFERENCES_CONTINUE = "continue";
    private FitViewport viewport;
    private Skin skint;
    private ArrayList<Texture> backGround;
    private OrthographicCamera cam;
    private Image backGroundI;
    private ContinueManager continueManager;
    public static float k;//константа для экрана (высота)
    public static float w;//константа для экрана (ширина)
    private float timeFrame;//время проигрывания одного кадра
    private float maxTimeStopFrame;//время, между кадрами
    private float fastTime;//время для вспышек
    private int frame;//текущий кадр
    private Skin skin;
    private float pressedTime;//время, с начала работы экрана

    public MenuScreen(MyGdxGame myGdxGame, SpriteBatch batch) {
        parent = myGdxGame;
        prefs = Gdx.app.getPreferences("My Preferences");
        this.batch = batch;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280f * w, 720f * k, cam);
        cam.setToOrtho(false, 1280f * w, 720f * k);
        stage = new Stage(viewport);
        timeFrame = 0.0f;
        maxTimeStopFrame = 0.5f;
        fastTime = 0.08f;
        frame = 0;
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        skint = new Skin(Gdx.files.internal("skin/Clean_Crispy_UI_Skin//cleancrispyui/clean-crispy-ui.json"));
        pressed = false;
        String path = "Image/menuAnimation/animation";
        continueManager = new ContinueManager(parent);
        backGround = new ArrayList<Texture>(3);
        pressedTime = 0;
        for (int i = 0; i < 7; i++){
            backGround.add(A2DAssetManager.manager.get(path + (i + 1) + ".png", Texture.class));
        }

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        backGroundI = new Image(backGround.get(0));
        backGroundI.setBounds(0, 0, 1280f * w, 720f * k);
        table.addActor(backGroundI);
        TextButton Continue = new TextButton("Continue", skin, "menu");
        Continue.getLabel().setFontScale(1.5f * k);
        Continue.setBounds(300f * w, 490f * k, 680f * w, 110f * k);
        table.addActor(Continue);
        TextButton newGame = new TextButton("New Game", skin,"menu");
        newGame.setBounds(300f * w,370f * k,680f * w,110f * k);
        newGame.getLabel().setFontScale(1.5f * k);
        TextButton preferences = new TextButton("Preferences", skin,"menu");
        preferences.getLabel().setFontScale(1.5f  * k);
        final TextButton exit = new TextButton("Exit", skin,"menu");
        exit.getLabel().setFontScale(1.5f * k);
        exit.setBounds(300f * w,130f * k,680f * w,110f * k);
        table.addActor(exit);
        preferences.setBounds(300f * w,250f * k,680f * w,110f * k);
        table.addActor(preferences);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pressed = true;
                ExitDialog exitDia = new ExitDialog("Confirm exit", skint);
                exitDia.show(stage);
                exitDia.setPosition(400f * w, 100f * k);
//                exitDia.setBounds(300, 100, 600, 200);
            }
        });
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (prefs.getBoolean(PREFERENCES_CONTINUE)){
                    Minus delete = new Minus(" ", skint);
                    delete.show(stage);
                    delete.setPosition(400f * w, 300f * k);
                }
                else{
                    parent.getPreferencesLoad().clear();
                    parent.getPreferencesLoad().flush();
                    setC(false);
                    pressedTime = 0;
                    parent.changeScreen(MyGdxGame.CHOICEPLAYER);
                }
            }
        });
        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.setPref(false);
                pressedTime = 0;
                parent.changeScreen(MyGdxGame.PREFERENCES);
            }
        });
        ClickListener continueListener = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y) {
                continueManager.loadSave();
                pressedTime = 0;
                parent.changeScreen(MyGdxGame.GAMEPLAY);
            }
        };
        stage.addActor(table);
        if (!prefs.getBoolean(PREFERENCES_CONTINUE)){
            Continue.setVisible(false);
        }
        else{
            Continue.setVisible(true);
            Continue.addListener(continueListener);
        }
        table.addActor(newGame);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        pressedTime += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && !pressed && pressedTime >= 0.4f) {
            pressed = true;
            ExitDialog exitDia = new ExitDialog("confirm exit", skint);
            exitDia.show(stage);
            exitDia.setPosition(400f * w, 100f * k);
        }
        batch.setProjectionMatrix(cam.combined);
        update();
        batch.begin();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.end();
    }
    public void setC (boolean check){
        prefs.putBoolean(PREFERENCES_CONTINUE, check);
        prefs.flush();
    }
   /* public float getC ()
    {
        return prefs.getFloat(PREFERENCES_CONTINUE);
    }*/

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
    public MyGdxGame getParent(){
        return parent;
    }
    public void update(){//обновление кадров
        if (frame > 0 && timeFrame >= fastTime){
            timeFrame = 0.0f;
            if (frame == backGround.size() - 1){
                frame = 0;
            }
            else{
                frame++;
            }
            backGroundI.setDrawable(new TextureRegionDrawable(backGround.get(frame)));
        }
        else if (timeFrame >= maxTimeStopFrame){
            timeFrame = 0.0f;
            if (frame == backGround.size() - 1){
                frame = 0;
            }
            else{
                frame++;
            }
            backGroundI.setDrawable(new TextureRegionDrawable(backGround.get(frame)));
        }
        else{
            timeFrame += Gdx.graphics.getDeltaTime();
        }
    }
//    public ContinueManager getContinue(){
//        return continueManager;
//    }
    @Override
    public void dispose() {
        stage.dispose();
    }
    public static class Minus extends Dialog {
        private float k = MyGdxGame.k;
        private float w = MyGdxGame.w;
        private MyGdxGame parent = MenuScreen.parent;
        public Minus(String title, Skin skin) {
            super(title, skin);
        }

        public Minus(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }

        public Minus(String title, WindowStyle windowStyle) {
            super(title, windowStyle);
        }
        {
            text ("Saving will be destroyed?").setScale(2.2f * w, 2.2f * k);
            button("Yes", "Yes").setScale(2.2f * w, 2.2f * k);
            button("No", "No").setScale(2.2f * w, 2.2f * k);
        }
        @Override
        protected void result(Object object) {
            if ("Yes".equals(object.toString())){
                parent.setC(false);
                parent.getPreferencesLoad().clear();
                parent.getPreferencesLoad().flush();
                parent.changeScreen(MyGdxGame.CHOICEPLAYER);
            }
            else{
                MenuScreen.pressed = false;
            }
        }
    }

    public static class ExitDialog extends Dialog {
        private float k = MyGdxGame.k;
        private float w = MyGdxGame.w;
        private MyGdxGame parent = MenuScreen.parent;
        public ExitDialog(String title, Skin skin) {
            super(title, skin);
        }

        public ExitDialog(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }

        public ExitDialog(String title, WindowStyle windowStyle) {
            super(title, windowStyle);
        }
        {
            text ("Do you really want to leave?").setScale(2.2f * w, 2.2f * k);
            button("Yes", "Yes").setScale(2.2f * w, 2.2f * k);
            button("No", "No").setScale(2.2f * w, 2.2f * k);
        }
        @Override
        protected void result(Object object) {
            if ("Yes".equals(object.toString())){
                parent.disposeLoad();
                Gdx.app.exit();
            }
            else{
                MenuScreen.pressed = false;
            }
        }
    }
}
