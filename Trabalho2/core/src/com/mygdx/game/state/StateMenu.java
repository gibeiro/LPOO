package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.gui.GUIMenu;
import com.mygdx.game.logic.Menu;

// TODO: Auto-generated Javadoc
/**
 * Estado que representa o Menu principal.
 */
public class StateMenu extends State {
    
    /** The menu. */
    Menu menu;
    
    /** The menu gui. */
    GUIMenu menuGUI;
    
    /** The safecounter. */
    float safecounter; // Para nao carregar acidentalmente num botao logo depois de o menu abrir

    /**
     * Instantiates a new state menu.
     *
     * @param s the s
     */
    public StateMenu(StateManager s) {
        super(s);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        menu = new Menu();
        menuGUI = new GUIMenu();
        safecounter = 1;
    }

    /* (non-Javadoc)
     * @see com.mygdx.game.state.State#handleInput()
     */
    @Override
    public void handleInput(){

        if(menuGUI.playButton.isPressed() && safecounter < 0){
            menu.setPlayPressed();
        }
        if(menuGUI.playButtonMult.isPressed() && safecounter < 0){
            menu.setPlayMultPressed();
        }

    }

    /* (non-Javadoc)
     * @see com.mygdx.game.state.State#update(double)
     */
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

    /* (non-Javadoc)
     * @see com.mygdx.game.state.State#render()
     */
    @Override
    public void render() {
        menuGUI.render(menu);

    }

    /* (non-Javadoc)
     * @see com.mygdx.game.state.State#dispose()
     */
    @Override
    public void dispose() {
        menuGUI.dispose();

    }
}
