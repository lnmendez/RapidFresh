package com.hgoayt.rapidfresh;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class editarContacto extends AppCompatActivity {

    private EditText editText_nombre;
    private EditText editText_telefono;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private AsyncHttpClient cliente = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        toolbar = (Toolbar) findViewById(R.id.editContacto_bar);
        editText_nombre = (EditText) findViewById(R.id.editContacto_editName);
        editText_telefono = (EditText) findViewById(R.id.editContacto_editPhone);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setTitle("RapiFresh App");
        actionBar.setSubtitle("Editar Contacto");

        editText_nombre.setText(getIntent().getStringExtra("NOMBRE_CONTACTO"));
        editText_telefono.setText(getIntent().getStringExtra("TELEFONO_CONTACTO"));

    }

    public void editContacto(View view) {
        if (editText_telefono.getText().toString().isEmpty() || editText_nombre.getText().toString().isEmpty()) {
            toast("Llene todos los campos");
        } else {
            String url = "http://rapidfresh.todojava.net/index.php/administrador/actualizarContacto";
            RequestParams params = new RequestParams();
            params.add("id", getIntent().getStringExtra("ID_CONTACTO"));
            params.add("telefono", editText_telefono.getText().toString());

            cliente.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String respuesta = new String(responseBody);
                    try {
                        JSONArray json = new JSONArray(respuesta);
                        String result = json.getJSONObject(0).getString("result");
                        finish();
                        toast(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        toast("Error de Autenticacion");
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    toast("Error de Conexion");
                }
            });
        }
    }

    public void deleteContacto(View view) {
        String url = "http://rapidfresh.todojava.net/index.php/administrador/eliminarContacto";
        RequestParams params = new RequestParams();
        params.add("id", getIntent().getStringExtra("ID_CONTACTO"));
        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta = new String(responseBody);
                try {
                    JSONArray json = new JSONArray(respuesta);
                    String result = json.getJSONObject(0).getString("result");
                    finish();
                    toast(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                    toast("Error de Autenticacion");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                toast("Error de Conexion");
            }
        });

    }

    public void toast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

