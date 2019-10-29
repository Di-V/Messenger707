package app.di_v.messenger707.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import app.di_v.messenger707.data.model.Messages;
import app.di_v.messenger707.data.model.User;

@Database(entities = {User.class, Messages.class}, version = 1, exportSchema = false)
public abstract class MessengerRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract MessagesDao messagesDao();

    private static volatile MessengerRoomDatabase INSTANCE;

    public static MessengerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MessengerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MessengerRoomDatabase.class, "msg_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
