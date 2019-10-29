package app.di_v.messenger707.data.model;

import androidx.room.ColumnInfo;

public class ListUsers {
    @ColumnInfo(name = "user_id")    private String mId;
    @ColumnInfo(name = "user_name")    private String mUserName;
    @ColumnInfo(name = "user_img")    private String mUserImg;
    @ColumnInfo(name = "user_status")    private String mUserStatus;
    @ColumnInfo(name = "user_message")    private String mLastMessage;
    @ColumnInfo(name = "user_message_time")    private String mMessageTime;
    @ColumnInfo(name = "message_status")    private String mMessageStatus;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserImg() {
        return mUserImg;
    }

    public void setUserImg(String userImg) {
        mUserImg = userImg;
    }

    public String getUserStatus() {
        return mUserStatus;
    }

    public void setUserStatus(String userStatus) {
        mUserStatus = userStatus;
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public void setLastMessage(String lastMessage) {
        mLastMessage = lastMessage;
    }

    public String getMessageTime() {
        return mMessageTime;
    }

    public void setMessageTime(String messageTime) {
        mMessageTime = messageTime;
    }

    public String getMessageStatus() {
        return mMessageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        mMessageStatus = messageStatus;
    }
}
