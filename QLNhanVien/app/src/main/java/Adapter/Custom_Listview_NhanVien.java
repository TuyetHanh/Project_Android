package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import DTO.NhanVienDTO;
import vn.hue.husc.it.tuyethanh.qlnhanvien.R;

/**
 * Created by TuyetHanh on 2016-12-15.
 */
//hiển thị listview theo layout_custom_listview đã tạo
public class Custom_Listview_NhanVien extends ArrayAdapter<NhanVienDTO>{

    Context context;
    int resource;
    List<NhanVienDTO> objects;

    public Custom_Listview_NhanVien(Context context, int resource, List<NhanVienDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewrow = inflater.inflate(R.layout.custom_layout_nhanvien, parent, false);

        TextView vTenNhanVien = (TextView)viewrow.findViewById(R.id.tvTenNhanVien);
        TextView vGioiTinh = (TextView)viewrow.findViewById(R.id.vGioiTinh);
        TextView vSoDienThoai = (TextView)viewrow.findViewById(R.id.vSoDienThoai);

        NhanVienDTO nhanvien = objects.get(position);

        vTenNhanVien.setText(nhanvien.getTennv());
        vGioiTinh.setText("Giới tính: " + nhanvien.getGioitinh());
        vSoDienThoai.setText("SĐT: " + nhanvien.getSdt());

        return viewrow;
    }
}
