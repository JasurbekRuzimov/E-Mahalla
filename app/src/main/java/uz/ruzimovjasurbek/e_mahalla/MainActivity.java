package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public boolean isBackPressed = false;
    PieChart pieChart;
    RoundedImageView roundedImageView;
    ImageView navMenu;

    // O'zgaruvchilarni e'lon qilamiz
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;
    LinearLayout linearLayout4;
//    LinearLayout linearLayout5;
    // O'zgaruvchilarni e'lon qildik


    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id larni e'lon qilish va tanlash
        linearLayout1 = findViewById(R.id.imageView1);
        linearLayout2 = findViewById(R.id.imageView2);
        linearLayout3 = findViewById(R.id.imageView3);
        linearLayout4 = findViewById(R.id.imageView4);
        roundedImageView = findViewById(R.id.roundedImageView);
//        linearLayout5 = findViewById(R.id.imageView5);
        // 1 - 5 gacha bo'lgan id larni tanlash

        // Navigation Bar uchun kodlar

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        navMenu = findViewById(R.id.imagemenu);
        navMenu.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_gallery:
                    Toast.makeText(this, "Aholi ro'yhati", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_share:
                    Toast.makeText(this, "Sozlamalar", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_share1:
                    Toast.makeText(this, "Do'stlarga ulashish", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_gallery1:
                    Toast.makeText(this, "Qo'llab quvvatlash markazi", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_slideshow1:
                    Toast.makeText(this, "FAQ", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_send:
                    Toast.makeText(this, "Biz haqimizda", Toast.LENGTH_SHORT).show();
                    break;

            }
            return false;
        });

//       Navigation Bar uchun kodlar tugadi


// Boshqa activityga o'tish

        linearLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Erkaklar_activity.class);
            startActivity(intent);
        });
        linearLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ayollar_activity.class);
            startActivity(intent);
        });
        linearLayout3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Chaqaloqlar_activity.class);
            startActivity(intent);
        });
        linearLayout4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Vafot_Etganlar_activity.class);
            startActivity(intent);
        });
        roundedImageView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Natijalar_Activity.class);
            startActivity(intent);
        });
//        linearLayout5.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, Demografik_malumotlar.class);
//            startActivity(intent);
//        });
        // boshqa activityga o'tish tugadi

        // PieChart
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
        yValues.add(new PieEntry(25f, "Erkaklar"));
        yValues.add(new PieEntry(33f, "Ayollar"));
        yValues.add(new PieEntry(42f, "Vafot etganlar"));
        PieDataSet dataSet = new PieDataSet(yValues, "Aholi");
        dataSet.setSliceSpace(10f);
        dataSet.setSelectionShift(15f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);
        pieChart.setData(data);
        // PieChart tugadi


    }

// Ikki martadan keyin chiqish uchun

    public void onBackPressed() {
        if (isBackPressed) {
            super.onBackPressed();
        } else {
            isBackPressed = true;
            Toast.makeText(this, " Chiqish uchun 🔙 ni ikki marta bosing ", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> isBackPressed = false, 2000);
        }
    }
// shu yerda 2 sekunddan keyin chiqishni amalga oshiradi

}