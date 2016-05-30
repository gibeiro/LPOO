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
    public ServerGame(){
        handler1 = null;
        handler2 = null;
        game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        try{
           InetAddress addr = InetAddress.getByName("192.168.1.7");
           server = new ServerSocket(4456,50,addr);
           System.out.println("Server IP:"+ server.getInetAddress() + ":"+ 4456);
       }catch(Exception e){

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
        BufferedReader in;
        PrintWriter out;
        public boolean connected;
        public ServerHandler(){
            this.start();
        }
        public void run(){
            try{
                this.socket = server.accept();
                System.out.println("Player connected");
                connected = true;
                if(this == handler1){
                    id = 1;
                }else if(this == handler2){
                    id = 2;
                }
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                out.println("ID");
                out.println(id);

                while(connected){
                    if(in.ready())
                        readInfo();
                }
            }catch(Exception e){

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
                    game.setGameEnd(true);
                    if(this == handler1){
                        handler1 = null;
                    }else if(this == handler2){
                        handler2 = null;
                    }
                    connected = false;
                }
            }catch(Exception e){

            }
        }
        public void sendInfo(InfoGame info){
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
            s+=info.p2x;
            s+="\n";
            s+=info.p2y;
            s+="\n";
            s+=info.p2vx;
            s+="\n";
            s+=info.p2vy;
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
            out.println(s);
        }
    }


}
