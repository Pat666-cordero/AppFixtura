package com.example.fixtura.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fixtura.helpers.QueueUtils;
import com.example.fixtura.ui.desafio.ConsultarDesafioFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Desafio {
    public String invitadoNombre;
    public String invitadoImage;
    public String retadorNombre;
    public String retadorImage;
    public String fecha;

    public Desafio(String _invitadoNombre, String _invitadoImage, String _retadorNombre,
                   String _retadorImage, String _fecha) {
        this.invitadoNombre = _invitadoNombre;
        this.invitadoImage = _invitadoImage;
        this.retadorNombre = _retadorNombre;
        this.retadorImage = _retadorImage;
        this.fecha = _fecha;
    }

    public static ArrayList getCollection() {
        ArrayList<Desafio> collection = new ArrayList<>();
        collection.add(new Desafio("Anonymous", "",
                "Monkeycoins", "", ""));
        return collection;
    }

    public static void injectDesafiosFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Desafio> desafios,
                                               final ConsultarDesafioFragment _interface) {
        String url = "https://acd3e6420677.ngrok.io/api/auth/desafios";
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
    }

    public static void injectDesafiosFilterFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Desafio> desafios,
                                               final String keyword,
                                               final ConsultarDesafioFragment _interface) {
        String url = "https://acd3e6420677.ngrok.io/api/auth/desafios?keyword=" + keyword;
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
    }

}
