package com.example.admin.myandroidappdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg,_btnlogon;
    EditText  _txtfanme, _txtlname, _txtpass, _txtemail, _txtphone;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new DatabaseCreater(this);
        _btnreg =(Button) findViewById(R.id.btnreg);
        _txtfanme = (EditText) findViewById(R.id.txtfname);
        _txtlname = (EditText) findViewById(R.id.txtlname);
        _txtpass = (EditText) findViewById(R.id.txtpass);
        _txtemail = (EditText) findViewById(R.id.txtemail);
        _txtphone = (EditText) findViewById(R.id.txtphone);
        _btnlogon = (Button) findViewById(R.id.btnlogon) ;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String fname = _txtfanme.getText().toString();
                String lname = _txtlname.getText().toString();
                String pass = _txtpass.getText().toString();
                String email = _txtemail.getText().toString();
                String phone = _txtphone.getText().toString();

                if(isDuplicateEmail(email)){
                    insertData(fname,lname,pass,email,phone);

                }
                if(isDuplicateEmail(email)==false){
                    Toast.makeText(getApplicationContext(),"Error : This email has been already registerd.",Toast.LENGTH_LONG).show();
                    _txtemail.getText().clear();
                }else{
                    Toast.makeText(getApplicationContext(),"Register Sucessfully",Toast.LENGTH_LONG).show();
                }


            }
        });



        _btnlogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
    }

    public void insertData(String fname,String lname,String pass,String email,String phone){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseCreater.COL_2,fname);
        contentValues.put(DatabaseCreater.COL_3,lname);
        contentValues.put(DatabaseCreater.COL_4,pass);
        contentValues.put(DatabaseCreater.COL_5,email);
        contentValues.put(DatabaseCreater.COL_6,phone);
        //db.delete(DatabaseCreater.TABLE_NAME,null,null);
        long id = db.insert(DatabaseCreater.TABLE_NAME,null,contentValues);

        //SmsSender smsSender = new SmsSender();
        //smsSender.sendSms(phone);
    }

    public boolean isDuplicateEmail(String email){
        cursor = db.rawQuery("SELECT * FROM "+DatabaseCreater.TABLE_NAME+ " WHERE "+ DatabaseCreater.COL_5 +" =? ",new String [] {email});

            if(cursor.getCount()>0){
                return false;

            }

            return  true;

    }


}
