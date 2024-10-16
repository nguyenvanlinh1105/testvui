package com.example.mychatgpt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mychatgpt.R;
import com.example.mychatgpt.model.message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    Context context;

    List<message> messageList;

    public MessageAdapter(Context context, List<message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        message currentMessage = messageList.get(position);
        if (currentMessage.getSend_Id() == 0) {
            holder.layout2.setVisibility(View.VISIBLE); // Tin nhắn từ người dùng
            holder.layout1.setVisibility(View.GONE);
            holder.text_right_chat.setText(currentMessage.getContent());
        } else {
            holder.layout2.setVisibility(View.GONE);
            holder.layout1.setVisibility(View.VISIBLE); // Tin nhắn từ chatbot
            holder.text_left_chat.setText(currentMessage.getContent());
        }
    }



    @Override
    public int getItemCount() {
        return messageList.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder {
        LinearLayout layout1, layout2;
        TextView text_left_chat , text_right_chat ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout1= itemView.findViewById(R.id.layout1);
            layout2= itemView.findViewById(R.id.layout2);
            text_left_chat = itemView.findViewById(R.id.item_chat_left);

            text_right_chat = itemView.findViewById(R.id.item_chat_right);
        }
    }

}
