package com.taylorstubbs.whodischat.holders;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taylorstubbs.whodischat.R;
import com.taylorstubbs.whodischat.models.Message;

/**
 * Created by taylorstubbs on 3/15/17.
 */

public class MessageHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "MessageHolder";

    private String mSenderId;
    private Context mContext;

    private TextView mTextView;
    private LinearLayout mBackgroundView;

    public MessageHolder(View itemView, String senderId, Context context) {
        super(itemView);

        mContext = context;
        mSenderId = senderId;
        mTextView = (TextView) itemView.findViewById(R.id.text);
        mBackgroundView = (LinearLayout) itemView.findViewById(R.id.message_background);
    }

    public void bindMessage(Message message) {
        mTextView.setText(message.text);

        if (mSenderId.equals(message.senderId)) {
            mBackgroundView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else {
            mBackgroundView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        }
    }
}
