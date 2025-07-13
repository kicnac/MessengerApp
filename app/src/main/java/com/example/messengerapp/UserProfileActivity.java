package com.example.messengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {
    private ImageView ivProfileImage;
    private TextView tvUserName;
    private TextView tvPhoneNumber;
    private Button btnCall;
    private Button btnMessage;
    private Button btnVideoCall;

    private String userId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Get data from intent
        Intent intent = getIntent();
        userId = intent.getStringExtra("user_id");
        userName = intent.getStringExtra("user_name");

        // Setup ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();
        setupUserData();
        setupClickListeners();
    }

    private void initViews() {
        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvUserName = findViewById(R.id.tvUserName);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        btnCall = findViewById(R.id.btnCall);
        btnMessage = findViewById(R.id.btnMessage);
        btnVideoCall = findViewById(R.id.btnVideoCall);
    }

    private void setupUserData() {
        // Set user data
        tvUserName.setText(userName != null ? userName : "Unknown User");
        
        // Generate mock phone number based on user ID
        String phoneNumber = generateMockPhoneNumber(userId);
        tvPhoneNumber.setText(phoneNumber);
        
        // Set profile image
        ivProfileImage.setImageResource(R.drawable.efault_chat_img);
    }

    private String generateMockPhoneNumber(String userId) {
        if (userId == null) return "+1 234 567 8900";
        
        // Generate a consistent phone number based on user ID
        int hash = userId.hashCode();
        int areaCode = Math.abs(hash % 900) + 100; // 100-999
        int prefix = Math.abs(hash / 1000 % 900) + 100; // 100-999
        int lineNumber = Math.abs(hash / 1000000 % 9000) + 1000; // 1000-9999
        
        return String.format("+1 %d %d %d", areaCode, prefix, lineNumber);
    }

    private void setupClickListeners() {
        btnCall.setOnClickListener(v -> {
            Toast.makeText(this, "Calling " + userName, Toast.LENGTH_SHORT).show();
        });

        btnMessage.setOnClickListener(v -> {
            Toast.makeText(this, "Messaging " + userName, Toast.LENGTH_SHORT).show();
        });

        btnVideoCall.setOnClickListener(v -> {
            Toast.makeText(this, "Video call " + userName, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 