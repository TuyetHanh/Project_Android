package vn.hue.husc.it.tuyethanh.qlnhanvien;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

//Hiên thị layout chi tiết nhân viên đã tạo
public class ChiTietNhanVienActivity extends Activity{

    TextView vTenNv, vGioiTinh, vSoDienThoai, vDiaChi, vLuong, vNgaySinh, vPhongBan, vEmail;
    NhanVienDAO dbNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietnhanvien);

        vTenNv = (TextView)findViewById(R.id.vCTTenNhanVien);
        vGioiTinh = (TextView)findViewById(R.id.vCTGioiTinh);
        vSoDienThoai = (TextView)findViewById(R.id.vCTSoDienThoai);
        vDiaChi = (TextView)findViewById(R.id.vHTDiaChi);
        vPhongBan = (TextView)findViewById(R.id.vHTPhongBan);
        vEmail = (TextView)findViewById(R.id.vHTEmail);
        vNgaySinh = (TextView)findViewById(R.id.vHTNgaySinh);
        vPhongBan = (TextView)findViewById(R.id.vHTPhongBan);
        vLuong  = (TextView)findViewById(R.id.vHTLuong);

        int id = getIntent().getExtras().getInt("manv");
        dbNhanVien = new NhanVienDAO(this);
        NhanVienDTO nhanvien = new NhanVienDTO();
        nhanvien = dbNhanVien.LayNhanVienTheoMa(id);

        vTenNv.setText(nhanvien.getTennv().toString());
        vGioiTinh.setText(nhanvien.getGioitinh().toString());
        vSoDienThoai.setText(nhanvien.getSdt().toString());
        vDiaChi.setText(nhanvien.getDiachi().toString());
        vEmail.setText(nhanvien.getEmail().toString());
        vLuong.setText(String.valueOf(nhanvien.getLuong()));
        vNgaySinh.setText("17/11/1995");
        vPhongBan.setText(nhanvien.getTenphongban());
    }
}
