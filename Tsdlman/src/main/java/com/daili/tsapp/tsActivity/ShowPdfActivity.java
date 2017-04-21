package com.daili.tsapp.tsActivity;

import android.databinding.DataBindingUtil;
import android.graphics.Canvas;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.daili.tsapp.R;
import com.daili.tsapp.databinding.ActivityShowPdfBinding;
import com.daili.tsapp.tsBase.BaseActivity;
import com.daili.tsapp.tsBase.BaseData;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//展示pdf
public class ShowPdfActivity extends BaseActivity implements View.OnClickListener {
    ActivityShowPdfBinding b;
    String pdfurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_show_pdf);
        pdfurl = getIntent().getStringExtra("pdf");
        b.pdfReback.setOnClickListener(this);
        getStreanFromNet(pdfurl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pdf_reback:
                onBackPressed();
                break;
        }
    }
    private  void showPdf(InputStream stream){
        b. pdfView.fromStream(stream).enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                })
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                    }
                })
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {

                    }
                })
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {

                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(getApplicationContext(), "加载错误", Toast.LENGTH_SHORT).show();
                    }
                })
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();
    }
    private  void getStreanFromNet(final String pdfurl){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(pdfurl);
                    HttpURLConnection connection = (HttpURLConnection)
                            url.openConnection();
                    connection.setRequestMethod("GET");//试过POST 可能报错
                    connection.setDoInput(true);
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);
                    //实现连接
                    connection.connect();

                    System.out.println("connection.getResponseCode()=" + connection.getResponseCode());
                    if (connection.getResponseCode() == 200) {
                        InputStream is = connection.getInputStream();
                        //这里给过去就行了
                        showPdf(is);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
