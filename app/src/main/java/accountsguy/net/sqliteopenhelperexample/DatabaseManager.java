package accountsguy.net.sqliteopenhelperexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by advic on 11/03/2018.
 */

public class DatabaseManager {
    private SQLDatabase sqlDatabase;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context){
        this.context = context;
    }

    public DatabaseManager open(boolean isReadable){
        sqlDatabase = new SQLDatabase(context, null, null, 1);
        if(isReadable){
            sqLiteDatabase = sqlDatabase.getReadableDatabase();
        }
        else {
            sqLiteDatabase = sqlDatabase.getWritableDatabase();
        }
        return  this;
    }

    public void close() {sqlDatabase.close();}

    public void insertEmploye(String name, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(sqlDatabase.NAME, name);
        contentValues.put(sqlDatabase.ADDRESS, address);

        sqLiteDatabase.insert(sqlDatabase.TABLE_NAME,null, contentValues);

//        String[] columns = new String[]{sqlDatabase._ID, SQLDatabase.NAME, SQLDatabase.ADDRESS};
//        String whereClause = sqlDatabase._ID+"=?";
//        String[] whereArgs = new String[]{"1"};
//        Cursor cursor = null;
//        Log.i("DatabaseManager: ", "Try Started");
//        cursor = sqLiteDatabase.query(sqlDatabase.TABLE_NAME, columns, whereClause, whereArgs,
//                null, null, null, null );
//        if(cursor!=null){
//            cursor.moveToFirst();
//        }
//        Log.i("DatabaseManager: ", String.valueOf(cursor.getCount()));
    }

    public Cursor getEmployees(String whereArgument){
        String[] columns = new String[]{sqlDatabase._ID, SQLDatabase.NAME, SQLDatabase.ADDRESS};
        String whereClause = sqlDatabase._ID+"=?";
        String[] whereArgs = new String[]{whereArgument};
        Cursor cursor = null;
        try{
            Log.i("Test - DatabaseManager:", "Try Started");
            cursor = sqLiteDatabase.query(sqlDatabase.TABLE_NAME, columns, whereClause,
                    whereArgs, null, null, null, null );
            if(cursor!=null){
                cursor.moveToFirst();
            }
            Log.i("Test - DatabaseManager:", String.valueOf(cursor.getCount()));
        }
        catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        finally {
            return cursor;
        }
    }

    public int updateEmployee(long _id, String name, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(sqlDatabase.NAME, name);
        contentValues.put(sqlDatabase.ADDRESS, address);

        int updatedRecords = sqLiteDatabase.update(sqlDatabase.TABLE_NAME,contentValues,
                sqlDatabase._ID+"="+_id, null);
        return updatedRecords;
    }

    public void delete(long _id){
        sqLiteDatabase.delete(sqlDatabase.TABLE_NAME,sqlDatabase._ID+"="+_id, null);
    }

    public void addBulkEmployees(List<Employee> employeeList){
        sqLiteDatabase.beginTransaction();

        try {
            ContentValues contentValues = new ContentValues();
            for(Employee employee : employeeList){
                contentValues.put(sqlDatabase.NAME, employee.getName());
                contentValues.put(sqlDatabase.ADDRESS, employee.getAddress());
                sqLiteDatabase.insert(sqlDatabase.TABLE_NAME, null, contentValues);
            }
            sqLiteDatabase.setTransactionSuccessful();
        }
        finally {
            sqLiteDatabase.endTransaction();
        }
    }
}
