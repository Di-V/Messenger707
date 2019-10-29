package app.di_v.messenger707.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Relation;

import java.util.List;

public class UserMessages {
    @ColumnInfo(name = "user_id")    private String mId;
    @ColumnInfo(name = "user_name")    private String mUserName;

    @Relation(parentColumn = "user_id", entityColumn = "message_user_id")
    private List<Messages> mMessagesList;

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

    public List<Messages> getMessagesList() {
        return mMessagesList;
    }

    public void setMessagesList(List<Messages> messagesList) {
        mMessagesList = messagesList;
    }
}
