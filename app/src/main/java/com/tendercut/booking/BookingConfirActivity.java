package com.tendercut.booking;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.tendercut.R;
import com.tendercut.customview.MyButton;
import com.tendercut.customview.MyTextView;
import com.tendercut.home.HomeActivity;
import com.tendercut.utils.Keys;
import com.tendercut.utils.LoadingDialog;
import com.tendercut.utils.Utils;
import com.tendercut.utils.sharedPreferances.BookingPreferances;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookingConfirActivity extends AppCompatActivity implements View.OnClickListener, BookingDetailsView {


    @Bind(R.id.image_iv)
    ImageView imageIv;
    @Bind(R.id.title_tv)
    MyTextView titleTv;
    @Bind(R.id.restaurant_name_tv)
    MyTextView restaurantNameTv;
    @Bind(R.id.place_name_tv)
    MyTextView placeNameTv;
    @Bind(R.id.rating_bar)
    AppCompatRatingBar ratingBar;
    @Bind(R.id.table_iv)
    ImageView tableIv;
    @Bind(R.id.tables_title_tv)
    MyTextView tablesTitleTv;
    @Bind(R.id.tables_rv)
    RecyclerView tablesRv;
    @Bind(R.id.tables_nsv)
    NestedScrollView tablesNsv;
    @Bind(R.id.food_iv)
    ImageView foodIv;
    @Bind(R.id.food_title_tv)
    MyTextView foodTitleTv;
    @Bind(R.id.food_rv)
    RecyclerView foodRv;
    @Bind(R.id.menu_name_nsv)
    NestedScrollView menuNameNsv;
    @Bind(R.id.continue_btn)
    MyButton continueBtn;
    @Bind(R.id.activity_booking_confir)
    RelativeLayout activityBookingConfir;

    private BookingDetailsPresenter mBookingDetailsPresenter;
    private LoadingDialog mLoadingDialog;

    private FoodNameAdapter mFoodNameAdapter;
    private TableNamesAdapter mTableNamesAdapter;

    private List<TableNames> mTableNamesList = new ArrayList<>();
    private List<FoodNames> mFoodNamesList = new ArrayList<>();

    private String bookingId;
    private boolean isFromBooking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confir);
        ButterKnife.bind(this);

        mLoadingDialog = new LoadingDialog(this);
        mBookingDetailsPresenter = new BookingDetailsPresnterImpl(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bookingId = extras.getString(Keys.BOOKING_ID);
            isFromBooking = true;
        }else {
            bookingId = BookingPreferances.getBookingId();
            isFromBooking = false;
        }

        mTableNamesAdapter = new TableNamesAdapter(this, mTableNamesList);
        tablesRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        tablesRv.setAdapter(mTableNamesAdapter);

        mFoodNameAdapter = new FoodNameAdapter(this, mFoodNamesList);
        foodRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        foodRv.setAdapter(mFoodNameAdapter);

        mBookingDetailsPresenter.getBookingDetails(BookingPreferances.getUserId(), bookingId);
       /* AnimationDrawable aw = (AnimationDrawable) imageIv.getBackground();
        aw.stop();
        aw.start();*/



        ImageView img = (ImageView) findViewById(R.id.image_iv);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getDrawable();
        frameAnimation.setCallback(img);
        frameAnimation.setVisible(true, true);
        frameAnimation.stop();
        frameAnimation.start();

        continueBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continue_btn:
                Intent home = new Intent(this, HomeActivity.class);
                home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(home);
                break;
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
    public void bookingDetailsSuccess(final String restaurantName, final String restaurantPlace, final String rating, final List<TableNames> tableNamesList, final List<FoodNames> foodNamesList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                restaurantNameTv.setText(restaurantName);
                placeNameTv.setText(restaurantPlace);
                ratingBar.setRating(Float.parseFloat(rating));

                if (tableNamesList.size() != 0){
                    mTableNamesList.clear();
                    mTableNamesList.addAll(tableNamesList);
                    mTableNamesAdapter.notifyDataSetChanged();
                }

                if (foodNamesList.size()!=0){

                    mFoodNamesList.clear();
                    mFoodNamesList.addAll(foodNamesList);
                    mFoodNameAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void bookingDetailsFailure(String msg) {
        Utils.showSnackbar(this, msg);
    }

    @Override
    public void retrofitError(String msg) {
        Utils.showSnackbar(this, msg);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent home = new Intent(this, HomeActivity.class);
        home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(home);
        finish();
    }
}
