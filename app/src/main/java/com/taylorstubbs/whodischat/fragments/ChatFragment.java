package com.taylorstubbs.whodischat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.taylorstubbs.whodischat.R;
import com.taylorstubbs.whodischat.helpers.FirebaseDatabaseHelper;
import com.taylorstubbs.whodischat.models.Message;
import com.taylorstubbs.whodischat.models.User;

/**
 * Created by taylorstubbs on 3/15/17.
 */

public class ChatFragment extends Fragment {
    private static final String TAG = "ChatFragment";
    private static final String ARGS_USER = "user";

    private FirebaseDatabaseHelper mDatabaseHelper;
    private User mUser;
    private String mTextString;

    private Button mSendButton;
    private EditText mTextInput;

    public static ChatFragment newInstance(User user) {
        Bundle args = new Bundle();
        ChatFragment chatFragment = new ChatFragment();
        args.putParcelable(ARGS_USER, user);
        chatFragment.setArguments(args);

        return chatFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabaseHelper = new FirebaseDatabaseHelper();
        mUser = getArguments().getParcelable(ARGS_USER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mSendButton = (Button) view.findViewById(R.id.send_button);
        mTextInput = (EditText) view.findViewById(R.id.text_input);

        mTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //NA
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextString = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //NA
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message(mTextString, mUser.userId);
                mDatabaseHelper.sendMessage(mUser, message);
            }
        });

        return view;
    }
}
