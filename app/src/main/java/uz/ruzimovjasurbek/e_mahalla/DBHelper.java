package uz.ruzimovjasurbek.e_mahalla;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "e_mahalla.db";
    private static final String TABLE_NAME = "mahalla";
    private static final String COL_ID = "id";
    private static final String Ismi = "Ismi";
    private static final String Familiyasi = "Familiyasi";
    private static final String Sharifi = "Otasining_ismi";
    private static final String Mahallasi = "Mahallasi";
    private static final String Jinsi = "Jinsi";
    private static final String TugilganYili = "Tugilgan_yili";
    private final Context context;

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users ("
                + "id integer primary key autoincrement,"
                + "login text,"
                + "password text" + ");");

        String query = "CREATE TABLE " + TABLE_NAME + " ("+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Ismi + " TEXT, " +
                Familiyasi + " TEXT, " +
                Sharifi + " TEXT, " +
                Mahallasi + " TEXT, " +
                Jinsi + " TEXT, " +
                TugilganYili + " TimeStamp DEFAULT CURRENT_TIMESTAMP" + ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContextValues contextValues = new ContextValues();
        contextValues.setLogin(login);
        contextValues.setPassword(password);

        long result = db.insert("users", null, contextValues.getContentValues());
        return result != -1;
    }

    public Boolean checkLogin(String login) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from users where login = ?", new String[]{login});
        return cursor.getCount() > 0;
    }

    public Boolean checkLoginPassword(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from users where login = ? and password = ?", new String[]{login, password});
        return cursor.getCount() > 0;
    }


    public Boolean checkusername(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from users where login = ?", new String[]{user});
        return cursor.getCount() > 0;
    }

    public Boolean checkusernamepassword(String user, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("select * from users where login = ? and password = ?", new String[]{user, pass});
        return cursor.getCount() > 0;
    }





    public void mahalla(String ismi, String familiyasi, String sharifi, String mahallasi, String jinsi, String tugilganYili) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Ismi, ismi);
        contentValues.put(Familiyasi, familiyasi);
        contentValues.put(Sharifi, sharifi);
        contentValues.put(Mahallasi, mahallasi);
        contentValues.put(Jinsi, jinsi);
        contentValues.put(TugilganYili, String.valueOf(tugilganYili));
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Xatolik yuz berdi !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ma'lumotlar saqlandi !", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

     void updateData(String row_id, String ismi, String familiyasi, String sharifi, String mahallasi, String jinsi, String tugilganYili) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Ismi, ismi);
        contentValues.put(Familiyasi, familiyasi);
        contentValues.put(Sharifi, sharifi);
        contentValues.put(Mahallasi, mahallasi);
        contentValues.put(Jinsi, jinsi);
        contentValues.put(TugilganYili, tugilganYili);
        long result = db.update(TABLE_NAME, contentValues, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Xatolik yuz berdi !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Ma'lumotlar yangilandi !", Toast.LENGTH_SHORT).show();
        }

    }

        void deleteOneRow(String row_id) {
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "Xatolik yuz berdi !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Ma'lumotlar o'chirildi !", Toast.LENGTH_SHORT).show();
            }
        }

        void deleteAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_NAME);
        }



}
