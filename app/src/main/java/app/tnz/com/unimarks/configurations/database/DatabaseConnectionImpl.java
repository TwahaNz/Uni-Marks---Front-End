package app.tnz.com.unimarks.configurations.database;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 2016/08/21.
 */
public class DatabaseConnectionImpl implements DatabaseConnection {

    private SQLiteOpenHelper database;

    public DatabaseConnectionImpl(SQLiteOpenHelper database){

        this.database = database;
    }

    @Override
    public void open_database() {

        this.database.getWritableDatabase();
    }

    @Override
    public void close_database() {

        this.database.close();
    }
}
