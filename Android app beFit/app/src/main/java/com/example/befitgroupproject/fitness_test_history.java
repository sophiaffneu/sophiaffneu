package com.example.befitgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.befitgroupproject.databinding.ActivityFitnessTestHistoryBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fitness_test_history extends AppCompatActivity {
    private ActivityFitnessTestHistoryBinding binding;
    private MyViewModel myViewModel;
    private String userId;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private FitnessTestHistoryAdapter adapter;
    private FirebaseUser user;
    List<FitnessTestResult> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_test_history);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fitness_test_history);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(this);
        binding.button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_0 = new Intent(fitness_test_history.this,fitness_test_detail.class);
                startActivity(intent_0);
            }
        });
        rootNode = FirebaseDatabase.getInstance();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FitnessTestHistoryAdapter(this, resultList);
        binding.recyclerView.setAdapter(adapter);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        getFitnessRecord();
    }

    public void getFitnessRecord() {
        reference = rootNode.getReference("Users").child(userId).child("FitnessTestHistory");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        FitnessTestResult result = ds.getValue(FitnessTestResult.class);
                        resultList.add(result);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
                Toast.makeText(fitness_test_history.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}