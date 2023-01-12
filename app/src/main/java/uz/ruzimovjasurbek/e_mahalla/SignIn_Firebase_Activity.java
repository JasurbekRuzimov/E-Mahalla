package uz.ruzimovjasurbek.e_mahalla;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Objects;

import uz.ruzimovjasurbek.e_mahalla.databinding.ActivitySignInFirebaseBinding;

public class SignIn_Firebase_Activity extends AppCompatActivity {

    ActivitySignInFirebaseBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInFirebaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        binding.signInFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Objects.requireNonNull(binding.emailFairbase.getEditText()).getText().toString();
                if (email.isEmpty()) {
                    binding.emailFairbase.setError("Emailni kiriting !");
                    return;
                }
                String password = Objects.requireNonNull(binding.passwordFairbase.getEditText()).getText().toString();
                if (password.isEmpty()) {
                    binding.passwordFairbase.setError("Parolni kiriting !");
                    return;
                }

                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if (Objects.requireNonNull(authResult.getUser()).isEmailVerified()) {
                                    startActivity(new Intent(SignIn_Firebase_Activity.this, MainActivity.class));
                                    progressDialog.cancel();
                                } else {
                                    startActivity(new Intent(SignIn_Firebase_Activity.this, MainActivity.class));
                                    progressDialog.cancel();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(SignIn_Firebase_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        binding.ForgotPasswordFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Objects.requireNonNull(binding.emailFairbase.getEditText()).getText().toString();
                if (email.isEmpty()) {
                    progressDialog.cancel();
                    progressDialog.setTitle("Iltimos emailni kiriting, chunki emailingizga parolni tiklash uchun link yuboramiz !");
                    progressDialog.show();
                    return;
                }
                progressDialog.show();
                progressDialog.setTitle("Emailga parolni tiklash uchun xabar yuborilmoqda");
                progressDialog.show();
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.cancel();
                                Toast.makeText(SignIn_Firebase_Activity.this, "Emailga xat yuborildi ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(SignIn_Firebase_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


        binding.GoToSignUpFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn_Firebase_Activity.this, SignUp_Firebase_Activity.class));
                finish();
            }
        });

    }
}