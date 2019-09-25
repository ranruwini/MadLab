package com.example.mtrsliit.siyenra;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity {
    private static final String TAG = "UpdateDeleteActivity";
    private HallBookingModel hallbookingModel;

    private Button btnupdate, btndelete;
    private DatabaseHelper databaseHelper;
    private EditText mDisplayDate1, mDisplayDate2, getTime,hall;

    private DatePickerDialog.OnDateSetListener mDateSetListner1;
    private DatePickerDialog.OnDateSetListener mDateSetListner2;
    private Spinner hallTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        Intent intent = getIntent();
        hallbookingModel = (HallBookingModel) intent.getSerializableExtra("HallType");

        databaseHelper = new DatabaseHelper(this);

        /*etname = (EditText) findViewById(R.id.etname);
        ethobby = (EditText) findViewById(R.id.ethobby);*/
        mDisplayDate1 = (EditText) findViewById(R.id.etdate1);
        mDisplayDate2 = (EditText) findViewById(R.id.etdate2);

        getTime = (EditText) findViewById(R.id.time1);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        hall = (EditText) findViewById(R.id.hall);
        hall.setText(hallbookingModel.getHall());
        mDisplayDate1.setText(hallbookingModel.getCheckindate());
        mDisplayDate2.setText(hallbookingModel.getCheckoutdate());
        getTime.setText(hallbookingModel.getTime());

       /* hallTypes = (Spinner) findViewById(R.id.spinner1);

        Spinner s1 = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.hallTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

*/

        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int year = cal1.get(Calendar.YEAR);
                int month = cal1.get(Calendar.MONTH);
                int day = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateDeleteActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListner1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListner1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet : mm/dd/yyyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate1.setText(date);
            }
        };


        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal2 = Calendar.getInstance();
                int year = cal2.get(Calendar.YEAR);
                int month = cal2.get(Calendar.MONTH);
                int day = cal2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateDeleteActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListner2,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListner2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet : mm/dd/yyyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate2.setText(date);
            }
        };



        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();

               /* databaseHelper.updatebooking(hallbookingModel.getId(),hall.getText().toString(), mDisplayDate1.getText().toString(),mDisplayDate2.getText().toString(),getTime .getText().toString());
                Toast.makeText(UpdateDeleteActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deletebooking(hallbookingModel.getId());
                Toast.makeText(UpdateDeleteActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void update() {
        boolean valid = true;

        String value = hall.getText().toString();
        String date1 = mDisplayDate1.getText().toString();
        String date2 = mDisplayDate2.getText().toString();
        String time = getTime.getText().toString();

        if (date1.isEmpty()) {
            Toast.makeText(UpdateDeleteActivity.this, "Please select a check in date!", Toast.LENGTH_SHORT).show();
            //mDisplayDate1.setError("Please select a check in date");
            valid = false;
        } else if (date2.isEmpty()) {
            Toast.makeText(UpdateDeleteActivity.this, "Please select a check out date!", Toast.LENGTH_SHORT).show();
            //mDisplayDate2.setError("Please select a check out date");
            valid = false;
        } else if (time.isEmpty()) {
            Toast.makeText(UpdateDeleteActivity.this, "Please select a time!", Toast.LENGTH_SHORT).show();
            //getTime.setError("Please select a time");
            valid = false;
        } else if (value.isEmpty()) {
            //err.setText("Please select a Hall Type");
            // err.setTextColor(Color.RED);
            Toast.makeText(UpdateDeleteActivity.this, "Please select a Hall Type!", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else if((!value.equals("Grand Ball Room")) || (!value.equals("Banquet Halls")) || (!value.equals("Eagle Hall"))){
            Toast.makeText(UpdateDeleteActivity.this, "Please select a valid Hall Type!", Toast.LENGTH_SHORT).show();
            valid = false;

        }else {

            databaseHelper.updatebooking(hallbookingModel.getId(), hallTypes.getSelectedItem().toString(), mDisplayDate1.getText().toString(), mDisplayDate2.getText().toString(), getTime.getText().toString());
            Toast.makeText(UpdateDeleteActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
    }


    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
