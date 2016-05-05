package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Functions;
import com.mygdx.game.body.Ball;
import com.mygdx.game.body.Obstacle;
import com.mygdx.game.body.Player;

import java.util.ArrayList;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateGame extends State{
    private final static float  GRAVITY = -100f;
    private final static float SCREENRESPROP = (float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight();
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    private World world;
    private Player player1;
    private Player player2;
    private Ball ball;
    private Obstacle floor;

    public StateGame(StateManager s) {
        super(s);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,100*SCREENRESPROP,100);
        world = new World(new Vector2(0,GRAVITY),false);
        b2dr = new Box2DDebugRenderer();
        ball = new Ball(world,90,90);
        player1 = new Player(world,30,15);
        floor = new Obstacle(world);
    }

    @Override
    public void handleInput(){

        if(Functions.leftButtonPressed() && player1.body.getLinearVelocity().x > -50){
            float impulse = player1.body.getMass() * -5;
            player1.body.applyLinearImpulse(new Vector2(impulse,0),player1.body.getWorldCenter(),false);
        }
        if(Functions.rightButtonPressed() && player1.body.getLinearVelocity().x < 50){
            float impulse = player1.body.getMass() * 5;
            player1.body.applyLinearImpulse(new Vector2(impulse,0),player1.body.getWorldCenter(),false);
        }
        if(Functions.jumpButtonPressed() && Functions.PlayerColidingWithGround(world, player1,floor) && Math.abs(player1.body.getLinearVelocity().y) < 0.2){
            float impulse = player1.body.getMass() * 70;
            player1.body.applyLinearImpulse(new Vector2(0,impulse),player1.body.getWorldCenter(),false);
        }




    }


    @Override
    public void update(double dt) {


        world.step((float)dt,6,2);


    }

    @Override
    public void render(SpriteBatch s) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world,camera.combined);//render fixtures only
        s.begin();
        //s.draw(ball.texture,ball.body.getPosition().x*2-50,ball.body.getPosition().y*2-50,100,100);
        s.end();
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        ball.texture.dispose();
    }
}
