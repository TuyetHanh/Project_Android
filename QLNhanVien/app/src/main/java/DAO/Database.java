package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TuyetHanh on 2016-12-12.
 */

public class Database extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "QLNhanVien";
    private static int DATABASE_VERSION = 1;

    public static String TABLE_NHANVIEN = "NhanVien";
    public static String MaNV_NhanVien = "MaNV";
    public static String TenNV_NhanVien = "TenNV";
    public static String SDT_NhanVien = "SoDT";
    public static String NgaySinh_NhanVien = "NgaySinh";
    public static String GioiTinh_NhanVien = "GioiTinh";
    public static String DiaChi_NhanVien = "DiaChi";
    public static String Email_NhanVien = "Email";
    public static String Luong_NhanVien = "Luong";

    public static String TABLE_PHONGBAN = "PhongBan";
    public static String MaPB_PhongBan = "MaPB";
    public static String TenPB_PhongBan = "TenPB";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String taoBangNhanVien = "create table "
                + TABLE_NHANVIEN
                + "("
                + MaNV_NhanVien
                + " Integer primary key autoincrement, "
                + TenNV_NhanVien
                + " text,"
                + SDT_NhanVien
                + " text,"
                + NgaySinh_NhanVien
                +"text,"
                + GioiTinh_NhanVien
                + " text,"
                + DiaChi_NhanVien
                + " text,"
                + Email_NhanVien
                + " text,"
                + Luong_NhanVien
                + " Integer, "
                + MaPB_PhongBan
                + " integer Constraint PK_MAPB_NhanVien References PhongBan(MaPB) on delete cascade"
                + ")";

        String tablBangPhongBan = "create table " + TABLE_PHONGBAN + "("
                + MaPB_PhongBan + " Integer primary key autoincrement, "
                + TenPB_PhongBan + " text " + ")";

        db.execSQL(tablBangPhongBan);
        db.execSQL(taoBangNhanVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("crop table if exists " + TABLE_NHANVIEN);
        db.execSQL("crop table if exists " + TABLE_PHONGBAN);
        onCreate(db);
    }
}
