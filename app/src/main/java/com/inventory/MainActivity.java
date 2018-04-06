package com.inventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.inventory.data.Contract.InventoryEntry;
import com.inventory.data.ContractHelper;


public class MainActivity extends AppCompatActivity {

    private EditText ProductName;
    private EditText Price;
    private EditText Quantity;
    private EditText SupplierName;
    private EditText SupplierPhone;
    private TextView displayView;
    private ContractHelper mContractHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductName = (EditText) findViewById(R.id.text_name);
        Price = (EditText) findViewById(R.id.text_price);
        Quantity = (EditText) findViewById(R.id.text_quantity);
        SupplierName = (EditText) findViewById(R.id.text_supplier_name);
        SupplierPhone = (EditText) findViewById(R.id.text_supplier_phone);
        displayView = (TextView) findViewById(R.id.text_output);
        mContractHelper = new ContractHelper(this);

        displayDatabaseInfo();
    }

    private void insertData() {
        // Insert into database.
        String name = ProductName.getText().toString();
        int price = Integer.parseInt(Price.getText().toString());
        int quantity = Integer.parseInt(Quantity.getText().toString());
        String supplierName = SupplierName.getText().toString();
        String supplierPhone = SupplierPhone.getText().toString();

        SQLiteDatabase db = mContractHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(InventoryEntry.COLUMN_Product_NAME, name);
        values.put(InventoryEntry.COLUMN_Product_Price, price);
        values.put(InventoryEntry.COLUMN_Product_Quantity, quantity);
        values.put(InventoryEntry.COLUMN_Supplier_Name, supplierName);
        values.put(InventoryEntry.COLUMN_Supplier_Phone, supplierPhone);
        long newRowId=db.insert(InventoryEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }

    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mContractHelper.getReadableDatabase();

        String project[] = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_Product_NAME,
                InventoryEntry.COLUMN_Product_Price,
                InventoryEntry.COLUMN_Product_Quantity,
                InventoryEntry.COLUMN_Supplier_Name,
                InventoryEntry.COLUMN_Supplier_Phone
        };

        Cursor cursor = db.query(
                InventoryEntry.TABLE_NAME,
                project,
                null,
                null,
                null,
                null,
                null);

        try {
            displayView.append(InventoryEntry._ID + " | " +
                    InventoryEntry.COLUMN_Supplier_Name + " | " +
                    InventoryEntry.COLUMN_Product_Price + " | " +
                    InventoryEntry.COLUMN_Product_Quantity + " | " +
                    InventoryEntry.COLUMN_Supplier_Name + " | " +
                    InventoryEntry.COLUMN_Supplier_Phone + "\n");


            int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Product_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Product_Price);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Product_Quantity);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Supplier_Name);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Supplier_Phone);

            while (cursor.moveToNext()) {
                int cursorId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentprice = cursor.getString(priceColumnIndex);
                String currentquantity = cursor.getString(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierphone = cursor.getString(supplierPhoneColumnIndex);

                displayView.append("\n" +
                        cursorId + " | " +
                        currentName + " | " +
                        currentprice + " | " +
                        currentquantity + " | " +
                        currentSupplierName + " | " +
                        currentSupplierphone + " | "
                );
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_save:
                insertData();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
