package com.example.aspire.universitydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

class DataBaseHelper extends SQLiteOpenHelper {public static final String DATABASE_NAME = "Student.db";

    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DEPARTMENT";
    public static final String COL_4 = "UNIVERSITY";
    public static final String COL_5 = "SESSION";
    public static final String COL_6 = "GENDER";



    public DataBaseHelper(Context context) { super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER,NAME TEXT,DEPARTMENT TEXT,UNIVERSITY TEXT,SESSION INTEGER,GENDER TEXT)");
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String id,String name,String department,String university,String session,String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,department);
        contentValues.put(COL_4,university);
        contentValues.put(COL_5,session);
        contentValues.put(COL_6,gender);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public  Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }

    public boolean updateData(String id, String name, String department, String university, String session,String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,department);
        contentValues.put(COL_4,university);
        contentValues.put(COL_5,session);
        contentValues.put(COL_6,gender);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        return true;
    }
}
