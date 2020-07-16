package com.example.fixtura.models;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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
import java.util.HashMap;
import java.util.Map;

public class Desafio {
    Date date = new Date();
    public int id;
    public String invitadoNombre;
    public String invitadoImage;
    public String retadorNombre;
    public String retadorImage;
    public int invitadoPunto = 0;
    public int retadorPunto = 0;
    SimpleDateFormat fechaC = new SimpleDateFormat("d,MMMM 'del', yyyy");
    public Date sFecha;// = fechaC.format(date);

    public Desafio(String _invitadoNombre, String _invitadoImage, String _retadorNombre,
                   String _retadorImage, String _fecha) {
        this.invitadoNombre = _invitadoNombre;
        this.invitadoImage = _invitadoImage;
        this.retadorNombre = _retadorNombre;
        this.retadorImage = _retadorImage;
        this.invitadoPunto = 0;
        this.retadorPunto = 0;
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

    public static void updateDesafioFromCloud(final QueueUtils.QueueObject o,
                                              final Desafio desafio,
                                              final String tipo,
                                              final DetalleDesafioFragment _interface) {
        String url = "https://e448cdf9014d.ngrok.io/api/auth/desafios/" + desafio.id + "/addpuntos";
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Do it with this it will work
                            JSONObject _response = new JSONObject(response);
                            if (_response.has("object")) {
                                responseToObject(desafio, _response);
                                _interface.refreshList(); // Esta función debemos implementarla
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            int a = 1;
                            a++;
                        } else if (error instanceof AuthFailureError) {
                            //TODO
                            int a = 1;
                            a++;
                        } else if (error instanceof ServerError) {
                            //TODO
                            int a = 1;
                            a++;
                        } else if (error instanceof NetworkError) {
                            //TODO
                            int a = 1;
                            a++;
                        } else if (error instanceof ParseError) {
                            //TODO
                            int a = 1;
                            a++;
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("tipo",tipo);
                params.put("invitado_puntaje", desafio.invitadoPunto + "");
                params.put("retador_puntaje", desafio.retadorPunto + "");

                return params;
            }
        };
        o.addToRequestQueue(stringRequest);
    }

    public static void responseToObject(final Desafio desafio,JSONObject response ) {
        try {

            JSONObject o = response.getJSONObject("object");
            String fecha = o.getString("desafio");
            int retadorPunto = o.getInt("retador_puntaje");
            int invitadoPunto = o.getInt("invitado_puntaje");

            JSONObject invitado = o.getJSONObject("invitado");
            JSONObject retador = o.getJSONObject("retador");
            //desafio.fe
            desafio.invitadoPunto = invitadoPunto;
            desafio.retadorPunto = retadorPunto;
            desafio.invitadoNombre = invitado.getString("nombre");
            desafio.invitadoImage = invitado.getString("image");
            desafio.retadorNombre = retador.getString("nombre");
            desafio.retadorImage = retador.getString("image");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void injectDesafioFromCloud(final QueueUtils.QueueObject o,
                                               final Desafio desafio,
                                               final DetalleDesafioFragment _interface) {
        String url = "https://e448cdf9014d.ngrok.io/api/auth/desafios/" + desafio.id;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (response.has("object")) {
                                    responseToObject(desafio, response);
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
        String url = "https://e448cdf9014d.ngrok.io/api/auth/desafios";
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
        String url = "https://e448cdf9014d.ngrok.io/api/auth/desafios?keyword=" + keyword;
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
