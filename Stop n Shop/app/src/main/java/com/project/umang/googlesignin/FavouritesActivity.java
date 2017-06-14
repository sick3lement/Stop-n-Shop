package com.project.umang.googlesignin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {
    List<Data> data;
    RecyclerView recyclerView;
    Recycler_View_Adapter adapter;
    SwipeRefreshLayout swipe;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        swipe = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetJSONswipe().execute();
            }
        });
        getJSON();
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override

            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FavouritesActivity.this, "Fetching Data", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMen();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GETFAVOURITES);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    class GetJSONswipe extends AsyncTask<Void, Void, String> {

        ProgressDialog loading;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSON_STRING = s;
            showMen();
            swipe.setRefreshing(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            RequestHandler rh = new RequestHandler();
            String s = rh.sendGetRequest(Config.URL_GETFAVOURITES);
            return s;
        }
    }

    private void showMen() {
        JSONObject jsonObject = null;
        String id;
        String title;
        String description;
        String price;
        String image;
        data = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                id = jo.getString("id");
                title = jo.getString("title");
                description = jo.getString("description");
                price = jo.getString("price");
                image = jo.getString("image");
                data.add(new Data(title,description,image,Integer.parseInt(price),Integer.parseInt(id)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Data d = data.get(position);
                Intent intent= new Intent(FavouritesActivity.this,FavouritesRemovalActivity.class);
                intent.putExtra("id",d.getId1());
                intent.putExtra("title",d.getTitle1());
                intent.putExtra("description",d.getDescription());
                intent.putExtra("price",d.getPrice());
                intent.putExtra("image",d.getImageId());
                startActivity(intent);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}

