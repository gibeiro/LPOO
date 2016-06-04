package com.mygdx.game.socketnetwork;

import com.mygdx.game.input.Inputs;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Nuno on 30/05/2016.
 */
public class ClientGame {
    public boolean inWait;
    public float timeOutTimer;
    public float enemyLeftTimer;
    public float youWonTimer;
    public float youLostTimer;
    public boolean inSelect;
    public boolean inGame;
    public boolean inPause;
    public int powerSelected;
    public boolean ready;

    public Game game;
    public Inputs i;
    public int id;

    public BufferedReader in;
    PrintWriter out;

    Socket socket;
    public boolean connected;
    public boolean timedOut;

    public ClientGame(){
        powerSelected = -1;
        inWait = true;
        timeOutTimer = -1;
        enemyLeftTimer = -1;
        youWonTimer = -1;
        youLostTimer = -1;
        inSelect = false;
        inGame = false;
        inPause = false;
        ready = false;


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
    public void sendMessage(String s){
        out.println(s);
    }
    public void readInfo(){
        try{
            if(in.ready()){
                InfoGame info = new InfoGame();
                String s = in.readLine();
                System.out.println(s);
                if(s.equals("POSITIONS")){
                    info.p1x = Float.parseFloat(in.readLine());
                    info.p1y = Float.parseFloat(in.readLine());
                    info.p1vx = Float.parseFloat(in.readLine());
                    info.p1vy = Float.parseFloat(in.readLine());
                    info.p1m = Float.parseFloat(in.readLine());
                    info.p2x = Float.parseFloat(in.readLine());
                    info.p2y = Float.parseFloat(in.readLine());
                    info.p2vx = Float.parseFloat(in.readLine());
                    info.p2vy = Float.parseFloat(in.readLine());
                    info.p2m = Float.parseFloat(in.readLine());
                    info.bx = Float.parseFloat(in.readLine());
                    info.by = Float.parseFloat(in.readLine());
                    info.bvx = Float.parseFloat(in.readLine());
                    info.bvy = Float.parseFloat(in.readLine());
                    info.ba = Float.parseFloat(in.readLine());
                    info.p1i.movingLeft = Integer.parseInt(in.readLine()) != 0;
                    info.p1i.movingRight = Integer.parseInt(in.readLine()) != 0;
                    info.p1i.jump = Integer.parseInt(in.readLine()) != 0;
                    info.p1i.power = Integer.parseInt(in.readLine()) != 0;
                    info.p2i.movingLeft = Integer.parseInt(in.readLine()) != 0;
                    info.p2i.movingRight = Integer.parseInt(in.readLine()) != 0;
                    info.p2i.jump = Integer.parseInt(in.readLine()) != 0;
                    info.p2i.power = Integer.parseInt(in.readLine()) != 0;
                    game.updateGame(info);
                }else if(s.equals("SELECT") && timeOutTimer < 0 && youWonTimer < 0 && youLostTimer < 0 && enemyLeftTimer < 0){
                    inWait = false;
                    inSelect = true;
                }else if(s.equals("P1S")){
                    game.getPlayer1().setPower(Integer.parseInt(in.readLine()));
                    ready = true;
                }else if(s.equals("P2S")){
                    game.getPlayer2().setPower(Integer.parseInt(in.readLine()));
                    ready = true;
                }else if(s.equals("CD")){
                    inGame = true;
                    inSelect = false;
                    game.setCountdown(Float.parseFloat(in.readLine()));
                }else if(s.equals("GOAL1")){
                    game.getPlayer1().setGoals(game.getPlayer1().getGoals()+1);
                    game.resetPositions();
                    if(game.getPlayer1().getGoals() >= 5){
                        inGame = false;
                        inWait = true;
                        powerSelected = -1;
                        if(id == 1){
                            youWonTimer = 3;
                        }else youLostTimer = 3;
                        game = new Game();
                        game.setPlayer1(new Player(game.getWorld(),20,15));
                        game.setPlayer2(new Player(game.getWorld(),80,15));
                    }
                }else if(s.equals("GOAL2")){
                    game.getPlayer2().setGoals(game.getPlayer2().getGoals()+1);
                    game.resetPositions();
                    if(game.getPlayer2().getGoals() >= 5){
                        inGame = false;
                        inWait = true;
                        powerSelected = -1;
                        if(id == 2){
                            youWonTimer = 3;
                        }else youLostTimer = 3;
                        game = new Game();
                        game.setPlayer1(new Player(game.getWorld(),20,15));
                        game.setPlayer2(new Player(game.getWorld(),80,15));
                    }
                }else if(s.equals("LEFT")){
                    System.out.println("saiu");
                    inWait = true;
                    enemyLeftTimer = 3;
                    inSelect = false;
                    inGame = false;
                    inPause = false;
                    ready = false;
                    powerSelected = -1;
                    game = new Game();
                    game.setPlayer1(new Player(game.getWorld(),20,15));
                    game.setPlayer2(new Player(game.getWorld(),80,15));
                }

            }
        }catch(Exception e){
            handleTimeOut();
        }
    }
    public class ClientHandler extends Thread{
        public ClientHandler(){

            this.start();

        }
        public void run(){
            try{
                InetAddress addr = InetAddress.getLocalHost();
                InetSocketAddress sa = new InetSocketAddress("192.168.1.7",4456);
                socket = new Socket();
                socket.setSoTimeout(3 * 1000);
                socket.connect(sa,3*1000);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                connected = true;
                String s = in.readLine();

                if(s.equals("ID"))
                    id = Integer.parseInt(in.readLine());
                else return;
                while(connected){
                    readInfo();
                }


            }catch(Exception e){
                handleTimeOut();
            }
            connected = false;
        }
    }
    void handleTimeOut(){
        System.out.println("Timed out");
        try{
            socket.close();
        }catch(Exception r){

        }
        timedOut = true;
        timeOutTimer = 3;
        connected = false;
        inGame = false;
        inWait = true;
    }

}
