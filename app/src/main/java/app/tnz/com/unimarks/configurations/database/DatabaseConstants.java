package app.tnz.com.unimarks.configurations.database;

/**
 * Created by Admin on 2016/08/21.
 */
public class DatabaseConstants {

        public static final String DATABASE_NAME="exam4me";
        public static final int DATABASE_VERSION=2;

        public static final String TABLE_NAME = "profile";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "student_name";
        public static final String COLUMN_SURNAME = "student_surname";

        public static final String CREATE_DATABASEA = "CREATE TABLE "
                + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT NOT NULL, "
                + COLUMN_SURNAME + " TEXT NOT NULL);";


        public static final String TABLE_NAMES = "account";
        public static final String COLUMN_EMAIL = "student_email";
        public static final String COLUMN_PASSWORD = "student_password";

        public static final String CREATE_DATABASEB = "CREATE TABLE "
                + TABLE_NAMES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_EMAIL + " TEXT NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL);";
}
