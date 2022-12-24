package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Demografik_Malumotlar extends AppCompatActivity {
Button addBaby;
LinearLayout backHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demografik_malumotlar);

        addBaby = findViewById(R.id.register);
        addBaby.setOnClickListener(v -> {
            Toast.makeText(this, "Ro'yxatga qo'shildi", Toast.LENGTH_SHORT).show();
        });

        backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(v -> {
            finish();
        });

    }
}