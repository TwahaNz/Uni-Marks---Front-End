package app.tnz.com.unimarks.repositories.student.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashSet;
import java.util.Set;

import app.tnz.com.unimarks.configurations.database.DatabaseConstants;
import app.tnz.com.unimarks.domain.student.StudentAccount;
import app.tnz.com.unimarks.domain.student.StudentProfile;
import app.tnz.com.unimarks.factories.student.StudentProfileFactory;
import app.tnz.com.unimarks.repositories.student.StudentProfileRepository;

/**
 * Created by Admin on 2016/08/31.
 */
public class StudentProfileRepositoryImpl extends SQLiteOpenHelper implements StudentProfileRepository {

    private SQLiteDatabase db;
    private Cursor cursor;
    private ContentValues contentValues;

    public static final String TABLE_NAME = "profile";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "student_name";
    public static final String COLUMN_SURNAME = "student_surname";

    private static final String CREATE_DATABASE = "CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_SURNAME + " TEXT NOT NULL);";


    public StudentProfileRepositoryImpl(Context context) {
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
    public StudentProfile find_by_id(Long id) {

        openDatabaseForReading();

        final String[] TABLES = {COLUMN_ID, COLUMN_NAME, COLUMN_SURNAME};
        final String[] ARGUMENTS = {String.valueOf(id)};
        final String WHERE_CLAUSE = COLUMN_ID + " =? ";

        cursor = db.query(TABLE_NAME,TABLES, WHERE_CLAUSE,ARGUMENTS,null,null,null,null);

        if (cursor.moveToFirst()) {

            final long ID = cursor.getLong(0);
            final String NAME = cursor.getString(1);
            final String SURNAME = cursor.getString(2);

            return StudentProfileFactory.getStudentProfile(ID, NAME, SURNAME);
        }

        return null;
    }

    @Override
    public StudentProfile insert(StudentProfile studentProfile) {

        openDatabaseForWriting();
        setContentValues(studentProfile, null);

        final long ID = db.insert(TABLE_NAME, null, contentValues);

        studentProfile = StudentProfileFactory.getStudentProfile(ID, studentProfile.getName(), studentProfile.getSurname());

        return studentProfile;
    }

    @Override
    public int update(StudentProfile studentProfile) {

        final long id = studentProfile.getId();
        final String[] ARGUMENTS = {String.valueOf(id)};
        final String WHERE_CLAUSE = COLUMN_ID + " =? ";

        openDatabaseForReading();

        setContentValues(studentProfile, studentProfile.getId());

        return db.update(TABLE_NAME, contentValues, WHERE_CLAUSE, ARGUMENTS);
    }

    @Override
    public StudentProfile delete(StudentProfile studentProfile) {
        return null;
    }

    @Override
    public Set<StudentProfile> find_all() {

        Set<StudentProfile> studentProfiles = new HashSet<>();

        openDatabaseForReading();

        cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            do {

                final long ID = cursor.getLong(0);
                final String NAME = cursor.getString(1);
                final String SURNAME = cursor.getString(2);

                StudentProfile studentProfile = StudentProfileFactory.getStudentProfile(ID, NAME, SURNAME);
                studentProfiles.add(studentProfile);

            }while (cursor.moveToNext());
        }

        return studentProfiles;
    }

    @Override
    public int delete_all() {

        openDatabaseForReading();

        int rowsDeleted = db.delete(TABLE_NAME, null, null);

        closeDatabaseConnection();

        return rowsDeleted;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void setContentValues(StudentProfile studentProfile, Long id) {

        contentValues = new ContentValues();

        if (id != null)
            contentValues.put(COLUMN_ID, id);

        contentValues.put(COLUMN_NAME, studentProfile.getName());
        contentValues.put(COLUMN_SURNAME, studentProfile.getSurname());
    }


    public void closeDatabaseConnection(){
        this.close();
    }
}
