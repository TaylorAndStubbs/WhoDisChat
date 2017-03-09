package com.taylorstubbs.whodischat.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.taylorstubbs.whodischat.fragments.StartChatFragment;
import com.taylorstubbs.whodischat.helpers.FirebaseAuthHelper;
import com.taylorstubbs.whodischat.helpers.FirebaseDatabaseHelper;
import com.taylorstubbs.whodischat.interfaces.FirebaseAuthCallbacks;
import com.taylorstubbs.whodischat.utils.AccountUtil;
import com.taylorstubbs.whodischat.utils.SharedPreferencesUtil;

/**
 * Activity that logs in the user, if they aren't already, and loads the appropriate fragment.
 */

public class LandingActivity extends SingleFragmentActivity implements FirebaseAuthCallbacks {
    private static final String TAG = "LandingActivity";

    private FirebaseAuthHelper mFirebaseAuthHelper;
    private FirebaseDatabaseHelper mFirebaseDatabaseHelper;
    private String mUserId;
    private String mUserPassword;

    @Override
    protected void onCreate(Bundle saveState) {
        super.onCreate(saveState);

        mFirebaseAuthHelper = new FirebaseAuthHelper();
        mFirebaseDatabaseHelper = new FirebaseDatabaseHelper();
        mFirebaseAuthHelper.setCallbacks(this);
        mUserId = SharedPreferencesUtil.getUserId(this);
        mUserPassword = SharedPreferencesUtil.getUserPassword(this);

        //if credentials exist on device
        if (mUserId != null && mUserPassword != null) {
            //if user is logged in
            if (mFirebaseAuthHelper.checkAuth() != null) {
                //TODO proceed to chat
                Log.d(TAG, "account already logged in, proceed to chat");
            //if user is not logged in
            } else {
                mFirebaseAuthHelper.login(mUserId, mUserPassword);
            }
        //if credentials don't exist on device
        } else {
            mFirebaseAuthHelper.createAnonymousUser();
        }
    }

    @Override
    protected Fragment createFragment() {
        return StartChatFragment.newInstance();
    }

    @Override
    public void onLogin(FirebaseUser user) {
        //TODO proceed to chat
        Log.d(TAG, "user logged in");
    }

    @Override
    public void onAnonymousLogin(FirebaseUser user) {
        mUserId = AccountUtil.appendDomainToId(user.getUid());
        mUserPassword = AccountUtil.createRandomPassword();

        //Save id and password to device
        SharedPreferencesUtil.setUserId(this, mUserId);
        SharedPreferencesUtil.setUserPassword(this, mUserPassword);

        //log out of anonymous session
        mFirebaseAuthHelper.logout();
        //create user with id and password
        mFirebaseAuthHelper.createUser(mUserId, mUserPassword);
    }

    @Override
    public void onCreateUser(FirebaseUser user) {
        //TODO proceed to chat
        Log.d(TAG, "user created");
    }
}
