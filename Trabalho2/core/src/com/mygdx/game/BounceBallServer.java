package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.network.GameServer;
import com.mygdx.game.network.ServerInterface;
import com.mygdx.game.state.StateManager;
import com.mygdx.game.state.StateMenu;

import java.net.InetAddress;

import lipermi.handler.CallHandler;
import lipermi.net.Server;

public class BounceBallServer extends ApplicationAdapter {
    private final static float RATE = 0.06f;
    float ratecounter;
    GameServer gameServer;
    @Override
    public void create () {
        ratecounter = 0;
        try {
            gameServer = new GameServer();
            CallHandler callHandler = new CallHandler();
            callHandler.registerGlobal(ServerInterface.class, gameServer);
            Server server = new Server();
            int thePortIWantToBind = 4456;
            server.bind(thePortIWantToBind, callHandler);
            InetAddress IP = InetAddress.getLocalHost();
            System.err.println("Server ready at " + IP.getHostAddress() + " port "+ 4456);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void render () {
        float dt =Gdx.graphics.getDeltaTime();
        if(gameServer.clients.size() == 2){
            ratecounter+=dt;
            if(ratecounter >= RATE){
                gameServer.clients.get(0).setGame(gameServer.transferWorld());
                gameServer.clients.get(1).setGame(gameServer.transferWorld());
                gameServer.setInput(1,gameServer.clients.get(0).getInputs());
                gameServer.setInput(2,gameServer.clients.get(1).getInputs());
                ratecounter = 0;
            }
            gameServer.updateWorld(dt);
        }
    }
}