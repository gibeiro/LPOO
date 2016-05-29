package com.mygdx.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Nuno on 12/05/2016.
 */
public class RectButton {
    int x1,y1,x2,y2;
    Texture buttonidle;
    Texture buttonpressed;

    public RectButton(int x1,int y1,int x2,int y2, String a){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;


        if(a != "null"){
            buttonidle = new Texture(a);
            buttonpressed = new Texture(a);
        }else{
            buttonidle = null;
            buttonpressed = null;
        }

    }
    public RectButton(int x1,int y1,int x2,int y2, Texture a, Texture b){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        buttonidle = a;
        buttonpressed = b;
    }

    public boolean isPressed(){
        for(int i = 0;i < 5;i++){
            if(!Gdx.input.isTouched(i))
                continue;
            float x = Gdx.input.getX(i);
            float y = Gdx.input.getY(i);
            if(x > x1 &&
                    x < x2 &&
                    y > y1 &&
                    y < y2){
                return true;
            }
        }
        return false;
    }
    public void render(SpriteBatch s){
        if(buttonidle == null)
            return;
        if(this.isPressed()){
            s.draw(buttonpressed,x1,-(-Gdx.graphics.getHeight()/2+y1)+Gdx.graphics.getHeight()/2-(y2-y1),x2-x1,y2-y1);
        }else{
            s.draw(buttonidle,x1,-(-Gdx.graphics.getHeight()/2+y1)+Gdx.graphics.getHeight()/2-(y2-y1),x2-x1,y2-y1);
        }
    }
}
