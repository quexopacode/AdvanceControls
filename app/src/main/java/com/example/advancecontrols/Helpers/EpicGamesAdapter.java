package com.example.advancecontrols.Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.advancecontrols.Entidades.EpicGames;
import com.example.advancecontrols.R;

import java.util.List;

public class EpicGamesAdapter extends ArrayAdapter<EpicGames> {

    private List<EpicGames> epicGames;

    public EpicGamesAdapter(Context context, List<EpicGames> epicGamesList){
        super(context, R.layout.listview_epicgames,epicGamesList);

        epicGames = epicGamesList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_epicgames, null);

        ImageView imvFoto = (ImageView)item.findViewById(R.id.imvFoto);
        imvFoto.setImageResource(epicGames.get(position).getFoto());

        TextView lblTitulo = (TextView)item.findViewById(R.id.lblTitulo);
        lblTitulo.setText(epicGames.get(position).getTitulo());

        TextView lblSubtitulo = (TextView)item.findViewById(R.id.lblSubtitulo);
        String subtitulo = epicGames.get(position).getSubtitulo();
        lblSubtitulo.setText(subtitulo);
        if (subtitulo.length() == 0){
            lblSubtitulo.setVisibility(View.INVISIBLE);
        }

        TextView lblPeso = (TextView)item.findViewById(R.id.lblPeso);
        lblPeso.setText(epicGames.get(position).getPeso());

        TextView lblReciente = (TextView)item.findViewById(R.id.lblReciente);
        lblReciente.setText(epicGames.get(position).getReciente());

        ImageView imvIcono = (ImageView)item.findViewById(R.id.imvIcono);
        imvIcono.setImageResource(epicGames.get(position).getIcono());
        return (item);
    }
}
