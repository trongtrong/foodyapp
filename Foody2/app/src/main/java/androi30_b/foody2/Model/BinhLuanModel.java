package androi30_b.foody2.Model;

import java.util.List;

/**
 * Created by Dell on 5/12/2017.
 */

public class BinhLuanModel {
    double chamdiem;
    long luotthich;
    ThanhVienModel thanhVienModel;
    String noidung, tieude, mauser;
   List<String> hinhAnhbinhLuanList;
    String maBinhLuan;

    public BinhLuanModel(){

    }

    public List<String> getHinhAnhbinhLuanList() {
        return hinhAnhbinhLuanList;
    }

    public void setHinhAnhbinhLuanList(List<String> hinhAnhbinhLuanList) {
        this.hinhAnhbinhLuanList = hinhAnhbinhLuanList;
    }

    public String getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(String maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
    }


    public String getMauser() {
        return mauser;
    }

    public void setMauser(String mauser) {
        this.mauser = mauser;
    }

    public double getChamdiem() {
        return chamdiem;
    }

    public void setChamdiem(double chamdiem) {
        this.chamdiem = chamdiem;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public ThanhVienModel getThanhVienModel() {
        return thanhVienModel;
    }

    public void setThanhVienModel(ThanhVienModel thanhVienModel) {
        this.thanhVienModel = thanhVienModel;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }
}
