package com.example.healthcare;

import static android.icu.util.Calendar.getInstance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView total;
    ListView lst;
    TextView tvtotalcost;
    private DatePickerDialog datePicker;

    private Button btndate,btntime,btncheckout,btnBack;
    private String[][] packages={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);


        btnBack=findViewById(R.id.buttonCBMBack);
        btncheckout=findViewById(R.id.buttonCBMcheck);
        btndate=findViewById(R.id.buttonCBMDate);
        tvtotalcost=findViewById(R.id.textViewCBMprice);
        lst=findViewById(R.id.listViewCBMCart);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        Database db=new Database(getApplicationContext(),"Healthcare",null,1);
        float totalamount=0;
        ArrayList dbdata=db.getCardData(username,"medicine");

        Toast.makeText(getApplicationContext(),""+dbdata,Toast.LENGTH_LONG).show();


        packages=new String[dbdata.size()][];
        for(int i=0;i<packages.length;i++)
        {
            packages[i]=new String[5];
        }

        for(int i=0;i<dbdata.size();i++)
        {
            String arrdata=dbdata.get(i).toString();
            String[] strdata=arrdata.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strdata[0];
            packages[i][4]="cost :"+strdata[1]+"/-";
            totalamount=totalamount+Float.parseFloat(strdata[1]);

        }

        tvtotalcost.setText("Total cost :"+totalamount);

        list=new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);

        }

        sa= new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineActivity.class));
            }
        });

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CartBuyMedicineActivity.this,BuyMedicineBookActivity.class);
                it.putExtra("price",tvtotalcost.getText());
                it.putExtra("date",btndate.getText());
                startActivity(it);
            }
        });



        initDatepicker();
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
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

}