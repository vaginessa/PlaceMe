package com.placeme;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        LinearLayout mLayout = (LinearLayout) findViewById(R.id.activity_details);
        Window mWindow = this.getWindow();

        String title = (String) getIntent().getExtras().get("title");
        String content = (String) getIntent().getExtras().get("content");
        int color = (int) getIntent().getExtras().get("color");

        mLayout.setBackgroundColor(color);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            mWindow.setStatusBarColor(color);
        }

        TextView titleTV = (TextView) findViewById(R.id.detailsTitle);
        WebView webView = (WebView) findViewById(R.id.detailsWebView);

        titleTV.setText(title);
        webView.loadData(content, "text/html", "UTF-8");
    }
}
