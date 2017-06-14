package com.project.umang.googlesignin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final int STATE_SIGNED_IN = 0;
    private static final int STATE_SIGN_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;
    private EditText editEmail;
    private EditText editName;
    private EditText editPassword;

    private Button buttonAdd;

    private int signInProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            /*   logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleApiClient.disconnect();
                googleApiClient.connect();
            }
        }); */


        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        editEmail = (EditText) findViewById(R.id.email);
        editName = (EditText) findViewById(R.id.name);
        editPassword = (EditText) findViewById(R.id.password);

        //Setting listeners to button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addEmployee();

            }
        });


    }

    private void addEmployee(){


        final String name = editName.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        final String image = "";

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                if(s.equalsIgnoreCase("successfully registered"))
                {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_PASSWORD,password);
                params.put(Config.KEY_EMP_EMAIL,email);
                params.put(Config.KEY_EMP_IMAGE,image);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_SIGN_UP, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }
}
