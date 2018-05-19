package cn.huanyan.mxwvideo.ui.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;

import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.entity.UserInfo;

public class SecurityActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.card_change_pwd)
    CardView cardChangePwd;
    @BindView(R.id.card_phone_num)
    CardView cardPhoneNum;
    @BindView(R.id.card_exit)
    CardView cardExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ivBack.setImageResource(R.mipmap.icon_arrow_left);
        toolbarTitle.setText(getResources().getString(R.string.card_security_setting));
    }

    @OnClick({R.id.card_change_pwd, R.id.card_phone_num, R.id.card_exit, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_change_pwd:
                break;
            case R.id.card_phone_num:
                break;
            case R.id.card_exit:
                SPUtils.getInstance().clear();
                DataSupport.delete(UserInfo.class, 1);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

}
