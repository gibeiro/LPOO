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
            game.player1.movingLeft = i.movingLeft;
            game.player1.movingRight = i.movingRight;
            game.player1.jump = i.jump;
        }else if(c.getId() == 2){
            game.player2.movingLeft = i.movingLeft;
            game.player2.movingRight = i.movingRight;
            game.player2.jump = i.jump;
        }
        return 1;
    }

    @Override
    public void transferWorld(ClientInterface c) {
        c.setWorld(game.world);
    }

}
