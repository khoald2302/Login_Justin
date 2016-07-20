package com.justin.android.login_sample;

/**
 * Created by PC on 7/18/2016.
 */
    public class Model {
    private  String hoten;
    private  String sdt;
    private  String socmnd;
    private  String diachi;
    private  int    matinh;
    private  String hinhthucvay;

    public Model() {

    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Model(String hoten, String sdt, String socmnd, String diachi, int matinh, String hinhthucvay) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.socmnd = socmnd;
        this.diachi = diachi;
        this.matinh = matinh;
        this.hinhthucvay = hinhthucvay;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSocmnd() {
        return socmnd;
    }

    public void setSocmnd(String socmnd) {
        this.socmnd = socmnd;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getMatinh() {
        return matinh;
    }

    public void setMatinh(int matinh) {
        this.matinh = matinh;
    }

    public String getHinhthucvay() {
        return hinhthucvay;
    }

    public void setHinhthucvay(String hinhthucvay) {
        this.hinhthucvay = hinhthucvay;
    }


}
