package com.example.fixtura.ui.puntaje;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.fixtura.R;

public class InsertarPuntajeFragment extends Fragment {

    private InsertarPuntajeViewModel mViewModel;

    public static InsertarPuntajeFragment newInstance() {
        return new InsertarPuntajeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.insertar_puntaje_fragment, container, false);
        //
        String[] invitadoList = new String[] {
                "Developers", "Amateur", "Arduino"
        };
        Spinner spinner = root.findViewById(R.id.invitado);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, invitadoList);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);

        String[] retadorList = new String[] {
                "Developers", "Amateur", "Arduino"
        };
        Spinner spinner1 = root.findViewById(R.id.retador);
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, retadorList);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador1);

        String[] disciplinaList = new String[] {
                "Futbol", "Voley", "Futsal"
        };
        Spinner spinner2 = root.findViewById(R.id.disciplina);
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, disciplinaList);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adaptador2);

        //
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InsertarPuntajeViewModel.class);
        // TODO: Use the ViewModel
    }

}