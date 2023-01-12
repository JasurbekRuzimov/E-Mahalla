package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Chaqaloqlar_activity extends AppCompatActivity {
    LinearLayout backHome;
    LineChart lineChart;
    LinearLayout addUser;
    PieChart pieChart;

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

        addUser = findViewById(R.id.addBabyLayout);
        addUser.setOnClickListener(v -> {
            Intent intent = new Intent(Chaqaloqlar_activity.this, Demografik_Malumotlar.class);
            startActivity(intent);
        });




        pieChart = findViewById(R.id.pieChartBaby);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.animateY(3000);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(52f, "O'g'il bolalar"));
        yValues.add(new PieEntry(48f, "Qiz bolalar"));
        PieDataSet dataSet = new PieDataSet(yValues, "Chaqaloqlar");
        dataSet.setSliceSpace(10f);
        dataSet.setSelectionShift(15f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);
        pieChart.setData(data);
        pieChart.invalidate();



    }
}