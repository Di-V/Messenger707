package app.di_v.messenger707.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "messages_table")
public class Messages {
    @PrimaryKey    @NonNull    @ColumnInfo(name = "message_id")    private String mId;
    @NonNull @ColumnInfo(name = "message_user_id")    private String mUserId;
    @ColumnInfo(name = "user_message")    private String mMessage;
    @ColumnInfo(name = "user_message_time")    private long mMessageTime;
    @ColumnInfo(name = "message_status")    private int mStatus;

    public Messages() {
        mId = UUID.randomUUID().toString();
    }

    @Ignore
    public Messages(String message, String userId, int status) {
        mId = UUID.randomUUID().toString();
        mMessage = message;
        mUserId = userId;
        mMessageTime = new Date().getTime();
        mStatus = status;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userName) {
        mUserId = userName;
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
