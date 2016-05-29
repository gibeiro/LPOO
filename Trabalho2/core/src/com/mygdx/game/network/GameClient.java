package com.mygdx.game.network;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.input.Inputs;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;

import java.io.IOException;
import java.io.Serializable;

import lipermi.handler.CallHandler;
import lipermi.net.Client;


/**
 * Created by Nuno on 10/05/2016.
 */
public class GameClient implements ClientInterface {
    Game game;
    int id;
    Client c;
    public ServerInterface proxy;
    public Inputs i;


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setGame(GameInfo o) {
        game.updateGame(o);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public Inputs getInputs() {
        return new Inputs(i.movingLeft,i.movingRight,i.jump,i.power);
    }

    public GameClient(){
        i = new Inputs(false,false,false,false);
        game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        try {
            CallHandler callHandler = new CallHandler();
            String remoteHost = "192.168.1.7";
            int portWasBinded = 4456;
            c = new Client(remoteHost,portWasBinded,callHandler);
            proxy = (ServerInterface)c.getGlobal(ServerInterface.class);
            callHandler.exportObject(ClientInterface.class,this);

        }catch(Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
