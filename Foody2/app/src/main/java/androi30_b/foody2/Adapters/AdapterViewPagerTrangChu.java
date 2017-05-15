package androi30_b.foody2.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import androi30_b.foody2.View.Fragment.AnGiFragment;
import androi30_b.foody2.View.Fragment.OdauFragment;

/**
 * Created by Dell on 5/10/2017.
 */

public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {
    OdauFragment oDauFragment;
    AnGiFragment anGiFragment;
    public AdapterViewPagerTrangChu(FragmentManager fm) {
        super(fm);
        anGiFragment = new AnGiFragment();
        oDauFragment = new OdauFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return oDauFragment;
            case 1:
                return anGiFragment;

            default:return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
