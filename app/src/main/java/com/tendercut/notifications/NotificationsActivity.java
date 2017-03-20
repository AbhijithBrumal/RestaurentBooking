package com.tendercut.notifications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.tendercut.R;
import com.tendercut.customview.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {


    @Bind(R.id.toolbarText)
    MyTextView toolbarText;
    @Bind(R.id.notifications_rv)
    RecyclerView notificationsRv;
    @Bind(R.id.activity_notifications)
    RelativeLayout activityNotifications;
    private NotificationsAdapter mNotificationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);


        notificationsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mNotificationsAdapter = new NotificationsAdapter(this);
        notificationsRv.setAdapter(mNotificationsAdapter);

        toolbarText.setText(R.string.notifications);
    }
}
