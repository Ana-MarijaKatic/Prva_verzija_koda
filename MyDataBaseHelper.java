package com.example.whereileftoff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Storage.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SERIES_TABLE_NAME = "Series_storage";
    private static final String SERIES_COLUMN_ID = "series_id";
    private static final String SERIES_COLUMN_TITLE = "series_title";
    private static final String SERIES_COLUMN_SEASON = "series_season";
    private static final String SERIES_COLUMN_EPISODE = "series_episode";
    private static final String SERIES_COLUMN_MINUTE = "series_minute";
    private static final String SERIES_COLUMN_SECOND = "series_second";

    private static final String MOVIES_TABLE_NAME = "Movies_storage";
    private static final String MOVIES_COLUMN_ID = "movie_id";
    private static final String MOVIES_COLUMN_TITLE = "movie_title";
    private static final String MOVIES_COLUMN_MINUTE = "movie_minute";
    private static final String MOVIES_COLUMN_SECOND = "movie_second";

    private static final String BOOKS_TABLE_NAME = "Books_storage";
    private static final String BOOKS_COLUMN_ID = "book_id";
    private static final String BOOKS_COLUMN_TITLE = "book_title";
    private static final String BOOKS_COLUMN_AUTHOR = "book_author";
    private static final String BOOKS_COLUMN_PAGE = "book_page";

    private static final String COMICS_TABLE_NAME = "Comics_storage";
    private static final String COMICS_COLUMN_ID = "comic_id";
    private static final String COMICS_COLUMN_TITLE = "comic_title";
    private static final String COMICS_COLUMN_AUTHOR = "comic_author";
    private static final String COMICS_COLUMN_VOLUME = "comic_volume";
    private static final String COMICS_COLUMN_ISSUE = "comic_issue";
    private static final String COMICS_COLUMN_PAGE = "comic_page";

    MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String series_query = " CREATE TABLE " + SERIES_TABLE_NAME +
                " (" + SERIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SERIES_COLUMN_TITLE + " TEXT, " + SERIES_COLUMN_SEASON + " INTEGER, " +
                SERIES_COLUMN_EPISODE + " INTEGER, " + SERIES_COLUMN_MINUTE + " INTEGER, " + SERIES_COLUMN_SECOND + " INTEGER);";

        String movie_query = " CREATE TABLE " + MOVIES_TABLE_NAME +
                " (" + MOVIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MOVIES_COLUMN_TITLE + " TEXT, " + MOVIES_COLUMN_MINUTE + " INTEGER, " +
                MOVIES_COLUMN_SECOND + " INTEGER);";

        String book_query = " CREATE TABLE " + BOOKS_TABLE_NAME +
                " (" + BOOKS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BOOKS_COLUMN_TITLE + " TEXT, " + BOOKS_COLUMN_AUTHOR + " TEXT, " +
                BOOKS_COLUMN_PAGE + " INTEGER);";

        String comic_query = " CREATE TABLE " + COMICS_TABLE_NAME +
                " (" + COMICS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COMICS_COLUMN_TITLE + " TEXT, " + COMICS_COLUMN_AUTHOR + " TEXT, " +
                COMICS_COLUMN_VOLUME + " INTEGER," + COMICS_COLUMN_ISSUE + " INTEGER," + COMICS_COLUMN_PAGE + " INTEGER);";

        db.execSQL(series_query);
        db.execSQL(movie_query);
        db.execSQL(book_query);
        db.execSQL(comic_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SERIES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MOVIES_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BOOKS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COMICS_TABLE_NAME);
        onCreate(db);
    }

    void addNewSeries(String title, int season, int episode, int minute, int second){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SERIES_COLUMN_TITLE, title);
        contentValues.put(SERIES_COLUMN_SEASON, season);
        contentValues.put(SERIES_COLUMN_EPISODE, episode);
        contentValues.put(SERIES_COLUMN_MINUTE, minute);
        contentValues.put(SERIES_COLUMN_SECOND, second);

        long result = database.insert(SERIES_TABLE_NAME, null, contentValues);
        if(result == -1){
            Toast.makeText(context,"Adding failed.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Adding succeeded.", Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAllSeriesData(){
        String query = " SELECT * FROM " + SERIES_TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
    void updateSeries(String id, String title, String season, String episode, String minute, String second){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SERIES_COLUMN_TITLE, title);
        contentValues.put(SERIES_COLUMN_SEASON, season);
        contentValues.put(SERIES_COLUMN_EPISODE, episode);
        contentValues.put(SERIES_COLUMN_MINUTE, minute);
        contentValues.put(SERIES_COLUMN_SECOND, second);

        Cursor cursor = database.rawQuery(" SELECT * FROM " + SERIES_TABLE_NAME + " WHERE series_id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = database.update(SERIES_TABLE_NAME, contentValues, "series_id = ?", new String[]{id});
            if (result == -1) {
                Toast.makeText(context, "Updating failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updating succeeded.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Updating failed!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteSeries(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(" SELECT * FROM " + SERIES_TABLE_NAME + " WHERE series_id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = database.delete(SERIES_TABLE_NAME, "series_id = ?", new String[]{id});
            if (result == -1) {
                Toast.makeText(context, "Deleting failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deleting succeeded.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Deleting failed!", Toast.LENGTH_SHORT).show();
        }
    }


    void addNewMovie(String title, int minute, int second){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MOVIES_COLUMN_TITLE, title);
        contentValues.put(MOVIES_COLUMN_MINUTE, minute);
        contentValues.put(MOVIES_COLUMN_SECOND, second);

        long result = database.insert(MOVIES_TABLE_NAME, null, contentValues);
        if(result == -1){
            Toast.makeText(context,"Adding failed.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Adding succeeded.", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllMovieData(){
        String query = " SELECT * FROM " + MOVIES_TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
    void updateMovie(String id, String title, String minute, String second){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MOVIES_COLUMN_TITLE, title);
        contentValues.put(MOVIES_COLUMN_MINUTE, minute);
        contentValues.put(MOVIES_COLUMN_SECOND, second);

        Cursor cursor = database.rawQuery(" SELECT * FROM " + MOVIES_TABLE_NAME + " WHERE movie_id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = database.update(MOVIES_TABLE_NAME, contentValues, "movie_id = ?", new String[]{id});
            if (result == -1) {
                Toast.makeText(context, "Updating failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updating succeeded.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Updating failed!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteMovie(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(" SELECT * FROM " + MOVIES_TABLE_NAME + " WHERE movie_id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = database.delete(MOVIES_TABLE_NAME, "movie_id = ?", new String[]{id});
            if (result == -1) {
                Toast.makeText(context, "Deleting failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deleting succeeded.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Deleting failed!", Toast.LENGTH_SHORT).show();
        }
    }


    void addNewBook(String title, String author, int page){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BOOKS_COLUMN_TITLE, title);
        contentValues.put(BOOKS_COLUMN_AUTHOR, author);
        contentValues.put(BOOKS_COLUMN_PAGE, page);

        long result = database.insert(BOOKS_TABLE_NAME, null, contentValues);
        if(result == -1){
            Toast.makeText(context,"Adding failed.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Adding succeeded.", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllBookData(){
        String query = " SELECT * FROM " + BOOKS_TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
    void updateBook(String id, String title, String author, String page){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BOOKS_COLUMN_TITLE, title);
        contentValues.put(BOOKS_COLUMN_AUTHOR, author);
        contentValues.put(BOOKS_COLUMN_PAGE, page);

        Cursor cursor = database.rawQuery(" SELECT * FROM " + BOOKS_TABLE_NAME + " WHERE book_id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = database.update(BOOKS_TABLE_NAME, contentValues, "book_id = ?", new String[]{id});
            if (result == -1) {
                Toast.makeText(context, "Updating failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updating succeeded.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Updating failed!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteBook(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(" SELECT * FROM " + BOOKS_TABLE_NAME + " WHERE book_id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = database.delete(BOOKS_TABLE_NAME, "book_id = ?", new String[]{id});
            if (result == -1) {
                Toast.makeText(context, "Deleting failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deleting succeeded.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Deleting failed!", Toast.LENGTH_SHORT).show();
        }
    }


    void addNewComic(String title, String author, int volume, int issue, int page){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COMICS_COLUMN_TITLE, title);
        contentValues.put(COMICS_COLUMN_AUTHOR, author);
        contentValues.put(COMICS_COLUMN_VOLUME, volume);
        contentValues.put(COMICS_COLUMN_ISSUE, issue);
        contentValues.put(COMICS_COLUMN_PAGE, page);

        long result = database.insert(COMICS_TABLE_NAME, null, contentValues);
        if(result == -1){
            Toast.makeText(context,"Adding failed.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Adding succeeded.", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllComicData(){
        String query = " SELECT * FROM " + COMICS_TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
    void updateComic(String id, String title, String author, String volume, String issue, String page){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COMICS_COLUMN_TITLE, title);
        contentValues.put(COMICS_COLUMN_AUTHOR, author);
        contentValues.put(COMICS_COLUMN_VOLUME, volume);
        contentValues.put(COMICS_COLUMN_ISSUE, issue);
        contentValues.put(COMICS_COLUMN_PAGE, page);

        Cursor cursor = database.rawQuery(" SELECT * FROM " + COMICS_TABLE_NAME + " WHERE comic_id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = database.update(COMICS_TABLE_NAME, contentValues, "comic_id = ?", new String[]{id});
            if (result == -1) {
                Toast.makeText(context, "Updating failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updating succeeded.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Updating failed!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteComic(String id){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(" SELECT * FROM " + COMICS_TABLE_NAME + " WHERE comic_id = ?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = database.delete(COMICS_TABLE_NAME, "comic_id = ?", new String[]{id});
            if (result == -1) {
                Toast.makeText(context, "Deleting failed.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deleting succeeded.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Deleting failed!", Toast.LENGTH_SHORT).show();
        }
    }

}
