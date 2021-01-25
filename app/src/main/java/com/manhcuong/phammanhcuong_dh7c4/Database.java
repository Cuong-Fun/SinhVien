package com.manhcuong.phammanhcuong_dh7c4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    // khai bao tên CSDL
    public static final String DATABASE_NAME = "QL_sinhvien.db";
    // khai bao tên bảng
    private static final String TABLE_NAME = "t_sinhvien";
    //khai báo tên các trường
    private static final String ID = "masv";
    private static final String NAME = "hoten";
    private static final String TENKHOA = "tenkhoa";
    private static final String DIEMTIN = "diemtin";
    // khai báo phiên bản
    private static int VERSION = 1;
    private Context context;
    //Tạo bảng với các trường tương ứng
    String sqlQuery = " CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key , " +    //autoincrement : tu tang
            NAME + " TEXT, " +
            TENKHOA + " TEXT, " +
            DIEMTIN + " TEXT )";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d(TAG,"Database:");
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);
        Toast.makeText(context,"Tao bang thanh cong",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // xoa bang cu~
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        // tao bang moi
        onCreate(db);
        Toast.makeText(context,"Drop thanh cong", Toast.LENGTH_SHORT).show();
    }
    //Them moi 1 SV
    public void addSV(SinhVien sv){
        SQLiteDatabase db=this.getWritableDatabase(); // cap nhat du lieu ( create , delete, update, ... )
        ContentValues gt=new ContentValues(); // chen 1 hang moi vao` database ( o day la list )
       // gt.put(ID,sv.getMasv());
        gt.put(NAME,sv.getHoten());
        gt.put(TENKHOA, sv.getTenkhoa());  // day du lieu len database
        gt.put(DIEMTIN, sv.getDiemtin());
        db.insert(TABLE_NAME, null, gt);
        db.close();
    }
    //Đọc thông tin SV
    public SinhVien getSVById(int id){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        Cursor cursor = (Cursor) db.query(TABLE_NAME,new String[]{
                ID,NAME,TENKHOA,DIEMTIN},"ID=?",new
                String[]{String.valueOf(id)},null,null,null,null);
        if (cursor !=null)
            cursor.moveToFirst();
        SinhVien sv=new
                SinhVien(cursor.getString(1),cursor.getString(2),cursor.getString(3));
        cursor.close();
        db.close();
        return sv;
    }
    // Lấy toàn bộ danh sách SV
    public List<SinhVien> getAllSV(){
        List<SinhVien> listSV=new ArrayList<SinhVien>();
        // lựa chọn tất cả
        String selectQuery="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                SinhVien sv=new SinhVien();
                sv.setMasv(cursor.getInt(0));
                sv.setHoten(cursor.getString(1));
                sv.setTenkhoa(cursor.getString(2));
                sv.setDiemtin(cursor.getString(3));
                listSV.add(sv);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listSV;
    }
    public List<SinhVien> searchSV(String masv){
        List<SinhVien> listSV=new ArrayList<SinhVien>();
        // lựa chọn tất cả
        String selectQuery="SELECT * FROM "+ TABLE_NAME +" WHERE " + ID +" LIKE '%"+masv+"%'" ;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                SinhVien sv=new SinhVien();
                sv.setMasv(cursor.getInt(0));
                sv.setHoten(cursor.getString(1));
                sv.setTenkhoa(cursor.getString(2));
                sv.setDiemtin(cursor.getString(3));
                listSV.add(sv);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listSV;
    }


    // Update thong tin sv
    public int UpdateSV(SinhVien sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ID,sv.getMasv());
        values.put(NAME,sv.getHoten());
        values.put(TENKHOA,sv.getTenkhoa());
        values.put(DIEMTIN,sv.getDiemtin());
        int a= db.update(TABLE_NAME,values,ID+"=?",new
                String[]{String.valueOf(sv.getMasv())});
        return a;
    }
    // Xoa 1 sv thông qua ID
    public int DeleteSV(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int kq= db.delete(TABLE_NAME, ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
        return kq;
    }



}
