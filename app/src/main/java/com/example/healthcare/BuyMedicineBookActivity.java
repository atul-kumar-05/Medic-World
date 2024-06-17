package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    private Button btnBook;
    EditText eaddress,econtact,ecost,efullname,epincode;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        tv=findViewById(R.id.textView);
        efullname=findViewById(R.id.editTextBMBfullname);
        eaddress=findViewById(R.id.editTextBMBadd);
        ecost=findViewById(R.id.editTextBMBcontact);
        econtact=findViewById(R.id.editTextBMBcontact);
        btnBook=findViewById(R.id.buttonBMBBook);
        epincode=findViewById(R.id.editTextBMBpincode);

        Intent intent=getIntent();
        String[] price =intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"Healthcare",null,1);
                db.addOrder(username,efullname.getText().toString(),eaddress.getText().toString(),econtact.getText().toString(),Integer.parseInt(epincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"lab");
                db.removecart(username,"medicine");
                Toast.makeText(getApplicationContext(), "your booking is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
            }
        });

    }
}