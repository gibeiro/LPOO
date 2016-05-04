package com.mygdx.game.body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

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

        CircleShape circle = new CircleShape();
        circle.setRadius(3);


        body.createFixture(circle,10.0f);

        body.getFixtureList().get(0).setRestitution(0.7f);//Para a bola saltar
        circle.dispose();
        texture = new Texture("ball.png");
    }
}
