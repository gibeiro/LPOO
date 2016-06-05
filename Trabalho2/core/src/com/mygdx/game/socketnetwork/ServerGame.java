package com.mygdx.game.socketnetwork;

import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Nuno on 30/05/2016.
 */
public class ServerGame {
    public Game game;
    public ServerHandler handler1;
    public ServerHandler handler2;
    ServerSocket server;
    public boolean inGame;
    public boolean inSelect;
    public ServerGame(){
        inGame = false;
        inSelect = true;
        handler1 = null;
        handler2 = null;
        game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        try{
            InetAddress addr = InetAddress.getLocalHost();
            server = new ServerSocket(4456,50,addr);
            System.out.println("Server IP:"+ server.getInetAddress().getHostAddress() + "\nPort:"+ 4456);
       }catch(Exception e){
            System.out.println("Error creating server, exiting...");
            System.exit(0);
       }
    }
    public void openPlayerSlot(){

        try{
              if(handler1 == null){
                   handler1 = new ServerHandler();
               }else if(handler2 == null){
                  handler2 = new ServerHandler();
              }
        }catch(Exception e){

        }
    }
    public class ServerHandler extends Thread{
        private Socket  socket;
        int id;
        public int powerSelected;
        BufferedReader in;
        PrintWriter out;
        public boolean connected;
        public ServerHandler(){
            powerSelected = -1;
            this.start();
        }
        public void run(){
            try{
                this.socket = server.accept();
                System.out.println("Player connected");
                this.socket.setSoTimeout(1000*3);
                if(this == handler1){
                    id = 1;
                }else if(this == handler2){
                    id = 2;
                }
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                out.println("ID");
                out.println(id);
                connected = true;

                while(connected){
                    readInfo();
                }
                System.out.println("Closed");
            }catch(Exception e){
                handleTimeout();
            }
        }
        public void readInfo(){
            try{
                String s = in.readLine();
                if(s.equals("INPUTS")){
                    if(id == 1){
                        game.getPlayer1().getInputs().movingLeft = (0 != Integer.parseInt(in.readLine()));
                        game.getPlayer1().getInputs().movingRight = (0 != Integer.parseInt(in.readLine()));
                        game.getPlayer1().getInputs().jump = (0 != Integer.parseInt(in.readLine()));
                        game.getPlayer1().getInputs().power = (0 != Integer.parseInt(in.readLine()));
                    }
                    else
                    if (id == 2){
                        game.getPlayer2().getInputs().movingLeft = (0 != Integer.parseInt(in.readLine()));
                        game.getPlayer2().getInputs().movingRight = (0 != Integer.parseInt(in.readLine()));
                        game.getPlayer2().getInputs().jump = (0 != Integer.parseInt(in.readLine()));
                        game.getPlayer2().getInputs().power = (0 != Integer.parseInt(in.readLine()));
                    }
                }else if(s.equals("EXIT")){
                    handleLeave();
                }else if(s.equals("POWER")){
                    powerSelected = Integer.parseInt(in.readLine());
                }
            }catch(Exception e){
                handleTimeout();
            }
        }
        public void sendPos(InfoGame info){
            String s = new String();
            s+="POSITIONS";
            s+="\n";
            s+=info.p1x;
            s+="\n";
            s+=info.p1y;
            s+="\n";
            s+=info.p1vx;
            s+="\n";
            s+=info.p1vy;
            s+="\n";
            s+=info.p1m;
            s+="\n";
            s+=info.p2x;
            s+="\n";
            s+=info.p2y;
            s+="\n";
            s+=info.p2vx;
            s+="\n";
            s+=info.p2vy;
            s+="\n";
            s+=info.p2m;
            s+="\n";
            s+=info.bx;
            s+="\n";
            s+=info.by;
            s+="\n";
            s+=info.bvx;
            s+="\n";
            s+=info.bvy;
            s+="\n";
            s+=info.ba;
            s+="\n";
            s+=info.p1i.movingLeft ? 1 : 0;
            s+="\n";
            s+=info.p1i.movingRight? 1 : 0;
            s+="\n";
            s+=info.p1i.jump? 1 : 0;
            s+="\n";
            s+=info.p1i.power? 1 : 0;
            s+="\n";
            s+=info.p2i.movingLeft? 1 : 0;
            s+="\n";
            s+=info.p2i.movingRight? 1 : 0;
            s+="\n";
            s+=info.p2i.jump? 1 : 0;
            s+="\n";
            s+=info.p2i.power? 1 : 0;
            out.println(s);
        }
        public void sendMessage(String s){
            out.println(s);
        }

        public void handleTimeout(){

            System.out.println("Client "+ id + " timed out.");
            connected = false;
            if(id == 1 && handler2.connected == true)
                handler2.out.println("LEFT");
            if(id == 2 && handler1.connected == true)
                handler1.out.println("LEFT");
            inSelect = true;
            inGame = false;
            game = new Game();
            game.setPlayer1(new Player(game.getWorld(),20,15));
            game.setPlayer2(new Player(game.getWorld(),80,15));
            if(id == 1){
                handler1 = null;
            }else if(id == 2){
                handler2 = null;
            }
            try{
                socket.close();
            }catch(Exception r){
                System.out.println("Couldn't close socket.");
            }
            return;
        }
        public void handleLeave(){
            System.out.println("Client "+ id + " left.");
            if(id == 1 && handler2.connected == true)
                handler2.out.println("LEFT");
            if(id == 2 && handler1.connected == true)
                handler1.out.println("LEFT");
            inSelect = true;
            inGame = false;
            game = new Game();
            connected = false;
            game.setPlayer1(new Player(game.getWorld(),20,15));
            game.setPlayer2(new Player(game.getWorld(),80,15));
            if(id == 1){
                handler1 = null;
            }else if(id == 2){
                handler2 = null;
            }
            try{
                socket.close();
            }catch(Exception r){
                System.out.println("Couldn't close socket.");
            }
            return;
        }


    }


}
