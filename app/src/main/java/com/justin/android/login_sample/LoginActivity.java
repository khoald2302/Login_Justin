package com.justin.android.login_sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText inputName;
    private EditText inputPassword;
    private Button btnLogin;
    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        inputName = (EditText) findViewById(R.id.name);
        inputPassword = (EditText) findViewById(R.id.password);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        Log.e(TAG, "Checklgin: " + session.isLoggedIn());
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

//        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (!name.isEmpty() && !password.isEmpty()) {
                    checkLogin(name, password);

                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(LoginActivity.this,
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

            }


        });


    }

    private void checkLogin(final String Name, final String Pass) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean check = jObj.getBoolean("success");

                    // Check for error node in json
                    if (check) {
                        // user successfully logged in
                        // Create login session

                        String token = jObj.getString("token");
                        Log.e(TAG, "token_login"+token );
                        session.setLogin(true);

                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("token",token);
                        intent.putExtra("Login", bundle);


                        startActivity(intent);
                        finish();


                    } else {

                        Toast.makeText(getApplicationContext(),
                                "Login fail", Toast.LENGTH_LONG).show();
                        inputName.setText("");
                        inputPassword.setText("");
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", Name);
                params.put("pw", Pass);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
