package androi30_b.foody2.Model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androi30_b.foody2.Controller.Interfaces.OdauInterface;

/**
 * Created by Dell on 5/11/2017.
 */

public class QuanAnModel {
    boolean giaohang;
    String giodongcua, giomocua, tenquanan, videogioithieu, maquanan;
    List<String> tienich,hinhanhquanans;
    List<BinhLuanModel> binhLuanModelList;
    private DatabaseReference nodeRoot;
    long luothich;


    public QuanAnModel(){
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public List<String> getHinhanhquanans() {
        return hinhanhquanans;
    }

    public List<BinhLuanModel> getBinhLuanModelList() {
        return binhLuanModelList;
    }

    public void setBinhLuanModelList(List<BinhLuanModel> binhLuanModelList) {
        this.binhLuanModelList = binhLuanModelList;
    }

    public void setHinhanhquanans(List<String> hinhanhquanans) {
        this.hinhanhquanans = hinhanhquanans;
    }

    public long getLuothich() {
        return luothich;
    }

    public void setLuothich(long luothich) {
        this.luothich = luothich;
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }
    public void getDanhSachQuanAn(final OdauInterface odauInterface){
       // final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshotQuanAn = dataSnapshot.child("quanans");
                //lay danh sach quan an
                for (DataSnapshot valueQuanAn: dataSnapshotQuanAn.getChildren()){
                    QuanAnModel quanAnModel = valueQuanAn.getValue(QuanAnModel.class);
                    quanAnModel.setMaquanan(valueQuanAn.getKey());
                    //lay danh sach hinh anh cua quan an theo ma
                    DataSnapshot dataSnapshotHinhQuanAn = dataSnapshot.child("hinhanhquanans").child(valueQuanAn.getKey());

                    List<String> hinhAnhList = new ArrayList<>();

                    for (DataSnapshot valueHinhQuanAn: dataSnapshotHinhQuanAn.getChildren()){
                        hinhAnhList.add(valueHinhQuanAn.getValue(String.class));

                    }
                    quanAnModel.setHinhanhquanans(hinhAnhList);

                    //lay danh sach binh luat cua quan an theo ma key
                    DataSnapshot snapshotBinhLuan = dataSnapshot.child("binhluans").child(quanAnModel.getMaquanan());
                    List<BinhLuanModel> binhLuanModels = new ArrayList<>();

                    for (DataSnapshot valueBinhLuan : snapshotBinhLuan.getChildren()){
                        BinhLuanModel binhLuanModel = valueBinhLuan.getValue(BinhLuanModel.class);
                        binhLuanModel.setMaBinhLuan(valueBinhLuan.getKey());
                        ThanhVienModel thanhVienModel = dataSnapshot.child("thanhviens").child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                        binhLuanModel.setThanhVienModel(thanhVienModel);

                        List<String> hinhanhBinhLuanList = new ArrayList<>();
                        DataSnapshot snapshoNodetHinhAnhBL = dataSnapshot.child("hinhanhbinhluans").child(binhLuanModel.getMaBinhLuan());
                        for (DataSnapshot valueHinhBinhLuan: snapshoNodetHinhAnhBL.getChildren()){
                            hinhanhBinhLuanList.add(valueHinhBinhLuan.getValue(String.class));
                        }

                        binhLuanModel.setHinhAnhbinhLuanList(hinhanhBinhLuanList);
                        binhLuanModels.add(binhLuanModel);
                    }
                    quanAnModel.setBinhLuanModelList(binhLuanModels);

                    //quanAnModelList.add(quanAnModel);
                    odauInterface.getDanhSachQuanAn(quanAnModel);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }
}
