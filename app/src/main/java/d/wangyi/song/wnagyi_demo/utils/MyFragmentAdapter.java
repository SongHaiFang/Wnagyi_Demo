package d.wangyi.song.wnagyi_demo.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * data:2017/8/3 0003.
 * Created by ：宋海防  song on
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    private List<Fragment> lists;



    public void setFragment(List<Fragment> list){
        lists = list;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = lists.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}