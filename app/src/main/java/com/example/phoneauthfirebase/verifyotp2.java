package com.example.phoneauthfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verifyotp2 extends AppCompatActivity {

    TextView textView;
    EditText inputno1, inputno2, inputno3, inputno4, inputno5, inputno6;
    Button button;
    ProgressBar progressBarverifybtnotp;
    String getotpbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp2);


        inputno1 = findViewById(R.id.inputOtp1);
        inputno2 = findViewById(R.id.inputOtp2);
        inputno3 = findViewById(R.id.inputOtp3);
        inputno4 = findViewById(R.id.inputOtp4);
        inputno5 = findViewById(R.id.inputOtp5);
        inputno6 = findViewById(R.id.inputOtp6);


        textView = findViewById(R.id.showTxtmobilenumber);

        button = findViewById(R.id.verifyotp_btn);
        textView.setText(String.format("+91-%s", getIntent().getStringExtra("mobile")));

        getotpbackend = getIntent().getStringExtra("backendotp");

        progressBarverifybtnotp = findViewById(R.id.verify_otp_pbar);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!inputno1.getText().toString().trim().isEmpty() && !inputno2.getText().toString()
                        .trim().isEmpty() && !inputno3.getText().toString().trim().isEmpty() &&
                        !inputno4.getText().toString().trim().isEmpty() && !inputno5.getText().toString().trim()
                        .isEmpty() && !inputno6.getText().toString().trim().isEmpty()) {
                    String entrecodeotp = inputno1.getText().toString() +
                            inputno2.getText().toString() +
                            inputno3.getText().toString() +
                            inputno4.getText().toString() +
                            inputno5.getText().toString() +
                            inputno6.getText().toString();

                    if (getotpbackend == null) {

                        progressBarverifybtnotp.setVisibility(View.GONE);
                        progressBarverifybtnotp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend, entrecodeotp);

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBarverifybtnotp.setVisibility(View.GONE);
                                        progressBarverifybtnotp.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(verifyotp2.this, "Please enter Correct Otp", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    } else {
                        Toast.makeText(verifyotp2.this, "Please check your Internet connection", Toast.LENGTH_SHORT).show();
                    }

                    // Toast.makeText(verifyotp2.this, "Otp Verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(verifyotp2.this, "Please Enter All Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        numberotpmove();

        TextView resendlabel = findViewById(R.id.resendOtp);

        resendlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber
                        ("+91" + getIntent().getStringExtra("mobile"), 60, TimeUnit.SECONDS, verifyotp2.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        Toast.makeText(verifyotp2.this, "Error , Please check your internet connection", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        getotpbackend = newbackendotp;
                                        Toast.makeText(verifyotp2.this, "Otp sent Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });

            }
        });
    }

    private void numberotpmove() {

        inputno1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (!s.toString().trim().isEmpty()) {
                    inputno2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputno2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (!s.toString().trim().isEmpty()) {
                    inputno3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputno3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (!s.toString().trim().isEmpty()) {
                    inputno4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputno4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (!s.toString().trim().isEmpty()) {
                    inputno5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputno5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (!s.toString().trim().isEmpty()) {
                    inputno6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}