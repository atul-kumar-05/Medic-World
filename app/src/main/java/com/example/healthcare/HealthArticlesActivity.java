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

public class HealthArticlesActivity extends AppCompatActivity {
String[][] helath_details={
        {"Walking daily","","","","click more details"},
        {"Home care of covid 19","","","","click more details"},
        {"stop smoking","","","","click more details"},
        {"Menstrual cramps","","","","click more details"},
        {"Healthy gut","","","","click more details"}
};
private int[] images={
        R.drawable.health1,
       R.drawable.health2,
       R.drawable.health3,
       R.drawable.health4,
       R.drawable.health5
};

    SimpleAdapter simpleAdapter;
    ArrayList arrayList;
    ListView lst;
    Button btnback;
    HashMap<String,String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);
        btnback=findViewById(R.id.buttonAback);
        lst=findViewById(R.id.listViewHACart);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this,HomeActivity.class));
            }
        });

        arrayList=new ArrayList();
        for (int i=0;i<helath_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",helath_details[i][0]);
            item.put("line2",helath_details[i][1]);
            item.put("line3",helath_details[i][2]);
            item.put("line4",helath_details[i][3]);
            item.put("line5",helath_details[i][4]);
            arrayList.add(item);

        }
        simpleAdapter= new SimpleAdapter(this,arrayList,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        lst.setAdapter(simpleAdapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(HealthArticlesActivity.this,HealthArticlesDetailsActivity.class);
               intent.putExtra("text1",helath_details[position][0]);
               intent.putExtra("text2",images[position]);
               startActivity(intent);
            }
        });
    }
}