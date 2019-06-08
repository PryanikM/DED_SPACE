package com.mygdx.game.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import javax.xml.soap.Text;

public class A2DAssetManager {
    public static final AssetManager manager = new AssetManager();
    //ссылки на ресурсы
    public static String rocketBroken = "Image/rocketBroken.png";
    public static String song = "Music/music.mp3";
    public static String background = "Image/background.png";
    public static String biology = "Image/biology.png";
    public static String biologyDown = "Image/biologyDown.png";
    public static String biologyF = "Image/biologyF.png";
    public static String biologyWork = "Image/biologyWork.png";
    public static String buildingCheck = "Image/buildingCheck.png";
    public static String cosmonaut = "Image/cosmonaut.png";
    public static String end = "Image/end.png";
    public static String engineer = "Image/engineer.png";
    public static String engineerDown = "Image/engineerDown.png";
    public static String engineerF = "Image/engineerF.png";
    public static String engineerWork = "Image/engineerWork.png";
    public static String factory = "Image/factory.png";
    public static String foodFactory = "Image/foodFactory.png";
    public static String foodMake = "Image/foodMake.png";
    public static String heart = "Image/heart.png";
    public static String heartF = "Image/heartF.png";
    public static String miner = "Image/miner.png";
    public static String minerF = "Image/minerF.png";
    public static String minerDown = "Image/minerDown.png";
    public static String minerWork = "Image/minerWork.png";
    public static String strength = "Image/strength.png";
    public static String strengthDown = "Image/strengthDown.png";
    public static String strengthF = "Image/strengthF.png";
    public static String strengthWork = "Image/strengthWork.png";
    public static String water = "Image/water.png";
    public static String food = "Image/food.png";
    public static String plus = "Image/plus.png";
    public static String minus = "Image/minus.png";
    public static String gun = "Image/gun.png";
    public static String rock = "Image/rock.png";
    public static String rockFactory = "Image/rockFactory.png";
    public static String rockMake = "Image/rockMake.png";
    public static String clock = "Image/clock.png";
    public static String shovel = "Image/shovel.png";
    public static String transmitter = "Image/transmitter.png";
    public static String waterFactory = "Image/waterFactory.png";
    public static String waterMake = "Image/waterMake.png";
    public static String MinerS = "Image/MinerS.png";
    public static String StrengthS = "Image/StrengthS.png";
    public static String EngineerS = "Image/EngineerS.png";
    public static String BiologyS = "Image/BiologyS.png";
    public static String waterBrokenS = "Image/BrokenBuildingScreen/waterBrokenS.png";
    public static String foodBrokenS = "Image/BrokenBuildingScreen/foodBrokenS.png";
    public static String rockBrokenS = "Image/BrokenBuildingScreen/rockBrokenS.png";
    public static String battery = "Image/battery.png";
    public static String antenna = "Image/antenna.png";
    public static String microboard = "Image/microboard.png";
    public static String dirt = "Image/dirt.png";
    public static String dirtWithWood = "Image/dirtWithWood.png";
    public static String firstAid = "Image/firstAid.png";
    public static String waterBrokenImage = "Image/waterBrokenImage.png";
    public static String robot = "Image/robot.png";
    public static String hammer = "Image/hammer.png";
    public static String foodBrokenImage = "Image/foodBrokenImage.png";
    public static String rockBrokenImage = "Image/rockBrokenImage.png";
    public static String backPack = "Image/backPack.png";
    public static String pause = "Image/pause.png";


    //skills
    public static String biologySkills = "Image/skills/biologySkills.png";
    public static String engineerSkills = "Image/skills/engineerSkills.png";
    public static String minerSkills = "Image/skills/minerSkills.png";
    public static String strengthSkills = "Image/skills/strengthSkills.png";

    //skills

    //foodAnimation

    public static String food0 = "Image/foodAnimation/food0.png";
    public static String food1 = "Image/foodAnimation/food1.png";
    public static String food2 = "Image/foodAnimation/food2.png";
    public static String food3 = "Image/foodAnimation/food3.png";
    public static String food4 = "Image/foodAnimation/food4.png";
    public static String food5 = "Image/foodAnimation/food5.png";
    public static String food6 = "Image/foodAnimation/food6.png";
    public static String food7 = "Image/foodAnimation/food7.png";

    //foodAnimation

    //menuAnimation

    public static String menuAnimation1 = "Image/menuAnimation/animation1.png";
    public static String menuAnimation2 = "Image/menuAnimation/animation2.png";
    public static String menuAnimation3 = "Image/menuAnimation/animation3.png";
    public static String menuAnimation4 = "Image/menuAnimation/animation4.png";
    public static String menuAnimation5 = "Image/menuAnimation/animation5.png";
    public static String menuAnimation6 = "Image/menuAnimation/animation6.png";
    public static String menuAnimation7 = "Image/menuAnimation/animation7.png";

    //menuAnimation

    //newDayAnimation

    public static String newDay0 = "Image/newDayAnimation/0.png";
    public static String newDay1 = "Image/newDayAnimation/1.png";
    public static String newDay2 = "Image/newDayAnimation/2.png";
    public static String newDay3 = "Image/newDayAnimation/3.png";
    public static String newDay4 = "Image/newDayAnimation/4.png";
    public static String newDay5 = "Image/newDayAnimation/5.png";
    public static String newDay6 = "Image/newDayAnimation/6.png";

    //newDayAnimation


    //rockAnimation

    public static String rock0 = "Image/rockAnimation/rock0.png";
    public static String rock1 = "Image/rockAnimation/rock1.png";
    public static String rock2 = "Image/rockAnimation/rock2.png";
    public static String rock3 = "Image/rockAnimation/rock3.png";
    public static String rock4 = "Image/rockAnimation/rock4.png";
    public static String rock5 = "Image/rockAnimation/rock5.png";
    public static String rock6 = "Image/rockAnimation/rock6.png";
    public static String rock7 = "Image/rockAnimation/rock7.png";

    //rockAnimation

    //tombstone

    public static String tombstoneBiology = "Image/tombstone/tombstoneBiology.png";
    public static String tombstoneMiner = "Image/tombstone/tombstoneMiner.png";
    public static String tombstoneStrength = "Image/tombstone/tombstoneStrength.png";
    public static String tombstoneEngineer = "Image/tombstone/tombstoneEngineer.png";
    //tombstone


    //waterAnimation

    public static String water0 = "Image/waterAnimation/water0.png";
    public static String water1 = "Image/waterAnimation/water1.png";
    public static String water2 = "Image/waterAnimation/water2.png";
    public static String water3 = "Image/waterAnimation/water3.png";
    public static String water4 = "Image/waterAnimation/water4.png";
    public static String water5 = "Image/waterAnimation/water5.png";
    public static String water6 = "Image/waterAnimation/water6.png";
    public static String water7 = "Image/waterAnimation/water7.png";
    //waterAnimation

    public static void load(){//загрузка ресурсов
        manager.load(rocketBroken, Texture.class);
        manager.load(end, Texture.class);
        manager.load(biology, Texture.class);
        manager.load(biologyF, Texture.class);
        manager.load(engineer, Texture.class);
        manager.load(engineerF, Texture.class);
        manager.load(miner, Texture.class);
        manager.load(minerF, Texture.class);
        manager.load(strength, Texture.class);
        manager.load(strengthF, Texture.class);
        manager.load(water, Texture.class);
        manager.load(food, Texture.class);
        manager.load(plus, Texture.class);
        manager.load(minus, Texture.class);
        manager.load(gun, Texture.class);
        manager.load(song, Music.class);
        manager.load(clock, Texture.class);
        manager.load(rock, Texture.class);
        manager.load(biologySkills, Texture.class);
        manager.load(engineerSkills, Texture.class);
        manager.load(strengthSkills, Texture.class);
        manager.load(minerSkills, Texture.class);
        manager.load(background, Texture.class);
        manager.load(biologyDown, Texture.class);
        manager.load(biologyWork, Texture.class);
        manager.load(buildingCheck, Texture.class);
        manager.load(cosmonaut, Texture.class);
        manager.load(engineerDown, Texture.class);
        manager.load(engineerWork, Texture.class);
        manager.load(factory, Texture.class);
        manager.load(foodFactory, Texture.class);
        manager.load(foodMake, Texture.class);
        manager.load(heart, Texture.class);
        manager.load(heartF, Texture.class);
        manager.load(minerDown, Texture.class);
        manager.load(minerWork, Texture.class);
        manager.load(strengthDown, Texture.class);
        manager.load(strengthWork, Texture.class);
        manager.load(rockFactory, Texture.class);
        manager.load(rockMake, Texture.class);
        manager.load(shovel, Texture.class);
        manager.load(transmitter, Texture.class);
        manager.load(waterFactory, Texture.class);
        manager.load(waterMake, Texture.class);
        manager.load(food0, Texture.class);
        manager.load(food1, Texture.class);
        manager.load(food2, Texture.class);
        manager.load(food3, Texture.class);
        manager.load(food4, Texture.class);
        manager.load(food5, Texture.class);
        manager.load(food6, Texture.class);
        manager.load(food7, Texture.class);
        manager.load(water0, Texture.class);
        manager.load(water1, Texture.class);
        manager.load(water2, Texture.class);
        manager.load(water3, Texture.class);
        manager.load(water4, Texture.class);
        manager.load(water5, Texture.class);
        manager.load(water6, Texture.class);
        manager.load(water7, Texture.class);
        manager.load(rock0, Texture.class);
        manager.load(rock1, Texture.class);
        manager.load(rock2, Texture.class);
        manager.load(rock3, Texture.class);
        manager.load(rock4, Texture.class);
        manager.load(rock5, Texture.class);
        manager.load(rock6, Texture.class);
        manager.load(rock7, Texture.class);
        manager.load(newDay0, Texture.class);
        manager.load(newDay1, Texture.class);
        manager.load(newDay2, Texture.class);
        manager.load(newDay3, Texture.class);
        manager.load(newDay4, Texture.class);
        manager.load(newDay5, Texture.class);
        manager.load(newDay6, Texture.class);
        manager.load(menuAnimation1, Texture.class);
        manager.load(menuAnimation2, Texture.class);
        manager.load(menuAnimation3, Texture.class);
        manager.load(menuAnimation4, Texture.class);
        manager.load(menuAnimation5, Texture.class);
        manager.load(menuAnimation6, Texture.class);
        manager.load(menuAnimation7, Texture.class);
        manager.load(menuAnimation7, Texture.class);
        manager.load(menuAnimation7, Texture.class);
        manager.load(menuAnimation7, Texture.class);
        manager.load(tombstoneBiology, Texture.class);
        manager.load(tombstoneStrength, Texture.class);
        manager.load(tombstoneMiner, Texture.class);
        manager.load(tombstoneEngineer, Texture.class);
        manager.load(StrengthS, Texture.class);
        manager.load(EngineerS, Texture.class);
        manager.load(BiologyS, Texture.class);
        manager.load(MinerS, Texture.class);
        manager.load(foodBrokenS, Texture.class);
        manager.load(waterBrokenS, Texture.class);
        manager.load(rockBrokenS, Texture.class);
        manager.load(battery, Texture.class);
        manager.load(antenna, Texture.class);
        manager.load(microboard, Texture.class);
        manager.load(dirt, Texture.class);
        manager.load(dirtWithWood, Texture.class);
        manager.load(firstAid, Texture.class);
        manager.load(waterBrokenImage, Texture.class);
        manager.load(robot, Texture.class);
        manager.load(hammer, Texture.class);
        manager.load(foodBrokenImage, Texture.class);
        manager.load(rockBrokenImage, Texture.class);
        manager.load(backPack, Texture.class);
        manager.load(pause, Texture.class);
    }
    public void dispose(){
        manager.clear();
    }
}
