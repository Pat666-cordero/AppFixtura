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
        String[] disciplinasList = new String[] {
          "Fultbol 8", "Voley Mixto", "Futsal", "Basquet", "Canotaje"
        };
        Spinner spinner = root.findViewById(R.id.disciplina);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, disciplinasList);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);


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