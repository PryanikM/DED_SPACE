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
import com.mygdx.game.Managers.BuildingRepairManager;

import java.util.ArrayList;

public class BuildingRepairScreen implements Screen {
    private Stage stage;
    private Table playerTable;
    private Viewport viewport;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private MyGdxGame parent;
//    private Image waterFactory;
//    private Image foodFactory;
//    private Image rockFactory;
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private BuildingRepairManager manager;
    private GameMainScreen main;
    private Skin skin;
    private ArrayList<Image> buttons;
    private TextButton repair;
    private ArrayList<Label> counts;//хранятся label, которые нужны для показа количества ресурсов
    private ArrayList<Image> images;
    private ArrayList<TextButton> repairB;
    private Label countDay;
    private ArrayList<Label> checkDay;//кол-во дней
    private Image foodI;//всего воды
    private Image waterI;//всего еды
    private Image rockI;//всего камня
    private Skin skint;//для высплывающего окна
    private Label foodLabelS;
    private Label waterLabelS;
    private Label rockLabelS;
    public BuildingRepairScreen(MyGdxGame parent, GameMainScreen main, SpriteBatch batch) {
        this.parent = parent;
        this.main = main;
        k = MyGdxGame.k;
        w = MyGdxGame.w;
        this.batch = batch;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1280 * w, 720 * k, cam);
        cam.setToOrtho(false, 1280 * w, 720 * k);
        stage = new Stage(viewport);
//        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        skin = new Skin(Gdx.files.internal("skin/Flat_Earth_UI_Skin//flatearthui/flat-earth-ui.json"));
        buttons = new ArrayList<Image>();
        counts = new ArrayList<Label>();
        images = new ArrayList<Image>();
        repairB = new ArrayList<TextButton>();
        checkDay = new ArrayList<Label>();
        manager = new BuildingRepairManager(main.getPlayers().size(), this, k, parent);
        countDay = new Label("", skin, "title");
        countDay.setFontScale(1.2f);
        foodI = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
        waterI = new Image (A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
        rockI = new Image(A2DAssetManager.manager.get(A2DAssetManager.rock, Texture.class));
        skint = new Skin(Gdx.files.internal("skin/Clean_Crispy_UI_Skin//cleancrispyui/clean-crispy-ui.json"));
    }

    @Override
    public void show() {
        this.stage.clear();
        Gdx.input.setInputProcessor(stage);
        playerTable = new Table();
        buttons.clear();
        counts.clear();
        images.clear();
        repairB.clear();
        checkDay.clear();
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
                        buttons.get(size_pl / 2 + 1).setBounds(820  * k, 565 * w, 93 * w, 150 * k);
                        playerTable.addActor(buttons.get(size_pl / 2 + 1));
                    }
                }
            }
        }
        for (int i = 0; i < main.getMainManager().getBuildings().size(); i++){
            Image temp;
            if (main.getMainManager().getBuildings().get(i).getHealth() == 0){
                temp = new Image(A2DAssetManager.manager.get("Image/BrokenBuildingScreen/" + main.getMainManager().getBuildings().get(i).getResourse() + "BrokenS.png", Texture.class));
            }
            else{
                temp = new Image(new Texture(Gdx.files.internal(main.getMainManager().getBuildings().get(i).getLink())));
            }
            temp.setBounds(10 * w, (410 - (195 * i)) * k, 140 * w, 170 * k);
            playerTable.addActor(temp);
            for (int g = 0; g < main.getMainManager().getBuildings().get(i).getHealth(); g++){
                Image heartImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.heart, Texture.class));
                heartImage.setBounds((250 + (g * 55)) * w, (470 - (i * 195)) * k, 50 * w, 50 * k);
                playerTable.addActor(heartImage);

            }
            for (int g = main.getMainManager().getBuildings().get(i).getMax_health(); g > main.getMainManager().getBuildings().get(i).getHealth(); g--){
                Image heartImageF = new Image(A2DAssetManager.manager.get(A2DAssetManager.heartF, Texture.class));
                heartImageF.setBounds((250 + ((g - 1) * 55)) * w, (470 - (i * 195)) * k, 50 * w, 50 * k);
                playerTable.addActor(heartImageF
                );
            }
            repair = new TextButton("Repair", skin);
            repair.getLabel().setFontScale(1.0f * k);
            repair.getLabel().setColor(Color.GRAY);
            repair.setColor(Color.GRAY);
            repair.getListeners().clear();
            repair.setBounds(520 * w, (455 - (i * 195)) * k, 200 * w , 80 * k);
            if (main.getMainManager().getBuildings().get(i).getHealth() != main.getMainManager().getBuildings().get(i).getMax_health() && !main.getMainManager().getBuildings().get(i).getIsBusy()){
                repair.getLabel().setColor(Color.WHITE);
                repair.setColor(Color.WHITE);
                final int finalI = i;
                repair.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent in, float x, float y) {
                        int g = 6;
                        if (finalI == 1){
                            g = 3;
                        }
                        else if (finalI == 0){
                            g = 0;
                        }
                        if (main.getMainManager().getFood() - Integer.valueOf(String.valueOf(counts.get(g).getText())) >= 0 && main.getMainManager().getWater() - Integer.valueOf(String.valueOf(counts.get(g + 1).getText())) >= 0) {
                            repairB.get(finalI).clearListeners();
                            main.getMainManager().setMinusFood(Integer.valueOf(String.valueOf(counts.get(g).getText())));
                            main.getMainManager().setMinusWater(Integer.valueOf(String.valueOf(counts.get(g + 1).getText())));
                            main.getMainManager().setMinusRock(Integer.valueOf(String.valueOf(counts.get(g + 2).getText())));
                            manager.setDay(main.getMainManager().getBuildings().get(finalI).getResourse(), Integer.valueOf(String.valueOf(counts.get(g + 2).getText())));
                            manager.repair(main.gameManager.getBuildings().get(finalI).getX(), main.gameManager.getBuildings().get(finalI).getY(), main.getMainManager().getBuildings().get(finalI).getResourse());
                            main.getMainManager().getBuildings().get(finalI).setRepair(true);
                            main.getMainManager().getBuildings().get(finalI).setBusy(true);
                            updateButtonRepair();
                            updateLabel();

                        }
                        else{
                            NoResource noResource = new NoResource("", skint);
                            noResource.show(stage);
                            noResource.setPosition(500 * w, 200 * k);
                        }

                    }
                });
            }
            repair.setVisible(false);
            repairB.add(repair);
            playerTable.addActor(repair);
            Image foodImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.food, Texture.class));
            foodImage.setBounds(760 * w, (455 - (i * 195)) * k, 80 * w, 80 * k);
            Label foodLabel = new Label("", skin, "title");
            foodLabel.setScale(0.7f * k);
            foodLabel.setPosition(850 * w,(455 - (i * 195)) * k);
            Image waterImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.water, Texture.class));
            waterImage.setBounds(955 * w, (455 - (i * 195)) * k, 70 * w , 80 * k);
            Label waterLabel = new Label("", skin, "title");
            waterLabel.setScale(0.7f * k);
            waterLabel.setPosition(1040 * w, (455 - (i * 195)) * k);
            Image timeImage = new Image(A2DAssetManager.manager.get(A2DAssetManager.clock, Texture.class));
            timeImage.setBounds(1140 * w, (455 - (i * 195)) * k, 80 * w, 80 * k);
            Label timeLabel = new Label("", skin, "title");
            timeLabel.setScale(0.7f * k);
            timeLabel.setPosition(1230 * w, (455 - (i * 195)) * k);
            Label checkDays = new Label("0", skin, "title");
            checkDays.setFontScale(1.2f * k);
            checkDays.setPosition(800 * w, (470 - (i * 195)) * k);
            foodImage.setVisible(false);
            waterImage.setVisible(false);
            foodImage.setVisible(false);
            foodLabel.setVisible(false);
            waterLabel.setVisible(false);
            timeImage.setVisible(false);
            if (main.getMainManager().getBuildings().get(i).getRepair()){
                checkDays.setText("Days left: " +  manager.getTimeWork(main.getMainManager().getBuildings().get(i).getResourse()));

            }
            else{
                checkDays.setVisible(false);
            }
            checkDay.add(checkDays);
            counts.add(foodLabel);
            counts.add(waterLabel);
            counts.add(timeLabel);
            images.add(foodImage);
            images.add(waterImage);
            images.add(timeImage);
            playerTable.addActor(checkDays);
            playerTable.addActor(foodImage);
            playerTable.addActor(foodLabel);
            playerTable.addActor(waterImage);
            playerTable.addActor(waterLabel);
            playerTable.addActor(timeImage);
            playerTable.addActor(timeLabel);
        }
        foodI.setBounds(5 * w, 665 * k, 50 * w, 50 * k);
        waterI.setBounds(5 * w, 595 * k, 50 * w, 50 * k);
        rockI.setBounds(140 * w, 620 * k , 50 * w, 55 * k );
        waterLabelS = new Label(String.valueOf(main.getMainManager().getWater()), skin, "title");
        waterLabelS.setFontScale(1.2f * k);
        waterLabelS.setPosition(70 * w, 595 * k);
        rockLabelS = new Label(String.valueOf(main.getMainManager().getRock()), skin, "title");
        rockLabelS.setFontScale(1.2f * k);
        rockLabelS.setPosition(200 * w, 635 * k);
        foodLabelS = new Label(String.valueOf(main.getMainManager().getFood()), skin, "title");
        foodLabelS.setFontScale(1.2f * k);
        foodLabelS.setPosition(70 * w, 665 * k);
        playerTable.addActor(foodI);
        playerTable.addActor(waterI);
        playerTable.addActor(rockI);
        playerTable.addActor(foodLabelS);
        playerTable.addActor(rockLabelS);
        playerTable.addActor(waterLabelS);
        stage.addActor(playerTable);

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
    public void update(boolean check, int ind){//обновление кнопок
        if(check){
            buttons.get(ind).setDrawable(new TextureRegionDrawable(new TextureRegion(A2DAssetManager.manager.get("Image/players/" + main.getPlayers().get(ind).getLinks() + "Work.png", Texture.class))));
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
    public void setResVisibleTrue(int sum){//делает labels видимыми
        for (int buildPos = 0; buildPos < main.getMainManager().getBuildings().size(); buildPos++) {
            if (main.getMainManager().getBuildings().get(buildPos).getRepair()) {
                checkDay.get(buildPos).setText("Days left: " + manager.getTimeWork(main.getMainManager().getBuildings().get(buildPos).getResourse()));
                checkDay.get(buildPos).setVisible(true);

            }
            else {
                int k = 6;
                if (buildPos == 0) {
                    k = 0;
                } else if (buildPos == 1) {
                    k = 3;
                }
                counts.get(k).setText(manager.getFood(main.getPlayers().get(sum).getEngineeringSkills(), main.getMainManager().getBuildings().get(buildPos).getResourse()));
                counts.get(k + 1).setText(manager.getWater(main.getPlayers().get(sum).getEngineeringSkills(), main.getMainManager().getBuildings().get(buildPos).getResourse()));
                counts.get(k + 2).setText(manager.getWorkTime(main.getPlayers().get(sum).getEngineeringSkills(), main.getMainManager().getBuildings().get(buildPos).getResourse()));
                for (int i = k; i < k + 3; i++) {
                    counts.get(i).setVisible(true);
                    images.get(i).setVisible(true);
                }
            }
        }
        for (int i = 0; i < repairB.size(); i++){
            repairB.get(i).setVisible(true);
        }
    }
    public void setResVisibleFalse(){//делает labels, показывающие кол-во ресурсов видимыми
        for (int i = 0; i < counts.size(); i++){
            counts.get(i).setVisible(false);
            images.get(i).setVisible(false);
        }
        for (int i = 0; i < repairB.size(); i++){
            repairB.get(i).setVisible(false);
        }
    }
    public GameMainScreen getMain(){
        return main;
    }
    private void updateButtonRepair(){//обновляте кнопки
        for (int i = 0; i < main.getMainManager().getBuildings().size(); i++){
            if (!main.getMainManager().getBuildings().get(i).getIsBusy() && main.getMainManager().getBuildings().get(i).getHealth() != main.getMainManager().getBuildings().get(i).getMax_health()){
                repairB.get(i).getLabel().setColor(Color.WHITE);
                repairB.get(i).setColor(Color.WHITE);
            }
            else{
                repairB.get(i).getLabel().setColor(Color.GRAY);
                repairB.get(i).setColor(Color.GRAY);
                int k = 6;
                if (i == 0){
                    k = 0;
                }
                else if (i == 1){
                    k = 3;
                }
                counts.get(k).setVisible(false);
                counts.get(k + 1).setVisible(false);
                counts.get(k + 2).setVisible(false);
                images.get(k).setVisible(false);
                images.get(k + 1).setVisible(false);
                images.get(k + 2).setVisible(false);
            }
            if (main.getMainManager().getBuildings().get(i).getRepair()){
                checkDay.get(i).setText("Days left: " +  manager.getTimeWork(main.getMainManager().getBuildings().get(i).getResourse()));
                checkDay.get(i).setVisible(true);
            }
        }
    }
    public BuildingRepairManager getManager(){
        return manager;
    }
    private void updateLabel(){//обнволяет общее кол-во ресурсов
        foodLabelS.setText(String.valueOf(main.getMainManager().getFood()));
        waterLabelS.setText(String.valueOf(main.getMainManager().getWater()));
        rockLabelS.setText(String.valueOf(main.getMainManager().getRock()));
    }
    public static class NoResource extends Dialog {//вложенный класс, для отображения высплывающего окна
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
