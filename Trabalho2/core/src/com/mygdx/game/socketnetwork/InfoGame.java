package com.mygdx.game.socketnetwork;

import com.mygdx.game.logic.Game;

/**
 * Created by Nuno on 30/05/2016.
 */
public class InfoGame {
    public float p1x,p1y,p1vx,p1vy;//Posicao, velocidade
    public float p2x,p2y,p2vx,p2vy;//Posicao, velocidade
    public float bx,by,bvx,bvy,ba,bav;//Posicao, velocidade e velocidade angular
    public InfoGame(){

    }
    public InfoGame(Game game){
        p1x = game.getPlayer1().body.getPosition().x;
        p1y = game.getPlayer1().body.getPosition().y;
        p1vx = game.getPlayer1().body.getLinearVelocity().x;
        p1vy = game.getPlayer1().body.getLinearVelocity().y;
        p2x = game.getPlayer2().body.getPosition().x;
        p2y = game.getPlayer2().body.getPosition().y;
        p2vx = game.getPlayer2().body.getLinearVelocity().x;
        p2vy = game.getPlayer2().body.getLinearVelocity().y;
        bx = game.getBall().body.getPosition().x;
        by = game.getBall().body.getPosition().y;
        bvx = game.getBall().body.getLinearVelocity().x;
        bvy = game.getBall().body.getLinearVelocity().y;
        ba = game.getBall().body.getAngle();
        bav = game.getBall().body.getAngularVelocity();
    }
}
