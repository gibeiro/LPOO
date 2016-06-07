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
 * Servidor que terá 2 clientes.
 * A transmissão de informação é feita com Sockets.
 * Ao ligarem 2 clientes ao servidor, o jogo dará início, com a seleção das personagens seguido do countdown e por fim o jogo.
 */
public class ServerGame {
    /**
     * Jogo corrido no servidor cuja informação é periodicamente enviada aos clients.
     */
    public Game game;
    /**
     * Handler para o jogador 1
     */
    public ServerHandler handler1;
    /**
     * Handler para o jogador 2
     */
    public ServerHandler handler2;
    /**
     * Servidor
     */
    ServerSocket server;
    /**
     * Booleano que indica se se está em jogo
     */
    public boolean inGame;
    /**
     * Booleano que indica se se está na seleção da personagem OU quando se está à espera de jogadores
     */
    public boolean inSelect;

    /**
     * Cria um novo server sem handlers
     */
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

    /**
     * Abre um novo handler para um client.
     */
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

    /**
     * Handler para um client, que vai receber informação deste passivamente num thread separado.
     * So devem existir 2, sendo cada um para cada client.
     * Ao sair um dos clients, o handler deste é terminado, e de seguida iniciado um novo.
     */
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

        /**
         * Lê informação recebida pelo socket, define que tipo de informação é através da primeira string.
         */
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

        /**
         * Envia todas as informações relativas às entidades presentes no jogo para o cliente(apenas é usada depois de o jogo dar inicio).
         * @param info
         */
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
            s+=info.p1t;
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
            s+=info.p2t;
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

        /**
         * Envia uma certa mensagem ao client.
         */
        public void sendMessage(String s){
            out.println(s);
        }

        /**
         * No caso de um client dar Timeout, esta função é chamada para retornar ao estado de aguardar por um 2º jogador.
         */
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

        /**
         * No caso de um client sair, esta função é chamada para retornar ao estado de aguardar por um 2º jogador.
         */
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
