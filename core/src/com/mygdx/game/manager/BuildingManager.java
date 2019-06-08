package com.mygdx.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.loader.A2DAssetManager;
import com.mygdx.game.views.MainGame.BuildingBuildScreen;
import com.mygdx.game.views.MenuScreen;

public class BuildingManager {

    public final static String PREFERENCES_WHOROCK = "PREFERENCES_WHOROCK";//константа для сохранения
    public final static String PREFERENCES_WHOWATER = "PREFERENCES_WHOWATER";//константа для сохранения
    public final static String PREFERENCES_WHOFOOD = "PREFERENCES_WHOFOOD";//константа для сохранения
    private final static String PREFERENCES_ROCK_IS_BUSY = "PREFERENCES_ROCK_IS_BUSY";//константа для сохранения
    private final static String PREFERENCES_FOOD_IS_BUSY = "PREFERENCES_FOOD_IS_BUSY";//константа для сохранения
    private final static String PREFERENCES_WATER_IS_BUSY = "PREFERENCES_WATER_IS_BUSY";//константа для сохранения
    private final static String PREFERENCES_ROCK_FACTORY = "PREFERENCES_ROCK_FACTORY";//константа для сохранения
    private final static String PREFERENCES_FOOD_FACTORY = "PREFERENCES_FOOD_FACTORY";//константа для сохранения
    private final static String PREFERENCES_WATER_FACTORY = "PREFERENCES_WATER_FACTORY";//константа для сохранения
    private final static String WATER_FACTORY_TIME_BISY = "WATER_FACTORY_TIME_BISY";//константа для сохранения
    private final static String FOOD_FACTORY_TIME_BISY = "FOOD_FACTORY_TIME_BISY";//константа для сохранения
    private final static String ROCK_FACTORY_TIME_BISY = "ROCK_FACTORY_TIME_BISY";//константа для сохранения

    private boolean pressed[];//массив для хранения состояний нажатия персонажей
    private int quantity;
    private int food;//еда, требуемая для постройки
    private int water;//воды, требуемая для постройки
    private int rock;//камни, требуемая для постройки
    private int shovelTime;//время постройки лопаты
    private int gunTime;// время постройки писталета
    private int hammerTime;//время постройки молота
    private int firstAidTime;// время постройки аптечки
    private String whoGun;//кто производит пистолет
    private String whoShovel;//кто производит лопату
    private String whoFirstAid;//кто производит аптечку
    private String whoHammer;//кто производит молот
    private int foodFactory;//этап постройки здания
    private int waterFactory;//этап постройки здания
    private int rockFactory;//этап постройки здания
    private String whoWater;//кто строит здание
    private String whoFood;//кто строит здание
    private String whoRock;//кто строит здание
    private BuildingBuildScreen screen;
    private int rockFactoryWorkDay;// сколько времени осталось строить здание
    private int waterFactoryWorkDay;// сколько времени осталось строить здание
    private int foodFactoryWorkDay;// сколько времени осталось строить здание
    private int isPressed;// кто из персонажей выбран
    private boolean rockIsBusy;// строится ли здание
    private boolean waterIsBusy;// строится ли здание
    private boolean foodIsBusy;// строится ли здание
    private Boolean first;//впервые ли звапущена активность с выбором

    private int waterFacoryTimeBisy;// сколько времени осталось строить здание
    private int rockFacoryTimeBisy;// сколько времени осталось строить здание
    private int foodFacoryTimeBisy;// сколько времени осталось строить здание
    //factoryFood


    //Development
    private int foodFoodFactoryD = 1;//константа, используемые в формулах
    private int waterFoodFactoryD = 1;//константа, используемые в формулах
    private int dayFoodFactoryD = 5;//константа, используемые в формулах
    //Development

    //TRANSFER
    private int foodFoodFactoryT = 2;//константа, используемые в формулах
    private int waterFoodFactoryT = 2;//константа, используемые в формулах
    private int rockFoodFactory = 2;//константа, используемые в формулах
    private int dayFoodFactoryT = 6;//константа, используемые в формулах
    //TRANSFER

    //MAKE
    private int foodFoodFactoryM = 3;//константа, используемые в формулах
    private int waterFoodFactoryM = 3;//константа, используемые в формулах
    private int dayFoodFactoryM = 7;//константа, используемые в формулах

    //MAKE

    //factoryFood

    //factoryWater


    //Development
    private int foodWaterFactoryD = 1;//константа, используемые в формулах
    private int waterWaterFactoryD = 1;//константа, используемые в формулах
    private int resourcesWaterFactoryD = 4;//константа, используемые в формулах
    private int dayWaterFactoryD = 5;//константа, используемые в формулах
    //Development

    //TRANSFER
    private int foodWaterFactoryT = 2;//константа, используемые в формулах
    private int waterWaterFactoryT = 2;//константа, используемые в формулах
    private int rockWaterFactory = 2;//константа, используемые в формулах
    private int dayWaterFactoryT = 6;//константа, используемые в формулах
    //TRANSFER

    //MAKE
    private int foodWaterFactoryM = 3;//константа, используемые в формулах
    private int waterWaterFactoryM = 3;//константа, используемые в формулах
    private int dayWaterFactoryM = 7;//константа, используемые в формулах

    //MAKE

    //factoryWater

    //rockFactory


    //Development
    private int foodRockFactoryD = 1;//константа, используемые в формулах
    private int waterRockFactoryD = 1;//константа, используемые в формулах
    private int resourcesRockFactoryD = 4;//константа, используемые в формулах
    private int dayRockFactoryD = 5;//константа, используемые в формулах
    //Development

    //TRANSFER
    private int foodRockFactoryT = 2;//константа, используемые в формулах
    private int waterRockFactoryT = 2;//константа, используемые в формулах
    private int rockRockFactory = 2;//константа, используемые в формулах
    private int dayRockFactoryT = 6;//константа, используемые в формулах
    //TRANSFER

    //MAKE
    private int foodRockFactoryM = 3;//константа, используемые в формулах
    private int waterRockFactoryM = 3;//константа, используемые в формулах
    private int dayRockFactoryM = 7;//константа, используемые в формулах

    //MAKE
    //rockFactory
    private Sprite dirt1;//food
    private Sprite dirtWithWood1;//food
    private Sprite dirt2;//water
    private Sprite dirtWithWood2;//water
    private Sprite dirt3;//rock
    private Sprite dirtWithWood3;//rock



    //EQUIPMENT
    private int gunWater = 1;//константа, используемые в формулах
    private int gunFood = 1;//константа, используемые в формулах
    private int gunRock = 5;//константа, используемые в формулах
    private int shovelWater = 1;//константа, используемые в формулах
    private int shovelFood = 1;//константа, используемые в формулах
    private int shovelRock = 3;//константа, используемые в формулах
    private int firstAidFood = 2;//константа, используемые в формулах
    private int firstAidWater = 2;//константа, используемые в формулах
    private int hammerFood = 1;//константа, используемые в формулах
    private int hammerWater = 2;//константа, используемые в формулах
    private int hammerRock = 5;//константа, используемые в формулах


    //EQUIPMENT

    public BuildingManager(int col, BuildingBuildScreen screen) {
        quantity = col;
        whoRock = "null";
        whoFood = "null";
        whoWater = "null";
        whoGun = "null";
        whoFirstAid = "null";
        whoGun = "null";
        whoShovel = "null";
        this.screen = screen;
        first = true;
        rockIsBusy = false;
        waterIsBusy = false;
        foodIsBusy = false;
        foodFactory = 0;
        waterFactory = 0;
        rockFactory = 0;
        pressed = new boolean[]{false, false, false, false, false};
        water = 0;
        food = 0;
        dirt1 = new Sprite(A2DAssetManager.manager.get(A2DAssetManager.dirt, Texture.class));
        dirtWithWood1 = new Sprite(A2DAssetManager.manager.get(A2DAssetManager.dirtWithWood, Texture.class));
        dirt2 = new Sprite(A2DAssetManager.manager.get(A2DAssetManager.dirt, Texture.class));
        dirtWithWood2 = new Sprite(A2DAssetManager.manager.get(A2DAssetManager.dirtWithWood, Texture.class));
        dirt3 = new Sprite(A2DAssetManager.manager.get(A2DAssetManager.dirt, Texture.class));
        dirtWithWood3 = new Sprite(A2DAssetManager.manager.get(A2DAssetManager.dirtWithWood, Texture.class));
        dirt3.setBounds(screen.getMain().getLayer().getWidth() * 38 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * 3 * MyGdxGame.k * 1.2f,screen.getMain().getLayer().getHeight() * MyGdxGame.w * 1.2f * 7, screen.getMain().getLayer().getWidth() * MyGdxGame.k * 1.2f * 4);
        dirtWithWood3.setBounds(screen.getMain().getLayer().getWidth() * 38 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * 3 * MyGdxGame.k * 1.2f,screen.getMain().getLayer().getHeight() * MyGdxGame.w * 1.2f * 7, screen.getMain().getLayer().getWidth() * MyGdxGame.k * 1.2f * 4);
        dirt1.setBounds(screen.getMain().getLayer().getWidth() * 25 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * -10 * MyGdxGame.k * 1.2f,screen.getMain().getLayer().getHeight() * MyGdxGame.w * 1.2f * 7, screen.getMain().getLayer().getWidth() * MyGdxGame.k * 1.2f * 4);
        dirtWithWood1.setBounds(screen.getMain().getLayer().getWidth() * 25 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * -10 * MyGdxGame.k * 1.2f,screen.getMain().getLayer().getHeight() * MyGdxGame.w * 1.2f * 5, screen.getMain().getLayer().getWidth() * MyGdxGame.k * 1.2f * 4);
        dirt2.setBounds(screen.getMain().getLayer().getWidth() * 40 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * -3 * MyGdxGame.k * 1.2f,screen.getMain().getLayer().getHeight() * MyGdxGame.w * 1.2f * 7, screen.getMain().getLayer().getWidth() * MyGdxGame.k * 1.2f * 4);
        dirtWithWood2.setBounds(screen.getMain().getLayer().getWidth() * 40 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * -3 * MyGdxGame.k * 1.2f,screen.getMain().getLayer().getHeight() * MyGdxGame.w * 1.2f * 7, screen.getMain().getLayer().getWidth() * MyGdxGame.k * 1.2f * 4);
    }

    public boolean getPressed(int ind) {
        return pressed[ind];
    }

    public void setPressed(int ind) {//на какого персонажа нажали
        if (pressed[ind]) {
            pressed[ind] = false;
            first = true;
            isPressed = -1;
            screen.update(false, 0);
            screen.updateChoiсeFalse();
            screen.setLabelFalse();
        } else {
            Gdx.app.log("quantity", String.valueOf(quantity));
            for (int i = 0; i < quantity; i++) {
                if (pressed[i]) pressed[i] = false;
            }
            pressed[ind] = true;
            isPressed = ind;
            screen.update(false, 0);
            if (first) {
                screen.updateChoiсeTrue();
                screen.setLabelVisible();
                first = false;
            }
            screen.setLabelText();

        }
    }

    public boolean whatPressed(int pos) {
        return pressed[pos];
    }//возвращает какой персонаж нажат

    public void first() {//если впервые запускается
        for (int i = 0; i < quantity; i++) {
            pressed[i] = false;
        }
    }

    public void setFirst(boolean check) {
        first = check;
    }

    public void plusFoodFactory() {
        foodFactory++;
    }

    public void plusWaterFactory() {
        waterFactory++;
    }

    public void plusRockFactory() {
        rockFactory++;
        if (rockFactory == 3){
        }

    }

    public int getFoodFactory() {
        return foodFactory;
    }

    public int getWaterFactory() {
        return waterFactory;
    }

    public int getRockFactory() {
        return rockFactory;
    }

    public int getRockFactoryWorkDay() {
        return rockFactoryWorkDay;
    }

    public void setRockFactoryWorkDay(int rockFactoryWorkDay) {
        this.rockFactoryWorkDay = rockFactoryWorkDay;
    }

    public int getWaterFactoryWorkDay() {
        return waterFactoryWorkDay;
    }

    public void setWaterFactoryWorkDay(int waterFactoryWorkDay) {
        this.waterFactoryWorkDay = waterFactoryWorkDay;
    }

    public int getFoodFactoryWorkDay() {
        return foodFactoryWorkDay;
    }

    public void setFoodFactoryWorkDay(int foodFactoryWorkDay) {
        this.foodFactoryWorkDay = foodFactoryWorkDay;
    }

    public void foodFactoryWorkDayMinus() {
        foodFactoryWorkDay--;
    }

    public void waterFactoryWorkDayMinus() {
        waterFactoryWorkDay--;
    }

    public void rockFactoryWorkDayMinus() {
        rockFactoryWorkDay--;
    }

    public boolean isRockIsBusy() {
        return rockIsBusy;
    }

    public boolean isWaterIsBusy() {
        return waterIsBusy;
    }

    public void setWaterIsBusyFalse() {
        waterIsBusy = false;
        screen.makeWaterDayFalse();
    }

    public void setFoodIsBusyFalse() {
        foodIsBusy = false;
        screen.makeFoodDayFalse();
    }

    public void setRockIsBusyFalse() {
        rockIsBusy = false;
        screen.makeRockDayFalse();
    }

    public boolean isFoodIsBusy() {
        return foodIsBusy;
    }

    public boolean developmentWaterFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
            food = foodWaterFactoryD;
            water = waterWaterFactoryD;
        } else {
            food = foodWaterFactoryD + 1;
            water = waterWaterFactoryD + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0  /* && */ && waterFacoryTimeBisy == 0) {
            waterIsBusy = true;
            whoWater = screen.getMain().getPlayers().get(isPressed).getName();
            Gdx.app.log("check", screen.getMain().getPlayers().get(isPressed).getName());
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            waterFacoryTimeBisy = dayWaterFactoryD - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();//
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            pressed[isPressed] = false;
            first = true;
            screen.makeWaterDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean transferWaterFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
            food = foodWaterFactoryT;
            water = waterWaterFactoryT;
        } else {
            food = foodWaterFactoryT + 1;
            water = waterWaterFactoryT + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0  && waterFacoryTimeBisy == 0 && screen.getMain().getMainManager().getRock() - (rockWaterFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills())) >= 0) {
            waterIsBusy = true;
            whoWater = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            screen.getMain().getMainManager().setMinusRock(rockWaterFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills()));
            waterFacoryTimeBisy = dayWaterFactoryT - screen.getMain().getPlayers().get(isPressed).getStrengthSkills();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            screen.getMain().getPlayers().get(isPressed).setRepair(true);
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(screen.getMain().getLayer().getWidth() * 43 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * -1 * MyGdxGame.k * 1.2f);
            pressed[isPressed] = false;
            first = true;
            screen.makeWaterDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean makeWaterFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            food = foodWaterFactoryM;
            water = waterWaterFactoryM;
        } else {
            food = foodWaterFactoryM + 1;
            water = waterWaterFactoryM + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0  /* && */ && waterFacoryTimeBisy == 0) {
            waterIsBusy = true;
            whoWater = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            waterFacoryTimeBisy = dayWaterFactoryM - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            screen.getMain().getPlayers().get(isPressed).setRepair(true);
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(screen.getMain().getLayer().getWidth() * 43 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * -1 * MyGdxGame.k * 1.2f);
            pressed[isPressed] = false;
            first = true;
            screen.makeWaterDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
//            screen.setLabelWaterFalse();
        }
        else{
            return false;
        }
    }

    public boolean developmentFoodFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
            food = foodWaterFactoryD;
            water = waterWaterFactoryD;
        } else {
            food = foodWaterFactoryD + 1;
            water = waterWaterFactoryD + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0  /* && */ && foodFacoryTimeBisy == 0) {
            foodIsBusy = true;
            whoFood = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            foodFacoryTimeBisy = dayFoodFactoryD - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            pressed[isPressed] = false;
            first = true;
            screen.makeFoodDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean transferFoodFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
            food = foodWaterFactoryT;
            water = waterWaterFactoryT;
        } else {
            food = foodWaterFactoryD + 1;
            water = waterWaterFactoryD + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0  && foodFacoryTimeBisy == 0 && screen.getMain().getMainManager().getRock() - (rockFoodFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills())) >= 0) {
            foodIsBusy = true;
            whoFood = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            screen.getMain().getMainManager().setMinusRock(rockFoodFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills()));
            foodFacoryTimeBisy = dayFoodFactoryT - screen.getMain().getPlayers().get(isPressed).getStrengthSkills();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            screen.getMain().getPlayers().get(isPressed).setRepair(true);
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(screen.getMain().getLayer().getWidth() * 28 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * -8 * MyGdxGame.k * 1.2f);
            pressed[isPressed] = false;
            first = true;
            screen.makeFoodDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean makeFoodFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            food = foodWaterFactoryM;
            water = waterWaterFactoryM;
        } else {
            food = foodWaterFactoryM + 1;
            water = waterWaterFactoryM + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0  /* && */ && foodFacoryTimeBisy == 0) {
            foodIsBusy = true;
            whoFood = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            foodFacoryTimeBisy = dayFoodFactoryM - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            screen.getMain().getPlayers().get(isPressed).setRepair(true);
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(screen.getMain().getLayer().getWidth() * 28 * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * -8 * MyGdxGame.k * 1.2f);
            pressed[isPressed] = false;
            first = true;
            screen.makeFoodDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean developmentRockFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
            food = foodRockFactoryD;
            water = waterRockFactoryD;
        } else {
            food = foodRockFactoryD + 1;
            water = waterRockFactoryD + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0  /* && */ && rockFacoryTimeBisy == 0) {
            rockIsBusy = true;
            whoRock = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            rockFacoryTimeBisy = dayRockFactoryD - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            pressed[isPressed] = false;
            first = true;
            screen.makeRockDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean transferRockFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
            food = foodRockFactoryT;
            water = waterRockFactoryT;
        } else {
            food = foodRockFactoryT + 1;
            water = waterRockFactoryT + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0 && rockFacoryTimeBisy == 0 && screen.getMain().getMainManager().getRock() - (rockRockFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills())) >= 0) {
            rockIsBusy = true;
            whoRock = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            screen.getMain().getMainManager().setMinusRock(rockRockFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills()));
            rockFacoryTimeBisy = dayRockFactoryT - screen.getMain().getPlayers().get(isPressed).getStrengthSkills();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            screen.getMain().getPlayers().get(isPressed).setRepair(true);
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(screen.getMain().getLayer().getWidth() * 40.8f * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * 5 * MyGdxGame.k * 1.2f);
            pressed[isPressed] = false;
            first = true;
            screen.makeRockDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean makeRockFactoryPressed() {//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            food = foodRockFactoryM;
            water = waterRockFactoryM;
        } else {
            food = foodRockFactoryM + 1;
            water = waterRockFactoryM + 1;
        }
        if (screen.getMain().getMainManager().getFood() - food >= 0 && screen.getMain().getMainManager().getWater() - water >= 0  /* && */ && rockFacoryTimeBisy == 0) {
            rockIsBusy = true;
            whoRock = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getMainManager().setMinusFood(food);
            screen.getMain().getMainManager().setMinusWater(water);
            rockFacoryTimeBisy = dayRockFactoryM - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            screen.getMain().getPlayers().get(isPressed).setRepair(true);
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(screen.getMain().getLayer().getWidth() * 40.8f * MyGdxGame.w * 1.2f, screen.getMain().getLayer().getHeight() * 5 * MyGdxGame.k * 1.2f);
            pressed[isPressed] = false;
            first = true;
            screen.makeRockDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        return false;
    }
    public boolean gunPressed(){//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            rock = gunRock;
        }
        else if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() == 1){
            rock = gunRock + 2;
        }
        else{
            rock = gunRock + 1;
        }
        if (screen.getMain().getMainManager().getFood() - gunFood >= 0 && screen.getMain().getMainManager().getWater() - gunWater >= 0 && screen.getMain().getMainManager().getRock() - rock >= 0){
            screen.getMain().getMainManager().setMinusFood(gunFood);
            screen.getMain().getMainManager().setMinusWater(gunWater);
            screen.getMain().getMainManager().setMinusRock(rock);
            gunTime = 1;
            whoGun = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            pressed[isPressed] = false;
            first = true;
            screen.makeGunDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean shovelPressed(){//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            rock = shovelRock;
        }
        else if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() == 1){
            rock = shovelRock + 2;
        }
        else{
            rock = shovelRock + 1;
        }
        if (screen.getMain().getMainManager().getFood() - shovelFood >= 0 && screen.getMain().getMainManager().getWater() - shovelWater >= 0 && screen.getMain().getMainManager().getRock() - rock >= 0){
            screen.getMain().getMainManager().setMinusFood(shovelFood);
            screen.getMain().getMainManager().setMinusWater(shovelWater);
            screen.getMain().getMainManager().setMinusRock(rock);
            shovelTime = 1;
            whoShovel = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            pressed[isPressed] = false;
            first = true;
            screen.makeShovelDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean firstAidPressed(){//заполнение и списывание ресурсов
        if (screen.getMain().getMainManager().getFood() - firstAidFood >= 0 && screen.getMain().getMainManager().getWater() - firstAidWater >= 0){
            screen.getMain().getMainManager().setMinusFood( firstAidFood);
            screen.getMain().getMainManager().setMinusWater( firstAidWater);
            firstAidTime = 1;
            whoFirstAid = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            pressed[isPressed] = false;
            first = true;
            screen.makeFirstAidDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hammerPressed(){//заполнение и списывание ресурсов
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            rock = hammerRock;
        }
        else if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() == 1){
            rock = hammerRock + 2;
        }
        else{
            rock = hammerRock + 1;
        }
        if (screen.getMain().getMainManager().getFood() - hammerFood >= 0 && screen.getMain().getMainManager().getWater() - hammerWater >= 0 && screen.getMain().getMainManager().getRock() - rock >= 0 && screen.getMain().getMainManager().getThings().indexOf(7) == -1){
            screen.getMain().getMainManager().setMinusFood(hammerFood);
            screen.getMain().getMainManager().setMinusWater(hammerWater);
            screen.getMain().getMainManager().setMinusRock(rock);
            hammerTime = 1;
            whoHammer = screen.getMain().getPlayers().get(isPressed).getName();
            screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
            pressed[isPressed] = false;
            first = true;
            screen.makeHammerDayTrue();
            screen.update(true, isPressed);
            screen.updateChoiсeFalse();
            screen.updateLabel();
            return true;
        }
        else {
            return false;
        }
    }



    public String getWhoWater() {
        return whoWater;
    }//кто делате здание, в котором производится вода

    public void setNullWhoWater() {
        this.whoWater = "null";
    }

    public String getWhoFood() {
        return whoFood;
    }//кто делате здание, в котором производится еда

    public void setNullWhoFood() {
        this.whoFood = "null";
    }

    public String getWhoRock() {
        return whoRock;
    }//кто делате здание, в котором производится камень

    public void setNullWhoRock() {
        this.whoRock = "null";
    }

    public int getWaterFacoryTimeBisy() {//получение времени работы здания, в котором производится вода
        return waterFacoryTimeBisy;
    }

    public void setWaterFacoryMinusTimeBisy() {//установка времени работы здания, в котором производится вода
        this.waterFacoryTimeBisy--;
    }

    public int getRockFacoryTimeBisy() {//получение времени работы здания, в котором производится камень
        return rockFacoryTimeBisy;
    }

    public void setRockFacoryTimeBisy() {//утсановка времени работы здания, в котором производится камень
        this.rockFacoryTimeBisy--;
    }

    public int getFoodFacoryTimeBisy() {//получение времени работы здания, в котором производится еда
        return foodFacoryTimeBisy;
    }

    public void setFoodFacoryTimeBisy() {//установка времени работы здания, в котором производится еда
        this.foodFacoryTimeBisy--;
    }
    public String getWhoGun(){// кто производит пистолет
        return whoGun;
    }
    public String getWhoShovel(){// кто производит лопату
        return whoShovel;
    }
    public String getWhoHammer(){// кто производит молот
        return whoHammer;
    }
    public String getWhoFirstAid(){// кто производит аптечку
        return whoFirstAid;
    }
    public int getGunTime(){// время производста пистолета
        return gunTime;
    }
    public int getShovelTime(){// время производста лопатыв
        return shovelTime;
    }
    public int getFirstAidTime(){// время производста аптечки
        return firstAidTime;
    }
    public int getHammerTime(){// время производста молота
        return hammerTime;
    }
    public void setMinusGunDay(){// уменьишть оставшееся кол-во дней производста пистолета
        gunTime--;
    }
    public void setMinusShovelDay(){// уменьишть оставшееся кол-во дней производста лопаты
        shovelTime--;
    }
    public void setMinusHammerDay(){// уменьишть оставшееся кол-во дней производста молота
        hammerTime--;
    }
    public void setMinusFirstAidDay(){// уменьишть оставшееся кол-во дней производста аптечки
        firstAidTime--;
    }

    public void savePreferences() {
        Gdx.app.log("whoRock", whoRock);
        screen.getParent().getPreferencesLoad().putString(PREFERENCES_WHOWATER, whoWater);
        screen.getParent().getPreferencesLoad().putString(PREFERENCES_WHOFOOD, whoFood);
        screen.getParent().getPreferencesLoad().putString(PREFERENCES_WHOROCK, whoRock);
        screen.getParent().getPreferencesLoad().putBoolean(PREFERENCES_ROCK_IS_BUSY, rockIsBusy);
        screen.getParent().getPreferencesLoad().putBoolean(PREFERENCES_FOOD_IS_BUSY, foodIsBusy);
        screen.getParent().getPreferencesLoad().putBoolean(PREFERENCES_WATER_IS_BUSY, waterIsBusy);
        screen.getParent().getPreferencesLoad().putInteger(PREFERENCES_ROCK_FACTORY, rockFactory);
        screen.getParent().getPreferencesLoad().putInteger(PREFERENCES_FOOD_FACTORY, foodFactory);
        screen.getParent().getPreferencesLoad().putInteger(PREFERENCES_WATER_FACTORY, waterFactory);
        screen.getParent().getPreferencesLoad().putInteger(WATER_FACTORY_TIME_BISY, waterFacoryTimeBisy);
        screen.getParent().getPreferencesLoad().putInteger(FOOD_FACTORY_TIME_BISY, foodFacoryTimeBisy);
        screen.getParent().getPreferencesLoad().putInteger(ROCK_FACTORY_TIME_BISY, rockFacoryTimeBisy);
    }

    public void loadPreferences() {
        whoRock = screen.getParent().getPreferencesLoad().getString(PREFERENCES_WHOROCK);
        whoWater = screen.getParent().getPreferencesLoad().getString(PREFERENCES_WHOWATER);
        whoFood = screen.getParent().getPreferencesLoad().getString(PREFERENCES_WHOFOOD);
        rockIsBusy = screen.getParent().getPreferencesLoad().getBoolean(PREFERENCES_ROCK_IS_BUSY);
        foodIsBusy = screen.getParent().getPreferencesLoad().getBoolean(PREFERENCES_FOOD_IS_BUSY);
        waterIsBusy = screen.getParent().getPreferencesLoad().getBoolean(PREFERENCES_WATER_IS_BUSY);
        rockFactory = screen.getParent().getPreferencesLoad().getInteger(PREFERENCES_ROCK_FACTORY);
        foodFactory = screen.getParent().getPreferencesLoad().getInteger(PREFERENCES_FOOD_FACTORY);
        waterFactory = screen.getParent().getPreferencesLoad().getInteger(PREFERENCES_WATER_FACTORY);
        waterFacoryTimeBisy = screen.getParent().getPreferencesLoad().getInteger(WATER_FACTORY_TIME_BISY);
        foodFacoryTimeBisy = screen.getParent().getPreferencesLoad().getInteger(FOOD_FACTORY_TIME_BISY);
        rockFacoryTimeBisy = screen.getParent().getPreferencesLoad().getInteger(ROCK_FACTORY_TIME_BISY);
    }
    public int getWaterFactoryWater(){//получение количества воды, для производства здания, в котором производится вода
        if (waterFactory == 0){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
                return waterWaterFactoryD;
            } else {
                return waterWaterFactoryD + 1;
            }
        }
        if (waterFactory == 1){
            if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
                return waterWaterFactoryT;
            }
            else {
                return waterWaterFactoryT + 1;
            }
        }
        if (waterFactory == 2){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
                return waterWaterFactoryM;
            }
            else {
                return waterWaterFactoryM + 1;
            }
        }
        else{
            return  0;
        }
    }
    public int getFoodFactoryWater(){//получение количества еды, для производства здания, в котором производится вода
        if (waterFactory == 0){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
                return foodWaterFactoryD;
            } else {
                return foodWaterFactoryD + 1;
            }
        }
        if (waterFactory == 1){
            if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
                return foodWaterFactoryT;
            }
            else {
                return foodWaterFactoryT + 1;
            }
        }
        if (waterFactory == 2){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
                return foodWaterFactoryM;
            }
            else {
                return foodWaterFactoryM + 1;
            }
        }
        else{
            return  0;
        }
    }
    public int getTimeFactoryWater(){//получение количества времени, для производства здания, в котором производится вода
        if (waterFactory == 0){
            return dayWaterFactoryD - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        if (waterFactory == 1){
            return dayWaterFactoryT - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        if (waterFactory == 2){
            return dayWaterFactoryM - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        else{
            return  0;
        }
    }
    public int getRockFactoryWater(){//получение количества камня, для производства здания, в котором производится вода
        return rockWaterFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills());
    }
    public int getWaterFactoryFood(){//получение количества воды, для производства здания, в котором производится еда
        if (foodFactory == 0){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
                return waterFoodFactoryD;
            } else {
                return waterFoodFactoryD + 1;
            }
        }
        if (foodFactory == 1){
            if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
                return waterFoodFactoryT;
            }
            else {
                return waterFoodFactoryT + 1;
            }
        }
        if (foodFactory == 2){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
                return waterFoodFactoryM;
            }
            else {
                return waterFoodFactoryM + 1;
            }
        }
        else{
            return  0;
        }
    }
    public int getFoodFactoryFood(){//получение количества еды, для производства здания, в котором производится еда
        if (foodFactory == 0){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
                return foodFoodFactoryD;
            } else {
                return foodFoodFactoryD + 1;
            }
        }
        if (foodFactory == 1){
            if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
                return foodFoodFactoryT;
            }
            else {
                return foodFoodFactoryT + 1;
            }
        }
        if (foodFactory == 2){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
                return foodFoodFactoryM;
            }
            else {
                return foodFoodFactoryM + 1;
            }
        }
        else{
            return  0;
        }
    }
    public int getTimeFactoryFood(){//получение количества времени, для производства здания, в котором производится еда
        if (foodFactory == 0){
            return dayFoodFactoryD - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        if (foodFactory == 1){
            return dayFoodFactoryT - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        if (foodFactory == 2){
            return dayFoodFactoryM - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        else{
            return  0;
        }
    }
    public int getRockFactoryFood(){//получение количества камня, для производства здания, в котором производится еда
        return rockFoodFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills());
    }
    public int getWaterFactoryRock(){//получение количества воды, для производства здания, в котором производится камень
        if (rockFactory == 0){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
                return waterRockFactoryD;
            } else {
                return waterRockFactoryD + 1;
            }
        }
        if (rockFactory == 1){
            if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
                return waterRockFactoryT;
            }
            else {
                return waterRockFactoryT + 1;
            }
        }
        if (rockFactory == 2){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
                return waterRockFactoryM;
            }
            else {
                return waterRockFactoryM + 1;
            }
        }
        else{
            return  0;
        }
    }
    public int getFoodFactoryRock(){//получение количества еды, для производства здания, в котором производится камень
        if (rockFactory == 0){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() >= 3) {
                return foodRockFactoryD;
            } else {
                return foodRockFactoryD + 1;
            }
        }
        if (rockFactory == 1){
            if (screen.getMain().getPlayers().get(isPressed).getStrengthSkills() >= 3) {
                return foodRockFactoryT;
            }
            else {
                return foodRockFactoryT + 1;
            }
        }
        if (rockFactory == 2){
            if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
                return foodRockFactoryM;
            }
            else {
                return foodRockFactoryM + 1;
            }
        }
        else{
            return  0;
        }
    }
    public int getTimeFactoryRock(){//получение количества времени, для производства здания, в котором производится камень
        if (rockFactory == 0){
            return dayRockFactoryD - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        if (rockFactory == 1){
            return dayRockFactoryT - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        if (rockFactory == 2){
            return dayRockFactoryM - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills();
        }
        else{
            return  0;
        }
    }
    public int getRockFactoryRock(){//получение количества камня, для производства здания, в котором производится камень
        return rockRockFactory + (5 - screen.getMain().getPlayers().get(isPressed).getEngineeringSkills());
    }
    public Sprite getSpriteFood(){
        if (foodFactory == 1){
            return dirt1;
        }
        else{
            return dirtWithWood1;
        }
    }
    public Sprite getSpriteWater(){
        if (waterFactory == 1){
            return dirt2;
        }
        else{
            return dirtWithWood2;
        }
    }
    public Sprite getSpriteRock(){
        if (rockFactory == 1){
            return dirt3;
        }
        else{
            return dirtWithWood3;
        }
    }
    public int getFoodGun(){
        return gunFood;
    }
    public int getWaterGun(){
        return gunWater;
    }
    public int getRockGun(){
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            return gunRock;
        }
        else if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() == 1){
            return gunRock + 2;
        }
        else {
            return gunRock + 1;
        }
    }



    public int getFoodShovel(){
        return shovelFood;
    }
    public int getWaterShovel(){
        return shovelWater;
    }
    public int getRockShovel(){
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            return shovelRock;
        }
        else if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() == 1){
            return shovelRock + 2;
        }
        else {
            return shovelRock + 1;
        }
    }
    public int getTimeGun(){
        return 1;
    }
    public int getTimeShovel(){
        return 1;
    }
    public int getFoodFirstAid(){
        return firstAidFood;
    }
    public int getWaterHammer(){
        return hammerWater;
    }
    public int getFoodHammer(){
        return hammerFood;
    }
    public int getRockHammer(){
        if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() > 3) {
            return hammerRock;
        }
        else if (screen.getMain().getPlayers().get(isPressed).getEngineeringSkills() == 1){
            return hammerRock + 2;
        }
        else {
            return hammerRock + 1;
        }
    }
    public int getTimeHammer(){
        return 1;
    }
    public int getWaterFirstAid(){
        return firstAidWater;
    }
    public int getTimeFirstAid(){
        return 1;
    }

}
