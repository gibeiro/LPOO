package com.mygdx.game.logic;

/**
 * Created by Nuno on 11/05/2016.
 */
public class Power {
    int powerId;
    Player player;
    public Power(Player player,int id){
        this.powerId = id;
        this.player = player;
    }

    public int getPower(){
        return powerId;
    }

    public void usePower(Game game,double dt){
        if(powerId ==1){
            power1(game,dt);
            return;
        }
        if(powerId ==2){
            power2(game,dt);
            return;
        }
        if(powerId ==3){
            power3(game,dt);
            return;
        }
        if(powerId ==4){
            power4(game,dt);
            return;
        }
    }
    //Aumenta velocidade da bola
    public void power1(Game game, double dt){
        if(player.getPowerBeingUsed() == 1 && player.getMana() >= 80 && !player.inputs.getClickedPower() || player.getPowerBeingUsed() == 1 && player.getMana() >0.05 && player.inputs.getClickedPower()){
            game.getBall().body.setLinearVelocity(
                    game.getBall().body.getLinearVelocity().x+game.getBall().body.getLinearVelocity().x*(float)dt*2,
                    game.getBall().body.getLinearVelocity().y+game.getBall().body.getLinearVelocity().y*(float)dt*2);
            player.inputs.setClickedPower(true);
            player.setMana(player.mana-40*dt);
            if(player.mana == 0){
                player.inputs.setClickedPower(false);
            }
        }else if(player.getPowerBeingUsed() == 0){
            player.inputs.setClickedPower(false);
        }
    }
    //Para a bola
    public void power2(Game game, double dt){
        if(player.getPowerBeingUsed() == 2 && !player.inputs.getClickedPower() && player.mana >= 50){
            game.getBall().body.setLinearVelocity(0,0);
            game.getBall().body.setAngularVelocity(0);
            player.inputs.setClickedPower(true);
            player.setMana(player.mana-50);
        }else if(player.getPowerBeingUsed() == 0)
            player.inputs.setClickedPower(false);
    }
    //Troca direcao da bola
    public void power3(Game game, double dt){
        if(player.getPowerBeingUsed() == 3 && !player.inputs.getClickedPower() && player.mana >= 50){
            game.getBall().body.setLinearVelocity(-game.getBall().body.getLinearVelocity().x,game.getBall().body.getLinearVelocity().y);
            game.getBall().body.setAngularVelocity(-game.getBall().body.getAngularVelocity());
            player.setMana(player.mana-50);
            player.inputs.setClickedPower(true);
        }else if(player.getPowerBeingUsed() == 0)
            player.inputs.setClickedPower(false);
    }
    //Íman
    public void power4(Game game, double dt){
        if(player.getPowerBeingUsed() == 4 && player.getMana() >= 80 && !player.inputs.getClickedPower() || player.getPowerBeingUsed() == 4 && player.getMana() >0.05 && player.inputs.getClickedPower()){
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
        }else if(player.getPowerBeingUsed() == 0){
            player.inputs.setClickedPower(false);
        }
    }
}
