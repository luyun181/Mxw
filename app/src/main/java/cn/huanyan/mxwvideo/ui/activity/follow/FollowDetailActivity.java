package cn.huanyan.mxwvideo.ui.activity.follow;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.adapter.FollowDetailAdapter;
import cn.huanyan.mxwvideo.entity.CommonBean;
import cn.huanyan.mxwvideo.entity.FollowInfoBean;
import cn.huanyan.mxwvideo.entity.UserInfo;
import cn.huanyan.mxwvideo.event.LoginEvent;
import cn.huanyan.mxwvideo.interf.ApiService;
import cn.huanyan.mxwvideo.utils.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FollowDetailActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    Unbinder unbinder;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.tv_followed)
    TextView tvFollowed;
    @BindView(R.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R.id.tv_fans_num)
    TextView tvFansNum;
    private String token;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String user_name = head.getUser_name();
                    if (user_name != null) {
                        tvUserName.setText(user_name);
                    }
                    String signature = head.getSignature();
                    if (signature != null) {
                        tvSecondTitle.setText(signature);
                    }
                    String fans = head.getFans();
                    if (fans != null) {
                        tvFansNum.setText(fans);
                    }
                    String follows = head.getFollows();
                    if (follows != null) {
                        tvFollowNum.setText(follows);
                    }
                    status = head.getStatus();
                    if (status == 10) {
                        tvFollowed.setText(R.string.followed);
                    } else {
                        tvFollowed.setText(R.string.add_follow);
                    }
                    user_id = head.getUser_id();
                    recyclerView.setAdapter(homeIndexAdapter);
                    break;
                case 2:
                    if (status == 10) {
                        tvFollowed.setText(R.string.add_follow);
                        status = 20;
                    } else {
                        tvFollowed.setText(R.string.followed);
                        status = 10;
                    }
                    break;
                case 3:
                    ToastUtils.showShort("请先登录");
                    break;
            }
            return false;
        }
    });
    private FollowDetailAdapter homeIndexAdapter;
    private List<FollowInfoBean.DataBeanX.ListsBean.DataBean> data;
    private FollowInfoBean.DataBeanX.HeadBean head;
    private ApiService apiService;
    private int status;
    private int user_id;

    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_detail);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiService = RetrofitUtils.getInstance(this).create(ApiService.class);
        Bundle extras = getIntent().getExtras();
        int user_id = extras.getInt("user_id");
        getFollowDetail(user_id);
    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getFollowDetail(int user_id) {
        UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
        if (userInfo != null) {
            token = userInfo.getToken();
        } else {
            token = "";
        }

        Observable<FollowInfoBean> orderDetail = apiService.getVideoInfo(token, user_id, 1, 10);
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(FollowInfoBean bean) {
                        Message message = new Message();
                        if (bean.getCode() == 200) {
                            head = bean.getData().getHead();
                            data = bean.getData().getLists().getData();
                            homeIndexAdapter = new FollowDetailAdapter(R.layout.item_follow_detail, data);
                            message.what = 1;
                        } else {
                          /*  String msg = bean.getMsg();
                            if (msg != null) {
                                ToastUtils.showShort(msg);
                            }*/
                        }
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.i("eoooo");
                        LogUtils.e(throwable.getMessage());
                        ToastUtils.showShort(R.string.net_error);
//                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

//                        dialog.dismiss();
                    }
                });
    }

    private View getHeaderView(int type, FollowInfoBean.DataBeanX.HeadBean head) {
        View view = getLayoutInflater().inflate(R.layout.head_follow_view, (ViewGroup) recyclerView.getParent(), false);
        if (type == 1) {
            TextView user_name = view.findViewById(R.id.tv_user_name);
            String user_name1 = head.getUser_name();
            if (user_name1 != null) {
                user_name.setText(user_name1);
            }
            TextView tv_content = view.findViewById(R.id.tv_second_title);
            String signature = head.getSignature();
            if (signature != null) {
                tv_content.setText(signature);
            }
            TextView fansNum = view.findViewById(R.id.tv_fans_num);
            String fans = head.getFans();
            if (fans != null) {
                fansNum.setText(fans);
            }
            TextView followNum = view.findViewById(R.id.tv_follow_num);
            String follows = head.getFollows();
            if (follows != null) {
                followNum.setText(follows);
            }
            final int status = head.getStatus();
            final int user_id = head.getUser_id();
            TextView followed = view.findViewById(R.id.tv_followed);
            followed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showShort("xxxxxx");
                    getFollows(status, user_id + "");
                }
            });

        }
//        view.setOnClickListener(listener);
        return view;
    }

    private void getFollows(int status, String user_id) {
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
                            EventBus.getDefault().post(new LoginEvent(true));

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.tv_followed, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_followed:
                getFollows(status, user_id + "");
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
