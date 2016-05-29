package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BounceBall;
import com.mygdx.game.BounceBallServer;
import com.mygdx.game.network.GameServer;
import com.mygdx.game.network.ServerInterface;

import java.net.InetAddress;

import lipermi.handler.CallHandler;
import lipermi.net.Server;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BounceBallServer(), config);
	}
}
