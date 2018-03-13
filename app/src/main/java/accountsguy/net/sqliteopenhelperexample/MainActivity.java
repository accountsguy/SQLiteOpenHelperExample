package accountsguy.net.sqliteopenhelperexample;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseManager databaseManager;
    SQLDatabase sqlDatabase;

    Button insertbutton, searchbutton, updatebutton, deletebutton, bulkentrybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DatabaseManager(this);

        insertbutton = (Button) findViewById(R.id.insertbutton);
        searchbutton = (Button) findViewById(R.id.searchbutton);
        updatebutton = (Button) findViewById(R.id.updatebutton);
        deletebutton= (Button) findViewById(R.id.insertBulkData);
        bulkentrybutton = (Button) findViewById(R.id.deletebutton);

        insertbutton.setOnClickListener(this);
        searchbutton.setOnClickListener(this);
        updatebutton.setOnClickListener(this);
        deletebutton.setOnClickListener(this);
        bulkentrybutton.setOnClickListener(this);
    }



//    public void showdetails(View view) {
////        subscriber_preferences.putExtra("preferences", new String[]{"Red", "Blue"});
////        subscriber_preferences.putExtra("Image", "/mipmap/ic_launcher.png");
////        subscriber_preferences.putExtra( "0", ((Button)findViewById(R.id.displaydetails)).getText()
////                .toString());
//        Toast.makeText(this, "Starting Photos", Toast.LENGTH_SHORT).show();
//        startActivity(subscriber_preferences);
//        Toast.makeText(this, "Photo Choosen", Toast.LENGTH_SHORT).show();
//    }
//
//    public void uploadphoto(View view) {
//        Intent photoIntent = new Intent();
//        photoIntent.setType("image/*");
//        photoIntent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(photoIntent, 1);
//        try
//        {
//            //            File image = new File("/mipmap/ic_launcher.png");
//            //            int length = (int)image.length();
//            //
//            //            FileInputStream fileInputStream = new FileInputStream(image);
//            //        databasestatement.setBinaryStream(3, fileInputStream,length);
//            //        int i = databasestatement.executeUpdate();
//        }
//        catch (Exception e){
//            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//        }
//
//    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.insertbutton:
                Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show();
                EditText nameedittext = (EditText) findViewById(R.id.name_insert);
                EditText addressedittext = (EditText) findViewById(R.id.address_insert);
                String name = nameedittext.getText().toString();
                String address = addressedittext.getText().toString();

                databaseManager.open(false);
                databaseManager.insertEmploye(name, address);
                databaseManager.close();
                nameedittext.setText("");
                addressedittext.setText("");
                Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.searchbutton:
                EditText idedittext = (EditText) findViewById(R.id.search_id);
                String searchid = idedittext.getText().toString();
                databaseManager.open(true);
                Cursor cursor = databaseManager.getEmployees(searchid);

                StringBuilder stringBuilder = new StringBuilder();
                do{
                    String nameData = cursor.getString(cursor.getColumnIndex(sqlDatabase.NAME));
                    String addressData = cursor.getString(cursor.getColumnIndex(sqlDatabase.ADDRESS));
                    String idData = cursor.getString(cursor.getColumnIndex(sqlDatabase._ID));
                    stringBuilder.append("ID:"+idData+"Name:"+nameData+"Address:"+addressData+'\n');
                }while (cursor.moveToNext());

                databaseManager.close();
                cursor.close();
                TextView searchresult = (TextView) findViewById(R.id.searchresult);
                Toast.makeText(this, "Record Searched", Toast.LENGTH_SHORT).show();
                break;

            case R.id.updatebutton:
                EditText updatename = (EditText) findViewById(R.id.name_update);
                EditText updateaddress= (EditText) findViewById(R.id.address_update);
                EditText updateid = (EditText) findViewById(R.id.id_update);

                String nameData = updatename.getText().toString();
                String addreessData = updateaddress.getText().toString();
                long idData = Long.parseLong(updateid.getText().toString());

                databaseManager.open(false);
                databaseManager.updateEmployee(idData, nameData, addreessData);
                databaseManager.close();

                updatename.setText("");
                updateid.setText("");
                updateaddress.setText("");
                Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show();
                break;

            case R.id.deletebutton:
                EditText deleteId = (EditText) findViewById(R.id.id_update);
                long deleteIdLong = Long.parseLong(deleteId.getText().toString());
                databaseManager.open(false);
                databaseManager.delete(deleteIdLong);
                databaseManager.close();

                deleteId.setText("");
                Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.insertBulkData:
                Employee employee1 = new Employee();
                employee1.setName("Ornald");
                employee1.setAddress("New York");

                Employee employee2 = new Employee();
                employee1.setName("Yashwanth");
                employee1.setAddress("Owk");

                Employee employee3 = new Employee();
                employee1.setName("Ramanaiah");
                employee1.setAddress("Kurnool");

                Employee employee4 = new Employee();
                employee1.setName("Rajnith");
                employee1.setAddress("Haryana");

                List<Employee> employeeList = new ArrayList<>();
                employeeList.add(employee1);
                employeeList.add(employee2);
                employeeList.add(employee3);
                employeeList.add(employee4);

                databaseManager.open(false);
                databaseManager.addBulkEmployees(employeeList);
                databaseManager.close();
                Toast.makeText(this, "Bulk entries recorded", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
