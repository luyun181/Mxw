package cn.huanyan.mxwvideo.ui.fragment.me.child;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.huanyan.mxwvideo.MainActivity;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.base.BaseFragment;
import cn.huanyan.mxwvideo.entity.UserInfo;
import cn.huanyan.mxwvideo.event.LoginEvent;
import cn.huanyan.mxwvideo.event.TabSelectedEvent;
import cn.huanyan.mxwvideo.helper.DetailTransition;
import cn.huanyan.mxwvideo.ui.activity.login.LoginActivity;
import cn.huanyan.mxwvideo.ui.activity.me.PersonInfoActivity;
import cn.huanyan.mxwvideo.ui.activity.me.SecurityActivity;
import cn.huanyan.mxwvideo.ui.activity.pulish.MyReleaseActivity;
import cn.huanyan.mxwvideo.ui.fragment.login.LoginFragment;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class MeHomeFragment extends BaseFragment {

    @BindView(R.id.head_icon)
    ImageView headIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R.id.tv_fans_num)
    TextView tvFansNum;
    @BindView(R.id.card_publish)
    CardView cardPublish;
    @BindView(R.id.card_ticket)
    CardView cardTicket;
    @BindView(R.id.card_setting)
    CardView cardSetting;
    Unbinder unbinder;
    private boolean is_login;


    public static MeHomeFragment newInstance() {

        Bundle args = new Bundle();

        MeHomeFragment fragment = new MeHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Subscribe
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment_home, container, false);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        is_login = SPUtils.getInstance().getBoolean("is_login");
        initView(is_login);
        return view;
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
        initView(loginEvent.isLogin());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @OnClick({R.id.head_icon, R.id.tv_user_name, R.id.tv_follow_num, R.id.tv_fans_num, R.id.card_publish, R.id.card_ticket, R.id.card_setting})
    public void onViewClicked(View view) {
        Intent intent;
        is_login = SPUtils.getInstance().getBoolean("is_login");
        switch (view.getId()) {
            case R.id.head_icon:
                if (is_login) {
                    intent = new Intent(_mActivity, PersonInfoActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(_mActivity, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_user_name:
                if (is_login) {
                    intent = new Intent(_mActivity, PersonInfoActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(_mActivity, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_follow_num:
                break;
            case R.id.tv_fans_num:
                break;
            case R.id.card_publish:
                if (is_login){
                    intent = new Intent(_mActivity, MyReleaseActivity.class);
                }else {
                    intent = new Intent(_mActivity, LoginActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.card_ticket:
                break;
            case R.id.card_setting:
                intent = new Intent(_mActivity, SecurityActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void initView(boolean is_login) {
        if (is_login) {
            UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
            String user_name = userInfo.getUser_name();
            if (user_name != null) {
                tvUserName.setText(user_name);
                LogUtils.i(user_name);
            }
            String fans = userInfo.getFans();
            if (fans != null) {
                tvFansNum.setText(fans);
            }
            String follows = userInfo.getFollows();
            if (follows != null) {
                tvFollowNum.setText(follows);
            }
            String headimg = userInfo.getHeadimg();
            if (headimg != null) {
                Glide.with(_mActivity).load(headimg).into(headIcon);
            }
            String token = userInfo.getToken();
            if (token!=null){
                LogUtils.i(token);
            }
        }
    }
}
