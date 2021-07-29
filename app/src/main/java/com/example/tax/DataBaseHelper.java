package com.example.tax;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.FontsContract;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.jar.Attributes;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "incometax.db";
    public static final String TABLE_NAME = "incometax_table";
    public static final String COL_1 = "ID";
   public static final String COL_2 = "s";
    public static final String COL_3 = "INCOME_TAX";
   public static final String COL_4 = "EDUCATION_AND_HEALTH_CESS";
   public static final String COL_5 = "SURCHARGE";
   public static final String COL_6 = "TOTAL_TAX_LIABILITY";




    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
        SQLiteDatabase db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("Create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,INCOME_TAX STRING)");
        db.execSQL("Create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME STRING,INCOME_TAX STRING,EDUCATION_AND_HEALTH_CESS STRING,SURCHARGE STRING ,TOTAL_TAX_LIABILITY STRING)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public  boolean insertData(String tx1){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,tx1);

    // contentValues.put(COL_3,Income_tax);
      //contentValues.put(COL_4,EDUCATION_AND_HEALTH_CES);
    //  contentValues.put(COL_5,surcharge);
    //  contentValues.put(COL_2,total_tax_liability);
        db.insert(TABLE_NAME,null,contentValues);
    long result =db.insert(TABLE_NAME,null,contentValues);
    if (result==-1)
        return false;
   else
         return true;
    }
    public Cursor getListContents(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor data =db.rawQuery(" SELECT * FROM "+TABLE_NAME,null);
        return data;
    }


}
