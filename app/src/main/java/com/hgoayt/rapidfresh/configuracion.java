package com.hgoayt.rapidfresh;

import android.content.Intent;
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

public class configuracion extends AppCompatActivity {

    private EditText editText_correo;
    private EditText editText_telefono;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private AsyncHttpClient cliente = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        toolbar = (Toolbar) findViewById(R.id.conf_bar);
        editText_correo = (EditText) findViewById(R.id.conf_editMail);
        editText_telefono = (EditText) findViewById(R.id.conf_editPhone);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setTitle("RapiFresh App");
        actionBar.setSubtitle("Editar mis datos");

        editText_correo.setText(getIntent().getStringExtra("MAIL"));
        editText_telefono.setText(getIntent().getStringExtra("TELEFONO"));
    }

    public void savePerfil(View view) {


        if (editText_telefono.getText().toString().isEmpty() || editText_correo.getText().toString().isEmpty()) {
            toast("Llene todos los campos");
        } else {
            String url = "http://rapidfresh.todojava.net/index.php/administrador/actualizarUsuario";
            RequestParams params = new RequestParams();
            String mail = editText_correo.getText().toString();
            String telefono = editText_telefono.getText().toString();
            String rut = getIntent().getStringExtra("RUT");
            params.add("mail", mail);
            params.add("telefono", telefono);
            params.add("rut", rut);

            cliente.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    String respuesta = new String(responseBody);
                    try {
                        JSONArray json = new JSONArray(respuesta);
                        String result = json.getJSONObject(0).getString("result");
                        reload();
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
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public void reload() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("NOMBRE", getIntent().getStringExtra("NOMBRE"));
        intent.putExtra("APELLIDO", getIntent().getStringExtra("APELLIDO"));
        intent.putExtra("RUT", getIntent().getStringExtra("RUT"));
        intent.putExtra("CLAVE", getIntent().getStringExtra("CLAVE"));
        intent.putExtra("FOTO", getIntent().getStringExtra("FOTO"));
        intent.putExtra("MAIL", editText_correo.getText().toString());
        intent.putExtra("TELEFONO", editText_telefono.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
