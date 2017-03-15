package com.taylorstubbs.whodischat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.taylorstubbs.whodischat.R;
import com.taylorstubbs.whodischat.adapters.MessageAdapter;
import com.taylorstubbs.whodischat.helpers.FirebaseDatabaseHelper;
import com.taylorstubbs.whodischat.models.Message;
import com.taylorstubbs.whodischat.models.User;

import java.util.ArrayList;

/**
 * Created by taylorstubbs on 3/15/17.
 */

public class ChatFragment extends Fragment {
    private static final String TAG = "ChatFragment";
    private static final String ARGS_USER = "user";

    private FirebaseDatabaseHelper mDatabaseHelper;
    private User mUser;
    private String mTextString;
    private ChildEventListener mChildEventListener;
    private MessageAdapter mMessageAdapter;
    private LinearLayoutManager mManager;

    private Button mSendButton;
    private EditText mTextInput;
    private RecyclerView mTextRecycler;

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
        mMessageAdapter = new MessageAdapter(getContext(), new ArrayList<Message>(), mUser.userId);
        mManager = new LinearLayoutManager(getContext());
        mManager.setStackFromEnd(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                mMessageAdapter.addMessage(message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //NA
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //NA
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //NA
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO?
            }
        };

        mDatabaseHelper.getThread(mUser.messageThread).addChildEventListener(mChildEventListener);
    }

    @Override
    public void onPause() {
        mDatabaseHelper.getThread(mUser.messageThread).removeEventListener(mChildEventListener);
        super.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mSendButton = (Button) view.findViewById(R.id.send_button);
        mTextInput = (EditText) view.findViewById(R.id.text_input);
        mTextRecycler = (RecyclerView) view.findViewById(R.id.text_recycler);

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

        mTextRecycler.setLayoutManager(mManager);
        mTextRecycler.setAdapter(mMessageAdapter);

        return view;
    }
}
