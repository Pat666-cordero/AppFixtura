package com.example.fixtura.ui.jugador;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fixtura.R;
import com.example.fixtura.adapters.JugadorAdaptador;
import com.example.fixtura.helpers.QueueUtils;
import com.example.fixtura.models.Jugador;

import java.util.ArrayList;

public class ConsultarJugadorFragment extends Fragment {

    ListView lista;
    JugadorAdaptador jugadorAdaptador;
    ArrayList<Jugador> datos;
    QueueUtils.QueueObject encolador;
    //ImageLoader encoladorImagenes;

    private ConsultarJugadorViewModel mViewModel;

    public static ConsultarJugadorFragment newInstance() {
        return new ConsultarJugadorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.consultar_jugador_fragment, container, false);

        encolador = QueueUtils.getInstance(this.getContext());
        //encoladorImagenes = encolador.getImageLoader();
        datos = new ArrayList<>();

        //Desafio.injectDesafiosFromCloud(encolador, datos, this);

        lista = root.findViewById(R.id.lista);
        jugadorAdaptador = new JugadorAdaptador(this.getContext(), Jugador.getCollection()/*datos, encoladorImagenes*/);
        lista.setAdapter(jugadorAdaptador);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConsultarJugadorViewModel.class);
        // TODO: Use the ViewModel
    }

    /*public void refreshList() {
        if( this.desafioAdaptador != null ) {
            this.desafioAdaptador.notifyDataSetChanged();
        }
    }*/

}