package com.example.formulaapp.Models;

public class Chat {

    private String message;
    private String receiver;
    private String sender;
    private String isSeen;

    public Chat() {
    }

    public Chat(String message, String receiver, String sender, String isSeen) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        this.isSeen = isSeen;
    }

    public String getMessage() {
            return message;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public String getIsSeen() {
        return isSeen;
    }

    public void setSeen(String seen) {
        isSeen = seen;
    }
}


