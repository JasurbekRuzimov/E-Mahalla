package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Update_Activity extends AppCompatActivity {

    LinearLayout backHome;
    Button addbaby, delete_button ;
    TextInputLayout Ismi;
    TextInputLayout Familiyasi;
    TextInputLayout OtasiningIsmi;
    TextInputLayout Mahallasi;
    TextInputLayout Jinsi;
    TextInputLayout TugilganVaqti;

    String id, ismi, familiyasi, otasiningIsmi, mahallasi, jinsi, tugilganVaqti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // id larni e'lon qilish va tanlash

        Ismi = findViewById(R.id.ismi_update);
        Familiyasi = findViewById(R.id.familiyasi_update);
        OtasiningIsmi = findViewById(R.id.sharifi_update);
        Mahallasi = findViewById(R.id.mahallasi_update);
        Jinsi = findViewById(R.id.jinsi_update);
        TugilganVaqti = findViewById(R.id.tugilganVaqti_update);
        delete_button = findViewById(R.id.royxatgaOlish_delete);

        backHome = findViewById(R.id.backHome1);
        backHome.setOnClickListener(v -> {
            finish();
        });

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(ismi);
        }



        addbaby = findViewById(R.id.royxatgaOlish_update);
        addbaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(Update_Activity.this);
                dbHelper.updateData(id, ismi, familiyasi, otasiningIsmi, mahallasi, jinsi, tugilganVaqti);
                Toast.makeText(Update_Activity.this, "Yangilandi", Toast.LENGTH_SHORT).show();

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }

        });


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
        builder.setTitle(ismi + "ni o'chirish");
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