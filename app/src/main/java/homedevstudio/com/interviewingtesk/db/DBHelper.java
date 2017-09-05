package homedevstudio.com.interviewingtesk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xdewnik on 05.09.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;


    private final static String DATABASE_NAME = "loginDb";


    private final String CREATE_USER_TABLE = "CREATE TABLE "+
            DBContracts.USERS.TABLE_NAME + " ("+
            DBContracts.USERS._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DBContracts.USERS.COLUMN_LOGIN + " TEXT NOT NULL, "+
            DBContracts.USERS.COLUMN_PASSWORD + " TEXT NOT NULL "+
            ");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME ,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);

        db.execSQL("INSERT INTO " + DBContracts.USERS.TABLE_NAME +
                "( " + DBContracts.USERS.COLUMN_LOGIN + ", " + DBContracts.USERS.COLUMN_PASSWORD+ "  ) values ('Login','password' );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContracts.USERS.TABLE_NAME);

    }
}
