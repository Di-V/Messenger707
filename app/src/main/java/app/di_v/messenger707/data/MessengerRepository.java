package app.di_v.messenger707.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import app.di_v.messenger707.data.model.ListUsers;
import app.di_v.messenger707.data.model.Messages;
import app.di_v.messenger707.data.model.User;
import app.di_v.messenger707.data.model.UserMessages;

public class MessengerRepository {

    private UserDao mUserDao;
    private MessagesDao mMessagesDao;
    private LiveData<UserMessages> mMessagesList;
    private LiveData<List<ListUsers>> mUserList;

    public MessengerRepository(Application application) {
        MessengerRoomDatabase db = MessengerRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mMessagesDao = db.messagesDao();
    }

    public LiveData<UserMessages> getAllMessagesFromUser(String id) {
        mMessagesList = mUserDao.getAllMessagesFromUser(id);
        return  mMessagesList;
    }

    public LiveData<List<ListUsers>> getListAllUsers() {
        mUserList = mUserDao.getListAllUsers();
        return mUserList;
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
            mAsyncTaskDao.insertMsg(messages[0]);
            return null;
        }
    }
}
