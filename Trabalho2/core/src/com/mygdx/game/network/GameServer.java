package com.mygdx.game.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.input.Inputs;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nuno on 10/05/2016.
 */
public class GameServer implements ServerInterface {
    public ArrayList<ClientInterface> clients;
    public Game game;

    public GameServer(){
        clients = new ArrayList<ClientInterface>();
        game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
    }
    @Override
    public int join(ClientInterface c) {
        if(clients.size() >= 2)
            return 0;
        clients.add(c);
        c.setId(clients.size());
        System.out.println("Player joined");
        return clients.size();
    }

    @Override
    public int setInput(int player, Inputs i) {
        if(player != 1 && player != 2){
            return 0;
        }
        if(player == 1){
            game.getPlayer1().getInputs().setJump(i.getJump());
            game.getPlayer1().getInputs().setMovingLeft(i.getMovingLeft());
            game.getPlayer1().getInputs().setMovingRight(i.getMovingRight());
            game.getPlayer1().getInputs().setPower(i.getPower());
        }else if(player == 2){
            game.getPlayer2().getInputs().setJump(i.getJump());
            game.getPlayer2().getInputs().setMovingLeft(i.getMovingLeft());
            game.getPlayer2().getInputs().setMovingRight(i.getMovingRight());
            game.getPlayer2().getInputs().setPower(i.getPower());
        }
        return 1;
    }

    @Override
    public GameInfo transferWorld() {
        return new GameInfo(game);
    }

    public void updateWorld(float dt){
        game.update(dt);
    }

}
