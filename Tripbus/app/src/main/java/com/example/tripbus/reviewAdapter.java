package com.example.tripbus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tripbus.reviewItem;

import java.util.ArrayList;

/**
 * Created by user on 2018-08-16.
 */

public class reviewAdapter extends BaseAdapter {
    private Context mContext = null;
    private LayoutInflater inflater;
    private ArrayList<reviewItem> reviews = new ArrayList<reviewItem>();

    public reviewAdapter(Context context, ArrayList<reviewItem> rl) {
        super();
        this.mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.reviews = rl;
    }


    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return  reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder testViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.review_item, null);
            testViewHolder = new ViewHolder();
            testViewHolder.userId = (TextView) convertView.findViewById(R.id.userId);
            testViewHolder.reviewStar = (RatingBar) convertView.findViewById(R.id.userRating);
            testViewHolder.reviewText = (TextView) convertView.findViewById(R.id.userText);
            convertView.setTag(testViewHolder);
        } else {
            testViewHolder = (ViewHolder) convertView.getTag();
        }

        reviewItem a = (reviewItem) getItem(position);

        testViewHolder.userId.setText("" + a.getUserId());
        testViewHolder.reviewStar.setRating(a.getUserStar());
        testViewHolder.reviewText.setText("" + a.getUserText());
        return convertView;

    }

    private class ViewHolder {
        public TextView userId;
        public RatingBar reviewStar;
        public TextView reviewText;
    }
}