package com.example.messengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class StatesActivity extends AppCompatActivity {
    private ImageView ivGroups;
    private ImageView ivChats;
    private ImageView ivCalls;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.states_activity);
        ivGroups =findViewById(R.id.ivGroups);
        ivChats =findViewById(R.id.ivChats);
        ivCalls =findViewById(R.id.ivCalls);

        ivChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatesActivity.this, MainPage.class);
                startActivity(intent);
            }
        });
        ivCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatesActivity.this, Calls.class);
                startActivity(intent);
            }
        });
        ivGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatesActivity.this, Groups.class);
                startActivity(intent);
            }
        });

    }
}
