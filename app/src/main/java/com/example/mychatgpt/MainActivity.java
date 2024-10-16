package com.example.mychatgpt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mychatgpt.Adapter.MessageAdapter;
import com.example.mychatgpt.model.messParamPost;
import  com.example.mychatgpt.model.message;
import com.example.mychatgpt.retrofit.ApiGPT;
import com.example.mychatgpt.retrofit.RetrofixClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView button_send;
    private EditText txt_mess;

    private List<message> messageList;
    private MessageAdapter messageAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiGPT apiGPT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        apiGPT = RetrofixClient.getInstance().create(ApiGPT.class);
        messageList = new ArrayList<>();
        messageList.add(new message("Xin chào Linh Nguyễn",1));
        messageList.add(new message("Xin chào Linh Nguyễn",0));
        messageAdapter = new MessageAdapter(this,messageList);
        //recyclerView.setAdapter(messageAdapter);

        initView();
        initControll();


    }

    private void initControll() {
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques = txt_mess.getText().toString().trim();
                if (!ques.isEmpty()) {
                    addQuesToRecycleView(ques, 0);
                    txt_mess.setText("");  // Xóa nội dung sau khi gửi
                   PostData(ques);
                } else {
                    Toast.makeText(MainActivity.this, "Nhập nội dung câu hỏi nha", Toast.LENGTH_SHORT).show();  // Thêm .show() để hiển thị Toast
                }
            }
        });
    }
    private void addQuesToRecycleView(String ques, int send_id) {
        messageList.add(new message(ques, send_id));
        Toast.makeText(MainActivity.this ,"Current size: " + messageList.size(),Toast.LENGTH_LONG).show(); // Ghi lại kích thước
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.smoothScrollToPosition(messageList.size() - 1);
    }



    public void PostData(String ques){
        messParamPost messParamPost = new messParamPost();
        messParamPost.setModel("text-davinci-003");
        messParamPost.setPrompt("Say this is a test");
        messParamPost.setMax_tokens(2048);
        messParamPost.setTemperature(0);
        compositeDisposable.add(apiGPT.postQues(messParamPost).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        messResponse -> {
                            String result = messResponse.getChoices().get(0).getText();
                            addQuesToRecycleView(result,1);
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )

        );


    }

    private void initView() {
        recyclerView = findViewById(R.id.RecycleView_ChatGPT);
        button_send = findViewById(R.id.button_send);
        txt_mess = findViewById(R.id.edt_mes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

}