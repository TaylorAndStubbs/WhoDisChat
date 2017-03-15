package com.taylorstubbs.whodischat.helpers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.taylorstubbs.whodischat.models.Message;
import com.taylorstubbs.whodischat.models.User;

/**
 * A Helper class to help use firebase database.
 */

public class FirebaseDatabaseHelper {
    private static final String TAG = "FirebaseDatabaseHelper";
    private static final String USER_REF = "users";
    private static final String USER_MESSAGE_THREAD_REF = "messageThread";
    private static final String USER_SEARCHING_FOR_THREAD_REF = "searchingForThread";
    private static final String MESSAGE_THREAD_REF = "messageThreads";

    private FirebaseDatabase mDatabase;

    /**
     * Constructor.
     */
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    /**
     * Save user in the database.
     *
     * @param user  the user to insert into the database
     * @return      the Task.
     */
    public Task<Void> saveUser(User user) {
        return mDatabase.getReference().child(USER_REF).child(user.userId).setValue(user);
    }

    public Task<Void> findThread(String userId) {
        //Set userSearchingForThread to true
        return getReference(USER_REF).child(userId).child(USER_SEARCHING_FOR_THREAD_REF).setValue(true);
    }

    public Task<Void> sendMessage(User user, Message message) {
        return getReference(MESSAGE_THREAD_REF).child(user.messageThread).push().setValue(message);
    }

    public DatabaseReference getThread(String threadId) {
        return getReference(MESSAGE_THREAD_REF).child(threadId);
    }

    public DatabaseReference getUser(String userId) {
        return getReference(USER_REF).child(userId);
    }

    /**
     * Get database reference.
     *
     * @param refString the string to get a reference to
     * @return          the reference
     */
    private DatabaseReference getReference(String refString) {
        return mDatabase.getReference(refString);
    }
}
