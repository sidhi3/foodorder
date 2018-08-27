package com.example.ridhi.foodorder.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.view.View;

import com.example.ridhi.foodorder.model.order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ridhi & Sidhi on 1/28/2018.
 */

public class Database extends SQLiteAssetHelper {
    private static final String db_name = "food.db";
    private static final int db_ver = 1;

    public Database(Context context) {

        super(context, db_name, null, db_ver);
    }

    public List<order> getcarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"productid", "productname", "quantity", "price", "discount"};
        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null,null);

        final List<order> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                result.add(new order(c.getString(c.getColumnIndex("productid")),
                        c.getString(c.getColumnIndex("productname")),
                        c.getString(c.getColumnIndex("quantity")),
                        c.getString(c.getColumnIndex("price")),
                        c.getString(c.getColumnIndex("discount"))
                ));
            } while (c.moveToNext());
        }

        return result;
    }

    public void addtoCart(order o) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(productid, productname, quantity, price, discount) VALUES('%s','%s','%s','%s','%s');",
        o.getProductid(),
        o.getProductname(),
        o.getQuantity(),
        o.getPrice(),
        o.getDiscount());

        db.execSQL(query);

    }

    public void cleancart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);

    }

}
