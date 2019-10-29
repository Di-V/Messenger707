package app.di_v.messenger707.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import app.di_v.messenger707.data.model.Messages;

@Dao
interface MessagesDao {

    @Insert
    void insertMsg(Messages messages);

    @Delete
    void deleteMsg(Messages msg);
}
