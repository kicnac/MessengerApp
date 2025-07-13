package com.example.messengerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    private List<Message> messages;
    private OnAvatarClickListener avatarClickListener;

    public interface OnAvatarClickListener {
        void onAvatarClick(String userId, String userName);
    }

    public MessageAdapter(List<Message> messages, OnAvatarClickListener listener) {
        this.messages = messages;
        this.avatarClickListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        return message.isFromCurrentUser() ? VIEW_TYPE_SENT : VIEW_TYPE_RECEIVED;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        if (holder.getItemViewType() == VIEW_TYPE_SENT) {
            SentMessageViewHolder sentHolder = (SentMessageViewHolder) holder;
            sentHolder.tvMessageText.setText(message.getText());
            sentHolder.tvTimestamp.setText(sdf.format(message.getTimestamp()));
            sentHolder.ivAvatar.setImageResource(message.getAvatarResId());
            
            sentHolder.ivAvatar.setOnClickListener(v -> {
                if (avatarClickListener != null) {
                    avatarClickListener.onAvatarClick(message.getSenderId(), message.getSenderName());
                }
            });
        } else {
            ReceivedMessageViewHolder receivedHolder = (ReceivedMessageViewHolder) holder;
            receivedHolder.tvMessageText.setText(message.getText());
            receivedHolder.tvTimestamp.setText(sdf.format(message.getTimestamp()));
            receivedHolder.ivAvatar.setImageResource(message.getAvatarResId());
            
            receivedHolder.ivAvatar.setOnClickListener(v -> {
                if (avatarClickListener != null) {
                    avatarClickListener.onAvatarClick(message.getSenderId(), message.getSenderName());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessageText;
        TextView tvTimestamp;
        ImageView ivAvatar;

        SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessageText = itemView.findViewById(R.id.tvMessageText);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessageText;
        TextView tvTimestamp;
        ImageView ivAvatar;

        ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessageText = itemView.findViewById(R.id.tvMessageText);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
        }
    }
} 