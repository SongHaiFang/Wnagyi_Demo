package d.wangyi.song.wnagyi_demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static android.R.attr.action;
import static android.R.attr.text;
import static android.R.id.text1;

/**
 * data:2017/8/17 0017.
 * Created by ：宋海防  song on
 */
public class WeaTher extends Activity{

    private WebView webView;
    private TextView text11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        webView = (WebView)findViewById(R.id.web_wea);
        text11 = (TextView) findViewById(R.id.action_tianqi);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("http://www.weather.com.cn/weather/101181201.shtml");

        ccc();
    }
    public void ccc(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //从一个URL加载一个Document对象。
                    Document doc = Jsoup.connect("http://www.weather.com.cn/weather/101181201.shtml").get();
                    //选择“美食天下”所在节点
//                    <p title="雷阵雨转多云" class="wea">雷阵雨转多云</p>
//                    <p class="tem">
                    Elements elements = doc.select("div.slid").select("p");
                    for (int x = 0;x<elements.size();x++){
                        //打印 <a>标签里面的title
                        Log.i("mytag",elements.select("p").attr("title"));
                    }
                    String attr = elements.select("a").attr("title");
                    text11.setText(attr);
                    //gif
//                    Elements picGif = doc.select("div.pic");
//                    for (int x = 0;x<picGif.size();x++){
//                        Log.i("mytag",picGif.select("img").attr("src"));



//                    Toast.makeText(MainActivity.this,attr,Toast.LENGTH_SHORT).show();


                }catch(Exception e) {
                    Log.i("mytag", e.toString());
                }
            }
        }).start();
    }
}
