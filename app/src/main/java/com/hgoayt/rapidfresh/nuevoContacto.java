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

public class nuevoContacto extends AppCompatActivity {

    private EditText editText_nombre;
    private EditText editText_telefono;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private AsyncHttpClient cliente = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        toolbar = (Toolbar) findViewById(R.id.addContacto_bar);
        editText_nombre = (EditText) findViewById(R.id.addContacto_editName);
        editText_telefono = (EditText) findViewById(R.id.addContacto_editPhone);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        actionBar.setTitle("RapiFresh App");
        actionBar.setSubtitle("Nuevo Contacto");
    }

    public void addContacto(View view) {

        if (editText_telefono.getText().toString().isEmpty() || editText_nombre.getText().toString().isEmpty()) {
            toast("Llene todos los campos");
        } else {
            String url = "http://rapidfresh.todojava.net/index.php/administrador/insertarContacto";
            final RequestParams params = new RequestParams();
            params.add("id_usuario", null);
            params.add("nombre", editText_nombre.getText().toString());
            params.add("telefono", editText_telefono.getText().toString());
            params.add("rut", getIntent().getStringExtra("RUT"));

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

    public void toast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
