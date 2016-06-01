package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.mygdx.game.logic.Game;
import com.mygdx.game.socketnetwork.InfoGame;
import com.mygdx.game.socketnetwork.ServerGame;


public class BounceBallServer extends ApplicationAdapter {
    private final static float RATE = 0.06f;
    float rateCounter;
    ServerGame server;
    float dt;
    @Override
    public void create () {
        rateCounter = 0;
        try{
            server = new ServerGame();
        }catch(Exception e){

        }
    }

    @Override
    public void render () {

        while(server.handler1 == null || server.handler2 == null){
            System.out.println("Abriu slot");
            server.openPlayerSlot();
        };
        dt = Gdx.graphics.getDeltaTime();

        if(server.handler1.connected == true){
            server.handler1.sendMessage("TEST");//Testar se está ligado

            server.handler1.timeoutCounter += dt;

            if(server.handler1.timeoutCounter >= 10){
                server.handler1 = null;
                server.game = new Game();
            }
        }
        if(server.handler2.connected == true){
            server.handler2.sendMessage("TEST");
            server.handler2.timeoutCounter += dt;
            if(server.handler2.timeoutCounter >= 10){
                server.handler2 = null;
                server.game = new Game();
            }
        }
        if(server.handler1.connected == true && server.handler2.connected == true && !server.game.isGameEnd()){

            server.game.update(dt);
            rateCounter += dt;

            if(rateCounter >= RATE){
                InfoGame info = new InfoGame(server.game);

                server.handler1.sendPos(info);
                server.handler2.sendPos(info);
                rateCounter = 0;
            }
        }
    }
}