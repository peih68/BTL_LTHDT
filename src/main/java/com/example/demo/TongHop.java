package com.example.demo;

public class TongHop {
    private String loaiTu;
    private String phatAm;
    private String yNghia;
    private String cauViDuTiengViet;
    private String cauViDuTiengAnh;

    public String getLoaiTu() {return loaiTu;}

    public String getPhatAm() {return phatAm;}

    public String getYNghia() {return yNghia;}

    public String getCauViDuTiengViet() {return cauViDuTiengViet;}

    public String getCauViDuTiengAnh() {return cauViDuTiengAnh;}

    public TongHop() {
        loaiTu = "" ; phatAm = "" ; yNghia = "" ; cauViDuTiengAnh = "" ; cauViDuTiengViet = "" ;
    }

    public TongHop(String loaiTu, String phatAm , String cauViDuTiengViet, String cauViDuTiengAnh, String yNghia) {
        this.loaiTu = loaiTu;
        this.cauViDuTiengViet = cauViDuTiengViet;
        this.cauViDuTiengAnh = cauViDuTiengAnh;
        this.yNghia = yNghia;
        this.phatAm = phatAm;
    }

    public void print(){
        System.out.println("Loại từ: " + loaiTu + " " + phatAm);
        System.out.println("Ý nghĩa: " + yNghia);
        System.out.println("Ví dụ: " + cauViDuTiengViet + "---" + cauViDuTiengAnh);
    }
}
