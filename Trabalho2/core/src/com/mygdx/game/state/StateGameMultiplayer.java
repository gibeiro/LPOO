package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.Functions;
import com.mygdx.game.Inputs;
import com.mygdx.game.client.Client;
import com.mygdx.game.server.ServerInterface;

import java.rmi.Naming;

/**
 * Created by Nuno on 09/05/2016.
 */
public class StateGameMultiplayer extends State {
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private Client client;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;

    public StateGameMultiplayer(StateManager s) {
        super(s);

        try {
            System.out.println("aqui1");
            client = new Client();
            System.out.println("aqui2");
        }
        catch(Exception e){

        }

        try {

            client.proxy = (ServerInterface) Naming.lookup("rmi://localhost:1099/Server");

            System.out.print("Joining server ...");

            client.id = client.proxy.join(client);
            if ( client.id == -1) {
                System.out.println("Server full.");
                return;
            }
            else
                System.out.println("Connected.");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false,100,100*SCREENRESPROP);
        b2dr = new Box2DDebugRenderer();
    }


    @Override
    public void handleInput(){
        Inputs i = new Inputs(Functions.leftButtonPressed(),Functions.rightButtonPressed(),Functions.jumpButtonPressed());
        try{
            client.proxy.handleInput(client.id,i);
        }catch(Exception e){

        }

    }


    @Override
    public void update(double dt) {

        if(client.world != null)
           client.world.step((float)dt,6,2);

    }

    @Override
    public void render(SpriteBatch s) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(client.world != null)
            b2dr.render(client.world,camera.combined);//render fixtures only
    }

    @Override
    public void dispose(){
    }
}
