package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String [][] doctor_detail1={
            {"Dr Name : ASHISH PAL","Hospital Address :AIIMS DELHI","Exp : 5 YEARS",",","Fees : 5000",},
            {"Dr Name : AJAY SINGH","Hospital Address :AIIMS BHOPAL","Exp : 10 YEARS","","Fees : 5"},
            {"Dr Name : PUJA K.","Hospital Address :KGMU LKO","Exp : 2 YEARS","","Fees : 1"},
            {"Dr Name : ROOPSI MISHRA","Hospital Address :GSVM KANPUR","Exp : 15 YEARS","","Fees : 6000"},
            {"Dr Name : ANOOP KUMAR","Hospital Address :AIIMS RAIPUR","Exp : 6 YEARS","","Fees : 5000"}
    };
    private String [][] doctor_detail2={
            {"Dr Name : ALVIYA ZAIDI","Hospital Address : UNKNWON","Exp : 5 YEARS","","Fees : 5000"},
            {"Dr Name : MILAN SHUKLA","Hospital Address :AIIMS BHOPAL","Exp : 10 YEARS","","Fees : 500"},
            {"Dr Name : DEEPAK PAL","Hospital Address :KGMU LKO","Exp : 2 YEARS","","Fees : 1"},
            {"Dr Name : SACHIN KUMAR","Hospital Address :GSVM KANPUR","Exp : 15 YEARS","","Fees : 6000"},
            {"Dr Name : SATYAM KUMAR","Hospital Address :AIIMS RAIPUR","Exp : 6 YEARS","","Fees : 5000"}
    };
    private String [][] doctor_detail3={
            {"Dr Name : AKASH TRIPATHI","Hospital Address :AIIMS DELHI","Exp : 5 YEARS","","Fees : 5000"},
            {"Dr Name : AVINASH SINGH","Hospital Address :AIIMS BHOPAL","Exp : 10 YEARS","","Fees : 5"},
            {"Dr Name : AMIT YADAV","Hospital Address :KGMU LKO","Exp : 2 YEARS","","Fees : 1"},
            {"Dr Name : SAURABH KUMAR","Hospital Address :GSVM KANPUR","Exp : 15 YEARS","","Fees : 6000"},
            {"Dr Name : ABHISHEK VERMA","Hospital Address :AIIMS RAIPUR","Exp : 6 YEARS","","Fees : 5000"}
    };
    private String [][] doctor_detail4={
            {"Dr Name : SURYANSHU BAGHEL","Hospital Address :AIIMS DELHI","Exp : 5 YEARS","","Fees : 5000"},
            {"Dr Name : AJAY SINGH","Hospital Address :AIIMS BHOPAL","Exp : 10 YEARS","","Fees : 5"},
            {"Dr Name : PUJA K.","Hospital Address :KGMU LKO","Exp : 2 YEARS","","Fees : 1"},
            {"Dr Name : ROOPSI MISHRA","Hospital Address :GSVM KANPUR","Exp : 15 YEARS","","Fees : 6000"},
            {"Dr Name : ANOOP KUMAR","Hospital Address :AIIMS RAIPUR","Exp : 6 YEARS","","Fees : 5000"}
    };
    private String [][] doctor_detail5={
            {"Doctor Name : ASHISH PAL","Hospital Address :AIIMS DELHI","Exp : 5 YEARS","","Fees : 5000"},
            {"Doctor Name : AJAY SINGH","Hospital Address :AIIMS BHOPAL","Exp : 10 YEARS","","Fees : 5"},
            {"Doctor Name : PUJA K.","Hospital Address :KGMU LKO","Exp : 2 YEARS","","Fees : 1"},
            {"Doctor Name : ROOPSI MISHRA","Hospital Address :GSVM KANPUR","Exp : 15 YEARS","","Fees : 6000"},
            {"Doctor Name : ANOOP KUMAR","Hospital Address :AIIMS RAIPUR","Exp : 6 YEARS","","Fees : 5000"}
    };
    TextView tv;
    Button btn;
    String [][] doctor_details={};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String>item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv=findViewById(R.id.titleDoctor);
        btn=findViewById(R.id.buttonDDBack);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family Physician")==0)
            doctor_details=doctor_detail1;
        else if(title.compareTo("Dietitian")==0)
            doctor_details=doctor_detail2;
        else if(title.compareTo("Cardiologist")==0)
            doctor_details=doctor_detail3;
        else if(title.compareTo("Dentist")==0)
            doctor_details=doctor_detail4;
        else if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_detail5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        list=new ArrayList();
        for (int i=0;i<doctor_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5",doctor_details[i][4]+"/-");
            list.add(item);

        }
        sa= new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView listView=findViewById(R.id.ListviewDD);
        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}