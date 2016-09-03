package app.tnz.com.unimarks.repositories.student.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import app.tnz.com.unimarks.configurations.database.DatabaseConstants;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.factories.student.StudentAccountFactory;
import app.tnz.com.unimarks.repositories.student.StudentAccountRepository;

/**
 * Created by Admin on 2016/08/21.
 */

public class StudentAccountRepositoryImpl extends SQLiteOpenHelper implements StudentAccountRepository {

    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues contentValues;

    public static final String TABLE_NAME = "account";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "student_email";
    public static final String COLUMN_PASSWORD = "student_password";

    private static final String CREATE_DATABASE = "CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EMAIL + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL);";


    public StudentAccountRepositoryImpl(Context context) {

        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatabaseConstants.CREATE_DATABASEA);
        db.execSQL(DatabaseConstants.CREATE_DATABASEB);
    }

    public void openDatabaseForWriting(){

        db = this.getWritableDatabase();
    }

    public void openDatabaseForReading(){

        db = this.getWritableDatabase();
    }

    @Override
    public StudentAccount find_by_id(Long id) {

        openDatabaseForReading();

        final String[] TABLES = {COLUMN_ID, COLUMN_EMAIL, COLUMN_PASSWORD};
        final String[] ARGUMENTS = {String.valueOf(id)};
        final String WHERE_CLAUSE = COLUMN_ID + " =? ";

        cursor = db.query(TABLE_NAME,TABLES, WHERE_CLAUSE,ARGUMENTS,null,null,null,null);

        if (cursor.moveToFirst()) {

            final long ID = cursor.getLong(0);
            final String EMAIL = cursor.getString(1);
            final String PASSWORD = cursor.getString(2);

            return StudentAccountFactory.getStudent(ID, EMAIL, PASSWORD);
        }

        return null;
    }

    @Override
    public Long find_by_details(String email, String password) {

        openDatabaseForReading();

        final String[] TABLES = {COLUMN_ID, COLUMN_EMAIL, COLUMN_PASSWORD};
        final String[] ARGUMENTS = {email, password};
        final String WHERE_CLAUSE = COLUMN_EMAIL + " =? AND " + COLUMN_PASSWORD + " =? ";

        cursor = db.query(TABLE_NAME,TABLES, WHERE_CLAUSE,ARGUMENTS,null,null,null,null);

        if (cursor.moveToFirst()) {
            return cursor.getLong(0);
        }

        return null;
    }

    @Override
    public StudentAccount insert(StudentAccount studentAccount) {

        openDatabaseForWriting();
        setContentValues(studentAccount, null);

        final long ID = db.insert(TABLE_NAME, null, contentValues);

        studentAccount = StudentAccountFactory.getStudent(ID, studentAccount.getStudentEmail(), studentAccount.getStudentPassword());

        return studentAccount;
    }

    @Override
    public int update(StudentAccount studentAccount) {

        final long id = studentAccount.getId();
        final String[] ARGUMENTS = {String.valueOf(id)};
        final String WHERE_CLAUSE = COLUMN_ID + " =? ";

        openDatabaseForReading();

        setContentValues(studentAccount, studentAccount.getId());

        return db.update(TABLE_NAME, contentValues, WHERE_CLAUSE, ARGUMENTS);
    }

    @Override
    public StudentAccount delete(StudentAccount studentAccount) {
        return null;
    }

    @Override
    public Set<StudentAccount> find_all() {

        Set<StudentAccount> studentAccounts = new HashSet<>();

        openDatabaseForReading();

        cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            do {

                final long ID = cursor.getLong(0);
                final String EMAIL = cursor.getString(1);
                final String PASSWORD = cursor.getString(2);

                StudentAccount studentAccount = StudentAccountFactory.getStudent(ID, EMAIL, PASSWORD);
                studentAccounts.add(studentAccount);

            }while (cursor.moveToNext());
        }

        return studentAccounts;
    }

    @Override
    public int delete_all() {

        openDatabaseForReading();

        int rowsDeleted = db.delete(TABLE_NAME, null, null);

        return rowsDeleted;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void setContentValues(StudentAccount studentAccount, Long id) {

        contentValues = new ContentValues();

        if (id != null)
            contentValues.put(COLUMN_ID, id);

        contentValues.put(COLUMN_EMAIL, studentAccount.getStudentEmail());
        contentValues.put(COLUMN_PASSWORD, studentAccount.getStudentPassword());
    }

    public void closeDatabaseConnection(){
        this.close();
    }
}
