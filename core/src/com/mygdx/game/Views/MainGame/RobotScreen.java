package com.mygdx.game.Views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class RobotScreen implements Screen {
    private OrthographicCamera cam;
    private Table table;
    private Stage stage;
    private Viewport viewport;
    private float k;
    private float w;
    private Skin skin;
    private Label text;
    private Image what;
    private Label count;
    private TextButton backButton;
    private MyGdxGame parent;
    private SpriteBatch batch;
    private TextButton give;
    private ClickListener giveButton;
    private Skin skint;

    public RobotScreen(MyGdxGame parent, SpriteBatch batch) {
        this.parent = parent;
        table = new Table();
        k = MyGdxGame.k;//константа для экрана (высота)
        w = MyGdxGame.w;//константа для экрана (ширина)
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        text = new Label("               Hi people!" + "\n" + "Today, if you have, I would" + "\n" + "like to ask you:", skin , "title");
        text.setFontScale(1.2f * k);
        text.setPosition(200 * w, 400 * k);
        backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1f * k);
        backButton.setBounds(1020 * w, 615 * k, 250 * w, 100 * k);
        count = new Label("0", skin, "title");
        count.setFontScale(1.2f * k);
        count.setPosition(720 * w, 270 * k);
        what = new Image();
        give = new TextButton("Give", skin);
        give.getLabel().setFontScale(1.5f * k);
        give.setBounds(540 * w, 50 * k, 200 * w, 100 * k);
        count.setVisible(false);
        skint = new Skin(Gdx.files.internal("skin/Clean_Crispy_UI_Skin//cleancrispyui/clean-crispy-ui.json"));
        this.batch = batch;
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        table.addActor(text);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.GAMEPLAY);
            }
        });
        if (parent.getGameScreen().getRobot().getWhat() == 0 || parent.getGameScreen().getRobot().getWhat() == 1 || parent.getGameScreen().getRobot().getWhat() == 2){
            count.setVisible(true);
            count.setText(parent.getGameScreen().getRobot().getRandom());
            if (parent.getGameScreen().getRobot().getWhat() == 0){
                what.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class)));
            }
            else if (parent.getGameScreen().getRobot().getWhat() == 1){
                what.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class)));
            }
            else if (parent.getGameScreen().getRobot().getWhat() == 2){
                what.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class)));
            }
        }
        else{
            if (parent.getGameScreen().getRobot().getWhat() == 3){
                what.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.microboard, Texture.class)));
                count.setVisible(false);
            }
            else if (parent.getGameScreen().getRobot().getWhat() == 7){
                what.setDrawable(new TextureRegionDrawable(A2DAssetManager.manager.get(A2DAssetManager.hammer, Texture.class)));
                count.setVisible(false);
            }
        }
        giveButton = new ClickListener(){
            @Override
            public void clicked(InputEvent in, float x, float y) {
                int pos;
                if (parent.getGameScreen().getMainManager().getThings().indexOf(parent.getGameScreen().getRobot().getWhat()) != -1){
                    pos = parent.getGameScreen().getMainManager().getThings().indexOf(parent.getGameScreen().getRobot().getWhat());
                    parent.getGameScreen().getMainManager().getThings().remove(pos);
                    pos = parent.getGameScreen().getRobot().getThings().indexOf(parent.getGameScreen().getRobot().getWhat());
                    parent.getGameScreen().getRobot().getThings().remove(pos);
                    parent.getGameScreen().getRobot().setDayStopNow(0);
                    parent.getGameScreen().getRobot().setIsDraw(false);
                    parent.getGameScreen().getRobot().setWhatNull();
                    parent.getGameScreen().getRobot().setNullRandomC();
//                    parent.getGameScreen().getRobot().save();
                    if (parent.getGameScreen().getRobot().getThings().size() == 0){
                        parent.getGameScreen().getRobot().setGivePresent(true);
                        parent.getGameScreen().getRobot().setIsDraw(false);
                    }
                    parent.changeScreen(MyGdxGame.GAMEPLAY);
                }
                else if (parent.getGameScreen().getRobot().getWhat() == 0
                        &&
                        (parent.getGameScreen().getMainManager().getFood() - Integer.valueOf(String.valueOf(count.getText())) >= 0)){
                    parent.getGameScreen().getMainManager().setMinusFood(Integer.valueOf(String.valueOf(count.getText())));
                    parent.getGameScreen().updateFood();
                    parent.getGameScreen().getRobot().setDayStopNow(0);
                    parent.getGameScreen().getRobot().setIsDraw(false);
                    parent.getGameScreen().getRobot().setWhatNull();
                    parent.getGameScreen().getRobot().setNullRandomC();
                    pos = parent.getGameScreen().getRobot().getThings().indexOf(0);
//                    parent.getGameScreen().getRobot().save();
                    parent.getGameScreen().getRobot().getThings().remove(pos);
                    if (parent.getGameScreen().getRobot().getThings().size() == 0){
                        parent.getGameScreen().getRobot().setGivePresent(true);
                        parent.getGameScreen().getRobot().setIsDraw(false);
                    }
                    parent.changeScreen(MyGdxGame.GAMEPLAY);
                }
                else if (parent.getGameScreen().getRobot().getWhat() == 1 && (parent.getGameScreen().getMainManager().getWater() - Integer.valueOf(String.valueOf(count.getText())) >= 0)){
                    parent.getGameScreen().getMainManager().setMinusWater(Integer.valueOf(String.valueOf(count.getText())));
                    parent.getGameScreen().updateWater();
                    parent.getGameScreen().getRobot().setDayStopNow(0);
                    parent.getGameScreen().getRobot().setIsDraw(false);
                    parent.getGameScreen().getRobot().setWhatNull();
                    parent.getGameScreen().getRobot().setNullRandomC();
                    pos = parent.getGameScreen().getRobot().getThings().indexOf(1);
                    parent.getGameScreen().getRobot().getThings().remove(pos);
                    if (parent.getGameScreen().getRobot().getThings().size() == 0){
                        parent.getGameScreen().getRobot().setIsDraw(false);
                        parent.getGameScreen().getRobot().setGivePresent(true);
                    }
//                    parent.getGameScreen().getRobot().save();
                    parent.changeScreen(MyGdxGame.GAMEPLAY);
                }
                else if (parent.getGameScreen().getRobot().getWhat() == 2
                        &&
                        (parent.getGameScreen().getMainManager().getRock() - Integer.valueOf(String.valueOf(count.getText())) >= 0))
                {
                    parent.getGameScreen().getMainManager().setMinusRock(Integer.valueOf(String.valueOf(count.getText())));
                    parent.getGameScreen().updateRock();
                    parent.getGameScreen().getRobot().setDayStopNow(0);
                    parent.getGameScreen().getRobot().setIsDraw(false);
                    parent.getGameScreen().getRobot().setWhatNull();
                    parent.getGameScreen().getRobot().setNullRandomC();
                    pos = parent.getGameScreen().getRobot().getThings().indexOf(2);
                    parent.getGameScreen().getRobot().getThings().remove(pos);
//                    parent.getGameScreen().getRobot().save();
                    if (parent.getGameScreen().getRobot().getThings().size() == 0){
                        parent.getGameScreen().getRobot().setIsDraw(false);
                        parent.getGameScreen().getRobot().setGivePresent(true);
                    }
                    parent.changeScreen(MyGdxGame.GAMEPLAY);

                }
                else{
                    Have dialog = new Have(" ", skint);
                    dialog.show(stage);
                    dialog.setPosition(350 * w, 200 * k);
                }
            }
        };
        give.addListener(giveButton);
        what.setBounds(590 * w, 250 * k, 100 * w, 100 * k);
        table.addActor(count);
        table.addActor(backButton);
        table.addActor(give);
        table.addActor(text);
        table.addActor(what);
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
    public static class Have extends Dialog {
        float k = MyGdxGame.k;
        public Have(String title, Skin skin) {
            super(title, skin);
        }
        {
            text("You do not have these resources.").setScale(2.2f * k);
            button("Ok").setScale(2.2f * k);
        }
    }
}
