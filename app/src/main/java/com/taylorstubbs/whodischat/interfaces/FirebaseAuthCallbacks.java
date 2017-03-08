package com.taylorstubbs.whodischat.interfaces;

import com.google.firebase.auth.FirebaseUser;

/**
 *  Callbacks for FirebaseAuthHelper.
 */

public interface FirebaseAuthCallbacks {
    void onLogin(FirebaseUser user);
    void onAnonymousLogin(FirebaseUser user);
    void onCreateUser(FirebaseUser user);
}
