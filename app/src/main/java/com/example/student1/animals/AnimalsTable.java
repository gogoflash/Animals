package com.example.student1.animals;

import android.database.sqlite.SQLiteDatabase;

public class AnimalsTable
{
    public static final String TABLE_ANIMALS = "animals";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANIMAL = "animal";

    private static final String ANIMALS_CREATE = "create table "
            + TABLE_ANIMALS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_ANIMAL
            + " text not null);";

    public static void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ANIMALS_CREATE);
        populate(sqLiteDatabase);
    }

    private static void populate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("insert into animals (animal) values ('crocodile')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('leo')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('wolf')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('cat')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('hippo')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('butterfly')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('bird')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('fox')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('whale')");

        sqLiteDatabase.execSQL("insert into animals (animal) values ('dog')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('sea star')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('medusa')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('owl')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('eagle')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('marabu')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('snake')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('ant')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('bat')");

        sqLiteDatabase.execSQL("insert into animals (animal) values ('hare')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('crow')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('tiger')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('puma')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('bear')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('falcon')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('turtle')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('snout')");
        sqLiteDatabase.execSQL("insert into animals (animal) values ('dolphin')");
    }

    public static void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMALS);
        onCreate(sqLiteDatabase);
    }
}