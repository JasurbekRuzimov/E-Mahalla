package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Sign_Up_Activity extends AppCompatActivity {
    TextInputLayout login, password, confirm_password;
    Button sign_up;
    TextView sign_in;
    DBHelper DB;

    public boolean isBackPressed = false;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login = findViewById(R.id.loginId);
        password = findViewById(R.id.passwordId);
        confirm_password = findViewById(R.id.ConfirmPasswordId);
        sign_up = findViewById(R.id.signUpButton);
        sign_in = findViewById(R.id.alreadyHaveAnAccount);
        DB = new DBHelper(this);


        sign_up.setOnClickListener(v -> {
            String user = login.getEditText().getText().toString();
            String pass = password.getEditText().getText().toString();
            String confirm_pass = confirm_password.getEditText().getText().toString();

            if (user.equals("") || pass.equals("") || confirm_pass.equals("")) {
                Toast.makeText(Sign_Up_Activity.this, "Iltimos, barcha maydonlarni to'ldiring !", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.equals(confirm_pass)) {
                    Boolean checkuser = DB.checkusername(user);
                    if (checkuser == false) {
                        Boolean insert = DB.insertData(user, pass);
                        if (insert == true) {
                            Toast.makeText(Sign_Up_Activity.this, "Ro'yxatdan o'tdingiz", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Sign_In_Activity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Sign_Up_Activity.this, "Ro‘yxatdan o‘tish amalga oshmadi", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Sign_Up_Activity.this, "Foydalanuvchi allaqachon mavjud! Iltimos tizimga kiring", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Sign_Up_Activity.this, "Parol mos emas !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign_in.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Sign_In_Activity.class);
            startActivity(intent);
        });


    }

    public void onBackPressed() {
        if (isBackPressed) {
            super.onBackPressed();
        } else {
            isBackPressed = true;
            Toast.makeText(this, " Chiqish uchun 🔙 ni ikki marta bosing ", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> isBackPressed = false, 2000);
        }
    }
    }
