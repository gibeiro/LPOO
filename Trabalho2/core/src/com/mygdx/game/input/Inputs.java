package com.mygdx.game.input;

/**
 * Created by up201403877 on 09-05-2016.
 */
public class Inputs {
    private Boolean movingLeft;
    private Boolean movingRight;
    private Boolean jump;
    private Boolean power;
    private Boolean clickedPower;
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

    public void setMovingLeft(Boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public Boolean getMovingRight() {
        return movingRight;
    }

    public void setMovingRight(Boolean movingRight) {
        this.movingRight = movingRight;
    }

    public Boolean getJump() {
        return jump;
    }

    public void setJump(Boolean jump) {
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
