package com.example.mychatgpt.model;

public class message {
    private String Content ;
    private int Send_Id;// 0 là câu hỏi của người dùng, 1 là câu trả lòi của chat

    public message(String content, int send_Id) {
        Content = content;
        Send_Id = send_Id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getSend_Id() {
        return Send_Id;
    }

    public void setSend_Id(int send_Id) {
        Send_Id = send_Id;
    }
}
