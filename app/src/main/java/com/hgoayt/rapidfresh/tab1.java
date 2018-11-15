package com.hgoayt.rapidfresh;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hgoayt.rapidfresh.modelo.Recepcion;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class tab1 extends Fragment {

    private ListView listView;

    public tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        listView = (ListView) view.findViewById(R.id.tab1_list);

        cargarList();

        return view;
    }


    private void cargarList(){
        String url = "http://rapidfresh.todojava.net/index.php/administrador/recepciones";
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    procesar(new String (responseBody));
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void procesar(String respuesta){
        List<Recepcion> lista = new ArrayList<>();
        try{
            JSONArray json = new JSONArray(respuesta);
            for (int i=0; i<json.length();i++){
                Recepcion r = new Recepcion();
                r.setNombre_producto(json.getJSONObject(i).getString("nombre_producto"));
                r.setProductor(json.getJSONObject(i).getString("productor"));
                r.setFecha_recepcion(json.getJSONObject(i).getString("fecha_recepcion"));
                lista.add(r);
            }

            listView.setAdapter(new AdaptadorTab1(getActivity(), lista));

        } catch (Exception e){
            e.printStackTrace();

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        cargarList();
    }


}
