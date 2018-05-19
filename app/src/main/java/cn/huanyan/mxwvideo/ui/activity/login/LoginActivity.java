package cn.huanyan.mxwvideo.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.entity.EquipmentBean;
import cn.huanyan.mxwvideo.entity.LoginBean;
import cn.huanyan.mxwvideo.entity.LoginPostBean;
import cn.huanyan.mxwvideo.entity.UserInfo;
import cn.huanyan.mxwvideo.event.LoginEvent;
import cn.huanyan.mxwvideo.interf.ApiService;
import cn.huanyan.mxwvideo.utils.AppInfoUtils;
import cn.huanyan.mxwvideo.utils.GsonUtils;
import cn.huanyan.mxwvideo.utils.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportActivity;

public class LoginActivity extends SupportActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_phone_num)
    TextInputEditText tvPhoneNum;
    @BindView(R.id.tv_enter_password)
    TextInputEditText tvEnterPassword;
    @BindView(R.id.ck_choose)
    CheckBox ckChoose;
    @BindView(R.id.tv_allow)
    TextView tvAllow;
    @BindView(R.id.tv_login)
    CardView tvLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_btn_login)
    TextView tvBtnLogin;
    private String phone;
    private String password;
    private ApiService apiService;
    private EquipmentBean instance;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ToastUtils.showShort(R.string.login_success);
                    SPUtils.getInstance().put("is_login", true);
                    LoginActivity.this.finish();
                    break;
            }
            return false;
        }
    });

    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initData();
    }

    private void initView() {
        ivBack.setImageResource(R.mipmap.icon_close);
        toolbarTitle.setText(R.string.login);
        apiService = RetrofitUtils.getInstance(this).create(ApiService.class);
        instance = AppInfoUtils.getInstance();
    }

    private void initData() {

        EquipmentBean instance = AppInfoUtils.getInstance();
        String s = GsonUtils.toJson(instance, true);
        LogUtils.i(s);
    }

    @OnClick({R.id.iv_back, R.id.ck_choose, R.id.tv_allow, R.id.tv_register, R.id.tv_forget_password, R.id.tv_btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.ck_choose:
                break;
            case R.id.tv_allow:
                break;
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget_password:
                break;
            case R.id.tv_btn_login:
                if (!checkPhone()) {
                    return;
                }
                boolean checked = ckChoose.isChecked();
                if (!checked) {
                    ToastUtils.showShort("请查看并同意用户协议！");
                    return;
                }
                String verifyContent = getVerifyContent();
                login(verifyContent);
                LogUtils.i(verifyContent);
                break;
        }
    }

    private boolean checkPhone() {
        phone = tvPhoneNum.getText().toString();
        password = tvEnterPassword.getText().toString();
        boolean mobileExact = RegexUtils.isMobileExact(phone);
        if (StringUtils.isEmpty(phone) || !mobileExact) {
            ToastUtils.showLong(R.string.input_phone);
            return false;
        } else if (StringUtils.isEmpty(password)) {
            ToastUtils.showShort(R.string.input_password);
            return false;
        }
        return true;
    }

    private void login(String content) {
        Observable<LoginBean> orderDetail = apiService.getLogin(content);
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(LoginBean bean) {
                        if (bean.getCode() == 200) {
                            LoginBean.DataBean data = bean.getData();
                            String token = data.getToken();
                            LoginBean.DataBean.UserinfoBean userinfo = data.getUserinfo();
                            UserInfo userInfo = new UserInfo();
                            if (token != null) {
                                userInfo.setToken(token);
                            }
                            int user_id = userinfo.getUser_id();
                            userInfo.setUser_id(user_id);

                            String user_name = userinfo.getUser_name();
                            if (user_name != null) {
                                userInfo.setUser_name(user_name);
                            }
                            String fans = userinfo.getFans();
                            if (fans != null) {
                                userInfo.setFans(fans);
                            }
                            String follows = userinfo.getFollows();
                            if (follows != null) {
                                userInfo.setFollows(follows);
                            }
                            String headimg = userinfo.getHeadimg();
                            if (headimg != null) {
                                userInfo.setHeadimg(headimg);
                            }
                            String signature = userinfo.getSignature();
                            if (signature != null) {
                                userInfo.setSign(signature);
                            }
                            String birthday = userinfo.getBirthday();
                            if (birthday != null) {
                                userInfo.setBirth(birthday);
                            }
                            int gender = userinfo.getGender();
                            userInfo.setSex(gender);

                            userInfo.save();
                            EventBus.getDefault().post(new LoginEvent(true));
                            Message message = new Message();
                            message.obj = bean;
                            message.what = 1;
                            mHandler.sendMessage(message);
                        } else {
                            String msg = bean.getMsg();
                            if (msg != null) {
                                ToastUtils.showShort(msg);
                            }
                        }
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

    private String getVerifyContent() {
        LoginPostBean loginPostBean = new LoginPostBean(phone, encodePassword(password), instance);
        String content = GsonUtils.toJson(loginPostBean, true);
        String s = EncodeUtils.base64Encode2String(content.getBytes());
        return s;
    }

    private String encodePassword(String pwd) {
        if (pwd != null) {
            String s = pwd + "ihuanyan";
            String s1 = EncryptUtils.encryptMD5ToString(s);
            return s1;
        }
        return "";
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
