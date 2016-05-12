package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.input.RectButton;
import com.mygdx.game.logic.Game;


/**
 * Created by Nuno on 10/05/2016.
 */
public class GUIGame {
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    private SpriteBatch sprites;
    public RectButton leftButton;
    public RectButton rightButton;
    public RectButton jumpButton;
    public RectButton powerButton;
    public GUIGame(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false,100,100*SCREENRESPROP);
        b2dr = new Box2DDebugRenderer();
        sprites = new SpriteBatch();
        leftButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.0),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.15),
                (int)(Gdx.graphics.getHeight()*1.0),
                "null");
        rightButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.15),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.30),
                (int)(Gdx.graphics.getHeight()*1.0),
                "null");
        powerButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.7),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.85),
                (int)(Gdx.graphics.getHeight()*1.0),
                "null");
        jumpButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.85),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*1.0),
                (int)(Gdx.graphics.getHeight()*1.0),
                "null");
    }

    public void render(Game game){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(game.getWorld(),camera.combined);//render fixtures only
        sprites.begin();
        //s.draw(ball.texture,ball.body.getPosition().x*2-50,ball.body.getPosition().y*2-50,100,100);
        sprites.end();
    }

    public void render(World o){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(o,camera.combined);//render fixtures only
        sprites.begin();
        leftButton.render(sprites);
        rightButton.render(sprites);
        jumpButton.render(sprites);
        powerButton.render(sprites);
        //s.draw(ball.texture,ball.body.getPosition().x*2-50,ball.body.getPosition().y*2-50,100,100);
        sprites.end();
    }
}
