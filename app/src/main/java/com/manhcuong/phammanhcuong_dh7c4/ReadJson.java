package com.manhcuong.phammanhcuong_dh7c4;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadJson {
    public static SinhVien readSinhVienJSONFIle(Context context) throws IOException, JSONException {
        String jsonText = readText(context, R.raw.sinhvien);
        JSONObject jsonOC = new JSONObject(jsonText);
        int maSv = jsonOC.getInt("maSv");
        String hoTen = jsonOC.getString("hoTen");
        String tenKhoa = jsonOC.getString("tenKhoa");
        String diemTin = jsonOC.getString("diemTin");
        SinhVien sv = new SinhVien();
        sv.setMasv(maSv);
        sv.setHoten(hoTen);
        sv.setTenkhoa(tenKhoa);
        sv.setDiemtin(diemTin);
        return sv;

    }

    private static String readText(Context context, int resld) throws IOException {
        InputStream iS = context.getResources().openRawResource(resld);
        BufferedReader bR = new BufferedReader(new InputStreamReader(iS));
        StringBuilder sB = new StringBuilder();
        String s = null;
        while ((s = bR.readLine())!=null){
            sB.append(s+"\n");
        }
        return sB.toString();
    }
}
