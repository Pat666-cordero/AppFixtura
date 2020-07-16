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
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fixtura.R;
import com.example.fixtura.adapters.DesafioAdaptador;
import com.example.fixtura.helpers.QueueUtils;
import com.example.fixtura.models.Desafio;

import java.util.ArrayList;

public class DetalleDesafioFragment extends Fragment {

    //public Desafio desafioObj;
    ImageLoader imageLoader;
    QueueUtils.QueueObject encolador;
    ImageLoader encoladorImagenes;

    public int desafioId;
    public Desafio desafioObject;
    private DetalleDesafioViewModel mViewModel;
    View root;
    Button btnAddInvitado;
    Button btnAddRetador;
    public static DetalleDesafioFragment newInstance() {
        return new DetalleDesafioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.detalle_desafio_fragment, container, false);

        btnAddInvitado = root.findViewById(R.id.invitadoAdd);
        btnAddRetador = root.findViewById(R.id.retadorAdd);

        desafioId = getArguments().getInt("desafio_id");
        desafioObject = new Desafio("-", "-", "-", "", "");
        desafioObject.id = desafioId;
        encolador = QueueUtils.getInstance(this.getContext());
        encoladorImagenes = encolador.getImageLoader();
        Desafio.injectDesafioFromCloud(encolador, desafioObject, this);

        btnAddInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddInvitado.setEnabled(false);
                desafioObject.invitadoPunto += 1;
                Desafio.updateDesafioFromCloud(encolador, desafioObject,
                        "invitado", DetalleDesafioFragment.this);
            }
        });

        btnAddRetador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddRetador.setEnabled(false);
                desafioObject.retadorPunto += 1;
                Desafio.updateDesafioFromCloud(encolador, desafioObject,
                        "retador", DetalleDesafioFragment.this);
            }
        });

        return root;
    }
    public void markAsOk() {

    }
public void refreshList() {
        pintar();
}
    public  void pintar() {
        TextView invitadoNombre = root.findViewById(R.id.invitadoNombre);
        invitadoNombre.setText(this.desafioObject.invitadoNombre);
        NetworkImageView invitadoImage = root.findViewById(R.id.invitadoImage);
        imageLoader = QueueUtils.getInstance(this.getContext()).getImageLoader();
        invitadoImage.setImageUrl(this.desafioObject.invitadoImage, imageLoader);
        TextView retadorNombre = root.findViewById(R.id.retadorNombre);
        retadorNombre.setText(this.desafioObject.retadorNombre);
        NetworkImageView retadorImage = root.findViewById(R.id.retadorImage);
        imageLoader = QueueUtils.getInstance(this.getContext()).getImageLoader();
        retadorImage.setImageUrl(this.desafioObject.retadorImage, imageLoader);
        // La fecha se tiene que analizar, eso te lo dejo.
        TextView fechaDesafio = root.findViewById(R.id.fechaDesafio);
        fechaDesafio.setText(this.desafioObject.getFechaFormat());
        btnAddInvitado.setText(this.desafioObject.invitadoPunto + "");
        btnAddInvitado.setEnabled(true);
        btnAddRetador.setText(this.desafioObject.retadorPunto + "");
        btnAddRetador.setEnabled(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetalleDesafioViewModel.class);
        // TODO: Use the ViewModel
    }

}