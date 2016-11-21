package com.tendercut.prelogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tendercut.R;
import com.tendercut.customview.MyEditText;
import com.tendercut.customview.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocationSelector extends AppCompatActivity {

    @Bind(R.id.toolbarText)
    MyTextView toolbarText;
    @Bind(R.id.locationET)
    MyEditText locationET;
    @Bind(R.id.locationIV)
    ImageView locationIV;
    @Bind(R.id.footerView)
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
