package homedevstudio.com.interviewingtesk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import homedevstudio.com.interviewingtesk.db.DBContracts;
import homedevstudio.com.interviewingtesk.db.DBHelper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.usr)EditText userLogin;
    @BindView(R.id.psd)EditText userPassword;
    @BindView(R.id.btn)Button loginButton;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn)
    public void onLoginButtonClick() {
        String login = userLogin.getText().toString();
        String password = userPassword.getText().toString();
        boolean success =false;

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBContracts.USERS.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                if(login.equals(cursor.getString(cursor.getColumnIndex(DBContracts.USERS.COLUMN_LOGIN))) &&
                        password.equals(cursor.getString(cursor.getColumnIndex(DBContracts.USERS.COLUMN_PASSWORD)))) {
                        success = true;

                    Intent intent = new Intent(this, MapsActivity.class);
                    startActivity(intent);


                }

            } while (cursor.moveToNext());
        }
        if(!success){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Не верный логин или пароль!", Toast.LENGTH_SHORT);
            toast.show();
        }
        cursor.close();


        }

    }

