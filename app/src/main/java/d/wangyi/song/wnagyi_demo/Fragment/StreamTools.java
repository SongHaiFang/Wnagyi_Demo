package d.wangyi.song.wnagyi_demo.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * data:2017/8/9 0009.
 * Created by ：宋海防  song on
 */
public class StreamTools {
    public static String readFromFile(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len =is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            return baos.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
