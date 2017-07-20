package com.example.hzxr.websocketdemo;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;

        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.Response;
        import okhttp3.WebSocket;
        import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {
    public Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.bt_connect);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "ws://10.0.3.2:8080/WebSocketDemo2/websocket";
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                client.newWebSocket(request, new WebSocketListener() {
                    @Override
                    public void onOpen(WebSocket webSocket, Response response) {
                        Log.d("TAG","Open");
                    }

                    @Override
                    public void onMessage(WebSocket webSocket, String text) {
                        Log.d("TAG","Message:"+text);
                    }

                    @Override
                    public void onClosing(WebSocket webSocket, int code, String reason) {
                        Log.d("TAG","onclosing reason is" + String.valueOf(code)+reason);
                    }

                    @Override
                    public void onClosed(WebSocket webSocket, int code, String reason) {
                        Log.d("TAG","onclosed reason is" + String.valueOf(code)+reason);
                    }

                    @Override
                    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                        Log.e("Error",t.getMessage().toString());
                        Log.e("Error casuse",t.getCause().toString());
                        Log.e("Error",t.getStackTrace().toString());
                    }
                });

            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.start();
            }
        });
    }
}
