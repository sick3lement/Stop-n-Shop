package com.project.umang.googlesignin;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddressActivity extends AppCompatActivity {
    TextInputLayout ed1;
    TextInputLayout ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        TextView item = (TextView)findViewById(R.id.items);
        TextView price = (TextView)findViewById(R.id.pricefinal);

          ed1 = (TextInputLayout) findViewById(R.id.add1);
          ed2 = (TextInputLayout) findViewById(R.id.pincode1);

        item.setText("Items : "+String.valueOf(getIntent().getIntExtra("number",0)));
        price.setText("Total Price : Rs. "+String.valueOf(getIntent().getIntExtra("price",0)));

        AppCompatButton order = (AppCompatButton) findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((ed1.getEditText().getText().toString().equalsIgnoreCase(""))|| (ed2.getEditText().getText().toString().equalsIgnoreCase("")))
                {
                    Toast.makeText(AddressActivity.this, "Enter all values first !", Toast.LENGTH_SHORT).show();
                }
                else{
                Toast.makeText(AddressActivity.this, "Your order will be delivered at "+ed1.getEditText().getText().toString()+" : "+ed2.getEditText().getText().toString() + "\nThankyou for shopping !" , Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddressActivity.this,HomeActivity.class));
                finish();
            }}
        });

    }
}
