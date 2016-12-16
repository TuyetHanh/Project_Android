package vn.hue.husc.it.tuyethanh.qlnhanvien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.Custom_Listview_NhanVien;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

public class NhanVienActivity extends Activity{

    NhanVienDAO dbNhanVien;//kết nối csdl
    List<NhanVienDTO> listNhanVien;
    Custom_Listview_NhanVien adapter;
    ListView listviewNhanVien;
    int vitri;
    int idnhanvien;
    public static int RESULT_CAPNHATNHANVIEN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nhanvien);

        LinearLayout layout_nhanvien = (LinearLayout)findViewById(R.id.layout_nhanvien);

        dbNhanVien = new NhanVienDAO(this);

        LoadListViewNhanVien();
        registerForContextMenu(layout_nhanvien);
        registerForContextMenu(listviewNhanVien);

        listviewNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                vitri = position;
                return false;
            }
        });

        listviewNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iChiTietNhanVien =  new Intent(NhanVienActivity.this, ChiTietNhanVienActivity.class);
                iChiTietNhanVien.putExtra("manv", listNhanVien.get(position).getManv());
                startActivity(iChiTietNhanVien);
            }
        });

    }

    private void LoadListViewNhanVien(){
        listNhanVien = new ArrayList<NhanVienDTO>();
        listNhanVien = dbNhanVien.LoadAllNhanVien();
        adapter = new Custom_Listview_NhanVien(this, R.layout.custom_layout_nhanvien, listNhanVien);
        listviewNhanVien = (ListView)findViewById(R.id.listNhanVien);
        listviewNhanVien.setAdapter(adapter);
    }

    private void XoaNhanVien(){
       idnhanvien = listNhanVien.get(vitri).getManv();
        if(dbNhanVien.XoaNhanVien(idnhanvien) != -1 ){
            Toast.makeText(getApplicationContext(),"Xóa thành công", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        menu.setHeaderTitle("Chức năng phòng ban");
        menu.setHeaderIcon(R.drawable.ic_avatar); // sửa lại icon chức năng
        getMenuInflater().inflate(R.menu.menu_chucnang,menu);
        if(v.getId() == R.id.listNhanVien){
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(false);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       int id = item.getItemId();
        if(id == R.id.menuThem){

            Intent iThemNhanVien = new Intent(NhanVienActivity.this, ThemNhanVienActivity.class);
            startActivity(iThemNhanVien);
        }

        if(id == R.id.menuXoa){
            XoaNhanVien();
            LoadListViewNhanVien();
        }

        if (id == R.id.menuSua){ //click vào menu sửa sẽ chuyển đến cập nhật nhân viên class
            Intent iCapNhatNhanVien = new Intent(NhanVienActivity.this, CapNhatNhanVienActivity.class);
            int idnhanvien = listNhanVien.get(vitri).getManv();
            iCapNhatNhanVien.putExtra("manv", idnhanvien);
            startActivityForResult(iCapNhatNhanVien, RESULT_CAPNHATNHANVIEN);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_CAPNHATNHANVIEN && requestCode == RESULT_OK){
            LoadListViewNhanVien();
        }
    }
}
