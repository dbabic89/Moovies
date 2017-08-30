package com.example.android.moovies.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.moovies.R;

public class WebViewActivity extends Activity {

    String token;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        token = getIntent().getExtras().getString("token");

        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("https://www.themoviedb.org/authenticate/" + token);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (url.contains("/allow")){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.putExtra("token", token);
                            setResult(11, intent);
                            finish();
                        }
                    }, 1000);


                }
            }
        });
    }
}
