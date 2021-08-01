package com.br.megabytesoftware.mydelivery;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Inicio do código para iniciar o Splash em fullScreen
        View decorView = getWindow().getDecorView();
        // Esconde tanto a barra de navegação e a barra de status .
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //Fim do código para iniciar o Splash em fullScreen


        //Executar algua regra de negócios
        //enquanto carrega a tela Splash
        //GPS
        //Ler Preferência do Usuário
        //Enviar Notificações

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(Splash.this, Dashboard.class); //Dashboard
                finish();
                startActivity(i);
            }
        },SPLASH_TIME_OUT);
    }
}
