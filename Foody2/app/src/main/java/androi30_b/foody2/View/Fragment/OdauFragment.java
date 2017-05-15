package androi30_b.foody2.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import androi30_b.foody2.Controller.OdauController;
import androi30_b.foody2.Model.QuanAnModel;
import androi30_b.foody2.R;

/**
 * Created by Dell on 5/10/2017.
 */

public class OdauFragment extends Fragment {
    RecyclerView recyclerOdau;
    OdauController odauController;
    ProgressBar progressBarOdau;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau, container, false);
        recyclerOdau = (RecyclerView) view.findViewById(R.id.recyclerOdau);
        progressBarOdau = (ProgressBar) view.findViewById(R.id.progresBarOdau);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        odauController = new OdauController(getContext());
        odauController.getDanhSachQuanAnController(recyclerOdau, progressBarOdau);
    }
}
