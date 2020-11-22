
package bai7;

import java.util.StringTokenizer;

class maSVException extends Exception{
    public String toString(){
        return "ma sinh vien phai co 4 chu so";
    }
}
class tenException extends Exception{
    public String toString(){
        return "ten sinh vien phai gom ca ho va ten";
    }
}
class lopException extends Exception{
    public String toString(){
        return "quy tac dat ten cua PTIT phai duoc tuan thu";
    }
}class dtbException extends Exception{
    public String toString(){
        return "dtb phai tu 0 - 10";
    }
}
public class SinhVienPTIT {
    private int maSV;
    private String ten;
    private String lop;
    private double dtb;
    private static String lopPattern="D\\d{2}CQ(CN||AT||VT)\\d{2}-B";
    
    public SinhVienPTIT() {
    }

    public SinhVienPTIT(int maSV, String ten, String lop, double dtb) {
        this.maSV = maSV;
        this.ten = ten;
        this.lop = lop;
        this.dtb = dtb;
    }

    public void setMaSV(int maSV) throws maSVException {
        if(maSV>=1000 && maSV<=9999)
        this.maSV = maSV;
        else throw new maSVException();
    }

    public void setTen(String ten) throws tenException {
        StringTokenizer st=new StringTokenizer(ten);
        if(st.countTokens()<=1) throw new tenException(); //phai du ca ho va ten
        else
        this.ten = ten;
    }

    public void setLop(String lop) throws lopException {
        if(lop.matches(lopPattern))
        this.lop = lop;
        else throw new lopException();
    }

    public void setDtb(double dtb) throws dtbException {
        if(dtb>=0 && dtb<=10)
        this.dtb = dtb;
        else throw new dtbException();
    }

    public String getTen() {
        return ten;
    }

    public double getDtb() {
        return dtb;
    }

    @Override
    public String toString() {
        return maSV+" "+ten+" "+lop+" "+dtb;
    }

    public int getMaSV() {
        return maSV;
    }

    public String getLop() {
        return lop;
    }

    
    
    
    
}
