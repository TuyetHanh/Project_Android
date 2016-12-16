package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import DTO.NhanVienDTO;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

public class NhanVienDAO { //Truy vấn vào CSDL
    Database dbhelper;
    SQLiteDatabase db;
    Context context;

    public NhanVienDAO(Context context){
        this.context = context;
        dbhelper = new Database(context);
    }

    public List<NhanVienDTO> LayNhanVienTheoPhong(int mapb){
        List<NhanVienDTO> list = new ArrayList<NhanVienDTO>();
        db = dbhelper.getWritableDatabase();
        String sql = "select * from " + Database.TABLE_NHANVIEN + ","
                + Database.TABLE_PHONGBAN + " where " + Database.TABLE_NHANVIEN
                + "." + Database.MaPB_PhongBan + " = "
                + Database.TABLE_PHONGBAN + "." + Database.MaPB_PhongBan
                + " and " + Database.TABLE_NHANVIEN + "." + Database.MaPB_PhongBan + " = " + mapb;
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            NhanVienDTO nhanvien = new NhanVienDTO();
            nhanvien.setDiachi(c.getString(c.getColumnIndex(Database.DiaChi_NhanVien)));
            nhanvien.setEmail(c.getString(c.getColumnIndex(Database.Email_NhanVien)));
            nhanvien.setGioitinh(c.getString(c.getColumnIndex(Database.GioiTinh_NhanVien)));
            nhanvien.setLuong(Integer.parseInt(c.getString(c.getColumnIndex(Database.Luong_NhanVien))));
            nhanvien.setNgaysinh("17/11/1995");
            nhanvien.setSdt(c.getString(c.getColumnIndex(Database.SDT_NhanVien)));
            nhanvien.setTennv(c.getString(c.getColumnIndex(Database.TenNV_NhanVien)));
            nhanvien.setTenphongban(c.getString(c.getColumnIndex(Database.TenPB_PhongBan)));
            nhanvien.setManv(Integer.parseInt(c.getString(c.getColumnIndex(Database.MaNV_NhanVien))));
            list.add(nhanvien);
            c.moveToNext();
        }
        return list;
    }

    public NhanVienDTO LayNhanVienTheoMa(int id){
        db = dbhelper.getWritableDatabase();
        String sql = "select * from " + Database.TABLE_NHANVIEN + ","
                + Database.TABLE_PHONGBAN + " where " + Database.TABLE_NHANVIEN
                + "." + Database.MaPB_PhongBan + " = "
                + Database.TABLE_PHONGBAN + "." +Database.MaPB_PhongBan
                + " and " + Database.MaNV_NhanVien + " = " + id;
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        NhanVienDTO nhanvien = new NhanVienDTO();
        while (!c.isAfterLast()){

            nhanvien.setDiachi(c.getString(c.getColumnIndex(Database.DiaChi_NhanVien)));
            nhanvien.setEmail(c.getString(c.getColumnIndex(Database.Email_NhanVien)));
            nhanvien.setGioitinh(c.getString(c.getColumnIndex(Database.GioiTinh_NhanVien)));
            nhanvien.setLuong(Integer.parseInt(c.getString(c.getColumnIndex(Database.Luong_NhanVien))));
            nhanvien.setNgaysinh("17/11/1995");
            nhanvien.setSdt(c.getString(c.getColumnIndex(Database.SDT_NhanVien)));
            nhanvien.setTennv(c.getString(c.getColumnIndex(Database.TenNV_NhanVien)));
            nhanvien.setTenphongban(c.getString(c.getColumnIndex(Database.TenPB_PhongBan)));
            c.moveToNext();
        }
        return  nhanvien;
    }

    public int CapNhatNhanVien(NhanVienDTO nhanvien){
        db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.TenNV_NhanVien,nhanvien.getTennv());
        values.put(Database.SDT_NhanVien,nhanvien.getSdt());
        values.put(Database.GioiTinh_NhanVien,nhanvien.getGioitinh());
        //values.put(Database.NgaySinh_NhanVien,nhanvien.getNgaysinh());
        values.put(Database.DiaChi_NhanVien,nhanvien.getDiachi());
        values.put(Database.Email_NhanVien,nhanvien.getEmail());
        values.put(Database.Luong_NhanVien,nhanvien.getLuong());
        values.put(Database.MaPB_PhongBan,nhanvien.getMapb());
        return db.update(Database.TABLE_NHANVIEN, values, Database.MaNV_NhanVien + "=?", new String[]{String.valueOf(nhanvien.getManv())});
    }

    public int XoaNhanVien(int id){
        db = dbhelper.getWritableDatabase();
       return db.delete(Database.TABLE_NHANVIEN, Database.MaNV_NhanVien + "=?", new String[]{String.valueOf(id)});
    }

    public void ThemNhanVien(NhanVienDTO nhanvien){
        db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.TenNV_NhanVien,nhanvien.getTennv());
        values.put(Database.SDT_NhanVien,nhanvien.getSdt());
        values.put(Database.GioiTinh_NhanVien,nhanvien.getGioitinh());
        //values.put(Database.NgaySinh_NhanVien,nhanvien.getNgaysinh());
        values.put(Database.DiaChi_NhanVien,nhanvien.getDiachi());
        values.put(Database.Email_NhanVien,nhanvien.getEmail());
        values.put(Database.Luong_NhanVien,nhanvien.getLuong());
        values.put(Database.MaPB_PhongBan,nhanvien.getMapb());

        db.insert(Database.TABLE_NHANVIEN,null,values);
        db.close();

    }

    public List<NhanVienDTO> LoadAllNhanVien(){
        List<NhanVienDTO> list = new ArrayList<NhanVienDTO>();
        db = dbhelper.getWritableDatabase(); //cho phép đọc là lưu vào csdl
        String sql = "select * from " + Database.TABLE_NHANVIEN;
       Cursor c =  db.rawQuery(sql, null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            NhanVienDTO nhanvien = new NhanVienDTO();
            nhanvien.setManv(Integer.parseInt(c.getString(c.getColumnIndex(Database.MaNV_NhanVien))));
            nhanvien.setTennv(c.getString(c.getColumnIndex(Database.TenNV_NhanVien)));
            nhanvien.setSdt(c.getString(c.getColumnIndex(Database.SDT_NhanVien)));
            nhanvien.setGioitinh(c.getString(c.getColumnIndex(Database.GioiTinh_NhanVien)));
           // nhanvien.setNgaysinh(c.getString(c.getColumnIndex(Database.NgaySinh_NhanVien)));
            nhanvien.setDiachi(c.getString(c.getColumnIndex(Database.DiaChi_NhanVien)));
            nhanvien.setEmail(c.getString(c.getColumnIndex(Database.Email_NhanVien)));
            nhanvien.setLuong(Integer.parseInt(c.getString(c.getColumnIndex(Database.Luong_NhanVien))));
            nhanvien.setMapb(Integer.parseInt(c.getString(c.getColumnIndex(Database.MaPB_PhongBan))));

            list.add(nhanvien);
            c.moveToNext();
        }
        return list;
    }
}
