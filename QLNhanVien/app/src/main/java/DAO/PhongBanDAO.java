package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import DTO.PhongBanDTO;

/**
 * Created by TuyetHanh on 2016-12-14.
 */

public class PhongBanDAO {
    Context context;
    Database dbhelper;
    SQLiteDatabase db;
    public PhongBanDAO(Context context){
        this.context = context;
        dbhelper = new Database(context); //class database đã khởi tạo lúc đầu

    }

    public int SuaPhongBanTheoMa(PhongBanDTO phongban){
        db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.TenPB_PhongBan, phongban.getTenphongban());

        return db.update(Database.TABLE_PHONGBAN,values,Database.MaPB_PhongBan + "=?", new String[]{String.valueOf(phongban.getMaphongban())});
    }

    public int XoaPhongBanTheoMa(int id){
        db = dbhelper.getWritableDatabase();
        return db.delete(Database.TABLE_PHONGBAN,Database.MaPB_PhongBan + "=?", new String[]{String.valueOf(id)});

    }

    public  void ThemPhongBan(PhongBanDTO phongban){
        db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Database.TenPB_PhongBan, phongban.getTenphongban());
        db.insert(Database.TABLE_PHONGBAN, null, values);
        db.close();
    }

    public List<PhongBanDTO> LayAllPhongBan(){
        List<PhongBanDTO> list = new ArrayList<PhongBanDTO>();
        db = dbhelper.getWritableDatabase();
        String sql = "select * from " + Database.TABLE_PHONGBAN;
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            PhongBanDTO phongban = new PhongBanDTO();
            phongban.setTenphongban(c.getString(c.getColumnIndex(Database.TenPB_PhongBan)));
            phongban.setMaphongban(Integer.parseInt(c.getString(c.getColumnIndex(Database.MaPB_PhongBan))));
            list.add(phongban);
            c.moveToNext();
        }
        return list;
    }
}
