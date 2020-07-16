package com.example.fixtura.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fixtura.helpers.QueueUtils;
import com.example.fixtura.ui.jugador.ConsultarJugadorFragment;
import com.example.fixtura.ui.jugador.DetalleJugadorFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Disciplina {
    public Integer id;
    public String nombre;
    public String participantes;

    public Disciplina(Integer _id ,String _nombre, String _participantes) {
        this.id = _id;
        this.nombre = _nombre;
        this.participantes = _participantes;
    }

    public static ArrayList getCollection() {
        ArrayList<Disciplina> collection = new ArrayList<>();
        collection.add(new Disciplina(0,"Canotaje", "12"));
        return collection;
    }

    public static void injectDisciplinasFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Disciplina> disciplinas,
                                               final ConsultarJugadorFragment _interface) {
        String url = "https://e448cdf9014d.ngrok.io/api/auth/disciplinas";
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
                                            disciplinas.add(new Disciplina(o.getInt("id"),o.getString("nombre"),
                                                    o.getString("participantes")));
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
    }
}
