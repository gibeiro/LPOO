package com.client;

import java.io.IOException;
import com.server.ServerInterface;
import lipermi.handler.CallHandler;
import lipermi.net.Client;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GameClient extends Activity {

    private String serverIP = "192.168.1.231";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGet = (Button) findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new Conn().execute();
            }
        });

    }

    class Conn extends AsyncTask<Void, Void, GameClient> {

        @Override
        protected GameClient doInBackground(Void... params) {
            Looper.prepare();
            try {
                CallHandler callHandler = new CallHandler();
                Client client = new Client(serverIP, 7777, callHandler);
                ServerInterface testService = (ServerInterface) client.getGlobal(ServerInterface.class);
                String msg = testService.getResponse("qwe");
                //Toast.makeText(MainActivity.this, testService.getResponse("abc"), Toast.LENGTH_SHORT).show();
                Toast.makeText(GameClient.this, msg, Toast.LENGTH_SHORT).show();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Looper.loop();
            return null;
        }

    }
}