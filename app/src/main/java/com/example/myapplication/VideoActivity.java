package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    private VideoView videoView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Inicializar VideoView
        videoView = findViewById(R.id.videoView);
        // Configuramos el video para la reproducción local
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.trailer;
        // Convierto la cadena en un URI
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        // Agrego controles de reproducción del video
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        // Vinculamos el controlador de video para mostrar en la app
        mediaController.setAnchorView(videoView);
        videoView.start();

        // Inicializar WebView
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario
        webView.setWebViewClient(new WebViewClient()); // Para abrir enlaces en el WebView y no en un navegador externo
        // Cargar una URL en el WebView
        webView.loadUrl("https://www.youtube.com/watch?v=i9KQVBPHnuo&pp=ygUVYWRtaXNpb24gc2FudG8gdG9tYXMg"); // Cambia esto por la URL que desees

        // Configurar el botón para redirigir a la actividad de inscripción
        Button botonFormulario = findViewById(R.id.botonFormulario);
        botonFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideoActivity.this, InscripcionActivity.class);
                startActivity(intent);
            }
        });

        // Configuración de reproducción del sonido MP3
        findViewById(R.id.reproducir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creo un MediaPlayer para reproducir el sonido
                MediaPlayer mediaPlayer = MediaPlayer.create(VideoActivity.this, R.raw.sonido);
                // Inicio la reproducción
                mediaPlayer.start();
                // Listener para liberar recursos cuando sonido termine
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release(); // Libero recursos cuando el sonido termine
                    }
                });
            }
        });
    }
}