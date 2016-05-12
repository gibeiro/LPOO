package com.mygdx.game.auxclass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.logic.Obstacle;
import com.mygdx.game.logic.Player;

/**
 * Created by Nuno on 05/05/2016.
 */
public class Functions {

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
