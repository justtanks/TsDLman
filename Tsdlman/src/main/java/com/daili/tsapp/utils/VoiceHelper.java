package com.daili.tsapp.utils;
import android.content.Context;
import android.os.Environment;

import com.baidu.android.common.logging.Log;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;
import com.daili.tsapp.tsBase.BaseData;
import com.daili.tsapp.tsBase.BaseSpeechSyListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by Administrator on 2017/1/20.
 * 百度语音播放合成封装类
 */

public class VoiceHelper {
    Context context;

    public VoiceHelper(Context context) {
        this.context = context;
        init();
    }

    private SpeechSynthesizer mSpeechSynthesizer;
    private String mSampleDirPath;
    private void init() {
        initialEnv();
        startTTS();
    }

    public void release() {
        this.mSpeechSynthesizer.release();
    }

    public void speek(String text) {
        this.mSpeechSynthesizer.speak(text);

    }

    private void initialEnv() {
        if (mSampleDirPath == null) {
            String sdcardPath = Environment.getExternalStorageDirectory().toString();
            mSampleDirPath = sdcardPath + "/" + BaseSpeechSyListener.SAMPLE_DIR_NAME;
        }

        //需要将这段代码执行一次，也就是程序刚开始使用的时候
//        File file = new File(mSampleDirPath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        copyFromAssetsToSdcard(false, BaseSpeechSyListener.SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/" + BaseSpeechSyListener.SPEECH_FEMALE_MODEL_NAME);
//        copyFromAssetsToSdcard(false, BaseSpeechSyListener.SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/" + BaseSpeechSyListener.SPEECH_MALE_MODEL_NAME);
//        copyFromAssetsToSdcard(false, BaseSpeechSyListener.TEXT_MODEL_NAME, mSampleDirPath + "/" + BaseSpeechSyListener.TEXT_MODEL_NAME);
//        copyFromAssetsToSdcard(false, BaseSpeechSyListener.LICENSE_FILE_NAME, mSampleDirPath + "/" + BaseSpeechSyListener.LICENSE_FILE_NAME);
//        copyFromAssetsToSdcard(false, "english/" + BaseSpeechSyListener.ENGLISH_SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/"
//                + BaseSpeechSyListener.ENGLISH_SPEECH_FEMALE_MODEL_NAME);
//        copyFromAssetsToSdcard(false, "english/" + BaseSpeechSyListener.ENGLISH_SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/"
//                + BaseSpeechSyListener.ENGLISH_SPEECH_MALE_MODEL_NAME);
//        copyFromAssetsToSdcard(false, "english/" + BaseSpeechSyListener.ENGLISH_TEXT_MODEL_NAME, mSampleDirPath + "/"
//                + BaseSpeechSyListener.ENGLISH_TEXT_MODEL_NAME);
    }

    //将资源文件复制到sd卡
    public void copyFromAssetsToSdcard(boolean isCover, String source, String dest) {
        File file = new File(dest);
        if (isCover || (!isCover && !file.exists())) {
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                is = context.getResources().getAssets().open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startTTS() {
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(context);
        mSpeechSynthesizer.setSpeechSynthesizerListener(new BaseSpeechSyListener() {
            @Override
            public void onError(String s, SpeechError speechError) {

            }
        });
        mSpeechSynthesizer.setApiKey(BaseData.APIKEY, BaseData.SECRECTKEY);
        mSpeechSynthesizer.setAppId(BaseData.YUYINAPPID);
        // 设置语音合成文本模型文件
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mSampleDirPath + "/"
                + BaseSpeechSyListener.TEXT_MODEL_NAME);
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mSampleDirPath + "/"
                + BaseSpeechSyListener.SPEECH_FEMALE_MODEL_NAME);
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "8");  //设置音量
        // 设置Mix模式的合成策略
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        AuthInfo authInfo = this.mSpeechSynthesizer.auth(TtsMode.MIX);
        if (authInfo.isSuccess()) {

        } else {
            String errorMsg = authInfo.getTtsError().getDetailMessage();
            Log.i("yuyin", ">>>auth failed errorMsg: " + errorMsg);
        }

        mSpeechSynthesizer.initTts(TtsMode.MIX);
        // 加载离线英文资源（提供离线英文合成功能）
        int result =
                mSpeechSynthesizer.loadEnglishModel(mSampleDirPath + "/" + BaseSpeechSyListener.ENGLISH_TEXT_MODEL_NAME, mSampleDirPath
                        + "/" + BaseSpeechSyListener.ENGLISH_SPEECH_FEMALE_MODEL_NAME);
    }

}
