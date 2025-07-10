package com.example.messengerapp;

import android.content.Intent;
import android.os.Bundle;
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

public class Settings extends AppCompatActivity {
    
    private LinearLayout settingsContainer;
    private ImageView ivSearch;
    private Spinner spinner, spinnerMore;
    private EditText etSearch;
    private LinearLayout.LayoutParams searchParams;
    private List<SettingsItem> settingsItems = new ArrayList<>();
    
    private boolean isSearchExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        initViews();
        generateSettingsItems();
        displaySettings();
    }

    private void initViews() {
        settingsContainer = findViewById(R.id.LLSettingsList);
        ivSearch = findViewById(R.id.ivSearch);
        spinner = findViewById(R.id.spn_search);
        spinnerMore = findViewById(R.id.spn_more);
        etSearch = findViewById(R.id.etSearch);
        searchParams = (LinearLayout.LayoutParams) ivSearch.getLayoutParams();
        
        // Setup search functionality
        setupSpinners();
        setupClickListeners();
    }

    private void generateSettingsItems() {
        settingsItems.add(new SettingsItem("Аккаунт", "Уведомление безопасности, изменение номера"));
        settingsItems.add(new SettingsItem("Конфиденциальность", "Заблокировать контакты, исчезающие сообзения"));
        settingsItems.add(new SettingsItem("Аватар", "Создать, изменить фото профиля"));
        settingsItems.add(new SettingsItem("Списки", "Управление контактами и группами"));
        settingsItems.add(new SettingsItem("Чаты", "Тема, обои, история чатов"));
        settingsItems.add(new SettingsItem("Уведомления", "Звуки сообщений, групп и звонков"));
        settingsItems.add(new SettingsItem("Данные и хранилище", "Использование сети, автозагрузка"));
        settingsItems.add(new SettingsItem("Специальные возможности", "Анимация"));
        settingsItems.add(new SettingsItem("Язык приложения", "Русский(язык устройства)"));
        settingsItems.add(new SettingsItem("Помощь", "Справочный центр"));
        settingsItems.add(new SettingsItem("Пригласить друга",""));
    }
    
    private void addProfileSection() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View profileView = inflater.inflate(R.layout.calls_item, settingsContainer, false);

        ImageView ivContactAvatar = profileView.findViewById(R.id.ivContactAvatar);
        TextView tvContactName = profileView.findViewById(R.id.tvContactName);
        TextView tvDateCall = profileView.findViewById(R.id.tvDateCall);
        ImageView ivCallType = profileView.findViewById(R.id.ivCallType);

        // Set profile image
        ivContactAvatar.setImageResource(R.drawable.efault_chat_img);
        
        // Set username
        tvContactName.setText("Имя Пользователя");
        
        // Set status
        tvDateCall.setText("Онлайн");
        
        // Hide the call type icon
        ivCallType.setVisibility(View.GONE);

        // Add click listener for profile section
        profileView.setOnClickListener(v -> onProfileClicked());

        // Add profile section at the beginning
        settingsContainer.addView(profileView, 0);
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
                    Intent intent = new Intent(Settings.this, Settings.class);
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
        // Search
        ivSearch.setOnClickListener(v -> toggleSearch());
    }

    private void toggleSearch() {
        isSearchExpanded = !isSearchExpanded;

        if (isSearchExpanded) {
            etSearch.setVisibility(View.VISIBLE);

            int margin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    100,
                    getResources().getDisplayMetrics()
            );
            searchParams.leftMargin = margin;
        } else {
            etSearch.setVisibility(View.GONE);
            searchParams.leftMargin = 10;
        }

        ivSearch.setLayoutParams(searchParams);
    }

    private void displaySettings() {
        // Add profile section first
        addProfileSection();
        
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < settingsItems.size(); i++) {
            SettingsItem item = settingsItems.get(i);
            View itemView = inflater.inflate(R.layout.calls_item, settingsContainer, false);

            ImageView ivContactAvatar = itemView.findViewById(R.id.ivContactAvatar);
            TextView tvContactName = itemView.findViewById(R.id.tvContactName);
            TextView tvDateCall = itemView.findViewById(R.id.tvDateCall);
            ImageView ivCallType = itemView.findViewById(R.id.ivCallType);

            // Hide all images for settings items
            ivContactAvatar.setVisibility(View.GONE);
            
            // Set setting name as contact name
            tvContactName.setText(item.getName());
            
            // Set active status as date
            tvDateCall.setText(item.getStatus());
            
            // Hide the call type icon since it's not needed for settings
            ivCallType.setVisibility(View.GONE);

            // Add click listener for settings item
            itemView.setOnClickListener(v -> onSettingsItemClicked(item));

            settingsContainer.addView(itemView);
        }
    }

    private void onSettingsItemClicked(SettingsItem item) {
        // Handle settings item click
        // You can add specific functionality for each setting here
    }
    
    private void onProfileClicked() {
        // Handle profile section click
        // You can implement profile editing functionality here
    }

    // Settings item data model
    static class SettingsItem {
        private final String name;
        private final String status;

        public SettingsItem(String name, String status) {
            this.name = name;
            this.status = status;
        }

        public String getName() { return name; }
        public String getStatus() { return status; }
    }
} 