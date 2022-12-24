package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Vafot_Etganlar_activity extends AppCompatActivity {
    LinearLayout backHome;
    PieChart pieChart;
    LinearLayout addUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vafot_etganlar);

        backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(v -> {
            finish();
        });


        pieChart = findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.animateY(3000);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(10f, "20 yoshdan kichik"));
        yValues.add(new PieEntry(21f, "20-40 yosh"));
        yValues.add(new PieEntry(27f, "40-60 yosh"));
        yValues.add(new PieEntry(42f, "60 yoshdan katta"));
        PieDataSet dataSet = new PieDataSet(yValues, "Vafot etganlar");
        dataSet.setSliceSpace(9f);
        dataSet.setSelectionShift(19f);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);
        pieChart.setData(data);




        addUser = findViewById(R.id.linearLayout);
        addUser.setOnClickListener(v -> {
            Intent intent = new Intent(Vafot_Etganlar_activity.this, Demografik_Malumotlar_Vafot_etganlar.class);
            startActivity(intent);
        });

    }
}