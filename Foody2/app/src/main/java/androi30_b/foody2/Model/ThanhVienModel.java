package androi30_b.foody2.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Dell on 5/12/2017.
 */

public class ThanhVienModel {
    String hinhanh, hoten, mathanhvien;
    private DatabaseReference dataNodeThanhvien;

    public ThanhVienModel(){
        dataNodeThanhvien = FirebaseDatabase.getInstance().getReference().child("thanhviens");

    }

    public String getMathanhvien() {
        return mathanhvien;
    }

    public void setMathanhvien(String mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    public void themThanhVienModel(ThanhVienModel thanhvienmodel, String uid){
        dataNodeThanhvien.child(uid).setValue(thanhvienmodel);
    }
}
