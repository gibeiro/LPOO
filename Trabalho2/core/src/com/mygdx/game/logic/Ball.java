package com.mygdx.game.logic;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

// TODO: Auto-generated Javadoc
/**
 * Representa a bola.
 */
public class Ball extends Object {

    /**
     * Instantiates a new ball.
     *
     * @param world the world
     * @param x the x
     * @param y the y
     */
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
