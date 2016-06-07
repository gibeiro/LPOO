package com.mygdx.game.socketnetwork;

import com.mygdx.game.input.Inputs;
import com.mygdx.game.logic.Game;

// TODO: Auto-generated Javadoc
/**
 * Classe que encapsula toda a informação do jogo a enviar pelo servidor ao client, e ao usar pelo client para atualizar o jogo.
 */
public class InfoGame {
    
    /** The p1t. */
    public float p1x,p1y,p1vx,p1vy,p1m,p1t;//Posicao, velocidade, mana, timer de poder instantaneo
    
    /** The p2t. */
    public float p2x,p2y,p2vx,p2vy,p2m,p2t;//Posicao, velocidade, mana, timer de poder instantaneo
    
    /** The bav. */
    public float bx,by,bvx,bvy,ba,bav;//Posicao, velocidade e velocidade angular
    
    /** The p2i. */
    public Inputs p1i,p2i;
    
    /**
     * Instantiates a new info game.
     */
    public InfoGame(){
        p1i = new Inputs(false,false,false,false);
        p2i = new Inputs(false,false,false,false);
    }
    
    /**
     * Instantiates a new info game.
     *
     * @param game the game
     */
    public InfoGame(Game game){
        p1i = new Inputs(false,false,false,false);
        p2i = new Inputs(false,false,false,false);
        p1x = game.getPlayer1().body.getPosition().x;
        p1y = game.getPlayer1().body.getPosition().y;
        p1vx = game.getPlayer1().body.getLinearVelocity().x;
        p1vy = game.getPlayer1().body.getLinearVelocity().y;
        p1m = (float)game.getPlayer1().getMana();
        p1t = game.getPlayer1().usedPowerTimer;
        p2x = game.getPlayer2().body.getPosition().x;
        p2y = game.getPlayer2().body.getPosition().y;
        p2vx = game.getPlayer2().body.getLinearVelocity().x;
        p2vy = game.getPlayer2().body.getLinearVelocity().y;
        p2m = (float)game.getPlayer2().getMana();
        p2t = game.getPlayer2().usedPowerTimer;
        bx = game.getBall().body.getPosition().x;
        by = game.getBall().body.getPosition().y;
        bvx = game.getBall().body.getLinearVelocity().x;
        bvy = game.getBall().body.getLinearVelocity().y;
        ba = game.getBall().body.getAngle();
        bav = game.getBall().body.getAngularVelocity();
        p1i.movingLeft = game.getPlayer1().getInputs().movingLeft;
        p1i.movingRight = game.getPlayer1().getInputs().movingRight;
        p1i.jump = game.getPlayer1().getInputs().jump;
        p1i.power = game.getPlayer1().getInputs().power;
        p2i.movingLeft = game.getPlayer2().getInputs().movingLeft;
        p2i.movingRight = game.getPlayer2().getInputs().movingRight;
        p2i.jump = game.getPlayer2().getInputs().jump;
        p2i.power = game.getPlayer2().getInputs().power;
    }
}
