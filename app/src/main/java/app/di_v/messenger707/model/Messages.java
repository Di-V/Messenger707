package app.di_v.messenger707.model;

import java.util.Date;
import java.util.UUID;

public class Messages {
    private UUID mId;
    private String mUserName;
    private String mMessage;
    private long mMessageTime;
    private int mStatus;

    public Messages(UUID id) {
        mId = id;
    }

    public Messages(String message, String userName, int status) {
        mId = UUID.randomUUID();
        mMessage = message;
        mUserName = userName;
        mMessageTime = new Date().getTime();
        mStatus = status;
    }

    public UUID getId() {
        return mId;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public long getMessageTime() {
        return mMessageTime;
    }

    public void setMessageTime(long messageTime) {
        mMessageTime = messageTime;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }
}
