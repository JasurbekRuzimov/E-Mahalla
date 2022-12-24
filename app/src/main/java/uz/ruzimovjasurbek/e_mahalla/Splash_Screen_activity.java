package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import uz.ruzimovjasurbek.e_mahalla.ui.login.LoginActivity;

public class Splash_Screen_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splash_Screen_activity.this, Sign_Up_Activity.class));
            finish();
        }, 1500);
    }
}