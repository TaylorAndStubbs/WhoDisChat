package com.taylorstubbs.whodischat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taylorstubbs.whodischat.R;

/**
 * Fragment displayed when logging in the user
 */

public class LoadingFragment extends Fragment {
    private static final String TAG = "LoadingFragment";

    /**
     * Create new instance.
     *
     * @return  the fragment
     */
    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }
}
