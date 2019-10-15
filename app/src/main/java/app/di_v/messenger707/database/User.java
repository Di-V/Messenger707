package app.di_v.messenger707.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey  @NonNull  @ColumnInfo(name = "user_id")    private String mId;
    @ColumnInfo(name = "user_name")    private String mUserName;
    @ColumnInfo(name = "user_img")    private int mUserImg;
    @ColumnInfo(name = "user_status")    private int mUserStatus;

    public User () {
        mId = UUID.randomUUID().toString();
    }

    @Ignore
    public User(String id, String userName) {
        mId = id;
        mUserName = userName;
        mUserImg = 1;
        mUserStatus = 1;
    }

    @Ignore
    public User(String userName) {
        mId = UUID.randomUUID().toString();
        mUserName = userName;
        mUserImg = 1;
        mUserStatus = 1;
    }

    @NonNull
    @Override
    public String toString() {
        return "User {" +
                " id" + getId() +
                " name " + getUserName() +
                " img " + getUserImg() +
                " status " + getUserStatus() +
                "}";
    }

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

    public int getUserImg() {
        return mUserImg;
    }

    public void setUserImg(int userImg) {
        mUserImg = userImg;
    }

    public int getUserStatus() {
        return mUserStatus;
    }

    public void setUserStatus(int status) {
        mUserStatus = status;
    }
}
