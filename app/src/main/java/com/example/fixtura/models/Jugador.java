package com.example.fixtura.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fixtura.ui.desafio.ConsultarDesafioFragment;
import com.example.fixtura.helpers.QueueUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Jugador {
    public String equipoNombre;
    public String equipoImage;
    public String jugadorNombre;
    public String jugadorApellido;
    public String jugadorFoto;

    public Jugador(String _equipoNombre/*, String _equipoImage*/, String _jugadorNombre,
                   String _jugadorApellido/*, String _jugadorFoto*/) {
        this.equipoNombre = _equipoNombre;
        //this.equipoImage = _equipoImage;
        this.jugadorNombre = _jugadorNombre;
        this.jugadorApellido = _jugadorApellido;
        //this.jugadorFoto = _jugadorFoto;
    }

    public static ArrayList getCollection() {
        ArrayList<Jugador> collection = new ArrayList<>();
        collection.add(new Jugador("Anonymous", "Jose",
                "Campos"));
        return collection;
    }

    /*public static void injectJugadoresFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Jugador> jugadores,
                                               final ConsultarDesafioFragment _interface) {
        String url = "https://protected-fjord-91518.herokuapp.com/api/auth/personas";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (response.has("objects")) {
                                    try {
                                        JSONArray list = response.getJSONArray("objects");
                                        for(int i = 0; list.length() > i; i++) {

                                            JSONObject o = list.getJSONObject(i);
                                            String fecha = o.getString("desafio");
                                            JSONObject invitado = o.getJSONObject("invitado");
                                            JSONObject retador = o.getJSONObject("retador");
                                            desafios.add(new Desafio(
                                                    invitado.getString("nombre"),
                                                    invitado.getString("image"),
                                                    retador.getString("nombre"),
                                                    retador.getString("image"),
                                                    fecha));

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    _interface.refreshList(); // Esta función debemos implementarla
                                    // en nuestro activity
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }*/
}
