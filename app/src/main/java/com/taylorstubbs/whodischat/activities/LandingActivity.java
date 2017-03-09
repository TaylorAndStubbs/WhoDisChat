package com.taylorstubbs.whodischat.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.taylorstubbs.whodischat.fragments.StartChatFragment;
import com.taylorstubbs.whodischat.helpers.FirebaseAuthHelper;
import com.taylorstubbs.whodischat.helpers.FirebaseDatabaseHelper;
import com.taylorstubbs.whodischat.interfaces.FirebaseAuthCallbacks;
import com.taylorstubbs.whodischat.models.User;
import com.taylorstubbs.whodischat.utils.AccountUtil;
import com.taylorstubbs.whodischat.utils.SharedPreferencesUtil;

/**
 * Activity that logs in the user, if they aren't already, and loads the appropriate fragment.
 */

public class LandingActivity extends SingleFragmentActivity implements FirebaseAuthCallbacks {
    private static final String TAG = "LandingActivity";

    private FirebaseAuthHelper mFirebaseAuthHelper;
    private FirebaseDatabaseHelper mFirebaseDatabaseHelper;
    private String mUserEmail;
    private String mUserPassword;

    @Override
    protected void onCreate(Bundle saveState) {
        super.onCreate(saveState);

        mFirebaseAuthHelper = new FirebaseAuthHelper();
        mFirebaseDatabaseHelper = new FirebaseDatabaseHelper();
        mFirebaseAuthHelper.setCallbacks(this);
        mUserEmail = SharedPreferencesUtil.getUserId(this);
        mUserPassword = SharedPreferencesUtil.getUserPassword(this);

        //if credentials exist on device
        if (mUserEmail != null && mUserPassword != null) {
            //if user is logged in
            if (mFirebaseAuthHelper.checkAuth() != null) {
                //TODO proceed to chat
                Log.d(TAG, "account already logged in, proceed to chat");
            //if user is not logged in
            } else {
                mFirebaseAuthHelper.login(mUserEmail, mUserPassword);
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
    public void onAnonymousLogin(FirebaseUser firebaseUser) {
        User user = new User(firebaseUser.getUid());
        mUserEmail = AccountUtil.appendDomainToId(firebaseUser.getUid());
        mUserPassword = AccountUtil.createRandomPassword();

        //Save id and password to device
        SharedPreferencesUtil.setUserId(this, mUserEmail);
        SharedPreferencesUtil.setUserPassword(this, mUserPassword);

        //log out of anonymous session
        mFirebaseAuthHelper.logout();
        //create user with id and password
        mFirebaseAuthHelper.createUser(mUserEmail, mUserPassword);
        //save user in database
        mFirebaseDatabaseHelper.saveUser(user).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //TODO indicate user can now log in
                Log.d(TAG, "User created in database");
            }
        });
    }

    @Override
    public void onCreateUser(FirebaseUser user) {
        //TODO proceed to chat
        Log.d(TAG, "user created");
    }
}
