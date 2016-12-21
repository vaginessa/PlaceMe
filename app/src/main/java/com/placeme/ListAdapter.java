package com.placeme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    List<Item> itemList;
    Context context;

    private static LayoutInflater inflater = null;

    public ListAdapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        final int mColor;

        rowView = inflater.inflate(R.layout.item, null);

        holder.tv = (TextView) rowView.findViewById(R.id.titleId);
        holder.img = (ImageView) rowView.findViewById(R.id.imgId);
        holder.tv.setText(itemList.get(position).getTitle());

        mColor = ColorGenerator.MATERIAL.getColor(itemList.get(position).getTitle());

        TextDrawable mTextD = TextDrawable.builder()
                .beginConfig()
                .bold()
                .endConfig()
                .buildRound(itemList.get(position).getTitle().substring(0, 1), mColor);
        holder.img.setImageDrawable(mTextD);

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.setData(Uri.parse(itemList.get(position).getContent()));
                intent.putExtra("title", itemList.get(position).getTitle());
                intent.putExtra("content", itemList.get(position).getContent());
                intent.putExtra("color", mColor);
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}