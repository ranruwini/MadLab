package com.example.mtrsliit.siyenra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HallBookingModel> hallBookingModelArrayList;

    public CustomAdapter(Context context, ArrayList<HallBookingModel> hallBookingModelArrayList) {

        this.context = context;
        this.hallBookingModelArrayList = hallBookingModelArrayList;
    }


    @Override
    public int getCount() {
        return hallBookingModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return hallBookingModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.hall = (TextView) convertView.findViewById(R.id.txt1);
            holder.checkin = (TextView) convertView.findViewById(R.id.checkinDate);
            holder.checkout = (TextView) convertView.findViewById(R.id.checkoutDate);
            holder.time = (TextView) convertView.findViewById(R.id.time);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.hall.setText("Hall Type : "+hallBookingModelArrayList.get(position).getHall());
        holder.checkin.setText("Check in Date: "+hallBookingModelArrayList.get(position).getCheckindate());
        holder.checkout.setText("Check out Date: "+hallBookingModelArrayList.get(position).getCheckoutdate());
        holder.time.setText("Time : "+hallBookingModelArrayList.get(position).getTime());

        return convertView;
    }

    private class ViewHolder {

        protected TextView  checkin,checkout,time,hall;
        //protected Spinner halltype;

    }

}