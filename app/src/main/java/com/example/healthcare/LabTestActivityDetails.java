package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestActivityDetails extends AppCompatActivity {
 TextView tvPackage,tvcost;
 EditText edDetails;
 Button btnaddtocart,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPackage=findViewById(R.id.textViewLDPackage);
        tvcost=findViewById(R.id.LDcost);
        edDetails=findViewById(R.id.listViewCart);
        btnaddtocart=findViewById(R.id.buttonLDaddtocart);
        btnback=findViewById(R.id.buttonLDBack);

        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvPackage.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("TEXT2"));
        tvcost.setText("Total Cost " +intent.getStringExtra("text3")+"/-");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(LabTestActivityDetails.this,LabTestActivity.class));
            }
        });
btnaddtocart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();
        String product=tvPackage.getText().toString();
        float price=Float.parseFloat(intent.getStringExtra("text3").toString());

        Database db=new Database(getApplicationContext(),"Healthcare",null,1);
        if(db.checkcart(username,product)==1)
            Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_LONG).show();
        else{
                     db.addCart(username,product,price,"lab");
            Toast.makeText(getApplicationContext(),"Record inserted to cart",Toast.LENGTH_LONG).show();
            startActivity(new Intent(LabTestActivityDetails.this,LabTestActivity.class));
        }
    }
});
    }
}