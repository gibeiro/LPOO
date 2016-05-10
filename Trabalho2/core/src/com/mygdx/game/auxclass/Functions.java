package com.mygdx.game.auxclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.logic.Obstacle;
import com.mygdx.game.logic.Player;

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
    public static boolean leftButtonPressed(){
        if(!Gdx.input.isTouched())
            return false;
        for(int i = 0;i < 4;i++){
            int x = Gdx.input.getX(i);
            int y = Gdx.input.getY(i);
            if(x > (float)Gdx.graphics.getWidth()*0.0 &&
                    x < (float)Gdx.graphics.getWidth()*0.15 &&
                    y > (float)Gdx.graphics.getHeight()*0.7 &&
                    y < (float)Gdx.graphics.getHeight()*1){
                return true;
            }
        }
        return false;
    }
    public static boolean rightButtonPressed(){
        if(!Gdx.input.isTouched())
            return false;
        for(int i = 0;i < 4;i++){
            int x = Gdx.input.getX(i);
            int y = Gdx.input.getY(i);
            if(x > (float)Gdx.graphics.getWidth()*0.15 &&
                    x < (float)Gdx.graphics.getWidth()*0.30 &&
                    y > (float)Gdx.graphics.getHeight()*0.7 &&
                    y < (float)Gdx.graphics.getHeight()*1){
                return true;
            }
        }
        return false;
    }
    public static boolean jumpButtonPressed(){
        if(!Gdx.input.isTouched())
            return false;
        for(int i = 0;i < 4;i++){
            int x = Gdx.input.getX(i);
            int y = Gdx.input.getY(i);
            if(x > (float)Gdx.graphics.getWidth()*0.85 &&
                    x < (float)Gdx.graphics.getWidth()*1 &&
                    y > (float)Gdx.graphics.getHeight()*0.7 &&
                    y < (float)Gdx.graphics.getHeight()*1){
                return true;
            }
        }
        return false;
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
