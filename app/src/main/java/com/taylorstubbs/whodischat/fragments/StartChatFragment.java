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
import com.taylorstubbs.whodischat.helpers.FirebaseAuthHelper;

/**
 * Fragment that lets the user begin searching for a chat.
 */

public class StartChatFragment extends Fragment {
    private static final String TAG = "StartChatFragment";

    private Button mStartChatButton;

    /**
     * Create a new StartChatFragment instance.
     *
     * @return  the fragment
     */
    public static StartChatFragment newInstance() {
        return new StartChatFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_start, container, false);

        mStartChatButton = (Button) view.findViewById(R.id.start_chat_button);

        mStartChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
