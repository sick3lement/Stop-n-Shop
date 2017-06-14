package com.project.umang.googlesignin;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class Recycler_View_Adapter_Home extends RecyclerView.Adapter<View_Holder_Home> {
    List<Homedata> list = Collections.emptyList();
    Context context ;
    Recycler_View_Adapter_Home(List list,Context context)
    {
        this.list= list;
        this.context = context;
    }
    @Override
    public View_Holder_Home onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_h, parent, false);
        View_Holder_Home holder = new View_Holder_Home(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder_Home holder, int position) {
        Picasso.with(context).load(list.get(position).image).into(holder.imagehome);
        holder.homebutton.setText(list.get(position).button.toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
