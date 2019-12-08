package app.di_v.messenger707.ui.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import app.di_v.messenger707.data.MessengerRepository
import app.di_v.messenger707.data.model.User

class UserViewModel(application: Application): AndroidViewModel(application) {
    private var mRepository: MessengerRepository = MessengerRepository(application)
    var user: LiveData<User?>? = null

    fun getUser(id: String): LiveData<User?>? {
        user = mRepository!!.getUser(id)
        return user
    }
}