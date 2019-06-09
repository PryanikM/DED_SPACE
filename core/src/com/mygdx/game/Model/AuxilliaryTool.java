package com.mygdx.game.Model;

public class AuxilliaryTool {
    private int health;// кол-во жизней
    private String name = null;//название предмета

    public AuxilliaryTool(int health, String name) {
        this.health = health;
        this.name = name;
    }
    public int getHealth(){
        return health;
    }
    public void setMinusHealth(){
        health--;
    }
    public String getName(){
        return name;
    }
}
