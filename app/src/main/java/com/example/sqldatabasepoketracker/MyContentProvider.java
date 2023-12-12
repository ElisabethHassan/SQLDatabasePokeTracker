package com.example.sqldatabasepoketracker;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static final String DBNAME = "POKEDB";
    public static final String TABLE_NAME = "PokeData";
    public static final String COLUMN1_NATIONAL_NUMBER = "National_Number";
    public static final String COLUMN2_NAME = "Name";
    public static final String COLUMN3_SPECIES = "Species";
    public static final String COLUMN4_GENDER = "Gender";
    public static final String COLUMN5_HEIGHT = "Height";
    public static final String COLUMN6_WEIGHT = "Weight";
    public static final String COLUMN7_LEVEL = "Level";
    public static final String COLUMN8_HP = "Hp";
    public static final String COLUMN9_ATTACK = "Attack";
    public static final String COLUMN10_DEFENSE = "Defense";
    public static final String AUTHORITY = "com.example.database";
    public static final Uri contentURI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);
    private MainDatabaseHelper SQLHelper;
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "users", 1);
        uriMatcher.addURI(AUTHORITY, "users/*", 1);
    }


    public MyContentProvider() {}

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper{
        MainDatabaseHelper(Context context){
            super(context, DBNAME, null, 1);
        }

        public void onCreate(SQLiteDatabase db){
            String CREATE_DB_QUERY = " CREATE TABLE " + DBNAME + " ( " +
                    "_ID INTEGER PRIMARY KEY ," +
                    COLUMN1_NATIONAL_NUMBER + " TEXT , "
                    + COLUMN2_NAME + " TEXT, "
                    +COLUMN3_SPECIES + " TEXT, "
                    +COLUMN4_GENDER + " TEXT, "
                    +COLUMN5_HEIGHT + " TEXT, "
                    +COLUMN6_WEIGHT + " TEXT, "
                    +COLUMN7_LEVEL + " TEXT, "
                    +COLUMN8_HP + " TEXT, "
                    +COLUMN9_ATTACK + " TEXT, "
                    + COLUMN10_DEFENSE + " TEXT)";
            db.execSQL(CREATE_DB_QUERY);
        }

//        public void onCreate(SQLiteDatabase db){
//            String CREATE_DB_QUERY = " CREATE TABLE " + TABLE_NAME + " ( " +
//                    "_ID INTEGER PRIMARY KEY ," +
//                    COLUMN1_NATIONAL_NUMBER + " TEXT , "
//                    + COLUMN2_NAME + " TEXT, "
//                    +COLUMN3_SPECIES + " TEXT, "
//                    +COLUMN4_GENDER + " TEXT, "
//                    +COLUMN5_HEIGHT + " DOUBLE, "
//                    +COLUMN6_WEIGHT + " DOUBLE, "
//                    +COLUMN7_LEVEL + " INTEGER, "
//                    +COLUMN8_HP + " INTEGER, "
//                    +COLUMN9_ATTACK + " INTEGER, "
//                    + COLUMN10_DEFENSE + "INTEGER)";
//            db.execSQL(CREATE_DB_QUERY);
//        }
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        }

        public boolean putP(String num, String name, String spec, String gen, double height, double weight, int level, int hp, int attack, int defense){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NATIONAL_NUMBER, num);
            cv.put(COLUMN2_NAME, name);
            cv.put(COLUMN3_SPECIES, spec);
            cv.put(COLUMN4_GENDER, gen);
//            cv.put(COLUMN5_HEIGHT, height);
            cv.put(COLUMN6_WEIGHT, weight);
            cv.put(COLUMN7_LEVEL, level);
            cv.put(COLUMN8_HP, hp);
            cv.put(COLUMN9_ATTACK, attack);
            cv.put(COLUMN10_DEFENSE, defense);
            long result = db.insert(TABLE_NAME, null, cv);
            if (result == -1) {return false;}  else {return true;}
        }

        public boolean putP(String num, String name, String spec, String gen, String height, String weight, String level, String hp, String attack, String defense){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NATIONAL_NUMBER, num);
            cv.put(COLUMN2_NAME, name);
            cv.put(COLUMN3_SPECIES, spec);
            cv.put(COLUMN4_GENDER, gen);
            cv.put(COLUMN5_HEIGHT, height);
            cv.put(COLUMN6_WEIGHT, weight);
            cv.put(COLUMN7_LEVEL, level);
            cv.put(COLUMN8_HP, hp);
            cv.put(COLUMN9_ATTACK, attack);
            cv.put(COLUMN10_DEFENSE, defense);
            long result = db.insert(TABLE_NAME, null, cv);
            if (result == -1) {return false;}  else {return true;}
        }

        public boolean putPoke(final Pokemon poke){
            boolean success = false;
            int result = 0;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            if(poke.id > -1){
                result += db.update(Pokemon.TABLE_NAME, poke.getContent(), Pokemon.COL_ID + " IS ?", new String[] {String.valueOf(poke.id)});

            }

            if (result > 0) {
                success = true;
            } else {
                final long id = db.insert(Pokemon.TABLE_NAME, null, poke.getContent());
                if(id > -1){
                    poke.id = id;
                    success = true;
                }
            }

            return success;
        }


        public Pokemon getPoke(final long id){
            final SQLiteDatabase db = this.getReadableDatabase();
            final Cursor cursor = db.query(Pokemon.TABLE_NAME, Pokemon.FIELDS, "_ID" + " IS ?", new String[]  { String.valueOf(id) }, null, null, null, null);
            if (cursor == null || cursor.isAfterLast()){
                return null;
            }
            Pokemon item = null;
            if (cursor.moveToFirst()) item = new Pokemon(cursor);
            cursor.close();
            return item;
        }


    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return SQLHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = SQLHelper.getWritableDatabase().insert(TABLE_NAME,null, values);
        return Uri.withAppendedPath(contentURI , "" + id);
    }

    @Override
    public boolean onCreate() {
        SQLHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return SQLHelper.getReadableDatabase().query(TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}