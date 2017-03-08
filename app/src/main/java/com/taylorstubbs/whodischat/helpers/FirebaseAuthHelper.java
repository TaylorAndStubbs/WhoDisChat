package com.taylorstubbs.whodischat.helpers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.taylorstubbs.whodischat.interfaces.FirebaseAuthCallbacks;

/**
 * Helper class for working with FirebaseAuth.
 */

public class FirebaseAuthHelper {
    private static final String TAG = "FirebaseAuthHelper";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuthCallbacks mCallbacks;

    /**
     * Constructor.
     */
    public FirebaseAuthHelper() {
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Set callbacks for auth methods.
     *
     * @param callbacks the interface
     */
    public void setCallbacks(FirebaseAuthCallbacks callbacks) {
        mCallbacks = callbacks;
    }

    /**
     * Check if user is authenticated.
     *
     * @return  the user
     */
    public FirebaseUser checkAuth() {
        return mAuth.getCurrentUser();
    }

    /**
     * Log in with the email and password.
     * 
     * @param email     the email
     * @param password  the password
     */
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mCallbacks.onLogin(mAuth.getCurrentUser());
                Log.d(TAG, String.valueOf(task.isSuccessful()));
            }
        });
    }

    /**
     * Create a user with an email and password.
     * 
     * @param email     the email
     * @param password  the password
     */
    public void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mCallbacks.onCreateUser(mAuth.getCurrentUser());
            }
        });
    }

    /**
     * Create an anonymous user.
     */
    public void createAnonymousUser() {
        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mCallbacks.onAnonymousLogin(mAuth.getCurrentUser());
            }
        });
    }

    /**
     * Logout.
     */
    public void logout() {
        mAuth.signOut();
    }
}
