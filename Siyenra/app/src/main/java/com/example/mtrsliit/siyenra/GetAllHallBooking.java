package com.example.mtrsliit.siyenra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class GetAllHallBooking extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HallBookingModel> hallBookingModelArrayList;
    private CustomAdapter customAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_hall_booking);

        listView = (ListView) findViewById(R.id.lv);

        databaseHelper = new DatabaseHelper(this);

        hallBookingModelArrayList = databaseHelper.getAllhallooking();

        customAdapter = new CustomAdapter(this,hallBookingModelArrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GetAllHallBooking.this,UpdateDeleteActivity.class);
                intent.putExtra("HallType",hallBookingModelArrayList.get(position));
                startActivity(intent);
            }
        });
    }
}
