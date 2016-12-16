package vn.hue.husc.it.tuyethanh.qlnhanvien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import Adapter.Custom_Listview_NhanVien;
import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

public class NhanVienPhongBanActivity extends Activity {
    ListView listViewNhanVienPhongBan;
    NhanVienDAO dbNhanVien;
    List<NhanVienDTO> listNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nhanvien_phongban);

        listViewNhanVienPhongBan = (ListView)findViewById(R.id.listNhanVienPhongBan);
        dbNhanVien = new NhanVienDAO(this);

        Intent intent = getIntent();
        int mapb = Integer.parseInt(intent.getExtras().getString("maphongban"));
        listNhanVien = dbNhanVien.LayNhanVienTheoPhong(mapb);

        Custom_Listview_NhanVien adapter = new Custom_Listview_NhanVien(this,R.layout.custom_layout_nhanvien,listNhanVien);
        listViewNhanVienPhongBan.setAdapter(adapter);

        listViewNhanVienPhongBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iChiTietNhanVien = new Intent(NhanVienPhongBanActivity.this, ChiTietNhanVienActivity.class);
                iChiTietNhanVien.putExtra("manv", listNhanVien.get(position).getManv());
                startActivity(iChiTietNhanVien);
            }
        });
    }
}
