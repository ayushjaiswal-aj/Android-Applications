package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    //initialize parameters
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes_db";
    public static final String TABLE_NAME = "Notes_table";
    //keys for database table
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCTTITLE = "noteTitle";
    public static final String COLUMN_PRODUCTNOTE = "notes";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query  = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_PRODUCTTITLE + " TEXT, " + COLUMN_PRODUCTNOTE + " TEXT" + ")" ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public void addProduct (Products products){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCTTITLE, products.getTitle());
        values.put(COLUMN_PRODUCTNOTE, products.getNote());
        db.insert(TABLE_NAME, null, values);
        Log.d("ayush", "Successfully inserted");
        db.close();
    }

    public int updateProduct (Products products){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, products.getId());
        values.put(COLUMN_PRODUCTTITLE, products.getTitle());
        values.put(COLUMN_PRODUCTNOTE, products.getNote());
        //update
        return db.update(TABLE_NAME, values, COLUMN_ID +"=? ", new String[]{String.valueOf(products.getId())});
    }

    public void deleteProduct (int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=? ", new String[]{String.valueOf(id)});
        db.close();

    }

    // readable database
    public List<Products> getAllNotes(){
        List<Products> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        //looping
        if (cursor.moveToFirst()){
            do{
                Products products =  new Products();
                products.setId(Integer.parseInt(cursor.getString(0)));
                products.setTitle(cursor.getString(1));
                products.setNote(cursor.getString(2));
                productList.add(products);
            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return productList;
    }
}
