package DTO;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

public class NhanVienDTO {
    private int manv;
    private String tennv;
    private String sdt;
    private String gioitinh;
    private String ngaysinh;
    private String diachi;
    private String email;
    private int luong;
    private int mapb;
    private String tenphongban;

    public NhanVienDTO(){

    }
    public String getTenphongban(){return tenphongban;}

    public void setTenphongban(String tenphongban){this.tenphongban = tenphongban;}

    public int getManv(){
        return manv;
    }

    public void setManv(int manv){
        this.manv = manv;
    }

    public String getTennv(){
        return tennv;
    }

    public void setTennv(String tennv){
        this.tennv = tennv;
    }

    public String getSdt(){
        return sdt;
    }

    public void setSdt(String sdt){
        this.sdt = sdt;
    }

    public String getGioitinh(){
        return gioitinh;
    }

    public void setGioitinh(String gioitinh){
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh(){
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh){
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi(){
        return diachi;
    }

    public void setDiachi(String diachi){
        this.diachi = diachi;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getLuong(){
        return luong;
    }

    public void setLuong(int luong){
        this.luong = luong;
    }

    public int getMapb(){
        return mapb;
    }

    public void setMapb(int mapb){
        this.mapb = mapb;
    }

}
