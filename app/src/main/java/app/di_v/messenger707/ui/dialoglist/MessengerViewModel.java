package app.di_v.messenger707.ui.dialoglist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import app.di_v.messenger707.data.MessengerRepository;
import app.di_v.messenger707.data.model.ListUsers;

public class MessengerViewModel extends AndroidViewModel {
    private MessengerRepository mRepository;

    private LiveData<List<ListUsers>> mListAllUsers;

    public MessengerViewModel (Application application) {
        super(application);
        mRepository = new MessengerRepository(application);
    }

    public LiveData<List<ListUsers>> getListAllUsers() {
        mListAllUsers = mRepository.getListAllUsers();
        return mListAllUsers;
    }
}
