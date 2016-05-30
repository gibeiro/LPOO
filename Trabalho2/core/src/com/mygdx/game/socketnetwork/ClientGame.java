package com.mygdx.game.socketnetwork;

import com.mygdx.game.input.Inputs;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Nuno on 30/05/2016.
 */
public class ClientGame {
    Socket socket;
    public Inputs i;
    public Game game;
    public int id;
    public BufferedReader in;
    PrintWriter out;
    public boolean connected;
    public ClientGame(){
        game = new Game();
        game.setPlayer1(new Player(game.getWorld(),20,15));
        game.setPlayer2(new Player(game.getWorld(),80,15));
        connected = false;
        i = new Inputs(false,false,false,false);
        new ClientHandler();

    }
    public void sendInfo(){
        String s = new String();
        s+="INPUTS";
        s+="\n";
        s+=i.movingLeft ? 1 : 0;
        s+="\n";
        s+=i.movingRight ? 1 : 0;
        s+="\n";
        s+=i.jump ? 1 : 0;
        s+="\n";
        s+=i.power ? 1 : 0;
        out.println(s);
    }
    public void readInfo(){
        try{
            if(in.ready()){
                InfoGame info = new InfoGame();
                String s = in.readLine();
                if(s.equals("POSITIONS")){
                    System.out.println("Recebi info");
                    info.p1x = Float.parseFloat(in.readLine());
                    info.p1y = Float.parseFloat(in.readLine());
                    info.p1vx = Float.parseFloat(in.readLine());
                    info.p1vy = Float.parseFloat(in.readLine());
                    info.p2x = Float.parseFloat(in.readLine());
                    info.p2y = Float.parseFloat(in.readLine());
                    info.p2vx = Float.parseFloat(in.readLine());
                    info.p2vy = Float.parseFloat(in.readLine());
                    info.bx = Float.parseFloat(in.readLine());
                    info.by = Float.parseFloat(in.readLine());
                    info.bvx = Float.parseFloat(in.readLine());
                    info.bvy = Float.parseFloat(in.readLine());
                    info.ba = Float.parseFloat(in.readLine());
                    System.out.println(info.by);
                    game.updateGame(info);
                }

            }
        }catch(Exception e){
            System.out.println("Excepcao");
        }
    }
    public class ClientHandler extends Thread{
        public ClientHandler(){

            this.start();

        }
        public void run(){
            try{
                InetSocketAddress sa = new InetSocketAddress("192.168.1.7",4456);
                socket = new Socket();
                socket.connect(sa);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                connected = true;
                String s = in.readLine();
                if(s.equals("ID"))
                    id = Integer.parseInt(in.readLine());
                else return;
                while(connected){
                    if(in.ready())
                        readInfo();
                }


            }catch(Exception e){
                System.out.println("Couldn't connect");
            }
            connected = false;



        }
    }

}
