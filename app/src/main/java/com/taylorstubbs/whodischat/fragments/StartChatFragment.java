package com.taylorstubbs.whodischat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.taylorstubbs.whodischat.R;
import com.taylorstubbs.whodischat.helpers.FirebaseDatabaseHelper;
import com.taylorstubbs.whodischat.models.User;

/**
 * Fragment that lets the user begin searching for a chat.
 */

public class StartChatFragment extends Fragment {
    private static final String TAG = "StartChatFragment";
    private static final String ARGS_USER = "user";

    private FirebaseDatabaseHelper mFirebaseDatabaseHelper;
    private StartChatFragmentCallbacks mCallbacks;
    private User mUser;

    private Button mStartChatButton;

    /**
     * Create new instance.
     *
     * @param user  the user
     * @return      the fragment
     */
    public static StartChatFragment newInstance(User user) {
        Bundle args = new Bundle();
        StartChatFragment fragment = new StartChatFragment();
        args.putParcelable(ARGS_USER, user);
        fragment.setArguments(args);

        return fragment;
    }

    public interface StartChatFragmentCallbacks {
        void searchingForChat();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabaseHelper = new FirebaseDatabaseHelper();
        mUser = getArguments().getParcelable(ARGS_USER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_start, container, false);

        mStartChatButton = (Button) view.findViewById(R.id.start_chat_button);

        mStartChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabaseHelper.findThread(mUser.userId).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mCallbacks.searchingForChat();
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (StartChatFragmentCallbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
