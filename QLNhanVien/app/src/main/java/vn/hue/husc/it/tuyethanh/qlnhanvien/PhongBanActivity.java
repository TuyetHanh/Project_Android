package vn.hue.husc.it.tuyethanh.qlnhanvien;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.Custom_Listview_PhongBan;
import DTO.PhongBanDTO;
import DAO.PhongBanDAO;

/**
 * Created by TuyetHanh on 2016-12-14.
 */

public class PhongBanActivity extends Activity {
    ListView listviewPhongBan;
    LinearLayout layout_phongban;
    EditText editTenPhongBan;
    Button btnThem;
    Button btnSua;
    PhongBanDAO dbPhongBan;
    Custom_Listview_PhongBan adapter;
    List<PhongBanDTO> listPhongBan;
    int position; //vị trí

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_phongban);

        listviewPhongBan = (ListView)findViewById(R.id.listPhongBan);
        layout_phongban = (LinearLayout)findViewById(R.id.layout_phongban);
        registerForContextMenu(listviewPhongBan);
        registerForContextMenu(layout_phongban);

        dbPhongBan = new PhongBanDAO(this);

        LoadListViewPhongBan();

        listviewPhongBan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int vt, long id) {
                position = vt; //vị trí của listview
                return false;
            }
        });

        listviewPhongBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int vt, long id) {
                Intent iNhanVienPhongBan = new Intent(PhongBanActivity.this, NhanVienPhongBanActivity.class);
                iNhanVienPhongBan.putExtra("maphongban", String.valueOf(listPhongBan.get(vt).getMaphongban()));
                startActivity(iNhanVienPhongBan);

            }
        });
    }


    public  void LoadListViewPhongBan(){
        listPhongBan = new ArrayList<PhongBanDTO>();
        listPhongBan = dbPhongBan.LayAllPhongBan();
        adapter = new Custom_Listview_PhongBan(this,R.layout.custom_listview_phongban,listPhongBan);
        listviewPhongBan.setAdapter(adapter);
    }


    //cho phép hiển thị context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Chức năng phòng ban");
        menu.setHeaderIcon(R.drawable.ic_dangky); // sửa lại icon chức năng

        getMenuInflater().inflate(R.menu.menu_chucnang,menu);
        if(v.getId() == R.id.listPhongBan){
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(false);
        }
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.menuThem){
            final Dialog dal = new Dialog(this);
            dal.setTitle("Thêm phòng ban");
            dal.setContentView(R.layout.layout_themphongban);
            editTenPhongBan = (EditText)dal.findViewById(R.id.editTenPhongBan);
            btnThem = (Button)dal.findViewById(R.id.btnThem);
            dal.show();

            btnThem.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    PhongBanDTO phongban = new PhongBanDTO();
                    phongban.setTenphongban(editTenPhongBan.getText().toString());
                    dbPhongBan.ThemPhongBan(phongban);
                    Toast.makeText(getApplicationContext(),"Thêm thành công", Toast.LENGTH_LONG).show();
                    LoadListViewPhongBan();
                    dal.dismiss();

                }
            });

        }
        if(id == R.id.menuXoa){
            int maphongban = listPhongBan.get(position).getMaphongban();
            if (dbPhongBan.XoaPhongBanTheoMa(maphongban) != -1 ) {
                Toast.makeText(getApplicationContext(),"Xóa thành công!",Toast.LENGTH_SHORT).show();
                LoadListViewPhongBan();
            }
            else {
                Toast.makeText(getApplicationContext(),"Xóa thất bại!",Toast.LENGTH_SHORT).show();

            }
        }
        if(id == R.id.menuSua){
            final Dialog dal = new Dialog(this);
            dal.setTitle("Sửa phòng ban");
            dal.setContentView(R.layout.layout_suaphongban);
            EditText editMaPhongBanSua = (EditText)dal.findViewById(R.id.editMaPhongBanSua);
            final EditText editTenPhongBanSua = (EditText)dal.findViewById(R.id.editTenPhongBanSua);
            btnSua = (Button)dal.findViewById(R.id.btnSua);

            editMaPhongBanSua.setEnabled(false);
            final String maphongban = String.valueOf(listPhongBan.get(position).getMaphongban());
            editMaPhongBanSua.setText(maphongban);


            dal.show();

            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PhongBanDTO phongban = new PhongBanDTO();
                    phongban.setMaphongban(Integer.parseInt(maphongban));
                    phongban.setTenphongban(editTenPhongBanSua.getText().toString());

                    AlertDialog.Builder alb = new AlertDialog.Builder(PhongBanActivity.this);
                    alb.setTitle("Thông báo");
                    alb.setMessage("Bạn có muốn sửa ?");
                    alb.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbPhongBan.SuaPhongBanTheoMa(phongban) != -1) {
                                Toast.makeText(getApplicationContext(), "Sửa thành công!", Toast.LENGTH_SHORT).show();
                                LoadListViewPhongBan();
                                dal.dismiss();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Sửa thật bại!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                    alb.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            dal.dismiss();
                        }
                    });
                    alb.show();

                }
            });

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuNhanVien:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuHeThong:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void NhanVien(View v){ // click vào thì chuyển đến layout nhân viên
        /*Intent iNhanVienActivity = new Intent(PhongBanActivity.this, NhanVienActivity.class);
        startActivity(iNhanVienActivity);
        Toast.makeText(getApplicationContext(),R.string.menuItemNhanVien,Toast.LENGTH_SHORT).show(); */
        Intent iNhanVienActivity = new Intent(PhongBanActivity.this,NhanVienActivity.class);
        startActivity(iNhanVienActivity);
    }
    public void PhongBan(View v){
        Toast.makeText(getApplicationContext(),R.string.menuItemPhongBan,Toast.LENGTH_SHORT).show();
    }
    public void HeThong(View v){
        Toast.makeText(getApplicationContext(),R.string.menuItemHeThong,Toast.LENGTH_SHORT).show();
    }
    public void LienHe(View v){
        Toast.makeText(getApplicationContext(),R.string.menuItemLienHe,Toast.LENGTH_SHORT).show();
    }


}
