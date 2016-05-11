package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.auxclass.Inputs;

import java.util.ArrayList;

/**
 * Created by Nuno on 04/05/2016.
 */
public class Player extends Object {
    public Inputs inputs;
    public int goals;
    public Power power;
    public Player(World world, int x, int y){
        inputs = new Inputs(false,false,false,false);
        goals = 0;
        power = new Power(this,0);
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x,y);
        def.fixedRotation = true;


        body = world.createBody(def);
        //corpo
        FixtureDef f = new FixtureDef();

        PolygonShape shape = new PolygonShape();

        ArrayList<Vector2> vertices = new ArrayList<Vector2>();

        for(int i = 0;i < 7;i++){
            vertices.add(new Vector2(5f*(float)Math.cos(Math.PI*i/6),5f*(float)Math.sin(Math.PI*i/6)));
        }

        shape.set(vertices.toArray(new Vector2[7]));

        f.filter.categoryBits = 4;
        f.filter.maskBits = 3;

        f.density = 100f;

        f.friction = 10f;

        f.restitution = 0f;

        f.shape = shape;

        body.createFixture(f);
        //sensor para saltar
        vertices.clear();

        vertices.add(new Vector2(5f,0));
        vertices.add(new Vector2(-5f,0));
        vertices.add(new Vector2(-5f,-1));
        vertices.add(new Vector2(5f,-1));

        shape.set(vertices.toArray(new Vector2[4]));

        body.createFixture(shape,0f);



        shape.dispose();

        body.getFixtureList().get(1).setSensor(true);


    }

    public void setPower(int i) {
        this.power = new Power(this,i);
    }
    /*
     *  Returns 0 if the user has no power or he has one but is not being used, returns its index if it's being used
     */
    public int getPowerBeingUsed(){
        if(inputs.power)
            return power.getPower();
        return 0;
    }
    public void usePower(Game game, double dt){
        power.usePower(game,dt);
    }

}
