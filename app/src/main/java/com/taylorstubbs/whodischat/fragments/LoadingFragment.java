package com.taylorstubbs.whodischat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taylorstubbs.whodischat.R;

/**
 * Fragment displayed when logging in the user
 */

public class LoadingFragment extends Fragment {
    private static final String TAG = "LoadingFragment";
    public static final String ARGS_LOADING_MESSAGE = "loadingMessage";
    public static final String TYPE_LOADING = "Loading";
    public static final String TYPE_SEARCHING = "Searching";

    private String mLoadingText;

    private TextView mLoadingTextView;

    /**
     * Create new loading fragment
     *
     * @param type  use static types, displays type of message
     * @return      the fragment
     */
    public static LoadingFragment newInstance(String type) {
        Bundle args = new Bundle();
        LoadingFragment fragment = new LoadingFragment();
        args.putString(ARGS_LOADING_MESSAGE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoadingText = getArguments().getString(ARGS_LOADING_MESSAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        mLoadingTextView = (TextView) view.findViewById(R.id.loading_text);
        mLoadingTextView.setText(mLoadingText);

        return view;
    }
}
