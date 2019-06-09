package com.mygdx.game.Managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Views.MainGame.GameMainScreen;

public class PlayerStateManager {
    private GameMainScreen main;
    private GameMainManager manager;
    public PlayerStateManager(GameMainScreen main) {
        this.main = main;
        this.manager = main.getMainManager();
    }

    public int feed(int pos) {//покормить персонажа
        if (manager.getFood() - 1 >= 0 && main.getPlayers().get(pos).getDayWithoutFood() != 0) {
            manager.setMinusFood(1);
            main.getPlayers().get(pos).setZeroDayWithoutFood();
            main.parent.getPlayerState().updateFood();
            return main.getPlayers().get(pos).getMaxDayWithoutFood();
        }
        else return main.getPlayers().get(pos).getMaxDayWithoutFood() - main.getPlayers().get(pos).getDayWithoutFood();
    }
    public int water(int pos) {//напоить персонажа
        if (manager.getWater() - 1 >= 0 && main.getPlayers().get(pos).getDayWithoutWater() != 0) {
            manager.setMinusWater(1);
            main.getPlayers().get(pos).setZeroDayWithoutWater();
            main.parent.getPlayerState().updateWater();
            return main.getPlayers().get(pos).getMaxDayWithoutWater();
        }
        else return main.getPlayers().get(pos).getMaxDayWithoutWater() - main.getPlayers().get(pos).getDayWithoutWater();
    }
    public String setStateFood(int pos){//возвращает текст, характеризующий состояние персонажа (голод)
        if (main.getPlayers().get(pos).getMaxDayWithoutFood() - main.getPlayers().get(pos).getDayWithoutFood() == 1){
            return "Fatally hungry";
        }
        else if (main.getPlayers().get(pos).getMaxDayWithoutFood() - main.getPlayers().get(pos).getDayWithoutFood() == main.getPlayers().get(pos).getMaxDayWithoutFood()){
            return "Full";
        }
        else return "Hungry";
    }
    public String setStateWater(int pos){//возвращает текст, характеризующий состояние персонажа (жажда)
        if (main.getPlayers().get(pos).getMaxDayWithoutWater() - main.getPlayers().get(pos).getDayWithoutWater() == 1){
            return "Dehydration";
        }
        else if (main.getPlayers().get(pos).getMaxDayWithoutWater() - main.getPlayers().get(pos).getDayWithoutWater() == main.getPlayers().get(pos).getMaxDayWithoutWater()){
            return "Watered up";
        }
        else return "Water scarcity";
    }
}
