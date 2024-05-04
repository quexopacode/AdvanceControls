package com.example.advancecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.advancecontrols.Entidades.EpicGames;
import com.example.advancecontrols.Helpers.EpicGamesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ListView lstEpicGames;
    DatePicker dpFecha;
    ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InicializarControles();
    }

    private void InicializarControles() {
        lstEpicGames = (ListView)findViewById(R.id.lstJuegos);
        this.SetearListView();

        dpFecha = (DatePicker)findViewById(R.id.dpFecha);

        pbProgress = (ProgressBar)findViewById(R.id.pbLoader);
    }

    private void SetearListView(){
        EpicGamesAdapter adapter = new EpicGamesAdapter(getApplicationContext(),this.ObtenerListadoJuegos());
        lstEpicGames.setAdapter(adapter);
    }

    private List<EpicGames> ObtenerListadoJuegos(){
        List<EpicGames> epicGamesList = new ArrayList<>();
        //RECORDAR SIEMPRE TRY CATCH
        //AQUI LOGICA DE OBTENER DATOS DESDE BD O WEBSERVICE
        epicGamesList.add(new EpicGames(R.drawable.rdr,R.drawable.points,"Red Dead Redemption II","","5","90 GB"));
        epicGamesList.add(new EpicGames(R.drawable.imgpsh_fullsize,R.drawable.points,"Persona 5","El juego que tiene a arsene y sin eso Joker no gana en smash","3","5 GB"));
        epicGamesList.add(new EpicGames(R.drawable.imgpsh_fullsize,R.drawable.points,"CyberPunk","LLL","6","EE"));
        epicGamesList.add(new EpicGames(R.drawable.ho,R.drawable.points,"Horizon Zero Dawn","LLL","1","EE"));
        epicGamesList.add(new EpicGames(R.drawable.botw,R.drawable.points,"BOTW","LLL","2","EE"));
        epicGamesList.add(new EpicGames(R.drawable.smash,R.drawable.points,"SMASH","LLL","4","EE"));
        epicGamesList.add(new EpicGames(R.drawable.cod,R.drawable.points,"Carlos Duty","LLL","1","EE"));
        epicGamesList.add(new EpicGames(R.drawable.fall,R.drawable.points,"Fall Guys","LLL","1","EE"));
        epicGamesList.add(new EpicGames(R.drawable.gow,R.drawable.points,"Godof War","LLL","6","EE"));
        return epicGamesList;
    }

    public void FiltrarListView(View v) throws InterruptedException {
        try {
            int anio = dpFecha.getYear();
            int diff = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                diff = (java.time.LocalDate.now().getYear()-anio);
            }
            List<EpicGames> juegos = this.ObtenerListadoJuegos();
            List<EpicGames> juegosFiltrados = new ArrayList<>();

            final int progress = Math.round(100 / juegos.size());
            pbProgress.setVisibility(View.VISIBLE);

            int finalDiff = diff;
            new CountDownTimer(juegos.size() * 1000, 1000) {
                int newProgess = 0;
                public void onTick(long millisUntilFinished) {
                    pbProgress.setProgress(newProgess);
                    newProgess += progress;
                    int reciente = Integer.parseInt(juegos.get(Math.round(millisUntilFinished/1000)).getReciente());
                    if (finalDiff >= reciente){
                        juegosFiltrados.add(juegos.get(Math.round(millisUntilFinished/1000)));
                    }
                }
                public void onFinish() {
                    pbProgress.setVisibility(View.GONE);
                }
            }.start();
            Log.d("PROGRESO", String.valueOf(progress));

            EpicGamesAdapter adapter = new EpicGamesAdapter(getApplicationContext(),juegosFiltrados);
            lstEpicGames.setAdapter(adapter);

        }catch (Exception e){

        }finally {

            //pbProgress.setVisibility(View.GONE);
        }
    }

}