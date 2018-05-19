package cn.huanyan.mxwvideo.ui.fragment.home.child;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.huanyan.mxwvideo.MainActivity;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.adapter.HomeIndexAdapter;
import cn.huanyan.mxwvideo.base.BaseFragment;
import cn.huanyan.mxwvideo.entity.BannerBean;
import cn.huanyan.mxwvideo.entity.IndexBean;
import cn.huanyan.mxwvideo.entity.NoticeBean;
import cn.huanyan.mxwvideo.event.TabSelectedEvent;
import cn.huanyan.mxwvideo.interf.ApiService;
import cn.huanyan.mxwvideo.utils.GlideImageLoader;
import cn.huanyan.mxwvideo.utils.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class HomeListFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.nested_view)
    NestedScrollView nestedView;
    private boolean mInAtTop = true;
    private int mScrollTotal;
    private Context mContext;
    private List<String> imageList = new ArrayList<>();
    private ApiService apiService;
    private List<IndexBean.DataBeanX.DataBean> data = new ArrayList<>();
    private HomeIndexAdapter homeIndexAdapter;
    private boolean refreshing = false;
    private int PAGE = 1;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    banner.setImages(imageList).setBannerStyle(BannerConfig.NUM_INDICATOR).setImageLoader(new GlideImageLoader()).start();
                    break;
                case 2:
                    homeIndexAdapter = new HomeIndexAdapter(R.layout.item_home_list, data);
                    recyclerView.setAdapter(homeIndexAdapter);
                    refreshLayout.finishRefreshing();
                    break;
                case 3:
                    homeIndexAdapter.setNewData(data);
                    refreshLayout.finishLoadmore();
                    break;
                case 4:
                    ToastUtils.showShort("没有更多数据了");
                    refreshLayout.finishLoadmore();
                    break;

            }
            return false;
        }
    });
    private List<IndexBean.DataBeanX.DataBean> newData;

    public static HomeListFragment newInstance() {
        Bundle args = new Bundle();
        HomeListFragment fragment = new HomeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        mContext = getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        toolbarTitle.setText(R.string.tab_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        apiService = RetrofitUtils.getInstance(_mActivity).create(ApiService.class);
        getBanner();
        getNoticeList();
        getIndexList();
        onFreshView();
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort(position+"");
            }
        });
    }

    private void onFreshView() {
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                getIndexLoad();
            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                getIndexList();
            }

        });
    }

    private void getIndexList() {
        Observable<IndexBean> orderDetail = apiService.getIndexDetail(1, 10);
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
                            Message msg = new Message();
                            msg.what = 2;
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

    private void getIndexLoad() {
        Observable<IndexBean> orderDetail = apiService.getIndexDetail(++PAGE, 10);
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
                            Message msg = new Message();
                            newData = bean.getData().getData();
                            if (newData == null || newData.size()==0){
                                msg.what = 4;
                            }else {
                                data.addAll(newData);
                                msg.what = 3;
                            }
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

    private void getBanner() {
        Observable<BannerBean> orderDetail = apiService.getBannerDetail();
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(BannerBean bean) {
                        if (bean.getCode() == 200) {
                            List<BannerBean.DataBean> data = bean.getData();
                            for (int i = 0; i < data.size(); i++) {
                                String img = data.get(i).getImg();
                                if (img != null) {
                                    imageList.add(img);
                                }
                            }
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

    private void getNoticeList() {

        Observable<NoticeBean> orderDetail = apiService.getNoticeDetail();
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NoticeBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(NoticeBean bean) {
                        if (bean.getCode() == 200) {
                            List<NoticeBean.DataBean> data = bean.getData();
                            List<String> info = new ArrayList<>();
                            for (int i = 0; i < data.size(); i++) {
                                String title = data.get(i).getTitle();
                                if (title != null) {
                                    info.add(title);
                                }

                            }
                            marqueeView.startWithList(info);
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


    /**
     * 选择tab事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != MainActivity.FIRST) return;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @OnClick({R.id.iv_back, R.id.banner, R.id.marqueeView, R.id.recycler_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.banner:
                break;
            case R.id.marqueeView:
                break;
            case R.id.recycler_view:
                break;

        }
    }
}
