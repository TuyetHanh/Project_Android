package Adapter;

import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import DTO.NhanVienDTO;
import DTO.PhongBanDTO;
import vn.hue.husc.it.tuyethanh.qlnhanvien.R;

/**
 * Created by TuyetHanh on 2016-12-15.
 */

public class Custom_Spinner_ThemNhanVien extends ArrayAdapter<PhongBanDTO>{

    Context context;
    int resource;
    List<PhongBanDTO> object;

    public Custom_Spinner_ThemNhanVien(Context context, int resource, List<PhongBanDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;
    }

    /**/@Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    } /**/

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewrow = inflater.inflate(R.layout.custom_layout_spinner, parent, false);

        TextView vTenPhongBan = (TextView)viewrow.findViewById(R.id.vSpinnerPhongBan);

        PhongBanDTO phongban = object.get(position);//lấy vị trí list phòng ban truyền vào
        vTenPhongBan.setText(phongban.getTenphongban().toString());
        return viewrow;
    }
}
