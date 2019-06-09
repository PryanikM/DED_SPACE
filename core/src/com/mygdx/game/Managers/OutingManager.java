package com.mygdx.game.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Loader.A2DAssetManager;
import com.mygdx.game.Model.Players;
import com.mygdx.game.Views.MainGame.OutingNotBusyScreen;

public class OutingManager {
    private boolean pressed[];//массив для хранения состояний нажатия персонажей
    private int quantity = 0;
    private int isPressed;//кто из персонажей выбран
    private Boolean first;
    private OutingNotBusyScreen screen;
//    private int easyFood;
//    private int easyWater;
//    private int easyTime;
//    private int mediumFood;
//    private int mediumWater;
//    private int mediumTime;
//    private int hardFood;
//    private int hardWater;
//    private int hardTime;
    private boolean isGun;// выбран ли пистолет
    private boolean isShovel;//выбрана ли лопата
    private boolean stateOuting;//переменная для выбора загрузки активности
    private int days;//дни в вылазке
    private String whoBisy;//кто в вылазке
    private String whatLevel;//какой выбран уровень сложности
    private Image playerFace;//фотография играка, отправивщегося в вылазку
    private Boolean isBusy;//ушел ли кто-нибудь уже
    private MyGdxGame parent;

    //константы для сохранения в Preferences
    private final static String WHOBISY = "WHOBISY";
    private final static String WHATLEVEL = "WHTALEVEL";
    private final static String DAYS = "DAYS";
    private final static String ISBUSY = "ISBUSY";
    private final static String STATEOUTING = "STATEOUTING";
    private final static String ISGUN = "ISGUN";
    private final static String ISSHOVEL = "ISSHOVEL";
    public OutingManager(int col, OutingNotBusyScreen screen, MyGdxGame parent) {
        this.parent = parent;
        quantity = col;
        this.screen  = screen;
        first = true;
        pressed = new boolean[]{false, false, false, false, false};
//        easyFood = 0;
//        easyWater = 0;
//        easyTime = 0;
//        mediumFood = 1;
//        mediumWater = 1;
//        mediumTime = 1;
//        hardFood = 2;
//        hardWater = 2;
//        hardTime = 2;
        whoBisy = "null";
        isGun = false;
        isShovel = false;
        whatLevel = null;
        days = 0;
        stateOuting = false;
        isBusy = false;
    }
    public void setPressed(int ind){//установить кто из персонажей выбран
        if (pressed[ind]){
            pressed[ind] = false;
            first = true;
            isPressed = -1;
            screen.update(false, 0);
            screen.updateChoiсeFalse();
        }
        else {
            for (int i = 0; i < quantity; i++) {
                if (pressed[i]) pressed[i] = false;
            }
            pressed[ind] = true;
            isPressed = ind;
            screen.update(false, 0);
            screen.updateResource(ind);
            if (first){
                screen.updateChoiсeTrue();
                first = false;
            }

        }
    }
    public boolean whatPressed(int pos){//кто из персонажей выбран
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

    public int getWorkTime(Players player, int level){//получить время нахождения в вылазке
        if (level == 0){
            if (player.getStrengthSkills() == 4 || player.getEngineeringSkills() == 4){
                return 4;
            }
            else{
                return 6;
            }
        }
        if (level == 1) {
            if (player.getStrengthSkills() == 4 || player.getEngineeringSkills() == 4) {
                return 7;
            }
            else {
                return 9;
            }
        }
        else {
            if (player.getStrengthSkills() == 4 || player.getEngineeringSkills() == 4){
                return 12;
            }
            else{
                return 14;
            }
        }
    }
    public int getWater(Players player, int level) {//получить кол-во воды, для того, чтобы отправить персонажа в вылазку
        if (level == 0) {
            if (player.getStrengthSkills() == 4){
                return 3;
            }
            else if (player.getEngineeringSkills() == 4){
                return 4;
            }
            else if(player.getMiningSkills() == 4){
                return 5;
            }
            else{
                return 6;
            }
        }
        if (level == 1){
            if (player.getStrengthSkills() == 4){
                return 5;
            }
            else if (player.getEngineeringSkills() == 4){
                return 6;
            }
            else if(player.getMiningSkills() == 4){
                return 7;
            }
            else{
                return 8;
            }
        }
        else{
            if (player.getStrengthSkills() == 4){
                return 7;
            }
            else if (player.getEngineeringSkills() == 4){
                return 8;
            }
            else if(player.getMiningSkills() == 4){
                return 9;
            }
            else{
                return 10;
            }
        }
    }
    public int getFood(Players player, int level){//получить кол-во еды, для того, чтобы отправить персонажа в вылазку
        if (level == 0) {
            if (player.getStrengthSkills() == 4){
                return 3;
            }
            else if (player.getEngineeringSkills() == 4){
                return 4;
            }
            else if(player.getMiningSkills() == 4){
                return 5;
            }
            else{
                return 6;
            }
        }
        if (level == 1){
            if (player.getStrengthSkills() == 4){
                return 5;
            }
            else if (player.getEngineeringSkills() == 4){
                return 6;
            }
            else if(player.getMiningSkills() == 4){
                return 7;
            }
            else{
                return 8;
            }
        }
        else{
            if (player.getStrengthSkills() == 4){
                return 7;
            }
            else if (player.getEngineeringSkills() == 4){
                return 8;
            }
            else if(player.getMiningSkills() == 4){
                return 9;
            }
            else{
                return 10;
            }
        }
    }
    public void setDays(int days){
        this.days = days;
    }
    public int getDays(){
        return days;
    }
    public void setImage(Image player){
        playerFace = player;
    }
    public Image getImage(){
        return playerFace;
    }
    public void setImageNull(){
        playerFace = null;
    }
    public int getIsPressed(){//установить кто из персонажей выбран
        return isPressed;
    }
    public void setWhatBisy(String name){//установить какйо уровень выбран
        this.whatLevel = name;
    }
    public String getWhatBisy(){//получить какой уровень задействован
        return whatLevel;
    }
    public void setBusy (Boolean check){
        isBusy = check;
    }
    public Boolean getIsBusy(){
        return isBusy;
    }
    public void setMinusDay(){
        days--;
    }
    public void setWhoBisy(String name){
        whoBisy = name;
    }
    public String getWhoBisy(){
        return whoBisy;
    }
    public void save(){
        parent.getOutingBuildings().putString(WHOBISY, whoBisy);
        parent.getOutingBuildings().putInteger(DAYS, days);
        parent.getOutingBuildings().putBoolean(ISBUSY, isBusy);
        parent.getOutingBuildings().putString(WHATLEVEL, whatLevel);
        parent.getOutingBuildings().putBoolean(STATEOUTING, stateOuting);
        parent.getOutingBuildings().putBoolean(ISGUN, isGun);
        parent.getOutingBuildings().putBoolean(ISSHOVEL, isShovel);
        parent.getOutingBuildings().flush();
    }
    public void load(){
        whoBisy = parent.getOutingBuildings().getString(WHOBISY);
        days = parent.getOutingBuildings().getInteger(DAYS);
        isBusy = parent.getOutingBuildings().getBoolean(ISBUSY);
        whatLevel = parent.getOutingBuildings().getString(WHATLEVEL);
        stateOuting = parent.getOutingBuildings().getBoolean(STATEOUTING);
        isGun = parent.getOutingBuildings().getBoolean(ISGUN);
        isShovel = parent.getOutingBuildings().getBoolean(ISSHOVEL);
        if (isBusy) {
            playerFace = new Image(A2DAssetManager.manager.get("Image/players/" + whoBisy + "S.png", Texture.class));
        }
    }

    public void setOuting(boolean check){
        stateOuting = check;
    }
    public Boolean getOuting(){
        return stateOuting;
    }
    public void setIsGun(boolean check){
        isGun = check;
    }
    public void setIsShowel(boolean check){
        isShovel = check;
    }
    public boolean getIsGun(){//получить, выбран ли пистолет
        return isGun;
    }
    public boolean getIsShovel(){//получить, выбрана ли лопата
        return isShovel;
    }
}
