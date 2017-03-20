package com.tendercut.change_password;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tendercut.R;
import com.tendercut.customview.MyButton;
import com.tendercut.customview.MyEditText;
import com.tendercut.utils.LoadingDialog;
import com.tendercut.utils.Utils;
import com.tendercut.utils.sharedPreferances.BookingPreferances;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ChangePasswordFragment extends Fragment implements ChangePasswordView, View.OnClickListener {


    @Bind(R.id.current_password_et)
    MyEditText currentPasswordEt;
    @Bind(R.id.new_password_et)
    MyEditText newPasswordEt;
    @Bind(R.id.confirm_password_et)
    MyEditText confirmPasswordEt;
    @Bind(R.id.done_btn)
    MyButton doneBtn;

    private LoadingDialog mLoadingDialog;
    private ChangePassowordPresenter mChangePasswordPresenter;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLoadingDialog = new LoadingDialog(getActivity());
        mChangePasswordPresenter = new ChangePasswordPresenterImpl(this);

        doneBtn.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
    public void changePasswordSuccess(String msg) {
        currentPasswordEt.setText("");
        newPasswordEt.setText("");
        confirmPasswordEt.setText("");
        currentPasswordEt.requestFocus();
        Utils.showSnackbar(getActivity(), msg);
    }

    @Override
    public void changePasswordFailure(String msg) {
        Utils.showSnackbar(getActivity(), msg);
    }

    @Override
    public void retrofitError(String msg) {
        Utils.showSnackbar(getActivity(), msg);
    }

    @Override
    public void currentPasswordError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                currentPasswordEt.setError(msg);
                currentPasswordEt.requestFocus();
            }
        });
    }

    @Override
    public void newPasswordError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newPasswordEt.setError(msg);
                newPasswordEt.requestFocus();
            }
        });
    }

    @Override
    public void confirmPasswordError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                confirmPasswordEt.setError(msg);
                confirmPasswordEt.requestFocus();
            }
        });
    }

    @Override
    public void passwordMissmatchError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newPasswordEt.setError(msg);
                newPasswordEt.setText("");
                confirmPasswordEt.setText("");
                newPasswordEt.requestFocus();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.done_btn:
                mChangePasswordPresenter.changePassword(BookingPreferances.getUserId(), currentPasswordEt.getText().toString(),
                        newPasswordEt.getText().toString(), confirmPasswordEt.getText().toString());
                break;
        }
    }
}
