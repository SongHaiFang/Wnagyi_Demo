package d.wangyi.song.wnagyi_demo.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.sn.xlistviewlibrary.XListView;
import com.youth.banner.Banner;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import d.wangyi.song.wnagyi_demo.R;
import d.wangyi.song.wnagyi_demo.WebAct;
import d.wangyi.song.wnagyi_demo.bean.DataBean1;

/**
 * data:2017/8/8 0008.
 * Created by ：宋海防  song on
 */

public class Fragment02 extends Fragment {

    private XListView listView;
    String path = "http://v.juhe.cn/toutiao/index?type=top&key=e76b62dbe5ce78645516fe866dc7058b";
    private String s;
    private List<DataBean1.ResultBean.DataBean> data;
    private View view;
    private List<String> lists;
    private Banner banner22;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment02,container,false);

        listView = (XListView) view.findViewById(R.id.list_002);

        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    if(connection.getResponseCode() == 200){
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        while((len=inputStream.read(buffer))!=-1){
                            baos.write(buffer,0,len);
                        }
                        s = baos.toString();
                        return s;

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                DataBean1 newsBean = gson.fromJson(s, DataBean1.class);
                data = newsBean.getResult().getData();
                Fragment02.myAdapter adapter = new Fragment02.myAdapter();
                listView.setAdapter(adapter);

                lists = new ArrayList<>();
                for (int i = 5; i < 10; i++) {
                    lists.add(data.get(i).getThumbnail_pic_s());
                }
                View view = View.inflate(getActivity(),R.layout.banner_first,null);
                banner22 = (Banner) view.findViewById(R.id.banner1);
                banner22.setImageLoader(new imageLoaderBanner());
                banner22.setImages(lists);
                banner22.start();
                listView.addHeaderView(view);
            }
        };
        asyncTask.execute();

        return view;
    }
    class myAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(getActivity(), R.layout.list_item1, null);
            TextView textView = (TextView) view.findViewById(R.id.text_item);
            ImageView img = (ImageView) view.findViewById(R.id.img1_f1);

            TextView date_item = (TextView) view.findViewById(R.id.date_item);
            TextView follow = (TextView) view.findViewById(R.id.follow_item);

            date_item.setText(data.get(i).getDate());
            follow.setText(data.get(i).getAuthor_name());

            textView.setText(data.get(i).getTitle());
            loderImage(data.get(i).getThumbnail_pic_s(),img);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getContext(), WebAct.class);
                    intent.putExtra("path",data.get(i-2).getUrl());
                    startActivity(intent);

                }
            });

            return view;
        }
    }

    public  void loderImage(String url,ImageView imageView){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(getActivity());

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
