package uz.ruzimovjasurbek.e_mahalla;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Natijalar_Activity extends AppCompatActivity {
    LinearLayout backHome;
    DBHelper dbHelper;
    ArrayList<String> user_id, ismi, familiyasi, otasining_ismi, mahallasi, jinsi, tugilgan_vaqti;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    ImageView delete_all_data, empty;
    TextView empty_text;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natijalar);

        empty = findViewById(R.id.empty);
        empty_text = findViewById(R.id.empty_text);


        delete_all_data = findViewById(R.id.delete_all_data);
        delete_all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Natijalar_Activity.this);
                builder.setTitle("Barcha ma'lumotlarni o'chirishni xohlaysizmi?");
                builder.setMessage("Bu amalni qayta tiklash imkoni yo'q");
                builder.setPositiveButton("Ha", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper myDB = new DBHelper(Natijalar_Activity.this);
                        myDB.deleteAllData();
                        //Refresh Activity
                        Intent intent = new Intent(Natijalar_Activity.this, Natijalar_Activity.class);
                        startActivity(intent);
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

        });


        backHome = findViewById(R.id.backHome);
        backHome.setOnClickListener(v -> finish());

        dbHelper = new DBHelper(Natijalar_Activity.this);
        user_id = new ArrayList<>();
        ismi = new ArrayList<>();
        familiyasi = new ArrayList<>();
        otasining_ismi = new ArrayList<>();
        mahallasi = new ArrayList<>();
        jinsi = new ArrayList<>();
        tugilgan_vaqti = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(Natijalar_Activity.this, this, user_id, ismi, familiyasi, otasining_ismi, mahallasi, jinsi, tugilgan_vaqti);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Natijalar_Activity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            empty.setVisibility(View.VISIBLE);
            empty_text.setVisibility(View.VISIBLE);

        } else {
            while (cursor.moveToNext()) {
                user_id.add(cursor.getString(0));
                ismi.add(cursor.getString(1));
                familiyasi.add(cursor.getString(2));
                otasining_ismi.add(cursor.getString(3));
                mahallasi.add(cursor.getString(4));
                jinsi.add(cursor.getString(5));
                tugilgan_vaqti.add(cursor.getString(6));
            }
            empty.setVisibility(View.GONE);
            empty_text.setVisibility(View.GONE);
        }
    }


}
