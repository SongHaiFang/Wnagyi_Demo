package d.wangyi.song.wnagyi_demo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * data:2017/8/14 0014.
 * Created by ：宋海防  song on
 */

public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String mes){
        if (toast== null){
            toast = Toast.makeText(context,"", Toast.LENGTH_SHORT);
        }
        toast.setText(mes);
        toast.show();
    }

}
