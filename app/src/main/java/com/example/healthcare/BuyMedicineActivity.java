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

public class BuyMedicineActivity extends AppCompatActivity {

   private String[][] packages={
           {"Adderall","","","","50"},
           {"Ativan","","","","46"},
           {"Amoxicillin","","","","48"},
           {"Actetaminophen","","","","47"},
           {"Actrosine 40 injection","","","","2500"},
           {"Aromasin 25 mg tablet","","","","79"},
           {"ascoril LS Duo tablet","","","","109"},
           {"augmentin 625 Duo tablet","","","","1000"},
           {"Avil 25 tablet","","","","200"},
    };
    private String[] package_details={
        "building and keeping the bone and teeth strong\n"+
                "reduce fatigue/stress and muscular pain\n"+
                "boosting immune and increase resistance against infection\n",
                "chromium is a essential trace minerals that play an important role in insulin regulation\n"+
                "provide relief from vitamin B deficiency\n"+
                "helps in increase in red blood cell\n"+
                "maintains healthy nervous system\n",
                "it promotes skin health as well as overall health\n"

    };

    SimpleAdapter simpleAdapter;
    ArrayList arrayList;
    ListView listView;
    Button btnback,btngotocart;
    HashMap<String,String>item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        btnback=findViewById(R.id.buttonBMBack);
        listView=findViewById(R.id.listViewBMCart);
        btngotocart=findViewById(R.id.buttonBMGOtocart);

        btngotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });
        arrayList=new ArrayList();
        for (int i=0;i<packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total cost:"+packages[i][4]+"/-");
            arrayList.add(item);

        }
        simpleAdapter= new SimpleAdapter(this,arrayList,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);

                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);

                startActivity(it);
            }
        });
    }
}