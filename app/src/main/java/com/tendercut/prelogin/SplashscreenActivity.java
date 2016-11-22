package com.tendercut.prelogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tendercut.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashscreenActivity extends AppCompatActivity {


    @BindView(R.id.logoIV)
    ImageView logoIV;
    @BindView(R.id.gifIV)
    ImageView gifIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.splash).into(gifIV);
    }
}
