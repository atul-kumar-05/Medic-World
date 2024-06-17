package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages={
            {"package 1: Full Body checkup","","","","999"},
            {"package 2: Immunity checkup","","","","499"},
            {"package 3: sugar checkup","","","","699"},
            {"package 4: Covid 19 checkup","","","","799"},
            {"package 5: Thyroid checkup","","","","599"}
    };
    public String[] package_details= {
            "blood glucose fasting\n " +
                    " complete hemogram\n " +
                    " HbA1c\n" +
                    " Iron studies\n" +
                    " kidney function test\n" +
                    " LDH lactate Dehydrogenase, serum\n" +
                    " lipid profile\n" +
                    " liver function",
            " blood glucose fasting",
            " covid 19 antibodies- IgG",
            " Thyroid profile total (T3,T4,TSH ultra sensitive)",
            " complete hemogram\n" +
                    "CRP quantitative , serum\n" +
                    "iron test\n" +
                    "kidney function test\n" +
                    "vitamin D total-25 hydroxy\n" +
                    "Liver function test\n" +
                    "lipid profile"
    };

    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button btngotocart,btnback;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btngotocart=findViewById(R.id.buttonLDaddtocart);
        btnback=findViewById(R.id.buttonBack);
        listView=findViewById(R.id.listViewCart);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++)
        {
            item= new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost : "+packages[i][4]+" /-");
            list.add(item);

        }
        sa=new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(sa);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
               Intent it=new Intent(LabTestActivity.this,LabTestActivityDetails.class);

               it.putExtra("text1",packages[i][0]);
               it.putExtra("TEXT2",package_details[i]);
               it.putExtra("text3",packages[i][4]);
                startActivity(it);
           }
       });
       btngotocart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(LabTestActivity.this,CardLabActivity.class));
           }
       });
    }
}