package com.android.dollartoinr.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.dollartoinr.Model.Dollar;
import com.android.dollartoinr.R;
import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class AddDollarValueActivity extends AppCompatActivity {
    EditText editText;
    Button button;
FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
DatabaseReference databaseReference=firebaseDatabase.getReference("ChartValue");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dollar_value);
        editText=findViewById(R.id.editTextTextPersonName);
        button=findViewById(R.id.Insert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              insetData();
                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void  insetData()
    {
        Calendar c = Calendar.getInstance();

        String id=databaseReference.push().getKey();
        Double value= Double.valueOf(editText.getText().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(c.getTime());
        Dollar dollar= new Dollar(currentDateandTime,  value);
        databaseReference.child(id).setValue(dollar);



    }

}