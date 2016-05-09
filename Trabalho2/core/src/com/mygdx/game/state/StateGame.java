package com.mygdx.game.state;

import com.mygdx.game.Inputs;
import com.mygdx.game.client.Client;
import com.mygdx.game.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Functions;
import com.mygdx.game.body.Ball;
import com.mygdx.game.body.Obstacle;
import com.mygdx.game.body.Player;
import com.mygdx.game.server.ServerInterface;

import java.rmi.Naming;
import java.util.ArrayList;

//import lipermi.handler.CallHandler;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateGame extends State{

    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private Client client;
    private ServerInterface proxy;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;

    public StateGame(StateManager s) {
        super(s);

        try {
            client = new Client();
        }
        catch(Exception e){

        }

        try {

            proxy = (ServerInterface) Naming.lookup("rmi://localhost:1099/Server");

            System.out.print("Joining server ...");

            client.id = proxy.join(client);
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
        camera.setToOrtho(false,100,100*SCREENRESPROP);
        b2dr = new Box2DDebugRenderer();
    }


    @Override
    public void handleInput(){
        Inputs i = new Inputs(Functions.leftButtonPressed(),Functions.rightButtonPressed(),Functions.jumpButtonPressed());
        try{
            proxy.handleInput(client.id,i);
        }catch(Exception e){

        }

    }


    @Override
    public void update(double dt) {

        client.world.step((float)dt,6,2);

    }

    @Override
    public void render(SpriteBatch s) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(client.world,camera.combined);//render fixtures only
    }

    @Override
    public void dispose(){
    }
}
