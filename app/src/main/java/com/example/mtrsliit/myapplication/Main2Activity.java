package com.example.mtrsliit.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mtrsliit.labexercise1.R;

public class Main2Activity extends AppCompatActivity {

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //set the tetview within the layout for class object
        textView2 = findViewById(R.id.textView2);

        //This set the value of Msg 2 as the text string
        textView2.setText(R.string.Msg2);

        Log.i("LifeCycle","onCreate() Invoked!!!");

    }


}
