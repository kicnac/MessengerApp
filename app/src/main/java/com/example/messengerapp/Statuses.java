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

public class Statuses extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 1;

    private ImageView ivGroups,ivChats,ivCalls;
    private LinearLayout LLStatusList;

        // New UI elements for buttons
    private ImageView ivSearch, ivMore;
    private Spinner spinner, spinnerMore;
    private EditText etSearch;
    private LinearLayout.LayoutParams searchParams;
    
    private boolean isSearchExpanded = false;
    private boolean isMoreMenuVisible = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.states_activity);
        initViews();
        setupNavigation();
        displayStatusLists();
    }
    private void initViews() {
        ivGroups =findViewById(R.id.ivGroups);
        ivChats =findViewById(R.id.ivChats);
        ivCalls =findViewById(R.id.ivCalls);
        LLStatusList = findViewById(R.id.LLStatusList);

        // Initialize new UI elements
        ivSearch = findViewById(R.id.ivSearch);
        ivMore = findViewById(R.id.ivMore);
        spinner = findViewById(R.id.spn_search);
        spinnerMore = findViewById(R.id.spn_more);
        etSearch = findViewById(R.id.etSearch);
        searchParams = (LinearLayout.LayoutParams) ivSearch.getLayoutParams();
    }
    private void setupNavigation() {
        ivChats.setOnClickListener(v -> navigateTo(MainPage.class));
        ivGroups.setOnClickListener(v -> navigateTo(Groups.class));
        ivCalls.setOnClickListener(v -> navigateTo(Calls.class));

        // Setup new button functionality
        setupSpinners();
        setupClickListeners();
    }
    private void navigateTo(Class<?> destination) {
        startActivity(new Intent(Statuses.this, destination));
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
                    Intent intent = new Intent(Statuses.this, Settings.class);
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
        // More options
        ivMore.setOnClickListener(v -> toggleMoreMenu());

        // Search
        ivSearch.setOnClickListener(v -> toggleSearch());
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
            etSearch.setVisibility(View.VISIBLE);
            ivMore.setVisibility(View.GONE);

            int margin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    100,
                    getResources().getDisplayMetrics()
            );
            searchParams.leftMargin = margin;
        } else {
            etSearch.setVisibility(View.GONE);
            ivMore.setVisibility(View.VISIBLE);
            searchParams.leftMargin = 10;
        }

        ivSearch.setLayoutParams(searchParams);
    }
    private void displayStatusLists() {
        List<Status> allStatuses = generateTestStatuses();
        List<Status> etcStatuses = new ArrayList<>();
        List<Status> recentStatuses = new ArrayList<>();

        // Разделение звонков на избранные и обычные
        for (Status status : allStatuses) {
            if (!status.isRecent()) {
                etcStatuses.add(status);
            } else {
                recentStatuses.add(status);
            }
        }

        // Очистка списка перед добавлением новых элементов
        LLStatusList.removeAllViews();

        // Добавление избранных звонков
        if (!etcStatuses.isEmpty()) {
            addSectionHeader("Все");
            fillStatusList(etcStatuses);
        }

        // Добавление недавних звонков
        addSectionHeader("Недавние");
        fillStatusList(recentStatuses);
    }
    private List<Status> generateTestStatuses() {
        List<Status> statuses = new ArrayList<>();
        Random random = new Random();
        int statusCount = 15;
        int recentCount = 5;

        for (int i = 0; i < statusCount; i++) {
            statuses.add(new Statuses.Status(
                    "Контакт " + (i + 1),
                    "Сегодня в " + (i % 12 + 1) + ":00",
                    "img_" + (i % 5),
                    i < recentCount
            ));
        }
        return statuses;
    }
    private void fillStatusList(List<Statuses.Status> statuses) {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Statuses.Status status : statuses) {
            View itemView = inflater.inflate(R.layout.status_item, LLStatusList, false);

            ImageView ivContactAvatarStatus = itemView.findViewById(R.id.ivContactAvatarStatus);
            TextView tvContactNameStatus = itemView.findViewById(R.id.tvContactNameStatus);
            TextView tvDateStatus = itemView.findViewById(R.id.tvDateStatus);

            ivContactAvatarStatus.setImageResource(R.drawable.efault_chat_img);
            tvContactNameStatus.setText(status.getContactName());
            tvDateStatus.setText(status.getDateOfStatus());

            LLStatusList.addView(itemView);
        }
    }
    private void addSectionHeader(String title) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.headline, LLStatusList, false);
        TextView headerText = headerView.findViewById(R.id.tvHeadline);
        headerText.setText(title);
        LLStatusList.addView(headerView);
    }
    //Структура данных Статус
    static class Status{
        private final String contactName;
        private final String dateOfStatus;
        private final String imgName;
        private final boolean isRecent;

        public Status(String contactName, String dateOfStatus, String imgName, boolean isFavorite) {
            this.contactName = contactName;
            this.dateOfStatus = dateOfStatus;
            this.imgName = imgName;
            this.isRecent = isFavorite;
        }

        public String getContactName() { return contactName; }
        public String getDateOfStatus() { return dateOfStatus; }
        public boolean isRecent() { return isRecent; }
    }

}
