package com.mygdx.game.client;

import com.mygdx.game.BounceBall;
import com.mygdx.game.server.Server;
import com.mygdx.game.server.ServerInterface;
import java.util.Scanner;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.badlogic.gdx.ApplicationAdapter;


public class Client extends UnicastRemoteObject implements ClientInterface {

    public Client() throws RemoteException {
        super( );
    }

    public static void main(String[] args) {
        try {

            ServerInterface proxy = (ServerInterface)Naming.lookup("rmi://localhost:1099/Server");

            System.out.print("Joining server ...");

            int id = proxy.join(new Client());
            if ( id == -1) {
                System.out.println("Server full.");
                return;
            }
            else
                System.out.println("Connected.");

            //Client loop here

            System.exit(0);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}