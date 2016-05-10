package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.auxclass.Functions;
import com.mygdx.game.gui.GUIMenu;
import com.mygdx.game.logic.Menu;

/**
 * Created by Nuno on 02/05/2016.
 */
public class StateMenu extends State {
    Menu menu;
    GUIMenu menurenderer;

    public StateMenu(StateManager s) {
        super(s);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        menu = new Menu();
        menurenderer = new GUIMenu();
    }

    @Override
    public void handleInput(){

        if(Functions.rectanglePressed(Gdx.graphics.getWidth()/2-menurenderer.playButton.getWidth()/2,Gdx.graphics.getHeight()/2-menurenderer.playButton.getHeight()/2-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/2+menurenderer.playButton.getWidth()/2,Gdx.graphics.getHeight()/2+menurenderer.playButton.getHeight()/2-Gdx.graphics.getHeight()/10)){
            menu.setPlayPressed();
        }
        if(Functions.rectanglePressed(Gdx.graphics.getWidth()/2-menurenderer.playButton.getWidth()/2,Gdx.graphics.getHeight()/2-menurenderer.playButton.getHeight()/2+Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/2+menurenderer.playButton.getWidth()/2,Gdx.graphics.getHeight()/2+menurenderer.playButton.getHeight()/2+Gdx.graphics.getHeight()/10)){
            menu.setPlayMultPressed();
        }

    }

    @Override
    public void update(double dt) {
        if(!menu.menuEnd){

        }else{
            if(menu.getPlayPressed()){
                dispose();
                sm.pop();
                sm.push(new StateGame(sm));
            }else if(menu.getPlayMultPressed()){
                dispose();
                sm.pop();
                sm.push(new StateGameMultiplayer(sm));
            }
        }
    }

    @Override
    public void render() {
        menurenderer.render(menu);

    }

    @Override
    public void dispose() {

    }
}
