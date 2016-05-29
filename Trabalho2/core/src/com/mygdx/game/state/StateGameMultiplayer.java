package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.input.Inputs;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.network.GameClient;


/**
 * Created by Nuno on 09/05/2016.
 */
public class StateGameMultiplayer extends State {
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private final static float RATE = 0.05f;
    private GameClient client;
    private GUIGame gameGUI;
    private float ratecounter;
    public StateGameMultiplayer(StateManager s) {
        super(s);

        ratecounter = 0f;
        gameGUI = new GUIGame();
        try{

            client = new GameClient();
            int joined = -1;
            if(client.proxy != null){
                joined = client.proxy.join(client);
            }
            if(joined == 0){
                System.out.println("Server full");
                dispose();
                sm.pop();
                sm.push(new StateMenu(sm));
            }
            if(joined == -1){
                System.out.println("Server offline");
                dispose();
                sm.pop();
                sm.push(new StateMenu(sm));
            }
        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
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
        if(client.getGame().isGameEnd()){
            dispose();
            sm.pop();
            sm.push(new StateMenu(sm));
        }
        try {
            ratecounter += dt;
            if (ratecounter >= RATE && client.proxy != null) {
                //client.getGame().updateGame(client.proxy.transferWorld());
                System.out.println(client.i.movingLeft);
                //client.proxy.sendInput(client,i);
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
        client.getGame().update(dt);
    }

    @Override
    public void render() {
        if(client == null){
            //System.out.println("presoClient");
            return;
        }
        if(client.getGame() != null){
            //System.out.println("Render");
            gameGUI.render(client.getGame());
        }else{
            //System.out.println("presoRender");
        }
    }

    @Override
    public void dispose(){
    }
}
