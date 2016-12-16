package vn.hue.husc.it.tuyethanh.qlnhanvien;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    SharedPreferences sharedPreferences;
    Button btnDangKy, btnThoatDK, btnDangNhap, btnThoatDN;
    EditText editTaiKhoanDK, editMatKhauDK, editNhapLaiMatKhauDK, editMatKhauDN, editTaiKhoanDN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);


        editTaiKhoanDN = (EditText)findViewById(R.id.editTenDangNhap);
        editMatKhauDN = (EditText)findViewById(R.id.editMatKhau);

        //Thử nhập dữ liệu
        //SharedPreferences.Editor edit = sharedPreferences.edit();
        //edit.putString("MatKhau", "tuyethanh");
        //edit.putString("TaiKhoan", "123");
        //edit.commit(); //tiến hành cập nhật dữ liệu cho sharedPreferences

    }

    public void DangKyTK(View v){
     /**   final String taikhoan, matkhau;
        taikhoan = sharedPreferences.getString("TaiKhoan","");
        matkhau = sharedPreferences.getString("MatKhau","");
        if(taikhoan.trim().length()==0 || matkhau.trim().length() == 0){
            final Dialog dal = new Dialog(this);
            dal.setContentView(R.layout.layout_dangky);
            dal.setTitle(R.string.titleDangKy);
            btnDangKy = (Button)dal.findViewById(R.id.btnDangKy);
            btnDangKy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String matkhaudk, nhaplaimatkhaudk;
                    editMatKhauDK = (EditText)dal.findViewById(R.id.editMatKhauDK);
                    editNhapLaiMatKhauDK = (EditText)dal.findViewById(R.id.editxNhapLaiMatKhauDK);
                    matkhaudk = editMatKhauDK.getText().toString();
                    nhaplaimatkhaudk = editNhapLaiMatKhauDK.getText().toString();
                    if(matkhaudk.equals(nhaplaimatkhaudk)){
                        Toast.makeText(getApplicationContext(),"Mật khẩu nhập lại không khớp",Toast.LENGTH_LONG).show();

                    }
                    else {

                    }
                }
            });
            dal.show();

        }
        else {
            Toast.makeText(getApplicationContext(),"Bạn đã có tài khoản", Toast.LENGTH_LONG).show();
        } **/

        final Dialog dal = new Dialog(this);
        dal.setContentView(R.layout.layout_dangky);
        dal.setTitle(R.string.titleDangKy);
        dal.show();
        editMatKhauDK = (EditText)dal.findViewById(R.id.editMatKhauDK);
        editNhapLaiMatKhauDK = (EditText)dal.findViewById(R.id.editNhapLaiMatKhauDK);
        editTaiKhoanDK = (EditText)dal.findViewById(R.id.editTaiKhoanDK);

        btnDangKy = (Button)dal.findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matkhaudk, nhaplaimatkhaudk;

                matkhaudk = editMatKhauDK.getText().toString();
                nhaplaimatkhaudk  =editNhapLaiMatKhauDK.getText().toString();
                if(!matkhaudk.equals(nhaplaimatkhaudk)){
                    Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không khớp", Toast.LENGTH_LONG).show();
                }
                else {
                   // Toast.makeText(getApplicationContext(), "Mật khẩu khớp", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("MatKhau",editMatKhauDK.getText().toString());
                    edit.putString("TaiKhoan",editTaiKhoanDK.getText().toString());
                    edit.commit();
                    Toast.makeText(getApplication(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void DangNhap(View v){
        String matkhauconfig, taikhoanconfig, taikhoanDN, matkhauDN;
        taikhoanconfig = sharedPreferences.getString("TaiKhoan", "");
        matkhauconfig = sharedPreferences.getString("MatKhau","");
        taikhoanDN = editTaiKhoanDN.getText().toString();
        matkhauDN = editMatKhauDN.getText().toString();

        if(!(taikhoanDN.equals(taikhoanconfig) && matkhauDN.equals(matkhauconfig))){
            Intent iPhongBan = new Intent(MainActivity.this, PhongBanActivity.class);
            startActivity(iPhongBan);
            Toast.makeText(getApplicationContext(),"Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //load file menu main
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.menuDangKy){

            String taikhoan, matkhau;
            taikhoan = sharedPreferences.getString("TaiKhoan","");
            matkhau = sharedPreferences.getString("MatKhau", "");

            if(matkhau.trim().length()==0 || matkhau.trim().length() ==0){
                final Dialog dal =  new Dialog(this);
                dal.setContentView(R.layout.layout_dangky);
                dal.setTitle(R.string.titleDangKy);


                btnDangKy = (Button)dal.findViewById(R.id.btnDangKy);
                btnDangKy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });
                dal.show();
            }
            else{

                Toast.makeText(getApplicationContext(),"Bạn không thể đăng kí vì đã có tài khoản",Toast.LENGTH_LONG).show();
            }


        }
        return super.onOptionsItemSelected(item);
    }
}
