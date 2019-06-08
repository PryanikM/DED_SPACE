package com.mygdx.game.manager;

import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

public class ContinueManager {
    private MyGdxGame parent;
    public ContinueManager(MyGdxGame parent) {
        this.parent = parent;
    }
    public void loadSave(){
        parent.makeGameMain();
        makePlayers();
        makeBuilding();
        makeTombstone();
        parent.getGameScreen().updateBuildingCheck();
        parent.makeClassForLoad();
        parent.getBuildingBuildScreen().getManager().loadPreferences();
        parent.getGameScreen().getMainManager().loadPreferences();
        makeMakeBuilding();
        makeRepair();
        makeOuting();
        makeInv();
        makeRobot();
        makeThings();
    }
    private void makePlayers(){//выгрузка ресурсов из Preferences
        parent.getGameScreen().getMainManager().loadPlayers();
    }
    private void makeTombstone(){//выгрузка ресурсов из Preferences
        parent.getGameScreen().getMainManager().loadTombstone();
    }
    private void makeBuilding(){//выгрузка ресурсов из Preferences
        parent.getGameScreen().getMainManager().loadBuilding();
    }
    private void makeMakeBuilding(){//выгрузка ресурсов из Preferences
        parent.getBuildingsMakeNoScreen().getManager().loadPreferences();
    }
    private void makeRepair(){//выгрузка ресурсов из Preferences
        parent.getRepairScreen().getManager().load();
    }
    private void makeOuting(){//выгрузка ресурсов из Preferences
        parent.getOutingNotBusyScreen().getManager().load();
    }
    private void makeInv(){//выгрузка ресурсов из Preferences
        parent.getGameScreen().getMainManager().loadInv();}
    private void makeRobot(){//выгрузка ресурсов из Preferences
        parent.getGameScreen().getRobot().load();}
    private void makeThings(){//выгрузка ресурсов из Preferences
        parent.getGameScreen().getMainManager().loadThings();
    }

}
