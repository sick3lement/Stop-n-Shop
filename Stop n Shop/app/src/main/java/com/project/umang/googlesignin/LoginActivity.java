package com.project.umang.googlesignin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
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


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    //Defining views
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private AppCompatButton buttonLogin;

    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (AppCompatButton) findViewById(R.id.buttonLogin);

        //Adding click listener
        buttonLogin.setOnClickListener(this);
        TextView text = (TextView)findViewById(R.id.login);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });


        signInButton = (SignInButton)findViewById(R.id.signin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!googleApiClient.isConnecting())
                {
                    Toast.makeText(LoginActivity.this, "signing in", Toast.LENGTH_SHORT).show();
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(signInIntent, 4);

                }

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(Scopes.PROFILE))
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }
    @Override
    public void onConnectionSuspended(int i) {
    }
    @Override
    protected void onStart(){
        super.onStart();
        googleApiClient.connect();
    }
    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 4) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            addEmployee(acct.getDisplayName(),acct.getEmail(),acct.getPhotoUrl().toString());

        } else {
            // Signed out, show unauthenticated UI.

        }
    }

    private void addEmployee(String name , String email ,String image){

        final String name1 = name;
        final String password1 = "";
        final String email1 = email;
        final String image1 = image;
        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this,"Signing In...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                //Creating editor to store values to shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //Adding values to editor
                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                editor.putString(Config.EMAIL_SHARED_PREF, email1);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name1);
                params.put(Config.KEY_EMP_PASSWORD,password1);
                params.put(Config.KEY_EMP_EMAIL,email1);
                params.put(Config.KEY_EMP_IMAGE,image1);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_GOOGLE, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,null);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void login(){
        //Getting values from edit texts
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this,"Logging in...","Wait...",false,false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG).show();
                if(s.equalsIgnoreCase("success")){
                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Adding values to editor
                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(Config.EMAIL_SHARED_PREF, email);

                    //Saving values to editor
                    editor.commit();

                    //Starting profile activity

                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_EMAIL, email);
                params.put(Config.KEY_PASSWORD, password);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.LOGIN_URL, params);
                return res;

            }

        }
        AddEmployee ae = new AddEmployee();
        ae.execute();

    }

    @Override
    public void onClick(View v) {
        //Calling the login function
        login();
    }
}
