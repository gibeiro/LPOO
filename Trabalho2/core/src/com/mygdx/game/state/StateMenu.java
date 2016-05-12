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
    GUIMenu menuGUI;

    public StateMenu(StateManager s) {
        super(s);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        menu = new Menu();
        menuGUI = new GUIMenu();
    }

    @Override
    public void handleInput(){

        if(menuGUI.playButton.isPressed()){
            menu.setPlayPressed();
        }
        if(menuGUI.playButtonMult.isPressed()){
            menu.setPlayMultPressed();
        }

    }

    @Override
    public void update(double dt) {
        if(!menu.getMenuEnd()){

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
        menuGUI.render(menu);

    }

    @Override
    public void dispose() {

    }
}
