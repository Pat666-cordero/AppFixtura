package com.example.fixtura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fixtura.R;
import com.example.fixtura.models.Desafio;
import com.example.fixtura.models.Jugador;

import java.util.List;

public class JugadorAdaptador extends ArrayAdapter<Jugador> {
    Context context;
    //ImageLoader queue;

    private class ViewHolder {
        TextView equipoNombre;
        //NetworkImageView equipoImage;
        TextView jugadorNombre;
        TextView jugadorApellido;
        //NetworkImageView retadorImage;

        private ViewHolder() {
        }
    }

    public JugadorAdaptador(Context context, List<Jugador> items/*, ImageLoader _queue */) {
        super(context, 0, items);
        this.context = context;
        //this.queue = _queue;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Jugador rowItem = (Jugador) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_jugador, null);
            holder = new ViewHolder();
            holder.equipoNombre = (TextView) convertView.findViewById(R.id.equipoNombre);
            //holder.equipoImage = (NetworkImageView) convertView.findViewById(R.id.equipoImage);
            holder.jugadorNombre = (TextView) convertView.findViewById(R.id.jugadorNombre);
            holder.jugadorApellido = (TextView) convertView.findViewById(R.id.jugadorApellido);
            //holder.jugadorFoto = (NetworkImageView) convertView.findViewById(R.id.jugadorFoto);
            convertView.setTag(holder);
        } else {
            holder = (JugadorAdaptador.ViewHolder) convertView.getTag();
        }
        holder.equipoNombre.setText(rowItem.equipoNombre);
        holder.jugadorNombre.setText(rowItem.jugadorNombre);
        holder.jugadorApellido.setText(rowItem.jugadorApellido);
        //holder.retadorImage.setImageUrl(rowItem.retadorImage, this.queue);
        return convertView;
    }
}
