package com.example.befitgroupproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText emailInput;
    private EditText passWordInput;
    private Button buttonLogin;
    private EditText name;
    private EditText dob;
    private EditText phone;
    private EditText email;
    private EditText passWord;
    private TextView gender;
    private Button buttonM;
    private Button buttonF;
    private Button buttonLogin2;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private MyViewModel myViewModel;
    private Clients clients = new Clients();
    private String emailA;
    private String password;
    private ImageView logo;
    private TextView slogan;
    private Button register;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailInput = findViewById(R.id.editTextTextPersonName3);
        passWordInput = findViewById(R.id.editTextTextPersonName4);
        passWordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        buttonLogin = findViewById(R.id.button_login);
        name = findViewById(R.id.editTextTextPersonName);
        dob = findViewById(R.id.editTextDate);
        phone = findViewById(R.id.editTextPhone);
        gender = findViewById(R.id.textView15);
        buttonM = findViewById(R.id.button2);
        buttonF = findViewById(R.id.button3);
        email = findViewById(R.id.editTextTextPersonName6);
        passWord = findViewById(R.id.editTextTextPersonName7);
        buttonLogin2 = findViewById(R.id.button4);
        logo = findViewById(R.id.imageView);
        slogan = findViewById(R.id.textView27);
        register = findViewById(R.id.button_login2);

        name.setVisibility(View.INVISIBLE);
        dob.setVisibility(View.INVISIBLE);
        phone.setVisibility(View.INVISIBLE);
        gender.setVisibility(View.INVISIBLE);
        buttonM.setVisibility(View.INVISIBLE);
        buttonF.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);
        passWord.setVisibility(View.INVISIBLE);
        buttonLogin2.setVisibility(View.INVISIBLE);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");
        clients.userProfile = new userProfile();

// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                emailA = String.valueOf(emailInput.getText()).trim();
                password = String.valueOf(passWordInput.getText());
                if (emailA.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please provide your email address.",
                            Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please provide your password.",
                            Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "Password has to be at least 6 characters.",
                            Toast.LENGTH_SHORT).show();
                }
                //check if user enter correct userId format
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailA).matches()) {
                    Toast.makeText(MainActivity.this, "Wrong email address format, please reenter.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(emailA, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "User exists, please log in.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                addUserPermission();
                            }
                        }
                    });
                }
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailA = String.valueOf(emailInput.getText()).trim();
                password = String.valueOf(passWordInput.getText());
                if (emailA.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please provide your email address.",
                            Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please provide your password.",
                            Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "Password has to be at least 6 characters.",
                            Toast.LENGTH_SHORT).show();
                }
                //check if user enter correct userId format
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailA).matches()) {
                    Toast.makeText(MainActivity.this, "Wrong email address format, please reenter.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(emailA, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if(user.isEmailVerified()) {
                                    Intent intent = new Intent(MainActivity.this, LaunchActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    user.sendEmailVerification();
                                    Toast.makeText(MainActivity.this, "An email has been sent to your email account," +
                                                    " please verify your email account.",
                                            Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Fail to log in, please reenter credentials or register.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }


    private void addUserPermission() {
        new AlertDialog.Builder(this).setTitle("New user").
                setMessage("Welcome to beFit, you will be registered as a new user. " +
                        "If it is a mistake, please reenter your information.").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name.setVisibility(View.VISIBLE);
                        dob.setVisibility(View.VISIBLE);
                        phone.setVisibility(View.VISIBLE);
                        gender.setVisibility(View.VISIBLE);
                        buttonM.setVisibility(View.VISIBLE);
                        buttonF.setVisibility(View.VISIBLE);
                        email.setVisibility(View.VISIBLE);
                        passWord.setVisibility(View.VISIBLE);
                        buttonLogin2.setVisibility(View.VISIBLE);
                        buttonLogin.setVisibility(View.INVISIBLE);
                        emailInput.setVisibility(View.INVISIBLE);
                        passWordInput.setVisibility(View.INVISIBLE);
                        logo.setVisibility(View.INVISIBLE);
                        slogan.setVisibility(View.INVISIBLE);
                        register.setVisibility(View.INVISIBLE);

                        buttonM.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clients.userProfile.gender = "Male";
                            }
                        });
                        buttonF.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clients.userProfile.gender = "Female";
                            }
                        });
                        buttonLogin2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clients.userProfile.dob = dob.getText().toString();
                                clients.userProfile.name = String.valueOf(name.getText());
                                clients.userProfile.phoneNumber = String.valueOf(phone.getText());                                                             // text.setText(date);
                                clients.userProfile.email = email.getText().toString();
                                clients.userProfile.password = passWord.getText().toString();

                                if (clients.userProfile.email.isEmpty()) {
                                    Toast.makeText(MainActivity.this, "Email is required.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                if (clients.userProfile.name != "" && clients.userProfile.phoneNumber
                                        != null && clients.userProfile.gender != null
                                        && clients.userProfile.dob != null && clients.userProfile.email != null
                                        && clients.userProfile.password != null
                                ) {
                                    if (clients.userProfile.dob.length() != 8) {
                                        Toast.makeText(MainActivity.this, "Wrong date format, please enter mm/dd/yyyy",
                                                Toast.LENGTH_SHORT).show();
                                    } else if (!Patterns.EMAIL_ADDRESS.matcher(clients.userProfile.email).matches()) {
                                        Toast.makeText(MainActivity.this, "Wrong userId address format, please reenter.",
                                                Toast.LENGTH_SHORT).show();
                                    } else if (clients.userProfile.password.length() < 6) {
                                        Toast.makeText(MainActivity.this, "Pass word has to be at least 6 characters long.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    //Add user userId authentication here.
                                    else {
                                        mAuth.createUserWithEmailAndPassword(clients.userProfile.email, clients.userProfile.password)
                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                                                    setValue(clients.userProfile);
                                                        }
                                                    }

                                                });
                                        Toast.makeText(MainActivity.this, "New User registered, going back to log in page.",
                                                Toast.LENGTH_SHORT).show();

                                        name.setVisibility(View.INVISIBLE);
                                        dob.setVisibility(View.INVISIBLE);
                                        phone.setVisibility(View.INVISIBLE);
                                        gender.setVisibility(View.INVISIBLE);
                                        buttonM.setVisibility(View.INVISIBLE);
                                        buttonF.setVisibility(View.INVISIBLE);
                                        email.setVisibility(View.INVISIBLE);
                                        passWord.setVisibility(View.INVISIBLE);
                                        buttonLogin2.setVisibility(View.INVISIBLE);
                                        buttonLogin.setVisibility(View.VISIBLE);
                                        emailInput.setVisibility(View.VISIBLE);
                                        passWordInput.setVisibility(View.VISIBLE);
                                        logo.setVisibility(View.VISIBLE);
                                        slogan.setVisibility(View.VISIBLE);
                                        register.setVisibility(View.VISIBLE);


                                        /*Intent intent = new Intent(MainActivity.this, LaunchActivity.class);
                                        intent.putExtra("userId", clients.userProfile.userId);
                                        startActivity(intent);*/
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Please fill in all the information",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        });


                    }
                }).
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }


    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}

