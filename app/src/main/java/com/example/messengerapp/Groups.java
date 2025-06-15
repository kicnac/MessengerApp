package com.example.messengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Groups extends AppCompatActivity {
    private ImageView ivChats;
    private ImageView ivCalls;
    private ImageView ivStates;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups_activity);
        ivChats =findViewById(R.id.ivChats);
        ivStates = findViewById(R.id.ivStats);
        ivCalls =findViewById(R.id.ivCalls);

        ivChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Groups.this, MainPage.class);
                startActivity(intent);
            }
        });
        ivCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Groups.this, Calls.class);
                startActivity(intent);
            }
        });
        ivStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Groups.this, StatesActivity.class);
                startActivity(intent);
            }
        });
    }
}
