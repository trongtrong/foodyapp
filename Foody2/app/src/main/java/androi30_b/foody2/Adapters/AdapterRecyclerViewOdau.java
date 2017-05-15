package androi30_b.foody2.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import androi30_b.foody2.Model.BinhLuanModel;
import androi30_b.foody2.Model.QuanAnModel;
import androi30_b.foody2.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dell on 5/11/2017.
 */

public class AdapterRecyclerViewOdau extends RecyclerView.Adapter<AdapterRecyclerViewOdau.ViewHolder> {
    List<QuanAnModel> quanAnModelList;
    int resource;
    public AdapterRecyclerViewOdau(List<QuanAnModel> quanAnModelList, int resource){
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnOdau, txtTieuDeBinhLuan, txtTieuDeBinhLuan2, txtNoiDungBinhLuan,
                txtNoiDungBinhLuan2, txtChamDiem, txtChamDiem2, txtTongBinhLuan, txtTongHinhBinhLuan, txtDiemTrungBinh;

        CircleImageView ciclerImageUser, ciclerImageUser2;
        Button btnDatMonOdau;
        ImageView imageHinhQuanAnOdau;
        LinearLayout containerBinhluan, containerBinhluan2;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenQuanAnOdau = (TextView) itemView.findViewById(R.id.txtTenQuanQuanOdau);
            btnDatMonOdau = (Button) itemView.findViewById(R.id.btnDatMonOdau);
            imageHinhQuanAnOdau = (ImageView) itemView.findViewById(R.id.imageHinhQuanAnOdau);
            txtTieuDeBinhLuan = (TextView) itemView.findViewById(R.id.txtTieuDeBinhLuan);
            txtTieuDeBinhLuan2 = (TextView) itemView.findViewById(R.id.txtTieuDeBinhLuan2);
            txtNoiDungBinhLuan = (TextView) itemView.findViewById(R.id.txtNoiDungBinhLuan);
            txtNoiDungBinhLuan2 = (TextView) itemView.findViewById(R.id.txtNoiDungBinhLuan2);
            ciclerImageUser = (CircleImageView) itemView.findViewById(R.id.cicleImageUser);
            ciclerImageUser2 = (CircleImageView) itemView.findViewById(R.id.cicleImageUser2);
            containerBinhluan = (LinearLayout) itemView.findViewById(R.id.containerBinhluan);
            containerBinhluan2 = (LinearLayout) itemView.findViewById(R.id.containerBinhluan2);
            txtChamDiem = (TextView) itemView.findViewById(R.id.txtChamDiem);
            txtChamDiem2 = (TextView) itemView.findViewById(R.id.txtChamDiem2);
            txtTongBinhLuan = (TextView) itemView.findViewById(R.id.txttongBinhLuan);
            txtTongHinhBinhLuan = (TextView) itemView.findViewById(R.id.txtTongHinhBinhLuan);
            txtDiemTrungBinh = (TextView) itemView.findViewById(R.id.txtDiemTrungBinh);

        }
    }
    @Override
    public AdapterRecyclerViewOdau.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdapterRecyclerViewOdau.ViewHolder holder, int position) {
        QuanAnModel quanAnModel = quanAnModelList.get(position);
        holder.txtTenQuanAnOdau.setText(quanAnModel.getTenquanan());
        if (quanAnModel.isGiaohang()){
            holder.btnDatMonOdau.setVisibility(View.VISIBLE);
        }
        if (quanAnModel.getHinhanhquanans().size() > 0){
            StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanhquanans().get(0));
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.imageHinhQuanAnOdau.setImageBitmap(bitmap);
                }
            });
        }
        //lay danh sach binh luan cua qyan an
        if (quanAnModel.getBinhLuanModelList().size() > 0 ){
            BinhLuanModel binhLuanModel = quanAnModel.getBinhLuanModelList().get(0);
            holder.txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
            holder.txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
            holder.txtChamDiem.setText(binhLuanModel.getChamdiem() + "");
            setHinhAnhBinhLuan(holder.ciclerImageUser, binhLuanModel.getThanhVienModel().getHinhanh());
            if (quanAnModel.getBinhLuanModelList().size() > 2){
                BinhLuanModel binhLuanModel2 = quanAnModel.getBinhLuanModelList().get(1);
                holder.txtTieuDeBinhLuan2.setText(binhLuanModel2.getTieude());
                holder.txtNoiDungBinhLuan2.setText(binhLuanModel2.getNoidung());
                holder.txtChamDiem2.setText(binhLuanModel2.getChamdiem() + "");
                setHinhAnhBinhLuan(holder.ciclerImageUser2, binhLuanModel2.getThanhVienModel().getHinhanh());
            }
            holder.txtTongBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");
            int tongHinh = 0;
            double tongDiem = 0;
            for (BinhLuanModel binhLuanModelTongHinhAnh : quanAnModel.getBinhLuanModelList()){
                tongHinh += binhLuanModelTongHinhAnh.getHinhAnhbinhLuanList().size();
                tongDiem += binhLuanModel.getChamdiem();
            }
            double diemTrungBinh = tongDiem / quanAnModel.getBinhLuanModelList().size();
            holder.txtDiemTrungBinh.setText(String.format("%.2f", diemTrungBinh ));
            if (tongHinh > 0) {
                holder.txtTongHinhBinhLuan.setText(tongHinh + "");
            }

        }else {

            holder.containerBinhluan.setVisibility(View.GONE);
            holder.containerBinhluan2.setVisibility(View.GONE);

        }
    }
    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkHinh){

        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkHinh);
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });

    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }


}