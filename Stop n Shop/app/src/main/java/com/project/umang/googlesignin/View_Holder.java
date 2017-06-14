package com.project.umang.googlesignin;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.umang.googlesignin.R;

import org.w3c.dom.Text;

/**
 * Created by UMANG on 6/5/2017.
 */

public class View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;
    TextView price;
    TextView id;
    View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        price= (TextView)itemView.findViewById(R.id.price);
        id= (TextView)itemView.findViewById(R.id.idd);
    }
}