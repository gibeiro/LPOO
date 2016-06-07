package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.Player;
import com.mygdx.game.socketnetwork.InfoGame;
import com.mygdx.game.socketnetwork.ServerGame;

/**
 * Servidor a ser criado no computador
 */
public class BounceBallServer extends ApplicationAdapter {
    /**
     * Taxa de envio de informacao do servidor para client
     */
    private final static float RATE = 0.06f;
    /**
     * Taxa de envio de informacao da mensagem de teste para client, para evitar timeout.
     */
    private final static float TESTRATE = 1;
    /**
     * Contador da taxa de envio de informacao do servidor para client
     */
    float rateCounter;
    /**
     * Contador da taxa de envio de informacao da mensagem de teste para client, para evitar timeout.
     */
    float testCounter;
    /**
     * Servidor
     */
    ServerGame server;
    float dt;
    @Override
    /**
     * Cria novo servidor
     */
    public void create () {
        rateCounter = 0;
        testCounter = 0;
        try{
            server = new ServerGame();
        }catch(Exception e){

        }
    }

    @Override
    /**
     * 1-Se um dos handlers for null, cria um novo(um handler pode tornar-se null ao sair um client do jogo ou na criacao do servidor)
     * 2-Se um dos handlers nao estiver ligado a um client, é enviado uma mensagem de espera ao outro client.
     * 3-Quando ambos os jogadores estao ligados, é enviada mensagem de seleção de personagem a ambos os clients.
     * 4-Quando forem recebidas as informações do poder do jogador, o jogo dá inicio, e são enviadas as posições das entidades, contagem decrescente, e se foi marcado um golo.
     * 5-Ao terminar o jogo, volta-se ao passo 3, selecao de personagem, de forma a dar inicio a um novo jogo.
     * 6-Se um jogador sair ou der timeout, volta-se ao passo 1.
     */
    public void render () {

        while(server.handler1 == null || server.handler2 == null){
            System.out.println("Player slot opened");
            server.openPlayerSlot();
        }
        dt = Gdx.graphics.getDeltaTime();
        rateCounter += dt;
        testCounter += dt;

        if(server.inGame){
            server.game.update(dt);
        }
        if(testCounter > TESTRATE){
            sendPassiveMessage();
            testCounter = 0;
        }
        if(rateCounter > RATE){


            if((server.handler1.connected == true && server.handler2.connected == false) || (server.handler1.connected ==false && server.handler2.connected == true)){
                sendWaitMessage();
            }

            if(server.handler1.connected == true && server.handler2.connected == true) {

                if (server.inSelect) {
                    if(server.handler1.powerSelected == -1)
                       server.handler1.sendMessage("SELECT");
                    if(server.handler2.powerSelected == -1)
                       server.handler2.sendMessage("SELECT");
                    if(server.handler1.powerSelected != -1 && server.handler2.powerSelected != -1){
                        server.game.getPlayer1().setPower(server.handler1.powerSelected);
                        server.game.getPlayer2().setPower(server.handler2.powerSelected);
                        server.handler1.sendMessage("P1S\n"+server.game.getPlayer1().getPower());
                        server.handler1.sendMessage("P2S\n"+server.game.getPlayer2().getPower());
                        server.handler2.sendMessage("P1S\n"+server.game.getPlayer1().getPower());
                        server.handler2.sendMessage("P2S\n"+server.game.getPlayer2().getPower());
                        server.inSelect = false;
                        server.inGame = true;
                    }
                }

                else if (server.inGame) {

                    server.game.update(dt);

                    if(server.game.isGameEnd()){
                        server.inSelect = true;
                        server.inGame = false;
                        server.handler1.powerSelected = -1;
                        server.handler2.powerSelected = -1;
                        server.game = new Game();
                        server.game.setPlayer1(new Player(server.game.getWorld(),20,15));
                        server.game.setPlayer2(new Player(server.game.getWorld(),80,15));
                    }else
                    if(server.game.getCountdown() > 0){
                        String s = "CD";
                        s+="\n";
                        s+=server.game.getCountdown();
                        server.handler1.sendMessage(s);
                        server.handler2.sendMessage(s);
                    }
                    {
                        int goal = server.game.checkGoals();
                        if(goal == 1){
                            server.handler1.sendMessage("GOAL1");
                            server.handler2.sendMessage("GOAL1");
                        }else if(goal == 2){
                            server.handler1.sendMessage("GOAL2");
                            server.handler2.sendMessage("GOAL2");
                        }else if(goal == 0){
                            InfoGame info = new InfoGame(server.game);
                            server.handler1.sendPos(info);
                            server.handler2.sendPos(info);
                        }
                    }
                }
            }
            rateCounter = 0;
        }

    }

    /**
     * Envia mensagem de espera ao unico jogador ligado, e coloca o poder do jogador ligado a -1 para o caso de ele já ter o poder definido do jogo anterior
     */
    public void sendWaitMessage(){
        if(server.handler1.connected == true && server.handler2.connected == false){
            server.handler1.powerSelected = -1;
            server.handler1.sendMessage("WAIT");
        }
        if(server.handler1.connected == false && server.handler2.connected == true){
            server.handler2.powerSelected = -1;
            server.handler2.sendMessage("WAIT");
        }
    }
    /**
     * Envia mensagem de teste para evitar timeout
     */
    public void sendPassiveMessage(){
        if(server.handler1.connected == true){
            server.handler1.sendMessage("TEST");//Testar se está ligado
        }
        if(server.handler2.connected == true){
            server.handler2.sendMessage("TEST");
        }
    }
}