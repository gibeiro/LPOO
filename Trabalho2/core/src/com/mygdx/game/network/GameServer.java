package com.mygdx.game.network;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.auxclass.Inputs;
import com.mygdx.game.logic.Game;

import java.util.ArrayList;
import lipermi.handler.CallHandler;
import lipermi.net.Server;
/**
 * Created by Nuno on 10/05/2016.
 */
public class GameServer implements ServerInterface {
    public ArrayList<ClientInterface> clients;
    public Game game;

    public GameServer(){
        clients = new ArrayList<ClientInterface>();
        game = new Game();
    }
    @Override
    public int join(ClientInterface c) {
        if(clients.size() >= 2)
            return 0;
        clients.add(c);
        c.setId(clients.size());
        return clients.size();
    }

    @Override
    public int sendInput(ClientInterface c, Inputs i) {
        if(c.getId() != 1 && c.getId() != 2)
            return 0;
        if(c.getId() == 1){
            game.getPlayer1().inputs = i;
        }else if(c.getId() == 2){
            game.getPlayer2().inputs = i;
        }
        return 1;
    }

    @Override
    public void transferWorld(ClientInterface c) {
        c.setWorld(game.getWorld());
    }

}
