package com.hgoayt.rapidfresh;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hgoayt.rapidfresh.modelo.Contacto;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class tab2 extends Fragment {

    private ListView listView;
    private FloatingActionButton button;

    public tab2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        listView = (ListView) view.findViewById(R.id.tab2_list);
        button = (FloatingActionButton) view.findViewById(R.id.tab2_add);

        cargarList();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), nuevoContacto.class);
                intent.putExtra("RUT",getActivity().getIntent().getStringExtra("RUT"));
                startActivity(intent);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contacto contacto = (Contacto) listView.getAdapter().getItem(i);
                Intent intent = new Intent(getActivity(), editarContacto.class);
                intent.putExtra("ID_CONTACTO",contacto.getId_usuario());
                intent.putExtra("RUT_CONTACTO",contacto.getRut_usuario());
                intent.putExtra("NOMBRE_CONTACTO",contacto.getNombre_contacto());
                intent.putExtra("TELEFONO_CONTACTO",contacto.getTelefono());
                startActivity(intent);
            }
        });

        return view;
    }



    private void cargarList(){
        String url = "http://rapidfresh.todojava.net/index.php/administrador/contactos";
        AsyncHttpClient cliente = new AsyncHttpClient();
        String rut = getActivity().getIntent().getStringExtra("RUT");
        RequestParams params = new RequestParams();
        params.add("rut", rut);
        cliente.post(url, params, new AsyncHttpResponseHandler() {
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
        List<Contacto> lista = new ArrayList<>();
        try{
            JSONArray json = new JSONArray(respuesta);
            for (int i=0; i<json.length();i++){
                Contacto c = new Contacto();
                c.setId_usuario(json.getJSONObject(i).getString("id_contacto"));
                c.setNombre_contacto(json.getJSONObject(i).getString("nombre_contacto"));
                c.setTelefono(json.getJSONObject(i).getString("telefono"));
                c.setRut_usuario(json.getJSONObject(i).getString("rut_usuario"));
                lista.add(c);
            }

            listView.setAdapter(new AdaptadorTab2(getActivity(), lista));

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
