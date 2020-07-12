package com.example.fixtura.ui.desafio;

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

import com.example.fixtura.R;

public class DetalleDesafioFragment extends Fragment {

    private DetalleDesafioViewModel mViewModel;

    public static DetalleDesafioFragment newInstance() {
        return new DetalleDesafioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.detalle_desafio_fragment, container, false);
        Button btnSaltar = root.findViewById(R.id.btnSaltar);
        btnSaltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_detalleDesafioFragment_to_consultarJugadorFragment2);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetalleDesafioViewModel.class);
        // TODO: Use the ViewModel
    }

}