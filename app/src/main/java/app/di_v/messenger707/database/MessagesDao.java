package app.di_v.messenger707.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface MessagesDao {

    @Insert
    void insertMsg(Messages messages);

    // Удаление message из бд
    @Delete
    void deleteMsg(Messages msg);

    // Получение всех messages из бд
    @Query("SELECT * FROM messages_table")
    LiveData<List<Messages>> getAllMessages();
}
