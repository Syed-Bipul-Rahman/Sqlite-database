package com.clipboard.sqlitedatabase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //Declaration of global variables
    EditText eid, ename, eage, ecity;
    Button save, view, update, delete, search;

    String id, name, age, city;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of vairables
        //edit text
        eid=findViewById(R.id.editTextTextPersonName3);
        ename=findViewById(R.id.editTextTextPersonName4);
        eage=findViewById(R.id.editTextTextPersonName2);
        ecity=findViewById(R.id.editTextTextPersonName);


        //button
        save=findViewById(R.id.button5);
        view=findViewById(R.id.button3);
        update=findViewById(R.id.button2);
        delete=findViewById(R.id.button4);
        search=findViewById(R.id.button);





    }
}