package com.example.healthcare;

import static android.icu.util.Calendar.getInstance;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class BookAppointmentActivity extends AppCompatActivity {

    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private Button btndate,btntime,btnBook,btnBack;
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        tv=findViewById(R.id.textViewAppTitle);
        ed1=findViewById(R.id.editTextfullname);
        ed2=findViewById(R.id.editTextRegadd);
        ed3=findViewById(R.id.editTextRegcon);
        ed4=findViewById(R.id.editTextappFees);
        btndate=findViewById(R.id.buttonAppdate);
        btntime=findViewById(R.id.buttonApptime);
        btnBook=findViewById(R.id.buttonBook);
        btnBack=findViewById(R.id.btnBack);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Fees :"+fees+"/-");

        initDatepicker();
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });
        intiTimepicker();
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });

                btnBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        String username=sharedPreferences.getString("username","").toString();

                        Database db=new Database(getApplicationContext(),"Healthcare",null,1);
                        if(db.checkAppointmentExit(username,ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString(),btndate.toString(),btntime.toString())==1)
                        {
                            Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_SHORT).show();
                        }
                        db.addOrder(username,ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString(),0,btndate.toString(),btntime.toString(),Float.parseFloat(ed4.toString()),"lab");
                        Toast.makeText(getApplicationContext(), "your booking is done successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
                    }
                });
    }
    private  void initDatepicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                year=year+1;
                btndate.setText(dayOfMonth+"/"+month+"/"+year);

            }
        };
      Calendar cal= null;
        cal = getInstance();

            int year=cal.get(Calendar.YEAR);


            int month=cal.get(Calendar.YEAR);


            int day=cal.get(Calendar.YEAR);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePicker=new DatePickerDialog(this,style,dateSetListener,year,month,day);

            datePicker.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }
    private void intiTimepicker()
    {
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                 btntime.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal= null;
        cal = getInstance();

        int hrs=cal.get(Calendar.HOUR);


        int min=cal.get(Calendar.MINUTE);


        int style= AlertDialog.THEME_HOLO_DARK;
        timePicker=new TimePickerDialog(this,style,timeSetListener,hrs,min,true);

    }
}