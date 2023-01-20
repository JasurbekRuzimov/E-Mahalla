package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public boolean isBackPressed = false;

    //    PieChart pieChart;
    FloatingActionButton floatingActionButton;
    ImageView navMenu;

    Demografik_Malumotlar demografik_malumotlar = new Demografik_Malumotlar();

    // O'zgaruvchilarni e'lon qilamiz
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    //   LinearLayout linearLayout3;
    LinearLayout linearLayout4;
    //    LinearLayout linearLayout5;
    LinearLayout linearLayout6;
    LinearLayout linearLayout7;
    TextView textView;

    // O'zgaruvchilarni e'lon qildik


    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id larni e'lon qilish va tanlash
        floatingActionButton = findViewById(R.id.floatingActionButton2);
        linearLayout1 = findViewById(R.id.Erkaklar);
        linearLayout2 = findViewById(R.id.Ayollar);
        // linearLayout3 = findViewById(R.id.Chaqaloqlar);
        linearLayout4 = findViewById(R.id.VafotEtganlar);
//        linearLayout5 = findViewById(R.id.imageView5);
        linearLayout6 = findViewById(R.id.AholiRoyxati);
        linearLayout7 = findViewById(R.id.AholiQoshish);
        textView = findViewById(R.id.AllPeopleCount);

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
                    Intent intent = new Intent(MainActivity.this, Natijalar_Activity.class);
                    startActivity(intent);
                    break;
                case R.id.nav_share:
                    Intent intent1 = new Intent(MainActivity.this, Settings_activity.class);
                    startActivity(intent1);
                    break;
                case R.id.nav_share1:
                    Intent intent2 = new Intent(Intent.ACTION_SEND);
                    intent2.setType("text/plain");
                    String shareBody = "https://play.google.com/store/apps/details?id=uz.ruzimovjasurbek.e_mahalla";
                    String shareSub = "E-Mahalla";
                    intent2.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                    intent2.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(intent2, "Share Using"));
                    break;
                case R.id.nav_gallery1:
                    Intent intent3 = new Intent(MainActivity.this, Support_Activity.class);
                    startActivity(intent3);
                    break;
                case R.id.nav_slideshow1:
                    Intent intent4 = new Intent(MainActivity.this, FAQ_activity.class);
                    startActivity(intent4);
                    break;
                case R.id.nav_send:
                    Intent intent5 = new Intent(MainActivity.this, AboutUs_activity.class);
                    startActivity(intent5);
                    break;

            }
            return false;
        });

//       Navigation Bar uchun kodlar tugadi


// Boshqa activityga o'tish

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Demografik_Malumotlar.class);
            startActivity(intent);
        });

        linearLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Erkaklar_activity.class);
            startActivity(intent);
        });
        linearLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ayollar_activity.class);
            startActivity(intent);
        });

        linearLayout4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Vafot_Etganlar_activity.class);
            startActivity(intent);
        });

        linearLayout6.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Natijalar_Activity.class);
            startActivity(intent);
        });
        linearLayout7.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Demografik_Malumotlar.class);
            startActivity(intent);
        });
        // boshqa activityga o'tish tugadi




        Counter counter = new Counter(demografik_malumotlar.umumiySoni, textView);
        counter.start();

        TextView textView1;
        textView1 = findViewById(R.id.VafotEtganlar_soni);
        Counter counter1 = new Counter(demografik_malumotlar.vafotEtganlarSoni, textView1);
        counter1.start();

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

    public static class Counter {
        private int counter;
        private TextView textView;
        private CountDownTimer countDownTimer;

        public Counter(int counter, TextView textView) {
            this.counter = counter;
            this.textView = textView;
        }


        public void start() {
            countDownTimer = new CountDownTimer(counter, 1) {
                public void onTick(long millisUntilFinished) {
                    textView.setText(String.valueOf(counter - (int) (millisUntilFinished)));
                }

                public void onFinish() {
                    textView.setText(String.valueOf(counter));
                }
            }.start();
        }

        public void stop() {
            countDownTimer.cancel();
        }
    }


}