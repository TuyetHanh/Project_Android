package vn.hue.husc.it.tuyethanh.qlnhanvien;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.Custom_Spinner_ThemNhanVien;
import DAO.NhanVienDAO;
import DAO.PhongBanDAO;
import DTO.NhanVienDTO;
import DTO.PhongBanDTO;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

public class ThemNhanVienActivity extends Activity{

    Spinner spinnerPhongBan;
    EditText txtTenNhanVien, txtDiaChi, txtSoDienThoai, txtNgaySinh, txtLuong, txtEmail;
    Button btnThem, btnThoat;
    List<PhongBanDTO> list;
    PhongBanDAO dbPhongBan;
    NhanVienDAO dbNhanVien;
    int vitri;

     @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_themnhanvien);

         btnThem = (Button)findViewById(R.id.btnThemNhanVien);
         btnThoat = (Button)findViewById(R.id.btnThoatNhanVien);
         txtTenNhanVien = (EditText)findViewById(R.id.editThemTenNhanVien);
         txtSoDienThoai = (EditText)findViewById(R.id.editThemSoDienThoai);
         txtNgaySinh = (EditText)findViewById(R.id.editThemNgaySinh);
         txtLuong = (EditText)findViewById(R.id.editThemLuong);
         txtEmail = (EditText)findViewById(R.id.editThemEmail);
         txtDiaChi = (EditText)findViewById(R.id.editThemDiaChi);

        dbPhongBan = new PhongBanDAO(this);
         dbNhanVien = new NhanVienDAO(this);

        spinnerPhongBan = (Spinner)findViewById(R.id.spinnerPhongBan);
        list = new ArrayList<PhongBanDTO>();
         list = dbPhongBan.LayAllPhongBan();

         Custom_Spinner_ThemNhanVien adapter = new Custom_Spinner_ThemNhanVien(this,R.layout.custom_layout_spinner,list);
         //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPhongBan.setAdapter(adapter);

         spinnerPhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                 RadioGroup rdGroup = (RadioGroup)findViewById(R.id.rdGioiTinh);
                 int rdID = rdGroup.getCheckedRadioButtonId();
                 RadioButton rdChecked = (RadioButton)findViewById(rdID);
                 String gioitinh = rdChecked.getText().toString();
                 int mapb = list.get(vitri).getMaphongban();

                 try {
                     NhanVienDTO nhanvien = new NhanVienDTO();
                     nhanvien.setDiachi(txtDiaChi.getText().toString());
                     nhanvien.setEmail(txtEmail.getText().toString());
                     nhanvien.setGioitinh(gioitinh);
                     nhanvien.setLuong(Integer.parseInt(txtLuong.getText().toString()));
                     nhanvien.setMapb(mapb);
                     nhanvien.setNgaysinh(txtNgaySinh.getText().toString());
                     nhanvien.setSdt(txtSoDienThoai.getText().toString());
                     nhanvien.setTennv(txtTenNhanVien.getText().toString());

                     dbNhanVien.ThemNhanVien(nhanvien);
                     Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();

                 }catch (Exception e){
                     Toast.makeText(getApplication(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                 }
             }
         });

         btnThoat.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
     }

}
