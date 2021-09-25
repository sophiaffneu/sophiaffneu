package com.example.befitgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.befitgroupproject.databinding.ActivityGetPlanBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class get_plan extends AppCompatActivity {
    private ActivityGetPlanBinding bindingR;
    private MyViewModel myViewModel;
    protected String weight = "";
    protected String height = "";
    protected String coreStrength = "";
    protected String pushUp = "";
    protected String running = "";
    protected String[] planChoice = new String[2];
    private FirebaseDatabase rootNode;
    private DatabaseReference mDatabaseUser;
    protected String userId;
    private Clients user = new Clients();
    private String gender;
    private String dob;
    private int age;
    boolean cancelled = false;
    private FirebaseUser user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingR = DataBindingUtil.setContentView(this, R.layout.activity_get_plan);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        bindingR.setData(myViewModel);
        bindingR.setLifecycleOwner(this);
        rootNode = FirebaseDatabase.getInstance();
        mDatabaseUser = rootNode.getReference("Users");
        user.fitnessTestResult = new FitnessTestResult();
        Intent testResult = getIntent();
        weight = testResult.getStringExtra("weight");
        height = testResult.getStringExtra("height");
        coreStrength = testResult.getStringExtra("core strength");
        pushUp = testResult.getStringExtra("Pushup");
        running = testResult.getStringExtra("run");
        user1 = FirebaseAuth.getInstance().getCurrentUser();
        userId = user1.getUid();

        bindingR.button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(get_plan.this, fitness_test_detail.class);

                startActivity(intent);
            }
        });
        //get user name and gender from database;
         mDatabaseUser.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    gender = dataSnapshot.child("gender").getValue(String.class);
                    dob = dataSnapshot.child("dob").getValue(String.class);
                    age = setAge(dob);
                    setBMIText();
                    setCoreStrength();
                    setPushUp();
                    setRunning();
                    if ((weight != null && height != null) || coreStrength != null || pushUp != null
                            || running != null) {
                        mDatabaseUser.child(userId).child("FitnessTestHistory").
                                child(user.fitnessTestResult.date).setValue(user.fitnessTestResult);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
                Toast.makeText(get_plan.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
//choose plan for user.
        bindingR.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (planChoice[0] == null || planChoice[1] == null) {
                    bindingR.radioGroup2.clearCheck();
                    bindingR.radioGroup1.clearCheck();
                    Toast.makeText(getApplicationContext(), "Please select preferences again", Toast.LENGTH_LONG).show();
                } else {
                    String intensity = planChoice[0];
                    String time = planChoice[1];
                    new AlertDialog.Builder(get_plan.this).setTitle("We have a plan selected for you!").
                            setMessage("We selected a plan for you base on your fitness test and " +
                                    "your preference, click OK to get started!").
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (time.equals("25") && intensity.equals("low")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan1");
                                    } else if (time.equals("25") && intensity.equals("medium")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan2");
                                    } else if (time.equals("25") && intensity.equals("high")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan3");
                                    } else if (time.equals("35") && intensity.equals("low")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan4");
                                    } else if (time.equals("35") && intensity.equals("medium")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan5");
                                    } else if (time.equals("35") && intensity.equals("high")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan6");
                                    } else if (time.equals("45") && intensity.equals("low")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan7");
                                    } else if (time.equals("45") && intensity.equals("medium")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan8");
                                    } else if (time.equals("45") && intensity.equals("high")) {
                                        mDatabaseUser.child(userId).child("Current Plan").setValue("Plan9");
                                    }
                                    bindingR.radioGroup2.clearCheck();
                                    bindingR.radioGroup1.clearCheck();
                                    //Jump to punch card page
                                    Intent intent = new Intent(get_plan.this, LaunchActivity.class);
                                    startActivity(intent);
                                }
                            }).
                            setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "Plan selection cancelled.", Toast.LENGTH_LONG).show();
                                    bindingR.radioGroup2.clearCheck();
                                    bindingR.radioGroup1.clearCheck();
                                    dialog.dismiss();
                                }
                            }).create().show();

                }
            }
        });
    }

    public int setAge(String dob) {
        if (dob != null && !dob.equals("")) {
            String year = dob.substring(4);
            int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);
            int age = yearCurrent - Integer.parseInt(year);
        }
        return age;
    }

    public void setBMIText() {
        if(!isNumeric(weight) || !isNumeric(height)){
            Toast.makeText(get_plan.this, "Please enter numbers only",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            if (weight != null && !weight.equals("") && height != null && !height.equals("")) {
                int weight = Integer.parseInt(this.weight);
                int height = Integer.parseInt(this.height);
                int BMI = weight * 703 / (height * height);

                if (BMI >= 25) {
                    bindingR.textView4.setText("Your BMI is " + BMI + ", you are overweight.");
                    user.fitnessTestResult.BMI = "Your BMI is " + BMI + ", you are overweight.";
                } else if (BMI < 25 || BMI >= 18.5) {
                    bindingR.textView4.setText("Your BMI is " + BMI + ", your weight is normal.");
                    user.fitnessTestResult.BMI = "Your BMI is " + BMI + ", your weight is normal.";
                } else {
                    bindingR.textView4.setText("Your BMI is " + BMI + ", you are underweight.");
                    user.fitnessTestResult.BMI = "Your BMI is " + BMI + ", you are underweight.";
                }
            } else {
                bindingR.textView4.setText("Please input weight and height.");
                user.fitnessTestResult.BMI = "no test result";
            }
        }
    }

    public void setCoreStrength() {
        if (!isNumeric(coreStrength)) {
            Toast.makeText(get_plan.this, "Please enter numbers only",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (coreStrength != null && !coreStrength.equals("")) {
                int coreStrength = Integer.parseInt(this.coreStrength);
                if (coreStrength > 80) {
                    bindingR.textView2.setText("You have good core strength.");
                    user.fitnessTestResult.coreStrength = "You completed " + coreStrength + "% of the test, " +
                            " You have good core strength.";
                } else {
                    bindingR.textView2.setText("your core strength needs improvement.");
                    user.fitnessTestResult.coreStrength = "You completed " + coreStrength + "% of the test," +
                            " your core strength needs improvement.";
                }
            } else {
                bindingR.textView2.setText("Please finish the core strength test.");
                user.fitnessTestResult.coreStrength = "no test result";
            }
        }
    }

    public void setPushUp() {
        int pushUpNumber = 0;
        if(!isNumeric(coreStrength)){
            Toast.makeText(get_plan.this, "Please enter numbers only",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            if (pushUp != null && !pushUp.equals("")) {
                pushUpNumber = Integer.parseInt(this.pushUp);
            } else {
                bindingR.textView5.setText("Please finish the push-up test.");
                user.fitnessTestResult.pushUp = "no test result";
            }

            if (pushUpNumber != 0 && gender.equals("Male")) {
                if (age < 29) {
                    if (pushUpNumber >= 54) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 45 || pushUpNumber < 54) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 35 || pushUpNumber < 44) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                } else if (age < 39 || age >= 30) {
                    if (pushUpNumber >= 44) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 35 || pushUpNumber < 44) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 24 || pushUpNumber < 34) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                } else if (age < 49 || age >= 40) {
                    if (pushUpNumber >= 39) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 30 || pushUpNumber < 39) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 20 || pushUpNumber < 29) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                } else if (age < 59 || age >= 50) {
                    if (pushUpNumber >= 34) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 25 || pushUpNumber < 34) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 15 || pushUpNumber < 24) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                } else {
                    if (pushUpNumber >= 29) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 20 || pushUpNumber < 29) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 10 || pushUpNumber < 19) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                }
            } else if (pushUpNumber != 0 && gender.equals("Female")) {
                if (age < 29) {
                    if (pushUpNumber >= 48) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 34 || pushUpNumber < 48) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 17 || pushUpNumber < 33) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                } else if (age < 39 || age >= 30) {
                    if (pushUpNumber >= 39) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 25 || pushUpNumber < 39) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 12 || pushUpNumber < 24) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                } else if (age < 49 || age >= 40) {
                    if (pushUpNumber >= 34) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 20 || pushUpNumber < 34) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 8 || pushUpNumber < 19) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                } else if (age < 59 || age >= 50) {
                    if (pushUpNumber >= 29) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 15 || pushUpNumber < 29) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 6 || pushUpNumber < 14) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                } else {
                    if (pushUpNumber >= 19) {
                        bindingR.textView5.setText("Excellent upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Excellent upper body strength.";
                    } else if (pushUpNumber >= 5 || pushUpNumber < 19) {
                        bindingR.textView5.setText("Good upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Good upper body strength.";
                    } else if (pushUpNumber >= 3 || pushUpNumber < 4) {
                        bindingR.textView5.setText("Average upper body strength.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds, Average upper body strength.";
                    } else {
                        bindingR.textView5.setText("Poor upper body strength, you need more practice.");
                        user.fitnessTestResult.pushUp = "You did " + pushUp + " push-ups in 30 seconds," +
                                " Poor upper body strength, you need more practice.";
                    }
                }
            }
        }
    }


    public void setRunning() {
        double runMeterNumber = 0;
        if(!isNumeric(coreStrength)){
            Toast.makeText(get_plan.this, "Please enter numbers only",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            if (running != null && !running.equals("")) {
                runMeterNumber = Double.parseDouble(this.running) * 1609;

            } else {
                bindingR.textView6.setText("Please finish the 12 minutes running test.");
                user.fitnessTestResult.running = "no test result";
            }
            if (runMeterNumber != 0 && gender.equals("Male")) {
                if (age < 29) {
                    if (runMeterNumber >= 2800) {
                        bindingR.textView6.setText("Excellent cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Excellent cardiovascular endurance.";
                    } else if (runMeterNumber >= 2400 || runMeterNumber < 2800) {
                        bindingR.textView6.setText("Above average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Above average cardiovascular endurance.";
                    } else if (runMeterNumber >= 2200 || runMeterNumber < 2399) {
                        bindingR.textView6.setText("Average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1600 || runMeterNumber < 2199) {
                        bindingR.textView6.setText("Below average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, below average cardiovascular endurance.";
                    } else {
                        bindingR.textView6.setText("Poor cardiovascular endurance, you need more practice.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, poor cardiovascular endurance.";
                    }
                } else if (age < 39 || age >= 30) {
                    if (runMeterNumber >= 2700) {
                        bindingR.textView6.setText("Excellent cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Excellent cardiovascular endurance.";
                    } else if (runMeterNumber >= 2300 || runMeterNumber < 2700) {
                        bindingR.textView6.setText("Above average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Above average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1900 || runMeterNumber < 2299) {
                        bindingR.textView6.setText("Average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1500 || runMeterNumber < 1999) {
                        bindingR.textView6.setText("Below average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, below average cardiovascular endurance.";
                    } else {
                        bindingR.textView6.setText("Poor cardiovascular endurance, you need more practice.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, poor cardiovascular endurance.";
                    }
                } else if (age < 49 || age >= 40) {
                    if (runMeterNumber >= 2500) {
                        bindingR.textView6.setText("Excellent cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Excellent cardiovascular endurance.";
                    } else if (runMeterNumber >= 2100 || runMeterNumber < 2500) {
                        bindingR.textView6.setText("Above average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Above average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1700 || runMeterNumber < 2099) {
                        bindingR.textView6.setText("Average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1400 || runMeterNumber < 1699) {
                        bindingR.textView6.setText("Below average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, below average cardiovascular endurance.";
                    } else {
                        bindingR.textView6.setText("Poor cardiovascular endurance, you need more practice.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, poor cardiovascular endurance.";
                    }
                } else {
                    if (runMeterNumber >= 2400) {
                        bindingR.textView6.setText("Excellent cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Excellent cardiovascular endurance.";
                    } else if (runMeterNumber >= 2000 || runMeterNumber < 2400) {
                        bindingR.textView6.setText("Above average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Above average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1600 || runMeterNumber < 1999) {
                        bindingR.textView6.setText("Average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1300 || runMeterNumber < 1599) {
                        bindingR.textView6.setText("Below average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, below average cardiovascular endurance.";
                    } else {
                        bindingR.textView6.setText("Poor cardiovascular endurance, you need more practice.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, poor cardiovascular endurance.";
                    }
                }
            } else if (runMeterNumber != 0 && gender.equals("Female")) {

                if (age < 29) {
                    if (runMeterNumber >= 2700) {
                        bindingR.textView6.setText("Excellent cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Excellent cardiovascular endurance.";
                    } else if (runMeterNumber >= 2200 || runMeterNumber < 2700) {
                        bindingR.textView6.setText("Above average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Above average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1800 || runMeterNumber < 2199) {
                        bindingR.textView6.setText("Average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1500 || runMeterNumber < 1799) {
                        bindingR.textView6.setText("Below average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, below average cardiovascular endurance.";
                    } else {
                        bindingR.textView6.setText("Poor cardiovascular endurance, you need more practice.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, poor cardiovascular endurance.";
                    }
                } else if (age < 39 || age >= 30) {
                    if (runMeterNumber >= 2500) {
                        bindingR.textView6.setText("Excellent cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Excellent cardiovascular endurance.";
                    } else if (runMeterNumber >= 2000 || runMeterNumber < 2500) {
                        bindingR.textView6.setText("Above average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Above average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1700 || runMeterNumber < 1999) {
                        bindingR.textView6.setText("Average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1400 || runMeterNumber < 1699) {
                        bindingR.textView6.setText("Below average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, below average cardiovascular endurance.";
                    } else {
                        bindingR.textView6.setText("Poor cardiovascular endurance, you need more practice.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, poor cardiovascular endurance.";
                    }
                } else if (age < 49 || age >= 40) {
                    if (runMeterNumber >= 2300) {
                        bindingR.textView6.setText("Excellent cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Excellent cardiovascular endurance.";
                    } else if (runMeterNumber >= 1900 || runMeterNumber < 2300) {
                        bindingR.textView6.setText("Above average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Above average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1500 || runMeterNumber < 1899) {
                        bindingR.textView6.setText("Average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1200 || runMeterNumber < 1499) {
                        bindingR.textView6.setText("Below average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, below average cardiovascular endurance.";
                    } else {
                        bindingR.textView6.setText("Poor cardiovascular endurance, you need more practice.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, poor cardiovascular endurance.";
                    }
                } else {
                    if (runMeterNumber >= 2200) {
                        bindingR.textView6.setText("Excellent cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Excellent cardiovascular endurance.";
                    } else if (runMeterNumber >= 1700 || runMeterNumber < 2200) {
                        bindingR.textView6.setText("Above average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Above average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1400 || runMeterNumber < 1699) {
                        bindingR.textView6.setText("Average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, Average cardiovascular endurance.";
                    } else if (runMeterNumber >= 1100 || runMeterNumber < 1399) {
                        bindingR.textView6.setText("Below average cardiovascular endurance.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, below average cardiovascular endurance.";
                    } else {
                        bindingR.textView6.setText("Poor cardiovascular endurance, you need more practice.");
                        user.fitnessTestResult.running = "You ran " + running + " miles in 12 minutes, poor cardiovascular endurance.";
                    }
                }
            }
        }

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {

            case R.id.radioButton4:
                if (checked)
                    planChoice[0] = ("high");
                break;
            case R.id.radioButton5:
                if (checked)
                    planChoice[0] = ("medium");
                break;
            case R.id.radioButton6:
                if (checked)
                    planChoice[0] =("low");
                break;
            case R.id.radioButton7:
                if (checked)
                    planChoice[1] =("25");
                break;
            case R.id.radioButton8:
                if (checked)
                    planChoice[1] =("35");
                break;
            case R.id.radioButton9:
                if (checked)
                    planChoice[1] = ("45");
                break;
        }
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

