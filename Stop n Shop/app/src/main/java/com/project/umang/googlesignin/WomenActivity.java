package com.project.umang.googlesignin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WomenActivity extends AppCompatActivity {
    List<Data> data;
    RecyclerView recyclerView;
    Recycler_View_Adapter adapter;
    private String JSON_STRING;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women);
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
                loading = ProgressDialog.show(WomenActivity.this, "Fetching Data", "Wait...", false, false);
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
                String s = rh.sendGetRequest(Config.URL_GETWOMEN);
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
                String s = rh.sendGetRequest(Config.URL_GETWOMEN);
                return s;
            }
        }


    private void showMen() {
        JSONObject jsonObject = null;
        data = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString("id");
                String title = jo.getString("title");
                String description = jo.getString("description");
                String price = jo.getString("price");
                String image = jo.getString("image");
                data.add(new Data(title,description,image,Integer.parseInt(price),Integer.parseInt(id)));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Data d = data.get(position);
                Intent intent= new Intent(WomenActivity.this,Details.class);
                intent.putExtra("id",d.getId1());
                intent.putExtra("title",d.getTitle1());
                intent.putExtra("description",d.getDescription());
                intent.putExtra("price",d.getPrice());
                intent.putExtra("image",d.getImageId());
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.men, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.carttool) {
            startActivity(new Intent(this,CartActivity.class));
            return true;
        }
        else if (id == R.id.favouritestool) {
            startActivity(new Intent(this,FavouritesActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

