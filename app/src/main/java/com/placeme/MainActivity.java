package com.placeme;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private final String urlLoc = "http://sxcpc.blogspot.com/feeds/posts/default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        setTitle(R.string.app_name);

        final SwipeRefreshLayout mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetData().execute(urlLoc);
                mSwipe.setRefreshing(false);
            }
        });
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        new GetData().execute(urlLoc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText(getResources().getString(R.string.app_name))
                        .setContentText(getResources().getString(R.string.abt_content))
                        .setCustomImage(R.mipmap.ic_launcher)
                        .show();
                break;
        }
        return true;
    }

    private class GetData extends AsyncTask<String, Void, List<Item>> {
        SweetAlertDialog sProgress = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);

        @Override
        protected void onPreExecute() {
            sProgress.getProgressHelper().setBarColor(Color.parseColor("#80DEEA"));
            sProgress.getProgressHelper().setSpinSpeed((float) 2);
            sProgress.setTitleText("Loading");
            sProgress.setCancelable(false);
            sProgress.show();
            super.onPreExecute();
        }

        @Override
        protected List<Item> doInBackground(String... urls) {
            try {
                DataReader dataReader = new DataReader(urls[0]);
                return dataReader.getItems();
            } catch (Exception e) {
                Log.e("reader", e.getMessage());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            ListView mListView = (ListView) findViewById(R.id.lview);
            mListView.setAdapter(new ListAdapter(MainActivity.this, items));
            sProgress.dismiss();
        }
    }
}
