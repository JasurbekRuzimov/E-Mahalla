package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Chaqaloqlar_activity extends AppCompatActivity {
    LinearLayout backHome;
    LineChart lineChart;
    LinearLayout addUser;
    BarChart barChart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaqaloqlar);

        // id larni e'lon qilish va tanlash
        backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(v -> {
            finish();
        });



//        lineChart = findViewById(R.id.lineChart);



        addUser = findViewById(R.id.linearLayout);
        addUser.setOnClickListener(v -> {
            Intent intent = new Intent(Chaqaloqlar_activity.this, Demografik_Malumotlar.class);
            startActivity(intent);
        });

        barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            float value = (float) (Math.random() * 100);
            barEntries.add(new BarEntry(i, value));
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Chaqaloqlar");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(R.color.primary);
        barDataSet.setValueTextSize(16f);
        barDataSet.setDrawValues(false);
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(3000);
        barChart.getDescription().setText("Chaqaloqlar");
        barChart.getDescription().setTextColor(R.color.primary);



    }
}