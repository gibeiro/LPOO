package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gui.GUIMenu;
import com.mygdx.game.logic.Menu;

/**
 * Estado que representa o Menu principal
 */
public class StateMenu extends State {
    Menu menu;
    GUIMenu menuGUI;
    float safecounter; // Para nao carregar acidentalmente num botao logo depois de o menu abrir

    public StateMenu(StateManager s) {
        super(s);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        menu = new Menu();
        menuGUI = new GUIMenu();
        safecounter = 1;
    }

    @Override
    public void handleInput(){

        if(menuGUI.playButton.isPressed() && safecounter < 0){
            menu.setPlayPressed();
        }
        if(menuGUI.playButtonMult.isPressed() && safecounter < 0){
            menu.setPlayMultPressed();
        }

    }

    @Override
    public void update(double dt) {
        safecounter-= dt;
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
        menuGUI.dispose();

    }
}
