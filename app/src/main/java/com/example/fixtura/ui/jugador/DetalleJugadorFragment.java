package com.example.fixtura.ui.jugador;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.toolbox.ImageLoader;
import com.example.fixtura.R;
import com.example.fixtura.adapters.DisciplinaAdaptador;
import com.example.fixtura.adapters.PersonaAdaptador;
import com.example.fixtura.helpers.QueueUtils;
import com.example.fixtura.models.Disciplina;
import com.example.fixtura.models.Persona;

import java.util.ArrayList;

public class DetalleJugadorFragment extends Fragment {

    public Disciplina disciplinaObj;
    ListView lista;
    PersonaAdaptador personaAdaptador;
    ArrayList<Persona> datos;
    QueueUtils.QueueObject encolador;
    ImageLoader encoladorImagenes;

    private DetalleJugadorViewModel mViewModel;

    public static DetalleJugadorFragment newInstance() {
        return new DetalleJugadorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.detalle_jugador_fragment, container, false);

        encolador = QueueUtils.getInstance(this.getContext());
        encoladorImagenes = encolador.getImageLoader();
        datos = new ArrayList<>();
        //aqui de alguna manera tienes que tenes el discpina ID
        //para poder llamar a las personas de esa disciplina.
        //Partiendo de que tu discplina es el 1
        int discplinaId = 1;
        Persona.injectJugadoresFromCloud(encolador, datos,0,this);

        personaAdaptador = new PersonaAdaptador(this.getContext(), datos, encoladorImagenes);
        lista = root.findViewById(R.id.lista);
        lista.setAdapter(personaAdaptador);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetalleJugadorViewModel.class);
        // TODO: Use the ViewModel
    }

    public void refreshList() {
        if( this.personaAdaptador != null ) {
            this.personaAdaptador.notifyDataSetChanged();
        }
    }


}