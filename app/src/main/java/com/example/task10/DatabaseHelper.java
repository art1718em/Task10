package com.example.task10;


import static android.app.DownloadManager.COLUMN_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "cars";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_MARK = "mark";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_COLOR = "color";

    public DatabaseHelper(Context context) {
        super(context, "contacts.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_MARK + " TEXT, " +
                COLUMN_MODEL + " TEXT, " +
                COLUMN_COLOR + " TEXT)";
        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER, car.getNumber());
        cv.put(COLUMN_MARK, car.getMark());
        cv.put(COLUMN_MODEL, car.getModel());
        cv.put(COLUMN_COLOR, car.getColor());
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        return result != -1;

    }

    public boolean deleteCar(String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_NUMBER+ " = ?",
                new String[]{number});
        db.close();
        return result > 0;
    }


    public Car findContact(String number) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new
                        String[]{COLUMN_ID, COLUMN_NUMBER, COLUMN_MARK, COLUMN_MODEL, COLUMN_COLOR},
                COLUMN_NUMBER + " = ?", new String[]{number}, null,
                null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Car car = new Car(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4));
            cursor.close();
            db.close();
            return car;
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }

    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Car contact = new Car(cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4));
                carList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return carList;
    }

    public boolean updateCar(String oldNumber, String newNumber,
                                 String newMark, String newModel, String newColor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NUMBER, newNumber);
        cv.put(COLUMN_MARK, newMark);
        cv.put(COLUMN_MODEL, newModel);
        cv.put(COLUMN_COLOR, newColor);
        int result = db.update(TABLE_NAME, cv, COLUMN_NUMBER + " = ?", new String[]{oldNumber});
        db.close();
        return result > 0;
    }
}

