package com.example.sqldatabasepoketracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class Pokemon {
    public static final String DBNAME = "POKEDB";
    public static final String TABLE_NAME = "PokeData";
    public static final String COL_ID = "_ID";
    public static final String COLUMN1_NATIONAL_NUMBER = "National_Number";
    public static final String COLUMN2_NAME = "Name";
    public static final String COLUMN3_SPECIES = "Species";
    public static final String COLUMN4_GENDER = "Gender";
    public static final String COLUMN5_HEIGHT = "Height";
    public static final String COLUMN6_WEIGHT = "Weight";
    public static final String COLUMN7_LEVEL = "Level";
    public static final String COLUMN8_HP = "HP";
    public static final String COLUMN9_ATTACK = "Attack";
    public static final String COLUMN10_DEFENSE = "Defense";

    public static final String AUTHORITY = "com.example.database";
    public static final Uri contentURI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
    private MyContentProvider.MainDatabaseHelper SQLHelper;


    public static final String[] FIELDS = { COLUMN1_NATIONAL_NUMBER, COLUMN2_NAME, COLUMN3_SPECIES, COLUMN4_GENDER, COLUMN5_HEIGHT, COLUMN6_WEIGHT, COLUMN7_LEVEL, COLUMN8_HP, COLUMN9_ATTACK, COLUMN10_DEFENSE};


    public static final String CREATE_DB_QUERY = " CREATE TABLE " + TABLE_NAME + " ( " + COL_ID +
            " INTEGER PRIMARY KEY,"
            + COLUMN1_NATIONAL_NUMBER + " INTEGER, "
            + COLUMN2_NAME + "  TEXT, "
            +COLUMN3_SPECIES + "  TEXT, "
            +COLUMN4_GENDER + "  TEXT, "
            +COLUMN5_HEIGHT + "  DOUBLE, "
            +COLUMN6_WEIGHT + "  DOUBLE, "
            +COLUMN7_LEVEL + "  INTEGER, "
            +COLUMN8_HP + "  INTEGER, "
            +COLUMN9_ATTACK + "  INTEGER, "
            + COLUMN10_DEFENSE + "INTEGER)";

    long id = -1;
    int num = 0;
    String name = "";
    String species = "";
    String gender = "";
    double height = 0.0;
    double weight = 0.0;
    int level = 0;
    int hp = 0;
    int attack = 0;
    int defense = 0;

    public Pokemon() {}

    public Pokemon(final Cursor cursor) {
        this.id = cursor.getLong(0);
        this.num = cursor.getInt(1);
        this.name = cursor.getString(2);
        this.species = cursor.getString(3);
        this.gender = cursor.getString(4);
        this.height = cursor.getDouble(5);
        this.weight = cursor.getDouble(6);
        this.level = cursor.getInt(7);
        this.hp = cursor.getInt(8);
        this.attack = cursor.getInt(9);
        this.defense = cursor.getInt(10);
    }


    public ContentValues getContent( ){
        final ContentValues cv = new ContentValues();
        cv.put(COLUMN1_NATIONAL_NUMBER, num);
        cv.put(COLUMN2_NAME, name);
        cv.put(COLUMN3_SPECIES, species);
        cv.put(COLUMN4_GENDER, gender);
        cv.put(COLUMN5_HEIGHT, height);
        cv.put(COLUMN6_WEIGHT, weight);
        cv.put(COLUMN7_LEVEL, level);
        cv.put(COLUMN8_HP, hp);
        cv.put(COLUMN9_ATTACK, attack);
        cv.put(COLUMN10_DEFENSE, defense);

        return cv;
    }


//    public boolean putP(String num, String name, String spec, String gen, double height, double weight, int level, int hp, int attack, int defense){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN1_NUM, num);
//        cv.put(COLUMN2_NAME, name);
//        cv.put(COLUMN3_SPECIES, spec);
//        cv.put(COLUMN4_GENDER, gen);
//        cv.put(COLUMN5_HEIGHT, height);
//        cv.put(COLUMN6_WEIGHT, weight);
//        cv.put(COLUMN7_LEVEL, level);
//        cv.put(COLUMN8_HP, hp);
//        cv.put(COLUMN9_ATTACK, attack);
//        cv.put(COLUMN10_DEFENSE, defense);
//        long result = db.insert(TABLE_NAME, null, cv);
//        if (result == -1) {return false;}  else {return true;}
//    }

}
