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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DesafioAdaptador extends ArrayAdapter<Desafio> {
    Context context;
    ImageLoader queue;

    private class ViewHolder {
        TextView invitadoNombre;
        NetworkImageView invitadoImage;
        TextView retadorNombre;
        NetworkImageView retadorImage;
        //TextView fecha;


        private ViewHolder() {
        }
    }

    public DesafioAdaptador(Context context, List<Desafio> items, ImageLoader _queue) {
        super(context, 0, items);
        this.context = context;
        this.queue = _queue;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        final Desafio rowItem = (Desafio) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_desafio, null);
            holder = new ViewHolder();
            holder.invitadoNombre = (TextView) convertView.findViewById(R.id.invitadoNombre);
            holder.invitadoImage = (NetworkImageView) convertView.findViewById(R.id.invitadoImage);
            holder.retadorNombre = (TextView) convertView.findViewById(R.id.retadorNombre);
            holder.retadorImage = (NetworkImageView) convertView.findViewById(R.id.retadorImage);
            //holder.fecha = (TextView) convertView.findViewById(R.id.fechaDesafio);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.invitadoNombre.setText(rowItem.invitadoNombre);
        holder.invitadoImage.setImageUrl(rowItem.invitadoImage, this.queue);
        holder.retadorNombre.setText(rowItem.retadorNombre);
        holder.retadorImage.setImageUrl(rowItem.retadorImage, this.queue);
        //holder.fecha.setText(rowItem.fecha.get);
        return convertView;
    }
}
