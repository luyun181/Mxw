package cn.huanyan.mxwvideo.ui.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.entity.EquipmentBean;
import cn.huanyan.mxwvideo.entity.ModifyBean;
import cn.huanyan.mxwvideo.entity.ModifyNamePostBean;
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

public class ModifyNameActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_name)
    EditText edName;
    private EquipmentBean instance;

    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_name);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        ivBack.setImageResource(R.mipmap.icon_arrow_left);
        toolbarTitle.setText(R.string.modify_name);
        tvSecondTitle.setText(R.string.save);
        instance = AppInfoUtils.getInstance();

    }

    @OnClick({R.id.iv_back, R.id.tv_second_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_second_title:
                String name = edName.getText().toString();
                if (name != null) {
                    ModifyName(name);
                }
                break;
        }
    }

    private void ModifyName(final String name) {
        final UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
        String token = "";
        if (userInfo != null) {
            token = userInfo.getToken();
        }
        String verifyContent = getVerifyContent(name);
        ApiService apiService = RetrofitUtils.getInstance(this).create(ApiService.class);
        Observable<ModifyBean> orderDetail = apiService.getName(token, verifyContent);
        orderDetail.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyBean>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
//                        dialog.show();
                        LogUtils.i("first");
                    }

                    @Override
                    public void onNext(ModifyBean bean) {
                        if (bean.getCode() == 200) {
                            ToastUtils.showShort("修改成功");
                            userInfo.setUser_name(name);
                            userInfo.update(1);
                            EventBus.getDefault().post(new LoginEvent(true));
                            ModifyNameActivity.this.finish();
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
                        LogUtils.i("onComplete");
//                        dialog.dismiss();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private String getVerifyContent(String name) {
        ModifyNamePostBean modifyNamePostBean = new ModifyNamePostBean(name, instance);
        String content = GsonUtils.toJson(modifyNamePostBean, true);
        String s = EncodeUtils.base64Encode2String(content.getBytes());
        return s;
    }
}
