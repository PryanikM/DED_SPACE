package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Model.Players;

public class RandomManager {
    private int random;//переменная для сохранения рандомного числа
    private int check;
    private int skills;//хранит в себе определленный скилл игрока
    private boolean checkShovel;
    private GameMainManager manager;
    private int ogr;//ограничение дней, для выпадения предмета, нужного для постройки финальной цели
    public RandomManager(GameMainManager manager) {
        this.manager = manager;
    }

    public void generateFoodFactoryRandom(Players player){//рандомится количество еды, которое произведено на заводе
        random = (int) (Math.random() * 20);
        manager.setPlusFood((Integer) player.getFoodArray().get(random));
    }
    public void generateWaterFactoryRandom(Players player){//рандомится количество воды, которое произведено на заводе
        random = (int) (Math.random() * 20);
        manager.setPlusWater((Integer) player.getWaterArray().get(random));
    }
    public void generateRockFactoryRandom(Players player){//рандомится количество камня, которое произведено на заводе
        random = (int) (Math.random() * 20);
        manager.setPlusRock((Integer) player.getRockArray().get(random));
    }
    public void setMinusHealthPlayerOut(Players player, String level, boolean isGun){//рандомится кол-во жизней, которое отнимется у игрока после вылазки
        random = (int) (Math.random() * 20);
        if (level.equals("easy")){
            if (isGun) {
                player.setMinusHealth((Integer) player.getOutEasyArray().get(random) - 1);
            }
            else{
                player.setMinusHealth((Integer) player.getOutEasyArray().get(random));
            }
        }
        if (level.equals("medium")){
            if (isGun){
                player.setMinusHealth((Integer) player.getOutMediumArray().get(random) - 1);
            }
            else{
                player.setMinusHealth((Integer) player.getOutMediumArray().get(random));
            }
        }
        if (level.equals("hard")){
            if (isGun){
                player.setMinusHealth((Integer) player.getOutHardArray().get(random) - 1);
            }
            else{
                player.setMinusHealth((Integer) player.getOutHardArray().get(random));
            }
        }
    }
    public void setThings(String level,  Players player, boolean checkShovel){//рандомится предмет, который выпадет после вылазки
        random = (int) (Math.random() * 20);
        skills = player.getStrengthSkills();
        this.checkShovel =  checkShovel;
        if (level.equals("easy")) {
            int plusR = 0;
            if (checkShovel){
                plusR = 1;
            }
            check = player.getEasyThings().get(random);
            if (check != 2) {
                if (skills == 4) {
                    random = (int) ((2 + plusR) + Math.random() * (skills + 2 + plusR));
                }
                if (skills == 3) {
                    random = (int) ((2 + plusR) + Math.random() * (skills + 2 + plusR));
                }
                if (skills == 2) {
                    random = (int) ((1 + plusR) + Math.random() * (skills + 1 + plusR));
                }
                if (skills == 1) {
                    random = (int) ((0 + plusR) + Math.random() * (skills + 2 + plusR));
                }
                if (check == 0) {
                    manager.setPlusFood(random);
                }
                else if (check == 1) {
                    manager.setPlusWater(random);
                }
            }
            else{
                if (skills == 4) {
                    random = (int) ((3  + plusR) + Math.random() * (skills + 1 + plusR));
                }
                if (skills == 3) {
                    random = (int) ((2 + plusR) + Math.random() * (skills + 1 + plusR));
                }
                if (skills == 2) {
                    random = (int) ((1 + plusR) + Math.random() * (skills + 2 + plusR));
                }
                if (skills == 1) {
                    random = (int) ((1 + plusR) + Math.random() * (skills + 1 + plusR));
                }
                manager.setPlusRock(random);
            }

        }
        if (level.equals("medium")){
            check = player.getMediumThings().get(random);
            skills = player.getStrengthSkills();
            int plusR = 0;
            if (checkShovel){
                plusR = 1;
            }
            while (manager.getThings().indexOf(check) != -1){
                random = (int) (Math.random() * 20);
                check = player.getMediumThings().get(random);
            }
            if (check != 3){
                if (skills == 4) {
                    random = (int) ((3 + plusR) + Math.random() * (skills + 3 + plusR));
                }
                if (skills == 3) {
                    random = (int) ((3 + plusR) + Math.random() * (skills + 3 + plusR));
                }
                if (skills == 2) {
                    random = (int) ((2 + plusR) + Math.random() * (skills + 4 + plusR));
                }
                if (skills == 1) {
                    random = (int) ((1 + plusR) + Math.random() * (skills + 3 + plusR));
                }
                if (check == 0){
                    manager.setPlusWater(random);
                }
                if (check == 1){
                    manager.setPlusFood(random);
                }
            }
            if (check == 3){
                manager.getThings().add(3);
            }

        }
        if (level.equals("hard")){
            check = player.getHardThings().get(random);
            skills = player.getStrengthSkills();
            int plusR = 0;
            if (checkShovel){
                plusR = 1;
                ogr = 2;
            }
            else{
                ogr = 3;
            }
            boolean check1;
            if (manager.getThings().indexOf(check) != -1) {
                check1 = true;
            }
            else{
                check1 = false;
            }
            while (check1){
                random = (int) (Math.random() * 20);
                check = player.getMediumThings().get(random);
                if (manager.getThings().indexOf(check) == -1) {
                    if (check == 4) {
                        if (manager.getCheckdayhard() >= ogr) {
                            check1 = false;
                        }
                    }
                    else{
                        check1 = false;
                    }
                }
            }
            if (check == 2){
                if (skills == 4) {
                    random = (int) ((1 + plusR) + Math.random() * (skills + 2 + plusR));
                }
                if (skills == 3) {
                    random = (int) ((1 + plusR) + Math.random() * (skills + 1 + plusR));
                }
                if (skills == 2) {
                    random = (int) ((1 + plusR) + Math.random() * (skills + 1 + plusR));
                }
                if (skills == 1) {
                    random = (int) ((1 + plusR) + Math.random() * (skills + 2 + plusR));
                }
                manager.setPlusRock(random);
            }
            if (check == 3){
                manager.getThings().add(3);
            }
            if (check == 4){
                manager.getThings().add(4);
            }

        }

    }
}
