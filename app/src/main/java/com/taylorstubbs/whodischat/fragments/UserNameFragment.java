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

/**
 * Created by taylorstubbs on 3/6/17.
 */

public class UserNameFragment extends Fragment {
    private static final String TAG = "UserNameFragment";

    private String mUserName;
    private EditText mUserNameInput;
    private Button mStartChatButton;

    public static UserNameFragment newInstance() {
        return new UserNameFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name_user, container, false);

        mUserNameInput = (EditText) view.findViewById(R.id.user_name_input);
        mStartChatButton = (Button) view.findViewById(R.id.start_chat_button);

        mUserNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //NA
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUserName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //NA
            }
        });

        mStartChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
