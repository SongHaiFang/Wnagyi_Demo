package d.wangyi.song.wnagyi_demo;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.xutils.x;

/**
 * data:2017/8/9 0009.
 * Created by ：宋海防  song on
 */

public class x_App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
