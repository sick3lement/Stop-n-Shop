package com.project.umang.googlesignin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    int endprice = 0;
    int number = 0;
    List<DataCart> data;
    RecyclerView recyclerView;
    Recycler_View_Adapter_Cart adapter;
    private String JSON_STRING;
    SwipeRefreshLayout swipe;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        swipe = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getJSON();
            }
        });

        getJSON();
        AppCompatButton button = (AppCompatButton)findViewById(R.id.checkout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, AddressActivity.class);
                for(i=0;i<data.size();i++){
                endprice = endprice + data.get(i).getQuantity()*data.get(i).getPrice();
                    number = number + data.get(i).getQuantity();
                }
                intent.putExtra("price",endprice);
                intent.putExtra("number",number);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getJSON() {

        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String usermail =  String.valueOf(preferences.getString("email",null));
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override

            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(CartActivity.this, "Fetching Data", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                swipe.setRefreshing(false);
                JSON_STRING = s;
                showMen();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("usermail",usermail);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_GETCART,params);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showMen() {
        JSONObject jsonObject = null;
        String id;
        String title;
        String description;
        String price;
        String image;
        String count;
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
                count = jo.getString("count");
                data.add(new DataCart(title,description,image,Integer.parseInt(price),Integer.parseInt(id),Integer.parseInt(count)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new Recycler_View_Adapter_Cart(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
      /*  recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this, recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                DataCart d = data.get(position);
                Intent intent= new Intent(CartActivity.this,RemovalActivity.class);
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
        })); */

    }
}

