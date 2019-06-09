package com.mygdx.game.Managers;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Model.AuxilliaryTool;
import com.mygdx.game.Model.Building;
import com.mygdx.game.Views.MainGame.GameMainScreen;

public class NextDayManager implements Runnable{
    private GameMainScreen main;
    private GameMainManager gameManager;
    private MyGdxGame parent;
    public NextDayManager(GameMainScreen main, GameMainManager gameMain, MyGdxGame parent) {
        this.parent = parent;
        this.main = main;
        this.gameManager = gameMain;


    }
    @Override
    public void run(){
        Gdx.app.postRunnable(new Runnable() {//поток, в котором происходит обновление и сохранение переменных
            @Override
            public void run() {
                gameManager.setPlusDay();
                main.getHud().updateCountDay();
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (!main.getPlayers().get(i).getIsBusy()) {
                        if (main.getPlayers().get(i).getDayWithoutFood() + 1 <= main.getPlayers().get(i).getMaxDayWithoutFood() && main.getPlayers().get(i).getDayWithoutWater() + 1 <= main.getPlayers().get(i).getMaxDayWithoutWater()) {
                            main.getPlayers().get(i).setPlusDayWithoutFood(1);
                            main.getPlayers().get(i).setPlusDayWithoutWater(1);
                        }
                        else if ((main.getPlayers().get(i).getDayWithoutFood() == main.getPlayers().get(i).getMaxDayWithoutFood() || main.getPlayers().get(i).getDayWithoutWater() == main.getPlayers().get(i).getMaxDayWithoutWater())){
                            if (main.getPlayers().get(i).getDayWithoutFood() == main.getPlayers().get(i).getMaxDayWithoutFood() && main.getPlayers().get(i).getDayWithoutWater() == main.getPlayers().get(i).getMaxDayWithoutWater()){
                                main.getPlayers().get(i).setMinusHealth(2);
                            }
                            else{
                                main.getPlayers().get(i).setMinusHealth(1);
                            }
                            if (main.getPlayers().get(i).getHealth() <= 0){
                                main.minusPlayer(i);
                                i--;
                            }
                        }
                    }
                }
                    setRobot();
                    setMinusDayBuildings();
                    setMinusDayBuildingsWork();
                    setMinusDayForRepairBuildings();
                    saveBuilding();
                    saveTombstone();
                    savePlayers();
                    saveBuildingsWork();
                    saveRepairBuilding();
                    saveOutingManager();
                    saveInv();
                    saveRobot();
                    saveThings();
                    saveOptions();
                }
        });
    }
    private void savePlayers(){//сохранение переменных
        main.getMainManager().savePlayer();
    }
    private void saveTombstone(){//сохранение переменных
        main.getMainManager().saveTombstone();
    }
    private void saveBuilding(){//сохранение переменных
        main.getMainManager().saveBuildings();
    }
    private void saveOptions(){//сохранение переменных
        parent.getBuildingBuildScreen().getManager().savePreferences();
        gameManager.savePreferences();
        main.parent.getPreferencesLoad().flush();
    }
    private void setMinusDayBuildingsWork(){//обновление переменных
       if (parent.getBuildingsMakeNoScreen().getManager().getisFoodIsBusy()){
           if (parent.getBuildingsMakeNoScreen().getManager().getFoodFactoryWorkDay() > 1){
               parent.getBuildingsMakeNoScreen().getManager().setMinusDayFoodFactory();
           }
           else{
               parent.getBuildingsMakeNoScreen().getManager().setMinusDayFoodFactory();
               for (int i = 0; i < main.getPlayers().size(); i++){
                   if (main.getPlayers().get(i).getName().equals(parent.getBuildingsMakeNoScreen().getManager().getWhoFood())){
                       main.getPlayers().get(i).setIsBusy(false);
                       parent.getBuildingsMakeNoScreen().getManager().setFoodBusyFalse();
                       main.getMainManager().getRandom().generateFoodFactoryRandom(main.getPlayers().get(i));
                       main.updateFood();
//                       main.updateFood();
                   }
               }
           }
       }
       if (parent.getBuildingsMakeNoScreen().getManager().getisWaterIsBusy()){
           if (parent.getBuildingsMakeNoScreen().getManager().getWaterFactoryWorkDay() > 1){
               parent.getBuildingsMakeNoScreen().getManager().setMinusDayWaterFactory();
           }
           else{
               parent.getBuildingsMakeNoScreen().getManager().setMinusDayWaterFactory();
               for (int i = 0; i < main.getPlayers().size(); i++){
                   if (main.getPlayers().get(i).getName().equals(parent.getBuildingsMakeNoScreen().getManager().getWhoWater())){
                       main.getPlayers().get(i).setIsBusy(false);
                       parent.getBuildingsMakeNoScreen().getManager().setWaterBusyFalse();
                       main.getMainManager().getRandom().generateWaterFactoryRandom(main.getPlayers().get(i));
                       main.updateWater();
                   }
               }
           }
       }
        if (parent.getBuildingsMakeNoScreen().getManager().getisRockIsBusy()){
            if (parent.getBuildingsMakeNoScreen().getManager().getRockFactoryWorkDay() > 1){
                parent.getBuildingsMakeNoScreen().getManager().setMinusDayRockFactory();
            }
            else{
                parent.getBuildingsMakeNoScreen().getManager().setMinusDayRockFactory();
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(parent.getBuildingsMakeNoScreen().getManager().getWhoRock())){
                        main.getPlayers().get(i).setIsBusy(false);
                        parent.getBuildingsMakeNoScreen().getManager().setRockBusyFalse();
                        main.getMainManager().getRandom().generateRockFactoryRandom(main.getPlayers().get(i));
                        main.updateRock();
                    }
                }
            }
        }
    }
    private void setRobot(){//обновление переменных
        if (main.getRobot().getThings().size() > 0) {
            if (!main.getRobot().getIsDraw()) {
                main.getRobot().setPlusDayNow();
                if (main.getRobot().getDayNow() >= main.getRobot().getDay()) {
                    main.getRobot().setIsDraw(true);
                    main.getRobot().setDayNow(0);
                }
            } else {
                main.getRobot().setPlusDayStopNow();
                if (main.getRobot().getDayStopNow() >= main.getRobot().getDayStop()) {
                    main.getRobot().setDayStopNow(0);
                    main.getRobot().setIsDraw(false);
                    main.getRobot().setWhatNull();
                    main.getRobot().setNullRandomC();
                }
            }
        }
        else{
            if (main.getRobot().getGivePresent()){
                main.getRobot().setIsDraw(false);
                main.getRobot().setGivePresent(false);
                gameManager.getThings().add(6);
            }
        }
    }
    private void setMinusDayBuildings(){//обновление перемпенных
        if (!main.parent.getBuildingBuildScreen().getManager().getWhoWater().equals("null")){
            if (main.parent.getBuildingBuildScreen().getManager().getWaterFacoryTimeBisy() > 1){
                main.parent.getBuildingBuildScreen().getManager().setWaterFacoryMinusTimeBisy();
            }
            else{
                main.parent.getBuildingBuildScreen().getManager().setWaterFacoryMinusTimeBisy();
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(main.parent.getBuildingBuildScreen().getManager().getWhoWater())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getPlayers().get(i).setRepair(false);
                        main.parent.getBuildingBuildScreen().getManager().setWaterIsBusyFalse();
                        main.parent.getBuildingBuildScreen().getManager().plusWaterFactory();
                        if (main.parent.getBuildingBuildScreen().getManager().getWaterFactory() == 3) {
                            main.getMainManager().addBuildings(new Building(3,
                                    0,
                                    main.parent.getPreferencesLoad().getString(BuildingManager.PREFERENCES_WHOWATER),
                                    main.getLayer().getWidth() * 40 * parent.k * 1.2f,
                                    main.getLayer().getHeight() * -3 * parent.k * 1.2f,
                                    "Image/factory/waterFactory.png",
                                    "water",
                                    3,
                                    false,
                                    main.getLayer().getHeight() * parent.k * 1.2f * 6,
                                    main.getLayer().getWidth() * parent.k * 1.2f * 7,
                                    false
                                    ));
                            main.getHud().updateBuildingCheck();
                        }
                    }
                }
                main.parent.getBuildingBuildScreen().getManager().setNullWhoWater();
            }
        }
        if (!main.parent.getBuildingBuildScreen().getManager().getWhoFood().equals("null")){
            if (main.parent.getBuildingBuildScreen().getManager().getFoodFacoryTimeBisy() > 1){
                main.parent.getBuildingBuildScreen().getManager().setFoodFacoryTimeBisy();
            }
            else{
                main.parent.getBuildingBuildScreen().getManager().setFoodFacoryTimeBisy();
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(main.parent.getBuildingBuildScreen().getManager().getWhoFood())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getPlayers().get(i).setRepair(false);
                        main.parent.getBuildingBuildScreen().getManager().setFoodIsBusyFalse();
                        main.parent.getBuildingBuildScreen().getManager().plusFoodFactory();
                        if (main.parent.getBuildingBuildScreen().getManager().getFoodFactory() == 3) {
                            main.getHud().updateBuildingCheck();
                            main.getMainManager().addBuildings(new Building( 3,
                                    0,
                                    main.parent.getPreferencesLoad().getString(BuildingManager.PREFERENCES_WHOFOOD),
                                    main.getLayer().getWidth() * 25 * parent.k * 1.2f,
                                    main.getLayer().getHeight() * -10 * parent.k * 1.2f,
                                    "Image/factory/foodFactory.png",
                                    "food",
                                    3,
                                    false,
                                    main.getLayer().getHeight() * parent.k * 1.2f * 6,
                                    main.getLayer().getWidth() * parent.k * 1.2f * 7,
                                    false
                            ));
                            main.getHud().updateBuildingCheck();
                        }
                    }
                }
                main.parent.getBuildingBuildScreen().getManager().setNullWhoFood();
            }
        }
        if (!main.parent.getBuildingBuildScreen().getManager().getWhoRock().equals("null")){
            if (main.parent.getBuildingBuildScreen().getManager().getRockFacoryTimeBisy() > 1){
                main.parent.getBuildingBuildScreen().getManager().setRockFacoryTimeBisy();
            }
            else{
                main.parent.getBuildingBuildScreen().getManager().setRockFacoryTimeBisy();
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(main.parent.getBuildingBuildScreen().getManager().getWhoRock())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getPlayers().get(i).setRepair(false);
                        main.parent.getBuildingBuildScreen().getManager().setRockIsBusyFalse();
                        main.parent.getBuildingBuildScreen().getManager().plusRockFactory();
                        if (main.parent.getBuildingBuildScreen().getManager().getRockFactory() == 3) {
                            main.getHud().updateBuildingCheck();
                            main.getMainManager().addBuildings(new Building( 3,
                                    0,
                                    main.parent.getPreferencesLoad().getString(BuildingManager.PREFERENCES_WHOROCK),
                                    main.getLayer().getWidth() * 37 * parent.k * 1.2f,
                                    main.getLayer().getHeight() * 3 * parent.k * 1.2f,
                                    "Image/factory/rockFactory.png", "rock",
                                    3,
                                    false,
                                    main.getLayer().getHeight() * parent.k * 1.2f * 12,
                                    main.getLayer().getWidth() * parent.k * 1.2f * 9,
                                    false
                            ));
                            main.getHud().updateBuildingCheck();
                        }
                    }
                }
                main.parent.getBuildingBuildScreen().getManager().setNullWhoRock();
            }
        }
        if (parent.getOutingNotBusyScreen().getManager().getIsBusy()){
            if(parent.getOutingNotBusyScreen().getManager().getDays() > 1) {
                parent.getOutingNotBusyScreen().getManager().setMinusDay();
            }
            else{
                parent.getOutingNotBusyScreen().getManager().setMinusDay();
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(parent.getOutingNotBusyScreen().getManager().getWhoBisy())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getMainManager().getRandom().setMinusHealthPlayerOut(main.getPlayers().get(i), parent.getOutingNotBusyScreen().getManager().getWhatBisy(), parent.getOutingNotBusyScreen().getManager().getIsGun());
                        main.getMainManager().getRandom().setThings(parent.getOutingNotBusyScreen().getManager().getWhatBisy(), main.getPlayers().get(i), parent.getOutingNotBusyScreen().getManager().getIsShovel());
                        if (parent.getOutingNotBusyScreen().getManager().getIsGun()){
                            for (int k = 0; k < main.getMainManager().getInv().size(); k++){
                                if (main.getMainManager().getInv().get(k).getName().equals("Gun")){
                                    main.getMainManager().getInv().get(k).setMinusHealth();
                                    if(main.getMainManager().getInv().get(k).getHealth() == 0){
                                        main.getMainManager().getInv().remove(k);
                                    }
                                }
                            }
                        }
                        if (parent.getOutingNotBusyScreen().getManager().getIsShovel()){
                            for (int k = 0; k < main.getMainManager().getInv().size(); k++) {
                                if (main.getMainManager().getInv().get(k).getName().equals("Shovel")) {
                                    main.getMainManager().getInv().get(k).setMinusHealth();
                                    if (main.getMainManager().getInv().get(k).getHealth() == 0) {
                                        main.getMainManager().getInv().remove(k);
                                    }
                                }
                            }
                        }
                        main.updateWater();
                        main.updateRock();
                        main.updateFood();
                        if (main.getPlayers().get(i).getHealth() <= 0){
                            main.minusPlayer(i);
                            i--;
                        }
                        if (parent.getOutingNotBusyScreen().getManager().getIsGun()){
//                            for (int i = 0; )
                        }
                        parent.getOutingNotBusyScreen().getManager().setIsShowel(false);
                        parent.getOutingNotBusyScreen().getManager().setIsGun(false);
                        break;
                    }
                }
                parent.getOutingNotBusyScreen().getManager().setBusy(false);
                parent.getOutingNotBusyScreen().getManager().setOuting(false);
                parent.getOutingNotBusyScreen().getManager().setImageNull();
                parent.getOutingNotBusyScreen().getManager().setWhoBisy("null");
                parent.getOutingNotBusyScreen().getManager().setWhatBisy(null);
            }
        }
        if (main.parent.getBuildingBuildScreen().getManager().getGunTime() > 0){
            main.parent.getBuildingBuildScreen().getManager().setMinusGunDay();
            if (main.parent.getBuildingBuildScreen().getManager().getGunTime() == 0){
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(main.parent.getBuildingBuildScreen().getManager().getWhoGun())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getMainManager().addInv(new AuxilliaryTool(2, "Gun"));
                        main.parent.getBuildingBuildScreen().makeGunDayFalse();
                    }
                }
            }
        }
        if (main.parent.getBuildingBuildScreen().getManager().getShovelTime() > 0){
            main.parent.getBuildingBuildScreen().getManager().setMinusShovelDay();
            if (main.parent.getBuildingBuildScreen().getManager().getShovelTime() == 0){
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(main.parent.getBuildingBuildScreen().getManager().getWhoShovel())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getMainManager().addInv(new AuxilliaryTool(2, "Shovel"));
                        main.parent.getBuildingBuildScreen().makeShovelDayFalse();
                    }
                }
            }
        }
        if (main.parent.getBuildingBuildScreen().getManager().getFirstAidTime() > 0){
            main.parent.getBuildingBuildScreen().getManager().setMinusFirstAidDay();
            if (main.parent.getBuildingBuildScreen().getManager().getFirstAidTime() == 0){
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(main.parent.getBuildingBuildScreen().getManager().getWhoFirstAid())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getMainManager().setPlusFirstAid();
                        main.parent.getBuildingBuildScreen().makeFirstAidDayFalse();
                    }
                }
            }
        }
        if (main.parent.getBuildingBuildScreen().getManager().getHammerTime() > 0){
            main.parent.getBuildingBuildScreen().getManager().setMinusHammerDay();
            if (main.parent.getBuildingBuildScreen().getManager().getHammerTime() == 0){
                for (int i = 0; i < main.getPlayers().size(); i++){
                    if (main.getPlayers().get(i).getName().equals(main.parent.getBuildingBuildScreen().getManager().getWhoHammer())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getMainManager().getThings().add(7);
                        main.parent.getBuildingBuildScreen().makeHammerDayFalse();
                    }
                }
            }
        }
    }
    private void saveBuildingsWork(){//сохранение переменных
        parent.getBuildingsMakeNoScreen().getManager().savePreferences();
    }
    private void setMinusDayForRepairBuildings(){
        if (parent.getRepairScreen().getManager().getWaterIsRepair()){
            if (parent.getRepairScreen().getManager().getWaterDay() > 1){
                parent.getRepairScreen().getManager().setMinusWaterDay();
            }
            else{
                parent.getRepairScreen().getManager().setMinusWaterDay();
                parent.getRepairScreen().getManager().setWaterIsRepair(false);
                for (int i = 0; i < main.getPlayers().size(); i++ ){
                    if (main.getPlayers().get(i).getName().equals(parent.getRepairScreen().getManager().getwhoWorkWater())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getPlayers().get(i).setRepair(false);
                        i = 5;
                    }
                }
                for (int i = 0; i < main.getMainManager().getBuildings().size(); i++){
                    if (main.getMainManager().getBuildings().get(i).getResourse().equals("water")){
                        main.getMainManager().getBuildings().get(i).setRepair(false);
                        main.getMainManager().getBuildings().get(i).setBusy(false);
                        main.getMainManager().getBuildings().get(i).setMax_health();
                        i = 5;
                    }
                }

            }
        }
        if (parent.getRepairScreen().getManager().getFoodIsRepair()){
            if (parent.getRepairScreen().getManager().getFoodDay() > 1){
                parent.getRepairScreen().getManager().setMinusFoodDay();
            }
            else{
                parent.getRepairScreen().getManager().setMinusFoodDay();
                parent.getRepairScreen().getManager().setFoodIsRepair(false);
                for (int i = 0; i < main.getPlayers().size(); i++ ){
                    if (main.getPlayers().get(i).getName().equals(parent.getRepairScreen().getManager().getwhoWorkFood())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getPlayers().get(i).setRepair(false);
                        i = 5;
                    }
                }
                for (int i = 0; i < main.getMainManager().getBuildings().size(); i++){
                    if (main.getMainManager().getBuildings().get(i).getResourse().equals("food")){
                        main.getMainManager().getBuildings().get(i).setRepair(false);
                        main.getMainManager().getBuildings().get(i).setBusy(false);
                        main.getMainManager().getBuildings().get(i).setMax_health();
                        i = 5;
                    }
                }

            }
        }
        if (parent.getRepairScreen().getManager().getRockIsRepair()){
            if (parent.getRepairScreen().getManager().getRockDay() > 1){
                parent.getRepairScreen().getManager().setMinusRockDay();
            }
            else{
                parent.getRepairScreen().getManager().setMinusRockDay();
                parent.getRepairScreen().getManager().setRockIsRepair(false);
                for (int i = 0; i < main.getPlayers().size(); i++ ){
                    if (main.getPlayers().get(i).getName().equals(parent.getRepairScreen().getManager().getwhoWorkRock())){
                        main.getPlayers().get(i).setIsBusy(false);
                        main.getPlayers().get(i).setRepair(false);
                        i = 5;
                    }
                }
                for (int i = 0; i < main.getMainManager().getBuildings().size(); i++){
                    if (main.getMainManager().getBuildings().get(i).getResourse().equals("rock")){
                        main.getMainManager().getBuildings().get(i).setRepair(false);
                        main.getMainManager().getBuildings().get(i).setBusy(false);
                        main.getMainManager().getBuildings().get(i).setMax_health();
                        i = 5;
                    }
                }

            }
        }
    }
    public void saveRobot(){//сохранение переменных
        main.getRobot().save();
    }
    private void saveRepairBuilding(){//сохранение переменных
        parent.getRepairScreen().getManager().savePref();
    }
    private void saveOutingManager(){//сохранение переменных
        parent.getOutingNotBusyScreen().getManager().save();
    }
    private void saveInv(){//сохранение переменных
        main.getMainManager().saveInv();
    }
    private void saveThings(){//сохранение переменных
        main.getMainManager().saveThings();
    }


}
