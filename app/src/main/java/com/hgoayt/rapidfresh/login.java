package com.hgoayt.rapidfresh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {

    private EditText editText_rut;
    private EditText editText_clave;
    private AsyncHttpClient cliente = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_rut = (EditText) findViewById(R.id.login_rut);
        editText_clave = (EditText) findViewById(R.id.login_clave);
    }

    public void iniciarSesion(View view) {
        if (editText_rut.getText().toString().isEmpty() || editText_clave.getText().toString().isEmpty()) {
            toast("Llene todos los campos");
        } else {
            String url = "http://rapidfresh.todojava.net/index.php/administrador/iniciarSesionApp";
            RequestParams params = new RequestParams();
            params.add("rut", editText_rut.getText().toString());
            params.add("clave", editText_clave.getText().toString());
            cliente.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String respuesta = new String(responseBody);
                    try {
                        JSONArray json = new JSONArray(respuesta);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("RUT", json.getJSONObject(0).getString("rut"));
                        intent.putExtra("NOMBRE", json.getJSONObject(0).getString("nombre"));
                        intent.putExtra("APELLIDO", json.getJSONObject(0).getString("apellido"));
                        intent.putExtra("MAIL", json.getJSONObject(0).getString("mail"));
                        intent.putExtra("TELEFONO", json.getJSONObject(0).getString("telefono"));
                        intent.putExtra("FOTO", json.getJSONObject(0).getString("foto"));
                        intent.putExtra("CLAVE", editText_clave.getText().toString());
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        toast("Usuario o Contraseña Incorrecto");
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }

    }

    public void toast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        editText_clave.setText("");
        editText_rut.setText("");

    }
}
