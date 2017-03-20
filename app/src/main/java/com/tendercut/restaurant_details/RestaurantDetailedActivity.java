package com.tendercut.restaurant_details;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TimePicker;

import com.squareup.picasso.Picasso;
import com.tendercut.R;
import com.tendercut.customview.MyButton;
import com.tendercut.customview.MyTextView;
import com.tendercut.restaurant_details.JsonArray.Booking;
import com.tendercut.restaurant_details.JsonArray.Table;
import com.tendercut.restaurant_details.adapters.FecilitiesAdapter;
import com.tendercut.restaurant_details.adapters.PersonsCountAdapter;
import com.tendercut.restaurant_details.adapters.TableSelectionAdapter;
import com.tendercut.restaurant_details.adapters.TimeSelectionAdapter;

import com.tendercut.utils.Keys;
import com.tendercut.utils.LoadingDialog;
import com.tendercut.utils.Utils;
import com.tendercut.utils.sharedPreferances.BookingPreferances;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantDetailedActivity extends AppCompatActivity implements View.OnClickListener, TableSelectionAdapter.OnTableClick,
        TimeSelectionAdapter.OnTimeClick, PersonsCountAdapter.OnPersonClick, RestaurantDetailsView {


    @Bind(R.id.app_bar)
    Toolbar toolbar;
    @Bind(R.id.toolbarText)
    MyTextView toolbarText;
    @Bind(R.id.restaurant_iv)
    ImageView restaurantIv;
    @Bind(R.id.discription_tv)
    MyTextView discriptionTv;
    @Bind(R.id.place_name_tv)
    MyTextView placeNameTv;
    @Bind(R.id.restaurant_fl)
    RelativeLayout restaurantFl;
    @Bind(R.id.rating_bar)
    AppCompatRatingBar ratingBar;
    @Bind(R.id.time_tv)
    MyTextView timeTv;
    @Bind(R.id.fecilities_title_tv)
    MyTextView fecilitiesTitleTv;
    @Bind(R.id.fecilities_rv)
    RecyclerView fecilitiesRv;
    @Bind(R.id.select_time_title)
    MyTextView selectTimeTitle;
    @Bind(R.id.time_slot_rv)
    RecyclerView timeSlotRv;
    @Bind(R.id.pic_table_title)
    MyTextView picTableTitle;
    @Bind(R.id.tabele_name_rv)
    RecyclerView tabeleNameRv;
    @Bind(R.id.continue_btn)
    MyButton continueBtn;
    @Bind(R.id.fecilities_nsv)
    NestedScrollView fecilitiesNsv;
    @Bind(R.id.select_time_nsv)
    NestedScrollView selectTimeNsv;
    @Bind(R.id.pic_table_title_nsv)
    NestedScrollView picTableTitleNsv;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.activity_restaurant_detailed)
    RelativeLayout activityRestaurantDetailed;


    private int CalendarHour, CalendarMinute;
    String format;
    Calendar calendar;
    TimePickerDialog timepickerdialog;

    private RecyclerView nameRv, menuRv;

    private Dialog mDialog;
    private FecilitiesAdapter mTableAdapter;
    private TimeSelectionAdapter mTimeSelectionAdapter;
    private PersonsCountAdapter mPersonCountAdapter;

    private TableSelectionAdapter mTableSelectionAdapter;

    private GridLayoutManager gridLayoutManager;

    private RecyclerView timeRv, peopleRv;

    private LoadingDialog mLoadingDialog;

    private RestaurentDetailsPresenter mRestaurantDetailsPresenter;

    private FecilitiesAdapter mFecilitiesAdapter;

    private String restaurantId, bookingTime;

    private List<Time> mTimeList = new ArrayList<>();

    private List<TablesList> mTableList = new ArrayList<>();

    private List<Fecilities> mFecilities = new ArrayList<>();

    private List<Table> mTable = new ArrayList<>();
    private List<Booking> mBookingList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detailed);
        ButterKnife.bind(this);

        initToolBar();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            restaurantId = extras.getString(Keys.RESTAURANT_ID);
            BookingPreferances.setRestaurantID(restaurantId);
        }

        gridLayoutManager = new GridLayoutManager(this, 3);

        mLoadingDialog = new LoadingDialog(this);
        mRestaurantDetailsPresenter = new RestaurantDetailsPresenterImpl(this);

        tabeleNameRv.setVisibility(View.GONE);
        picTableTitle.setVisibility(View.GONE);
        continueBtn.setVisibility(View.GONE);


        //  mTimeSelectionAdapter = new TimeSelectionAdapter(this);

        tabeleNameRv.setLayoutManager(gridLayoutManager);
        mTableSelectionAdapter = new TableSelectionAdapter(this, mTableList);
        tabeleNameRv.setAdapter(mTableSelectionAdapter);

        timeSlotRv.setLayoutManager(new GridLayoutManager(this, 3));
        mTimeSelectionAdapter = new TimeSelectionAdapter(this, mTimeList);
        timeSlotRv.setAdapter(mTimeSelectionAdapter);

        fecilitiesRv.setLayoutManager(new LinearLayoutManager(this));
        mFecilitiesAdapter = new FecilitiesAdapter(this, mFecilities);
        fecilitiesRv.setAdapter(mFecilitiesAdapter);

        mRestaurantDetailsPresenter.getRestaurantDetails(BookingPreferances.getUserId(), BookingPreferances.getRestaurantId());

        continueBtn.setOnClickListener(this);
        // selectTimeTitle.setOnClickListener(this);
        // numChairTitle.setOnClickListener(this);


    }


    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        toolbarText.setText("Cochin Fort");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();

        getMenuInflater().inflate(R.menu.clear, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.clear_all:
                finish();
                startActivity(getIntent());
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showDialogBox() {

        mDialog = new Dialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.popup_window_names);

        nameRv = (RecyclerView) mDialog.findViewById(R.id.name_rv);

        nameRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        nameRv.setAdapter(mTableAdapter);

        mDialog.show();
    }

    /* private void showMenuDialogBox() {

         mDialog = new Dialog(this);
         mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
         mDialog.setContentView(R.layout.popup_window_menu);

         menuRv = (RecyclerView) mDialog.findViewById(R.id.menu_rv);

         menuRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
         menuRv.setAdapter(mMenuFoodAdapter);

         mDialog.show();
     }
 */

    private void showDialogBoxTime() {

        mDialog = new Dialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.popup_window_time);
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //dialog.setTitle("Choose Blood Group");

        timeRv = (RecyclerView) mDialog.findViewById(R.id.time_slot_rv);


        timeRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        timeRv.setAdapter(mTimeSelectionAdapter);

        mDialog.show();
    }

    private void showDialogBoxPeople() {

        mDialog = new Dialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.popup_window_people);
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //dialog.setTitle("Choose Blood Group");

        peopleRv = (RecyclerView) mDialog.findViewById(R.id.people_slot_rv);


        peopleRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        peopleRv.setAdapter(mPersonCountAdapter);

        mDialog.show();
    }

    private void showTimePickerDialog() {

        calendar = Calendar.getInstance();
        CalendarHour = calendar.get(Calendar.HOUR_OF_DAY);
        CalendarMinute = calendar.get(Calendar.MINUTE);


        timepickerdialog = new TimePickerDialog(RestaurantDetailedActivity.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        if (hourOfDay == 0) {

                            hourOfDay += 12;

                            format = "AM";
                        } else if (hourOfDay == 12) {

                            format = "PM";

                        } else if (hourOfDay > 12) {

                            hourOfDay -= 12;

                            format = "PM";

                        } else {

                            format = "AM";
                        }


                        // timeTv.setText(hourOfDay + ":" + minute + format);
                    }
                }, CalendarHour, CalendarMinute, false);
        timepickerdialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
          /*  case R.id.time_tv:

                showTimePickerDialog();

                break;
            case R.id.table_name_tv:

                showDialogBox();

                break;*/
            case R.id.continue_btn:




                //  mRestaurantDetailsPresenter.tableBooking(BookingPreferances.getUserId(), restaurantId, "0", bookingTime, mTablesList);
                break;


            default:
                break;
        }
    }

    @Override
    public void tableClicked(int position, String tableName, int tableId) {
        Table tables = new Table();

        tables.setTableId(String.valueOf(tableId));

        mTable.add(tables);
        selectTimeTitle.setVisibility(View.VISIBLE);
        timeSlotRv.setVisibility(View.VISIBLE);
        continueBtn.setVisibility(View.VISIBLE);
        continueBtn.requestFocus();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        }, 700);


    }


    @Override
    public void PersonClicked(int position) {
        //mDialog.cancel();
    }

    @Override
    public void timeClicked(int position, String time) {
        //  mDialog.cancel();
        bookingTime = time;
        tabeleNameRv.requestFocus();
        timeSlotRv.setVisibility(View.GONE);
        selectTimeTitle.setVisibility(View.GONE);
        picTableTitle.setVisibility(View.VISIBLE);
        tabeleNameRv.setVisibility(View.VISIBLE);
        picTableTitle.setVisibility(View.VISIBLE);
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
    public void restaurantDetailsSuccess(final String restaurantID, final String restaurantName, String restaurentAddress,
                                         String restaurentPhone, final String restaurantDescription,
                                         final String restaurantImage, final String restaurantTime, final String place,
                                         String starValue, final List<Fecilities> fecilitiesList, final List<Time> timeList, final List<TablesList> tableList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Picasso.with(getApplicationContext()).load(restaurantImage).fit().into(restaurantIv);

                restaurantId = restaurantID;
                BookingPreferances.setRestaurantID(restaurantID);
                toolbarText.setText(restaurantName);
                discriptionTv.setText(restaurantDescription);
                placeNameTv.setText(place);
                timeTv.setText(restaurantTime);

                if (tableList.size() != 0) {
                    mTableList.clear();
                    mTableList.addAll(tableList);
                    mTableSelectionAdapter.notifyDataSetChanged();
                }

                if (timeList.size() != 0) {

                    mTimeList.clear();
                    mTimeList.addAll(timeList);
                    mTimeSelectionAdapter.notifyDataSetChanged();

                }

                if (fecilitiesList.size() != 0) {

                    mFecilities.clear();
                    mFecilities.addAll(fecilitiesList);
                    mFecilitiesAdapter.notifyDataSetChanged();

                }

            }
        });
    }

    @Override
    public void restaurantDetailsFailure(String msg) {

        Utils.showSnackbar(this, msg);
    }

    @Override
    public void retrofitError(String msg) {
        Utils.showSnackbar(this, msg);
    }

    @Override
    public void restaurantBookingSuccess(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void restaurantBookingfailure(String msg) {
        Utils.showSnackbar(this, msg);
    }
}
