package com.example.mdshahadathassan.friendsemail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Md.Shahadat Hassan on 28-03-17.
 */

public class MySqlLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1; //version of your db
    private static final String DATABASE_NAME="mydb.db"; // USer define db name
    private static final String TABLE_NAME="mytable"; //user define table name.
    private static final String COLUMN1="ID";
    private static final String COLUMN2="Name";
    private static final String COLUMN3="Email Id";

    public MySqlLite(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query="CREATE TABLE  "+TABLE_NAME+" (Id INTEGER PRIMARY KEY , NAME TEXT,EMAIL TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS "+TABLE_NAME); // if previous db found , delete it
        onCreate(db);
    }
    // for inserting data
    public boolean addToTable(String Id,String NAME,String EMAIL){
        ContentValues co= new ContentValues();
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        co.put(COLUMN1,Id);
        co.put(COLUMN2,NAME);
        co.put(COLUMN3,EMAIL);

        long chk= sqLiteDatabase.insert(TABLE_NAME,null,co);

        if(chk==-1){
            //data not inserted, return -1
            return  false;
        }
        else
            return true;

    }
    public Cursor display(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
                Cursor result;
                result=sqLiteDatabase.rawQuery("SELECT *FROM "+TABLE_NAME,null );
                return result;

    }

}

