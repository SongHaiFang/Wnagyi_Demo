package com.andy.share;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/7/7 11:48
 */
public class QQOauthUtils {
    private UMShareAPI umShareAPI;
    public interface  QQCallBack{
        void logsuccess();
        void getuserinfo(Map<String, String> map);
        void getUserName(String name);
        void getImagepath(String url);
    }

    public QQOauthUtils(){
        umShareAPI=App.getApp().getUMShareAPI();
    }

    public void qqLogin(final Activity activity, final QQCallBack qqCallBack){
        if(umShareAPI.isInstall(activity,SHARE_MEDIA.QQ)){
            umShareAPI.doOauthVerify(activity, SHARE_MEDIA.QQ, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                         if (i==0){
                             if (qqCallBack!=null){
                                 qqCallBack.logsuccess();
                                 umShareAPI.getPlatformInfo(activity,SHARE_MEDIA.QQ,this);
                             }
                         }else if(i==2){
                             if (qqCallBack!=null){
                                 qqCallBack.getImagepath(map.get("iconurl"));
                                 qqCallBack.getuserinfo(map);
                                 qqCallBack.getUserName(map.get("name"));
                             }
                         }
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {

                }
            });
            Toast.makeText(App.getApp(), "已经安装QQ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(App.getApp(), "没有安装QQ", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 此方法需要在activity中的onActivityResult调用一下
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        umShareAPI.onActivityResult(requestCode,resultCode,data);
    }
}
