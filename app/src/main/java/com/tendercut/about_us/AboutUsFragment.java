package com.tendercut.about_us;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tendercut.R;
import com.tendercut.customview.MyTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutUsFragment extends Fragment implements View.OnClickListener {


    @Bind(R.id.aboutus_content_tv)
    MyTextView aboutusContentTv;
    @Bind(R.id.privacy_rl)
    RelativeLayout privacyRl;
    @Bind(R.id.terms_rl)
    RelativeLayout termsRl;
    @Bind(R.id.activity_about_us)
    RelativeLayout activityAboutUs;

    public AboutUsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        privacyRl.setOnClickListener(this);
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

    }
}
