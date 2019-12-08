package app.di_v.messenger707.ui.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import app.di_v.messenger707.data.MessengerRepository
import app.di_v.messenger707.data.model.Messages
import app.di_v.messenger707.data.model.UserMessages

class DialogViewModel(application: Application) : AndroidViewModel(application) {
    private var mRepository: MessengerRepository = MessengerRepository(application)
    var allMessagesFromUser: LiveData<UserMessages?>? = null

    fun getAllMessagesFromUser(id: String): LiveData<UserMessages?>? {
        allMessagesFromUser = mRepository!!.getAllMessagesFromUser(id)
        return allMessagesFromUser
    }

    fun insertMsg(msg: Messages?) {
        mRepository!!.insertMsg(msg)
    }
}