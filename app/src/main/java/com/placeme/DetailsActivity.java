package com.placeme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String title = (String) getIntent().getExtras().get("title");
        String content = (String) getIntent().getExtras().get("content");

        //Log.d("DEBUG", "title:\t" + title);
        //Log.d("DEBUG", "content:\t\t" + content);

        TextView titleTV = (TextView) findViewById(R.id.detailsTitle);
        WebView webView = (WebView) findViewById(R.id.detailsWebView);

        titleTV.setText(title);
        webView.loadData(content, "text/html", "UTF-8");
    }
}
