package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gui.GUIGame;
import com.mygdx.game.gui.GUIPause;
import com.mygdx.game.gui.GUISelection;
import com.mygdx.game.gui.GUIWaiting;
import com.mygdx.game.socketnetwork.ClientGame;


// TODO: Auto-generated Javadoc
/**
 * Estado do jogo no modo multijogador.
 */
public class StateGameMultiplayer extends State {
    
    /** The Constant SCREENRESPROP. */
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    
    /** Taxa de envio de informacao ao servidor. */
    private final static float RATE = 0.05f;
    /**
     * Taxa de envio de mensagens de teste para evitar timeout.
     */
    private final static float TESTRATE = 1;
    
    /** The client. */
    private ClientGame client;
    
    /** The game gui. */
    private GUIGame gameGUI;
    
    /** The pause gui. */
    private GUIPause pauseGUI;
    
    /** The wait gui. */
    private GUIWaiting waitGUI;
    
    /** The select gui. */
    private GUISelection selectGUI;
    
    /** Contador da taxa de envio de informacao ao servidor. */
    private float ratecounter;
    /**
     * Contador da taxa de envio de mensagens de teste para evitar timeout.
     */
    private float testcounter;
    
    /**
     * Instantiates a new state game multiplayer.
     *
     * @param s the s
     */
    public StateGameMultiplayer(StateManager s) {
        super(s);
        ratecounter = 0f;
        testcounter = 0f;
        try{
            client = new ClientGame();
        }catch(Exception e){
            e.printStackTrace();
        }
        waitGUI = new GUIWaiting();
        selectGUI = new GUISelection();
        gameGUI = new GUIGame();
        pauseGUI = new GUIPause();
    }

    /**
     * Recebe input do jogador e atualiza
     */
    @Override
    public void handleInput(){
        if(client.inPause){
            if(pauseGUI.resume.isPressed()){
                client.inPause = false;
            }
            if(pauseGUI.exit.isPressed()){
                if(client.connected)
                    client.sendMessage("EXIT");
                dispose();
                sm.pop();
                sm.push(new StateMenu(sm));
            }
        }
        if(client.inWait){
            if(waitGUI.pauseButton.isPressed()){
                client.inPause = true;
            }
        }
        if(client.inSelect){
            if(client.powerSelected != selectGUI.selected)
                selectGUI.selected = -1;
            if(selectGUI.power0.isPressed()){
                client.powerSelected = 0;
                selectGUI.selected = 0;
            }
            if(selectGUI.power1.isPressed()){
                client.powerSelected = 1;
                selectGUI.selected = 1;
            }
            if(selectGUI.power2.isPressed()){
                client.powerSelected = 2;
                selectGUI.selected = 2;
            }
            if(selectGUI.power3.isPressed()){
                client.powerSelected = 3;
                selectGUI.selected = 3;
            }
            if(selectGUI.power4.isPressed()){
                client.powerSelected = 4;
                selectGUI.selected = 4;
            }
            if(selectGUI.pauseButton.isPressed()){
                client.inPause = true;
            }
        }
        if(client.inGame){
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
            if(selectGUI.pauseButton.isPressed()){
                client.inPause = true;
            }
        }

    }


    /* (non-Javadoc)
     * @see com.mygdx.game.state.State#update(double)
     */
    @Override
    public void update(double dt) {
        client.timeOutTimer -= dt;
        client.enemyLeftTimer -= dt;
        client.youWonTimer -= dt;
        client.youLostTimer -= dt;
        waitGUI.timeOutTimer = client.timeOutTimer;
        waitGUI.enemyLeftTimer = client.enemyLeftTimer;
        waitGUI.youWonTimer = client.youWonTimer;
        waitGUI.youLostTimer = client.youLostTimer;
        ratecounter += dt;
        testcounter += dt;
        if(client.timedOut == true){
            if(waitGUI.timeOutTimer < 0){
                dispose();
                sm.pop();
                sm.push(new StateMenu(sm));
            }

        }
        if(client.inGame){
            client.game.update(dt);
        }
        if(testcounter >= TESTRATE && client.connected == true){
            client.sendMessage("TEST");
        }
        if(ratecounter >= RATE && client.connected == true){
            if(client.inSelect){
                if(client.powerSelected != -1){
                    String s = "";
                    s+="POWER";
                    s+="\n";
                    s+=client.powerSelected;
                    client.sendMessage(s);
                }
            }else if(client.inGame){
                client.sendInfo();
            }
            ratecounter = 0;
        }
    }

    /* (non-Javadoc)
     * @see com.mygdx.game.state.State#render()
     */
    @Override
    public void render() {
        if(client.inWait){
            waitGUI.render();
        }else if(client.inSelect){
            selectGUI.render();
        }else if(client.inGame){
            gameGUI.render(client.game);
        }
        if(client.inPause){
            pauseGUI.render();
        }

    }

    /* (non-Javadoc)
     * @see com.mygdx.game.state.State#dispose()
     */
    @Override
    public void dispose(){
        gameGUI.dispose();
        waitGUI.dispose();
        pauseGUI.dispose();
        selectGUI.dispose();
    }
}
