package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;

public class Update_Activity extends AppCompatActivity {

    LinearLayout backHome;
    Button addbaby, delete_button;
    TextInputLayout Ismi;
    TextInputLayout Familiyasi;
    TextInputLayout OtasiningIsmi;
    TextInputLayout Mahallasi;
    TextInputEditText mahallaEditText;
    TextInputLayout Jinsi;
    TextInputLayout TugilganVaqti;
    TextInputEditText dobEditText1;
    TextInputEditText genderEditText1;

    String id, ismi, familiyasi, otasiningIsmi, mahallasi, jinsi, tugilganVaqti;
    int day, month, year;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // id larni e'lon qilish va tanlash

        Ismi = findViewById(R.id.ismi_update);
        Familiyasi = findViewById(R.id.familiyasi_update);
        OtasiningIsmi = findViewById(R.id.sharifi_update);
        Mahallasi = findViewById(R.id.mahallasi_update);
        mahallaEditText = findViewById(R.id.MahallasiUpdate);
        Jinsi = findViewById(R.id.jinsi_update);
        genderEditText1 = findViewById(R.id.JinsiUpdate);
        TugilganVaqti = findViewById(R.id.tugilganVaqti_update);
        delete_button = findViewById(R.id.royxatgaOlish_delete);
        dobEditText1 = findViewById(R.id.BirthDataTimeUpdate);

        mahallaEditText.setOnClickListener(v -> {
            showMahallaDialog();
        });

        genderEditText1.setOnClickListener(v -> {
            showGenderDialog();
        });

        dobEditText1.setOnClickListener(v -> {
            showDatePicker();
        });

        backHome = findViewById(R.id.backHome1);
        backHome.setOnClickListener(v -> {
            finish();
        });


        addbaby = findViewById(R.id.royxatgaOlish_update);
        getAndSetIntentData();

        addbaby.setOnClickListener(v -> {
            DBHelper myDB = new DBHelper(Update_Activity.this);
            ismi = Objects.requireNonNull(Ismi.getEditText()).getText().toString().trim();
            if (ismi.isEmpty()) {
                Ismi.setError("Ismingizni kiriting");
                return;
            }
            familiyasi = Objects.requireNonNull(Familiyasi.getEditText()).getText().toString().trim();
            if (familiyasi.isEmpty()) {
                Familiyasi.setError("Familiyangizni kiriting");
                return;
            }
            otasiningIsmi = Objects.requireNonNull(OtasiningIsmi.getEditText()).getText().toString().trim();
            if (otasiningIsmi.isEmpty()) {
                OtasiningIsmi.setError("Otasining ismini kiriting");
                return;
            }
            mahallasi = Objects.requireNonNull(Mahallasi.getEditText()).getText().toString().trim();
            if (mahallasi.isEmpty()) {
                Mahallasi.setError("Mahallangizni kiriting");
                return;
            }
            jinsi = Objects.requireNonNull(Jinsi.getEditText()).getText().toString().trim();
            if (jinsi.isEmpty()) {
                Jinsi.setError("Jinsingizni kiriting");
                return;
            }
            tugilganVaqti = Objects.requireNonNull(TugilganVaqti.getEditText()).getText().toString().trim();
            if (tugilganVaqti.isEmpty()) {
                TugilganVaqti.setError("Tugilgan vaqtingizni kiriting");
                return;
            }
            myDB.updateData(id, ismi, familiyasi, otasiningIsmi, mahallasi, jinsi, tugilganVaqti);
            Intent intent = new Intent(Update_Activity.this, Natijalar_Activity.class);
            startActivity(intent);
            finish();
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }

        });


    }

    private void showMahallaDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
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

    private void showGenderDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
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

    private void showDatePicker() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(Update_Activity.this,
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


    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("ismi") && getIntent().hasExtra("familiyasi")
                && getIntent().hasExtra("otasining_ismi") && getIntent().hasExtra("mahallasi") &&
                getIntent().hasExtra("jinsi") && getIntent().hasExtra("tugilgan_vaqti")) {
            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            ismi = getIntent().getStringExtra("ismi");
            familiyasi = getIntent().getStringExtra("familiyasi");
            otasiningIsmi = getIntent().getStringExtra("otasining_ismi");
            mahallasi = getIntent().getStringExtra("mahallasi");
            jinsi = getIntent().getStringExtra("jinsi");
            tugilganVaqti = getIntent().getStringExtra("tugilgan_vaqti");
            // Setting Intent Data
            Objects.requireNonNull(Ismi.getEditText()).setText(ismi);
            Objects.requireNonNull(Familiyasi.getEditText()).setText(familiyasi);
            Objects.requireNonNull(OtasiningIsmi.getEditText()).setText(otasiningIsmi);
            Objects.requireNonNull(Mahallasi.getEditText()).setText(mahallasi);
            Objects.requireNonNull(Jinsi.getEditText()).setText(jinsi);
            Objects.requireNonNull(TugilganVaqti.getEditText()).setText(tugilganVaqti);
            Log.println(Log.ASSERT, "id", id + ismi + familiyasi + otasiningIsmi + mahallasi + jinsi + tugilganVaqti);

        } else {
            Toast.makeText(this, "Hech qanday ma'lumotlar yo'q.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(ismi + " ni o'chirish");
        builder.setMessage(ismi + " haqidagi barcha ma'lumotlarni o'chirmoqchimisiz ?");
        builder.setPositiveButton("Ha", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper myDB = new DBHelper(Update_Activity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Yo'q", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

}