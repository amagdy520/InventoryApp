package com.inventory.data;

import android.provider.BaseColumns;

/**
 * Created by Ahmed Magdy on 11/03/18.
 */

public final class Contract {
    private Contract() {
    }
    public static final class InventoryEntry implements BaseColumns {

        public final static String TABLE_NAME = "inventory";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_Product_NAME = "product_name";
        public final static String COLUMN_Product_Price = "price";
        public final static String COLUMN_Product_Quantity = "quantity";
        public final static String COLUMN_Supplier_Name = "supplier_name";
        public final static String COLUMN_Supplier_Phone = "supplier_phone";
    }
}
