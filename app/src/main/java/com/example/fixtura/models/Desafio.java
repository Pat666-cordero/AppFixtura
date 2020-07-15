package com.example.fixtura.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fixtura.helpers.QueueUtils;
import com.example.fixtura.ui.desafio.ConsultarDesafioFragment;
import com.example.fixtura.ui.desafio.DetalleDesafioFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Desafio {
    Date date = new Date();
    public int id;
    public String invitadoNombre;
    public String invitadoImage;
    public String retadorNombre;
    public String retadorImage;
    SimpleDateFormat fechaC = new SimpleDateFormat("d,MMMM 'del', yyyy");
    public Date sFecha;// = fechaC.format(date);

    public Desafio(String _invitadoNombre, String _invitadoImage, String _retadorNombre,
                   String _retadorImage, String _fecha) {
        this.invitadoNombre = _invitadoNombre;
        this.invitadoImage = _invitadoImage;
        this.retadorNombre = _retadorNombre;
        this.retadorImage = _retadorImage;
        try {
            this.sFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(_fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            this.sFecha = Calendar.getInstance().getTime();
        }
    }

        public String getFechaFormat(){
        return this.fechaC.format(this.sFecha);
        }

    public static ArrayList getCollection() {
        ArrayList<Desafio> collection = new ArrayList<>();
        collection.add(new Desafio("Anonymous", "",
                "Monkeycoins", "", ""));
        return collection;
    }


    public static void injectDesafioFromCloud(final QueueUtils.QueueObject o,
                                               final Desafio desafio,
                                               final DetalleDesafioFragment _interface) {
        String url = "https://dccd8e368d44.ngrok.io/api/auth/desafios/" + desafio.id;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (response.has("object")) {
                                    try {

                                            JSONObject o = response.getJSONObject("object");
                                            String fecha = o.getString("desafio");
                                            JSONObject invitado = o.getJSONObject("invitado");
                                            JSONObject retador = o.getJSONObject("retador");
                                            //desafio.fe
                                            desafio.invitadoNombre = invitado.getString("nombre");
                                            desafio.invitadoImage = invitado.getString("image");
                                            desafio.retadorNombre = retador.getString("nombre");
                                            desafio.retadorImage = retador.getString("image");

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
    public static void injectDesafiosFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Desafio> desafios,
                                               final ConsultarDesafioFragment _interface) {
        String url = "https://dccd8e368d44.ngrok.io/api/auth/desafios";
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
                                            Desafio xp = new Desafio(
                                                    invitado.getString("nombre"),
                                                    invitado.getString("image"),
                                                    retador.getString("nombre"),
                                                    retador.getString("image"),
                                                    fecha);
                                            xp.id = o.getInt("id");
                                            desafios.add(xp);

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
        String url = "https://dccd8e368d44.ngrok.io/api/auth/desafios?keyword=" + keyword;
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
                                            Desafio xp = new Desafio(
                                                    invitado.getString("nombre"),
                                                    invitado.getString("image"),
                                                    retador.getString("nombre"),
                                                    retador.getString("image"),
                                                    fecha);
                                            xp.id = o.getInt("id");
                                            desafios.add(xp);
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
