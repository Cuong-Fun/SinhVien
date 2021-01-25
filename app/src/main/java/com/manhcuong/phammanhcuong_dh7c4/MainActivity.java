package com.manhcuong.phammanhcuong_dh7c4;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter customAdapter;
    final Database db = new Database(this);
    private List<SinhVien> listsv;
    Button Save,Update,Delete,Show,Clear;
    EditText masv,hoten,tenkhoa,diemtin;
    ListView lv;
    int vitri = -1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Save = (Button) findViewById(R.id.Save);
        Update = (Button) findViewById(R.id.Update);
        Delete = (Button) findViewById(R.id.Delete);
        Show = (Button) findViewById(R.id.HienThi);

        masv = (EditText) findViewById(R.id.masv);
        hoten = (EditText) findViewById(R.id.hoten);

        tenkhoa = (EditText) findViewById(R.id.tenkhoa);

        diemtin = (EditText) findViewById(R.id.diemtin);

        Clear = (Button) findViewById(R.id.btnClean);

        lv = (ListView) findViewById(R.id.lv);
        listsv = db.getAllSV();
        setAdapter();

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinhVien sv = CreateSV();
                if(sv!=null)
                {
                    db.addSV(sv);
                }
                updateListSV();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinhVien sv = new SinhVien(Integer.parseInt(masv.getText().toString()),hoten.getText().toString(),tenkhoa.getText().toString(),diemtin.getText().toString());
                if(sv!=null)
                {
                    db.UpdateSV(sv);
                }
                updateListSV();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maSinhVien =0;
                maSinhVien = Integer.parseInt(masv.getText().toString());
                if(maSinhVien != 0){
                    db.DeleteSV(maSinhVien);
                }
                updateListSV();
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                SinhVien sv = listsv.get(position);
                masv.setText(String.valueOf(sv.getMasv()));
                hoten.setText(sv.getHoten()+"");
                tenkhoa.setText(sv.getTenkhoa()+"");
                diemtin.setText(sv.getDiemtin()+"");
                vitri = position;
            }

        });


        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateListSV();
               }
        });

            Clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = masv.getText().toString();
                    if (id.equals(String.valueOf(""))) {
                        masv.setError("NhapID");
                        return;
                    }
                    searchListSV(id);
                }
            });
    }

    private SinhVien CreateSV() {
        String Name = hoten.getText().toString();
        String TenKhoa = tenkhoa.getText().toString();
        String DiemTin = diemtin.getText().toString();
        SinhVien sv = new SinhVien(Name,TenKhoa,DiemTin);
        return sv;
    }

    private void setAdapter(){
        if(customAdapter==null){
            customAdapter=new CustomAdapter(this,R.layout.tao_lv,listsv);
            lv.setAdapter(customAdapter);
        }else {
            customAdapter.notifyDataSetChanged();
        }
    }

    public void updateListSV() {
        listsv.clear();
        listsv.addAll(db.getAllSV());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }

    public void searchListSV(String id) {
        listsv.clear();
        listsv.addAll(db.searchSV(id));
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }
    public void demo(View view){
        try {

            SinhVien sv = ReadJson.readSinhVienJSONFIle(this);
            masv.setText(sv.masv());
            hoten.setText(sv.getHoten());
            tenkhoa.setText(sv.getTenkhoa());
            diemtin.setText(sv.getDiemtin());
        }catch (IOException e){
            e.printStackTrace();
        }catch ( JSONException e){
            e.printStackTrace();
        }
    }

}

