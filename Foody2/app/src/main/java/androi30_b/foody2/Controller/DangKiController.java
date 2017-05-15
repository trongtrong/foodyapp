package androi30_b.foody2.Controller;

import androi30_b.foody2.Model.ThanhVienModel;

/**
 * Created by Dell on 5/12/2017.
 */

public class DangKiController {
    ThanhVienModel thanhVienModel;

    public DangKiController(){
        thanhVienModel = new ThanhVienModel();
    }
    public void themThanhVienMOdelController(ThanhVienModel thanhVienMode, String uid){
        thanhVienMode.themThanhVienModel(thanhVienMode, uid);
    }
}
