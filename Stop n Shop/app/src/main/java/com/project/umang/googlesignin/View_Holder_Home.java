package com.project.umang.googlesignin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
/**
 * Created by UMANG on 6/5/2017.
 */

public class View_Holder_Home extends RecyclerView.ViewHolder {

    ImageView imagehome;
    Button homebutton;
    View_Holder_Home(View itemView) {
        super(itemView);
        imagehome = (ImageView)itemView.findViewById(R.id.imagehome);
        homebutton = (Button)itemView.findViewById(R.id.homebutton);

    }
}