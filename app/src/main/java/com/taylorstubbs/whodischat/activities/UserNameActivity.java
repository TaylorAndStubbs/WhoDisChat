package com.taylorstubbs.whodischat.activities;

import android.support.v4.app.Fragment;

import com.taylorstubbs.whodischat.fragments.UserNameFragment;

/**
 * Created by taylorstubbs on 3/6/17.
 */

public class UserNameActivity extends SingleFragmentActivity {
    private static final String TAG = "UserNameActivity";

    @Override
    protected Fragment createFragment() {
        return UserNameFragment.newInstance();
    }
}
