package com.example.mtrsliit.siyenra;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,TimePickerDialog.OnTimeSetListener{

    private static final String TAG = "MainActivity";

    private Button  btnAdd, btnSelect;
    //private EditText etname, ethobby;
    private DatabaseHelper databaseHelper;
    private TextView hall,getTime;
    private TextView mDisplayDate1;
    private TextView mDisplayDate2;
    private DatePickerDialog.OnDateSetListener mDateSetListner1;
    private DatePickerDialog.OnDateSetListener mDateSetListner2;
    private TextView err;
    private Spinner hallTypes;

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);


        Spinner s1 = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.hallTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        /*
        s1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        showToast("Spinner1: position=" + position + " id=" + id);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        showToast("Spinner1: unselected");
                    }
                });*/

        //for validation
        //initializeWidgets();

        btnAdd = (Button) findViewById(R.id.btnstore);
        btnSelect = (Button) findViewById(R.id.btnget);

        mDisplayDate1 = (TextView) findViewById(R.id.checkinDate);
        mDisplayDate2 = (TextView) findViewById(R.id.checkoutDate);

        getTime = (TextView) findViewById(R.id.time);
        hallTypes = (Spinner) findViewById(R.id.spinner);

        hall = (TextView) findViewById(R.id.txt1);

        getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        mDisplayDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int year = cal1.get(Calendar.YEAR);
                int month = cal1.get(Calendar.MONTH);
                int day = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
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
                        MainActivity.this,
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


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();

                /*String value = hallTypes.getSelectedItem().toString();

                if (value.equals("Choose...")) {
                    err.setText("Please select a Hall Type");
                    err.setTextColor(Color.RED);

                    Toast.makeText(MainActivity.this, "Please select a Hall Type!", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.addBookingDetail(hallTypes.getSelectedItem().toString(), mDisplayDate1.getText().toString(), mDisplayDate2.getText().toString(), getTime.getText().toString());
                    //hallTypes.setOnItemSelectedListener(this);
                    hallTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        public void onItemSelected(AdapterView<?> adapterView,
                                                   View view, int i, long l) {
                            // TODO Auto-generated method stub
                            Toast.makeText(MainActivity.this, "You Selected : "
                                    + hallTypes.getSelectedItem() + " Level ", Toast.LENGTH_SHORT).show();

                        }

                        // If no option selected
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub

                        }

                    });
                    mDisplayDate1.setText("");
                    mDisplayDate2.setText("");
                    getTime.setText("");

                    Toast.makeText(MainActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, GetAllHallBooking.class);
                startActivity(intent);
            }
        });
    }

    private void validate(){
        boolean valid = true;


        String value = hallTypes.getSelectedItem().toString();
        String date1=mDisplayDate1.getText().toString();
        String date2=mDisplayDate2.getText().toString();
        String time = getTime.getText().toString();


        if(value.equals("Choose...")) {
           // err.setText("Please select a Hall Type");
            //err.setTextColor(Color.RED);

           Toast.makeText(MainActivity.this,"Please select a Hall Type!", Toast.LENGTH_SHORT).show();

            valid = false;
        }
        else if(date1.isEmpty()){
            Toast.makeText(MainActivity.this,"Please select a check in date", Toast.LENGTH_SHORT).show();
            //mDisplayDate1.setError("Please select a check in date");
            valid= false;
        }else if(date2.isEmpty()){
            Toast.makeText(MainActivity.this,"Please select a check out date", Toast.LENGTH_SHORT).show();
            //mDisplayDate2.setError("Please select a check out date");
            valid= false;
        }else if(time.isEmpty()){
            Toast.makeText(MainActivity.this,"Please select a time", Toast.LENGTH_SHORT).show();
            //getTime.setError("Please select a time");
            valid= false;
        }
           else {
            databaseHelper.addBookingDetail(hallTypes.getSelectedItem().toString(), mDisplayDate1.getText().toString(), mDisplayDate2.getText().toString(), getTime.getText().toString());
            //hallTypes.setOnItemSelectedListener(this);
            hallTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView<?> adapterView,
                                           View view, int i, long l) {
                    // TODO Auto-generated method stub
                    Toast.makeText(MainActivity.this, "You Selected : "
                            + hallTypes.getSelectedItem() + " Level ", Toast.LENGTH_SHORT).show();

                }

                // If no option selected
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }

            });
            mDisplayDate1.setText("");
            mDisplayDate2.setText("");
            getTime.setText("");

            Toast.makeText(MainActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        TextView textView = (TextView)findViewById(R.id.time);
        textView.setText("Hour : "+ i + "  Minute : " + i1);
    }


}