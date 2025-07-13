package com.example.messengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MessageAdapter adapter;
    private List<Message> messages;
    private String contactName;
    private String contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_history);

        // Get data from intent
        Intent intent = getIntent();
        contactName = intent.getStringExtra("contact_name");
        contactId = intent.getStringExtra("contact_id");

        // Setup ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(contactName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();
        setupRecyclerView();
        loadMessages();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewMessages);
    }

    private void setupRecyclerView() {
        messages = new ArrayList<>();
        adapter = new MessageAdapter(messages, new MessageAdapter.OnAvatarClickListener() {
            @Override
            public void onAvatarClick(String userId, String userName) {
                Intent intent = new Intent(ChatHistoryActivity.this, UserProfileActivity.class);
                intent.putExtra("user_id", userId);
                intent.putExtra("user_name", userName);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadMessages() {
        // Generate mock messages
        messages.addAll(generateMockMessages());
        adapter.notifyDataSetChanged();
        
        // Scroll to bottom
        recyclerView.post(() -> recyclerView.smoothScrollToPosition(messages.size() - 1));
    }

    private List<Message> generateMockMessages() {
        List<Message> mockMessages = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        long currentTime = System.currentTimeMillis();

        // Current user messages (blue bubbles, right side)
        mockMessages.add(new Message("current_user", "Me", "Hey, how are you?", currentTime - 3600000, R.drawable.efault_chat_img, true));
        mockMessages.add(new Message("current_user", "Me", "Are you free this weekend?", currentTime - 3000000, R.drawable.efault_chat_img, true));
        mockMessages.add(new Message("current_user", "Me", "Let's grab coffee!", currentTime - 2400000, R.drawable.efault_chat_img, true));
        mockMessages.add(new Message("current_user", "Me", "What do you think about the new movie?", currentTime - 1800000, R.drawable.efault_chat_img, true));
        mockMessages.add(new Message("current_user", "Me", "I'll see you tomorrow then!", currentTime - 1200000, R.drawable.efault_chat_img, true));

        // Other user messages (gray bubbles, left side)
        mockMessages.add(new Message(contactId, contactName, "Hi! I'm doing great, thanks!", currentTime - 3300000, R.drawable.efault_chat_img, false));
        mockMessages.add(new Message(contactId, contactName, "Yes, I should be free. What did you have in mind?", currentTime - 2700000, R.drawable.efault_chat_img, false));
        mockMessages.add(new Message(contactId, contactName, "Sounds good! Where should we meet?", currentTime - 2100000, R.drawable.efault_chat_img, false));
        mockMessages.add(new Message(contactId, contactName, "I heard it's really good! Want to watch it together?", currentTime - 1500000, R.drawable.efault_chat_img, false));
        mockMessages.add(new Message(contactId, contactName, "Perfect! Looking forward to it!", currentTime - 900000, R.drawable.efault_chat_img, false));
        mockMessages.add(new Message(contactId, contactName, "Don't forget to bring the tickets!", currentTime - 600000, R.drawable.efault_chat_img, false));

        return mockMessages;
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