package com.tendercut.prelogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tendercut.R;
import com.tendercut.customview.MyEditText;
import com.tendercut.customview.MyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationSelector extends AppCompatActivity {

    @BindView(R.id.toolbarText)
    MyTextView toolbarText;
    @BindView(R.id.locationET)
    MyEditText locationET;
    @BindView(R.id.locationIV)
    ImageView locationIV;
    @BindView(R.id.closeIV)
    ImageView closeIV;
    @BindView(R.id.searchRL)
    RelativeLayout searchRL;
    @BindView(R.id.currentLocationTV)
    MyTextView currentLocationTV;
    @BindView(R.id.currentLocationRL)
    RelativeLayout currentLocationRL;
    @BindView(R.id.locationListView)
    ListView locationListView;
    @BindView(R.id.oops_no_store)
    ImageView oopsNoStore;
    @BindView(R.id.truck_placeholder)
    ImageView truckPlaceholder;
    @BindView(R.id.footerView)
    RelativeLayout footerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selector);
        ButterKnife.bind(this);

        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        toolbarText.setText("Select Your Area");
    }
}
