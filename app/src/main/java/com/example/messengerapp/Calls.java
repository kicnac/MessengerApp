package com.example.messengerapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Calls extends AppCompatActivity {

    // UI элементы
    private LinearLayout LLCallsList;
    private ImageView ivChats, ivGroups, ivStates;
    private TextView tvHeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calls_activity);

        initViews();
        setupNavigation();
        displayCallLists();
    }

    private void initViews() {
        ivChats = findViewById(R.id.ivChats);
        ivGroups = findViewById(R.id.ivGroups);
        ivStates = findViewById(R.id.ivStats);
        LLCallsList = findViewById(R.id.LLCallsList);
        tvHeadline = findViewById(R.id.tvHeadline);
    }

    private void setupNavigation() {
        ivChats.setOnClickListener(v -> navigateTo(MainPage.class));
        ivGroups.setOnClickListener(v -> navigateTo(Groups.class));
        ivStates.setOnClickListener(v -> navigateTo(StatesActivity.class));
    }

    private void navigateTo(Class<?> destination) {
        startActivity(new Intent(Calls.this, destination));
    }

    private void displayCallLists() {
        List<Call> allCalls = generateTestCalls();
        List<Call> favCalls = new ArrayList<>();
        List<Call> recentCalls = new ArrayList<>();

        // Разделение звонков на избранные и обычные
        for (Call call : allCalls) {
            if (call.isFavorite()) {
                favCalls.add(call);
            } else {
                recentCalls.add(call);
            }
        }

        // Очистка списка перед добавлением новых элементов
        LLCallsList.removeAllViews();

        // Добавление избранных звонков
        if (!favCalls.isEmpty()) {
            addSectionHeader("Избранные");
            fillCallList(favCalls);
        }

        // Добавление недавних звонков
        addSectionHeader("Недавние");
        fillCallList(recentCalls);
    }

    private void addSectionHeader(String title) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.headline, LLCallsList, false);
        TextView headerText = headerView.findViewById(R.id.tvHeadline);
        headerText.setText(title);
        LLCallsList.addView(headerView);
    }

    private List<Call> generateTestCalls() {
        List<Call> calls = new ArrayList<>();
        Random random = new Random();
        int callCount = 15;
        int favoriteCount = 5;

        for (int i = 0; i < callCount; i++) {
            Call.CallType type = Call.CallType.values()[random.nextInt(3)];
            calls.add(new Call(
                    "Контакт " + (i + 1),
                    "Сегодня в " + (i % 12 + 1) + ":00",
                    "img_" + (i % 5),
                    i < favoriteCount,
                    type
            ));
        }
        return calls;
    }

    private void fillCallList(List<Call> calls) {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Call call : calls) {
            View itemView = inflater.inflate(R.layout.calls_item, LLCallsList, false);

            ImageView ivContactAvatar = itemView.findViewById(R.id.ivContactAvatar);
            TextView tvContactName = itemView.findViewById(R.id.tvContactName);
            TextView tvDateCall = itemView.findViewById(R.id.tvDateCall);
            ImageView ivCallType = itemView.findViewById(R.id.ivCallType);

            ivContactAvatar.setImageResource(R.drawable.efault_chat_img);
            tvContactName.setText(call.getContactName());
            tvDateCall.setText(call.getDateOfCall());

            setupCallTypeIcon(ivCallType, tvContactName, call.getType());
            LLCallsList.addView(itemView);
        }
    }

    private void setupCallTypeIcon(ImageView iconView, TextView nameView, Call.CallType type) {
        switch (type) {
            case INCOMING:
                iconView.setImageResource(R.drawable.incoming_call);
                break;
            case OUTGOING:
                iconView.setImageResource(R.drawable.outgoing_call);
                break;
            case MISSED:
                iconView.setImageResource(R.drawable.missed_call);
                nameView.setTextColor(ContextCompat.getColor(this, R.color.red));
                break;
        }
    }

    // Модель данных для звонка
    static class Call {
        private final String contactName;
        private final String dateOfCall;
        private final String imgName;
        private final boolean isFavorite;
        private final CallType type;

        enum CallType {
            INCOMING, OUTGOING, MISSED
        }

        public Call(String contactName, String dateOfCall, String imgName, boolean isFavorite, CallType type) {
            this.contactName = contactName;
            this.dateOfCall = dateOfCall;
            this.imgName = imgName;
            this.isFavorite = isFavorite;
            this.type = type;
        }

        public String getContactName() { return contactName; }
        public String getDateOfCall() { return dateOfCall; }
        public boolean isFavorite() { return isFavorite; }
        public CallType getType() { return type; }
    }
}