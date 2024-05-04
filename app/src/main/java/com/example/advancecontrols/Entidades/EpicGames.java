package com.example.advancecontrols.Entidades;

public class EpicGames {
    private int foto;
    private int icono;
    private String titulo;
    private String subtitulo;
    private String reciente;
    private String peso;

    public EpicGames(int f,int i, String t, String s, String r, String p){
        foto = f;
        icono = i;
        titulo = t;
        subtitulo = s;
        reciente = r;
        peso = p;
    }

    public int getFoto() {
        return foto;
    }

    public int getIcono() {
        return icono;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getReciente() {
        return reciente;
    }

    public String getPeso() {
        return peso;
    }

    public String getSubtitulo() {
        return subtitulo;
    }
}
