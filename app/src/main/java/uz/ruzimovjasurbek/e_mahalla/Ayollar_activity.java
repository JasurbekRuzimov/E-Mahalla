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

public class Ayollar_activity extends AppCompatActivity {
   LinearLayout backHome;
    PieChart pieChart;
   LinearLayout addUser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayollar);


        backHome=findViewById(R.id.backHome);
        backHome.setOnClickListener(v -> {
            finish();
        });


        pieChart=findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.animateY(3000);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(42f, "30 yoshdan kichik"));
        yValues.add(new PieEntry(33f, "30-50 yosh"));
        yValues.add(new PieEntry(25f, "50 yoshdan katta"));
        PieDataSet dataSet = new PieDataSet(yValues, "Ayollar");
        dataSet.setSliceSpace(10f);
        dataSet.setSelectionShift(15f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);
        pieChart.setData(data);




        addUser=findViewById(R.id.AyolQoshish);
        addUser.setOnClickListener(v -> {
            Intent intent=new Intent(Ayollar_activity.this,Demografik_Malumotlar.class);
            startActivity(intent);
        });


    }
}