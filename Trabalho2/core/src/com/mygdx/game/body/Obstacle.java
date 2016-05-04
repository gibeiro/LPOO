package com.mygdx.game.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Nuno on 04/05/2016.
 */
public class Obstacle extends Object {
    public Obstacle(World world){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(50,0);
        def.fixedRotation = false;
        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();

        ArrayList<Vector2> vertices = new ArrayList<Vector2>();
        vertices.add(new Vector2(50,6));
        vertices.add(new Vector2(-50,6));
        vertices.add(new Vector2(-50,0));
        vertices.add(new Vector2(50,0));

        shape.set(vertices.toArray(new Vector2[4]));

        body.createFixture(shape,1.0f);

        shape.dispose();
        texture = new Texture("ball.png");
    }
}
