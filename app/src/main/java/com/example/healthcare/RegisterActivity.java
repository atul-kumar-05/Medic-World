package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText editUsername, editPassword,editEmail, editConPassword;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editUsername=findViewById(R.id.editTextRegUsername);
        editPassword =findViewById(R.id.editTextRegPassword);
        btn=findViewById(R.id.btnRegister);
        tv=findViewById(R.id.textViewAlready);
        editEmail=findViewById(R.id.editTextRegEmail);
        editConPassword =findViewById(R.id.editTextappFees);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, loginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String usr=editUsername.getText().toString();
                 String email=editEmail.getText().toString();
                 String pass= editPassword.getText().toString();
                 String cnpass= editConPassword.getText().toString();
                 Database db= new Database(getApplicationContext(),"Healthcare",null,1);
                 if(usr.length()==0 || email.length()==0 || pass.length()==0 || cnpass.length()==0)
                 {
                     Toast.makeText(getApplicationContext(), "Fill all details", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     if(pass.compareTo(cnpass)==0)
                     {
                         if(isvalid(pass))
                         {
                             db.register(usr,email,pass);
                             Toast.makeText(getApplicationContext(), "Register inserted", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(RegisterActivity.this, loginActivity.class));
                         }

                         else
                             Toast.makeText(getApplicationContext(),"Password must contains 8 letters and digit and character and special symbols",Toast.LENGTH_SHORT).show();
                     }
                         else
                     Toast.makeText(getApplicationContext(), "password didn't match with confirm password", Toast.LENGTH_SHORT).show();
                 }

            }
        });

    }
  public static  boolean isvalid(String passwordhere)
    {
          int f1=0,f2=0,f3=0;
          if(passwordhere.length()<8)
              return false;
          else {
              for(int i=0;i<passwordhere.length();i++)
              {
                  if(Character.isLetter(passwordhere.charAt(i)))
                      f1=1;
              }
              for(int i=0;i<passwordhere.length();i++)
              {
                  if(Character.isDigit(passwordhere.charAt(i)))
                  f2=1;
              }
              for(int i=0;i<passwordhere.length();i++)
              {
                  char c=passwordhere.charAt(i);
                  if(c>=36 && c<=46 || c==64)
                  f3=1;
              }
          }
          if(f1==1 && f2==1 && f3==1)
              return true;
          return false;

    }
}