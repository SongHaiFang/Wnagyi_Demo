package d.wangyi.song.wnagyi_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sn.xlistviewlibrary.XListView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XListView xlistView = (XListView) findViewById(R.id.list_001);

        Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, MyAct.class);
                        startActivity(intent);
                        finish();
                    }

                };
                timer.schedule(task,500);

    }


}
