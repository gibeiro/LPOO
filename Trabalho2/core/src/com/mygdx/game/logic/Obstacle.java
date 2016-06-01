package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Nuno on 04/05/2016.
 */
public class Obstacle extends Object {
    public Obstacle(World world){
        //Fixtures: 1- chao, 2- parede esq ,3- parede dir, 4- teto, 5- baliza esq, 6-baliza dir, 7-sensor baliza esq, 8-sensor baliza dir
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(50,0);
        def.fixedRotation = false;
        body = world.createBody(def);

        /*
         *  Field limits
         */
        FixtureDef f = new FixtureDef();

        f.filter.categoryBits = 2;
        f.filter.maskBits = 7;
        f.density = 1f;

        PolygonShape shape = new PolygonShape();
        //floor
        ArrayList<Vector2> vertices = new ArrayList<Vector2>();
        vertices.add(new Vector2(50,10));
        vertices.add(new Vector2(-49,10));
        vertices.add(new Vector2(-49,1));
        vertices.add(new Vector2(50,1));

        shape.set(vertices.toArray(new Vector2[4]));

        f.shape = shape;
        body.createFixture(f);
        //left wall
        vertices.clear();
        vertices.add(new Vector2(-49,1));
        vertices.add(new Vector2(-46,1));
        vertices.add(new Vector2(-46,99));
        vertices.add(new Vector2(-49,99));

        shape.set(vertices.toArray(new Vector2[4]));

        f.friction = 0;
        f.shape = shape;
        body.createFixture(f);
        //right wall
        vertices.clear();
        vertices.add(new Vector2(50,1));
        vertices.add(new Vector2(47,1));
        vertices.add(new Vector2(47,99));
        vertices.add(new Vector2(50,99));

        shape.set(vertices.toArray(new Vector2[4]));

        f.shape = shape;
        body.createFixture(f);
        //ceiling
        vertices.clear();
        vertices.add(new Vector2(-49,60));
        vertices.add(new Vector2(-49,57));
        vertices.add(new Vector2(50,57));
        vertices.add(new Vector2(50,60));

        shape.set(vertices.toArray(new Vector2[4]));

        f.shape = shape;
        body.createFixture(f);

        /*
         * Beacons
         */
        //left beacon
        f.friction = 0.2f;
        f.filter.maskBits = 3;
        vertices.clear();
        vertices.add(new Vector2(-46,24));
        vertices.add(new Vector2(-38,24));
        vertices.add(new Vector2(-38,26));
        vertices.add(new Vector2(-46,26));


        shape.set(vertices.toArray(new Vector2[4]));

        f.shape = shape;
        body.createFixture(f);

        //right beacon
        vertices.clear();
        vertices.add(new Vector2(47,24));
        vertices.add(new Vector2(39,24));
        vertices.add(new Vector2(39,26));
        vertices.add(new Vector2(47,26));


        shape.set(vertices.toArray(new Vector2[4]));

        f.shape = shape;
        body.createFixture(f);





        shape.dispose();

    }
}
