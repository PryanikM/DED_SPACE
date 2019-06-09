package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Model.Players;
import com.mygdx.game.Views.MainGame.BuildingsMakeNoScreen;

public class BuildingsMakeManager {
    private boolean pressed[];//массив для хранения состояний нажатия персонажей
    private int quantity;
    private MyGdxGame parent;

    //Константы для сохранения в Preferences
    public final static String WHOWATER = "WHOWATER";
    public final static String WHOFOOD = "WHOFOOD";
    public final static String WHOROCK = "WHOROCK";
    public final static String ROCKFACTORYWORKDAY = "ROCKFACTORYWORKDAY";
    public final static String WATERFACTORYWORKDAY = "WATERFACTORYWORKDAY";
    public final static String FOODFACTORYWORKDAY = "FOODFACTORYWORKDAY";
    public final static String ROCKISBUSY = "ROCKISBUSY";
    public final static String FOODISBUSY = "FOODISBUSY";
    public final static String WATERISBUSY = "WATERISBUSY";
//    private int miner;
//    private int biology;
//    private int strength;
//    private int engineer;
//    private int foodFactory;
//    private int waterFactory;
//    private int rockFactory;
//    private boolean transmitter;
    private String whoWater;//кто работает в здание, в котормо производится вода
    private String whoFood;//кто работает в здание, в котором производится еда
    private String whoRock;//кто работает в здание, в котором производится камень
    private BuildingsMakeNoScreen screen;
    private int rockFactoryWorkDay;//сколько времени нужно для работы в здание
    private int waterFactoryWorkDay;//сколько времени нужно для работы в здание
    private int foodFactoryWorkDay;//сколько времени нужно для работы в здание
    private int isPressed;//какой персонаж выбран
    private boolean rockIsBusy;//занято ли здание
    private boolean waterIsBusy;//занято ли здание
    private boolean foodIsBusy;//занято ли здание
    private Boolean first;

    //factoryFood


    //water
    public int foodFactoryW = 1;
    public int rockFactoryW = 1;
    public int timeFactoryW = 1;
    //water

    //food
    public int rockFactoryF = 2;
     public int waterFactoryF = 2;
    public int timeFactoryF = 2;
    //food

    //rock
    public int foodFactoryR = 3;
    public int waterFactoryR = 3;
    public int timeFactoryR = 3;

    //rock


//    private int waterFacoryTimeBisy;
//    private int rockFacoryTimeBisy;
//    private int foodFacoryTimeBisy;
    public BuildingsMakeManager(int col, BuildingsMakeNoScreen screen, MyGdxGame parent) {
        quantity = col;
        whoRock = "null";
        whoFood = "null";
        whoWater = "null";
        this.screen  = screen;
        this.parent = parent;
        first = true;
        pressed = new boolean[]{false, false, false, false, false};
    }
    public void setPressed(int ind){
        if (pressed[ind]){
            pressed[ind] = false;
            first = true;
            isPressed = -1;
            screen.update(false, 0);
            screen.updateChoiсeFalse();
        }
        else {
            screen.setResVisibleTrue(ind);
            for (int i = 0; i < quantity; i++) {
                if (pressed[i]) pressed[i] = false;
            }
            pressed[ind] = true;
            isPressed = ind;
            screen.setLabel();
            screen.update(false, 0);
            if (first){
                screen.updateChoiсeTrue();
                first = false;
            }

        }
    }
    public boolean whatPressed(int pos){
        return pressed[pos];
    }
    public void first(){
        for (int i = 0; i < quantity; i++){
            pressed[i] = false;
        }
    }
    public void setFirst(boolean check){
        first = check;
    }
    public void setWhoWater (){
        whoWater = parent.getGameScreen().getPlayers().get(isPressed).getName();
        parent.getGameScreen().getPlayers().get(isPressed).setIsBusy(true);
        screen.update(true, isPressed);
    }
    public void setWhoRock(){
        whoRock = parent.getGameScreen().getPlayers().get(isPressed).getName();
        parent.getGameScreen().getPlayers().get(isPressed).setIsBusy(true);
        screen.update(true, isPressed);
    }
    public void setWhoFood(){
        whoFood = parent.getGameScreen().getPlayers().get(isPressed).getName();
        parent.getGameScreen().getPlayers().get(isPressed).setIsBusy(true);
        screen.update(true, isPressed);
    }
    public void setFoodFactoryWorkDay(int day){
        foodFactoryWorkDay = day;
    }
    public void setWaterFactoryWorkDay(int day){
        waterFactoryWorkDay = day;
    }
    public void setRockFactoryWorkDay(int day){
        rockFactoryWorkDay = day;
    }
    public int getWaterFactoryWorkDay(){
        return waterFactoryWorkDay;
    }
    public int getFoodFactoryWorkDay(){
        return foodFactoryWorkDay;
    }
    public int getRockFactoryWorkDay(){
        return rockFactoryWorkDay;
    }
    public void setMinusDayWaterFactory(){
        waterFactoryWorkDay--;
    }
    public void setMinusDayFoodFactory(){
        foodFactoryWorkDay--;
    }
    public void setMinusDayRockFactory(){
        rockFactoryWorkDay--;
    }
    public boolean getisWaterIsBusy(){
        return  waterIsBusy;
    }
    public boolean getisFoodIsBusy(){
        return  foodIsBusy;
    }
    public boolean getisRockIsBusy(){
        return  rockIsBusy;
    }
    public String getWhoWater(){
        return whoWater;
    }
    public String getWhoFood(){
        return whoFood;
    }
    public String getWhoRock(){
        return whoRock;
    }
    public void setFoodBusyFalse(){
        for (int i = 0; i < parent.getGameScreen().getMainManager().getBuildings().size(); i++){
            if (parent.getGameScreen().getMainManager().getBuildings().get(i).getResourse().equals("food")){
                parent.getGameScreen().getMainManager().getBuildings().get(i).setBusy(false);
                parent.getGameScreen().getMainManager().getBuildings().get(i).setMinusHealth();
                setFoodIsBusyFalse();
                return;
            }
        }
    }
    public void setWaterBusyFalse(){
        for (int i = 0; i < parent.getGameScreen().getMainManager().getBuildings().size(); i++){
            if (parent.getGameScreen().getMainManager().getBuildings().get(i).getResourse().equals("water")){
                parent.getGameScreen().getMainManager().getBuildings().get(i).setBusy(false);
                parent.getGameScreen().getMainManager().getBuildings().get(i).setMinusHealth();
                setWaterIsBusyFalse();
                return;
            }
        }
    }
    public void setRockBusyFalse(){
        for (int i = 0; i < parent.getGameScreen().getMainManager().getBuildings().size(); i++){
            if (parent.getGameScreen().getMainManager().getBuildings().get(i).getResourse().equals("rock")){
                parent.getGameScreen().getMainManager().getBuildings().get(i).setBusy(false);
                parent.getGameScreen().getMainManager().getBuildings().get(i).setMinusHealth();
                setRockIsBusyFalse();
                return;
            }
        }
    }
    public Players getPlayerPressed(){
        return parent.getGameScreen().getPlayers().get(isPressed);
    }
    public void setFoodBusyTrue(){
        foodIsBusy = true;
    }
    public void setWaterBusyTrue(){
        waterIsBusy = true;
    }
    public void setRockBusyTrue(){
        rockIsBusy = true;
    }
    public void setRockIsBusyFalse(){
        rockIsBusy = false;
    }
    public void setWaterIsBusyFalse(){
        waterIsBusy = false;
    }
    public void setFoodIsBusyFalse(){
        foodIsBusy = false;
    }
    public int getFoodForWaterFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getBiologicalSkills() > 3){
            return 1;
        }
        else if (parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3){
            return 2;
        }
        else return 3;
    }
    public int getRockForWaterFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getBiologicalSkills() > 3){
            return 1;
        }
        else if (parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3){
            return 2;
        }
        else return 3;
    }
    public int getTimeForWaterFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getBiologicalSkills() > 3 || parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3){
            return 2;
        }
        else return 3;
    }
    public int getWaterForFoodFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getBiologicalSkills() > 3){
            return 1;
        }
        else if (parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3){
            return  2;
        }
        else return 3;
    }
    public int getRockForFoodFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getBiologicalSkills() > 3){
            return 1;
        }
        else if (parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3){
            return 2;
        }
        else return 3;
    }
    public int getTimeForFoodFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getBiologicalSkills() > 3 || parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3){
            return 2;
        }
        else return 3;
    }
    public int getWaterForRockFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3){
            return 1;
        }
        else if (parent.getGameScreen().getPlayers().get(isPressed).getStrengthSkills() > 3){
            return 2;
        }
        else return 3;
    }
    public int getFoodForRockFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3){
            return 1;
        }
        else if (parent.getGameScreen().getPlayers().get(isPressed).getStrengthSkills() > 3){
            return 2;
        }
        else return 3;
    }
    public int getTimeForRockFactory(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getMiningSkills() > 3 || parent.getGameScreen().getPlayers().get(isPressed).getStrengthSkills() > 3){
            return 2;
        }
        else return 3;
    }
    public void savePreferences(){
        parent.getBuildingMake().putString(WHOWATER, whoWater);
        parent.getBuildingMake().putString(WHOFOOD, whoFood);
        parent.getBuildingMake().putString(WHOROCK, whoRock);
        parent.getBuildingMake().putInteger(FOODFACTORYWORKDAY, foodFactoryWorkDay);
        parent.getBuildingMake().putInteger(WATERFACTORYWORKDAY, waterFactoryWorkDay);
        parent.getBuildingMake().putInteger(ROCKFACTORYWORKDAY, rockFactoryWorkDay);
        parent.getBuildingMake().putBoolean(FOODISBUSY, foodIsBusy);
        parent.getBuildingMake().putBoolean(WATERISBUSY, waterIsBusy);
        parent.getBuildingMake().putBoolean(ROCKISBUSY, rockIsBusy);
        parent.getBuildingMake().flush();
    }
    public void loadPreferences()
    {
        whoWater = parent.getBuildingMake().getString(WHOWATER);
        whoFood = parent.getBuildingMake().getString(WHOFOOD);
        whoRock = parent.getBuildingMake().getString(WHOROCK);
        foodFactoryWorkDay = parent.getBuildingMake().getInteger(FOODFACTORYWORKDAY);
        waterFactoryWorkDay = parent.getBuildingMake().getInteger(WATERFACTORYWORKDAY);
        rockFactoryWorkDay = parent.getBuildingMake().getInteger(ROCKFACTORYWORKDAY);
        foodIsBusy = parent.getBuildingMake().getBoolean(FOODISBUSY);
        waterIsBusy = parent.getBuildingMake().getBoolean(WATERISBUSY);
        rockIsBusy = parent.getBuildingMake().getBoolean(ROCKISBUSY);
    }
    public int getFoodProcent1(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 30;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 25;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 5;
        }
        else{
            return 70;
        }
    }
    public int getFoodProcent2(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 35;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 45;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 10;
        }
        else{
            return 15;
        }
    }
    public int getFoodProcent3(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 30;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 25;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 15;
        }
        else{
            return 10;
        }
    }
    public int getFoodProcent4(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 5;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 5;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 70;
        }
        else{
            return 5;
        }
    }
    public int getWaterProcent1(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 25;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 30;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 5;
        }
        else{
            return 70;
        }
    }
    public int getWaterProcent2(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 45;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 35;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 10;
        }
        else{
            return 15;
        }
    }
    public int getWaterProcent3(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 25;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 30;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 20;
        }
        else{
            return 10;
        }
    }
    public int getWaterProcent4(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 5;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 5;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 65;
        }
        else{
            return 5;
        }
    }

    public int getRockProcent1(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 5;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 10;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 80;
        }
        else{
            return 20;
        }
    }
    public int getRockProcent2(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 5;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 40;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 10;
        }
        else{
            return 50;
        }
    }
    public int getRockProcent3(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 10;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 30;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 5;
        }
        else{
            return 25;
        }
    }
    public int getRockProcent4(){
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Miner")){
            return 80;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Engineer")){
            return 20;
        }
        if (parent.getGameScreen().getPlayers().get(isPressed).getName().equals("Biology")){
            return 5;
        }
        else{
            return 5;
        }
    }


}
