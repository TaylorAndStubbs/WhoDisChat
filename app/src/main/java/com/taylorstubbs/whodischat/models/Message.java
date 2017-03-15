package com.taylorstubbs.whodischat.models;

/**
 * Created by taylorstubbs on 3/15/17.
 */

public class Message {
    private static final String TAG = "Message";

    public String text;
    public String senderId;

    public Message() {
        //Default for firebase
    }

    public Message(String t, String sId) {
        text = t;
        senderId = sId;
    }
}
