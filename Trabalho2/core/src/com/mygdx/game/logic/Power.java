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
    }

    public void power1(Game game, double dt){
        if(player.getPowerBeingUsed() == 1 || player.getPowerBeingUsed() == 1){
            game.getBall().body.setLinearVelocity(
                    game.getBall().body.getLinearVelocity().x+game.getBall().body.getLinearVelocity().x*(float)dt*3,
                    game.getBall().body.getLinearVelocity().y+game.getBall().body.getLinearVelocity().y*(float)dt*3);
        }
    }

    public void power2(Game game, double dt){
        if(player.getPowerBeingUsed() == 2 && !player.inputs.getClickedPower()){
            game.getBall().body.setLinearVelocity(0,0);
            game.getBall().body.setAngularVelocity(0);
            player.inputs.setClickedPower(true);
        }else if(player.getPowerBeingUsed() == 0)
            player.inputs.setClickedPower(false);
    }

    public void power3(Game game, double dt){
        if(player.getPowerBeingUsed() == 3 && !player.inputs.getClickedPower()){
            game.getBall().body.setLinearVelocity(-game.getBall().body.getLinearVelocity().x,game.getBall().body.getLinearVelocity().y);
            game.getBall().body.setAngularVelocity(-game.getBall().body.getAngularVelocity());
            player.inputs.setClickedPower(true);
        }else if(player.getPowerBeingUsed() == 0)
            player.inputs.setClickedPower(false);
    }
}
