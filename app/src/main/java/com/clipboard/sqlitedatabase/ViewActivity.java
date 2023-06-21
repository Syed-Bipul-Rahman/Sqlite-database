package com.clipboard.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    TextView tv;
    DatabaseHandler db;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tv=findViewById(R.id.viewdata);

        db=new DatabaseHandler(this);
        data=db.getData();
        tv.setText(data);


    }
}