package com.taylorstubbs.whodischat.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.taylorstubbs.whodischat.R;

/**
 * Helper for working with fragments
 */

public class FragmentHelper {
    private static final String TAG = "FragmentHelper";

    private FragmentManager mFragmentManager;

    /**
     * Constructor.
     *
     * @param fragmentActivity  the calling activity
     */
    public FragmentHelper(FragmentActivity fragmentActivity) {
        mFragmentManager = fragmentActivity.getSupportFragmentManager();
    }

    /**
     * Add a fragment at the container.
     *
     * @param fragment  the fragment to add
     */
    public void addFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    /**
     * Replace a fragment at the container.
     *
     * @param fragment  the fragment to replace
     */
    public void replaceFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public Fragment getFragment() {
        return mFragmentManager.findFragmentById(R.id.fragment_container);
    }
}
