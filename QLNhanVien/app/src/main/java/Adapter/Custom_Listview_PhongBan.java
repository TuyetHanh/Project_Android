package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import DTO.PhongBanDTO;
import vn.hue.husc.it.tuyethanh.qlnhanvien.R;

/**
 * Created by TuyetHanh on 2016-12-14.
 */

public class Custom_Listview_PhongBan extends ArrayAdapter<PhongBanDTO> {

    Context context;
    int resource;
    List<PhongBanDTO> objects;

    public Custom_Listview_PhongBan(Context context, int resource, List<PhongBanDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewrow = inflater.inflate(R.layout.custom_listview_phongban,parent,false);
        TextView vTenPhongBan = (TextView)viewrow.findViewById(R.id.viewListTenPhongBan);
        TextView vMaPhongBan = (TextView)viewrow.findViewById(R.id.viewListMaPhongBan);
        TextView vSoNhanVien = (TextView)viewrow.findViewById(R.id.viewListSoNhanVien);

        PhongBanDTO phongban = objects.get(position);
        vTenPhongBan.setText(phongban.getTenphongban());
        vMaPhongBan.setText("Mã phòng ban: " + String.valueOf(phongban.getMaphongban()));
        vSoNhanVien.setText("");

        return viewrow;
    }
}
