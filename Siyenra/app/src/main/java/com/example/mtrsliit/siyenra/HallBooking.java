package com.example.mtrsliit.siyenra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HallBooking extends AppCompatActivity {
    ImageButton myImagebutton1;
    ImageButton myImagebutton2;
    ImageButton myImagebutton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_booking);

        myImagebutton1 = (ImageButton) findViewById(R.id.imagebtn_1);
        myImagebutton2 = (ImageButton) findViewById(R.id.imagebtn_2);
        myImagebutton3 = (ImageButton) findViewById(R.id.imagebtn_3);

        myImagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hallbooking = new Intent(HallBooking.this,MainActivity.class);
                startActivity(hallbooking);
            }
        });

        myImagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hallbooking = new Intent(HallBooking.this,MainActivity.class);
                startActivity(hallbooking);
            }
        });

        myImagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hallbooking = new Intent(HallBooking.this,MainActivity.class);
                startActivity(hallbooking);
            }
        });
    }


}
