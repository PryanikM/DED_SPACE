package com.mygdx.game.views.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;
import com.mygdx.game.manager.GameMainManager;
import com.mygdx.game.manager.nextDayManager;
import com.sun.org.apache.bcel.internal.generic.FLOAD;

import java.util.ArrayList;

public class NextDayScreen implements Screen{
    private Thread nextDay;//поток для сохранения, во время проигрывания анимации
    private float time;//прошедшее время
    private float maxTime;//время между кадрами
    private float endTime;//максимальное время, требуемое для проигрывания анимации
    private float allTime;//время, которое нужно для того, чтобы проверить, м ли анимация
    private GameMainScreen main;
    private GameMainManager gameManager;
    private MyGdxGame parent;
    private int frame;//текущей кадр
    private ArrayList<Sprite> frames;//массив кадров
    private SpriteBatch batch;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private OrthographicCamera cam;
    public NextDayScreen(GameMainScreen main ,GameMainManager gameManager, MyGdxGame parent, SpriteBatch batch) {
        this.parent = parent;
        this.main = main;
        this.gameManager = gameManager;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        this.batch = batch;
        cam = new OrthographicCamera();
        frames = new ArrayList<Sprite>();
        cam.setToOrtho(false, 1280 * w, 720 * k);
        frame = 0;
        time = 0;
        allTime = 0;
        maxTime = 0.25f;
        endTime = 0.9f;
        for (int i = 0; i < 7; i++){
            Sprite temp = new Sprite(A2DAssetManager.manager.get("Image/newDayAnimation/" + i + ".png", Texture.class));
            temp.setSize(1280 * w, 720 * k);
            temp.setPosition(0, 0);
            frames.add(temp);
        }
        start();
    }

    public void start(){//запуск потока
        nextDay = new Thread(new nextDayManager(main, gameManager, parent));
        nextDay.start();
    }
    private void stopFrame(){//остановка потока
        Gdx.app.log("CHECK1", "TRUE");
        try {
            Gdx.app.log("CHECK1", "TRUE");
            nextDay.join();
            Gdx.app.log("CHECK1", "TRUE");
            main.check();
//            Gdx.app.log("DELETE", "YES");
            //parent.changeScreen(MyGdxGame.GAMEPLAY);
        }
        catch ( InterruptedException e ) {
//            System.out.println("Главный поток прерван");
        }
        Gdx.app.log("CHECK1", "TRUE");
//        Gdx.app.log("CHECK", "TRUE");
    }

    @Override
    public void show() {
//        Gdx.input.setInputProcessor();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        playAnimathion(delta).draw(batch);
        batch.end();
    }
    private Sprite playAnimathion(float delta){//проигрывание анимации
        time += delta;
        Gdx.app.log("CHECK1", "TRUE");
        stopFrame();
        if (time >= maxTime){
            time = 0;
            if (frame == 6){
                frame = 0;
            }
            else {
                frame ++;
            }
        }
        else{
            time += delta;
        }
        allTime += delta;
        if (allTime >= endTime && !nextDay.isAlive()){
            main.setCheckActiv();
            Gdx.app.log("ACTIV", String.valueOf(nextDay.isAlive()));
            parent.changeScreen(MyGdxGame.GAMEPLAY);
        }
        return frames.get(frame);
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
