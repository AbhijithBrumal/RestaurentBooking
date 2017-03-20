package com.tendercut.contact_us;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tendercut.R;
import com.tendercut.customview.MyButton;
import com.tendercut.customview.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ContactUsFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.aboutus_content_tv)
    MyTextView aboutusContentTv;
    @Bind(R.id.email_us_btn)
    MyButton emailUsBtn;
    @Bind(R.id.call_us_btn)
    MyButton callUsBtn;
    @Bind(R.id.activity_about_us)
    RelativeLayout activityAboutUs;

    public ContactUsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callUsBtn.setOnClickListener(this);
        emailUsBtn.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.call_us_btn:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "1234567891"));
                startActivity(callIntent);
                break;
            case R.id.email_us_btn:
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "email@email.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "enquiry");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                break;
        }
    }
}
