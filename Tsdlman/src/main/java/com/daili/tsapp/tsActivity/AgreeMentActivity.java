package com.daili.tsapp.tsActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.daili.tsapp.R;
import com.daili.tsapp.databinding.AgreementBinding;
import com.daili.tsapp.jsView.BaseData1;
import com.daili.tsapp.tsBase.BaseActivity;

/**
 * 展示同意条约的activity
 */
public class AgreeMentActivity extends BaseActivity implements View.OnClickListener{

    AgreementBinding b;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_agree_ment);
        b= DataBindingUtil.setContentView(this, R.layout.activity_agree_ment);
        b.agreeReback.setOnClickListener(this);
        initWebview();
    }
    void  initWebview(){
        webView = new WebView(getApplication());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(params);
        b.activityAgreeMent.addView(webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(false);
        webView.loadUrl(BaseData1.XIEYI);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.agree_reback:
                onBackPressed();
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        b.activityAgreeMent.removeView(webView);
        webView.stopLoading();
        webView.removeAllViews();
        webView.destroy();
        webView = null;
        b=null;
    }

}
