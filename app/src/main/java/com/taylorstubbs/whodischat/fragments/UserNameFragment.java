package com.taylorstubbs.whodischat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taylorstubbs.whodischat.R;

/**
 * Created by taylorstubbs on 3/6/17.
 */

public class UserNameFragment extends Fragment {
    private static final String TAG = "UserNameFragment";

    public static UserNameFragment newInstance() {
        return new UserNameFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name_user, container, false);
    }
}
