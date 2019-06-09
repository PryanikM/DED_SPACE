package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Loader.A2DAssetManager;
import com.mygdx.game.Loader.AssetsScreen;
import com.mygdx.game.Managers.GameMainManager;
import com.mygdx.game.Managers.PreferencesManager;
import com.mygdx.game.Views.MainGame.BuildingBuildScreen;
import com.mygdx.game.Views.ChoiceScreenPlayers;
import com.mygdx.game.Views.DialogScreen;
import com.mygdx.game.Views.EndScreen;
import com.mygdx.game.Views.LoadingScreen;
import com.mygdx.game.Views.MainGame.BuildingRepairScreen;
import com.mygdx.game.Views.MainGame.BuildingsMakeNoScreen;
import com.mygdx.game.Views.MainGame.BuildingsMakeYesScreen;
import com.mygdx.game.Views.MainGame.ChoiseAuxiliaryToolOutingScreen;
import com.mygdx.game.Views.MainGame.GameMainScreen;
import com.mygdx.game.Views.MainGame.GameMenuScreen;
import com.mygdx.game.Views.MainGame.InvScreen;
import com.mygdx.game.Views.MainGame.NextDayScreen;
import com.mygdx.game.Views.MainGame.OutingNotBusyScreen;
import com.mygdx.game.Views.MainGame.OutingYesBusyScreen;
import com.mygdx.game.Views.MainGame.RobotScreen;
import com.mygdx.game.Views.MainGame.PlayerStateScreen;
import com.mygdx.game.Views.MainScreen;
import com.mygdx.game.Views.MenuScreen;
import com.mygdx.game.Views.PreferencesScreen;

import java.util.ArrayList;

public class MyGdxGame extends Game {
	private LoadingScreen loadingScreen;//откуда начинается загрузка
	private EndScreen endScreen;//экран проигрыша
	private MainScreen mainScreen;
	private MenuScreen menuScreen;//главное меню
	private ChoiceScreenPlayers choiceScreenPlayers;//экран выбора игроков и ресурсов
	private PreferencesScreen preferencesScreen;//экран настроек
	private PreferencesManager preferences;//класс, управляющий настройками
	private DialogScreen dialogScreen;//экранн с характеристиками игроков
	private GameMainScreen gameScreen;//главный игровой экран (с ракетой)
	private Music playingSong;//музыка
	private PlayerStateScreen stateScreen;//экран состояния игроков
	private BuildingRepairScreen repairScreen;//экран починки зданий
	private BuildingBuildScreen building;//экран построек зданий и вещей
	private NextDayScreen dayScreen;//экран, где проигрывается анимация следующего дня
	private OutingNotBusyScreen outingNotBusyScreen;//экран ракеты до того, как кого-то отправили на вылазку
	private OutingYesBusyScreen outingYesBusyScreen;//экран ракеты после того, как кого-то отправили на вылазку
	private BuildingsMakeNoScreen buildingsMakeNoScreen;//экран зданий (где производятся ресурсы) до того, как кто-то начал там работу
	private BuildingsMakeYesScreen buildingsMakeYesScreen;//экран зданий (где производятся ресурсы) после того, как кто-то начал там работу
	private RobotScreen robotScreen;//экран робота
	private ChoiseAuxiliaryToolOutingScreen auxiliaryToolOutingScreen;//экран для выбора лопаты и пистолета (вылазка)
	private InvScreen invScreen;//экран инвентаря
	private GameMenuScreen gameMenuScreen;//экран меню, во время игры
	private AssetsScreen loadScreen;//экран загрзки

	//для сохранения настроек
	private Preferences preferencesLoad;
	private Preferences player1;
	private SpriteBatch  batch;
	private Preferences player2;
	private Preferences player3;
	private Preferences player4;
	private Preferences building1;
	private Preferences building2;
	private Preferences building3;
	private Preferences tombstone1;
	private Preferences tombstone2;
	private Preferences tombstone3;
	private Preferences tombstone4;
	private Preferences tombstone5;
	private Preferences robotPref;
	private Preferences inv;
	private Preferences buildingMake;
	private Preferences buildingRepair;
	private Preferences outingBuildings;

	private ArrayList<Preferences> playerPreferences;//дял сохранения игроков
	private ArrayList<Preferences> buildingPreferences;//для сохранения зданий
	private ArrayList<Preferences> tombstonePreferences;//для сохранеия могил
	public A2DAssetManager Manager = new A2DAssetManager();//загрузчик

	//константы экранов
	public final static int LOAD = 0;
	public final static int MENU = 1;
	public final static int CHOICEPLAYER = 2;
	public final static int PREFERENCES = 3;
	public final static int APPLICATION = 4;
	public final static int DIALOG = 5;
	public final static int GAMEPLAY = 6;
	public final static int BUILDING = 7;
	public final static int STATE = 8;
	public final static int REPAIR = 9;
	public final static int OUTINGF = 10;
	public final static int OUTINGT = 11;
	public final static int BUILDINGMAKE = 12;
	public final static int NEXTDAYSCREEN = 13;
	public final static int BUILDINGMAKEYES = 14;
	public final static int OUTCHOCE = 15;
	public final static int ROBOTSCREEN = 16;
	public final static int GAMEMENU = 17;
	public final static int INVSCREEN = 18;
	public final static int ENDGAME = 19;


	public float height;//размеры экрана
	private float weight;//размеры экрана
	public static float k;//константа для экрана (высота)
	public static float w;//константа для экрана (ширина)


	private Boolean pref;
	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		batch = new SpriteBatch();
		playerPreferences = new ArrayList<Preferences>();
		buildingPreferences = new ArrayList<Preferences>();
		tombstonePreferences = new ArrayList<Preferences>();
		preferencesLoad = Gdx.app.getPreferences("Load");
		player1 = Gdx.app.getPreferences("player1");
		player2 = Gdx.app.getPreferences("player2");
		player3 = Gdx.app.getPreferences("player3");
		player4 = Gdx.app.getPreferences("player4");
		building1 = Gdx.app.getPreferences("building1");
		building2 = Gdx.app.getPreferences("building2");
		building3 = Gdx.app.getPreferences("building3");
		tombstone1 = Gdx.app.getPreferences("tombstone1");
		tombstone2 = Gdx.app.getPreferences("tombstone2");
		tombstone3 = Gdx.app.getPreferences("tombstone3");
		tombstone4 = Gdx.app.getPreferences("tombstone4");
		tombstone5 = Gdx.app.getPreferences("tombstone5");
		buildingMake = Gdx.app.getPreferences("buildingMake");
		buildingRepair = Gdx.app.getPreferences("buildingRepair");
		outingBuildings = Gdx.app.getPreferences("outingBuildings");
		inv = Gdx.app.getPreferences("inv");
		robotPref = Gdx.app.getPreferences("robotPref");
		tombstonePreferences.add(tombstone1);
		tombstonePreferences.add(tombstone2);
		tombstonePreferences.add(tombstone3);
		tombstonePreferences.add(tombstone4);
		tombstonePreferences.add(tombstone5);
		Gdx.app.log("qqqq",player1.toString());
		playerPreferences.add(player1);
		playerPreferences.add(player2);
		playerPreferences.add(player3);
		playerPreferences.add(player4);
		buildingPreferences.add(building1);
		buildingPreferences.add(building2);
		buildingPreferences.add(building3);
		loadingScreen = new LoadingScreen(this);
		preferences = new PreferencesManager(this);
		setScreen(loadingScreen);
//		Manager.addMusic();
//		Manager.manager.finishLoading();
		height = Gdx.graphics.getHeight();
		weight = Gdx.graphics.getWidth();
		pref = false;
		Gdx.app.log("screen", String.valueOf(height));
		k = height / 720f;
		w = weight / 1280f;
		Gdx.app.log("screen", String.valueOf(k));
	}
	public void setMusic(){
		playingSong = A2DAssetManager.manager.get(A2DAssetManager.song);
		changeVolumeMusic(preferences.getMusicVolume());
		turnOffMusic(preferences.isMusicEnable());
	}
	public void changeScreen(int screen){//здесь происходит смена всех экранов
		switch(screen){
			case LOAD:
				if(loadScreen == null) loadScreen = new AssetsScreen(this, batch);
				this.setScreen(loadScreen);
				break;
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this, batch);
				this.setScreen(menuScreen);
				break;
			case CHOICEPLAYER:
				if (endScreen != null) endScreen = null;
				if (choiceScreenPlayers == null) {
					choiceScreenPlayers = new ChoiceScreenPlayers(this, menuScreen, batch);
				}
				this.setScreen(choiceScreenPlayers);
				break;
			case PREFERENCES:
				if (this.preferencesScreen == null) {
					this.preferencesScreen = new PreferencesScreen(this, batch);
				}
				if (gameMenuScreen != null){
					gameMenuScreen = null;}
				this.setScreen(this.preferencesScreen);
				break;
			case APPLICATION:
				if(mainScreen == null) mainScreen = new MainScreen(this);
				this.setScreen(mainScreen);
				break;
			case DIALOG:
				if (dialogScreen == null) dialogScreen = new DialogScreen(this, choiceScreenPlayers.getDialogGame(), batch);
				this.setScreen(dialogScreen);
				break;
			case GAMEPLAY:
			    //if (building != null){building.dispose();}
//				if(menuScreen != null && choiceScreenPlayers != null){choiceScreenPlayers.dispose();}
				if (gameScreen == null) gameScreen = new GameMainScreen(this,
						getChoiceScreenPlayers().getFood(),
						getChoiceScreenPlayers().getWater(),
						1,
						false,
						getChoiceScreenPlayers().getRock(),
						batch
						);
				if (outingNotBusyScreen == null) outingNotBusyScreen = new OutingNotBusyScreen(this, gameScreen, k, batch);
				if (repairScreen == null){repairScreen = new BuildingRepairScreen(this, gameScreen, batch);}
				if (buildingsMakeNoScreen == null){ buildingsMakeNoScreen = new BuildingsMakeNoScreen(this, gameScreen, batch);}
				if (building == null){building = new BuildingBuildScreen(this, gameScreen, batch);}
				this.setScreen(gameScreen);
				break;
			case BUILDING:
				if (building == null) building = new BuildingBuildScreen(this, gameScreen, batch);
				this.setScreen(building);
				break;
			case STATE:
				if (stateScreen == null) stateScreen = new PlayerStateScreen(this, gameScreen, batch);
				this.setScreen(stateScreen);
				break;
			case REPAIR:
				if(repairScreen == null) repairScreen = new BuildingRepairScreen(this, gameScreen, batch);
				this.setScreen(repairScreen);
				break;
			case OUTINGF:
				if (outingNotBusyScreen == null) outingNotBusyScreen = new OutingNotBusyScreen(this, gameScreen, k, batch);
				this.setScreen(outingNotBusyScreen);
				break;
            case OUTINGT:
                if (outingYesBusyScreen == null) outingYesBusyScreen = new OutingYesBusyScreen(this, k, outingNotBusyScreen.getManager(), batch);
                this.setScreen(outingYesBusyScreen);
                break;
			case BUILDINGMAKE:
				if (buildingsMakeNoScreen == null) buildingsMakeNoScreen = new BuildingsMakeNoScreen(this, gameScreen, batch);
				this.setScreen(buildingsMakeNoScreen);
				break;
			case BUILDINGMAKEYES:
				if (buildingsMakeYesScreen == null) buildingsMakeYesScreen = new BuildingsMakeYesScreen(this, batch);
				this.setScreen(buildingsMakeYesScreen);
				break;
			case NEXTDAYSCREEN:
				if (dayScreen == null) dayScreen = new NextDayScreen(gameScreen, gameScreen.getMainManager(), this, batch);
				this.setScreen(dayScreen);
				break;
			case OUTCHOCE:
				if (auxiliaryToolOutingScreen == null){auxiliaryToolOutingScreen = new ChoiseAuxiliaryToolOutingScreen(this, batch, outingNotBusyScreen.getManager());}
				this.setScreen(auxiliaryToolOutingScreen);
				break;
            case ROBOTSCREEN:
                if (robotScreen == null){
                    robotScreen = new RobotScreen(this, batch);
                }
                this.setScreen(robotScreen);
                break;
			case GAMEMENU:
				if (gameMenuScreen == null){
					gameMenuScreen = new GameMenuScreen(this, batch);}
				this.setScreen(gameMenuScreen);
				break;
			case INVSCREEN:
				if (invScreen == null){invScreen = new InvScreen(this, batch);}
				this.setScreen(invScreen);
				break;
			case ENDGAME:
				if(endScreen == null) endScreen = new EndScreen(this, gameScreen.getMainManager().getCountDay(), batch);
				this.setScreen(endScreen);
				break;
		}
	}
	public void changeVolumeMusic(float volume){
		playingSong.setVolume(volume);
	}
	public void turnOffMusic(boolean position){
		if ((!position) && (playingSong.isPlaying())){
			playingSong.pause();
		}
		if ((position) && (!playingSong.isPlaying())){
			playingSong.play();
			playingSong.setLooping(true);
		}
	}
	public ChoiceScreenPlayers getChoiceScreenPlayers(){
		return choiceScreenPlayers;
	}
	public PreferencesManager getPreferences() {
		return this.preferences;
	}
	public PlayerStateScreen getPlayerState(){
		return stateScreen;
	}
	public BuildingBuildScreen getBuildingBuildScreen(){
		return building;
	}
	public OutingNotBusyScreen getOutingNotBusyScreen(){
		return outingNotBusyScreen;
	}
	public void createNextDaySc(){
		dayScreen = new NextDayScreen(gameScreen, gameScreen.getMainManager(), this, batch);
	}
//	public NextDayScreen getNextDayScree(){
//		return dayScreen;
//	}
	public Preferences getPreferencesLoad(){
		preferencesLoad.clear();
		return preferencesLoad;
	}
	public void setMenu(Boolean check){
		menuScreen.setC(check);
	}
	public GameMainScreen getGameScreen(){
		return gameScreen;
	}
	public void makeGameMain(){
        if (gameScreen == null) gameScreen = new GameMainScreen(this,
				preferencesLoad.getInteger(GameMainManager.COUNT_FOOD),
				preferencesLoad.getInteger(GameMainManager.COUNT_WATER),
				preferencesLoad.getInteger(GameMainManager.COUNT_DAY),
				true,
				preferencesLoad.getInteger(GameMainManager.COUNT_ROCK),
				batch
		);
    }
	public void makeClassForLoad(){//создания классов, для загрузки
		if (buildingsMakeNoScreen == null) buildingsMakeNoScreen = new BuildingsMakeNoScreen(this, gameScreen, batch);
		if (outingNotBusyScreen == null) outingNotBusyScreen = new OutingNotBusyScreen(this, gameScreen, k, batch);
		if (building == null) building = new BuildingBuildScreen(this, gameScreen, batch);
		if (repairScreen == null) repairScreen = new BuildingRepairScreen(this, gameScreen, batch);
	}
	public ArrayList<Preferences> getPlayerPreferences() {
		return playerPreferences;
	}
	public ArrayList<Preferences> getBuildingPreferences() {
		return buildingPreferences;
	}
	@Override
	public void dispose() {
	}
	public void destroy(){
		if (dialogScreen != null) dialogScreen = null;
		if (stateScreen != null) stateScreen = null;
		if (gameScreen != null)	{ gameScreen = null;}
		if (outingNotBusyScreen != null){outingNotBusyScreen = null;}
		if (outingYesBusyScreen != null){outingYesBusyScreen = null;}
		if (buildingsMakeNoScreen != null){buildingsMakeNoScreen = null;}
		if (repairScreen != null) repairScreen = null;
		if (choiceScreenPlayers != null) choiceScreenPlayers = null;
		if (building != null){building = null;}
		if (robotScreen != null){robotScreen = null;}
		if (auxiliaryToolOutingScreen != null){auxiliaryToolOutingScreen = null;}

	}
	public DialogScreen getDialogScreen(){
		if (dialogScreen == null) dialogScreen =new DialogScreen(this, choiceScreenPlayers.getDialogGame(), batch);
		return dialogScreen;
	}
	public ArrayList<Preferences> getTombstonePreferences() {
		return tombstonePreferences;
	}
	public BuildingsMakeNoScreen getBuildingsMakeNoScreen(){
		return buildingsMakeNoScreen;
	}
	public Preferences getBuildingMake(){
		return buildingMake;
	}
	public Preferences getPreferencesBuildingRepair(){
		return buildingRepair;
	}
	public Preferences getOutingBuildings(){
		return outingBuildings;
	}
	public BuildingRepairScreen getRepairScreen(){
		return  repairScreen;
	}
	public void disposeLoad(){
		Manager.dispose();
	}
	public Preferences getInv(){
		return inv;
	}
	public Preferences getRobotPref(){
		return robotPref;
	}
	public Boolean getPref(){
		return pref;
	}
	public void setPref(boolean pref){
		this.pref = pref;
	}
	public void setC(boolean check){
		menuScreen.setC(check);
	}
}
