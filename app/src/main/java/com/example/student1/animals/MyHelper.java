package com.example.student1.animals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Student1 on 13.09.2016.
 */
public class MyHelper extends SQLiteOpenHelper {

    public  static  final String mDatabase = "animals.db";

    public  static int mVersion = 1;

    public MyHelper(Context context) {

        super(context, mDatabase, null, mVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        AnimalsTable.onCreate(db);
        //Ne
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        AnimalsTable.onUpgrade(db, oldVersion, newVersion);
    }
}
