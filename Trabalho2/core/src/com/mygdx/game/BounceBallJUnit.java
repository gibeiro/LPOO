package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;
import com.mygdx.game.logic.Power;

import org.junit.Test;
import org.junit.Assert;

/**
 * Testes unitarios usando JUnit
 */
public class BounceBallJUnit extends ApplicationAdapter {

    Game game;
    @Override
    public void create () {
        System.out.println("Starting tests");

        test0();
        test1();
        test2();
        test3();
        test4();

        System.out.println("Finished tests");

        System.exit(0);
    }

    @Override
    public void render () {

    }

    /**
     * Testa se é marcado golo quando bola entra em qualquer das balizas
     */
    @Test
    public void test0(){
        Game game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        game.getBall().body.setTransform(93,20,0);
        Assert.assertEquals(game.checkGoals(),1);
        game.getBall().body.setTransform(10,20,0);
        Assert.assertEquals(game.checkGoals(),2);
    }
    /**
     * Testar se poder 1 acelera a bola
     */
    @Test
    public void test1(){
        Game game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        game.setCountdown(0);
        game.getPlayer1().setMana(100);
        game.update(2);
        game.getBall().body.setTransform(50,20,0);
        game.getBall().body.setLinearVelocity(1,0);
        //Coloca velocidade da bola X a 1
        Assert.assertEquals(game.getBall().body.getLinearVelocity().x,1,0.01);
        game.getPlayer1().getInputs().power = true;
        Power.usePower(game,0.3,1,game.getPlayer1());
        //Verifica se a velocidade X da bola é superior a 1
        Assert.assertEquals(game.getBall().body.getLinearVelocity().x > 1,true);
    }
    /**
     * Testar se poder 2 pára bola
     */
    @Test
    public void test2(){
        Game game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        game.setCountdown(0);
        game.getPlayer1().setMana(100);
        game.update(2);
        game.getPlayer1().getInputs().power = true;
        Assert.assertEquals(game.getBall().body.getLinearVelocity().y != 0f, true);
        Power.usePower(game,0.1,2,game.getPlayer1());
        Assert.assertEquals(game.getBall().body.getLinearVelocity().y,0,0.01);
    }
    /**
     * Testar se poder 3 troca direcao da bola
     */
    @Test
    public void test3(){
        Game game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        game.setCountdown(0);
        game.getPlayer1().setMana(100);
        game.update(2);
        game.getBall().body.setLinearVelocity(10,0);
        Assert.assertEquals(game.getBall().body.getLinearVelocity().x,10,0.01);
        game.getPlayer1().getInputs().power = true;
        Power.usePower(game,0.1,3,game.getPlayer1());
        Assert.assertEquals(game.getBall().body.getLinearVelocity().x,-10,0.01);
    }
    /**
     * Testar se poder 4 atrai a bola
     */
    @Test
    public void test4(){
        Game game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        game.setCountdown(0);
        game.getPlayer1().setMana(100);
        game.update(2);
        //Coloca velocidade a 0
        game.getBall().body.setTransform(50,20,0);
        game.getBall().body.setLinearVelocity(0,0);
        Assert.assertEquals(game.getBall().body.getLinearVelocity().x,0,0.01);
        game.getPlayer1().getInputs().power = true;
        Power.usePower(game,0.1,4,game.getPlayer1());
        //A bola ao ser atraida para a esquerda(direcao do jogador1), terá uma velocidade negativa
        Assert.assertEquals(game.getBall().body.getLinearVelocity().x< 0,true);
    }
}
