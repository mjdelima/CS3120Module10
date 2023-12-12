package com.delima.mod10database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "module10";
    private static final int DB_VERSION = 1;
    private static final String TB_NAME = "accounts";
    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        String query = "CREATE TABLE " + TB_NAME +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT, " +
                COL_PASSWORD + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TB_NAME;
        db.execSQL(query);
        onCreate(db);

    }

    void addAccount(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase(); //get writable db
        ContentValues cv = new ContentValues(); //hold content values to be passed in the db
        cv.put(COL_USERNAME, username);
        cv.put(COL_PASSWORD, password);

        long result = db.insert(TB_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed Insert", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Account Added!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TB_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String userid, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase(); //get writable db
        ContentValues cv = new ContentValues(); //hold content values to be passed in the db
        cv.put(COL_USERNAME, username);
        cv.put(COL_PASSWORD, password);

        long result = db.update(TB_NAME, cv, " id=?", new String[]{userid});
        if(result == -1){
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Account Updated!", Toast.LENGTH_SHORT).show();
        }

    }


    void removeData(String userid){
        SQLiteDatabase db = this.getWritableDatabase(); //get writable db

        long result = db.delete(TB_NAME, " id=?", new String[]{userid});
        if(result == -1){
            Toast.makeText(context, "Deletion Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Account Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

}
