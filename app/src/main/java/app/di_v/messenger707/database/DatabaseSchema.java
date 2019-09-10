package app.di_v.messenger707.database;

import android.provider.BaseColumns;

public class DatabaseSchema {

    private DatabaseSchema() {}

    public static class MessageTable implements BaseColumns {
        public static final String TABLE_NAME = "messages";
        public static final String COLUMN_USER_NAME = "user";
        public static final String COLUMN_MESSAGE = "message";
        public static final String COLUMN_MESSAGE_TIME = "time";
        public static final String COLUMN_STATUS = "status";
    }

    public static final String SQL_CREATE_MESSAGES =
            "CREATE TABLE " + MessageTable.TABLE_NAME + " (" +
                    MessageTable._ID + " INTEGER PRIMARY KEY," +
                    MessageTable.COLUMN_USER_NAME + " TEXT," +
                    MessageTable.COLUMN_MESSAGE + " TEXT)" +
                    MessageTable.COLUMN_MESSAGE_TIME + " INTEGER," +
                    MessageTable.COLUMN_STATUS + " INTEGER,";

    public static final String SQL_DELETE_MESSAGES =
            "DROP TABLE IF EXISTS " + MessageTable.TABLE_NAME;
}
