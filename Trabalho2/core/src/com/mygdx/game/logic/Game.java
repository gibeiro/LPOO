package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.auxclass.Functions;

/**
 * Created by up201403877 on 09-05-2016.
 */
public class Game {

    private final static float  GRAVITY = -100f;
    public World world;
    public Player player1;
    public Player player2;
    private Ball ball;
    private Obstacle field;
    private int limitGoals;
    public boolean gameEnd;
    public Game(){
        world = new World(new Vector2(0,GRAVITY),false);
        ball = new Ball(world,50,50);
        field = new Obstacle(world);
        gameEnd = false;
        limitGoals = 5;
    }

    public void update(double dt){
        /*
         * Verifica se a bola está dentro das balizas
         */
        if(player1.goals >=limitGoals){
            gameEnd = true;
            return;
        }

        float x = ball.body.getPosition().x;
        float y = ball.body.getPosition().y;
        if(x > 86 && y < 28){
            player1.goals++;
            resetPositions();
        }else if(x < 14 && y < 28){
            if(player2 != null)
                player2.goals++;
            resetPositions();
        }



        /*
         * Verifica movimentos do jogador 1
         */
        world.step((float)dt,6,2);
        if(player1.movingLeft){
            //float impulse = player1.body.getMass() * -5;
            //player1.body.applyLinearImpulse(new Vector2(impulse,0),player1.body.getWorldCenter(),false);
            player1.body.setLinearVelocity(player1.body.getLinearVelocity().x-0.5f*(float)dt*1000,player1.body.getLinearVelocity().y);
        }
        if(player1.movingRight){
            //float impulse = player1.body.getMass() * 5;
            //player1.body.applyLinearImpulse(new Vector2(impulse,0),player1.body.getWorldCenter(),false);
            player1.body.setLinearVelocity(player1.body.getLinearVelocity().x+0.5f*(float)dt*1000,player1.body.getLinearVelocity().y);
        }
        if(player1.jump && Math.abs(player1.body.getLinearVelocity().y) < 0.2 && Functions.PlayerColidingWithGround(world, player1, field)){
            //float impulse = player1.body.getMass() * 60;
            //player1.body.applyLinearImpulse(new Vector2(0,impulse),player1.body.getWorldCenter(),false);
            player1.body.setLinearVelocity(player1.body.getLinearVelocity().x,player1.body.getLinearVelocity().y+3.5f*(float)dt*1000);
            player1.jump = false;

        }else player1.jump = false;

        if(player1.body.getLinearVelocity().x > 50){
            player1.body.setLinearVelocity(50,player1.body.getLinearVelocity().y);
        }
        if(player1.body.getLinearVelocity().x < -50){
            player1.body.setLinearVelocity(-50,player1.body.getLinearVelocity().y);
        }
    }
    public void resetPositions(){
        ball.body.setTransform(50,50,0);
        ball.body.setLinearVelocity(0,0);
        ball.body.setAngularVelocity(0);
        player1.body.setTransform(20,15,0);
        player1.body.setLinearVelocity(0,0);
        //player2.body.setTransform(80,15,0);
        //player2.body.setLinearVelocity(0,0);
    }


    public void dispose() {
        world.dispose();
    }
}
