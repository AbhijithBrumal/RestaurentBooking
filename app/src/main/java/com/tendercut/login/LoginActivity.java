package com.tendercut.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tendercut.MainActivity;
import com.tendercut.R;
import com.tendercut.utils.Utils;
import com.tendercut.widgets.UbuntuMedumTextView;
import com.tendercut.widgets.UbuntuRegularEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    @BindView(R.id.email_et)
    UbuntuRegularEditText emailEt;
    @BindView(R.id.password_et)
    UbuntuRegularEditText passwordEt;
    @BindView(R.id.remember_cb)
    CheckBox rememberCb;
    @BindView(R.id.forgot_tv)
    UbuntuMedumTextView forgotTv;
    @BindView(R.id.login_btn_tv)
    UbuntuMedumTextView loginBtnTv;
    @BindView(R.id.or_tv)
    UbuntuMedumTextView orTv;
    @BindView(R.id.or_rl)
    RelativeLayout orRl;
    @BindView(R.id.google_btn)
    Button googleBtn;
    @BindView(R.id.facebook_btn)
    Button facebookBtn;
    @BindView(R.id.social_ll)
    LinearLayout socialLl;
    @BindView(R.id.create_acc_tv)
    UbuntuMedumTextView createAccTv;
    @BindView(R.id.sign_up)
    UbuntuMedumTextView signUp;
    @BindView(R.id.signup_ll)
    LinearLayout signupLl;
    @BindView(R.id.activity_login)
    RelativeLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/ubuntu_m.ttf");
        rememberCb.setTypeface(font);

        loginBtnTv.setOnClickListener(this);
    }

    //ZCode to hide the soft key board

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight()
                    || y < w.getTop() || y > w.getBottom())) {
                Utils.hideSoftKeyboard(LoginActivity.this);
            }
        }
        return ret;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.login_btn_tv:
                Intent directHome = new Intent(this, MainActivity.class);
                startActivity(directHome);
                break;
        }
    }
}
