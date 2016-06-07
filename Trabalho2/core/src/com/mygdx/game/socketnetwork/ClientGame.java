package com.mygdx.game.socketnetwork;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.input.Inputs;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

// TODO: Auto-generated Javadoc
/**
 * Representa um client que se vai juntar a um servidor.
 */
public class ClientGame {
    
    /** Indica se o jogador está num estado de espera (aguardando jogador || timeout || vitoria || derrota || inimigo saiu). */
    public boolean inWait;
    
    /** Contador para mensagem temporaria indicativa de timeout. */
    public float timeOutTimer;
    
    /** Contador para mensagem temporaria indicativa da saída do inimigo. */
    public float enemyLeftTimer;
    
    /** Contador para mensagem temporaria indicativa da vitoria do jogador. */
    public float youWonTimer;
    
    /** Contador para mensagem temporaria indicativa da derrota do jogador. */
    public float youLostTimer;
    
    /** Indica se o jogador está na seleção da personagem. */
    public boolean inSelect;
    
    /** Indica se o jogador está no jogo. */
    public boolean inGame;
    
    /** Indica se o jogador tem o menu pause aberto. */
    public boolean inPause;
    
    /** Indica o poder selecionado pelo jogador e a ser enviado para o servidor. */


    public int powerSelected;
    
    /** Indica se o jogador está pronto a começar o jogo. */
    public boolean ready;

    /** Jogo local do cliente usado para fazer render e prever os updates do servidor para maior suavidade. */
    public Game game;
    
    /** Inputs do jogador a ser enviados para o servidor. */
    public Inputs i;
    
    /** Id do jogador(pode ser 1 ou 2). */
    public int id;
    
    /** Leitor de informação do servidor. */
    public BufferedReader in;
    
    /** Imprime informacao para o servidor. */
    PrintWriter out;

    /** The socket. */
    Socket socket;
    
    /** Booleano que indica se o jogador está ligado ao servidor. */
    public boolean connected;
    
    /** Booleano que indica se o jogador deu timeout. */
    public boolean timedOut;

    /**
     * Cria um novo client, criando tambem o handler.
     */
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
    
    /**
     * Send info.
     */
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
    
    /**
     * Send message.
     *
     * @param s the s
     */
    public void sendMessage(String s){
        out.println(s);
    }

    /**
     * Recebe informacao do servidor e atualiza o estado do jogo consoante a informação(informação presente no comentário da funcao render da classe BounceBallServer).
     */
    public void readInfo(){
        try{
            if(in.ready()){
                InfoGame info = new InfoGame();
                String s = in.readLine();
                if(s.equals("POSITIONS")){
                    info.p1x = Float.parseFloat(in.readLine());
                    info.p1y = Float.parseFloat(in.readLine());
                    info.p1vx = Float.parseFloat(in.readLine());
                    info.p1vy = Float.parseFloat(in.readLine());
                    info.p1m = Float.parseFloat(in.readLine());
                    info.p1t = Float.parseFloat(in.readLine());
                    info.p2x = Float.parseFloat(in.readLine());
                    info.p2y = Float.parseFloat(in.readLine());
                    info.p2vx = Float.parseFloat(in.readLine());
                    info.p2vy = Float.parseFloat(in.readLine());
                    info.p2m = Float.parseFloat(in.readLine());
                    info.p2t = Float.parseFloat(in.readLine());
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
                    System.out.println("Oponent left");
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

    /**
     * Handler do client.Thread cuja função é receber a informação do servidor em paralelo.
     * Ao abrir handler,é pedido o ip do servidor numa caixa de texto.
     */
    public class ClientHandler extends Thread implements Input.TextInputListener{
        
        /** The ip. */
        String ip;
        
        /**
         * Instantiates a new client handler.
         */
        public ClientHandler(){

            this.start();

        }

        public void run(){
            try{
                Gdx.input.getTextInput(this,"IP Address","","");
                while(ip == null){

                }
                InetSocketAddress sa = new InetSocketAddress(ip,4456);
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

        /**
         * Input.
         *
         * @param text the text
         */
        @Override
        public void input(String text) {
            ip = text;
        }

        /**
         * Canceled.
         */
        @Override
        public void canceled() {
            ip = "";
        }
    }
    /**
     * No caso de perder ligaçao ao servidor, esta funcao é chamada para mostrar uma mensagem de timeOut durante 3 segundos e finalmente fechar.
     */
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
