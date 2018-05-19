package cn.huanyan.mxwvideo.ui.fragment.follow.child;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.huanyan.mxwvideo.MainActivity;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.adapter.RecommendAdapter;
import cn.huanyan.mxwvideo.base.BaseFragment;
import cn.huanyan.mxwvideo.entity.CommonBean;
import cn.huanyan.mxwvideo.entity.RecommendBean;
import cn.huanyan.mxwvideo.entity.UserInfo;
import cn.huanyan.mxwvideo.event.LoginEvent;
import cn.huanyan.mxwvideo.event.TabSelectedEvent;
import cn.huanyan.mxwvideo.interf.ApiService;
import cn.huanyan.mxwvideo.ui.activity.follow.FollowDetailActivity;
import cn.huanyan.mxwvideo.ui.activity.login.LoginActivity;
import cn.huanyan.mxwvideo.utils.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by  on 18/5/5.
 */
public class FollowHomeFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_follow_top)
    RelativeLayout layoutFollowTop;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.tv_list_title)
    TextView tvListTitle;


    private boolean mInAtTop = true;
    private int mScrollTotal;
    private ApiService apiService;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
//                    adapter.addHeaderView(getHeaderView(0), 0);
                    recyclerView.setAdapter(adapter);
                    if (adapter != null) {
                        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.tv_btn_follow:
                                        //todo
                                        RecommendBean.DataBean dataBean = data.get(position);
                                        int user_id = dataBean.getUser_id();
                                        status = dataBean.getStatus();
                                        getFollows(status, user_id + "", position);
                                        break;
                                }
                            }
                        });

                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                is_login = SPUtils.getInstance().getBoolean("is_login");
                                if (!is_login) {
                                    Intent intent = new Intent(_mActivity, LoginActivity.class);
                                    startActivity(intent);
                                    return;
                                }
                                if (data != null) {
                                    RecommendBean.DataBean dataBean = data.get(position);
                                    int user_id = dataBean.getUser_id();
                                    Intent intent = new Intent(_mActivity, FollowDetailActivity.class);
                                    intent.putExtra("user_id", user_id);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                    refreshLayout.finishRefreshing();
                    break;
                case 2:
                    int obj = (int) msg.obj;
                    RecommendBean.DataBean item = adapter.getItem(obj);
                    if (status == 20) {
                        item.setStatus(10);
                    } else {
                        item.setStatus(20);
                    }
                    adapter.notifyItemChanged(obj);
                    break;
                case 3:
                    Intent intent = new Intent(_mActivity, LoginActivity.class);
                    startActivity(intent);
                    break;
                case 4:
                    recyclerView.setAdapter(adapter);
                    if (adapter != null) {
                        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.tv_btn_follow:
                                        //todo
                                        RecommendBean.DataBean dataBean = data.get(position);
                                        int user_id = dataBean.getUser_id();
                                        status = dataBean.getStatus();
                                        getFollows(status, user_id + "", position);
                                        break;
                                }
                            }
                        });

                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                is_login = SPUtils.getInstance().getBoolean("is_login");
                                if (!is_login) {
                                    Intent intent = new Intent(_mActivity, LoginActivity.class);
                                    startActivity(intent);
                                    return;
                                }
                                if (data != null) {
                                    RecommendBean.DataBean dataBean = data.get(position);
                                    int user_id = dataBean.getUser_id();
                                    Intent intent = new Intent(_mActivity, FollowDetailActivity.class);
                                    intent.putExtra("user_id", user_id);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                    layoutFollowTop.setVisibility(View.GONE);
                    tvListTitle.setText(R.string.my_follow);
                    refreshLayout.finishRefreshing();
                    break;
                case 5:
                    tvListTitle.setText(R.string.guess_like);
                    layoutFollowTop.setVisibility(View.VISIBLE);
                    getCommendList();
                    break;
            }
            return false;
        }
    });
    private List<RecommendBean.DataBean> data = new ArrayList<>();
    private RecommendAdapter adapter;
    private String token = "";
    private boolean is_login;
    private int status;
    /* private List<RecommendBean.DataBean> dataMyFollow = new ArrayList<>();
    private RecommendAdapter  adapterMyFollow = new RecommendAdapter(R.layout.item_follow_list, dataMyFollow);*/

    public static FollowHomeFragment newInstance() {

        Bundle args = new Bundle();

        FollowHomeFragment fragment = new FollowHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow_list, container, false);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        initView(view);

        return view;
    }


    private void initView(View view) {
        toolbarTitle.setText(R.string.follow);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        apiService = RetrofitUtils.getInstance(_mActivity).create(ApiService.class);
        is_login = SPUtils.getInstance().getBoolean("is_login");
        if (is_login) {
            getMyFollows();
        } else {
            layoutFollowTop.setVisibility(View.VISIBLE);
            getCommendList();
        }
        onFreshView();
    }

    private void onFreshView() {
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
//                super.onRefresh(refreshLayout);
                getMyFollows();

            }

        });
    }

    private void getCommendList() {
        Observable<RecommendBean> orderDetail = apiService.getRecommend();
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendBean>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(RecommendBean bean) {
                        int code = bean.getCode();
                        Message message = new Message();
                        if (code == 200) {
                            data = bean.getData();
                            adapter = new RecommendAdapter(R.layout.item_follow_list, data);


                            message.what = 1;
                        } else if (code == 300) {
//                            message.what = 3;
                        }
                        mHandler.sendMessage(message);
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

    private void getFollows(int status, String user_id, final int postion) {
        UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
        if (userInfo == null) {
            token = "";
        } else {
            token = userInfo.getToken();
        }
        Observable<CommonBean> orderDetail = apiService.getClickFollow(token, status, user_id);
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(CommonBean bean) {
                        Message message = new Message();
                        if (bean.getCode() == 200) {
                            message.what = 2;
                            message.obj = postion;

                        } else if (bean.getCode() == 300) {
                            message.what = 3;
                        } else {
                            String msg = bean.getMsg();
                            if (msg != null) {
                                ToastUtils.showShort(msg);
                            }
                        }
                        mHandler.sendMessage(message);
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

    private void getMyFollows() {
        UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
        if (userInfo == null) {
            token = "";
        } else {
            token = userInfo.getToken();
        }
        Observable<RecommendBean> orderDetail = apiService.getMyFollow(token, 1, 10);
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendBean>() {

                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(RecommendBean bean) {
                        Message message = new Message();
                        if (bean.getCode() == 200) {
                            data = bean.getData();
                            adapter = new RecommendAdapter(R.layout.item_follow_list, data);
                            message.what = 4;

                        } else if (bean.getCode() == 300) {
                            message.what = 3;
                        } else if (bean.getCode() == 201) {
                            message.what = 5;
                        }
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.i("eoooo");
                        LogUtils.e(throwable.getMessage());
//                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.i("onComplete");
//                        dialog.dismiss();
                    }
                });
    }

    private void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    /**
     * 选择tab事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != MainActivity.FIRST) return;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent loginEvent) {
        if (loginEvent.isLogin()) {
            getMyFollows();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mRecy.setAdapter(null);
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    private View getHeaderView(int type) {
        View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) recyclerView.getParent(), false);
        if (type == 1) {
            TextView viewById = view.findViewById(R.id.tv_title);
            viewById.setText("我的关注");
        }
//        view.setOnClickListener(listener);
        return view;
    }
}
