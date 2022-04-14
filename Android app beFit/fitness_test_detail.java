package com.example.befitgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.befitgroupproject.databinding.ActivityFitnessTestDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class fitness_test_detail extends AppCompatActivity {

    private ActivityFitnessTestDetailBinding binding;
    private MyViewModel myViewModel;
    private String name;
    private String phoneNumber;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fitness_test_detail);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
        Intent main = getIntent();
        phoneNumber = main.getStringExtra("phoneNumber");
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");
        //set user name on top of the screen.
        Query checkPhone = reference.orderByChild("phoneNumber").equalTo(phoneNumber);
        checkPhone.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    name = dataSnapshot.child(phoneNumber).child("name").getValue(String.class);
                    binding.textView3.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
                Toast.makeText(fitness_test_detail.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        binding.textViewDetail.setVisibility(View.INVISIBLE);
        binding.imageButtonBack.setVisibility(View.INVISIBLE);
        binding.imageButtonBMIinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewDetail.setVisibility(View.VISIBLE);
                binding.textViewDetail.setText("Body mass index (BMI) is a measure of body fat based" +
                        " on height and weight that applies to adult men and women.  " +
                        "Enter your weight and height using standard or metric measures. " +
                        "Select \"Compute BMI\" and your BMI will appear below."
                );
                binding.imageButtonBack.setVisibility(View.VISIBLE);
            }
        });
        binding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewDetail.setVisibility(View.INVISIBLE);
                binding.imageButtonBack.setVisibility(View.INVISIBLE);
            }
        });

        binding.imageButtonCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewDetail.setVisibility(View.VISIBLE);
                binding.textViewDetail.setText("To perform the core strength and stability test, get into a plank position, resting your forearms on the ground. Hold this position for 60 seconds, then lift your right arm off the ground for 15 seconds. Return that arm to the ground, then your left arm for the same amount of time.\n" +
                        "\n" +
                        "Next, move on to your legs. First, lift your right leg for 15 seconds. Return it to the ground and then lift your left leg for 15 seconds. Return it to the ground.\n" +
                        "\n" +
                        "Next, lift both the right arm and left leg at the same time, holding for 15 seconds. Return them to the ground and lift your left arm and right leg for 15 seconds. Lower those back to the ground and hold the initial plank position for 30 seconds."
                );
                binding.imageButtonBack.setVisibility(View.VISIBLE);
            }
        });
        binding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewDetail.setVisibility(View.INVISIBLE);
                binding.imageButtonBack.setVisibility(View.INVISIBLE);
            }
        });

        binding.imageButtonPushup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewDetail.setVisibility(View.VISIBLE);
                binding.textViewDetail.setText("To perform the push-up test, begin in a push-up position before " +
                        "lowering your body until your elbows are bent at 90-degree angles. " +
                        "Straighten the arms and return to the starting position. This counts as one repetition." +
                        "" +
                        "Do as many push-ups as you can while still keeping good form (your toes, hips," +
                        " and shoulders should all be in a straight line). " +
                        "Records the number you were able to complete.");
                binding.imageButtonBack.setVisibility(View.VISIBLE);
            }
        });
        binding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewDetail.setVisibility(View.INVISIBLE);
                binding.imageButtonBack.setVisibility(View.INVISIBLE);
            }
        });

        binding.imageButtonRunInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewDetail.setVisibility(View.VISIBLE);
                binding.textViewDetail.setText("This test should be performed after a thorough warm-up. " +
                        "It's also best performed on a track so you can accurately measure the distance " +
                        "(or along a road or trail where you can use GPS).\n" +
                        "\n" +
                        "To do it, run for 12 minutes. Next, plug the distance you ran into one of " +
                        "these formulas to get an estimate of your VO2 Max.");
                binding.imageButtonBack.setVisibility(View.VISIBLE);
            }
        });
        binding.imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewDetail.setVisibility(View.INVISIBLE);
                binding.imageButtonBack.setVisibility(View.INVISIBLE);
            }
        });

        binding.buttonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fitness_test_detail.this, get_plan.class);
                intent.putExtra("weight", String.valueOf(binding.editTextBMI2.getText()));
                intent.putExtra("height", String.valueOf(binding.editTextBMI.getText()));
                intent.putExtra("core strength", String.valueOf(binding.editTextCore.getText()));
                intent.putExtra("Pushup", String.valueOf(binding.editTextTextPersonName2.getText()));
                intent.putExtra("run", String.valueOf(binding.editTextTextRun.getText()));
                intent.putExtra("phoneNumber", phoneNumber);

                startActivity(intent);
            }
        });

        binding.buttonPhysicalProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fitness_test_detail.this, fitness_test_history.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });

        binding.imageButtonLaunch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fitness_test_detail.this, LaunchActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });

        binding.imageButtonHeart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fitness_test_detail.this, HeartRateActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });

        binding.imageButtonSetting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fitness_test_detail.this, SettingActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });

    }

    public void onClickBadge(View view){
        Intent intent = new Intent(fitness_test_detail.this, BadgeActivity.class);
        intent.putExtra("phoneNumber", phoneNumber);
        startActivity(intent);
    }
}


