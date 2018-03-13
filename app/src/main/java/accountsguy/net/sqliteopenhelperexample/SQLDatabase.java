package accountsguy.net.sqliteopenhelperexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by advic on 06/03/2018.
 */

public class SQLDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "EMPLOYEES";
    public static final String _ID = "id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";

    public static final String DB_NAME = "EMP.DB";
    public static final int DB_VER = 1;

    public static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +"("+_ID+" INTEGER " +
            "PRIMARY KEY AUTOINCREMENT,"+NAME+" TEXT NOT NULL,"+ADDRESS+" TEXT);";

    public SQLDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.i("Test - SQLDatabase: ", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.i("Test - SQLDatabase: ", "Table onUpgrade");
        onCreate(db);
    }

//    private SQLiteDatabase db;
//    private final String SubscribersTable = "CREATE TABLE SubscriberPreferences Name TEXT, " +
//            "ID TEXT PRIMARY KEY, PHONE INTEGER";
//
//    private SubscriberPreferences subscriberPreferences;
//    public SQLDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SubscribersTable);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public void addSubscriber(SubscriberPreferences subsciber) {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("Name", subscriberPreferences.getSubscriber_Name());
//        values.put("ID", subscriberPreferences.getSubscriber_Email() );
//
//
//        db.insert(SubscribersTable, null, values);
//        db.close();
//    }
//
//    public int updateSubscriber(SubscriberPreferences subscriber, String UpdatableName, String
//            UpdatableEmail, String UpdatablePhone) {
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        String[] SQLiteWhereValues = {UpdatableName, UpdatableEmail, UpdatablePhone};
//
//        values.put("Name", subscriberPreferences.getSubscriber_Name());
//        values.put("ID", subscriberPreferences.getSubscriber_Email() );
//
//
//        int updatedrecords = db.update(SubscribersTable, values, "Name = ?, ID = ?, Phone = ?",
//                SQLiteWhereValues);
//        db.close();
//        return updatedrecords;
//    }
//
//    public void deleteSubscriber(SubscriberPreferences subscriber, String SubscriberEmail){
//        db = this.getWritableDatabase();
//        db.delete(SubscribersTable, "ID = ?", new String[]{SubscriberEmail});
//        db.close();
//    }
//
////    public SubscriberPreferences getSubscriber(String subscriber_email){
////        db = this.getReadableDatabase();
////        String[] columnnames = {"Name","ID","Phone"};
////
////        Cursor cursor = db.query( SubscribersTable, columnnames , "ID = ?" , new String[]{subscriber_email} ,
////                null, null ,null , null);
////
////        if(cursor!=null){
////            cursor.moveToFirst();
////        }
////
//////        SubscriberPreferences subscriberPreferences = new SubscriberPreferences(cursor.getString(0),cursor
//////                .getString(1), cursor.getInt(3));
////        return subscriberPreferences;
////    }
}
