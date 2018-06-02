package com.example.admin.myandroidappdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button _btnLogin;
    EditText _txtEmailLogin,_txtPassLogin;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseCreater(this);
        db=openHelper.getReadableDatabase();
        _btnLogin=(Button) findViewById(R.id.btnLogin);
        _txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        _txtPassLogin = (EditText) findViewById(R.id.txtPassLogin);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _txtEmailLogin.getText().toString();
                String pass = _txtPassLogin.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+DatabaseCreater.TABLE_NAME+ " WHERE "+ DatabaseCreater.COL_5 +" =? AND "+DatabaseCreater.COL_4+" =?",new String [] {email,pass});

                if (cursor!=null){
                    if(cursor.getCount()>0){
                    SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    String userDetails = preferences.getString(email,email);
                    editor.putString("display",userDetails);
                    editor.commit();

                        Intent intent = new Intent(login.this,MainDetail.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplicationContext(),"Error Wrong username",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }


}
