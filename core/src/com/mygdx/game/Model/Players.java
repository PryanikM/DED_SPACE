package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Players {
    private String name;// Имя игрока
//    public Sprite playerSprite;
    private float x;//позиция игрока x
    private float y;//позиция игрока Y
    private ArrayList<Integer> waterArray;//массив возможного выпадения количества воды
    private ArrayList<Integer> rockArray;//массив возможного выпадения количества камня
    private ArrayList<Integer> foodArray;//массив возможного выпадения количества еды
    private ArrayList<Integer> outEasy;//массив возможного количества потери жизней на легком уровне вылазки
    private ArrayList<Integer> outMedium;//массив возможного количества потери жизней на среднем уровне вылазки
    private ArrayList<Integer> outHard;//массив возможного количества потери жизней на сложном уровне вылазки
    private int max_health;//всего жизней персонажа
    private int health;//сколько сейчас жизней у персонажа
    private String face_link;//ссылка на картинку персонажа
    private int maxFrame;//всего кадров анимации
    private float height,width;//размеры sprite игрока
    private boolean isBusy;//занят ли персонаж
    private int maxDayWithoutFood;//максимальное количество дней, которое персонаж способен прожиьть без еды
    private int maxDayWithoutWater;//максимальное количество дней, которое персонаж способен прожиьть без воды
    private int dayWithoutWater;//количество дней, которые персонаж прожил без воды
    private int dayWithoutFood;//количесвто дней, которые персонаж прожил без еды
    private int engineeringSkills;//инженерные умения
    private int biologicalSkills;//биологические умения
    private int miningSkills;//умение покать персонажа
    private int strengthSkills;//сила персонажа
//    private Texture playerTexture;
    private int frame;//какой сейчас кадр
    private float time;//время,прошедшее с момента отрисовки последнего кадра
    private float maxTime;//максимальное время между кадрами
    private int frameRepair;//кадр отрисовки ремонта
    private float timeRepair;//время, прошедшее с моемнта отрисовки последнего кадра починки
    private float repairX;//позиция X, где происходит анимация постройки
    private float repairY;//позиция Y, где происходит анимация постройки
    private Sprite returnSprite;// возвращаемы sprite
    private Boolean repair;//ремонтирует ли сейчас игрок
    private int count1;//дополнительная переменная для заполнения массиввов
    private int count2;//дополнительная переменная для заполнения массиввов
    private int count3;//дополнительная переменная для заполнения массиввов
    private int count4;//дополнительная переменная для заполнения массиввов
    private int count5;//дополнительная переменная для заполнения массиввов
    private float maxTimeRepair;//максимальное время, которое проигрывается один кадр починки
    private String playerTextureT;//ссылка на текстуру персонажа (для панели)
    private ArrayList<Sprite> playerSprite;// массив, в котором хранятся sprite, для анимации
    private ArrayList<Sprite> repairSprite;// массив, в котором хранятся sprite, для анимации работы
    private ArrayList<Integer> easyThings;// массив, в котором хранятся вещи, которые могут выпасть на легком уровне вылазки
    private ArrayList<Integer> mediumThings;// массив, в котором хранятся вещи, которые могут выпасть на среднем уровне вылазки
    private ArrayList<Integer> hardThings;// массив, в котором хранятся вещи, которые могут выпасть на сложном уровне вылазки
    public Players(float x, float y, String name, float height, float width, String playerTexture, String face_link, int health, int maxDayWithoutFood, int maxDayWithoutWater, boolean isBusy, int engineeringSkills, int biologicalSkills, int miningSkills, int strengthSkills, int dayWithoutFood, int dayWithoutWater, int maxHealth, boolean isRepair, float repairX, float repairY, float maxTimeRepair, float maxTime, int maxFrame) {
        this.name = name;
        this.isBusy = isBusy;
        this.x = x;
        playerTextureT = playerTexture;
        waterArray = new ArrayList<Integer>(20);
        foodArray = new ArrayList<Integer>(20);
        rockArray = new ArrayList<Integer>(20);
        outEasy = new ArrayList<Integer>(20);
        outMedium = new ArrayList<Integer>(20);
        outHard = new ArrayList<Integer>(20);
        easyThings = new ArrayList<Integer>(20);
        mediumThings = new ArrayList<Integer>(20);
        hardThings = new ArrayList<Integer>(20);
        repairSprite = new ArrayList<Sprite>();
        this.y = y;
        frame = 0;
        time = 0;
        this.maxTime = maxTime;
        frameRepair = 0;
        timeRepair = 0;
        this.maxTimeRepair = maxTimeRepair;
        this.height = height;
        this.width = width;
        this.face_link = face_link;
        this.max_health = maxHealth;
        this.health = health;
        this.dayWithoutFood = dayWithoutFood;
        this.dayWithoutWater = dayWithoutWater;
        this.maxDayWithoutFood = maxDayWithoutFood;
        this.maxDayWithoutWater = maxDayWithoutWater;
        this.engineeringSkills = engineeringSkills;
        this.biologicalSkills = biologicalSkills;
        this.strengthSkills = strengthSkills;
        this.miningSkills = miningSkills;
        this.maxFrame = maxFrame;
        playerSprite = new ArrayList<Sprite>();
        this.repairX = repairX;
        this.repairY = repairY;
        repair = isRepair;
        Sprite temp = new Sprite(new Texture(playerTexture + "1.png"));
        temp.setSize(width, height);
        temp.setPosition(x, y);
        playerSprite.add(temp);
        Sprite temp1 = new Sprite(new Texture(playerTexture + "2.png"));
        temp1.setSize(width, height);
        temp1.setPosition(x, y);
        playerSprite.add(temp1);
//        makePlayerSprite();
        makeRepairSprite();
        makeArray();
    }
    public String getLinks(){
        return face_link;
    }
    public float getHeight(){
        return height;
    }
    public float getWidth(){
        return width;
    }
    public int getHealth() {
        return health;
    }
    public int getMax_health() {
        return max_health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setMinusHealth(int minusHealth){
        if (minusHealth >= 0) {
            this.health -= minusHealth;
        }
    }
    public void minusHealth(int health) {
        this.health -= health;
    }
    public int getMaxDayWithoutFood() {
        return maxDayWithoutFood;
    }

    public int getMaxDayWithoutWater() {
        return maxDayWithoutWater;
    }
    public int getDayWithoutWater() {
        return dayWithoutWater;
    }

    public void setPlusDayWithoutWater(int dayWithoutWater) {
        this.dayWithoutWater += dayWithoutWater;
    }

    public int getDayWithoutFood() {
        return dayWithoutFood;
    }
    public String getPlayerTextureT(){
        return playerTextureT;
    }
    public void setPlusDayWithoutFood(int dayWithoutFood) {
        this.dayWithoutFood += dayWithoutFood;
    }
    public void setZeroDayWithoutFood (){
        this.dayWithoutFood = 0;
    }
    public void setZeroDayWithoutWater (){
        this.dayWithoutWater = 0;
    }
    public boolean getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(boolean busy) {
        isBusy = busy;
    }
    public int getEngineeringSkills() {
        return engineeringSkills;
    }

    public int getBiologicalSkills() {
        return biologicalSkills;
    }

    public int getMiningSkills() {
        return miningSkills;
    }

    public int getStrengthSkills() {
        return strengthSkills;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public String getName(){
        return  name;
    }
    public Sprite animation(float delta){
        if (time >= maxTime) {
            time = 0.0f;
            if (frame == 1) {
                frame = 0;
            }
            else {
                frame++;
            }
        }
        else {
            time += delta;
        }
        return playerSprite.get(frame);
    }
    private void makeRepairSprite(){//подготовка кадров для работы персонажей
        for (int i = 0; i < maxFrame; i++){
            Sprite temp2 = new Sprite(new Texture(Gdx.files.internal("Image/repair" + name + "/" + i + ".png")));
            if (temp2.getWidth() > 500f){
                temp2.setSize(width * 1.3f, height);
                temp2.setPosition(x, y);
            }
            else {
                temp2.setSize(width * 0.9f, height);
                temp2.setPosition(x, y);
            }
            repairSprite.add(temp2);
        }
    }
    public Sprite repairAnimation(float delta){//проигрываение кадров для работы персонажей
        if (timeRepair >= maxTimeRepair) {
            timeRepair = 0.0f;
            if (frameRepair == repairSprite.size() - 1) {
                frameRepair = 0;
            }
            else {
                frameRepair++;
            }
        }
        else {
            timeRepair += delta;
        }
        returnSprite = repairSprite.get(frameRepair);
        returnSprite.setPosition(repairX, repairY);
        return returnSprite;
    }
//    public void makePlayerSprite(){
//
//    }
    public void setPositionForRepair(float xR, float yR){
        repairX = xR;
        repairY = yR;
    }
    public float getxR (){
        return repairX;
    }
    public float getyR (){
        return repairY;
    }
    public boolean getRepair(){
        return  repair;
    }
    public void setRepair(boolean repair){
        this.repair = repair;
    }
    public void makeArray(){//подбор параметров для того, чтобы осуществить случайный выбор количества ресурсов
        if (name.equals("Biology")){
            count1 = 1;
            count2 = 2;
            count3 = 4;
            count4 = 13;
        }
        if (name.equals("Miner")){
            count1 = 5;
            count2 = 9;
            count3 = 5;
            count4 = 1;
        }
        if (name.equals("Strength")){
            count1 = 14;
            count2 = 3;
            count3 = 2;
            count4 = 1;
        }
        if (name.equals("Engineer")){
            count1 = 6;
            count2 = 7;
            count3 = 6;
            count4 = 1;
        }
        for (int i = 0 ; i < count1; i++){
            waterArray.add(1);
        }
        for (int i = count1 ; i < count1 + count2; i++){
            waterArray.add(2);
        }
        for (int i = count1 + count2 ; i < count1 + count2 + count3; i++){
            waterArray.add(3);
        }
        for (int i = count1 + count2 + count3 ; i < count1 + count2 + count3 + count4; i++){
            waterArray.add(4);
        }
        if (name.equals("Biology")){
            count1 = 16;
            count2 = 2;
            count3 = 1;
            count4 = 1;
        }
        if (name.equals("Miner")){
            count1 = 1;
            count2 = 1;
            count3 = 2;
            count4 = 16;
        }
        if (name.equals("Strength")){
            count1 = 4;
            count2 = 10;
            count3 = 5;
            count4 = 1;
        }
        if (name.equals("Engineer")){
            count1 = 2;
            count2 = 8;
            count3 = 6;
            count4 = 4;
        }
        for (int i = 0 ; i < count1; i++){
            rockArray.add(1);
        }
        for (int i = count1 ; i < count1 + count2; i++){
            rockArray.add(2);
        }
        for (int i = count1 + count2 ; i < count1 + count2 + count3; i++){
            rockArray.add(3);
        }
        for (int i = count1 + count2 + count3 ; i < count1 + count2 + count3 + count4; i++){
            rockArray.add(4);
        }
        if (name.equals("Biology")){
            count1 = 1;
            count2 = 2;
            count3 = 3;
            count4 = 14;
        }
        if (name.equals("Miner")){
            count1 = 6;
            count2 = 7;
            count3 = 6;
            count4 = 1;
        }
        if (name.equals("Strength")){
            count1 = 14;
            count2 = 3;
            count3 = 2;
            count4 = 1;
        }
        if (name.equals("Engineer")){
            count1 = 5;
            count2 = 9;
            count3 = 5;
            count4 = 1;
        }
        for (int i = 0 ; i < count1; i++){
            foodArray.add(1);
        }
        for (int i = count1 ; i < count1 + count2; i++){
            foodArray.add(2);
        }
        for (int i = count1 + count2 ; i < count1 + count2 + count3; i++){
            foodArray.add(3);
        }
        for (int i = count1 + count2 + count3 ; i < count1 + count2 + count3 + count4; i++){
            foodArray.add(4);
        }
        if (name.equals("Biology")){
            count1 = 10;
            count2 = 10;
            count3 = 0;
            count4 = 0;
        }
        if (name.equals("Miner")){
            count1 = 12;
            count2 = 8;
            count3 = 0;
            count4 = 0;
        }
        if (name.equals("Strength")){
            count1 = 14;
            count2 = 6;
            count3 = 0;
            count4 = 0;
        }
        if (name.equals("Engineer")){
            count1 = 13;
            count2 = 7;
            count3 = 0;
            count4 = 0;
        }
        for (int i = 0 ; i < count1; i++){
            outEasy.add(0);
        }
        for (int i = count1 ; i < count1 + count2; i++){
            outEasy.add(1);
        }
        for (int i = count1 + count2 ; i <  count1 + count2 + count3; i++){
            outEasy.add(2);
        }
        for (int i = count1 + count2 + count3 ; i < count1 + count2 + count3 + count4; i++){
            outEasy.add(3);
        }
        if (name.equals("Biology")){
            count1 = 0;
            count2 = 7;
            count3 = 13;
            count4 = 0;
        }
        if (name.equals("Miner")){
            count1 = 3;
            count2 = 10;
            count3 = 6;
            count4 = 1;
        }
        if (name.equals("Strength")){
            count1 = 3;
            count2 = 13;
            count3 = 4;
            count4 = 0;
        }
        if (name.equals("Engineer")){
            count1 = 3;
            count2 = 10;
            count3 = 6;
            count4 = 1;
        }
        for (int i = 0 ; i < count1; i++){
            outMedium.add(0);
        }
        for (int i = count1 ; i < count1 + count2; i++){
            outMedium.add(1);
        }
        for (int i = count1 + count2 ; i < count1 + count2 + count3; i++){
            outMedium.add(2);
        }
        for (int i = count1 + count2 + count3 ; i < count1 + count2 + count3 + count4; i++){
            outMedium.add(3);
        }
        if (name.equals("Biology")){
            count1 = 0;
            count2 = 1;
            count3 = 4;
            count4 = 15;
            count5 = 0;
        }
        if (name.equals("Miner")){
            count1 = 0;
            count2 = 1;
            count3 = 5;
            count4 = 12;
            count5 = 2;
        }
        if (name.equals("Strength")){
            count1 = 1;
            count2 = 2;
            count3 = 8;
            count4 = 9;
            count5 = 0;
        }
        if (name.equals("Engineer")){
            count1 = 0;
            count2 = 1;
            count3 = 5;
            count4 = 12;
            count5 = 2;
        }
        for (int i = 0 ; i < count1; i++){
            outHard.add(0);
        }
        for (int i = count1 ; i < count1 + count2; i++){
            outHard.add(1);
        }
        for (int i = count1 + count2 ; i < count1 + count2 + count3; i++){
            outHard.add(2);
        }
        for (int i = count1 + count2 + count3 ; i < count1 + count2 + count3 + count4; i++){
            outHard.add(3);
        }
        for (int i = count1 + count2 + count3 + count4; i < count1 + count2 + count3 + count4 + count5; i++){
            outHard.add(4);
        }
        if (name.equals("Biology")){
            count1 = 8;
            count2 = 8;
            count3 = 4;
        }
        if (name.equals("Miner")){
            count1 = 4;
            count2 = 6;
            count3 = 10;
        }
        if (name.equals("Strength")){
            count1 = 7;
            count2 = 7;
            count3 = 6;
        }
        if (name.equals("Engineer")){
            count1 = 5;
            count2 = 7;
            count3 = 8;
        }
        for (int i = 0; i < count1; i++){
            easyThings.add(0);
        }
        for (int i = count1; i < count1 + count2; i++){
            easyThings.add(1);
        }
        for (int i = count1 + count2; i < count1 + count2 + count3; i++){
            easyThings.add(2);
        }
        if (name.equals("Biology")){
            count1 = 10;
            count2 = 9;
            count3 = 1;
        }
        if (name.equals("Miner")){
            count1 = 9;
            count2 = 9;
            count3 = 2;
        }
        if (name.equals("Strength")){
            count1 = 8;
            count2 = 8;
            count3 = 4;
        }
        if (name.equals("Engineer")){
            count1 = 9;
            count2 = 9;
            count3 = 2;
        }
        for (int i = 0; i < count1; i++){
            mediumThings.add(1);
        }
        for (int i = count1; i < count1 + count2; i++){
            mediumThings.add(0);
        }
        for (int i = count1 + count2; i < count1 + count2 + count3; i++){
            mediumThings.add(3);
        }
        if (name.equals("Biology")){
            count1 = 19;
            count2 = 1;
            count3 = 0;
        }
        if (name.equals("Miner")){
            count1 = 17;
            count2 = 2;
            count3 = 1;
        }
        if (name.equals("Strength")){
            count1 = 11;
            count2 = 5;
            count3 = 4;
        }
        if (name.equals("Engineer")){
            count1 = 16;
            count2 = 3;
            count3 = 1;
        }
        for (int i = 0; i < count1; i++){
            hardThings.add(2);
        }
        for (int i = count1; i < count1 + count2; i++){
            hardThings.add(3);
        }
        for (int i = count1 + count2; i < count1 + count2 + count3; i++){
            hardThings.add(4);
        }
    }
    public ArrayList getWaterArray(){
        return waterArray;
    }
    public ArrayList getFoodArray(){
        return foodArray;
    }
    public ArrayList getRockArray(){
        return rockArray;
    }
    public ArrayList getOutEasyArray(){
        return outEasy;
    }
    public ArrayList getOutMediumArray(){
        return outMedium;
    }
    public ArrayList getOutHardArray(){
        return outHard;
    }
    public ArrayList<Integer> getEasyThings(){
        return easyThings;
    }
    public ArrayList<Integer> getMediumThings(){
        return mediumThings;
    }
    public ArrayList<Integer> getHardThings(){
        return hardThings;
    }
    public float getTime(){
        return maxTime;
    }
    public float getTimeRepair(){
        return maxTimeRepair;
    }
    public int getMaxFrame(){
        return maxFrame;
    }



}
