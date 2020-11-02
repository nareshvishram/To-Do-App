package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelperClass extends SQLiteOpenHelper {
    public SqliteHelperClass(@Nullable Context context) {
        super(context, "todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todo(lid INTEGER primary key AUTOINCREMENT ,title text not null,date varchar(100),des text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insert_data(String title,String date,String des)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",title);
        contentValues.put("date",date);
        contentValues.put("des",des);
        long l=sqLiteDatabase.insert("todo",null,contentValues);
        return l;
    }
    public Cursor fetch_data()
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from todo",null);
        return  cursor;

    }
    public long delete_by_title(String title)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        long l=sqLiteDatabase.delete("todo","title=?",new String[]{title});
        return l;
    }
    public Cursor fetch_data_withTitle(String title)
    {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
       Cursor cursor= sqLiteDatabase.rawQuery("select * from todo where title='"+title+"'",null);
       return  cursor;
    }
}
