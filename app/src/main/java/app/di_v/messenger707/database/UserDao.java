package app.di_v.messenger707.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User User);

    // Удаление User из бд
    @Delete
    void deleteUser(User user);

    // Получение всех User из бд
    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    // Получение всех User из бд с условием
    @Query("SELECT * FROM user_table WHERE user_name LIKE :name")
    List<User> getAllUsersWithName(String name);
}
