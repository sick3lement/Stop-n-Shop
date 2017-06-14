package com.project.umang.googlesignin;

/**
 * Created by UMANG on 6/11/2017.
 */

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class View_Holder_Cart extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;
    TextView price;
    TextView id;
    TextView quantity;
    Button addition;
    Button subtraction;
    Button remove;

    View_Holder_Cart(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        price= (TextView)itemView.findViewById(R.id.price);
        id= (TextView)itemView.findViewById(R.id.idd);
        quantity = (TextView)itemView.findViewById(R.id.quantity1);
        addition = (Button)itemView.findViewById(R.id.addition);
        subtraction = (Button)itemView.findViewById(R.id.subtraction);
        remove= (Button)itemView.findViewById(R.id.remove);
    }
}