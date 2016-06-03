package com.mygdx.game.input;

import java.io.Serializable;

/**
 * Created by up201403877 on 09-05-2016.
 */
public class Inputs implements Serializable {
    public boolean movingLeft;
    public boolean movingRight;
    public boolean jump;
    public boolean power;
    public boolean clickedPower;
    public Inputs(boolean i1,boolean i2,boolean i3,boolean i4){
        movingLeft = i1;
        movingRight = i2;
        jump = i3;
        power = i4;
        clickedPower = false;
    }

    public Boolean getMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public Boolean getMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public Boolean getJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public Boolean getPower() {
        return power;
    }

    public void setPower(Boolean power) {
        this.power = power;
    }

    public Boolean getClickedPower() {
        return clickedPower;
    }

    public void setClickedPower(Boolean clickedPower) {
        this.clickedPower = clickedPower;
    }
}
