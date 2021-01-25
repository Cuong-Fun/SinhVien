package com.manhcuong.phammanhcuong_dh7c4;

public class SinhVien {
    private int masv;
    private String hoten;
    private String tenkhoa;
    private String diemtin;

    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public String getDiemtin() {
        return diemtin;
    }

    public void setDiemtin(String diemtin) {
        this.diemtin = diemtin;
    }

    public SinhVien() {
    }

    public SinhVien(String hoten, String tenkhoa, String diemtin) {
        this.hoten = hoten;
        this.tenkhoa = tenkhoa;
        this.diemtin = diemtin;
    }

    public SinhVien(int masv, String hoten, String tenkhoa, String diemtin) {
        this.masv = masv;
        this.hoten = hoten;
        this.tenkhoa = tenkhoa;
        this.diemtin = diemtin;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "masv=" + masv +
                ", hoten='" + hoten + '\'' +
                ", tenkhoa='" + tenkhoa + '\'' +
                ", diemtin='" + diemtin + '\'' +
                '}';
    }
    public String masv()
    {
        return String.valueOf(masv);
    }
    public String hoten()
    {
        return hoten;
    }
    public String tenkhoa()
    {
        return tenkhoa;
    }
    public String diemtin()
    {
        return diemtin;
    }
}

