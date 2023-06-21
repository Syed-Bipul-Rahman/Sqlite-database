package com.clipboard.sqlitedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //Declaration of global variables
    EditText eid, ename, eage, ecity;
    Button save, view, update, delete, search;

    String id, name, age, city;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of vairables
        //edit text
        eid = findViewById(R.id.editTextTextPersonName3);
        ename = findViewById(R.id.editTextTextPersonName4);
        eage = findViewById(R.id.editTextTextPersonName2);
        ecity = findViewById(R.id.editTextTextPersonName);


        //button
        save = findViewById(R.id.button5);
        view = findViewById(R.id.button3);
        update = findViewById(R.id.button2);
        delete = findViewById(R.id.button4);
        search = findViewById(R.id.button);

//save new data
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ename.getText().toString();
                age = eage.getText().toString();
                city = ecity.getText().toString();

                if (name.equals("") || age.equals("") || city.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill the fields", Toast.LENGTH_SHORT).show();
                    eid.setError("Fill all fields");
                } else {
                    db.addData(name, age, city);
                    eid.setText("");
                    ename.setText("");
                    eage.setText("");
                    ecity.setText("");
                    Toast.makeText(MainActivity.this, "Data saved!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = db.getData();
                if (data.equals("")) {
                    Toast.makeText(MainActivity.this, "Data not found!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(MainActivity.this, ViewActivity.class));
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = eid.getText().toString().trim();
                name = ename.getText().toString().trim();
                age = eage.getText().toString().trim();
                city = ecity.getText().toString().trim();

                if (id.equals("") || name.equals("") || age.equals("") || city.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill the fields", Toast.LENGTH_SHORT).show();
                    eid.setError("Fill all fields");
                } else {

                    long l = Long.parseLong(id);
                    db.updateData(l, name, age, city);

                    eid.setText("");
                    ename.setText("");
                    eage.setText("");
                    ecity.setText("");
                    Toast.makeText(MainActivity.this, "Data updated!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = eid.getText().toString();
                if (id.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill the Id field!", Toast.LENGTH_SHORT).show();
                } else {
                    long l = Long.parseLong(id);
                    db.deleteData(l);
                    eid.setText("");
                    ename.setText("");
                    eage.setText("");
                    ecity.setText("");
                    Toast.makeText(MainActivity.this, "Data Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = eid.getText().toString();
                if (id.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill out this Fields!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        long l = Long.parseLong(id);
                        name = db.getName(l);
                        age = db.getAge(l);
                        city = db.getCity(l);

                        ename.setText(name);
                        eage.setText(age);
                        ecity.setText(city);

                        Toast.makeText(MainActivity.this, "Data found and set to EditText", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "No data found with this ID!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}