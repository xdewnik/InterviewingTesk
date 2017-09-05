package homedevstudio.com.interviewingtesk.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by xdewnik on 05.09.2017.
 */

public class DBContracts {

    public static final String AUTHORITY = "homedevstudio.com.interviewingtesk";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_USERS = "users";

    public DBContracts(){
    }

    public static class USERS implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USERS).build();

        public static final String TABLE_NAME = "users";

        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASSWORD = "password";
    }

}
