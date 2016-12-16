package vn.hue.husc.it.tuyethanh.qlnhanvien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import Adapter.Custom_Spinner_ThemNhanVien;
import DAO.NhanVienDAO;
import DAO.PhongBanDAO;
import DTO.NhanVienDTO;
import DTO.PhongBanDTO;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

public class CapNhatNhanVienActivity extends Activity {
    PhongBanDAO dbPhongBan;
    NhanVienDAO dbNhanVien;
    Spinner spinner;
    EditText txtTenNhanVien, txtDiaChi, txtSoDienThoai, txtNgaySinh, txtLuong, txtEmail;
    Button btnThem, btnThoat;
    List<PhongBanDTO> listPhongban;
    int vitri;
    int iMaNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capnhatnhanvien);
        btnThem = (Button)findViewById(R.id.btnCapNhatNhanVien);
        btnThoat = (Button)findViewById(R.id.btnThoatCapNhatNhanVien);
        txtTenNhanVien = (EditText)findViewById(R.id.editSuaTenNhanVien);
        txtSoDienThoai = (EditText)findViewById(R.id.editSuaSoDienThoai);
        txtNgaySinh = (EditText)findViewById(R.id.editSuaNgaySinh);
        txtLuong = (EditText)findViewById(R.id.editSuaLuong);
        txtEmail = (EditText)findViewById(R.id.editSuaEmail);
        txtDiaChi = (EditText)findViewById(R.id.editSuaDiaChi);
        spinner = (Spinner)findViewById(R.id.spinnerSuaPhongBan);

        Intent intent = getIntent();
        iMaNV = intent.getExtras().getInt("manv");
        this.setResult(RESULT_OK);

        dbPhongBan =  new PhongBanDAO(this);
        dbNhanVien = new NhanVienDAO(this);
        listPhongban = dbPhongBan.LayAllPhongBan();
        Custom_Spinner_ThemNhanVien adapter = new Custom_Spinner_ThemNhanVien(this, R.layout.custom_layout_spinner,listPhongban);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vitri = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatNhanVien();
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void CapNhatNhanVien(){
        NhanVienDTO nhanvien = new NhanVienDTO();
        dbNhanVien.CapNhatNhanVien(nhanvien);

        RadioGroup rdGroup = (RadioGroup)findViewById(R.id.rdSuaGioiTinh);
        int rdID = rdGroup.getCheckedRadioButtonId();
        RadioButton rdChecked = (RadioButton)findViewById(rdID);
        String gioitinh = rdChecked.getText().toString();
        int mapb = listPhongban.get(vitri).getMaphongban();

        nhanvien.setManv(iMaNV);
        nhanvien.setDiachi(txtDiaChi.getText().toString());
        nhanvien.setEmail(txtEmail.getText().toString());
        nhanvien.setGioitinh(gioitinh);
        nhanvien.setLuong(Integer.parseInt(txtLuong.getText().toString()));
        nhanvien.setMapb(mapb);
        nhanvien.setNgaysinh(txtNgaySinh.getText().toString());
        nhanvien.setSdt(txtSoDienThoai.getText().toString());
        nhanvien.setTennv(txtTenNhanVien.getText().toString());

        if(dbNhanVien.CapNhatNhanVien(nhanvien) != -1){
            Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();

        }
    }
}
