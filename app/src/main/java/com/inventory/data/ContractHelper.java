package com.inventory.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.inventory.data.Contract.InventoryEntry;

/**
 * Created by Ahmed Magdy on 11/03/18.
 */

public class ContractHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbInventory.db";
    private static final int DATABASE_VERSION = 1;

    public ContractHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryEntry.TABLE_NAME + "("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_Product_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_Product_Price + " INTEGER NOT NULL, "
                + InventoryEntry.COLUMN_Product_Quantity + " INTEGER NOT NULL, "
                + InventoryEntry.COLUMN_Supplier_Name + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_Supplier_Phone + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Do nothing now
    }
}
