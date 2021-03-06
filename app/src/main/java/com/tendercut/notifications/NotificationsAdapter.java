package com.tendercut.notifications;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tendercut.R;
import com.tendercut.customview.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Abhijith on 21-11-2016.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationHolder> {



    private Context mContext;

    public NotificationsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        NotificationHolder holder = new NotificationHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class NotificationHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.notification_iv)
        ImageView notificationIv;
        @Bind(R.id.title_tv)
        MyTextView titleTv;
        @Bind(R.id.time_date_tv)
        MyTextView timeDateTv;
        @Bind(R.id.content_tv)
        MyTextView contentTv;

        public NotificationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
