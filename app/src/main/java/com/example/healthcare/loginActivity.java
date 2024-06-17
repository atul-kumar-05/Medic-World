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

public class loginActivity extends AppCompatActivity {

    EditText editUsername,editPassward;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername=findViewById(R.id.editTextLoginUsername);
        editPassward=findViewById(R.id.editTextLoginPassword);
        btn=findViewById(R.id.buttonLogin);
        tv=findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       //startActivity(new Intent(loginActivity.this, HomeActivity.class));
                                       String username=editUsername.getText().toString();
                                       String passward=editPassward.getText().toString();
                                       Database db= new Database(getApplicationContext(),"Healthcare",null,1);
                                       if(username.length()==0 || passward.length()==0)
                                           Toast.makeText(getApplicationContext(),"envalid username or passward",Toast.LENGTH_SHORT).show();
                                       else
                                       {
                                           if(db.login(username,passward)==1)
                                           {
                                               Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                                               SharedPreferences sharedPreferences=getSharedPreferences("shared pref", Context.MODE_PRIVATE);
                                               SharedPreferences.Editor editor=sharedPreferences.edit();
                                               editor.putString("username",username);
                                               editor.apply();
                                               startActivity(new Intent(loginActivity.this, HomeActivity.class));
                                           }
                                           else
                                               Toast.makeText(getApplicationContext(),"envalid username or passward",Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               }
        );
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,RegisterActivity.class));

            }
        });
    }
}