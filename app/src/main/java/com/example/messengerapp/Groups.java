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
import java.util.Random;

public class Groups extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 1;

    private ImageView ivGroups, ivChats, ivCalls, ivStatuses;
    private LinearLayout LLGroupsList;

        // New UI elements for buttons
    private ImageView ivMore;
    private Spinner spinnerMore;
    
    private boolean isMoreMenuVisible = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups_activity);
        initViews();
        setupNavigation();
        displayGroupsLists();
    }

    private void initViews() {
        ivGroups = findViewById(R.id.ivGroups);
        ivChats = findViewById(R.id.ivChats);
        ivCalls = findViewById(R.id.ivCalls);
        ivStatuses = findViewById(R.id.ivStats);
        LLGroupsList = findViewById(R.id.LLGroupsList);

        // Initialize new UI elements
        ivMore = findViewById(R.id.ivMore);
        spinnerMore = findViewById(R.id.spn_more);
    }

    private void setupNavigation() {
        ivChats.setOnClickListener(v -> navigateTo(MainPage.class));
        ivStatuses.setOnClickListener(v -> navigateTo(Statuses.class));
        ivCalls.setOnClickListener(v -> navigateTo(Calls.class));

        // Setup new button functionality
        setupSpinners();
        setupClickListeners();
    }

    private void navigateTo(Class<?> destination) {
        startActivity(new Intent(Groups.this, destination));
    }

    private void setupSpinners() {
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
                    Intent intent = new Intent(Groups.this, Settings.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        };

        spinnerMore.setOnItemSelectedListener(spinnerListener);
    }

    private void setupClickListeners() {
        // More options
        ivMore.setOnClickListener(v -> toggleMoreMenu());
    }

    private void toggleMoreMenu() {
        isMoreMenuVisible = !isMoreMenuVisible;
        spinnerMore.setVisibility(isMoreMenuVisible ? View.VISIBLE : View.GONE);
        if (isMoreMenuVisible) {
            spinnerMore.performClick();
        }
    }



    private void displayGroupsLists() {
        List<Group> allGroups = generateTestGroups();
        List<Group> favoriteGroups = new ArrayList<>();
        List<Group> regularGroups = new ArrayList<>();

        // Split groups into favorite and regular
        for (Group group : allGroups) {
            if (group.isFavorite()) {
                favoriteGroups.add(group);
            } else {
                regularGroups.add(group);
            }
        }

        // Clear the list before adding new items
        LLGroupsList.removeAllViews();

        // Add favorite groups
        if (!favoriteGroups.isEmpty()) {
            addSectionHeader("Избранные");
            fillGroupList(favoriteGroups);
        }

        // Add regular groups
        addSectionHeader("Все группы");
        fillGroupList(regularGroups);
    }

    private List<Group> generateTestGroups() {
        List<Group> groups = new ArrayList<>();
        Random random = new Random();
        int groupCount = 10;
        int favoriteCount = 3;

        for (int i = 0; i < groupCount; i++) {
            groups.add(new Group(
                    "Группа " + (i + 1),
                    random.nextInt(100) + " участников",
                    "img_" + (i % 5),
                    i < favoriteCount
            ));
        }
        return groups;
    }

    private void fillGroupList(List<Group> groups) {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Group group : groups) {
            View itemView = inflater.inflate(R.layout.group_item, LLGroupsList, false);

            ImageView ivAvatarGroup = itemView.findViewById(R.id.ivAvatarGroup);
            TextView tvGroupName = itemView.findViewById(R.id.tvGroupName);
            TextView tvMembersCount = itemView.findViewById(R.id.tvDateLastMessage);

            ivAvatarGroup.setImageResource(R.drawable.efault_chat_img);
            tvGroupName.setText(group.getGroupName());
            tvMembersCount.setText(group.getMembersCount());

            LLGroupsList.addView(itemView);
        }
    }

    private void addSectionHeader(String title) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.headline, LLGroupsList, false);
        TextView headerText = headerView.findViewById(R.id.tvHeadline);
        headerText.setText(title);
        LLGroupsList.addView(headerView);
    }

    // Group data structure
    static class Group {
        private final String groupName;
        private final String membersCount;
        private final String imgName;
        private final boolean isFavorite;

        public Group(String groupName, String membersCount, String imgName, boolean isFavorite) {
            this.groupName = groupName;
            this.membersCount = membersCount;
            this.imgName = imgName;
            this.isFavorite = isFavorite;
        }

        public String getGroupName() { return groupName; }
        public String getMembersCount() { return membersCount; }
        public boolean isFavorite() { return isFavorite; }
    }
}