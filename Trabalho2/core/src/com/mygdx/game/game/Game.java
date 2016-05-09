package com.mygdx.game.game;

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

/**
 * Created by up201403877 on 09-05-2016.
 */
public class Game {
    private BitmapFont scorefont;
    private final static float  GRAVITY = -100f;
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    public World world;
    public Player player1;
    public Player player2;
    private Ball ball;
    private Obstacle field;
    private int limitGoals;
    public boolean gameEnd;
    public Game(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false,100,100*SCREENRESPROP);
        world = new World(new Vector2(0,GRAVITY),false);
        b2dr = new Box2DDebugRenderer();
        ball = new Ball(world,50,50);
        player1 = new Player(world,20,15);
        field = new Obstacle(world);
        scorefont = new BitmapFont();
        gameEnd = false;
        limitGoals = 5;
    }
    public void handleInput(){
        if(Functions.leftButtonPressed()){
            player1.movingLeft = true;
        }else player1.movingLeft = false;
        if(Functions.rightButtonPressed()){
            player1.movingRight = true;
        }else player1.movingRight = false;
        if(Functions.jumpButtonPressed()){
            player1.jump = true;
        }else player1.jump = false;
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
    public void render(SpriteBatch s){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world,camera.combined);//render fixtures only
        s.begin();
        //s.draw(ball.texture,ball.body.getPosition().x*2-50,ball.body.getPosition().y*2-50,100,100);
        s.end();
    }

    public void dispose() {
        world.dispose();
        b2dr.dispose();
        ball.texture.dispose();
    }
}
