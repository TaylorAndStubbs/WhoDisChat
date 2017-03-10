package com.taylorstubbs.whodischat.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.taylorstubbs.whodischat.utils.ByteUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User.
 */

@IgnoreExtraProperties
public class User implements Parcelable {
    private static final String TAG = "User";

    public String userId;
    public String messageThread;
    public Boolean searchingForThread;

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User() {
        //Default Constructor required
    }

    public User(String userId) {
        this.userId = userId;
        this.searchingForThread = false;
    }

    public User(Parcel in) {
        userId = in.readString();
        messageThread = in.readString();
        searchingForThread = ByteUtil.byteToBoolean(in.readByte());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(messageThread);
        dest.writeByte(ByteUtil.booleanToByte(searchingForThread));
    }

    /**
     * Create map of object
     *
     * @return  the map
     */
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("messageThread", messageThread);
        result.put("searchingForThread", searchingForThread);

        return result;
    }
}
