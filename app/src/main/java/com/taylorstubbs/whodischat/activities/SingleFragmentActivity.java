package com.taylorstubbs.whodischat.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.taylorstubbs.whodischat.R;

/**
 * Hosts a single fragment.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {
    private static final String TAG = "SingleFragmentActivity";

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
