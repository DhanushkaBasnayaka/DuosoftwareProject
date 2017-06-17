package com.soluvition.dw.duosoftwareproject.activityview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soluvition.dw.duosoftwareproject.DuosoConstant;
import com.soluvition.dw.duosoftwareproject.R;
import com.soluvition.dw.duosoftwareproject.adapters.Listadapter;
import com.soluvition.dw.duosoftwareproject.helper.DuosoArryList;
import com.soluvition.dw.duosoftwareproject.helper.DuosoftHelper;
import com.soluvition.dw.duosoftwareproject.service.DuosoftServiceManager;
import com.soluvition.dw.duosoftwareproject.service.events.FailedEvent;
import com.soluvition.dw.duosoftwareproject.service.events.LoginUserEvent;
import com.soluvition.dw.duosoftwareproject.service.events.MyTicketsListEvent;
import com.soluvition.dw.duosoftwareproject.service.requestes.MyTicketsListRequest;


import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements View.OnClickListener, Listadapter.FriendlistCallback {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.rl_supervisor)
    RelativeLayout mRelativeLayoutSupervisor;

    MyTicketsListRequest mMyTicketsListRequest;
    Listadapter mAdapter;

    private Activity activity;

    @Inject
    public DuosoftServiceManager mDuosoftServiceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.list_data);
        mToolbar.setNavigationOnClickListener(this);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getTicketDetailList();

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onClicknext(int userId, int Is_friend, int position) {

    }

    public void getTicketDetailList() {
        mMyTicketsListRequest = new MyTicketsListRequest();
        mMyTicketsListRequest.limit = "15";
        mMyTicketsListRequest.pageNumber = "1";
        mMyTicketsListRequest.Authorization = DuosoConstant.BEARER + " " + accessToken;
        mDuosoftServiceManager.getMyTicketsList(mMyTicketsListRequest);
    }


    @Subscribe
    public void onEvent(MyTicketsListEvent event) {
        if (event.result.Result.size() > 0) {

            for (int i = 0; i < event.result.Result.size(); i++) {

                DuosoArryList.newReportList.add(event.result.Result.get(i));
            }
            mAdapter = new Listadapter(HomeActivity.this, this, DuosoArryList.newReportList);
            recyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe
    public void onEvent(FailedEvent event) {

        if (DuosoftHelper.isNetworkAvailable(this)) {
            DuosoftHelper.showSnackbar(mRelativeLayoutSupervisor, event.message, this);
        } else {
            DuosoftHelper.showSnackbar(mRelativeLayoutSupervisor, DuosoConstant.ERR_INTERNET_CONNECTION_FAILED, this);
        }
    }

}
