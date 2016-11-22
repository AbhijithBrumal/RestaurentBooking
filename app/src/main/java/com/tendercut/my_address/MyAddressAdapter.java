package com.tendercut.my_address;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tendercut.R;
import com.tendercut.customview.MyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abhijith on 22-11-2016.
 */

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.AddressHolder> {



    private int selectedPosition = 0;

    private Context mContext;

    private OnRecyclerClick onrecyclerClick;

    public MyAddressAdapter(Context mContext, MyAddressFragment fragment) {
        this.mContext = mContext;
        onrecyclerClick = (OnRecyclerClick) fragment;
    }

    @Override
    public AddressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        AddressHolder holder = new AddressHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AddressHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onrecyclerClick.itemClicked(position);
                selectedPosition = position;

                notifyDataSetChanged();
            }
        });

        if (position == selectedPosition) {
            holder.addressTv.setTextColor(mContext.getResources().getColor(R.color.colorBlackRegular));
            holder.locationIv.setColorFilter(Color.argb(255, 198, 32, 48));
        } else {
            holder.addressTv.setTextColor(mContext.getResources().getColor(R.color.colorBlackLight));
            holder.locationIv.setColorFilter(Color.argb(200, 134, 134, 134));
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class AddressHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.location_iv)
        ImageView locationIv;
        @BindView(R.id.address_tv)
        MyTextView addressTv;
        @BindView(R.id.edit_iv)
        ImageView editIv;
        @BindView(R.id.edit_rl)
        RelativeLayout editRl;

        public AddressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnRecyclerClick {
        public void itemClicked(int position);
    }
}
