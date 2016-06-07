package com.mygdx.game.logic;

/**
 * Poder que pode ser usado por um certo jogador
 */
public class Power {


    /**
     * É utilizado um poder pelo jogador player do jogo game, com o id powerId e delta T dt
     */
    public static void usePower(Game game,double dt,int powerId,Player player){
        if(powerId ==1){
            power1(game,dt,player);
            return;
        }
        if(powerId ==2){
            power2(game,dt,player);
            return;
        }
        if(powerId ==3){
            power3(game,dt,player);
            return;
        }
        if(powerId ==4){
            power4(game,dt,player);
            return;
        }
    }

    /**
     * Poder que aumenta a velocidade da bola
     */
    public static void power1(Game game, double dt,Player player){
        if(player.getPowerPressed() && player.getMana() >= 80 && !player.inputs.getClickedPower() || player.getPowerPressed() && player.getMana() >0.05 && player.inputs.getClickedPower()){
            game.getBall().body.setLinearVelocity(
                    game.getBall().body.getLinearVelocity().x+game.getBall().body.getLinearVelocity().x*(float)dt*2,
                    game.getBall().body.getLinearVelocity().y+game.getBall().body.getLinearVelocity().y*(float)dt*2);
            player.inputs.setClickedPower(true);
            player.setMana(player.mana-40*dt);
            if(player.mana == 0){
                player.inputs.setClickedPower(false);
            }
            player.usingPower = true;
        }else if(!player.getPowerPressed()){
            player.inputs.setClickedPower(false);
            player.usingPower = false;
        }else player.usingPower = false;
    }

    /**
     * Poder que faz a bola parar
     */
    public static void power2(Game game, double dt,Player player){
        if(player.getPowerPressed() && !player.inputs.getClickedPower() && player.mana >= 80){
            game.getBall().body.setLinearVelocity(0,0);
            game.getBall().body.setAngularVelocity(0);
            player.inputs.setClickedPower(true);
            player.setMana(player.mana-80);
            player.usedPowerTimer = 1;
        }else if(!player.getPowerPressed())
            player.inputs.setClickedPower(false);
    }

    /**
     * Poder que troca a direcao horizontal da bola
     */
    public static void power3(Game game, double dt,Player player){
        if(player.getPowerPressed() && !player.inputs.getClickedPower() && player.mana >= 80){
            game.getBall().body.setLinearVelocity(-game.getBall().body.getLinearVelocity().x,game.getBall().body.getLinearVelocity().y);
            game.getBall().body.setAngularVelocity(-game.getBall().body.getAngularVelocity());
            player.setMana(player.mana-80);
            player.inputs.setClickedPower(true);
            player.usedPowerTimer = 1;
        }else if(!player.getPowerPressed())
            player.inputs.setClickedPower(false);
    }

    /**
     * Poder que atrai a bola para o jogador que o invocou
     */
    public static void power4(Game game, double dt,Player player){
        if(player.getPowerPressed() && player.getMana() >= 80 && !player.inputs.getClickedPower() || player.getPowerPressed() && player.getMana() >0.05 && player.inputs.getClickedPower()){
            float playerx = player.body.getPosition().x;
            float playery = player.body.getPosition().y;
            float ballx = game.getBall().body.getPosition().x;
            float bally = game.getBall().body.getPosition().y;
            game.getBall().body.setLinearVelocity(
                    game.getBall().body.getLinearVelocity().x+100*(playerx-ballx)/(float)Math.sqrt(Math.pow(playerx-ballx,2)+Math.pow(playery-bally,2))*(float)dt,
                    game.getBall().body.getLinearVelocity().y+100*(playery-bally)/(float)Math.sqrt(Math.pow(playerx-ballx,2)+Math.pow(playery-bally,2))*(float)dt);
            player.inputs.setClickedPower(true);
            player.setMana(player.mana-40*dt);
            if(player.mana == 0){
                player.inputs.setClickedPower(false);
            }
            player.usingPower = true;
        }else if(!player.getPowerPressed()){
            player.inputs.setClickedPower(false);
            player.usingPower = false;
        }else player.usingPower = false;
    }
}
