package hcmute.edu.vn.nhom.project_foody.myclass;

import java.io.Serializable;

public class Quan_An implements Serializable {
    //int maquan , String tenquan ,String diachi, String doangioithieu, String anhdaidien , String thoigiandongcua ,
    // String thoigianmocua , int matinh , int maloaihinhkinhdoanh , int mathucdon, int mawifi
    private int maquan;
    private String tenquan;
    private String diachi;
    private String doangioithieu;
    private String anhdaidien;
    private String thoigiandongcua;
    private String thoigianmocua;
    private int matinh;
    private int maloaihinhkinhdoanh;
    private int mathucdon;
    private int mawifi;

    public Quan_An(int maquan, String tenquan, String diachi, String doangioithieu, String anhdaidien, String thoigiandongcua, String thoigianmocua, int matinh, int maloaihinhkinhdoanh, int mathucdon, int mawifi) {
        this.maquan = maquan;
        this.tenquan = tenquan;
        this.diachi = diachi;
        this.doangioithieu = doangioithieu;
        this.anhdaidien = anhdaidien;
        this.thoigiandongcua = thoigiandongcua;
        this.thoigianmocua = thoigianmocua;
        this.matinh = matinh;
        this.maloaihinhkinhdoanh = maloaihinhkinhdoanh;
        this.mathucdon = mathucdon;
        this.mawifi = mawifi;
    }

    public Quan_An() {
    }

    public int getMaquan() {
        return maquan;
    }

    public void setMaquan(int maquan) {
        this.maquan = maquan;
    }

    public String getTenquan() {
        return tenquan;
    }

    public void setTenquan(String tenquan) {
        this.tenquan = tenquan;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDoangioithieu() {
        return doangioithieu;
    }

    public void setDoangioithieu(String doangioithieu) {
        this.doangioithieu = doangioithieu;
    }

    public String getAnhdaidien() {
        return anhdaidien;
    }

    public void setAnhdaidien(String anhdaidien) {
        this.anhdaidien = anhdaidien;
    }

    public String getThoigiandongcua() {
        return thoigiandongcua;
    }

    public void setThoigiandongcua(String thoigiandongcua) {
        this.thoigiandongcua = thoigiandongcua;
    }

    public String getThoigianmocua() {
        return thoigianmocua;
    }

    public void setThoigianmocua(String thoigianmocua) {
        this.thoigianmocua = thoigianmocua;
    }

    public int getMatinh() {
        return matinh;
    }

    public void setMatinh(int matinh) {
        this.matinh = matinh;
    }

    public int getMaloaihinhkinhdoanh() {
        return maloaihinhkinhdoanh;
    }

    public void setMaloaihinhkinhdoanh(int maloaihinhkinhdoanh) {
        this.maloaihinhkinhdoanh = maloaihinhkinhdoanh;
    }

    public int getMathucdon() {
        return mathucdon;
    }

    public void setMathucdon(int mathucdon) {
        this.mathucdon = mathucdon;
    }

    public int getMawifi() {
        return mawifi;
    }

    public void setMawifi(int mawifi) {
        this.mawifi = mawifi;
    }
}
