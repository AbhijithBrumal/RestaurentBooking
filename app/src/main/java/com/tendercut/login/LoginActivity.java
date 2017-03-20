package com.tendercut.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tendercut.R;
import com.tendercut.customview.MyButton;
import com.tendercut.customview.MyCheckBox;
import com.tendercut.customview.MyEditText;
import com.tendercut.customview.MyTextView;
import com.tendercut.forgot_password.ForgotPasswordActivity;
import com.tendercut.home.HomeActivity;
import com.tendercut.register.RegisterActivity;
import com.tendercut.utils.LoadingDialog;
import com.tendercut.utils.Utils;
import com.tendercut.utils.db_flow.User;
import com.tendercut.utils.sharedPreferances.BookingPreferances;
import com.tendercut.utils.web_service.RetroClient;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener , LoginView{




    @Bind(R.id.email_et)
    MyEditText emailEt;
    @Bind(R.id.remember_cb)
    MyCheckBox rememberCb;
    @Bind(R.id.forgot_tv)
    MyTextView forgotTv;
    @Bind(R.id.login_btn_tv)
    MyButton loginBtnTv;
    @Bind(R.id.or_tv)
    MyTextView orTv;
    @Bind(R.id.or_rl)
    RelativeLayout orRl;
    @Bind(R.id.create_acc_tv)
    MyTextView createAccTv;
    @Bind(R.id.sign_up)
    MyTextView signUp;
    @Bind(R.id.signup_ll)
    LinearLayout signupLl;
    @Bind(R.id.password_et)
    MyEditText passwordEt;
    @Bind(R.id.activity_login)
    RelativeLayout activityLogin;

    private LoadingDialog mLoadingDialog;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mLoadingDialog = new LoadingDialog(this);
        mLoginPresenter = new LoginPresenterImpl(this);

        loginBtnTv.setOnClickListener(this);
        signupLl.setOnClickListener(this);
        rememberCb.setOnClickListener(this);
        forgotTv.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_tv:

                if (RetroClient.isNetworkAvailable(this)){
                    mLoginPresenter.userLogin(emailEt.getText().toString(), passwordEt.getText().toString());
                }
                break;
            case R.id.signup_ll:
                Intent signup = new Intent(this, RegisterActivity.class);
                startActivity(signup);
                break;

            case R.id.remember_cb:
                if (rememberCb.isChecked()){
                    BookingPreferances.setStatus("1");
                }else {
                    BookingPreferances.setStatus("");
                }
                break;
            case R.id.forgot_tv:
                Intent forgot = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgot);
        }
    }

    @Override
    public void showProgress(String msg) {
        mLoadingDialog.show(msg);
    }

    @Override
    public void hideProgress() {
        mLoadingDialog.cancel();

    }

    @Override
    public void loginSuccess(final String user_id, final String firstName, final String lastName,
                             final String userEmail, final String userMobile, final String registerDate,
                             final String userStatus) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                BookingPreferances.setStatus("1");
                BookingPreferances.setUserID(user_id);

                User mUser = new User();

                mUser.setUid(Long.parseLong(user_id));
                mUser.setFirstname(firstName);
                mUser.setLastName(lastName);
                mUser.setEmail(userEmail);
                mUser.setPhone(userMobile);
                mUser.setRegisterDate(registerDate);
                mUser.setUserStatus(userStatus);
                mUser.setGender("male");

                mUser.save();

                Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(home);
                finish();

            }
        });


    }

    @Override
    public void loginFailure(String message) {
        Utils.showSnackbar(this, message);
    }

    @Override
    public void emailEmptyError(final String msg) {
      runOnUiThread(new Runnable() {
          @Override
          public void run() {
              passwordEt.requestFocus();
              emailEt.setError(msg);
          }
      });

    }

    @Override
    public void passwordEmptyError(final String msg) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               passwordEt.requestFocus();
               passwordEt.setError(msg);
           }
       });
    }

    @Override
    public void passwordLengthError(final String msg) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               passwordEt.setError(msg);
               passwordEt.requestFocus();
           }
       });
    }

    @Override
    public void retrofiError(String msg) {
        Utils.showSnackbar(this, msg);
    }

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
}
