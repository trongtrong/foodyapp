package androi30_b.foody2.View;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androi30_b.foody2.Adapters.AdapterViewPagerTrangChu;
import androi30_b.foody2.R;

public class TrangChuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    ViewPager viewPager;
    RadioButton rdOdau, rdAngi;
    RadioGroup groupOdauAngi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        groupOdauAngi = (RadioGroup) findViewById(R.id.group_odau_angi);
        rdOdau = (RadioButton) findViewById(R.id.rd_odau);
        rdAngi = (RadioButton) findViewById(R.id.rd_angi);
        viewPager = (ViewPager) findViewById(R.id.viewpager_trangchu);

        AdapterViewPagerTrangChu adapterViewPagerTrangChu = new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPagerTrangChu);
        groupOdauAngi.setOnCheckedChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rdOdau.setChecked(true);
                break;
            case 1:
                rdAngi.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.rd_angi:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rd_odau:
                viewPager.setCurrentItem(0);
                break;
        }
    }
}
