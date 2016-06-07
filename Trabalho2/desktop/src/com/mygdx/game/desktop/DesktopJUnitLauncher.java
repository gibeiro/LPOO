package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BounceBallJUnit;
import com.mygdx.game.BounceBallServer;

/**
 * Launcher de Testes unitários
 */
public class DesktopJUnitLauncher {
    public static void main (String[] arg) {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new BounceBallJUnit(), config);
    }
}
