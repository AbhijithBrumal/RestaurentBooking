package com.tendercut.profile;

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
import com.tendercut.utils.db_flow.DataBaseHelper;
import com.tendercut.utils.db_flow.User;
import com.tendercut.utils.sharedPreferances.BookingPreferances;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileView{


    @Bind(R.id.profile_image)
    CircleImageView profileImage;
    @Bind(R.id.first_name_et)
    MyEditText firstNameEt;
    @Bind(R.id.last_name_et)
    MyEditText lastNameEt;
    @Bind(R.id.email_et)
    MyEditText emailEt;
    @Bind(R.id.phone_num_et)
    MyEditText phoneNumEt;
    @Bind(R.id.edit_btn)
    MyButton editBtn;

    private User mUSer;

    private LoadingDialog mLoadingDialog;
    private ProfilePresenter mProfilePresenter;

    private boolean isEdit = true;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLoadingDialog = new LoadingDialog(getActivity());
        mProfilePresenter = new ProfilePresenterImpl(this);


        mUSer = DataBaseHelper.getUserObject();
        setDisabled();
        editBtn.setOnClickListener(this);
        setDataUi();

    }

    private void setDataUi() {
        firstNameEt.setText(mUSer.getFirstname());
        lastNameEt.setText(mUSer.getLastName());
        emailEt.setText(mUSer.getEmail());
        phoneNumEt.setText(mUSer.getPhone());


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
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.edit_btn:

                if (isEdit){

                    editBtn.setText("Update");
                    setEnabled();

                    isEdit = false;
                }else {

                    String fName, lName, mail, phone;

                    fName = firstNameEt.getText().toString();
                    lName = lastNameEt.getText().toString();
                    mail = emailEt.getText().toString();
                    phone = phoneNumEt.getText().toString();

                    mProfilePresenter.updateProfile(fName, lName, mail, phone, BookingPreferances.getUserId());

                }
                break;
        }
    }

    private void setEnabled() {
        firstNameEt.setEnabled(true);
        lastNameEt.setEnabled(true);
        emailEt.setEnabled(true);
        phoneNumEt.setEnabled(true);
    }

    private void setDisabled(){
        firstNameEt.setEnabled(false);
        lastNameEt.setEnabled(false);
        emailEt.setEnabled(false);
        phoneNumEt.setEnabled(false);
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
    public void profileUpdateSuccess(final int userId, final String firstName, final String lastName, final String emailId, final String phoneNum) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {


                User user = DataBaseHelper.getUserObject();

                user.setFirstname(firstName);
                user.setLastName(lastName);
                user.setEmail(emailId);
                user.setPhone(phoneNum);
                user.setGender("male");

                user.save();

                Utils.showSnackbar(getActivity(), "Profile updated successfully.");

                setDisabled();
                editBtn.setText("Edit");
                isEdit = true;


            }
        });

    }

    @Override
    public void profileUpdateFailure(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setEnabled();
                Utils.showSnackbar(getActivity(), msg);

            }
        });
    }

    @Override
    public void firstNameError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstNameEt.setError(msg);
                firstNameEt.requestFocus();

                setEnabled();

            }
        });
    }

    @Override
    public void lastNameError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lastNameEt.setError(msg);
                lastNameEt.requestFocus();

                setEnabled();
                editBtn.setText("Update");
                isEdit = true;
            }
        });
    }

    @Override
    public void emailError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                emailEt.setError(msg);
                emailEt.requestFocus();

                setEnabled();
                editBtn.setText("Update");
                isEdit = true;
            }
        });
    }

    @Override
    public void mobileError(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                phoneNumEt.setError(msg);
                phoneNumEt.requestFocus();

                setEnabled();
                editBtn.setText("Update");
                isEdit = true;
            }
        });
    }

    @Override
    public void retrofitError(String msg) {
        Utils.showSnackbar(getActivity(), msg);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setEnabled();
                editBtn.setText("Update");
                isEdit = true;
            }
        });

    }
}
