package app.di_v.messenger707.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import app.di_v.messenger707.data.model.ListUsers;
import app.di_v.messenger707.data.model.Messages;
import app.di_v.messenger707.data.model.User;
import app.di_v.messenger707.data.model.UserMessages;

public class MessengerViewModel extends AndroidViewModel {
    private MessengerRepository mRepository;

    private LiveData<UserMessages> mAllMessagesFromUser;
    private LiveData<List<ListUsers>> mListAllUsers;

    public MessengerViewModel (Application application) {
        super(application);
        mRepository = new MessengerRepository(application);
    }

    public LiveData<UserMessages> getAllMessagesFromUser(@NonNull String id) {
        mAllMessagesFromUser = mRepository.getAllMessagesFromUser(id);
        return mAllMessagesFromUser;
    }

    public LiveData<List<ListUsers>> getListAllUsers() {
        mListAllUsers = mRepository.getListAllUsers();
        return mListAllUsers;
    }

    public void insert(User user) {
        mRepository.insert(user);
    }

    public void insertMsg(Messages msg) {
        mRepository.insertMsg(msg);
    }
}
