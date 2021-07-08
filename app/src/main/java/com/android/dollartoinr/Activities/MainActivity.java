package com.android.dollartoinr.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.dollartoinr.Helper.LineChartXAxisValueFormatter;
import com.android.dollartoinr.Model.Dollar;
import com.android.dollartoinr.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("ChartValue");
    ArrayList<Entry> linechart;
    LineDataSet lineDataSet;
    LineData lineData;
    LineChart lineChart;
    Button add;
    TextView textView1, textView2, textView3, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = findViewById(R.id.chart1);
        add = findViewById(R.id.add);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new LineChartXAxisValueFormatter());

        linechart = new ArrayList<>();


//        long s=    seconds("2017-02-0");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDollarValueActivity.class);
                startActivity(intent);
            }
        });

        getData();
        showData(linechart);
    }

    public long seconds(String date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();


            return TimeUnit.MILLISECONDS.toDays((long) timeInMilliseconds);
        } catch (ParseException e) {
//            Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d(TAG, "seconds: " + "Exception in catch");
        }

        return 0;
    }

    public void getData() {
//        ArrayList<Entry> entryArrayList;
//        entryArrayList= new ArrayList<>();
//        List<Entry> entryArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.hasChildren()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Dollar dollar = dataSnapshot.getValue(Dollar.class);

//                        linechart.add(new Entry( seconds(dollar.getDate()),(float)dollar.getPrice()));
                        linechart.add(new Entry(seconds(dollar.getDate()), (float) dollar.getPrice()));
                        Log.d("MAINACTIVITY", String.valueOf(seconds(dollar.getDate())));

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        showData(linechart);
    }

    public void showData(List<Entry> entryArrayList) {
        //        linechart.add(new Entry( seconds("2017-01-01"),65f));


        linechart.add(new Entry(linechart.get(0).getX() ,linechart.get(0).getY()));
//        linechart.add(new Entry( seconds("2019-01-01"),68f));
//        linechart.add(new Entry( seconds("2020-01-01"),69f));
//        linechart.add(new Entry( seconds("2021-01-01"),75f));
//        getData();
//                linechart.add(entryArrayList.);
//        Log.d("MAINACTIVITY", String.valueOf(entryArrayList.size()));


        lineDataSet = new LineDataSet(entryArrayList, "count");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setValueTextColor(Color.BLUE);
        lineDataSet.setValueTextSize(13f);
        lineDataSet.setDrawFilled(true);


    }

    @Override
    protected void onStart() {
        super.onStart();
//        getData();

    }
}