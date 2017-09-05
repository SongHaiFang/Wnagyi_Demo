package d.wangyi.song.wnagyi_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.share.QQOauthUtils;

import java.util.ArrayList;
import java.util.List;

import d.wangyi.song.wnagyi_demo.Fragment.Fragment_bot01;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment_bot02;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment_bot03;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment_bot04;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment_bot05;
import d.wangyi.song.wnagyi_demo.utils.MyFragmentAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class RighttFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView imageView;
    private QQOauthUtils qqOauthUtils;
    private TextView username;
    private ImageView image_actionbar;

    public RighttFragment() {
        // Required empty public constructor

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rightt, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.right_bot);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_bot);
        imageView = (ImageView) view.findViewById(R.id.user_right);
        username = (TextView) view.findViewById(R.id.username);
        image_actionbar =(ImageView)  view.findViewById(R.id.image_actionbar);
        initViewpager();


        return view;
    }
    private void initViewpager() {
        List<Fragment> list = new ArrayList<>();
        list.add(new Fragment_bot01());
        list.add(new Fragment_bot02());
        list.add(new Fragment_bot03());
        list.add(new Fragment_bot04());
        list.add(new Fragment_bot05());
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getFragmentManager());
        myFragmentAdapter.setFragment(list);

        viewPager.setAdapter(myFragmentAdapter);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("上头条");
        tabLayout.getTabAt(0).setIcon(R.drawable.toutiao);
        tabLayout.getTabAt(1).setText("消息");
        tabLayout.getTabAt(1).setIcon(R.drawable.xinxi);
        tabLayout.getTabAt(2).setText("邮件");
        tabLayout.getTabAt(2).setIcon(R.drawable.email);
        tabLayout.getTabAt(3).setText("公益");
        tabLayout.getTabAt(3).setIcon(R.drawable.gongyi);
        tabLayout.getTabAt(4).setText("彩票");
        tabLayout.getTabAt(4).setIcon(R.drawable.caipiao);

    }


}
