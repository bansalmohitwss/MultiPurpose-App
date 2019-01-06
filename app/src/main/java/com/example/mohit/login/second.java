package com.example.mohit.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class second extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        b1 = (Button)findViewById(R.id.button3);
        b2 = (Button)findViewById(R.id.button4);
        e1 = (EditText)findViewById(R.id.editText3);
        e2 = (EditText)findViewById(R.id.editText4);
        e3 = (EditText)findViewById(R.id.editText5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(second.this,MainActivity.class);
                startActivity(t);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if(s1.equals("") || s2.equals("") || s3.equals(""))
                {
                    Toast.makeText(second.this, "No field can not be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase data = openOrCreateDatabase("gh",MODE_PRIVATE,null);
                    data.execSQL("create table if not exists student (name varchar,email varchar,password varchar)");
                    String s4 = "select * from student  where name='"+s1+"' and email='"+s2+"'";
                    Cursor cursor = data.rawQuery(s4,null);
                    if(cursor.getCount() > 0)
                    {
                        Toast.makeText(second.this, "User exists", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        data.execSQL("insert into student values ('"+s1+"','"+s2+"','"+s3+"')");
                        Toast.makeText(second.this, "Suceessfully Registered", Toast.LENGTH_SHORT).show();
                    }
                    Intent t = new Intent(second.this,MainActivity.class);
                    startActivity(t);
                    finish(); 
                }

            }
        });
    }
}
