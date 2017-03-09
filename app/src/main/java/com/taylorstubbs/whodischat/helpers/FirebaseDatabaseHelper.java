package com.taylorstubbs.whodischat.helpers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.taylorstubbs.whodischat.models.User;

/**
 * Created by taylorstubbs on 3/8/17.
 */

public class FirebaseDatabaseHelper {
    private static final String TAG = "FirebaseDatabaseHelper";
    private static final String USER_REF = "users";
    private static final String USER_MESSAGE_THREAD_REF = "messageThread";
    private static final String USER_SEARCHING_FOR_THREAD_REF = "searchForThread";

    private FirebaseDatabase mDatabase;

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    public Task<Void> createNewFirebaseUser(User user) {
        return mDatabase.getReference().child(USER_REF).child(user.userId).setValue(user);
    }

    private DatabaseReference getReference(String refString) {
        return mDatabase.getReference(refString);
    }
}
