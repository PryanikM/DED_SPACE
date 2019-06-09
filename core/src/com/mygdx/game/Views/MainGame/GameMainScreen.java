package com.mygdx.game.Views.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Managers.MakePlayersManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Loader.A2DAssetManager;
import com.mygdx.game.Managers.GameMainManager;
import com.mygdx.game.Model.Players;
import com.mygdx.game.Model.Robot;
import com.mygdx.game.Model.Tombstone;

import java.util.ArrayList;

public class GameMainScreen implements Screen, InputProcessor {
    private World world;
    private SpriteBatch batch;
    private ArrayList<Players> players;//массив игроков
    public boolean checkActiv;
    private TiledMap tiledMap;
    private Box2DDebugRenderer b2dr;
    private MakePlayersManager information;
    private Sprite rocketBroken;//рокета в середине карты
    private Texture rocket;
    public MyGdxGame parent;
    protected GameMainManager gameManager;
    private OrthographicCamera cam;
    private float pressedTime;//время, которое работает активность
    private HudScreen hudScreen;
//    private NextDayManager NextDayManager;
    private IsometricTiledMapRenderer tmr;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private TiledMapTileLayer layer;
    private String whatBuildingPressed;//какое здание было выбрано
    private Robot robot;
    public GameMainScreen(MyGdxGame myGdxGame, int food, int water, int day, Boolean load, int rock, SpriteBatch batch) {
        parent = myGdxGame;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        gameManager = new GameMainManager(this, food, water, day, rock);
        this.batch = batch;
        tiledMap = new TmxMapLoader().load("Map/map(1).tmx");
        checkActiv = true;
//        tiledMap = new TmxMapLoader().load("Map/map1..tmx");
        layer = (TiledMapTileLayer) tiledMap.getLayers().get("Tile Layer 1");
        if (!load) {
            information = new MakePlayersManager(parent.getChoiceScreenPlayers().getArray(), k, (TiledMapTileLayer) tiledMap.getLayers().get("Tile Layer 1"), this);
            setPlayers();
//            savefirst();
            robot = new Robot(k, layer, 1, false, parent);
        }
        else{
            players = new ArrayList<Players>();
            robot = new Robot(k, layer, 1, true, parent);
        }
//        layer = (TiledMapTileLayer) tiledMap.getLayers().get("Слой тайлов 1");
        tmr = new IsometricTiledMapRenderer(tiledMap, 1.2f * k);
        b2dr = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1280 * w, 720 * k);
        cam.position.set(25 * layer.getTileWidth() * w , (layer.getHeight() - 50) * layer.getTileHeight() * k,  0f);
        hudScreen = new HudScreen(batch, parent, gameManager);
//        NextDayManager = new NextDayManager(this, gameManager, parent);
        rocket = A2DAssetManager.manager.get(A2DAssetManager.rocketBroken);
        rocketBroken = new Sprite(rocket);
        rocketBroken.setPosition(15 * (layer.getTileWidth() * 1.2f)  * w,-18 * layer.getTileHeight() * k);
        rocketBroken.setSize(12 * (layer.getTileWidth() * 1.2f)  * w,50 * layer.getTileHeight() * k);
        pressedTime = 0;
        if (!load){
            gameManager.savePlayer();
            gameManager.saveBuildings();
            gameManager.savePreferences();
            gameManager.saveInv();
            gameManager.saveTombstone();
            parent.getPreferencesLoad().flush();
        }
    }
    /*public void loadPlayers(){
        players = new ArrayList<Players>(gameManager);
    }*/
    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {//для передвежения камеры по игровому миру
        float x = Gdx.input.getDeltaX();
        float y = Gdx.input.getDeltaY();
        cam.translate(-x,y);
        return true;
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
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {//куда произошло нажатие
        Vector3 v = new Vector3(screenX, screenY, 0);
        v = cam.unproject(v);
        if (v.x > rocketBroken.getX() + 388f * w && v.x <= rocketBroken.getX() + rocketBroken.getWidth() && v.y >= rocketBroken.getY()  + 661f * k && v.y <= rocketBroken.getY() + 720f * k && checkActiv){
            if (!parent.getOutingNotBusyScreen().getManager().getOuting()) {
                pressedTime = 0;
                parent.changeScreen(MyGdxGame.OUTINGF);
            }
            else {
                pressedTime = 0;
                parent.changeScreen(MyGdxGame.OUTINGT);
            }
        }
        for (int i = 0;  i < gameManager.getBuildings().size(); i++){
            if (v.x > gameManager.getBuildings().get(i).getX() + 80f * w && v.x < gameManager.getBuildings().get(i).getX() - 80f * w + gameManager.getBuildings().get(i).getWdith() && v.y >= gameManager.getBuildings().get(i).getY() + 80f * k && v.y <= gameManager.getBuildings().get(i).getY() + gameManager.getBuildings().get(i).getHeight() - 130f * k && checkActiv && gameManager.getBuildings().get(i).getHealth() > 0 && !gameManager.getBuildings().get(i).getRepair()){
                whatBuildingPressed =  gameManager.getBuildings().get(i).getResourse();
                if (!gameManager.getBuildings().get(i).getIsBusy()) {
                    pressedTime = 0;
                    parent.changeScreen(MyGdxGame.BUILDINGMAKE);
                }
                else{
                    pressedTime = 0;
                    parent.changeScreen(MyGdxGame.BUILDINGMAKEYES);
                }
            }
        }
        if (v.x > robot.getX() && v.x < (robot.getX() + robot.getWidth()) && v.y > robot.getY() && v.y < (robot.getY() + robot.getHeight()) && robot.getIsDraw()) {
            pressedTime = 0;
            parent.changeScreen(MyGdxGame.ROBOTSCREEN);
        }
        if (screenX > 180 * w && screenX < 280 * w && screenY > 5 * k && screenY < 100 * k && checkActiv){
            pressedTime = 0;
            //parent.changeScreen(MyGdxGame.PREFERENCES);
            parent.changeScreen(MyGdxGame.BUILDING);
//            players.get(0).setPlusDayWithoutFood(1);
//            players.get(0).setPlusDayWithoutWater(1);
//            parent.changeScreen(MyGdxGame.CHOICEPLAYER);
        }
        if (screenX > 40 * w && screenX < 140 * w && screenY > 5 * k && screenY < 100 * k && checkActiv){
            pressedTime = 0;
            parent.changeScreen(MyGdxGame.STATE);
        }
        if (screenX > hudScreen.nextDay.getX() && screenX < hudScreen.nextDay.getX() + 200 * w &&  screenY < 720 * k - hudScreen.nextDay.getY() && screenY > 720 * k - hudScreen.nextDay.getY() - 70 * k && checkActiv){
            pressedTime = 0;
//
//            NextDayManager.minusDayPlayers();
            checkActiv = false;
            parent.createNextDaySc();
            parent.changeScreen(MyGdxGame.NEXTDAYSCREEN);
//            hudScreen.updateCountDay();
            parent.setMenu(true);

        }
        if (screenX > hudScreen.pause.getX() && screenX < hudScreen.pause.getX() + 100f * w && screenY < 720 * k - hudScreen.pause.getY() && screenY > 720 * k - hudScreen.pause.getY() - 100 * k & checkActiv){
            pressedTime = 0;
            parent.changeScreen(MyGdxGame.GAMEMENU);
        }
        if (screenX > 320 * w && screenX < 420 * w && screenY > 5 * k && screenY < 100 * k && gameManager.getBuildings().size() > 0 && checkActiv){
            pressedTime = 0;
            parent.changeScreen(MyGdxGame.REPAIR);

        }
        if (screenX > 40 * w && screenX < 140 * w && screenY > 155 * k && screenY < 250 * k && checkActiv){
            pressedTime = 0;
            parent.changeScreen(MyGdxGame.INVSCREEN);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public ArrayList<Players> getPlayers(){
        return players;
    }
    public GameMainManager getMainManager(){
        return gameManager;
    }
    public void updateFood(){//обнавляет hud еды
        hudScreen.updateFoodLabel();
    }
    public void updateWater(){//обноваляет hud воды
        hudScreen.updateWaterLabel();
    }
    public void updateRock(){//обновляет hud камня
        hudScreen.updateRockLabel();
    }
    public void minusPlayer(int ind){//удаление игроков
        gameManager.addTombstone(new Tombstone(players.get(ind).getX(), players.get(ind).getY(), 3f * layer.getTileHeight() * k * 1.2f,1.5f * layer.getTileWidth() * w * 1.2f, players.get(ind).getName()));
        players.remove(ind);
    }
    public void check(){//проверка на наличие игроков
        if (players.size() == 0){
            parent.changeScreen(MyGdxGame.ENDGAME);
        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        pressedTime += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) && pressedTime >= 0.4f) {
            pressedTime = 0;
            parent.changeScreen(MyGdxGame.GAMEMENU);
        }
        tmr.setView(cam);
//        parent.getBuildingBuildScreen().getManager().
        batch.setProjectionMatrix(cam.combined);
        tmr.render();
        b2dr.render(world, cam.combined);
        batch.begin();
        if (parent.getBuildingBuildScreen().getManager().getFoodFactory() != 0 && parent.getBuildingBuildScreen().getManager().getFoodFactory() != 3){
            parent.getBuildingBuildScreen().getManager().getSpriteFood().draw(batch);
        }
        if (parent.getBuildingBuildScreen().getManager().getWaterFactory() != 0 && parent.getBuildingBuildScreen().getManager().getWaterFactory() != 3){
            parent.getBuildingBuildScreen().getManager().getSpriteWater().draw(batch);
        }
        if (parent.getBuildingBuildScreen().getManager().getRockFactory() != 0 && parent.getBuildingBuildScreen().getManager().getRockFactory() != 3){
            parent.getBuildingBuildScreen().getManager().getSpriteRock().draw(batch);
        }
        if (robot.getIsDraw()){
            robot.getSprite().draw(batch);
        }
        rocketBroken.draw(batch);
        for (int i = 0; i < gameManager.getBuildings().size(); i++){
            Sprite temp;
            temp = gameManager.getBuildings().get(i).playAnimation(Gdx.graphics.getDeltaTime());
            temp.setPosition(gameManager.getBuildings().get(i).getX(), gameManager.getBuildings().get(i).getY());
            temp.draw(batch);
        }
        for (Players player : players){
            if (player.getRepair()){
                player.repairAnimation(delta).draw(batch);
            }
            else {
                player.animation(delta).draw(batch);
            }
//            player.repairAnimation(delta).draw(batch);
        }
        for (int i = 0 ; i < gameManager.getTombstones().size(); i++ ){
            gameManager.getTombstones().get(i).getSprite().draw(batch);
        }
        batch.end();
        batch.setProjectionMatrix(hudScreen.stage.getCamera().combined);
        hudScreen.stage.draw();

    }
    private void setPlayers(){//создание массива игроков
        players = new ArrayList<Players>(information.makePlayers());
    }
    public void update(float dt){
        world.step(dt, 6, 2);
    }
    public TiledMapTileLayer getLayer(){
        return layer;
    }
    public void updateBuildingCheck(){//обнрвление hud
        hudScreen.updateBuildingCheck();
    }
    public String getWhatBuildingPressed() {
        return whatBuildingPressed;
    }
    public SpriteBatch getBatch(){
        return batch;
    }
    public void setCheckActiv(){
        checkActiv = true;
    }
    public HudScreen getHud(){
        return hudScreen;
    }
    public Robot getRobot(){
        return robot;
    }
    public void saveFirst(){//промежуточное сохранение
        gameManager.savePlayer();
        gameManager.saveTombstone();
        gameManager.saveBuildings();
        gameManager.savePreferences();
        parent.getBuildingBuildScreen().getManager().savePreferences();
        gameManager.savePreferences();
        parent.getPreferencesLoad().flush();
        robot.save();
        parent.getBuildingsMakeNoScreen().getManager().savePreferences();
        parent.getRepairScreen().getManager().savePref();
        parent.getOutingNotBusyScreen().getManager().save();
        gameManager.saveInv();
        gameManager.saveThings();
    }

}

