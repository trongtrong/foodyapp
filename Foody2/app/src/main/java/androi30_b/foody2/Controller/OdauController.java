package androi30_b.foody2.Controller;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import androi30_b.foody2.Adapters.AdapterRecyclerViewOdau;
import androi30_b.foody2.Controller.Interfaces.OdauInterface;
import androi30_b.foody2.Model.QuanAnModel;
import androi30_b.foody2.R;

/**
 * Created by Dell on 5/11/2017.
 */

public class OdauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecyclerViewOdau adapterRecyclerViewOdau;
    public OdauController(Context context) {
        this.context = context;
        quanAnModel= new QuanAnModel();
    }
    public void getDanhSachQuanAnController(RecyclerView recyclerOdau, final ProgressBar progressBarOdau){
        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        adapterRecyclerViewOdau = new AdapterRecyclerViewOdau(quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerOdau.setAdapter(adapterRecyclerViewOdau);
        OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAn(QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                adapterRecyclerViewOdau.notifyDataSetChanged();
                progressBarOdau.setVisibility(View.GONE);
            }
        };
        quanAnModel.getDanhSachQuanAn(odauInterface);

    }
}
