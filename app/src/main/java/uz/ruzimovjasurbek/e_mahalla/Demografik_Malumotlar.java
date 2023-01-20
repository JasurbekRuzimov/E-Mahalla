package uz.ruzimovjasurbek.e_mahalla;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;

public class Demografik_Malumotlar extends AppCompatActivity {
    int umumiySoni=34;
    int erkaklarSoni = 22;
    int ayollarSoni = 12;
    int vafotEtganlarSoni = 0;
    int totalAyollar=1;
    int totalErkaklar=1;
    LinearLayout backHome;
    Button addbaby;
    TextInputLayout Ismi;
    TextInputLayout Familiyasi;
    TextInputLayout OtasiningIsmi;
    TextInputLayout Mahallasi;
    TextInputEditText mahallaEditText;
    TextInputLayout Jinsi;
    TextInputLayout TugilganVaqti;
    TextInputEditText dobEditText;
    TextInputEditText genderEditText;

    int day, month, year;

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
        mahallaEditText = findViewById(R.id.Mahallasi);
        Jinsi = findViewById(R.id.jinsi);
        genderEditText = findViewById(R.id.Jinsi);
        TugilganVaqti = findViewById(R.id.tugilganVaqti);
        dobEditText = findViewById(R.id.BirthDataTime);

        mahallaEditText.setOnClickListener(v -> {
            showMahallaDialog();
        });

        dobEditText.setOnClickListener(v -> {
            showDatePicker();
        });

        genderEditText.setOnClickListener(v -> {
            showGenderDialog();
        });

        backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(v -> {
            finish();
        });


        addbaby = findViewById(R.id.royxatgaOlish);
        addbaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                umumiySoni++;

                DBHelper dbHelper = new DBHelper(Demografik_Malumotlar.this);
                //check isEmpty
                if (Objects.requireNonNull(Ismi.getEditText()).getText().toString().isEmpty()) {
                    Ismi.setError("Ismingizni kiriting");
                    return;
                }
                if (Objects.requireNonNull(Familiyasi.getEditText()).getText().toString().isEmpty()) {
                    Familiyasi.setError("Familiyangizni kiriting");
                    return;
                }
                if (Objects.requireNonNull(OtasiningIsmi.getEditText()).getText().toString().isEmpty()) {
                    OtasiningIsmi.setError("Otasining ismini kiriting");
                    return;
                }
                if (Objects.requireNonNull(Mahallasi.getEditText()).getText().toString().isEmpty()) {
                    Mahallasi.setError("Mahallangizni kiriting");
                    return;
                }
                if (Objects.requireNonNull(Jinsi.getEditText()).getText().toString().isEmpty()) {
                    Jinsi.setError("Jinsingizni kiriting");
                    return;
                }
                if (Objects.requireNonNull(TugilganVaqti.getEditText()).getText().toString().isEmpty()) {
                    TugilganVaqti.setError("Tugilgan vaqtingizni kiriting");
                    return;
                }
                dbHelper.mahalla(Objects.requireNonNull(Ismi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(Familiyasi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(OtasiningIsmi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(Mahallasi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(Jinsi.getEditText()).getText().toString().trim(),
                        Objects.requireNonNull(TugilganVaqti.getEditText()).getText().toString().trim());

                Intent intent = new Intent(Demografik_Malumotlar.this, MainActivity.class);
                startActivity(intent);
                finish();

                if (Objects.requireNonNull(Jinsi.getEditText()).getText().toString().trim().equals("erkak")) {
                    totalErkaklar = DBHelper.getErkaklarCount();
                } else if (Objects.requireNonNull(Jinsi.getEditText()).getText().toString().trim().equals("ayol")) {
                    totalAyollar = DBHelper.getAyollarCount();
                }
            }

        });


    }

    private void showGenderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jinsni tanlang");
        builder.setItems(new CharSequence[]{"Erkak", "Ayol"}, new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Objects.requireNonNull(Jinsi.getEditText()).setText("Erkak");
                        break;
                    case 1:
                        Objects.requireNonNull(Jinsi.getEditText()).setText("Ayol");
                        break;
                }
            }
        });
        builder.show();
    }

    private void showMahallaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mahallani tanlang");
        builder.setItems(new CharSequence[]{"Kamolot"}, new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    Objects.requireNonNull(Mahallasi.getEditText()).setText("Kamolot");
                }
            }
        });
        builder.show();

    }


    private void showDatePicker() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(Demografik_Malumotlar.this,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        // Set selected date to dobEditText
                        Objects.requireNonNull(TugilganVaqti.getEditText()).setText(selectedDay + "." + (selectedMonth + 1) + "." + selectedYear);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

//    public void increment(View view) {
//        umumiySoni = umumiySoni + 1;
//        Intent intent = new Intent(Demografik_Malumotlar.this, MainActivity.class);
//        intent.putExtra("umumiySoni", umumiySoni);
//        startActivity(intent);
//    }
//    public void increaseInteger() {
//        umumiySoni = umumiySoni + 1;
//        display(umumiySoni);
//    }
//
//    private void display(int umumiySoni) {
//        TextView umumiySoniTextView = findViewById(R.id.AllPeopleCount);
//        umumiySoniTextView.setText(umumiySoni);
//    }

}