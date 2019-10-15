package app.di_v.messenger707;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import app.di_v.messenger707.database.MessengerRoomDatabase;
import app.di_v.messenger707.database.UserDao;
import app.di_v.messenger707.database.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private UserDao userDao;
    private MessengerRoomDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        db = Room.inMemoryDatabaseBuilder(context, MessengerRoomDatabase.class).build();
        userDao = db.userDao();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        User user = new User();
        user.setUserName("Tester");
        userDao.insert(user);
        List<User> byName = userDao.getAllUsersWithName("Tester");
        assertThat(byName.get(0), equalTo(user));
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }
}
