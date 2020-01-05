package com.example.homework02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    int nooftoppings = 0;
    int delivery_flag=0;
    ArrayList<String> mytoppings = new ArrayList<String>();
    ArrayList<String> temp = new ArrayList<String>();
    CheckBox delivery;
    ProgressBar topping_bar;
    LinearLayout row1;
    LinearLayout row2;
    Button add;
    Button clear;
    Button checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pizza Store");
        delivery = (CheckBox)findViewById(R.id.cb_delivery);
        topping_bar = (ProgressBar)findViewById(R.id.pb_topping);
        row1 = (LinearLayout)findViewById(R.id.linear1);
        row2 = (LinearLayout)findViewById(R.id.linear2);
        add = (Button)findViewById(R.id.b_addtopping);
        clear = (Button)findViewById(R.id.b_clearpizza);
        checkout = (Button)findViewById(R.id.b_checkout);

        final String [] toppings = {"Bacon","Cheese","Garlic","Green Pepper","Mushroom","Olive","Onion","Red Pepper"};


        if(getIntent()!=null){
            row1.removeAllViews();
            row2.removeAllViews();
            nooftoppings=0;
            topping_bar.setProgress(nooftoppings);
            mytoppings.clear();
            temp.clear();
            delivery.setChecked(false);
        }
        topping_bar.setMax(10);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder build1 = new AlertDialog.Builder(MainActivity.this);
                build1.setTitle("Choose a Topping");
                build1.setCancelable(true);

                build1.setItems(toppings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(nooftoppings == 10){
                            Toast.makeText(MainActivity.this, "Toppings Limit Reached!!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            final ImageView topping_img = new ImageView(MainActivity.this);
                            add_topping(toppings[i],topping_img);


                        }
                    }
                });
                build1.show();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1.removeAllViews();
                row2.removeAllViews();
                nooftoppings=0;
                topping_bar.setProgress(nooftoppings);
                mytoppings.clear();
                temp.clear();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(delivery.isChecked())
                    delivery_flag=1;
                else
                    delivery_flag=0;
                Intent i = new Intent(MainActivity.this,OrderActivity.class);
                i.putExtra("nooftoppings",nooftoppings);
                i.putExtra("mytoppings",mytoppings);
                i.putExtra("delivery_flag",delivery_flag);
                startActivity(i);
                finish();
            }
        });

    }

    protected void add_topping(String a, final ImageView topping_img){


            mytoppings.add(a);
            nooftoppings += 1;
            topping_img.setTag(nooftoppings-1);
            switch(a){
                case "Bacon":
                    topping_img.setImageDrawable(getDrawable(R.drawable.bacon));
                    break;
                case "Cheese":
                    topping_img.setImageDrawable(getDrawable(R.drawable.cheese));
                    break;
                case "Garlic":
                    topping_img.setImageDrawable(getDrawable(R.drawable.garlic));
                    break;
                case "Green Pepper":
                    topping_img.setImageDrawable(getDrawable(R.drawable.green_pepper));
                    break;
                case "Mushroom":
                    topping_img.setImageDrawable(getDrawable(R.drawable.mashroom));
                    break;
                case "Olive":
                    topping_img.setImageDrawable(getDrawable(R.drawable.olive));
                    break;
                case "Onion":
                    topping_img.setImageDrawable(getDrawable(R.drawable.onion));
                    break;
                case "Red Pepper":
                    topping_img.setImageDrawable(getDrawable(R.drawable.red_pepper));
                    break;

            }
            topping_bar.setProgress(nooftoppings);
            if(nooftoppings<6)
                row1.addView(topping_img);
            else
                row2.addView(topping_img);

        topping_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nooftoppings = 0;
                topping_bar.setProgress(nooftoppings);
                topping_img.getTag();
                String img_tag = String.valueOf(topping_img.getTag());
                int index = Integer.parseInt(img_tag);
                topping_img.setImageResource(0);
                mytoppings.set(index,"");
                row1.removeAllViews();
                row2.removeAllViews();
                temp.clear();
                for(int i=0;i<mytoppings.size();i++){
                    if(!mytoppings.get(i).isEmpty()){
                        temp.add(mytoppings.get(i));
                    }
                }
                mytoppings.clear();
                for(int i=0;i<temp.size();i++){
                    ImageView topping_img = new ImageView(MainActivity.this);
                    topping_img.setTag(null);
                    add_topping(temp.get(i),topping_img);
                }


            }
        });

    }

}
