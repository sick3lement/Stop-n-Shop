package com.project.umang.googlesignin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class FavouritesRemovalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_removal);

        ImageView imageView = (ImageView)findViewById(R.id.imageView1);
        TextView title = (TextView)findViewById(R.id.title1);
        TextView description = (TextView)findViewById(R.id.description1);
        TextView price= (TextView)findViewById(R.id.price1);

        Picasso.with(this).load(getIntent().getStringExtra("image")).into(imageView);
        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
        price.setText(String.valueOf(getIntent().getIntExtra(("price"),0)));

       AppCompatButton cart = (AppCompatButton)findViewById(R.id.removefavourites);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int id = getIntent().getIntExtra("id",0);

                class AddEmployee extends AsyncTask<Void,Void,String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(FavouritesRemovalActivity.this,"Removing...","Wait...",false,false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(FavouritesRemovalActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String,String> params = new HashMap<>();
                        //Adding parameters to request
                        params.put("id",String.valueOf(id));

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(Config.REMOVEFAVOURITES, params);
                        return res;

                    }
                }

                AddEmployee ae = new AddEmployee();
                ae.execute();
                startActivity(new Intent(FavouritesRemovalActivity.this,FavouritesActivity.class));
            }
        });


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, FavouritesActivity.class));
        finish();

    }
}
