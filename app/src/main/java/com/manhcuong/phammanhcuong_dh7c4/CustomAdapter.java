package com.manhcuong.phammanhcuong_dh7c4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<SinhVien> {
    private Context context;
    private int resource;
    private List<SinhVien> ListSV;
    public CustomAdapter(Context context, int resource, List<SinhVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.ListSV= objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=
                    LayoutInflater.from(context).inflate(R.layout.tao_lv,parent,false);
            viewHolder=new ViewHolder();
            //viewHolder.csid=(TextView)convertView.findViewById(R.id.csid);
            viewHolder.id=(TextView)convertView.findViewById(R.id.txtid);
            viewHolder.cshoten=(TextView)convertView.findViewById(R.id.cshoten);
            viewHolder.cskhoa=(TextView)convertView.findViewById(R.id.cskhoa);
            viewHolder.csdiemtin=(TextView)convertView.findViewById(R.id.csdiemtin);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        SinhVien sv=ListSV.get(position);
//        viewHolder.csid.setText(String.valueOf(sv.getMasv()));
        viewHolder.id.setText(String.valueOf(sv.getMasv()));
        viewHolder.cshoten.setText(sv.getHoten());
        viewHolder.cskhoa.setText(sv.getTenkhoa());
        viewHolder.csdiemtin.setText(sv.getDiemtin());

        return convertView;
    }
    public class ViewHolder{
        //khai báo các TextView trong Item su dung
        private TextView csid,cshoten,cskhoa,csdiemtin,id;

    }
}

