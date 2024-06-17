package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String qrl="create table users(username text,email text,password text)";
         db.execSQL(qrl);
         String qrl1="create table cart(username text,product text,price float,otype text)";
         db.execSQL(qrl1);
        String qrl2="create table orderplace(username text,fullname text,address text,contact text,pincode int,date text,time text,amount float,otype text)";
        db.execSQL(qrl2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void register(String username,String email,String password)
    {
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db= getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username,String password)
    {
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=? and password=?",str);
        if(cursor.moveToFirst())
            result=1;
        return result;
    }
    public void addCart(String username,String product,float price,String otype )
    {
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkcart(String username,String product)
    {
        int ans=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from cart where username = ? and product = ?",str);
        if(cursor.moveToFirst())
            ans= 1;
        db.close();
        return ans;
    }
    public  void removecart(String username,String otype)
    {
        String str[]=new String[2];
        str[1]=otype;
        str[0]=username;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart","username = ? and otype = ?",str);
        db.close();

    }
    public ArrayList getCardData(String username, String otype)
    {
        ArrayList<String> ar=new ArrayList<>();
        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from cart where username = ? and otype = ?",str);
        if(cursor.moveToFirst())
        {
           do {
               String product=cursor.getString(1);
               String price=cursor.getString(2);
               ar.add(product+"$"+price);
           }
           while (cursor.moveToNext());
        }
        db.close();
        return ar;
    }
    void addOrder(String username,String fullname,String address, String contact,int pincode,String date,String time,float amount, String otype)
    {
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contact",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);

        cv.put("amount",amount);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }
    public  ArrayList getorderdata(String username)
    {
        ArrayList<String> arr=new ArrayList<String>();
        SQLiteDatabase db=getReadableDatabase();
        String[] str=new String[1];
        str[0]=username;
        Cursor cr= db.rawQuery("select * from orderplace where username=?",str);

        if(cr.moveToFirst())
        {
            do{
                arr.add(cr.getString(1)+"$"+cr.getString(2)+"$"+cr.getString(3)+"$"+cr.getString(4)+"$"+cr.getString(5)+"$"+cr.getString(6)+"$"+cr.getString(7)+"$"+cr.getString(8));
            }while(cr.moveToNext());
        }
db.close();
        return arr;
    }
    public int checkAppointmentExit(String username,String fullname,String address,String contact,String date,String time)
    {
        int result=0;
        String[] str=new String[6];
        str[0]=username;
        str[1]=fullname;
        str[2]=address;
        str[3]=contact;
        str[4]=date;
        str[5]=time;

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from orderplace where username=? and fullname=? and address=? and contact=? and date=? and time=?",str);
      if(cr.moveToNext())
      {
          result=1;
      }
      db.close();
      return result;
    }
}
