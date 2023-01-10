package uz.ruzimovjasurbek.e_mahalla;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Demografik_Malumotlar extends AppCompatActivity {
    LinearLayout backHome;
    Button addbaby;
    TextInputLayout Ismi;
    TextInputLayout Familiyasi;
    TextInputLayout OtasiningIsmi;
    TextInputLayout Mahallasi;
    TextInputLayout Jinsi;
    TextInputLayout TugilganVaqti;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demografik_malumotlar);

        // id larni e'lon qilish va tanlash

        Ismi = findViewById(R.id.ismi);
        Familiyasi = findViewById(R.id.familiyasi);
        OtasiningIsmi = findViewById(R.id.sharifi);
        Mahallasi = findViewById(R.id.mahallasi);
        Jinsi = findViewById(R.id.jinsi);
        TugilganVaqti = findViewById(R.id.tugilganVaqti);


        backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(v -> {
            finish();
        });

        addbaby = findViewById(R.id.royxatgaOlish);
        addbaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(Demografik_Malumotlar.this);
                dbHelper.mahalla(Objects.requireNonNull(Ismi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(Familiyasi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(OtasiningIsmi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(Mahallasi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(Jinsi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(TugilganVaqti.getEditText()).getText().toString().trim());
                Intent intent = new Intent(Demografik_Malumotlar.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}