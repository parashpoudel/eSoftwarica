package helper;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.Student;

public class MyHelper extends SQLiteOpenHelper {

    private static final String databaseName = "CoventryDB";
    private  static final int dbVersion = 4;

    private  static final String studentTable = "studentTable";

    public  MyHelper(Context context){
        super(context, databaseName, null, dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + studentTable + "(studentID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "StudentName TEXT, age TEXT, gender TEXT, address TEXT  )";
        db.execSQL(query);
    }

    public boolean InsertData(String name, String age, String gender, String address, SQLiteDatabase db){
        try {
            String query = "insert into studentTable(StudentName, age, gender, address) values ('"+name+"','"+age+"','"+gender+"','"+address+"')";
            db.execSQL(query);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Student> GetAllStudent(SQLiteDatabase db){
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from studentTable",null);

        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                studentList.add(new Student(cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(3),cursor.getInt(0)));

            }
        }
        return studentList;
    }

    public  void deleteStudent(Student student){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(studentTable, "studentID" + " =?",
                new String[]{String.valueOf(student.getStudentId())});
        sqLiteDatabase.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
