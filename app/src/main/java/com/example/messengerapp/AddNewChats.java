package com.example.messengerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddNewChats extends AppCompatActivity {
    class Chat{
        String name;
        String lastMessage;
        public Chat(String name,String lastMessage){
            this.name=name;
            this.lastMessage = lastMessage;
        }
    }
    private ScrollView svAddNewChats;
    private LinearLayout LLAddNewChats;
    private ArrayList<com.example.messengerapp.Chat> chats = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_chats_activity);
        LLAddNewChats = findViewById(R.id.LLAddNewChats);
        svAddNewChats = findViewById(R.id.svAddNewChats);
        CreateChats(chats);
        LayoutInflater inflater = this.getLayoutInflater();
        ImageView chatImage;

        for(int i=0;i<chats.size();i++){
            if(i<3){
                View v = inflater.inflate(R.layout.chat_string_content,this.LLAddNewChats,false);
                chatImage = v.findViewById(R.id.chatImage);
                chatImage.setImageResource(R.drawable.groups);

                TextView tvChatName = v.findViewById(R.id.tvChatName);
                tvChatName.setText("Option "+(i+1));
                this.LLAddNewChats.addView(v);
            }else{
            View v = inflater.inflate(R.layout.chat_string_content,this.LLAddNewChats,false);
            com.example.messengerapp.Chat chat = this.chats.get(i);
            chatImage = v.findViewById(R.id.chatImage);
            chatImage.setImageResource(R.drawable.efault_chat_img);

            TextView tvChatName = v.findViewById(R.id.tvChatName);
            tvChatName.setText(chat.name);
            TextView tvLastMessage = v.findViewById(R.id.tvLastMessage);
            tvLastMessage.setText(chat.lastMessage);
            this.LLAddNewChats.addView(v);
            }
        }
    }
    private ArrayList<com.example.messengerapp.Chat> CreateChats(ArrayList<com.example.messengerapp.Chat> chats){
        for(int i=0;i<16;i++){
            chats.add(new com.example.messengerapp.Chat("Name"+(i+1),"Last chat message"));
        }
        return chats;
    }
}
