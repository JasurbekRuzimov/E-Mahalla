package uz.ruzimovjasurbek.e_mahalla;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
public static final String DATABASE_NAME = "e_mahalla.db";
    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users ("
                + "id integer primary key autoincrement,"
                + "login text,"
                + "password text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }
    public Boolean insertData(String login, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContextValues contextValues = new ContextValues();
        contextValues.setLogin(login);
        contextValues.setPassword(password);

        long result = db.insert("users", null, contextValues.getContentValues());
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkLogin(String login){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where login = ?", new String[]{login});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkLoginPassword(String login, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where login = ? and password = ?", new String[]{login, password});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }


    public Boolean checkusername(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where login = ?", new String[]{user});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkusernamepassword(String user, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where login = ? and password = ?", new String[]{user, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
