package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Loader.A2DAssetManager;

import java.util.ArrayList;

public class Robot {
    private int dayNow;//какой день сейчас
    private int day;//текущий день отсутствия робота
    private ArrayList<Integer> things;//массив вещей
    private float x;//позиция робота x
    private float y;//позиция роюота y
    private float width;//ширина sptite робота
    private float height;//высота sptite робота
    private int dayStop;//время нахождения робота
    private int what;//текущая вещь
    private int dayStopNow;//текущий день присутствия робота
    private int random;//число для хранения случайного числа
    private int randomC;//число для хранения случайного кол-во вещей
    private int last;//последняя вещь
    private boolean givePresent;
    private Sprite robotSprite;//sprite робота
    private TiledMapTileLayer layer;
//    private float k;//константа экарана
    private boolean isDraw;//нарисован ли
    public final static String DAYNOW = "DAYNOW";//константа для сохрванения
    public final static String DAY = "DAY";//константа для сохрванения
    public final static String DAYSTOP = "DAYSTOP";//константа для сохрванения
    public final static String DAYSTOPNOW = "DAYSTOPNOW";//константа для сохрванения
    public final static String WHAT = "WHAT";//константа для сохрванения

    //константы для сохрванения
    private final static String T0 = "T0";
    private final static String T1 = "T1";
    private final static String T2 = "T2";
    private final static String T3 = "T3";
    private final static String T7 = "T7";


    private final static String ISDRAW = "ISDRAW";//константа для сохрванения
    private final static String LAST = "LAST";//константа для сохрванения
    private final static String RANDOMCOUNT = "RANDOMCOUNT";//константа для сохрванения
    private MyGdxGame paprent;

    public Robot(float k, TiledMapTileLayer layer, int day, boolean load, MyGdxGame parent) {
//        this.k = k;
        this.layer = layer;
        robotSprite = new Sprite(A2DAssetManager.manager.get(A2DAssetManager.robot, Texture.class));
        x = 23 * layer.getTileWidth() * k;
        y = -3 * layer.getTileHeight() * k;
        height = 4f * layer.getTileHeight() * k * 1.2f;
        width = 1f * layer.getTileWidth() * k * 1.2f;
        robotSprite.setBounds(x, y, width, height);
        dayNow = 0;
        this.day = day;
        dayStopNow = 0;
        dayStop = 2;
        isDraw = false;
        last = -2;
        things = new ArrayList<Integer>();
        what = -1;
        randomC = -1;
        givePresent = false;
        this.paprent = parent;
        if (!load) {
            makeArray();
        }
    }
    private void makeArray(){//подготовка массива для хрванения вещей
        things.add(0);
        things.add(1);
        things.add(2);
        things.add(3);
        things.add(7);
    }
    public int getDayNow(){
        return dayNow;
    }
    public int getDayStopNow(){
        return dayStopNow;
    }
    public int getDay(){
        return day;
    }
    public int getDayStop(){
        return dayStop;
    }
    public void setDayNow(int day){
        dayNow = dayNow;
    }
    public void setPlusDayNow(){
        dayNow++;
    }
    public void setPlusDayStopNow(){
        dayStopNow++;
    }
    public void setDayStopNow(int day){
        dayStopNow = day;
    }
    public boolean getIsDraw(){
        return isDraw;
    }
    public Sprite getSprite(){
        return robotSprite;
    }
    public void setIsDraw (boolean draw){
        isDraw = draw;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public int getWhat(){
        if (what == -1 && paprent.getGameScreen().getRobot().getThings().size() > 0) {
            random = (int) (Math.random() * things.size());
            what = things.get(random);
            if (things.size() > 1) {
                while (what == last) {
                    random = (int) (Math.random() * things.size());
                    what = things.get(random);
                }
            }
            last = what;
            return what;
        }
        else return what;
    }
    public void setWhatNull(){
        what = -1;
    }
    public int getRandom(){
        if (randomC == -1){
            randomC = (int) (6 + Math.random() * 15);
            return randomC;
        }
        else{
            return randomC;
        }
    }
    public void setNullRandomC(){
        randomC = -1;
    }
    public ArrayList<Integer> getThings(){
        return things;
    }
    public void save(){
        paprent.getRobotPref().putInteger(DAYNOW, dayNow);
        paprent.getRobotPref().putInteger(DAY, day);
        paprent.getRobotPref().putInteger(DAYSTOP, dayStop);
        paprent.getRobotPref().putInteger(DAYSTOPNOW, dayStopNow);
        paprent.getRobotPref().putInteger(WHAT, what);
        paprent.getRobotPref().putInteger(LAST, last);
        paprent.getRobotPref().putInteger(RANDOMCOUNT, randomC);
        paprent.getRobotPref().putBoolean(ISDRAW, isDraw);
        for (int i = 0; i < things.size(); i++){
            paprent.getRobotPref().putInteger("T" + i, things.get(i));
        }
        for (int i = things.size(); i < 5; i++){
            paprent.getRobotPref().putInteger("T" + i, -1);
        }
        paprent.getRobotPref().flush();
    }
    public void load(){
        dayNow = paprent.getRobotPref().getInteger(DAYNOW);
        day = paprent.getRobotPref().getInteger(DAY);
        dayStop = paprent.getRobotPref().getInteger(DAYSTOP);
        dayStopNow = paprent.getRobotPref().getInteger(DAYSTOPNOW);
        what = paprent.getRobotPref().getInteger(WHAT);
        last = paprent.getRobotPref().getInteger(LAST);
        randomC = paprent.getRobotPref().getInteger(RANDOMCOUNT);
        isDraw = paprent.getRobotPref().getBoolean(ISDRAW);
        for (int i = 0; i < 5; i++){
            if (paprent.getRobotPref().getInteger("T" + i) != -1){
                things.add(paprent.getRobotPref().getInteger("T" + i));
            }
        }
    }
    public boolean getGivePresent(){
        return givePresent;
    }
    public void setGivePresent(boolean check){
        givePresent = check;
    }
}
