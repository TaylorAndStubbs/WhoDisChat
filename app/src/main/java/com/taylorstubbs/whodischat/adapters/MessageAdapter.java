package com.taylorstubbs.whodischat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taylorstubbs.whodischat.R;
import com.taylorstubbs.whodischat.holders.MessageHolder;
import com.taylorstubbs.whodischat.models.Message;

import java.util.List;

/**
 * Created by taylorstubbs on 3/15/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {
    private static final String TAG = "MessageAdapter";

    private List<Message> mMessages;
    private Context mContext;
    private String mSenderId;

    public MessageAdapter(Context context, List<Message> messages, String senderId) {
        mSenderId = senderId;
        mContext = context;
        mMessages = messages;
    }

    public void addMessage(Message message) {
        Log.d(TAG, "adding message");
        mMessages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_message, parent, false);

        return new MessageHolder(view, mSenderId, mContext);
    }

    @Override
    public void onBindViewHolder(MessageHolder messageHolder, int position) {
        Message message = mMessages.get(position);
        messageHolder.bindMessage(message);
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }
}
