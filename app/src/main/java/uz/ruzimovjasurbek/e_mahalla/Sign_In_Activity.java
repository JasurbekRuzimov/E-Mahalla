package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Sign_In_Activity extends AppCompatActivity {
    TextInputLayout login, password;
    Button sign_in;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        sign_in = findViewById(R.id.sign_In);
        DBHelper DB = new DBHelper(this);

        sign_in.setOnClickListener(v -> {
            String user = login.getEditText().getText().toString();
            String pass = password.getEditText().getText().toString();

            if (user.equals("") || pass.equals(""))
                Toast.makeText(Sign_In_Activity.this, "Iltimos, barcha maydonlarni to'ldiring !", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if (checkuserpass == true) {
                    Toast.makeText(Sign_In_Activity.this, "Tizimga muvaffaqiyatli kirdingiz !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Sign_In_Activity.this, "Hisob ma'lumotlari yaroqsiz", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}