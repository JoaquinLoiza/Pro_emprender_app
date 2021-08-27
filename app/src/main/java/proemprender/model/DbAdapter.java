package proemprender.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {

    DbHelper dbHelper;
    public DbAdapter(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void addProduct(String name, Integer price) {
        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME_PRODUCT, name);
        contentValues.put(DbHelper.PRICE_PRODUCT, price);
        dbb.insert(DbHelper.TABLE_PRODUCTS, null , contentValues);
        dbb.close();
    }

    public void addComponent(String name, Integer price, Integer cant, Integer idProduct) {
        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME_COMPONENT, name);
        contentValues.put(DbHelper.PRICE_COMPONENT, price);
        contentValues.put(DbHelper.CANT_COMPONENT, cant);
        contentValues.put(DbHelper.FK_COMPONENT, idProduct);
        dbb.insert(DbHelper.TABLE_COMPONENTS, null , contentValues);
        dbb.close();
    }

    public void editProduct( Integer id, String name, Integer price) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE "+ DbHelper.TABLE_PRODUCTS + " SET "+
                DbHelper.NAME_PRODUCT + "=" +"'"+ name +"'"+ ", "+
                DbHelper.PRICE_PRODUCT + "=" +price+
                " WHERE "+DbHelper.UID_PRODUCT+"="+id+";");
        db.close();
    }

    public void deleteProduct( Integer id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM "+ DbHelper.TABLE_PRODUCTS + " WHERE "+ DbHelper.UID_PRODUCT +" = "+ id + ";");
        db.close();
    }

    public Cursor getProduct( Integer id ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ DbHelper.TABLE_PRODUCTS + " WHERE "+ DbHelper.UID_PRODUCT +" = "+ id + ";",null);
        db.close();
        return cursor;
    }


    public Cursor getProducts() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {DbHelper.UID_PRODUCT, DbHelper.NAME_PRODUCT, DbHelper.PRICE_PRODUCT};
        return db.query(DbHelper.TABLE_PRODUCTS, columns, null, null, null, null, null);
    }


    //-----------------------------------------------------------------------------------------------------------------
    static class DbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "DB_ProEmprender";    // Database Name
        private static final int DATABASE_Version = 1;    // Database Version

        //----- Table components
        private static final String TABLE_COMPONENTS = "components";
        private static final String UID_COMPONENT = "id";
        private static final String NAME_COMPONENT = "name";
        private static final String PRICE_COMPONENT = "price";
        private static final String CANT_COMPONENT = "cant";
        private static final String FK_COMPONENT = "fk_componente";
        private static final String DROP_TABLE_COMPONENTS ="DROP TABLE IF EXISTS "+ TABLE_COMPONENTS;
        //----- Table products
        private static final String TABLE_PRODUCTS = "products";   // Table Name
        private static final String UID_PRODUCT ="id";     // Column I (Primary Key)
        private static final String NAME_PRODUCT = "name";    //Column II
        private static final String PRICE_PRODUCT = "price";    //Column II
        private static final String DROP_TABLE_PRODUCTS ="DROP TABLE IF EXISTS "+ TABLE_PRODUCTS;


        private static final String CREATE_TABLE_COMPONENTS = "CREATE TABLE "+ TABLE_COMPONENTS +" ("
                + UID_COMPONENT +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FK_COMPONENT +" INTEGER, "
                + NAME_COMPONENT + " VARCHAR(255), "
                + PRICE_COMPONENT + " INTEGER, "
                + CANT_COMPONENT + " INTEGER, "
                + "FOREIGN KEY("+ FK_COMPONENT +") REFERENCES "+TABLE_PRODUCTS+"("+UID_PRODUCT+"));";

        private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "+ TABLE_PRODUCTS +" ("
                + UID_PRODUCT +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_PRODUCT +" VARCHAR(255),"
                + PRICE_PRODUCT +" INTEGER);";

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_PRODUCTS);
                db.execSQL(CREATE_TABLE_COMPONENTS);
            } catch (Exception e) {
                //Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE_COMPONENTS);
                db.execSQL(DROP_TABLE_PRODUCTS);
                onCreate(db);
            }catch (Exception e) {
                //Message.message(context,""+e);
            }
        }
    }
}
