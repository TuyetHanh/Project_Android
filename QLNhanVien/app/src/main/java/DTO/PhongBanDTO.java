package DTO;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

public class PhongBanDTO {
    private int maphongban;
    private String tenphongban;
    private int sonhanvien;

    public PhongBanDTO(){

    }

    public int getMaphongban(){
        return maphongban;
    }

    public void setMaphongban(int maphongban){
        this.maphongban = maphongban;
    }

    public String getTenphongban()
    {
        return tenphongban;
    }

    public void setTenphongban(String tenphongban){
        this.tenphongban = tenphongban;
    }

    public int getSonhanvien(){
        return sonhanvien;
    }

    public void setSonhanvien(int sonhanvien){
        this.sonhanvien = sonhanvien;
    }
}
