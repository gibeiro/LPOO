package com.mygdx.game.input;

/**
 * Created by up201403877 on 09-05-2016.
 */
public class Inputs {
    public Boolean movingLeft;
    public Boolean movingRight;
    public Boolean jump;
    public Boolean power;
    public Boolean clickedPower;
    public Inputs(boolean i1,boolean i2,boolean i3,boolean i4){
        movingLeft = i1;
        movingRight = i2;
        jump = i3;
        power = i4;
        clickedPower = false;
    }
}
