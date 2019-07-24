package com.example.aviorka.bstrong;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;


public class HistoryAdapter extends OrderListAdapter {
    public HistoryAdapter(Context ctx, FragmentActivity activity, Fragment instance) {
        super(ctx, activity, instance);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        final OrderItem orderItem = dataList.get(position);
        final OrderListRowView row = (OrderListRowView) convertView.getTag();
//        row.rowDish.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                ((HistoryFragment)instance).showWorkers(orderItem);
//            }
//        });
//        row.rowSit.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                ((HistoryFragment)instance).showWorkers(orderItem);
//            }
//        });
//        row.rowComments.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                ((HistoryFragment)instance).showWorkers(orderItem);
//            }
//        });
        row.isServed.setEnabled(false);
        row.isReady.setEnabled(false);
        return convertView;
    }
}
