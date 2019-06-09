package com.mygdx.game.Managers;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Model.AuxilliaryTool;
import com.mygdx.game.Model.Players;
import com.mygdx.game.Views.MainGame.GameMainScreen;

import java.util.ArrayList;
public class MakePlayersManager {
    private ArrayList<String> players;
    private ArrayList<Players> makePlayers = new ArrayList<Players>();
    private float k;//константа для экрана (высота)
    private float w;//константа для экрана (ширина)
    private TiledMapTileLayer layer;
    private GameMainScreen main;
    public MakePlayersManager(ArrayList<String> players, float k, TiledMapTileLayer layer, GameMainScreen main){
        this.players = new ArrayList<String>(players);
        this.k = k;
        this.w = MyGdxGame.w;
        this.layer = layer;
        this.main = main;
    }
    public ArrayList<Players> makePlayers(){//создание игроков
//        Players main_player = new Players(10 * layer.getTileWidth() * k , 1 * layer.getTileHeight() * k, "Main", 3.2f * layer.getTileHeight() * k * 1.2f,1 * layer.getTileWidth() * k * 1.2f, "Image/playerAnimation/Main", "main", 3, 5, 4, false, 3, 0, 4, 2, 0, 0, 3);
//        MakePlayersManager.add(main_player);
        for (int i = 0; i < players.size(); i++){
            String name = players.get(i);
            if (name.equals("Miner")){
                    Players miner = new Players(16 * layer.getTileWidth() * w , 1 * layer.getTileHeight() * k, "Miner", 3.2f * layer.getTileHeight() * k * 1.2f,1 * layer.getTileWidth() * w * 1.2f, "Image/playerAnimation/Miner", "miner", 3, 5, 4, false, 3, 2, 4, 2, 0, 0, 3, false, 0f, 0f, 0.3f, 0.6f, 4);
                makePlayers.add(miner);
            }
            if (name.equals("Engineer")){
                Players engineer = new Players(26 * (layer.getTileWidth() * 1.2f)  * w , -5 * layer.getTileHeight() * k, "Engineer", 3.2f * layer.getTileHeight() * k * 1.2f, 1 * layer.getTileWidth() * w * 1.2f, "Image/playerAnimation/Engineer", "engineer", 3,4 ,5, false, 4, 1, 0, 3, 0, 0, 3, false, 0f, 0f, 0.4f, 0.5f, 3);
                makePlayers.add(engineer);
            }

            if (name.equals("Biology")){
                Players biology = new Players( 21 * (layer.getTileWidth() * 1.2f) * w, -3 * layer.getHeight() * k * 1.2f, "Biology", 3.3f * layer.getTileHeight() * k * 1.2f,1 * layer.getTileWidth() * w * 1.2f, "Image/playerAnimation/Biology", "biology", 2,3 ,2, false, 1, 4,1,1, 0, 0, 2, false, 0f, 0f, 0.5f, 0.8f, 3);
                makePlayers.add(biology);

            }
            if (name.equals("Strength")){
                Players strength = new Players(25 * layer.getTileWidth() * w , 5 * layer.getTileHeight() * k, "Strength", 3.2f * layer.getTileHeight() * k * 1.2f,1 * layer.getTileWidth() * w * 1.2f, "Image/playerAnimation/Strength", "strength", 4, 5, 3, false,2,1,2,4, 0, 0, 4, false, 0f, 0f, 0.8f, 0.9f, 3);
                makePlayers.add(strength);
            }
            if (name.equals("Gun")){
                main.getMainManager().addInv(new AuxilliaryTool(2, "Gun"));
            }
        }
        return makePlayers;
    }
    public ArrayList<String> getPlayers(){
        return players;
    }
}
