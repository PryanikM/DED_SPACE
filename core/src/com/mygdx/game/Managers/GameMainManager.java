package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Model.AuxilliaryTool;
import com.mygdx.game.Model.Building;
import com.mygdx.game.Model.Players;
import com.mygdx.game.Model.Tombstone;
import com.mygdx.game.Views.MainGame.GameMainScreen;

import java.util.ArrayList;

public class GameMainManager {
    private ArrayList<Building> buildings;//массив, в котором хранятся здания
    private ArrayList<Tombstone> tombstones;//массив, в котором хранятся могилы
    private RandomManager random;
    private int checkDayHard;//
    private ArrayList<AuxilliaryTool>inv;//массив, в котором хранятся инстурменты



    //константы для сохранений
    public final static String COUNT_DAY = "COUNT_DAY";
    public final static String COUNT_FOOD = "COUNT_FOOD";
    public final static String COUNT_WATER = "COUNT_WATER";
    public final static String COUNT_ROCK = "COUNT_ROCK";
    public final static String COUNT_FIRSTAID = "COUNT_FIRSTAID";
    public final static String CHECKDAYHARD = "CHECKDAYHARD";

    //Player
    public final static String LIVE = "LIVE";
    public final static String X = "X";
    public final static String Y = "Y";
    public final static String NAME = "NAME";
    public final static String HEIGHT = "HEIGHT";
    public final static String WIDTH = "WIDTH";
    public final static String PLAYER_TEXTURE = "PLAYER_TEXTURE";
    public final static String FACE_LINK = "FACE_LINK";
    public final static String HEALTH = "HEALTH";
    public final static String MAX_DAY_WITHOUT_FOOD = "MAX_DAY_WITHOUT_FOOD";
    public final static String MAX_DAY_WITHOUT_WATER = "MAX_DAY_WITHOUT_WATER";
    public final static String IS_BUSY = "IS_BUSY";
    public final static String ENGINEERING_SKILLS = "ENGINEERING_SKILLS";
    public final static String BIOLOGICAL_SKILLS  = "BIOLOGICAL_SKILLS";
    public final static String MINING_SKILLS = "MINING_SKILLS";
    public final static String STRENGTH_SKILLS = "STRENGTH_SKILLS";
    public final static String DAY_WITHOUT_FOOD = "DAY_WITHOUT_FOOD";
    public final static String DAY_WITHOUT_WATER = "DAY_WITHOUT_WATER";
    public final static String MAX_HEALTH = "MAX_HEALTH";
    public final static String ISREPAIR = "ISREPAIR";
    public final static String XR = "XR";
    public final static String YR = "YR";
    public final static String TIME = "TIME";
    public final static String TIMEREPAIR = "TIMEREPAIR";
    public final static String MAXFRAME = "MAXFRAME";

    //Player


    //BUILDINGS

    public final static String LIVEB = "LIVEB";
    public final static String MAX_HEALTHB = "MAX_HEALTHB";
    public final static String DAYSB = "DAYSB";
    public final static String WHO_WORKB = "WHO_WORKB";
    public final static String XB = "XB";
    public final static String YB = "YB";
    public final static String LINKB = "LINKB";
    public final static String RESOURSEB = "RESOURSEB";
    public final static String HEALTHB = "HEALTHB";
    public final static String  BUSYB = "BUSYB";
    public final static String HEIGHTB = "HEIGHTB";
    public final static String WIDTHB = "WIDTHB";
    public final static String REPAIRB = "REPAIRB";

    //BUILDINGS



    //Tombstone
    public final static String LIVET = "LIVET";
    public final static String XT = "XT";
    public final static String YT = "YT";
    public final static String HEIGHTT = "HEIGHTT";
    public final static String WEIDHT = "WEIDHT";
    public final static String WHOT = "WHOT";
    public final static String STATEOUTING = "STATEOUTING";
    //Tombstone


    //inv
    public final static String HEALTHGUN = "HEALTHGUN";
    public final static String NAMEGUN = "NAMEGUN";
    public final static String HEALTHSHOVEL = "HEALTHSHOVEL";
    public final static String NAMESHOVEL = "NAMESHOVEL";

    //inv

    //things
    public final static String T0 = "T0";
    public final static String T1 = "T1";
    public final static String T2 = "T2";
    public final static String T3 = "T3";
    public final static String T4 = "T4";
    //things




    private int food;//общее кол-во еды
    private int water;//общее кол-во воды
    private int rock;//общее кол-во камня
    private int countDay;//общее кол-во дней
    private int countFirstAid;//общее кол-во аптечек
    private static GameMainScreen game;
    private ArrayList<Integer> things;//массив, в котором хранятся промежуточные вещи
    public GameMainManager(GameMainScreen main, int food, int water, int countDay, int rock) {
        this.food = food;
        game = main;
        inv = new ArrayList<AuxilliaryTool>();
        this.water = water;
        this.countDay  = countDay;
        this.rock = rock;
        buildings = new ArrayList<Building>();
        tombstones = new ArrayList<Tombstone>();
        random = new RandomManager(this);
        things = new ArrayList<Integer>();
        countFirstAid = 0;
        checkDayHard = 0;

    }
    public int getFood() {
        return food;
    }
    public void addInv(AuxilliaryTool what){
        inv.add(what);
    }
    public void deleteInv(String what){
        for (int i = 0; i < inv.size(); i++){
            if (inv.get(i).getName().equals(what)){
                inv.remove(i);
                i = inv.size();
            }
        }

    }
    public void setPlusFood(int food) {
        this.food += food;
    }
    public void setMinusFood(int food) {
        if (this.food - food >= 0){
            this.food -= food;
            game.updateFood();

        }

    }
    public void setPlusRock(Integer rock){
        this.rock += rock;
    }
    public int getWater() {
        return water;
    }
    public int getRock(){
        return  rock;
    }
    public void setPlusWater(int water) {
        this.water += water;
    }
    public void setMinusWater(int water) {
        if (this.water - water >= 0) {
            this.water -= water;
            game.updateWater();

        }
    }
    public void setMinusRock(int rock){
        if (this.rock - rock >= 0){
            this.rock -= rock;
            game.updateRock();
        }
    }

    public void setPlusDay() {
        this.countDay++;
    }
    public void setCountDay(int Day){
        countDay = Day;
    }
    public void addBuildings(Building building){//добавление нового здания
        buildings.add(building);
    }
    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public ArrayList<Tombstone> getTombstones(){
        return tombstones;
    }
    public void addTombstone(Tombstone tombstone){
        tombstones.add(tombstone);
    }
    public void savePreferences(){
        game.parent.getPreferencesLoad().putInteger(COUNT_DAY, countDay);
        game.parent.getPreferencesLoad().putInteger(COUNT_FOOD, food);
        game.parent.getPreferencesLoad().putInteger(COUNT_WATER, water);
        game.parent.getPreferencesLoad().putInteger(COUNT_ROCK, rock);
        game.parent.getPreferencesLoad().putInteger(COUNT_FIRSTAID, countFirstAid);
        game.parent.getPreferencesLoad().putInteger(CHECKDAYHARD, checkDayHard);
    }
    public void loadPreferences(){
        countDay = game.parent.getPreferencesLoad().getInteger(COUNT_DAY);
        food = game.parent.getPreferencesLoad().getInteger(COUNT_FOOD);
        water = game.parent.getPreferencesLoad().getInteger(COUNT_WATER);
        rock = game.parent.getPreferencesLoad().getInteger(COUNT_ROCK);
        countFirstAid = game.parent.getPreferencesLoad().getInteger(COUNT_FIRSTAID);
        checkDayHard = game.parent.getPreferencesLoad().getInteger(CHECKDAYHARD);
    }
    public int getCountDay() {
        return countDay;
    }
    public void saveBuildings(){
        for (int i = 0 ; i < buildings.size(); i++){
            game.parent.getBuildingPreferences().get(i).putBoolean(LIVEB, true);
            game.parent.getBuildingPreferences().get(i).putInteger(MAX_HEALTHB, buildings.get(i).getMax_health());
            game.parent.getBuildingPreferences().get(i).putInteger(DAYSB, buildings.get(i).getDays());
            game.parent.getBuildingPreferences().get(i).putString(WHO_WORKB, buildings.get(i).getWhoWork());
            game.parent.getBuildingPreferences().get(i).putFloat(XB, buildings.get(i).getX());
            game.parent.getBuildingPreferences().get(i).putFloat(YB, buildings.get(i).getY());
            game.parent.getBuildingPreferences().get(i).putString(LINKB, buildings.get(i).getLink());
            game.parent.getBuildingPreferences().get(i).putString(RESOURSEB, buildings.get(i).getResourse());
            game.parent.getBuildingPreferences().get(i).putInteger(HEALTHB, buildings.get(i).getHealth());
            game.parent.getBuildingPreferences().get(i).putBoolean(BUSYB, buildings.get(i).getIsBusy());
            game.parent.getBuildingPreferences().get(i).putFloat(HEIGHTB, buildings.get(i).getHeight());
            game.parent.getBuildingPreferences().get(i).putFloat(WIDTHB, buildings.get(i).getWdith());
            game.parent.getBuildingPreferences().get(i).putBoolean(REPAIRB, buildings.get(i).getRepair());
            game.parent.getBuildingPreferences().get(i).flush();
        }
        for (int i = buildings.size(); i < 3; i++){
            game.parent.getBuildingPreferences().get(i).putBoolean(LIVEB, false);
            game.parent.getBuildingPreferences().get(i).flush();

        }
    }
    public void saveTombstone() {
        for (int i = 0; i < tombstones.size(); i++) {
            game.parent.getTombstonePreferences().get(i).putBoolean(LIVET, true);
            game.parent.getTombstonePreferences().get(i).putFloat(XT, tombstones.get(i).getX());
            game.parent.getTombstonePreferences().get(i).putFloat(YT, tombstones.get(i).getY());
            game.parent.getTombstonePreferences().get(i).putFloat(WEIDHT, tombstones.get(i).getWidth());
            game.parent.getTombstonePreferences().get(i).putFloat(HEIGHT, tombstones.get(i).getHeight());
            game.parent.getTombstonePreferences().get(i).putString(WHOT, tombstones.get(i).getWho());
            game.parent.getTombstonePreferences().get(i).flush();
        }
        for (int i = tombstones.size(); i < 5; i++) {
            game.parent.getTombstonePreferences().get(i).putBoolean(LIVET, false);
            game.parent.getTombstonePreferences().get(i).flush();

        }
    }
    public void loadTombstone(){
        for (int i = 0 ; i < game.parent.getTombstonePreferences().size(); i++){
            if (game.parent.getTombstonePreferences().get(i).getBoolean(LIVET)) {
                tombstones.add(new Tombstone(game.parent.getTombstonePreferences().get(i).getFloat(XT),
                game.parent.getTombstonePreferences().get(i).getFloat(YT),
                game.parent.getTombstonePreferences().get(i).getFloat(WEIDHT),
                game.parent.getTombstonePreferences().get(i).getFloat(HEIGHT),
                game.parent.getTombstonePreferences().get(i).getString(WHOT)

                )
                );

            }

        }

    }
    public void savePlayer() {
        for (int g = 0; g < game.getPlayers().size(); g++) {
            game.parent.getPlayerPreferences().get(g).putBoolean(LIVE, true);
            game.parent.getPlayerPreferences().get(g).putFloat(X, game.getPlayers().get(g).getX());
            game.parent.getPlayerPreferences().get(g).putFloat(Y, game.getPlayers().get(g).getY());
            game.parent.getPlayerPreferences().get(g).putString(NAME, game.getPlayers().get(g).getName());
            game.parent.getPlayerPreferences().get(g).putFloat(HEIGHT, game.getPlayers().get(g).getHeight());
            game.parent.getPlayerPreferences().get(g).putFloat(WIDTH, game.getPlayers().get(g).getWidth());
            game.parent.getPlayerPreferences().get(g).putString(PLAYER_TEXTURE, game.getPlayers().get(g).getPlayerTextureT());
            game.parent.getPlayerPreferences().get(g).putString(FACE_LINK, game.getPlayers().get(g).getLinks());
            game.parent.getPlayerPreferences().get(g).putInteger(HEALTH, game.getPlayers().get(g).getHealth());
            game.parent.getPlayerPreferences().get(g).putInteger(MAX_DAY_WITHOUT_FOOD, game.getPlayers().get(g).getMaxDayWithoutFood());
            game.parent.getPlayerPreferences().get(g).putInteger(MAX_DAY_WITHOUT_WATER, game.getPlayers().get(g).getMaxDayWithoutWater());
            game.parent.getPlayerPreferences().get(g).putBoolean(IS_BUSY, game.getPlayers().get(g).getIsBusy());
            game.parent.getPlayerPreferences().get(g).putInteger(ENGINEERING_SKILLS, game.getPlayers().get(g).getEngineeringSkills());
            game.parent.getPlayerPreferences().get(g).putInteger(BIOLOGICAL_SKILLS, game.getPlayers().get(g).getBiologicalSkills());
            game.parent.getPlayerPreferences().get(g).putInteger(MINING_SKILLS, game.getPlayers().get(g).getMiningSkills());
            game.parent.getPlayerPreferences().get(g).putInteger(STRENGTH_SKILLS, game.getPlayers().get(g).getStrengthSkills());
            game.parent.getPlayerPreferences().get(g).putInteger(DAY_WITHOUT_FOOD, game.getPlayers().get(g).getDayWithoutFood());
            game.parent.getPlayerPreferences().get(g).putInteger(DAY_WITHOUT_WATER, game.getPlayers().get(g).getDayWithoutWater());
            game.parent.getPlayerPreferences().get(g).putInteger(MAX_HEALTH, game.getPlayers().get(g).getMax_health());
            game.parent.getPlayerPreferences().get(g).putBoolean(ISREPAIR, game.getPlayers().get(g).getRepair());
            game.parent.getPlayerPreferences().get(g).putFloat(XR, game.getPlayers().get(g).getxR());
            game.parent.getPlayerPreferences().get(g).putFloat(YR, game.getPlayers().get(g).getyR());
            game.parent.getPlayerPreferences().get(g).putFloat(TIME, game.getPlayers().get(g).getTime());
            game.parent.getPlayerPreferences().get(g).putFloat(TIMEREPAIR, game.getPlayers().get(g).getTimeRepair());
            game.parent.getPlayerPreferences().get(g).putInteger(MAXFRAME, game.getPlayers().get(g).getMaxFrame());
            game.parent.getPlayerPreferences().get(g).flush();
        }
        for (int i = game.getPlayers().size(); i < 4; i++){
            game.parent.getPlayerPreferences().get(i).putBoolean(LIVE, false);
            game.parent.getPlayerPreferences().get(i).flush();

        }
    }
    public void loadPlayers(){
        for (int g = 0; g < game.parent.getPlayerPreferences().size(); g++){
            if (game.parent.getPlayerPreferences().get(g).getBoolean(LIVE)){
                game.getPlayers().add(new Players(game.parent.getPlayerPreferences().get(g).getFloat(X),
                        game.parent.getPlayerPreferences().get(g).getFloat(Y),
                        game.parent.getPlayerPreferences().get(g).getString(NAME),
                        game.parent.getPlayerPreferences().get(g).getFloat(HEIGHT),
                        game.parent.getPlayerPreferences().get(g).getFloat(WIDTH),
                        game.parent.getPlayerPreferences().get(g).getString(PLAYER_TEXTURE),
                        game.parent.getPlayerPreferences().get(g).getString(FACE_LINK),
                        game.parent.getPlayerPreferences().get(g).getInteger(HEALTH),
                        game.parent.getPlayerPreferences().get(g).getInteger(MAX_DAY_WITHOUT_FOOD),
                        game.parent.getPlayerPreferences().get(g).getInteger(MAX_DAY_WITHOUT_WATER),
                        game.parent.getPlayerPreferences().get(g).getBoolean(IS_BUSY),
                        game.parent.getPlayerPreferences().get(g).getInteger(ENGINEERING_SKILLS),
                        game.parent.getPlayerPreferences().get(g).getInteger(BIOLOGICAL_SKILLS),
                        game.parent.getPlayerPreferences().get(g).getInteger(MINING_SKILLS),
                        game.parent.getPlayerPreferences().get(g).getInteger(STRENGTH_SKILLS),
                        game.parent.getPlayerPreferences().get(g).getInteger(DAY_WITHOUT_FOOD),
                        game.parent.getPlayerPreferences().get(g).getInteger(DAY_WITHOUT_WATER),
                        game.parent.getPlayerPreferences().get(g).getInteger(MAX_HEALTH),
                        game.parent.getPlayerPreferences().get(g).getBoolean(ISREPAIR),
                        game.parent.getPlayerPreferences().get(g).getFloat(XR),
                        game.parent.getPlayerPreferences().get(g).getFloat(YR),
                        game.parent.getPlayerPreferences().get(g).getFloat(TIMEREPAIR),
                        game.parent.getPlayerPreferences().get(g).getFloat(TIME),
                        game.parent.getPlayerPreferences().get(g).getInteger(MAXFRAME)
                ));
            }
        }
    }
    public void loadBuilding(){
        for (int i = 0; i < game.parent.getBuildingPreferences().size(); i++){
            if(game.parent.getBuildingPreferences().get(i).getBoolean(LIVEB)){
                buildings.add(new Building( game.parent.getBuildingPreferences().get(i).getInteger(MAX_HEALTHB),
                        game.parent.getBuildingPreferences().get(i).getInteger(DAYSB),
                        game.parent.getBuildingPreferences().get(i).getString(WHO_WORKB),
                        game.parent.getBuildingPreferences().get(i).getFloat(XB),
                        game.parent.getBuildingPreferences().get(i).getFloat(YB),
                        game.parent.getBuildingPreferences().get(i).getString(LINKB),
                        game.parent.getBuildingPreferences().get(i).getString(RESOURSEB),
                        game.parent.getBuildingPreferences().get(i).getInteger(HEALTHB),
                        game.parent.getBuildingPreferences().get(i).getBoolean(BUSYB),
                        game.parent.getBuildingPreferences().get(i).getFloat(HEIGHTB),
                        game.parent.getBuildingPreferences().get(i).getFloat(WIDTHB),
                        game.parent.getBuildingPreferences().get(i).getBoolean(REPAIRB)
                ));
            }
        }
    }
    public void saveInv(){
        boolean findGun = false;
        boolean findShovel = false;
        for (int i = 0; i < inv.size(); i++) {
            if (inv.get(i).getName().equals("Gun")) {
                game.parent.getInv().putInteger(HEALTHGUN, inv.get(i).getHealth());
                game.parent.getInv().putString(NAMEGUN, inv.get(i).getName());
                findGun = true;
            }

            if (inv.get(i).getName().equals("Shovel")) {
                game.parent.getInv().putInteger(HEALTHSHOVEL, inv.get(i).getHealth());
                game.parent.getInv().putString(NAMESHOVEL, inv.get(i).getName());
                findShovel = true;
            }
        }
        if (!findGun){
            game.parent.getInv().putString(NAMEGUN, null);
        }
        if (!findShovel){
            game.parent.getInv().putString(NAMESHOVEL, null);
        }
        game.parent.getInv().flush();

    }
    public void loadInv(){
        if (game.parent.getInv().getString(NAMEGUN) != null) {
            inv.add(new AuxilliaryTool(game.parent.getInv().getInteger(HEALTHGUN), game.parent.getInv().getString(NAMEGUN)));
        }
        if (game.parent.getInv().getString(NAMESHOVEL) != null) {
            inv.add(new AuxilliaryTool(game.parent.getInv().getInteger(HEALTHSHOVEL), game.parent.getInv().getString(NAMESHOVEL)));
        }

    }
    public void saveThings(){
        for (int i = 0; i < things.size(); i++){
            game.parent.getInv().putInteger("T" + i, things.get(i));
        }
        for (int i = things.size(); i < 5; i++){
            game.parent.getInv().putInteger("T" + i, -1);
        }
    }
    public void loadThings(){
        for (int i = 0 ; i < 5; i++){
            if (game.parent.getInv().getInteger("T" + i) != -1){
                things.add(game.parent.getInv().getInteger("T" + i));
            }
        }
    }
    public void setNullFood(){
        food = 0;
    }
    public void setNullWater(){
        water = 0;
    }
    public RandomManager getRandom(){
        return random;
    }
    public ArrayList<AuxilliaryTool> getInv(){
        return inv;
    }
    public boolean findInv(String name){//поиск вещи в массиве
        boolean check = false;
        for (int i = 0; i < inv.size(); i++){
            if (inv.get(i).getName().equals(name)){
                check = true;
                return true;
            }
        }
        if (!check){
            return false;
        }
        else{
            return true;
        }

    }
    public ArrayList<Integer> getThings(){
        return things;
    }
    public int getCountFirstAid(){
        return countFirstAid;
    }
    public void setPlusFirstAid(){
        countFirstAid++;
    }
    public void setMinusFirstAid(){
        countFirstAid--;
    }
    public void addThings(int what){
        things.add(what);
    }
    public int getCheckdayhard(){
        return checkDayHard;
    }
    public void setPlusCheckdayhard(){
         checkDayHard++;
    }
}
