package d.wangyi.song.wnagyi_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;

import d.wangyi.song.wnagyi_demo.bean.XFBean;

/**
 * data:2017/8/16 0016.
 * Created by ：宋海防  song on
 */
public class Xunfei extends Activity{

    private Button anniu;
    private EditText et;
    private StringBuilder mStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xunfei);
//        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=597dc704");

        et = (EditText) findViewById(R.id.et);
        anniu = (Button) findViewById(R.id.anniu);

        anniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.创建RecognizerDialog对象,第二参数就是一个初始化的监听,我们用不上就设置为null
                RecognizerDialog mDialog = new RecognizerDialog(Xunfei.this,null);
                //2.设置accent、language等参数
                mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//设置为中文模式
                mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");//设置为普通话模式
                //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
                //mDialog.setParameter("asr_sch", "1");
                //mDialog.setParameter("nlp_version", "2.0");
                //创建一个装每次解析数据的容器(你说话有停顿,解析就是一段一段的,而用容器就能拼接成一句话了)
                mStringBuilder = new StringBuilder();
                //3.设置回调接口,语音识别后,得到数据,做响应的处理.
                mDialog.setListener(new RecognizerDialogListener() {
                    //识别成功执行 参数1 recognizerResult:识别出的结果,Json格式(用户可参见附录12.1)
                    // 参数2 b:等于true时会话结束。方法才不会继续回调
                    //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加,(关于解析Json的代码可参见MscDemo中JsonParser类)
                    public void onResult(RecognizerResult recognizerResult, boolean b) {
                        //拿到讯飞识别的结果
                        String resultString = recognizerResult.getResultString();
                        System.out.println("讯飞识别的结果 "+resultString);
                        //自定义解析bean数据的一个方法.用到了Gson,在项目里关联一下.
                        String text = parseData(resultString);
                        //容器装解析出来的数据
                        mStringBuilder.append(text);
                        //对参数2进行判断,如果为true,代表这个方法不会再回调,就把容器里的数据转成字符串.拿来使用.
                        if(b){
                            String result = mStringBuilder.toString();
                            et.setText(result);
                            String anwser = result;
                            if(result.contains("你好")){
                                anwser="好你大爷";
                            }else if(result.contains("我是好学生")){
                                String [] anwserList=new String[]{"好你大爷","我先表面上说是是是,其实我们都知道你是个傻逼","我先表,算了,表你大爷","哦"};
                                int random = (int)(Math.random() * anwserList.length);
                                anwser=anwserList[random];
                            }
                            else if(result.contains("你走吧")){
                                finish();
                            }
                            else if (result.contains("喜欢你")){
                                anwser="哦,那就巧了";
                            }
                            else if(result.contains("美女")){
                                String [] anwserList=new String[]{"你才是美女","你是坏人不和你玩","小助手很纯洁,不要说我听不懂的话","小助手我就是美女","500元,小助手帮主人找美女一起打英雄联盟"};
                                int random = (int)(Math.random() * anwserList.length);
                                anwser=anwserList[random];
                            }
                            shuo(anwser);
                        }
                    }
                    @Override//识别失败执行 speechError:错误码
                    public void onError(SpeechError speechError) {
                    }
                });
                //4.显示dialog，接收语音输入
                mDialog.show();
            }
        });

    }

    public void Talk(View view) {
        shuo("你很帅");
    }

    public void shuo(String result){
        //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer(this, null);
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        // 设置发音人（更多在线发音人，用户可参见 附录13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端,这些功能用到了讯飞服务器,所以要有网络
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式，如果不需要保存合成音频，注释该行代码
        // mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        // 3.开始合成,第一个参数就是转换成声音的文字,自定义,第二个参数就是合成监听器对象,我们不需要对声音有什么特殊处理,就传null
        mTts.startSpeaking(result, null);
    }

    private String parseData(String resultString) {
        //创建gson对象,记得要关联一下gson.jar包方可以使用.
        Gson gson = new Gson();
        //参数 1.String类型的json数据  参数 2.存放json数据对应的bean类
        XFBean xfBean = gson.fromJson(resultString, XFBean.class);
        //创建一个集合,用来存放bean类里的对象.
        ArrayList<XFBean.WS> ws = xfBean.ws;
        //创建一个容器,用来存放从每个集合里拿到的数据,使用StringBuilder效率更高
        StringBuilder stringBuilder = new StringBuilder();
        //使用高级for循环,取出特定属性的特有数据,装入StringBuilder中
        for ( XFBean.WS w: ws) {
            String text = w.cw.get(0).w;
            stringBuilder.append(text);
        }
        //把容器内的数据转为字符串返回出去.
        return stringBuilder.toString();
    }
}