package com.example.admin.myandroidappdemo;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainDetail extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseCreater databaseCreater = new DatabaseCreater(this);
    TextView _welcometxt,_usernametxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);
        openHelper = new DatabaseCreater(this);

        _welcometxt = (TextView) findViewById(R.id.welcometxt);
        _usernametxt= (TextView) findViewById(R.id.usernametxt);
        SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
        String display = preferences.getString("display","");
        _usernametxt.setText(display);


    }
}
