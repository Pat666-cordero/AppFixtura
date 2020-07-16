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

public class Persona {
    public String nombre;
    public String apellido;
    public String foto;

    public Persona(String _nombre, String _apellido, String _foto) {
        this.nombre = _nombre;
        this.apellido = _apellido;
        this.foto = _foto;
    }

    public static ArrayList getCollection() {
        ArrayList<Persona> collection = new ArrayList<>();
        collection.add(new Persona("Jose", "Campos", ""));
        return collection;
    }

    public static void injectJugadoresFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Persona> personas,
                                               int discplinaId,
                                               final DetalleJugadorFragment _interface) {
        String url = "https://e448cdf9014d.ngrok.io/api/auth/disciplinas/" + discplinaId;
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                if (response.has("objects")) {
                                    try {
                                        JSONArray list = response.getJSONArray("objects");
                                        JSONObject a =  (JSONObject)list.get(0);
                                        JSONArray personasx =  a.getJSONArray("personas");
                                    //).getJSONObject("personas")


                                        for(int i = 0; personasx.length() > i; i++) {
                                            JSONObject o = personasx.getJSONObject(i);
                                            //JSONObject persona = o.getJSONObject("persona");
                                            personas.add(new Persona(
                                                    o.getString("nombre"),
                                                    o.getString("apellido"),
                                                    o.getString(("foto"))));
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
