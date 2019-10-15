package app.di_v.messenger707.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MessengerViewModel extends AndroidViewModel {
    private MessengerRepository mRepository;

    private LiveData<List<User>> mAllUsers;
    private LiveData<List<Messages>> mAllMessages;

    public MessengerViewModel (Application application) {
        super(application);
        mRepository = new MessengerRepository(application);
        mAllUsers = mRepository.getAllUsersRep();
        mAllMessages = mRepository.getAllMessages();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public LiveData<List<Messages>> getAllMessages() {
        return mAllMessages;
    }

    public void insert(User user) {
        mRepository.insert(user);
    }

    public void insertMsg(Messages msg) {
        mRepository.insertMsg(msg);
    }
}
