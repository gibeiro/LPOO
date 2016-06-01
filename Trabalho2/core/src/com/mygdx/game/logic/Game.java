package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.socketnetwork.InfoGame;

/**
 * Created by up201403877 on 09-05-2016.
 */
public class Game {

    private final static float  GRAVITY = -120f;
    private World world;
    private Player player1;
    private Player player2;
    private Ball ball;
    private Obstacle field;
    private int limitGoals;
    private boolean gameEnd;
    private float countdown;
    public Game(){
        countdown = 3;
        world = new World(new Vector2(0,GRAVITY),false);
        ball = new Ball(world,50,50);
        field = new Obstacle(world);
        gameEnd = false;
        limitGoals = 5;
    }
    public World getWorld(){
        return world;
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Ball getBall(){return ball;}

    public void setWorld(World world) {
        this.world = world;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setLimitGoals(int limitGoals) {
        this.limitGoals = limitGoals;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void setField(Obstacle field) {
        this.field = field;
    }

    public void setGameEnd(boolean b){this.gameEnd = b;}


    public void update(double dt){
        /*
         * Verifica se a bola está dentro das balizas
         */
        if(player1.goals >=limitGoals || player2.goals >= limitGoals){
            gameEnd = true;
        }
        if(countdown > 0){
            countdown -= dt;
            return;
        }
        player1.setJumpCounter(player1.getJumpCounter()-dt);
        player2.setJumpCounter(player2.getJumpCounter()-dt);


        world.step(1/100f,6,2);
        /*
         * Verifica movimentos do jogador 1
         */
        if(player1.inputs.getMovingLeft()){
            if(player1.body.getLinearVelocity().x > 0)
                player1.body.setLinearVelocity(0,player1.body.getLinearVelocity().y);
            player1.body.setLinearVelocity(player1.body.getLinearVelocity().x-0.5f*(float)dt*1000,player1.body.getLinearVelocity().y);
        }
        if(player1.inputs.getMovingRight()){
            if(player1.body.getLinearVelocity().x < 0)
                player1.body.setLinearVelocity(0,player1.body.getLinearVelocity().y);
            player1.body.setLinearVelocity(player1.body.getLinearVelocity().x+0.5f*(float)dt*1000,player1.body.getLinearVelocity().y);
        }
        if(player1.inputs.getJump() && player1.getJumpCounter() < 0 && Functions.PlayerColidingWithGround(world, player1, field)){
            player1.body.setLinearVelocity(player1.body.getLinearVelocity().x,player1.body.getLinearVelocity().y+60f);
            player1.inputs.setJump(false);
            player1.setJumpCounter(1);

        }else player1.inputs.setJump(false);
        /*
         * Limita velocidade do jogador
         */
        if(player1.body.getLinearVelocity().x > 50){
            player1.body.setLinearVelocity(50,player1.body.getLinearVelocity().y);
        }
        if(player1.body.getLinearVelocity().x < -50){
            player1.body.setLinearVelocity(-50,player1.body.getLinearVelocity().y);
        }

        /*
         * Verifica movimentos do jogador 2
         */


        if(player2.inputs.getMovingLeft()){
            if(player2.body.getLinearVelocity().x > 0)
                player2.body.setLinearVelocity(0,player2.body.getLinearVelocity().y);
            player2.body.setLinearVelocity(player2.body.getLinearVelocity().x-0.5f*(float)dt*1000,player2.body.getLinearVelocity().y);
        }
        if(player2.inputs.getMovingRight()){
            if(player2.body.getLinearVelocity().x < 0)
                player2.body.setLinearVelocity(0,player2.body.getLinearVelocity().y);
            player2.body.setLinearVelocity(player2.body.getLinearVelocity().x+0.5f*(float)dt*1000,player2.body.getLinearVelocity().y);
        }
        if(player2.inputs.getJump() && player2.getJumpCounter() < 0 && Functions.PlayerColidingWithGround(world, player2, field)){
            player2.body.setLinearVelocity(player2.body.getLinearVelocity().x,player2.body.getLinearVelocity().y+60f);
            player2.inputs.setJump(false);
            player2.setJumpCounter(1);

        }else player2.inputs.setJump(false);
        /*
         * Limita velocidade do jogador
         */
        if(player2.body.getLinearVelocity().x > 50){
            player2.body.setLinearVelocity(50,player2.body.getLinearVelocity().y);
        }
        if(player2.body.getLinearVelocity().x < -50){
            player2.body.setLinearVelocity(-50,player2.body.getLinearVelocity().y);
        }


        player1.setMana(player1.getMana()+dt*14);
        player2.setMana(player2.getMana()+dt*14);


        checkPowers(dt);

    }
    public void checkGoals(){
        float x = ball.body.getPosition().x;
        float y = ball.body.getPosition().y;
        if(x > 89 && y < 28){
            player1.goals++;
            countdown = 3;
            resetPositions();
        }else if(x < 11 && y < 28){
            player2.goals++;
            countdown = 3;
            resetPositions();
        }
    }
    public void resetPositions(){
        ball.body.setTransform(50,50,0);
        ball.body.setLinearVelocity(0,0);
        ball.body.setAngularVelocity(0);
        player1.body.setTransform(20,15,0);
        player1.body.setLinearVelocity(0,0);
        player2.body.setTransform(80,15,0);
        player2.body.setLinearVelocity(0,0);
    }
    public void checkPowers(double dt){
        player1.usePower(this,dt);
        player2.usePower(this,dt);
    }


    public void updateGame(InfoGame g){
        player1.body.setTransform(new Vector2(g.p1x,g.p1y),0);
        player2.body.setTransform(new Vector2(g.p2x,g.p2y),0);
        player1.body.setLinearVelocity(g.p1vx,g.p1vy);
        player2.body.setLinearVelocity(g.p2vx,g.p2vy);
        ball.body.setTransform(new Vector2(g.bx,g.by),g.ba);
        ball.body.setLinearVelocity(g.bvx,g.bvy);
        ball.body.setAngularVelocity(g.bav);
    }


    public void dispose() {
        world.dispose();
    }


    public boolean isGameEnd() {
        return gameEnd;
    }

    public float getCountdown() {
        return countdown;
    }

    public void setCountdown(float countdown) {
        this.countdown = countdown;
    }
}
