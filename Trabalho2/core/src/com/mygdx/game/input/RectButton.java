package com.mygdx.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Nuno on 12/05/2016.
 */
public class RectButton {
    int x1,y1,x2,y2;
    Texture buttonidle;
    Texture buttonpressed;
    Sound click;

    public RectButton(int x1,int y1,int x2,int y2, String a,boolean sound){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        if(sound == true)
            click = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
        else click = null;

        if(a != "null"){
            buttonidle = new Texture(a);
            buttonpressed = new Texture(a);
        }else{
            buttonidle = null;
            buttonpressed = null;
        }

    }
    public RectButton(int x1,int y1,int x2,int y2, String a, String b,boolean sound){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        if(sound == true)
            click = Gdx.audio.newSound(Gdx.files.internal("sound/click.mp3"));
        else click = null;
        buttonidle = new Texture(a);
        buttonpressed = new Texture(b);

    }

    public boolean isPressed(){
        for(int i = 0;i < 10;i++){
            if(!Gdx.input.isTouched(i))
                continue;
            float x = Gdx.input.getX(i);
            float y = Gdx.input.getY(i);
            if(x > x1 &&
                    x < x2 &&
                    y > y1 &&
                    y < y2){
                if(click != null){
                    click.stop();
                    click.play();
                }
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

    public Texture getButtonidle() {
        return buttonidle;
    }


    public Texture getButtonpressed() {
        return buttonpressed;
    }

    public void dispose(){
        if(buttonidle != null){
            buttonidle.dispose();
        }
        if(buttonpressed != null){
            buttonpressed.dispose();
        }
        if(click != null)
            click.dispose();
    }

}
