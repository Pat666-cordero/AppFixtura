package com.example.fixtura.ui.desafio;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.toolbox.ImageLoader;
import com.example.fixtura.R;
import com.example.fixtura.adapters.DesafioAdaptador;
import com.example.fixtura.helpers.QueueUtils;
import com.example.fixtura.models.Desafio;

import java.util.ArrayList;

public class ConsultarDesafioFragment extends Fragment {

    ListView lista;
    DesafioAdaptador desafioAdaptador;
    ArrayList<Desafio> datos;
    QueueUtils.QueueObject encolador;
    ImageLoader encoladorImagenes;
    EditText et_buscar;
    boolean firstFilter = false;

    private ConsultarDesafioViewModel mViewModel;

    public static ConsultarDesafioFragment newInstance() {
        return new ConsultarDesafioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.consultar_desafio_fragment, container, false);


        encolador = QueueUtils.getInstance(this.getContext());
        encoladorImagenes = encolador.getImageLoader();
        datos = new ArrayList<>();

        Desafio.injectDesafiosFromCloud(encolador, datos, this);

        desafioAdaptador = new DesafioAdaptador(this.getContext(), datos, encoladorImagenes);
        lista = root.findViewById(R.id.lista);
        lista.setAdapter(desafioAdaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            /*    DetalleDesafioFragment o = new DetalleDesafioFragment();
                o.desafioObj = datos.get(i);
                int a = 1;
                a++;*/

                Bundle bundle = new Bundle();
                bundle.putInt("desafio_id", datos.get(i).id);
                Navigation.findNavController(view).
                        navigate(R.id.action_consultarDesafioFragment2_to_detalleDesafioFragment, bundle);
        /*        getFragmentManager().beginTransaction().replace(R.id.detalleDesafioFragment, o)
                        .addToBackStack(null).commit();*/
            }
        });
        et_buscar = root.findViewById(R.id.et_buscar);
        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //desafioAdaptador.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ( editable != null && editable.toString().length() > 3 ) {
                    firstFilter = true;
                    datos.clear();
                    ConsultarDesafioFragment.this.refreshList();
                    Desafio.injectDesafiosFilterFromCloud(encolador, datos, editable.toString(),
                            ConsultarDesafioFragment.this);
                } else {
                    if ( firstFilter ) {
                        datos.clear();
                        ConsultarDesafioFragment.this.refreshList();
                        Desafio.injectDesafiosFromCloud(encolador, datos,
                                ConsultarDesafioFragment.this);
                        firstFilter = false;
                    }
                }
            }
        });




        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConsultarDesafioViewModel.class);
        // TODO: Use the ViewModel
    }
    public void refreshList() {
        if( this.desafioAdaptador != null ) {
            this.desafioAdaptador.notifyDataSetChanged();
        }
    }

}