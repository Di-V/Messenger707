package app.di_v.messenger707.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import app.di_v.messenger707.model.Messages;

import static app.di_v.messenger707.database.DatabaseSchema.MessageTable.*;

public class SQLCursorWrapper extends CursorWrapper {

    public SQLCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Messages getMessage() {
        String uuidString = getString(getColumnIndex(_ID));
        String userName = getString(getColumnIndex(COLUMN_USER_NAME));
        String userMessage = getString(getColumnIndex(COLUMN_MESSAGE));
        Long messageTime = getLong(getColumnIndex(COLUMN_MESSAGE_TIME));
        Integer status = getInt(getColumnIndex(COLUMN_STATUS));

        Messages message = new Messages(UUID.fromString(uuidString));
        message.setUserName(userName);
        message.setMessage(userMessage);
        message.setMessageTime(messageTime);
        message.setStatus(status);

        return message;
    }
}
