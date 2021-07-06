package com.android.dollartoinr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<Entry> linechart;
    LineDataSet lineDataSet;
    LineData lineData;
    LineChart lineChart;
    Button add;
    TextView textView1,textView2,textView3,textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart=findViewById(R.id.chart1);
         add =findViewById(R.id.add);
          XAxis xAxis = lineChart.getXAxis();
          xAxis.setValueFormatter(new LineChartXAxisValueFormatter());

        linechart= new ArrayList<>();

//        long s=    seconds("2017-02-0");

        linechart.add(new Entry( seconds("2017-01-01"),65f));
        linechart.add(new Entry( seconds("2018-01-01"),67f));
        linechart.add(new Entry( seconds("2019-01-01"),68f));
        linechart.add(new Entry( seconds("2020-01-01"),69f));
        linechart.add(new Entry( seconds("2021-01-01"),75f));


        lineDataSet= new LineDataSet(linechart,"count");
        lineData=new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setValueTextColor(Color.BLUE);
        lineDataSet.setValueTextSize(13f);
        lineDataSet.setDrawFilled(true);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,AddDollarValueActivity.class);
                startActivity(intent);
            }
        });


    }
    public long seconds(String date)
    {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();


            return TimeUnit.MILLISECONDS.toDays((long)timeInMilliseconds);
        }
        catch (ParseException e)
        {
//            Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }
}