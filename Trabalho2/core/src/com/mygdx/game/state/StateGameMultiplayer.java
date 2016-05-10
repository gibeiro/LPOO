package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.auxclass.Inputs;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.logic.Game;
//import com.mygdx.game.client.Client;
//import com.mygdx.game.server.ServerInterface;


/**
 * Created by Nuno on 09/05/2016.
 */
public class StateGameMultiplayer extends State {
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
   // private Client client;
    private GUIGame gamerenderer;

    public StateGameMultiplayer(StateManager s) {
        super(s);

        gamerenderer = new GUIGame();
        try {
            System.out.println("aqui1");
            //client = new Client();
            System.out.println("aqui2");
        }
        catch(Exception e){

        }

        try {
/*
            client.proxy = (ServerInterface) Naming.lookup("rmi://localhost:1099/Server");

            System.out.print("Joining server ...");

            client.id = client.proxy.join(client);
            if ( client.id == -1) {
                System.out.println("Server full.");
                return;
            }
            else
                System.out.println("Connected.");
                */

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }


    @Override
    public void handleInput(){
        Inputs i = new Inputs(Functions.leftButtonPressed(),Functions.rightButtonPressed(),Functions.jumpButtonPressed());
        try{
           // client.proxy.handleInput(client.id,i);
        }catch(Exception e){

        }

    }


    @Override
    public void update(double dt) {
/*
        if(client.world != null)
           client.world.step((float)dt,6,2);
*/
    }

    @Override
    public void render() {
        gamerenderer.render(new Game());
    }

    @Override
    public void dispose(){
    }
}
