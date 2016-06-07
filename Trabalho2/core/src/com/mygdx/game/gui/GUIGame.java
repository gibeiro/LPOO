package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.input.RectButton;
import com.mygdx.game.logic.Game;


/**
 * Interface do jogo
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
    private Texture stop;
    private TextureRegion fireball;
    private float fireballrotatecounter;
    private Texture switchdir;
    private Texture magnet;
    private Texture defaultTex;
    private Texture stopTex;
    private Texture flipTex;
    private Texture magnetTex;
    private Texture rocketTex;
    private Texture net;
    private Texture n0;
    private Texture n1;
    private Texture n2;
    private Texture n3;
    private Texture n4;
    private Texture n5;
    private Texture manabar;
    private Texture mana;
    public RectButton leftButton;
    public RectButton rightButton;
    public RectButton jumpButton;
    public RectButton powerButton;
    public RectButton pauseButton;
    public GUIGame(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false,100,100*SCREENRESPROP);
        b2dr = new Box2DDebugRenderer();
        buttons = new SpriteBatch();
        objects = new SpriteBatch();
        buttons.enableBlending();
        buttons.setColor(buttons.getColor().r,buttons.getColor().g,buttons.getColor().b,0.2f);

        grass = new Texture("sprites/floor.png");

        sky = new Texture("sprites/sky.png");

        ball = new TextureRegion(new Texture("sprites/ball.png"));

        stop = new Texture("sprites/stop.png");
        fireball = new TextureRegion(new Texture("sprites/fireball.png"));
        fireballrotatecounter = 0;
        switchdir = new Texture("sprites/flip.png");
        magnet = new Texture("sprites/magnet.png");

        net = new Texture("sprites/net.png");

        defaultTex = new Texture("sprites/defaultplayer.png");
        stopTex = new Texture("sprites/herostop.png");
        flipTex = new Texture("sprites/flipplayer.png");
        magnetTex = new Texture("sprites/magnetplayer.png");
        rocketTex = new Texture("sprites/rocketslime.png");

        manabar = new Texture("sprites/manabar.png");
        mana = new Texture("sprites/mana.png");


        leftButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.0),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.20),
                (int)(Gdx.graphics.getHeight()*1.0),
                "buttons/leftbuttonup.png","buttons/leftbuttondown.png",false);
        rightButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.20),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.40),
                (int)(Gdx.graphics.getHeight()*1.0),
                "buttons/rightbuttonup.png","buttons/rightbuttondown.png",false);
        powerButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.6),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*0.8),
                (int)(Gdx.graphics.getHeight()*1.0),
                "buttons/powerbuttonup.png","buttons/powerbuttondown.png",false);
        jumpButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.8),
                (int)(Gdx.graphics.getHeight()*0.7),
                (int)(Gdx.graphics.getWidth()*1),
                (int)(Gdx.graphics.getHeight()*1.0),
                "buttons/jumpbuttonup.png","buttons/jumpbuttondown.png",false);
        pauseButton = new RectButton(
                (int)(Gdx.graphics.getWidth()*0.9),
                (int)(Gdx.graphics.getHeight()*0.02),
                (int)(Gdx.graphics.getWidth()*1),
                (int)(Gdx.graphics.getHeight()*0.17),
                "buttons/pausebuttonup.png","buttons/pausebuttondown.png",true);

        n0 = new Texture("text/0.png");
        n1 = new Texture("text/1.png");
        n2 = new Texture("text/2.png");
        n3 = new Texture("text/3.png");
        n4 = new Texture("text/4.png");
        n5 = new Texture("text/5.png");

    }
    public void dispose(){
        ball.getTexture().dispose();
        n0.dispose();
        n1.dispose();
        n2.dispose();
        n3.dispose();
        n4.dispose();
        n5.dispose();
        leftButton.dispose();
        rightButton.dispose();
        jumpButton.dispose();
        powerButton.dispose();
        pauseButton.dispose();
        grass.dispose();
        sky.dispose();
        net.dispose();
        defaultTex.dispose();
        magnetTex.dispose();
        flipTex.dispose();
        rocketTex.dispose();
        stopTex.dispose();
        mana.dispose();
        manabar.dispose();
        stop.dispose();
        fireball.getTexture().dispose();;
        switchdir.dispose();
        magnet.dispose();

    }

    public void render(Game game){
        //Preenche o ecra com preto
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //Desenha sprites do mundo
        objects.begin();


        //Ceu
        objects.draw(sky,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        //Jogadores
        drawPlayers(objects,game);


        //Bola e poderes
        drawBallAndPowers(objects,game);


        //Balizas
        objects.draw(net, Gdx.graphics.getWidth() * (4 / 100f), Gdx.graphics.getHeight() * (10 / 100f) / SCREENRESPROP, Gdx.graphics.getWidth() * (8 / 100f), Gdx.graphics.getHeight() * (16 / 100f) / SCREENRESPROP, 0, 0, net.getWidth(), net.getHeight(), true, false);
        objects.draw(net,Gdx.graphics.getWidth()*(89/100f),Gdx.graphics.getHeight()*(10/100f)/SCREENRESPROP,Gdx.graphics.getWidth()*(8/100f),Gdx.graphics.getHeight()*(16/100f)/SCREENRESPROP,0,0,net.getWidth(),net.getHeight(),false,false);

        //Relva
        objects.draw(grass, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / SCREENRESPROP * 0.1f * 1.5f);

        //Pontuacao
        drawScores(objects, game);

        //Mana
        drawMana(objects, game);

        //Countdown
        drawCountDown(objects, game);
        objects.end();

        //b2dr.render(game.getWorld(),camera.combined);//render fixtures only

        //Desenha sprites dos botoes
        buttons.begin();
        leftButton.render(buttons);
        rightButton.render(buttons);
        jumpButton.render(buttons);
        powerButton.render(buttons);
        pauseButton.render(buttons);
        buttons.end();
    }

    /**
     * Desenha as pontuações usando as sprites 0 a 5
     */
    void drawScores(SpriteBatch objects,Game game){
        if(game.getPlayer1().getGoals() == 0){
            objects.draw(n0,Gdx.graphics.getWidth()*0.35f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer1().getGoals() == 1){
            objects.draw(n1,Gdx.graphics.getWidth()*0.35f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer1().getGoals() == 2){
            objects.draw(n2,Gdx.graphics.getWidth()*0.35f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer1().getGoals() == 3){
            objects.draw(n3,Gdx.graphics.getWidth()*0.35f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer1().getGoals() == 4){
            objects.draw(n4,Gdx.graphics.getWidth()*0.35f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer1().getGoals() == 5){
            objects.draw(n5,Gdx.graphics.getWidth()*0.35f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }

        if(game.getPlayer2().getGoals() == 0){
            objects.draw(n0,Gdx.graphics.getWidth()*0.55f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer2().getGoals() == 1){
            objects.draw(n1,Gdx.graphics.getWidth()*0.55f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer2().getGoals() == 2){
            objects.draw(n2,Gdx.graphics.getWidth()*0.55f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer2().getGoals() == 3){
            objects.draw(n3,Gdx.graphics.getWidth()*0.55f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer2().getGoals() == 4){
            objects.draw(n4,Gdx.graphics.getWidth()*0.55f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }else if(game.getPlayer2().getGoals() == 5){
            objects.draw(n5,Gdx.graphics.getWidth()*0.55f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        }
    }

    /**
     * Desenha os jogadores consoante os poderes escolhidos
     */
    void drawPlayers(SpriteBatch objects,Game game){
        float p1x = game.getPlayer1().body.getPosition().x;
        float p1y = game.getPlayer1().body.getPosition().y;
        float p2x = game.getPlayer2().body.getPosition().x;
        float p2y = game.getPlayer2().body.getPosition().y;
        Texture todraw1;
        Texture todraw2;
        if(game.getPlayer1().getPower()== 0) {
            todraw1 = defaultTex;
        }else if(game.getPlayer1().getPower() == 1){
            todraw1 = rocketTex;
        }else if(game.getPlayer1().getPower() == 2) {
            todraw1 = stopTex;
        }else if(game.getPlayer1().getPower() == 3) {
            todraw1 = flipTex;
        }else if(game.getPlayer1().getPower() == 4) {
            todraw1 = magnetTex;
        }else todraw1 = defaultTex;
        objects.draw(todraw1, Gdx.graphics.getWidth() * (p1x / 100f) - Gdx.graphics.getWidth() * 0.1f / 2, Gdx.graphics.getHeight() / SCREENRESPROP * (p1y / 100f), Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f / SCREENRESPROP);
        if(game.getPlayer2().getPower() == 0) {
            todraw2 = defaultTex;
        }else if(game.getPlayer2().getPower()== 1){
            todraw2 = rocketTex;
        }else if(game.getPlayer2().getPower() == 2) {
            todraw2 = stopTex;
        }else if(game.getPlayer2().getPower() == 3) {
            todraw2 = flipTex;
        }else if(game.getPlayer2().getPower() == 4) {
            todraw2 = magnetTex;
        }else todraw2 = defaultTex;
        objects.draw(todraw2, Gdx.graphics.getWidth() * (p2x / 100f) - Gdx.graphics.getWidth() * 0.1f / 2, Gdx.graphics.getHeight() / SCREENRESPROP * (p2y / 100f), Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f / SCREENRESPROP);

    }

    /**
     * Desenha a contagem decrescente caso esta seja >0
     */
    void drawCountDown(SpriteBatch objects,Game game){
        float d = game.getCountdown();
        int number = (int)d;
        float size = d-(float)number;
        Texture drawNumber;
        if(number == 2){
            drawNumber = n3;
        }else if(number == 1){
            drawNumber = n2;
        }else if(number == 0 && d > 0){
            drawNumber = n1;
        }else return;
        //objects.draw(n3,Gdx.graphics.getWidth()*0.35f,Gdx.graphics.getHeight()*0.8f,Gdx.graphics.getWidth()*0.1f,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP);
        objects.draw(drawNumber,Gdx.graphics.getWidth()*0.45f-Gdx.graphics.getWidth()*0.45f*size,Gdx.graphics.getHeight()*0.45f-Gdx.graphics.getHeight()*0.45f*size,Gdx.graphics.getWidth()*0.1f+Gdx.graphics.getWidth()*2*0.45f*size,Gdx.graphics.getHeight()*0.1f/SCREENRESPROP+Gdx.graphics.getHeight()*2*0.45f*size);

    }

    /**
     * Desenha barras de mana de cada jogador, crescendo em direção ao meio do ecra
     */

    void drawMana(SpriteBatch objects,Game game){
        float p2length = Gdx.graphics.getWidth()*0.3f*(float)(game.getPlayer2().getMana()/100)*0.95f;
        objects.draw(mana,Gdx.graphics.getWidth()*0.06f,Gdx.graphics.getHeight()*0.87f,Gdx.graphics.getWidth()*0.3f*(float)(game.getPlayer1().getMana()/100)*0.95f,Gdx.graphics.getHeight()*0.06f);
        objects.draw(mana,Gdx.graphics.getWidth()*0.645f+Gdx.graphics.getWidth()*0.30f-p2length,Gdx.graphics.getHeight()*0.87f,p2length,Gdx.graphics.getHeight()*0.06f);
        objects.draw(manabar,Gdx.graphics.getWidth()*0.05f,Gdx.graphics.getHeight()*0.85f,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.1f);
        objects.draw(manabar,Gdx.graphics.getWidth()*0.65f,Gdx.graphics.getHeight()*0.85f,Gdx.graphics.getWidth()*0.3f,Gdx.graphics.getHeight()*0.1f);

    }

    /**
     * Desenha a bola e os poderes utilizados pelos jogadores
     */
    void drawBallAndPowers(SpriteBatch objects,Game game){
        float bx = game.getBall().body.getPosition().x;
        float by = game.getBall().body.getPosition().y;
        float bdrawx = Gdx.graphics.getWidth()*(bx/100f)-Gdx.graphics.getWidth()*0.046f/2;
        float bdrawy = Gdx.graphics.getHeight()*(by/100f)/SCREENRESPROP-Gdx.graphics.getHeight()*0.046f/2/SCREENRESPROP;
        if(game.getPlayer1().usingPower && game.getPlayer1().getPower() == 1 || game.getPlayer2().usingPower && game.getPlayer2().getPower() == 1){
            objects.draw(fireball,bdrawx,bdrawy,Gdx.graphics.getWidth()*0.046f/2,Gdx.graphics.getHeight()*0.046f/SCREENRESPROP/2,Gdx.graphics.getWidth()*0.046f,Gdx.graphics.getHeight()*0.046f/SCREENRESPROP,1.1f,1.1f,fireballrotatecounter);
            fireballrotatecounter -= Gdx.graphics.getDeltaTime()*2000;
        }
        else objects.draw(ball,bdrawx,bdrawy,Gdx.graphics.getWidth()*0.046f/2,Gdx.graphics.getHeight()*0.046f/SCREENRESPROP/2,Gdx.graphics.getWidth()*0.046f,Gdx.graphics.getHeight()*0.046f/SCREENRESPROP,1.1f,1.1f,game.getBall().body.getAngle()*(180f/(float)Math.PI));

        if(game.getPlayer1().getPower() == 2 && game.getPlayer1().usedPowerTimer > 0 ||game.getPlayer2().getPower() == 2 && game.getPlayer2().usedPowerTimer > 0){
            objects.draw(stop,bdrawx,bdrawy,Gdx.graphics.getWidth()*0.046f,Gdx.graphics.getHeight()*0.046f/SCREENRESPROP);
        }
        if(game.getPlayer1().getPower() == 3 && game.getPlayer1().usedPowerTimer > 0 ||game.getPlayer2().getPower() == 3 && game.getPlayer2().usedPowerTimer > 0){
            objects.draw(switchdir,bdrawx,bdrawy,Gdx.graphics.getWidth()*0.046f,Gdx.graphics.getHeight()*0.046f/SCREENRESPROP);
        }
        float p1x = game.getPlayer1().body.getPosition().x;
        float p1y = game.getPlayer1().body.getPosition().y;
        float p2x = game.getPlayer2().body.getPosition().x;
        float p2y = game.getPlayer2().body.getPosition().y;
        if(game.getPlayer1().usingPower && game.getPlayer1().getPower() == 4){
            System.out.println("draw magnet");
            objects.draw(magnet, Gdx.graphics.getWidth() * (p1x / 100f) - Gdx.graphics.getWidth() * 0.04f / 2, Gdx.graphics.getHeight() / SCREENRESPROP * (p1y / 100f)+Gdx.graphics.getHeight()*0.04f/SCREENRESPROP, Gdx.graphics.getWidth() * 0.04f, Gdx.graphics.getHeight() * 0.03f / SCREENRESPROP);
        }
        if(game.getPlayer2().usingPower && game.getPlayer2().getPower() == 4){
            objects.draw(magnet,game.getPlayer2().body.getPosition().x*Gdx.graphics.getWidth()/100,game.getPlayer2().body.getPosition().y+Gdx.graphics.getHeight() * 0.05f / SCREENRESPROP,Gdx.graphics.getWidth()/0.1f,Gdx.graphics.getHeight()/0.1f);
        }
    }

}
