package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private SpriteBatch buttons;
    private SpriteBatch objects;
    private Texture grass;
    private Texture sky;
    private TextureRegion ball;
    private Texture player1;
    private Texture player2;
    private Texture net;
    public RectButton leftButton;
    public RectButton rightButton;
    public RectButton jumpButton;
    public RectButton powerButton;
    public RectButton pauseButton;
    public GUIGame(Game game){
        camera = new OrthographicCamera();
        camera.setToOrtho(false,100,100*SCREENRESPROP);
        b2dr = new Box2DDebugRenderer();
        buttons = new SpriteBatch();
        objects = new SpriteBatch();
        buttons.enableBlending();
        buttons.setColor(buttons.getColor().r,buttons.getColor().g,buttons.getColor().b,0.2f);

        grass = new Texture("floor.png");

        sky = new Texture("sky.png");

        ball = new TextureRegion(new Texture("rasengan.png"));

        net = new Texture("net.png");

        if(game.getPlayer1().getPower().getIndex() == 0){
            player1 = new Texture("defaultplayer.png");
        }else player1 = new Texture("defaultplayer.png");
        if(game.getPlayer1().getPower().getIndex() == 0){
            player2 = new Texture("defaultplayer.png");
        }else player2 = new Texture("defaultplayer.png");



        leftButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.0),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.20),
                (int)(Gdx.graphics.getHeight()*1.0),
                "leftbuttonup.png","leftbuttondown.png");
        rightButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.20),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.40),
                (int)(Gdx.graphics.getHeight()*1.0),
                "rightbuttonup.png","rightbuttondown.png");
        powerButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.6),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.8),
                (int)(Gdx.graphics.getHeight()*1.0),
                "powerbuttonup.png","powerbuttondown.png");
        jumpButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.8),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*1),
                (int)(Gdx.graphics.getHeight()*1.0),
                "jumpbuttonup.png","jumpbuttondown.png");
        pauseButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.9),
                (int)(Gdx.graphics.getHeight()*0.02),
                (int)(Gdx.graphics.getWidth()*1),
                (int)(Gdx.graphics.getHeight()*0.17),
                "pausebuttonup.png","pausebuttondown.png");


    }

    public void render(Game game){
        //Preenche o ecra com preto
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //Desenha sprites do mundo
        objects.begin();

        objects.draw(sky,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        float p1x = game.getPlayer1().body.getPosition().x;
        float p1y = game.getPlayer1().body.getPosition().y;
        float p2x = game.getPlayer1().body.getPosition().x;
        float p2y = game.getPlayer1().body.getPosition().y;
        float bx = game.getBall().body.getPosition().x;
        float by = game.getBall().body.getPosition().y;

        objects.draw(player1,Gdx.graphics.getWidth()*(p1x/100f)-Gdx.graphics.getWidth()*0.1f/2,Gdx.graphics.getHeight()/SCREENRESPROP*(p1y/100f),Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.05f/SCREENRESPROP);

        objects.draw(player2,Gdx.graphics.getWidth()*(p2x/100f)-Gdx.graphics.getWidth()*0.1f/2,Gdx.graphics.getHeight()/SCREENRESPROP*(p2y/100f),Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.05f/SCREENRESPROP);

        float bdrawx = Gdx.graphics.getWidth()*(bx/100f)-Gdx.graphics.getWidth()*0.046f/2;
        float bdrawy = Gdx.graphics.getHeight()*(by/100f)/SCREENRESPROP-Gdx.graphics.getHeight()*0.046f/2/SCREENRESPROP;
        objects.draw(net,Gdx.graphics.getWidth()*(4/100f),Gdx.graphics.getHeight()*(10/100f)/SCREENRESPROP,Gdx.graphics.getWidth()*(8/100f),Gdx.graphics.getHeight()*(16/100f)/SCREENRESPROP,0,0,net.getWidth(),net.getHeight(),true,false);
        objects.draw(net,Gdx.graphics.getWidth()*(89/100f),Gdx.graphics.getHeight()*(10/100f)/SCREENRESPROP,Gdx.graphics.getWidth()*(8/100f),Gdx.graphics.getHeight()*(16/100f)/SCREENRESPROP,0,0,net.getWidth(),net.getHeight(),false,false);

        objects.draw(ball,bdrawx,bdrawy,Gdx.graphics.getWidth()*0.046f/2,Gdx.graphics.getHeight()*0.046f/SCREENRESPROP/2,Gdx.graphics.getWidth()*0.046f,Gdx.graphics.getHeight()*0.046f/SCREENRESPROP,1.1f,1.1f,game.getBall().body.getAngle()*100);

        objects.draw(grass,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/SCREENRESPROP*0.1f*1.5f);

        objects.end();




        b2dr.render(game.getWorld(),camera.combined);//render fixtures only



        //Desenha sprites dos botoes
        buttons.begin();
        leftButton.render(buttons);
        rightButton.render(buttons);
        jumpButton.render(buttons);
        powerButton.render(buttons);
        pauseButton.render(buttons);
        buttons.end();
    }

}
