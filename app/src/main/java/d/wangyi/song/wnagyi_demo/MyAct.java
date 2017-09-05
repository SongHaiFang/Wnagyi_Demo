package d.wangyi.song.wnagyi_demo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.andy.share.QQOauthUtils;
import com.bumptech.glide.Glide;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.sharesdk.onekeyshare.OnekeyShare;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment01;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment02;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment03;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment04;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment05;
import d.wangyi.song.wnagyi_demo.Fragment.Fragment06;
import d.wangyi.song.wnagyi_demo.utils.MyFragmentAdapter;

import static com.umeng.socialize.utils.DeviceConfig.context;
import static d.wangyi.song.wnagyi_demo.R.id.image_actionbar;

/**
 * data:2017/8/8 0008.
 * Created by ：宋海防  song on
 */
public class MyAct extends AppCompatActivity{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle abdt;
    private MyFragmentAdapter myFragmentAdapter;
    private QQOauthUtils qqOauthUtils;
    private ImageView imageView;
    private ImageView action;
    private String imageurl;
    private int theme = R.style.AppTheme;
    private TextView username;
    private RadioGroup rg1,rg2,rg3,rg4,rg5;
    private ImageView pindao;

    private List<ChannelBean> list;
    private String jsonStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.myact);
        //讯飞
        // 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5994ea77");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        imageView = (ImageView) findViewById(R.id.user_right);
        action = (ImageView) findViewById(R.id.image_actionbar);
        username = (TextView) findViewById(R.id.username);
        pindao = (ImageView) findViewById(R.id.pindao);

        rg1 = (RadioGroup) findViewById(R.id.gradio_group);
        rg2 = (RadioGroup) findViewById(R.id.gradio_group2);
        rg3 = (RadioGroup) findViewById(R.id.gradio_group3);
        rg4 = (RadioGroup) findViewById(R.id.gradio_group4);
        rg5 = (RadioGroup) findViewById(R.id.gradio_group5);

        pindao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinD();
            }
        });

        qqOauthUtils = new QQOauthUtils();
        initActionBar();
        initViewpager();
        if (imageurl!=null){
            Glide.with(this).load(imageurl).into(action);
        }

        rg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAct.this, MyAct.class);
                startActivity(intent);
            }
        });
        rg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAct.this, Dingyuey.class);
                startActivity(intent);
            }
        });
        rg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                WebView web_rg3 = new WebView(MyAct.this);
//                web_rg3.getSettings().setJavaScriptEnabled(true);
                Intent intent3 = new Intent(MyAct.this, WebAct.class);
                intent3.putExtra("path","http://image.so.com/z?ch=funny");
                startActivity(intent3);
            }
        });

        String channel = getChannel();
        Toast.makeText(this, "渠道标识"+channel, Toast.LENGTH_SHORT).show();
    }

    private void pinD() {
        if (list==null){//判断集合中是否已有数据，没有则创建
            list=new ArrayList<>();
            //第一个是显示的条目，第二个参数是否显示
            list.add(new ChannelBean("热点",true));
            list.add(new ChannelBean("军事",true));
            list.add(new ChannelBean("八卦",true));
            list.add(new ChannelBean("游戏",true));
            list.add(new ChannelBean("宠物",true));
            list.add(new ChannelBean("汽车",false));
            list.add(new ChannelBean("热卖",false));
            list.add(new ChannelBean("外卖",false));
            list.add(new ChannelBean("太阳花",false));
            list.add(new ChannelBean("九三",false));
            list.add(new ChannelBean("八嘎",false));
            list.add(new ChannelBean("色昂",false));
            ChannelActivity.startChannelActivity(this,list);
        }else if (jsonStr!=null){//当判断保存的字符串不为空的时候，直接加载已经有了的字符串
            ChannelActivity.startChannelActivity(this,jsonStr);
        }
    }

    //夜间1
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme",theme);
    }
    //夜间2
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

    private String getChannel() {
        try {
            PackageManager pm = getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException ignored) {}
        return "";
    }

    private void initViewpager() {
        List<Fragment> list = new ArrayList<>();
        list.add(new Fragment01());
        list.add(new Fragment02());
        list.add(new Fragment03());
        list.add(new Fragment04());
        list.add(new Fragment05());
        list.add(new Fragment06());
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        myFragmentAdapter.setFragment(list);

        viewPager.setAdapter(myFragmentAdapter);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("1");
        tabLayout.getTabAt(1).setText("2");
        tabLayout.getTabAt(2).setText("3");
        tabLayout.getTabAt(3).setText("4");
        tabLayout.getTabAt(4).setText("5");
        tabLayout.getTabAt(5).setText("6");

    }



    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        abdt = new ActionBarDrawerToggle(this,drawerLayout, R.string.open,R.string.close);

        abdt.syncState();
        drawerLayout.addDrawerListener(abdt);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (abdt.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case image_actionbar:
                actionBarOpenClose();

                break;
            case  R.id.action_tianqi:
                Intent intent2 = new Intent(MyAct.this, WeaTher.class);
                startActivity(intent2);

                break;
            case  R.id.action_lixian:

                break;
            case  R.id.action_yejian:
                theme = (theme==R.style.AppTheme)?R.style.NightAppTheme:R.style.AppTheme;
                recreate();
                break;
            case  R.id.action_sousuo:
                Intent intent = new Intent(MyAct.this, Xunfei.class);
                startActivity(intent);
                break;
            case  R.id.action_saoyisao:
                erweima();
                break;
            case  R.id.action_shezhi:
                fenXiang();

                break;

        }
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);//关闭抽屉
            return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    private void fenXiang() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

    private void erweima() {
        IntentIntegrator integrator = new IntentIntegrator(MyAct.this);
        // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setCaptureActivity(ErWeiMa.class);
        integrator.setPrompt("请扫描二维码"); //底部的提示文字，设为""可以置空
        integrator.setCameraId(0); //前置或者后置摄像头
        integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
        integrator.setBarcodeImageEnabled(true);//是否保留扫码成功时候的截图
        integrator.initiateScan();
    }

    private void actionBarOpenClose() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);//关闭抽屉
        } else {
            drawerLayout.openDrawer(GravityCompat.END);

        }

        qqOauthUtils = new QQOauthUtils();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qqOauthUtils = new QQOauthUtils();
                qqOauthUtils.qqLogin(MyAct.this, new QQOauthUtils.QQCallBack() {
                    @Override
                    public void logsuccess() {
                        Toast.makeText(MyAct.this,"登录成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void getuserinfo(Map<String, String> map) {

                    }

                    @Override
                    public void getUserName(String name) {
                                username.setText(name);
                    }

                    @Override
                    public void getImagepath(String url) {
                        imageurl = url.toString();
                        Glide.with(MyAct.this).load(url).into(imageView);
//
                    }
                });

            }

        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QQ第三方
        qqOauthUtils.onActivityResult(requestCode,resultCode,data);
        //二维码
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            Toast.makeText(MyAct.this, result, Toast.LENGTH_LONG).show();
        }
        if (requestCode==ChannelActivity.REQUEST_CODE&&resultCode==ChannelActivity.RESULT_CODE) {
            jsonStr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
        }

    }

//    加载menu调用  onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //==========================================
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.main_menu,menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    public  void loderImage(String url,ImageView imageView){
            ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                    .createDefault(context);

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .showImageOnFail(R.mipmap.ic_launcher)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();

            ImageLoader instance = ImageLoader.getInstance();
            instance.init(configuration);
            instance.displayImage(url, imageView ,options);

            //compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'
        }
}