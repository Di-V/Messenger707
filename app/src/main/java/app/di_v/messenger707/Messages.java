package app.di_v.messenger707;

public class Messages {
    private String mMessage;
    private String mUserName;

    public Messages() {

    }

    public Messages(String message, String userName) {
        mMessage = message;
        mUserName = userName;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }
}
