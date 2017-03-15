package com.taylorstubbs.whodischat.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.taylorstubbs.whodischat.fragments.ChatFragment;
import com.taylorstubbs.whodischat.fragments.LoadingFragment;
import com.taylorstubbs.whodischat.fragments.StartChatFragment;
import com.taylorstubbs.whodischat.helpers.FirebaseDatabaseHelper;
import com.taylorstubbs.whodischat.helpers.FragmentHelper;
import com.taylorstubbs.whodischat.models.User;
import com.taylorstubbs.whodischat.utils.SharedPreferencesUtil;

/**
 * Activity that logs in the user, if they aren't already, and loads the appropriate fragment.
 */

public class LandingActivity extends SingleFragmentActivity implements StartChatFragment.StartChatFragmentCallbacks {
    private static final String TAG = "LandingActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mListener;
    private FirebaseDatabaseHelper mFirebaseDatabaseHelper;
    private FragmentHelper mFragmentHelper;
    private User mUser;

    @Override
    protected void onCreate(Bundle saveState) {
        super.onCreate(saveState);

        mAuth = FirebaseAuth.getInstance();
        mListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                //user logs in
                if (firebaseUser != null) {
                    onLogIn(mAuth.getCurrentUser());
                } else {
                    //TODO logged out event
                }
            }
        };
        mFirebaseDatabaseHelper = new FirebaseDatabaseHelper();
        mFragmentHelper = new FragmentHelper(this);

        //Check if this is a fresh install of the app and sign out to make sure their isn't an old session
        if (SharedPreferencesUtil.getIsFreshInstall(this)) {
            Log.d(TAG, "Fresh install");
            mAuth.signOut();
            SharedPreferencesUtil.setIsFreshInstall(this, false);
        }

        mAuth.addAuthStateListener(mListener);

        if (mAuth.getCurrentUser() == null) {
            mAuth.signInAnonymously();
        } else {
            onLogIn(mAuth.getCurrentUser());
        }
    }

    @Override
    protected Fragment createFragment() {
        return LoadingFragment.newInstance(LoadingFragment.TYPE_LOADING);
    }

    @Override
    public void searchingForChat() {
        mFragmentHelper.replaceFragment(LoadingFragment.newInstance(LoadingFragment.TYPE_SEARCHING));
    }

    private void onLogIn(final FirebaseUser firebaseUser) {
        mFirebaseDatabaseHelper.getUser(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user == null) {
                    mFirebaseDatabaseHelper.saveUser(new User(firebaseUser.getUid()));
                } else if (user.messageThread != null) {
                    mFragmentHelper.replaceFragment(ChatFragment.newInstance(user));
                } else if (user.searchingForThread) {
                   mFragmentHelper.replaceFragment(LoadingFragment.newInstance(LoadingFragment.TYPE_SEARCHING));
                } else {
                    mFragmentHelper.replaceFragment(StartChatFragment.newInstance(user));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO
            }
        });
    }
}
