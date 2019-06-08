package com.mygdx.game.manager;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.views.MainGame.BuildingBuildScreen;
import com.mygdx.game.views.MainGame.BuildingRepairScreen;

public class BuildingRepairManager {
    private boolean pressed[];//массив для хранения состояний нажатия персонажей
    public Boolean first;
    private int isPressed;//кто из персонажей выбран
    private BuildingRepairScreen screen;
    int quantity = 0;
    private int waterWorkTime;//время для починки здания
    private int waterWater;//вода для починки здания
    private int waterFood;//еда для починки здания
    private int foodWorkTime;//время для починки здания
    private int foodWater;//вода для починки здания
    private int foodFood;//еда для починки здания
    private int rockWorkTime;//время для починки здания
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private Boolean rockIsRepair;//ремонтрируется ли здание
    private Boolean waterIsRepair;//ремонтрируется ли здание
    private Boolean foodIsRepair;//ремонтрируется ли здание
    private String whoWorkWater;//кто чинит здание, в котором производится вода
    private String whoWorkFood;//кто чинит зданиев, в котором производится еда
    private String whoWorkRock;//кто чинит здание, в котором производится камень
    private int waterDay;//сколько дней будет чиниться здание
    private int foodDay;//сколько дней будет чиниться здание
    private int rockDay;//сколько дней будет чиниться здание
    private int rockWater;//вода для починки здания
    private MyGdxGame parent;
    private int rockFood;//еда для починки здания


    //КОНСТАНТЫ ДЛЯ СОХРАНЕНИЯ
    public final static String WATERWORKTIME = "WATERWORKTIME";
    public final static String FOODWORKTIME = "FOODWORKTIME";
    public final static String ROCKWORKTIME = "ROCKWORKTIME";
    public final static String ROCKISREPAIR = "ROCKISREPAIR";
    public final static String WATERISREPAIR = "WATERISREPAIR";
    public final static String FOODISREPAIR = "FOODISREPAIR";
    public final static String WATERDAY = "WATERDAY";
    public final static String FOODDAY = "FOODDAY";
    public final static String ROCKDAY = "ROCKDAY";
    public final static String WHOWORKWATER = "WHOWORKWATER";
    public final static String WHOWORKFOOD = "WHOWORKFOOD";
    public final static String WHOWORKROCK = "WHOWORKROCK";
    public BuildingRepairManager(int col, BuildingRepairScreen screen, float k, MyGdxGame parent) {
        quantity = col;
        pressed = new boolean[]{false, false, false, false, false};
        this.screen = screen;
        first = true;
        waterWorkTime = 1;
        foodWorkTime = 1;
        rockWorkTime = 1;
        foodWater = 1;
        waterWater = 1;
        rockWater = 1;
        rockFood = 1;
        waterFood = 1;
        foodFood = 1;
        this.k = k;
        this.w = MyGdxGame.w;
        waterDay = 0;
        foodDay = 0;
        rockDay = 0;
        rockIsRepair = false;
        waterIsRepair = false;
        foodIsRepair = false;
        this.parent = parent;
    }
    public void setPressed(int ind){//установить кто из персонажей выбран
        if (pressed[ind]){
            pressed[ind] = false;
            first = true;
            isPressed = -1;
            screen.setResVisibleFalse();
            screen.update(false, 0);
        }
        else {
            for (int i = 0; i < quantity; i++) {
                if (pressed[i]) pressed[i] = false;
            }
            pressed[ind] = true;
            isPressed = ind;
            screen.update(false, 0);
            screen.setResVisibleTrue(ind);
            if (first){
                first = false;
            }

        }
    }
    public boolean whatPressed(int pos){
        return pressed[pos];
    }// получить кто из персонажей выбран
    public void first(){
        for (int i = 0; i < quantity; i++){
            pressed[i] = false;
        }
    }
    public int getWorkTime(int skills, String what){//получить время работы
        if (what.equals("food")){
            return (foodWorkTime + (5 - skills));
        }
        if (what.equals("water")){
            return (waterWorkTime + (5 - skills));
        }
        else {
            return (rockWorkTime  + (5 - skills));
        }
    }
    public int getWater(int skills, String what){//получить количесвто воды
        if (what.equals("food")){
            if (skills >= 3){
                return foodWater;
            }
            else return foodWater + 1;
        }
        if (what.equals("water")){
            if (skills >= 3){
                return waterWater;
            }
            else return waterWater + 1;
        }
        else {
            if (skills >= 3){
                return rockWater;
            }
            else return rockWater + 1;
        }
    }
    public int getFood(int skills, String what){//получить кол-во еды
        if (what.equals("food")){
            if (skills >= 3){
                return foodFood;
            }
            else return foodFood + 1;
        }
        if (what.equals("water")){
            if (skills >= 3){
                return waterFood;
            }
            else return waterFood + 1;
        }
        else {
            if (skills >= 3){
                return rockFood;
            }
            else return rockFood + 1;
        }
    }
    public int getPressed(){
        int what = 0;
        for (int i = 0; i < 5; i++){
            if (pressed[i]){
                what = i;
            }
        }
        return what;
    }
    public int getTimeWork(String what){//получить время работы
        if (what.equals("water")){
            return waterDay;
        }
        else if (what.equals("food")){
            return foodDay;
        }
        else {
            return rockDay;
        }
    }
    public void setFirst(boolean check){
        this.first = check;
    }
    public void repair(float x, float y, String what){//заполнение и списывание ресурсов
        screen.getMain().getPlayers().get(isPressed).setRepair(true);
        screen.getMain().getPlayers().get(isPressed).setIsBusy(true);
        if (what.equals("water")){
            waterIsRepair = true;
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(x  + 15f * w, y + 50f * k);
            whoWorkWater = screen.getMain().getPlayers().get(isPressed).getName();
        }
        if (what.equals("food")){
            foodIsRepair = true;
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(x  + 40f * w, y + 50f * k);
            whoWorkFood = screen.getMain().getPlayers().get(isPressed).getName();
        }
        if (what.equals("rock")){
            rockIsRepair = true;
            screen.getMain().getPlayers().get(isPressed).setPositionForRepair(x  + 140f * w, y + 110f * k);
            whoWorkRock = screen.getMain().getPlayers().get(isPressed).getName();
        }
        screen.update(true, isPressed);
    }
    public int getWaterDay(){//получить время работы здания, в котором производится вода
        return waterDay;
    }
    public int getFoodDay(){//получить время работы здания, в котором производится еда
        return foodDay;
    }
    public int getRockDay(){//получить время работы здания, в котором производится камень
        return rockDay;
    }
    public void setDay(String what, int day){
        if (what.equals("water")){
            waterDay = day;
        }
        if (what.equals("food")){
            foodDay = day;
        }
        if (what.equals("rock")){
            rockDay = day;
        }
    }
    public void setRockIsRepair(Boolean check){
        rockIsRepair = check;
    }
    public void setWaterIsRepair (Boolean check){
        waterIsRepair = check;
    }
    public void setFoodIsRepair (Boolean check){
        foodIsRepair = check;
    }
    public Boolean getRockIsRepair(){
        return rockIsRepair;
    }
    public Boolean getFoodIsRepair(){
        return foodIsRepair;
    }
    public Boolean getWaterIsRepair(){
        return waterIsRepair;
    }
    public void setMinusWaterDay(){
        waterDay--;
    }
    public void setMinusFoodDay(){
        foodDay--;
    }
    public void setMinusRockDay(){
        rockDay--;
    }
    public void setNullPlayerWater(){

    }
    public String getwhoWorkWater(){
        return whoWorkWater;
    }
    public String getwhoWorkFood(){
        return whoWorkFood;
    }
    public String getwhoWorkRock(){
        return whoWorkRock;
    }
    public void savePref(){
        parent.getPreferencesBuildingRepair().putInteger(WATERWORKTIME, waterWorkTime);
        parent.getPreferencesBuildingRepair().putInteger(FOODWORKTIME, foodWorkTime);
        parent.getPreferencesBuildingRepair().putInteger(ROCKWORKTIME, rockWorkTime);
        parent.getPreferencesBuildingRepair().putBoolean(ROCKISREPAIR, rockIsRepair);
        parent.getPreferencesBuildingRepair().putBoolean(WATERISREPAIR, waterIsRepair);
        parent.getPreferencesBuildingRepair().putBoolean(FOODISREPAIR, foodIsRepair);
        parent.getPreferencesBuildingRepair().putInteger(WATERDAY, waterDay);
        parent.getPreferencesBuildingRepair().putInteger(FOODDAY, foodDay);
        parent.getPreferencesBuildingRepair().putInteger(ROCKDAY, rockDay);
        parent.getPreferencesBuildingRepair().putString(WHOWORKWATER, whoWorkWater);
        parent.getPreferencesBuildingRepair().putString(WHOWORKFOOD, whoWorkFood);
        parent.getPreferencesBuildingRepair().putString(WHOWORKROCK, whoWorkRock);
        parent.getPreferencesBuildingRepair().flush();

    }
    public void load() {
        waterWorkTime = parent.getPreferencesBuildingRepair().getInteger(WATERWORKTIME);
        foodWorkTime = parent.getPreferencesBuildingRepair().getInteger(FOODWORKTIME);
        rockWorkTime = parent.getPreferencesBuildingRepair().getInteger(ROCKWORKTIME);
        rockIsRepair = parent.getPreferencesBuildingRepair().getBoolean(ROCKISREPAIR);
        waterIsRepair = parent.getPreferencesBuildingRepair().getBoolean(WATERISREPAIR);
        foodIsRepair = parent.getPreferencesBuildingRepair().getBoolean(FOODISREPAIR);
        waterDay = parent.getPreferencesBuildingRepair().getInteger(WATERDAY);
        foodDay = parent.getPreferencesBuildingRepair().getInteger(FOODDAY);
        rockDay = parent.getPreferencesBuildingRepair().getInteger(ROCKDAY);
        whoWorkWater = parent.getPreferencesBuildingRepair().getString(WHOWORKWATER);
        whoWorkFood = parent.getPreferencesBuildingRepair().getString(WHOWORKFOOD);
        whoWorkRock = parent.getPreferencesBuildingRepair().getString(WHOWORKROCK);
    }

}
