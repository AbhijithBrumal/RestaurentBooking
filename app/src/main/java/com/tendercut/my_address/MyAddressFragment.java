package com.tendercut.my_address;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tendercut.R;
import com.tendercut.notifications.NotificationsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAddressFragment extends Fragment implements MyAddressAdapter.OnRecyclerClick {

    @BindView(R.id.my_address_rv)
    RecyclerView myAddressRv;

    MyAddressAdapter mMyAddressAdapter;

    public MyAddressFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_address, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myAddressRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mMyAddressAdapter = new MyAddressAdapter(getActivity(), this);
        myAddressRv.setAdapter(mMyAddressAdapter);
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
    public void itemClicked(int position) {

    }
}
