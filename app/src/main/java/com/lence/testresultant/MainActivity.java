package com.lence.testresultant;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lence.testresultant.model.StocksModel;
import com.lence.testresultant.utils.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Mvp {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress)
    RelativeLayout mProgress;
    Presenter mPresenter;
    Adapter mAdapter;
    Handler mHandler = new Handler();
    private long mTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPresenter = new Presenter(this);
        if (NetworkUtil.isNetworkConnected(this)) {
            if (mTime == 0L) {
                mTime = SystemClock.uptimeMillis();
                mHandler.removeCallbacks(timeUpdaterRunnable);

            }
        }
        else showMessage(getString (R.string.internet_false));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);


    }

    private Runnable timeUpdaterRunnable = new Runnable() {
        public void run() {
            showProgress();
            mPresenter.load();
            mHandler.postDelayed(this, 15000);
        }
    };

    @Override
    protected void onPause() {
        mHandler.removeCallbacks(timeUpdaterRunnable);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(timeUpdaterRunnable, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refresh) {
            showProgress();
            mPresenter.load();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshList(StocksModel body) {
        mAdapter = new Adapter(body, mPresenter, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        notShowProgress();
    }

    @Override
    public void showMessage(String message) {
        notShowProgress();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    private void notShowProgress() {
        mProgress.setVisibility(View.GONE);
    }
}
