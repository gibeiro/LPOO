package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.state.StateGame;

/**
 * Created by Nuno on 05/05/2016.
 */
public class Functions {
    public static boolean rectanglePressed(int x1,int y1,int x2,int y2){
        for(int i = 0;i < 10;i++){
            int x = Gdx.input.getX(i);
            int y = Gdx.input.getY(i);
            if(x > x1 &&
                    x < x2 &&
                    y > y1 &&
                    y < y2){
                return true;
            }
        }
        return false;
    }
}
