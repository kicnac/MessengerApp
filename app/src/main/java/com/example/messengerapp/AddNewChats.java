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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddNewChats extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 1;

    private LinearLayout chatsContainer;
    private List<Contact> contacts = new ArrayList<>();

    // New UI elements for buttons
    private ImageView ivPhoto, ivSearch, ivMore;
    private Spinner spinner, spinnerMore;
    private EditText etSearch;
    private LinearLayout.LayoutParams searchParams;

    private boolean isSearchExpanded = false;
    private boolean isMoreMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_chats_activity);

        initViews();
        setupSpinners();
        setupClickListeners();
        generateContacts();
        displayContacts();
    }

    private void initViews() {
        chatsContainer = findViewById(R.id.LLAddNewChats);

        // Initialize new UI elements
        ivPhoto = findViewById(R.id.ivPhoto);
        ivSearch = findViewById(R.id.ivSearch);
        ivMore = findViewById(R.id.ivMore);
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
                    Intent intent = new Intent(AddNewChats.this, Settings.class);
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
            ivPhoto.setVisibility(View.GONE);
            ivMore.setVisibility(View.GONE);

            int margin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    100,
                    getResources().getDisplayMetrics()
            );
            searchParams.leftMargin = margin;
        } else {
            etSearch.setVisibility(View.GONE);
            ivPhoto.setVisibility(View.VISIBLE);
            ivMore.setVisibility(View.VISIBLE);
            searchParams.leftMargin = 10;
        }

        ivSearch.setLayoutParams(searchParams);
    }

    private void generateContacts() {
        // Первые 3 элемента - специальные опции
        contacts.add(new Contact("Option 1", "Group chat", R.drawable.groups));
        contacts.add(new Contact("Option 2", "New channel", R.drawable.groups));
        contacts.add(new Contact("Option 3", "Secret chat", R.drawable.groups));

        // Остальные - обычные контакты
        for (int i = 0; i < 13; i++) {
            contacts.add(new Contact(
                    "Contact " + (i + 1),
                    "Last seen recently",
                    R.drawable.efault_chat_img
            ));
        }
    }

    private void displayContacts() {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Contact contact : contacts) {
            View contactView = inflater.inflate(R.layout.chat_string_content, chatsContainer, false);

            ImageView contactImage = contactView.findViewById(R.id.chatImage);
            TextView contactName = contactView.findViewById(R.id.tvChatName);
            TextView contactStatus = contactView.findViewById(R.id.tvLastMessage);

            contactImage.setImageResource(contact.getImageRes());
            contactName.setText(contact.getName());
            contactStatus.setText(contact.getStatus());

            // Добавляем обработчик нажатия
            contactView.setOnClickListener(v -> onContactSelected(contact));

            chatsContainer.addView(contactView);
        }
    }

    private void onContactSelected(Contact contact) {
        // Обработка выбора контакта
        // Можно открыть новый чат или выполнить другие действия
    }

    // Модель данных контакта
    static class Contact {
        private final String name;
        private final String status;
        private final int imageRes;

        public Contact(String name, String status, int imageRes) {
            this.name = name;
            this.status = status;
            this.imageRes = imageRes;
        }

        public String getName() { return name; }
        public String getStatus() { return status; }
        public int getImageRes() { return imageRes; }
    }
}