package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Loader.A2DAssetManager;

import java.util.ArrayList;

public class Building {

    private int max_health;//максимальное количество жизней здания
    private int days;//количество дней работы
    private String whoWork;//кто работает
    private int health;//текущее кол-во жизней
    private float x;//позиция здания x
    private float y;//позиция здания y
    private float timeFrame;// время между отрисовки кадра анимации
    private float maxTimeStopFrame;//максимальное время между отрисовки кадра анимации
    private int frame;// текущий кадр
    private float height;//высота sprite здания
    private float widht;//ширина sprite здания
//    private Sprite building;
    private Sprite broken;//sprite сломанного здания
    private boolean isBusy;//занято ли здание
    private ArrayList<Sprite> animation;// массив кадров анимации работы
    private String link;//ссылка на картинку здания
    private String resourse;//какие ресурсы производятся в этом здание
    private Boolean repair;//ремонтируется ли здание
    public Building(int max_health,
                    int days,
                    String whoWork,
                    float x,
                    float y,
                    String link,
                    String resourse,
                    int health,
                    boolean busy,
                    float height,
                    float widht,
                    Boolean repair
    )
    {
        animation = new ArrayList<Sprite>();
        this.max_health = max_health;
        this.days = days;
        this.whoWork = whoWork;
        this.x = x;
        this.y = y;
        this.link = link;
        this.health = health;
        this.resourse = resourse;
        this.isBusy = busy;
        this.height = height;
        this.widht = widht;
        timeFrame = 0.0f;
        broken = new Sprite(A2DAssetManager.manager.get("Image/destroyedBuildings/" + resourse + "BrokenImage.png", Texture.class));
        if (resourse.equals("food")) {
            broken.setSize(widht / 1.44f, height / 1.44f);
        }
        else{
            broken.setSize(widht, height);
        }
        maxTimeStopFrame = 0.1f;
        this.repair = repair;
        makeArray();
    }
    public String getLink() {
        return link;
    }
    public String getWhoWork(){
        return whoWork;
    }
    public int getHealth() {
        return health;
    }
    public int getMax_health() {
        return max_health;
    }
    public int getDays(){
        return days;
    }
    public String getResourse() {
        return resourse;
    }
    public void setMax_health(){
        health = max_health;
    }
    private void makeArray(){//подготовка массива, для харния кадров работы здания
        for (int i = 0; i < 8; i++){
            animation.add(new Sprite(A2DAssetManager.manager.get("Image/" + resourse + "Animation/" + resourse + i + ".png", Texture.class)));
            animation.get(i).setSize(widht, height);
        }
    }
    public boolean getIsBusy(){
        return isBusy;
    }
    public Sprite playAnimation(float time){
        if (isBusy && !repair && health > 0) {
            if (timeFrame >= maxTimeStopFrame) {
                timeFrame = 0.0f;
                if (frame == animation.size() - 1) {
                    frame = 0;
                } else {
                    frame++;
                }
            }
            else {
                timeFrame += time;
            }
            return animation.get(frame);
        }
        else{
            if (health == 0){
                return broken;
            }
            else {
                return animation.get(0);
            }
        }
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return  y;
    }
    public float getWdith(){
        return widht;
    }
    public float getHeight(){
        return height;
    }
    public void setBusy(boolean check){
        isBusy = check;
    }
    public void setMinusHealth(){
        health--;
    }
    public void setRepair(boolean repair){
        this.repair = repair;
    }
    public boolean getRepair(){
        return repair;
    }

}
