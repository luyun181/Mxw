package cn.huanyan.mxwvideo.ui.activity.pulish;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.adapter.HomeIndexAdapter;
import cn.huanyan.mxwvideo.entity.IndexBean;
import cn.huanyan.mxwvideo.entity.UserInfo;
import cn.huanyan.mxwvideo.interf.ApiService;
import cn.huanyan.mxwvideo.utils.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyReleaseActivity extends AppCompatActivity {

    private static int PAGE = 1;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    private Unbinder bind;
    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    recyclerView.setAdapter(homeIndexAdapter);
                    refreshLayout.finishRefreshing();
                    break;
                case 3:
                    homeIndexAdapter.setNewData(newData);
                    refreshLayout.finishLoadmore();
                    break;
            }
            return false;
        }
    });
    private String token;
    private List<IndexBean.DataBeanX.DataBean> data;
    private HomeIndexAdapter homeIndexAdapter;
    private ApiService apiService;
    private List<IndexBean.DataBeanX.DataBean> newData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        bind = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ivBack.setImageResource(R.mipmap.icon_arrow_left);
        toolbarTitle.setText(R.string.card_my_publish);
        apiService = RetrofitUtils.getInstance(this).create(ApiService.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getReleaseList();
        onFresh();
    }

    private void getReleaseList() {
        UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
        if (userInfo != null) {
            token = userInfo.getToken();
        } else {
            token = "";
        }

        Observable<IndexBean> orderDetail = apiService.getRelease(token, 1, 10);
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IndexBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(IndexBean bean) {
                        if (bean.getCode() == 200) {
                            PAGE = 1;
                            data = bean.getData().getData();
                            while (data.size() < 10) {
                                data.addAll(data);
                            }
                            homeIndexAdapter = new HomeIndexAdapter(R.layout.item_home_list, data);
                            homeIndexAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    int user_id = data.get(position).getUser_id();
                                    ToastUtils.showShort(user_id + "");
                                }
                            });
                            Message msg = new Message();
                            msg.what = 1;
                            mHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.i("eoooo");
                        LogUtils.e(throwable.getMessage());
//                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

//                        dialog.dismiss();
                    }
                });
    }

    private void getLoadMore() {
        UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
        if (userInfo != null) {
            token = userInfo.getToken();
        } else {
            token = "";
        }

        Observable<IndexBean> orderDetail = apiService.getRelease(token, ++PAGE, 10);
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IndexBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(IndexBean bean) {
                        if (bean.getCode() == 200) {
                            newData = bean.getData().getData();
                            data.addAll(newData);
                            homeIndexAdapter = new HomeIndexAdapter(R.layout.item_home_list, data);
                            homeIndexAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    int user_id = data.get(position).getUser_id();
                                    ToastUtils.showShort(user_id + "");
                                }
                            });
                            Message msg = new Message();
                            msg.what = 3;
                            mHandler.sendMessage(msg);
                        }else {
                            ToastUtils.showShort("没有更多数据");
                            refreshLayout.finishLoadmore();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.i("eoooo");
                        LogUtils.e(throwable.getMessage());
//                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

//                        dialog.dismiss();
                    }
                });
    }

    private void onFresh() {
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                getReleaseList();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                getLoadMore();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @OnClick({R.id.iv_back, R.id.toolbar_title, R.id.tv_second_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.toolbar_title:
                break;
            case R.id.tv_second_title:
                break;
        }
    }
}
