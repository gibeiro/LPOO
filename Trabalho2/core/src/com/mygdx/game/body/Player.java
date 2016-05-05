package com.mygdx.game.body;

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
public class Player extends Object {
    public Player(World world, int x, int y){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x,y);
        def.fixedRotation = true;

        body = world.createBody(def);

        PolygonShape shape = new PolygonShape();

        ArrayList<Vector2> vertices = new ArrayList<Vector2>();

        for(int i = 0;i < 8;i++){
            vertices.add(new Vector2(6*(float)Math.cos(Math.PI*i/7),6*(float)Math.sin(Math.PI*i/7)));
        }

        shape.set(vertices.toArray(new Vector2[8]));

        body.createFixture(shape,100f);

        body.getFixtureList().get(0).setRestitution(0f);


    }
}
