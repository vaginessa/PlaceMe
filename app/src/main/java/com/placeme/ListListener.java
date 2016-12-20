package com.placeme;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.List;

public class ListListener implements OnItemClickListener {
    List<Item> listItem;
    Activity activity;

    public ListListener(List<Item> listItem, Activity activity) {
        this.listItem = listItem;
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.setData(Uri.parse(listItem.get(i).getContent()));
        intent.putExtra("title", listItem.get(i).getTitle());
        intent.putExtra("content", listItem.get(i).getContent());
        activity.startActivity(intent);
    }
}
