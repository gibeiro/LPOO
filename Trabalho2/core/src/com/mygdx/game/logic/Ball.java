package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Nuno on 04/05/2016.
 */
public class Ball extends Object {

    public Ball(World world,int x, int y){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x,y);
        def.fixedRotation = false;

        body = world.createBody(def);

        FixtureDef f = new FixtureDef();
        f.filter.categoryBits = 1;
        f.filter.maskBits = 7;
        f.density = 0.1f;
        f.restitution = 0.65f;
        f.friction = 0.1f;

        CircleShape circle = new CircleShape();
        circle.setRadius(2.3f);


        f.shape = circle;
        body.createFixture(f);



        circle.dispose();
    }

}
