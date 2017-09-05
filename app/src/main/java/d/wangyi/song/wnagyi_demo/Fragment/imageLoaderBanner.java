package d.wangyi.song.wnagyi_demo.Fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

/**
 * data:2017/8/14 0014.
 * Created by ：宋海防  song on
 */
public class imageLoaderBanner extends  ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);


    }

    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        return simpleDraweeView;


    }
}
