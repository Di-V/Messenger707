package app.di_v.messenger707.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new DbAsync(INSTANCE).execute();
                }
            };

    private static class DbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mDao;

        DbAsync(MessengerRoomDatabase db) {
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //mDao.deleteAll();
            User user = new User("user1");
            user.setUserStatus(1);
            mDao.insert(user);
            //user = new User("user2");
            //mDao.insertAllUser(user);
            return null;
        }
    }
}
