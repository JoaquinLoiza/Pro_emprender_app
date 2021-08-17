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

    public long insertProduct(String name) {
        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME, name);
        long id = dbb.insert(DbHelper.TABLE_NAME, null , contentValues);
        dbb.close();
        return id;
    }

    public Cursor getProduct( Integer id ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {DbHelper.UID, DbHelper.NAME};
        Cursor cursor = db.rawQuery("SELECT * FROM "+ DbHelper.TABLE_NAME + " WHERE "+ DbHelper.UID +" = "+ id + ";",null);
        db.close();
        return cursor;
    }


    public Cursor getProducts() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {DbHelper.UID, DbHelper.NAME};
        Cursor cursor =  db.query(DbHelper.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }


    //-----------------------------------------------------------------------------------------------------------------
    static class DbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "DB_ProEmprender";    // Database Name
        private static final String TABLE_NAME = "products";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="id";     // Column I (Primary Key)
        private static final String NAME = "name";    //Column II
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                //Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                //Message.message(context,""+e);
            }
        }
    }

}
