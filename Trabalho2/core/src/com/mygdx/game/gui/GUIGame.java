package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.logic.Game;


/**
 * Created by Nuno on 10/05/2016.
 */
public class GUIGame {
    private final static float SCREENRESPROP = (float) Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    private SpriteBatch sprites;
    public GUIGame(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false,100,100*SCREENRESPROP);
        b2dr = new Box2DDebugRenderer();
        sprites = new SpriteBatch();
    }

    public void render(Game game){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(game.world,camera.combined);//render fixtures only
        sprites.begin();
        //s.draw(ball.texture,ball.body.getPosition().x*2-50,ball.body.getPosition().y*2-50,100,100);
        sprites.end();
    }
}
