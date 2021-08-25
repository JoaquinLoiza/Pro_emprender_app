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

    public void insertProduct(String name) {
        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME_PRODUCT, name);
        dbb.insert(DbHelper.TABLE_PRODUCTS, null , contentValues);
        dbb.close();
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
        private static final String REFERENCE_TO_COMPONENT = "fk_componentes";
        private static final String DROP_TABLE_COMPONENTS ="DROP TABLE IF EXISTS "+ TABLE_COMPONENTS;
        //----- Table products
        private static final String TABLE_PRODUCTS = "products";   // Table Name
        private static final String UID_PRODUCT ="id";     // Column I (Primary Key)
        private static final String NAME_PRODUCT = "name";    //Column II
        private static final String PRICE_PRODUCT = "price";    //Column II
        private static final String DROP_TABLE_PRODUCTS ="DROP TABLE IF EXISTS "+ TABLE_PRODUCTS;


        private static final String CREATE_TABLE_COMPONENTS = "CREATE TABLE "+ TABLE_COMPONENTS +" ("
                + UID_COMPONENT +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REFERENCE_TO_COMPONENT +" INTEGER, "
                + NAME_COMPONENT + " VARCHAR(255), "
                + "FOREIGN KEY("+REFERENCE_TO_COMPONENT+") REFERENCES "+TABLE_PRODUCTS+"("+UID_PRODUCT+"));";

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
