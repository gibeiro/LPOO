package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.input.Inputs;

import java.util.ArrayList;

/**
 * Claase que representa um jogador
 */
public class Player extends Object {
    /**
     * Inputs do jogador
     */
    Inputs inputs;
    /**
     * Golos marcados
     */
    int goals;
    /**
     * Contador usado para que o jogador nao salte varias vezes no mesmo instante.
     */
    double jumpCounter;
    /**
     * Indice que representa o poder
     */
    int powerIndex;
    /**
     * Mana disponivel para usar poderes.
     */
    double mana;
    /**
     * Booleano que indica se um poder continuo esta a ser usado(atrair ou acelerar bola)
     */
    public boolean usingPower;
    /**
     * Timer que indica há quanto tempo foi invocado um poder instantaneo(parar a bola ou trocar sua direção)
     */
    public float usedPowerTimer;

    /**
     * Cria novo jogador
     */
    public Player(World world, int x, int y){
        inputs = new Inputs(false,false,false,false);
        goals = 0;
        jumpCounter = 0;
        powerIndex = 0;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x,y);
        def.fixedRotation = true;
        mana = 50;


        body = world.createBody(def);
        //corpo
        FixtureDef f = new FixtureDef();

        PolygonShape shape = new PolygonShape();

        ArrayList<Vector2> vertices = new ArrayList<Vector2>();

        for(int i = 0;i < 8;i++){
            vertices.add(new Vector2(5f*(float)Math.cos(Math.PI*i/7),5f*(float)Math.sin(Math.PI*i/7)));
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
        vertices.add(new Vector2(-5f,-0.1f));
        vertices.add(new Vector2(5f,-0.1f));

        shape.set(vertices.toArray(new Vector2[4]));

        body.createFixture(shape,0f);



        shape.dispose();

        body.getFixtureList().get(1).setSensor(true);


    }

    /**
     * Define o poder do jogador
     */
    public void setPower(int i) {
        powerIndex = i;
    }
    /**
     *  Retorna false se o poder nao estiver a ser premido.Caso contrario, retorna true
     */
    public boolean getPowerPressed(){
        if(inputs.getPower())
            return true;
        return false;
    }

    /**
     * Usa poder do jogador
     */
    public void usePower(Game game, double dt){
        Power.usePower(game,dt,powerIndex,this);
    }

    public Inputs getInputs() {
        return inputs;
    }

    public void setInputs(Inputs inputs) {
        this.inputs = inputs;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getPower() {
        return powerIndex;
    }


    public double getJumpCounter() {
        return jumpCounter;
    }

    public void setJumpCounter(double jumpCounter) {
        this.jumpCounter = jumpCounter;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
        if(this.mana > 100)
            this.mana = 100;
        if(this.mana < 0)
            this.mana = 0;
    }
}
