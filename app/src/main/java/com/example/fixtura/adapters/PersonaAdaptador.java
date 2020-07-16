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
import com.example.fixtura.models.Persona;

import java.util.List;

public class PersonaAdaptador extends ArrayAdapter<Persona> {
    Context context;
    ImageLoader queue;

    private class ViewHolder {
        TextView nombreCompleto;
        NetworkImageView foto;

        private ViewHolder() {
        }
    }

    public PersonaAdaptador(Context context, List<Persona> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue = _queue;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Persona rowItem = (Persona) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_jugador, null);
            holder = new ViewHolder();
            holder.nombreCompleto = (TextView) convertView.findViewById(R.id.nombre);
            holder.foto = (NetworkImageView) convertView.findViewById(R.id.foto);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nombreCompleto.setText(rowItem.nombreCompleto);
        holder.foto.setImageUrl(rowItem.foto, this.queue);
        return convertView;
    }
}
