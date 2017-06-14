package com.project.umang.googlesignin;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.umang.googlesignin.Data;
import com.project.umang.googlesignin.R;
import com.project.umang.googlesignin.View_Holder;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by UMANG on 6/5/2017.
 */

public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {
    List<Data> list = Collections.emptyList();
    Context context ;
    Recycler_View_Adapter(List list,Context context)
    {
        this.list= list;
        this.context = context;
    }
    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        holder.title.setText(list.get(position).title);
        holder.description.setText(list.get(position).description);
        holder.price.setText(String.valueOf(list.get(position).price));
        holder.id.setText(String.valueOf(list.get(position).id));
        Picasso.with(holder.imageView.getContext()).load(Uri.parse(list.get(position).imageId)).into(holder.imageView);
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
    public void insert(int position, Data data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }




}
