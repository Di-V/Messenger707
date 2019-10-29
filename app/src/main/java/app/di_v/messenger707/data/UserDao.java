package app.di_v.messenger707.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import app.di_v.messenger707.data.model.ListUsers;
import app.di_v.messenger707.data.model.User;
import app.di_v.messenger707.data.model.UserMessages;

@Dao
public interface UserDao {

    @Insert
    void insert(User User);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM user_table WHERE user_name LIKE :name")
    List<User> getAllUsersWithName(String name);

    @Query("SELECT user_id, user_name, user_img FROM user_table WHERE user_id LIKE :id")
    LiveData<UserMessages> getAllMessagesFromUser(String id);

    @Query("SELECT *" +
            " FROM user_table LEFT JOIN messages_table" +
            " ON user_id == message_user_id" +
            " AND user_message_time = (SELECT MAX(user_message_time) FROM messages_table WHERE message_user_id = user_id)")
    LiveData<List<ListUsers>> getListAllUsers();
}
