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
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.toolbox.ImageLoader;
import com.example.fixtura.R;
import com.example.fixtura.adapters.DisciplinaAdaptador;
import com.example.fixtura.adapters.PersonaAdaptador;
import com.example.fixtura.helpers.QueueUtils;
import com.example.fixtura.models.Disciplina;
import com.example.fixtura.models.Persona;

import java.util.ArrayList;

public class ConsultarJugadorFragment extends Fragment {

    public Disciplina disciplinaObj;
    ListView lista;
    DisciplinaAdaptador disciplinaAdaptador;
    ArrayList<Disciplina> datos;
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
        //int discplinaId = 1;

        Disciplina.injectDisciplinasFromCloud(encolador, datos,this);

        disciplinaAdaptador = new DisciplinaAdaptador(this.getContext(), datos);
        lista = root.findViewById(R.id.lista);
        lista.setAdapter(disciplinaAdaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Bundle bundle = new Bundle();
                bundle.putInt("discplina_id", datos.get(i).id);
                Navigation.findNavController(view).
                        navigate(R.id.action_consultarJugadorFragment2_to_detalleJugadorFragment, bundle);
        /*        getFragmentManager().beginTransaction().replace(R.id.detalleDesafioFragment, o)
                        .addToBackStack(null).commit();*/
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConsultarJugadorViewModel.class);
        // TODO: Use the ViewModel
    }

    public void refreshList() {
        if( this.disciplinaAdaptador != null ) {
            this.disciplinaAdaptador.notifyDataSetChanged();
        }
    }

}