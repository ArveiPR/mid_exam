package com.example.mid_exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "student_db";
    private static final String TB_NAME = "student";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "student_name";
    private static final String COL_STUDENT_ID = "student_id";
    private static final String COL_ENROLLED = "is_enrolled";

    private SQLiteDatabase mySQLite;

    private static final String CREATE_TABLE = "CREATE TABLE " + TB_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT NOT NULL, "
            + COL_STUDENT_ID + " TEXT NOT NULL, "
            + COL_ENROLLED + " INTEGER NOT NULL);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }

    // Menambah data mahasiswa baru
    public void addStudent(StudentModel studentModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, studentModel.getStudentName());
        contentValues.put(COL_STUDENT_ID, studentModel.getStudentId());
        contentValues.put(COL_ENROLLED, studentModel.getIsEnrolled());
        mySQLite = this.getWritableDatabase();
        mySQLite.insert(TB_NAME, null, contentValues);
    }

    // Mengubah data mahasiswa
    public void updateStudent(StudentModel studentModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, studentModel.getStudentName());
        contentValues.put(COL_STUDENT_ID, studentModel.getStudentId());
        contentValues.put(COL_ENROLLED, studentModel.getIsEnrolled());
        mySQLite = this.getWritableDatabase();
        mySQLite.update(TB_NAME, contentValues, COL_ID + " = ?",
                new String[]{String.valueOf(studentModel.getId())});
    }

    // Menghapus data mahasiswa berdasarkan ID
    public void deleteStudent(int id) {
        mySQLite = this.getWritableDatabase();
        mySQLite.delete(TB_NAME, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Mengambil daftar seluruh data mahasiswa dari database
    public List<StudentModel> getStudentList() {
        String viewData = "SELECT id, student_name, student_id, is_enrolled FROM " + TB_NAME;
        mySQLite = this.getReadableDatabase();
        List<StudentModel> storeStudent = new ArrayList<>();
        Cursor cursor = mySQLite.rawQuery(viewData, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String studentId = cursor.getString(2);
                int enrolled = cursor.getInt(3);
                storeStudent.add(new StudentModel(id, name, studentId, enrolled));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeStudent;
    }
}