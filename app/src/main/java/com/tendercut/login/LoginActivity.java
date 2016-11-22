package com.tendercut.login;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.tendercut.R;
import com.tendercut.customview.MyEditText;
import com.tendercut.customview.MyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.appbar)
    Toolbar appbar;
    @BindView(R.id.email_et)
    MyEditText emailEt;
    @BindView(R.id.password_et)
    MyEditText passwordEt;
    @BindView(R.id.remember_cb)
    CheckBox rememberCb;
    @BindView(R.id.forgot_tv)
    MyTextView forgotTv;
    @BindView(R.id.login_btn_tv)
    MyTextView loginBtnTv;
    @BindView(R.id.or_tv)
    MyTextView orTv;
    @BindView(R.id.google_btn)
    Button googleBtn;
    @BindView(R.id.facebook_btn)
    Button facebookBtn;
    @BindView(R.id.create_acc_tv)
    MyTextView createAccTv;
    @BindView(R.id.sign_up)
    MyTextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);


    }




}
