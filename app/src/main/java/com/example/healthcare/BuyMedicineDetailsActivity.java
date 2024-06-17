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

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView textViewcost,textViewpackage;
    Button btnback,btnaddtocart;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        textViewcost=findViewById(R.id.BMDcost);
        textViewpackage=findViewById(R.id.textViewBMDPackage);
        editText=findViewById(R.id.edittextBMDCart);
        btnaddtocart=findViewById(R.id.buttonBMDaddtocart);
        btnback=findViewById(R.id.buttonBMDBack);

        Intent intent=getIntent();
        textViewpackage.setText(intent.getStringExtra("text1"));
        editText.setText(intent.getStringExtra("text2"));
        textViewcost.setText("Total cost"+intent.getStringExtra("text3")+"/-");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"Healthcare",null,1);
                String product=textViewpackage.getText().toString();
                Float price=Float.parseFloat(intent.getStringExtra("text3").toString());
                if(db.checkcart(username,product)==1)
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_LONG).show();
                else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Record inserted to cart",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });


    }
}