package com.taylorstubbs.whodischat.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.taylorstubbs.whodischat.R;
import com.taylorstubbs.whodischat.helpers.FragmentHelper;

/**
 * Hosts a single fragment.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {
    private static final String TAG = "SingleFragmentActivity";

    private FragmentHelper mFragmentHelper;

    /**
     * Create the fragment to host.
     *
     * @return  the fragment
     */
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle saveState) {
        super.onCreate(saveState);
        setContentView(R.layout.acitivty_fragment_single);

        mFragmentHelper = new FragmentHelper(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            mFragmentHelper.addFragment(fragment);
        }
    }
}
