package com.example.homework02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Pizza Store");
        TextView toppings_price = (TextView)findViewById(R.id.tv_toppings_price);
        TextView custom_toppings = (TextView)findViewById(R.id.tv_mytoppings);
        TextView delivery_cost = (TextView)findViewById(R.id.tv_delivery_cost);
        TextView total = (TextView)findViewById(R.id.tv_total);
        ArrayList<String> mytoppings = new ArrayList<String>();
        final Button finish =(Button)findViewById(R.id.b_finish);
        Intent i = getIntent();
        int nooftoppings = i.getIntExtra("nooftoppings",0);
        mytoppings= i.getStringArrayListExtra("mytoppings");
        int delivery_flag = i.getIntExtra("delivery_flag",0);

        double nooftopping = nooftoppings * 1.50;
        double delivery,net;
        if (delivery_flag == 1)
            delivery = 4.00;
        else
            delivery = 0.00;
        net = 6.50 + delivery + nooftopping;

        toppings_price.setText(String.valueOf(nooftopping)+" $");
        String str="";
        for(int iq=0; iq< mytoppings.size(); iq++){
            if(!mytoppings.get(iq).isEmpty())
                str += mytoppings.get(iq)+", ";
        }
        if(mytoppings.size()>0)
            custom_toppings.setText(str.substring(0,str.length()-2));
        delivery_cost.setText(String.valueOf(delivery) + " $");
        total.setText(String.valueOf(net) + " $");

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i =new Intent(OrderActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}
