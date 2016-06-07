package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.socketnetwork.InfoGame;

/**
 * Created by up201403877 on 09-05-2016.
 */
public class Game {
    /**
     * Gravidade do jogo
     */
    private final static float  GRAVITY = -120f;
    /**
     * Mundo do jogo onde vao ser colocadas as entidades
     */
    private World world;
    /**
     * Jogador 1
     */
    private Player player1;
    /**
     * Jogador 2
     */
    private Player player2;
    /**
     * Bola
     */
    private Ball ball;
    /**
     * Campo de jogo
     */
    private Obstacle field;
    /**
     * Limite de golos
     */
    private int limitGoals;
    /**
     * Indica se o jogo já terminou
     */
    private boolean gameEnd;
    /**
     * Float que representa o tempo de contagem decrescente.Quando toma valor negativo, a contagem terminou.
     */
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

    /**
     * Atualiza o jogo:
     * -Reduz o countdown.
     * -Avança world
     * -Movimenta jogadores consoante seus inputs
     * -Aumenta mana dos jogadores
     * -Utiliza poderes caso estes estejam a ser usados
     */
    public void update(double dt){
        /*
         * Verifica se a bola está dentro das balizas
         */
        countdown -= dt;
        if(countdown > 0){
            return;
        }

        player1.setJumpCounter(player1.getJumpCounter()-dt);
        player2.setJumpCounter(player2.getJumpCounter()-dt);

        player1.usedPowerTimer-= dt;
        player2.usedPowerTimer-= dt;


        world.step((float)dt*0.7f,6,2);
        /*
         * Verifica movimentos do jogador 1
         */
        updateInputs(player1,(float)dt);

        /*
         * Verifica movimentos do jogador 2
         */

        updateInputs(player2,(float)dt);
        /*
         * Incrementa mana dos 2 jogadores
         */
        player1.setMana(player1.getMana()+dt*14);
        player2.setMana(player2.getMana()+dt*14);


        checkPowers(dt);

    }

    /**
     * Verifica se está a ser marcado algum golo, reiniciado a posição das entidades caso tenha sido marcado um, assim como
     * aumentando o contador de golos do jogador e terminando o jogo caso o limite de golos tenha sido alcançado.
     */
    public int checkGoals(){
        float x = ball.body.getPosition().x;
        float y = ball.body.getPosition().y;
        if(x > 89 && y < 28){
            player1.goals++;
            countdown = 3;
            resetPositions();
            if(player1.goals >=limitGoals || player2.goals >= limitGoals){
                gameEnd = true;
            }
            return 1;
        }else if(x < 11 && y < 28){
            player2.goals++;
            countdown = 3;
            resetPositions();
            if(player1.goals >=limitGoals || player2.goals >= limitGoals){
                gameEnd = true;
            }
            return 2;
        }
        return 0;
    }

    /**
     * Reinicia a posição de todas as entidades no campo.
     */
    public void resetPositions(){
        ball.body.setTransform(50,50,0);
        ball.body.setLinearVelocity(0,0);
        ball.body.setAngularVelocity(0);
        player1.body.setTransform(20,15,0);
        player1.body.setLinearVelocity(0,0);
        player2.body.setTransform(80,15,0);
        player2.body.setLinearVelocity(0,0);
    }

    /**
     * Utiliza poderes e atualiza variaveis indicativas da sua utilização.
     */
    public void checkPowers(double dt){
        player1.usedPowerTimer -= dt;
        player2.usedPowerTimer -= dt;
        player1.usePower(this,dt);
        player2.usePower(this,dt);
    }

    /**
     * Atualiza o jogo com informação recebida do servidor
     */
    public void updateGame(InfoGame g){
        player1.body.setTransform(new Vector2(g.p1x,g.p1y),0);
        player2.body.setTransform(new Vector2(g.p2x,g.p2y),0);
        player1.body.setLinearVelocity(g.p1vx,g.p1vy);
        player2.body.setLinearVelocity(g.p2vx,g.p2vy);
        player1.getInputs().movingLeft = g.p1i.movingLeft;
        player1.getInputs().movingRight = g.p1i.movingRight;
        player1.getInputs().jump = g.p1i.jump;
        player1.getInputs().power = g.p1i.power;
        player1.usedPowerTimer = g.p1t;
        player2.usedPowerTimer = g.p2t;
        player2.getInputs().movingLeft = g.p2i.movingLeft;
        player2.getInputs().movingRight = g.p2i.movingRight;
        player2.getInputs().jump = g.p2i.jump;
        player2.getInputs().power = g.p2i.power;
        player1.setMana(g.p1m);
        player2.setMana(g.p2m);
        ball.body.setTransform(new Vector2(g.bx,g.by),g.ba);
        ball.body.setLinearVelocity(g.bvx,g.bvy);
        ball.body.setAngularVelocity(g.bav);
    }

    /**
     * Move ou usa o poder dos jogadores consoante os seus inputs
     */
    public void updateInputs(Player p,float dt){
        if(p.inputs.getMovingLeft()){
            if(p.body.getLinearVelocity().x > 0)
                p.body.setLinearVelocity(0,p.body.getLinearVelocity().y);
            p.body.setLinearVelocity(p.body.getLinearVelocity().x-0.5f*(float)dt*1000,p.body.getLinearVelocity().y);
        }
        if(p.inputs.getMovingRight()){
            if(p.body.getLinearVelocity().x < 0)
                p.body.setLinearVelocity(0,p.body.getLinearVelocity().y);
            p.body.setLinearVelocity(p.body.getLinearVelocity().x+0.5f*(float)dt*1000,p.body.getLinearVelocity().y);
        }
        if(p.inputs.getJump() && p.getJumpCounter() < 0 && PlayerColidingWithGround(world, p, field)){
            p.body.setLinearVelocity(p.body.getLinearVelocity().x,60f);
            p.inputs.setJump(false);
            p.setJumpCounter(1);

        }else p.inputs.setJump(false);
        if(p.body.getLinearVelocity().x > 50){
            p.body.setLinearVelocity(50,p.body.getLinearVelocity().y);
        }
        if(p.body.getLinearVelocity().x < -50){
            p.body.setLinearVelocity(-50,p.body.getLinearVelocity().y);
        }
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

    public static boolean PlayerColidingWithGround(World world, Player o1, Obstacle o2){
        for(int i = 0;i < world.getContactList().size;i++){
            if((world.getContactList().get(i).getFixtureA() == o1.body.getFixtureList().get(1) && world.getContactList().get(i).getFixtureB() == o2.body.getFixtureList().get(0))||
                    (world.getContactList().get(i).getFixtureB() == o1.body.getFixtureList().get(1) && world.getContactList().get(i).getFixtureA() == o2.body.getFixtureList().get(0) )){
                return true;
            }
        }

        return false;
    }
}
