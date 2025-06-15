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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

class Chat{
    String name;
    String lastMessage;
    public Chat(String name,String lastMessage){
        this.name=name;
        this.lastMessage = lastMessage;
    }
}

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
    private boolean ivMore_visibility = false;
    private EditText etSearch;
    private LinearLayout.LayoutParams searchParams;
    private ArrayList<Chat> chats = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_content);
        LLChats = findViewById(R.id.LLChats);
        ivPhoto =findViewById(R.id.ivPhoto);
        ivSearch = findViewById(R.id.ivSearch);
        ivMore =findViewById(R.id.ivMore);
        ivGroups =findViewById(R.id.ivGroups);
        ivStates = findViewById(R.id.ivStats);
        ivCalls =findViewById(R.id.ivCalls);
        ivAddChats = findViewById(R.id.ivAddChats);
        spinner = findViewById(R.id.spn_search);
        spinnerMore = findViewById(R.id.spn_more);
        etSearch = findViewById(R.id.etSearch);
        searchParams = (LinearLayout.LayoutParams) ivSearch.getLayoutParams();
        CreateChats(chats);
        LayoutInflater inflater = this.getLayoutInflater();
        ImageView chatImage;
        for(int i=0;i<chats.size();i++){
            View v = inflater.inflate(R.layout.chat_string_content,this.LLChats,false);
            Chat chat = this.chats.get(i);
            chatImage = v.findViewById(R.id.chatImage);
            chatImage.setImageResource(R.drawable.efault_chat_img);

            TextView tvChatName = v.findViewById(R.id.tvChatName);
            tvChatName.setText(chat.name);
            TextView tvLastMessage = v.findViewById(R.id.tvLastMessage);
            tvLastMessage.setText(chat.lastMessage);
            this.LLChats.addView(v);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner_item, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter_more = ArrayAdapter.createFromResource(this,R.array.spinner_item_more, android.R.layout.simple_spinner_item);
        adapter_more.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMore.setAdapter(adapter_more);
        ivCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Calls.class);
                startActivity(intent);
            }
        });
        ivGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Groups.class);
                startActivity(intent);
            }
        });
        ivStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, StatesActivity.class);
                startActivity(intent);
            }
        });
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oneIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(oneIntent,MainPage.REQUEST_CAMERA);
            }
        });
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivMore_visibility = !ivMore_visibility;
                if(ivMore_visibility) {
                    spinnerMore.setVisibility(View.VISIBLE);
                    spinnerMore.performClick();
                }
                else spinnerMore.setVisibility(View.GONE);
            }
        });
        spinnerMore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select_item = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ivAddChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, AddNewChats.class);
                startActivity(intent);
            }
        });

                ivSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (spinner.getVisibility() == View.GONE) {
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
                    }
                });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select_item = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<Chat> CreateChats(ArrayList<Chat> chats){
        for(int i=0;i<10;i++){
            chats.add(new Chat("Name"+(i+1),"Last chat message"));
        }
        return chats;
    }
}
