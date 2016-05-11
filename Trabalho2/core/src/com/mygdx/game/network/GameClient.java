package com.mygdx.game.network;

import com.badlogic.gdx.physics.box2d.World;

import lipermi.handler.CallHandler;
import lipermi.net.Client;


/**
 * Created by Nuno on 10/05/2016.
 */
public class GameClient implements ClientInterface {
    World world;
    int id;
    Client c;
    public ServerInterface proxy;


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setWorld(World o) {
        world = o;
    }

    @Override
    public World getWorld() {
        return world;
    }

    public GameClient(){
        try {
            CallHandler callHandler = new CallHandler();
            String remoteHost = "localhost";
            int portWasBinded = 4455;
            c = new Client(remoteHost,portWasBinded,callHandler);
            proxy = (ServerInterface)c.getGlobal(ServerInterface.class);
        }catch(Exception e){

        }
    }
}
