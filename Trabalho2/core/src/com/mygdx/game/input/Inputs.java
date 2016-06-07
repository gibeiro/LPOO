package com.mygdx.game.input;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Representa inputs do jogador.
 */
public class Inputs implements Serializable {
    
    /** Indica se o jogador está a carregar no botao de mover para esquerda. */
    public boolean movingLeft;
    
    /** Indica se o jogador está a carregar no botao de mover para direita. */
    public boolean movingRight;
    
    /** Indica se o jogador está a carregar no botao de mover para saltar. */
    public boolean jump;
    
    /** Indica se o jogador está a carregar no botao de usar poder. */
    public boolean power;
    
    /** Indica se o jogador está a segurar o botao (para nao repetir poderes instantaneos). */
    public boolean clickedPower;
    
    /**
     * Instantiates a new inputs.
     *
     * @param i1 the i1
     * @param i2 the i2
     * @param i3 the i3
     * @param i4 the i4
     */
    public Inputs(boolean i1,boolean i2,boolean i3,boolean i4){
        movingLeft = i1;
        movingRight = i2;
        jump = i3;
        power = i4;
        clickedPower = false;
    }

    /**
     * Gets the moving left.
     *
     * @return the moving left
     */
    public Boolean getMovingLeft() {
        return movingLeft;
    }

    /**
     * Sets the moving left.
     *
     * @param movingLeft the new moving left
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    /**
     * Gets the moving right.
     *
     * @return the moving right
     */
    public Boolean getMovingRight() {
        return movingRight;
    }

    /**
     * Sets the moving right.
     *
     * @param movingRight the new moving right
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * Gets the jump.
     *
     * @return the jump
     */
    public Boolean getJump() {
        return jump;
    }

    /**
     * Sets the jump.
     *
     * @param jump the new jump
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    /**
     * Gets the power.
     *
     * @return the power
     */
    public Boolean getPower() {
        return power;
    }

    /**
     * Sets the power.
     *
     * @param power the new power
     */
    public void setPower(Boolean power) {
        this.power = power;
    }

    /**
     * Gets the clicked power.
     *
     * @return the clicked power
     */
    public Boolean getClickedPower() {
        return clickedPower;
    }

    /**
     * Sets the clicked power.
     *
     * @param clickedPower the new clicked power
     */
    public void setClickedPower(Boolean clickedPower) {
        this.clickedPower = clickedPower;
    }
}
