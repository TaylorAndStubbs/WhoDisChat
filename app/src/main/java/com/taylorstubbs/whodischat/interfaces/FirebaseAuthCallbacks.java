package com.taylorstubbs.whodischat.interfaces;

import com.google.firebase.auth.FirebaseUser;

/**
 *  Callbacks for FirebaseAuthHelper.
 */

public interface FirebaseAuthCallbacks {
    void onAnonymousLogin(FirebaseUser user);
}
