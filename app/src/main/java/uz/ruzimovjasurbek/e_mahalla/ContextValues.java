package uz.ruzimovjasurbek.e_mahalla;

import android.content.ContentValues;

public class ContextValues {
    private String login;
    private String password;

    public ContextValues() {
    }

    public ContextValues(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("password", password);
        return contentValues;
    }
}
