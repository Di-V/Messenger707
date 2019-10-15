package app.di_v.messenger707.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MessengerRepository {

    private UserDao mUserDao;
    private MessagesDao mMessagesDao;
    private LiveData<List<User>> mAllUsers;
    private LiveData<List<Messages>> mAllMessages;

    public MessengerRepository(Application application) {
        MessengerRoomDatabase db = MessengerRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mMessagesDao = db.messagesDao();
        mAllUsers = mUserDao.getAllUsers();
        mAllMessages = mMessagesDao.getAllMessages();
    }

    public LiveData<List<User>> getAllUsersRep() {
        return mAllUsers;
    }

    public LiveData<List<Messages>> getAllMessages() {
        return  mAllMessages;
    }

    public void insert (User user) {
        new insertAsyncTask(mUserDao).execute(user);
    }

    public void insertMsg (Messages msg) {
        new insertMsgAsyncTask(mMessagesDao).execute(msg);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... users) {
            Log.d("Rep:", " data for tsk: " + users.length + ";  " + users[0]);
            mAsyncTaskDao.insert(users[0]);
            return null;
        }
    }

    private static class insertMsgAsyncTask extends AsyncTask<Messages, Void, Void> {

        private MessagesDao mAsyncTaskDao;

        insertMsgAsyncTask(MessagesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Messages... messages) {
            Log.d("Rep:", " data for tsk: " + messages.length + ";  " + messages[0]);
            mAsyncTaskDao.insertMsg(messages[0]);
            return null;
        }
    }
}
