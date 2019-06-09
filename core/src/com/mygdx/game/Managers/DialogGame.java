package com.mygdx.game.Managers;

import java.util.ArrayList;

public class DialogGame {
    boolean check;
    private String name;//именование того, что выбрали
    public boolean minerButtonPressed;
    public boolean engineerButtonPressed;
    public boolean biologyButtonPressed;
    public boolean strengthButtonPressed;
    public boolean gunButtonPressed;
    private int weight;//текущий вес
    private int waterWeight;//сколько весит 1 вода
    private int foodWeight;//сколько весит 1 еда
    private int rockWeight;//сколько весит 1 камень
    private int water;//вес воды
    private int rock;//вес камня
    private int food;//вес еды
    private int putWeight;
    private int minerWeight;
    private int engineerWeight;
    private int biologyWeight;
    private int strengthWeight;
    private int MAX_WEIGHT;
    private int gunWeight;
    public ArrayList<String> players;//массив, который хранит выбранных игроков

    public DialogGame() {
        players = new ArrayList<String>();
        weight = 0;
        waterWeight = 1;
        foodWeight = 1;
        rockWeight = 2;
        water = 0;
        food = 0;
        rock = 0;
        putWeight = 0;
        minerWeight = 10;
        engineerWeight = 15;
        biologyWeight = 8;
        strengthWeight = 20;
        MAX_WEIGHT = 200;
        gunWeight = 5;
        minerButtonPressed = false;
        engineerButtonPressed = false;
        biologyButtonPressed = false;
        strengthButtonPressed = false;
        gunButtonPressed = false;
    }

    public int getPutWeight() {
        return putWeight;
    }
    public int getMinerWeight() {
        return minerWeight;
    }
    public void setPutWeight(int putWeight) {
        this.putWeight = putWeight;
    }
    public int getEngineerWeight() {
        return engineerWeight;
    }

    public int getBiologyWeight() {
        return biologyWeight;
    }

    public int getStrengthWeight() {
        return strengthWeight;
    }

    public int getMAX_WEIGHT() {
        return MAX_WEIGHT;
    }
    public int getGunWeight() {
        return gunWeight;
    }
    public void setName (String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public boolean getButtonPressed(){
        if (name.equals("Miner")){
            return minerButtonPressed;
        }
        if (name.equals("Engineer")){
            return engineerButtonPressed;
        }
        if (name.equals("Biology")){
            return biologyButtonPressed;
        }
        if (name.equals("Strength")){
            return strengthButtonPressed;
        }
        if (name.equals("Gun")){
            return gunButtonPressed;
        }
        return false;
    }
    public void setButtonPressed(){
        if (name.equals("Miner")){
            minerButtonPressed = !minerButtonPressed;
        }
        if (name.equals("Engineer")){
            engineerButtonPressed = !engineerButtonPressed;
        }
        if (name.equals("Biology")){
            biologyButtonPressed = !biologyButtonPressed;
        }
        if (name.equals("Strength")){
            strengthButtonPressed = !strengthButtonPressed;
        }
        if (name.equals("Gun")){
            gunButtonPressed = !gunButtonPressed;
        }
    }
    public void addPlayer(){
        players.add(name);
    }
    public void deletePlayer(){
        for (int i = 0; i < players.size(); i++){
            if (name.equals(players.get(i))){
                players.remove(i);
            }
        }
    }
    public int getRockWeight(){
        return rockWeight;
    }
    public int getWaterWeight(){
        return waterWeight;
    }
    public int getFoodWeight(){
        return foodWeight;
    }
    public void setMinusWeight(int Weight){
        weight -= Weight;
    }
    public void setPlusWeight(int Weight){
        weight += Weight;
    }
    public int getWeight(){
        return weight;
    }
    public boolean check(int Weight){//проверяет, можно ли добавить вес
        if (weight + Weight > MAX_WEIGHT){
            return false;
        }
        else {
            return true;
        }
    }
    public void setRock (int rock){//прибавляет вес
        this.rock += rock;
    }
    public void setWater(int water){//прибавляет вес
        this.water += water;
    }
    public int getWater(){
        return water;
    }
    public int getRock(){
        return rock;
    }
    public void setFood(int food){//прибавляет вес
        this.food += food;
    }
    public int getFood(){
        return food;
    }
    public void setFalse(){//обнуляет нажатые кнопки
        minerButtonPressed = false;
        engineerButtonPressed = false;
        biologyButtonPressed = false;
        strengthButtonPressed = false;
        gunButtonPressed = false;
    }
    public  void  setNull() {
        players.clear();
        minerButtonPressed = false;
        engineerButtonPressed = false;
        biologyButtonPressed = false;
        strengthButtonPressed = false;
        gunButtonPressed = false;
        water = 0;
        food = 0;
        rock = 0;
        putWeight = 0;
        weight = 0;
    }
}
