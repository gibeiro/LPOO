package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BounceBall;
import com.mygdx.game.network.GameServer;
import com.mygdx.game.network.ServerInterface;

import lipermi.handler.CallHandler;
import lipermi.net.Server;

public class DesktopLauncher {
	public static void main (String[] arg) {

		try {
			GameServer gameServer = new GameServer();
			CallHandler callHandler = new CallHandler();
			callHandler.registerGlobal(ServerInterface.class, gameServer);
			Server server = new Server();
			int thePortIWantToBind = 4455;
			server.bind(thePortIWantToBind, callHandler);
			System.err.println("Server ready");
			while(!gameServer.game.gameEnd){
                 if(gameServer.clients.size() == 2)
					 gameServer.game.update(Gdx.graphics.getDeltaTime());
			}
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}

		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new BounceBall(), config);
	}
}
