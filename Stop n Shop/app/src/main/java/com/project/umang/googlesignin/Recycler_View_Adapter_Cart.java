package com.project.umang.googlesignin;

/**
 * Created by UMANG on 6/10/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by UMANG on 6/5/2017.
 */

public class Recycler_View_Adapter_Cart extends RecyclerView.Adapter<View_Holder_Cart> {
    List<DataCart> list = Collections.emptyList();
    private String JSON_STRING;

    Context context ;
    Recycler_View_Adapter_Cart(List list,Context context)
    {
        this.list= list;
        this.context = context;
    }
    @Override
    public View_Holder_Cart onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_cart, parent, false);
        View_Holder_Cart holder = new View_Holder_Cart(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final View_Holder_Cart holder, final int position) {
        final DataCart dataCart = list.get(position);
        holder.title.setText(list.get(position).title);
        holder.description.setText(list.get(position).description);
        holder.price.setText(String.valueOf(list.get(position).price));
        holder.id.setText(String.valueOf(list.get(position).id));
        holder.quantity.setText((String.valueOf(list.get(position).quantity)));
        Picasso.with(holder.imageView.getContext()).load(Uri.parse(list.get(position).imageId)).into(holder.imageView);
        holder.addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.quantity.setText(String.valueOf(dataCart.getQuantity()+1));
                dataCart.setQuantity(dataCart.getQuantity()+1);
               getJSON(dataCart.id,(dataCart.getQuantity()));
            }
        });
        holder.subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.quantity.setText(String.valueOf(dataCart.getQuantity()-1));
                dataCart.setQuantity(dataCart.getQuantity()-1);
                getJSON(dataCart.id,(dataCart.getQuantity()));

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               remove(dataCart.getId1());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position


    // Remove a RecyclerView item containing a specified Data object
    private void getJSON(int id ,int quantity) {

        SharedPreferences preferences = this.context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String usermail =  String.valueOf(preferences.getString("email",null));
        final String id1 = String.valueOf(id);
        final  String quantity1 = String.valueOf(quantity);

        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override

            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(context, " Quantity updated : "+quantity1, Toast.LENGTH_SHORT).show();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                JSON_STRING = s;
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("id",id1);
                params.put("count",quantity1);
                params.put("usermail",usermail);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_QUANTITY,params);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void remove(int id)
    {
        SharedPreferences preferences = this.context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String usermail =  String.valueOf(preferences.getString("email",null));
        final int id2 = id;

    class AddEmployee extends AsyncTask<Void,Void,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(context, "Removing..", Toast.LENGTH_SHORT).show();


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context, "Item removed !\nPlease refresh the list", Toast.LENGTH_LONG).show();

        }
        @Override
        protected String doInBackground(Void... v) {
            HashMap<String,String> params = new HashMap<>();
            //Adding parameters to request
            params.put("id",String.valueOf(id2));
            params.put("email",usermail);

            RequestHandler rh = new RequestHandler();
            String res = rh.sendPostRequest(Config.REMOVECART, params);
            return res;

        }
    }

    AddEmployee ae = new AddEmployee();
                ae.execute();

}

}
