package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.input.Inputs;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.socketnetwork.ClientGame;


/**
 * Created by Nuno on 09/05/2016.
 */
public class StateGameMultiplayer extends State {
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private final static float RATE = 0.05f;
    private ClientGame client;
    private GUIGame gameGUI;
    private float ratecounter;
    public StateGameMultiplayer(StateManager s) {
        super(s);
        ratecounter = 0f;
        gameGUI = new GUIGame();
        try{
            client = new ClientGame();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void handleInput(){
        if(gameGUI.leftButton.isPressed()){
           client.i.setMovingLeft(true);
        }else client.i.setMovingLeft(false);
        if(gameGUI.rightButton.isPressed()){
            client.i.setMovingRight(true);
        }else client.i.setMovingRight(false);
        if(gameGUI.jumpButton.isPressed()){
            client.i.setJump(true);
        }else client.i.setJump(false);
        if(gameGUI.powerButton.isPressed()){
            client.i.setPower(true);
        }else client.i.setPower(false);
    }


    @Override
    public void update(double dt) {
        ratecounter += dt;
        if(ratecounter >= RATE){
            try{
                if(client.connected)
                     client.sendInfo();
            }catch(Exception e){

            }
            ratecounter = 0;
        }
        if(client.connected){
            client.game.update(dt);
        }
    }

    @Override
    public void render() {
        if(client == null){
            return;
        }
        if(client.game != null){
            gameGUI.render(client.game);
        }else{
        }
    }

    @Override
    public void dispose(){
    }
}
