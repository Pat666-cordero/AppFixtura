package com.example.fixtura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fixtura.R;
import com.example.fixtura.models.Disciplina;

import java.util.List;

public class DisciplinaAdaptador extends ArrayAdapter<Disciplina> {
    Context context;
    private class ViewHolder {
        TextView nombre;
        TextView participantes;

        private ViewHolder() {
        }
    }

    public DisciplinaAdaptador(Context context, List<Disciplina> items) {
        super(context, 0, items);
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final Disciplina rowItem = (Disciplina) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_disciplina, null);
            holder = new ViewHolder();
            holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.participantes = (TextView) convertView.findViewById(R.id.participantes);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nombre.setText(rowItem.nombre);
        holder.participantes.setText(rowItem.participantes);
        return convertView;
    }
}
