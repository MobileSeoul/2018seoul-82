package com.example.tripbus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class pathAdapter extends BaseAdapter {

    private Context mContext = null;
    LayoutInflater inflater;
    Context context;
    int layout;
    ArrayList<bus_pathitem> paths = new ArrayList<bus_pathitem>();

    public pathAdapter(Context context, ArrayList<bus_pathitem> bPI) {
        super();
        this.mContext = context;
        this.layout = layout;
        this.paths = bPI;
        inflater = LayoutInflater.from(context);
        //inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public Object getItem(int position) {
        return  paths.get(position);
    }
    @Override
    public long getItemId(int position) { // 정류소이름
        return position;
    }
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder testViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_bus_pathitem, null);
            testViewHolder = new ViewHolder();
            testViewHolder.getImg = (TextView) convertView.findViewById(R.id.rcmnd_placeimg);
            testViewHolder.getTxt = (TextView) convertView.findViewById(R.id.rcmnd_placetxt);
            convertView.setTag(testViewHolder);
        } else {
            testViewHolder = (ViewHolder) convertView.getTag();
        }

        bus_pathitem a = (bus_pathitem) getItem(position);

        testViewHolder.getImg.setText("" + a.getTxt());
        testViewHolder.getTxt .setText("" + a.getImg());
        return convertView;

    }

    private class ViewHolder {
        public TextView getTxt;
        public TextView getImg;
    }
}






