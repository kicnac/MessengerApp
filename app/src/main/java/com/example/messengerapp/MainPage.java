package com.example.messengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 1;

    private LinearLayout LLChats;
    private Spinner spinner;
    private Spinner spinnerMore;
    private ImageView ivSearch;
    private ImageView ivPhoto;
    private ImageView ivMore;
    private ImageView ivGroups;
    private ImageView ivCalls;
    private ImageView ivStates;
    private ImageView ivAddChats;
    private EditText etSearch;
    private LinearLayout.LayoutParams searchParams;

    private boolean isSearchExpanded = false;
    private boolean isMoreMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_content);

        initViews();
        setupSpinners();
        setupClickListeners();
        displayChats();
    }

    private void initViews() {
        LLChats = findViewById(R.id.LLChats);
        ivPhoto = findViewById(R.id.ivPhoto);
        ivSearch = findViewById(R.id.ivSearch);
        ivMore = findViewById(R.id.ivMore);
        ivGroups = findViewById(R.id.ivGroups);
        ivStates = findViewById(R.id.ivStats);
        ivCalls = findViewById(R.id.ivCalls);
        ivAddChats = findViewById(R.id.ivAddChats);
        spinner = findViewById(R.id.spn_search);
        spinnerMore = findViewById(R.id.spn_more);
        etSearch = findViewById(R.id.etSearch);
        searchParams = (LinearLayout.LayoutParams) ivSearch.getLayoutParams();
    }

    private void setupSpinners() {
        // Search spinner setup
        ArrayAdapter<CharSequence> searchAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_item,
                android.R.layout.simple_spinner_item
        );
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(searchAdapter);

        // More options spinner setup
        ArrayAdapter<CharSequence> moreAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_item_more,
                android.R.layout.simple_spinner_item
        );
        moreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMore.setAdapter(moreAdapter);

        // Spinner selection listeners
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Handle selected item
                if (selectedItem.equals("Настройки")) {
                    Intent intent = new Intent(MainPage.this, Settings.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        };

        spinner.setOnItemSelectedListener(spinnerListener);
        spinnerMore.setOnItemSelectedListener(spinnerListener);
    }

    private void setupClickListeners() {
        // Navigation
        ivCalls.setOnClickListener(v -> navigateTo(Calls.class));
        ivGroups.setOnClickListener(v -> navigateTo(Groups.class));
        ivStates.setOnClickListener(v -> navigateTo(Statuses.class));
        ivAddChats.setOnClickListener(v -> navigateTo(AddNewChats.class));

        // Camera
        ivPhoto.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        });

        // More options
        ivMore.setOnClickListener(v -> toggleMoreMenu());

        // Search
        ivSearch.setOnClickListener(v -> toggleSearch());
    }

    private void navigateTo(Class<?> destination) {
        startActivity(new Intent(MainPage.this, destination));
    }

    private void toggleMoreMenu() {
        isMoreMenuVisible = !isMoreMenuVisible;
        spinnerMore.setVisibility(isMoreMenuVisible ? View.VISIBLE : View.GONE);
        if (isMoreMenuVisible) {
            spinnerMore.performClick();
        }
    }

    private void toggleSearch() {
        isSearchExpanded = !isSearchExpanded;

        if (isSearchExpanded) {
            spinner.setVisibility(View.VISIBLE);
            etSearch.setVisibility(View.VISIBLE);
            ivPhoto.setVisibility(View.GONE);
            ivMore.setVisibility(View.GONE);

            int margin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    100,
                    getResources().getDisplayMetrics()
            );
            searchParams.leftMargin = margin;
        } else {
            spinner.setVisibility(View.GONE);
            etSearch.setVisibility(View.GONE);
            ivPhoto.setVisibility(View.VISIBLE);
            ivMore.setVisibility(View.VISIBLE);
            searchParams.leftMargin = 10;
        }

        ivSearch.setLayoutParams(searchParams);
    }

    private void displayChats() {
        List<Chat> chats = generateTestChats();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Chat chat : chats) {
            View chatView = inflater.inflate(R.layout.chat_string_content, LLChats, false);

            ImageView chatImage = chatView.findViewById(R.id.chatImage);
            TextView tvChatName = chatView.findViewById(R.id.tvChatName);
            TextView tvLastMessage = chatView.findViewById(R.id.tvLastMessage);

            chatImage.setImageResource(R.drawable.efault_chat_img);
            tvChatName.setText(chat.name);
            tvLastMessage.setText(chat.lastMessage);

            LLChats.addView(chatView);
        }
    }

    private List<Chat> generateTestChats() {
        List<Chat> chats = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            chats.add(new Chat("Name" + (i + 1), "Last chat message"));
        }
        return chats;
    }

    // Chat data model
    static class Chat {
        String name;
        String lastMessage;

        public Chat(String name, String lastMessage) {
            this.name = name;
            this.lastMessage = lastMessage;
        }
    }
}