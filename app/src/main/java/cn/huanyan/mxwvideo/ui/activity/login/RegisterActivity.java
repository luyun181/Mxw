package cn.huanyan.mxwvideo.ui.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.entity.EquipmentBean;
import cn.huanyan.mxwvideo.entity.CommonBean;
import cn.huanyan.mxwvideo.interf.ApiService;
import cn.huanyan.mxwvideo.utils.AppInfoUtils;
import cn.huanyan.mxwvideo.utils.GsonUtils;
import cn.huanyan.mxwvideo.utils.RetrofitUtils;
import cn.huanyan.mxwvideo.utils.ValidateCodeView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_phone_num)
    EditText tvPhoneNum;
    @BindView(R.id.tv_enter_code)
    EditText tvEnterCode;
    @BindView(R.id.tv_next)
    CardView tvNext;
    @BindView(R.id.validateCodeView)
    ValidateCodeView validateCodeView;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.tv_password_again)
    EditText tvPasswordAgain;
    private ApiService apiService;
    private EquipmentBean instance;
    private String phone;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    validateCodeView.start();
                    ToastUtils.showShort(R.string.sended);
                    break;
                case 2:
                    ToastUtils.showShort(R.string.regist_success);
                    RegisterActivity.this.finish();
                    break;
            }
            return false;
        }
    });
    private String code;
    private String password;
    private String passwordAgain;
    private String encodePassword;
    private String encodePassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        validateCodeView.onCreate();
        initView();
    }

    private void initView() {
        ivBack.setImageResource(R.mipmap.icon_arrow_left);
        toolbarTitle.setText(R.string.register_account);
        apiService = RetrofitUtils.getInstance(this).create(ApiService.class);
        instance = AppInfoUtils.getInstance();
    }

    @OnClick({R.id.iv_back, R.id.tv_enter_code, R.id.tv_next, R.id.validateCodeView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_enter_code:
                break;
            case R.id.tv_next:
                phone = tvPhoneNum.getText().toString();
                code = tvEnterCode.getText().toString();
                password = tvPassword.getText().toString();
                passwordAgain = tvPasswordAgain.getText().toString();
                if (StringUtils.isEmpty(password)){
                    ToastUtils.showShort("密码不能为空");
                    return;
                }
                if (!passwordAgain.equals(password)) {
                    ToastUtils.showShort("两次密码不同");
                    return;
                }
                encodePassword = encodePassword(password);
                encodePassword1 = encodePassword(passwordAgain);
                String verifyContent = getVerifyContent();
                registerInfo(verifyContent);
                LogUtils.i(verifyContent);
                break;
            case R.id.validateCodeView:
                phone = tvPhoneNum.getText().toString();
                boolean mobileExact = RegexUtils.isMobileExact(phone);
                if (!mobileExact) {
                    ToastUtils.showShort(R.string.right_num);
                    return;
                }
                getVerification(phone);
                break;
        }
    }

    private String getVerifyContent() {
        VerificationBean verificationBean = new VerificationBean(phone, code, encodePassword,encodePassword1, instance);
        String content = GsonUtils.toJson(verificationBean, true);
        String s = EncodeUtils.base64Encode2String(content.getBytes());
        return s;
    }

    private void getVerification(String phone) {
        Observable<CommonBean> orderDetail = apiService.getCode(phone);
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
                        if (bean.getCode() == 200) {
                            Message message = new Message();
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
//                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

//                        dialog.dismiss();
                    }
                });

    }

    private String encodePassword(String pwd) {
        if (pwd!=null) {
            String s = pwd + "ihuanyan";
            String s1 = EncryptUtils.encryptMD5ToString(s);
            return s1;
        }
        return "";
    }

    private void registerInfo(String content) {
        Observable<CommonBean> orderDetail = apiService.getRegisterResult(content);
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
                        if (bean.getCode() == 200) {
                            Message message = new Message();
                            message.what = 2;
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
//                        dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

//                        dialog.dismiss();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        validateCodeView.onDestroy();
        super.onDestroy();
    }


}
