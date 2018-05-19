package cn.huanyan.mxwvideo.ui.activity.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.entity.UserInfo;
import cn.huanyan.mxwvideo.event.LoginEvent;

public class PersonInfoActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_second_title)
    TextView tvSecondTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_head)
    RelativeLayout layoutHead;
    @BindView(R.id.layout_name)
    RelativeLayout layoutName;
    @BindView(R.id.layout_sex)
    RelativeLayout layoutSex;
    @BindView(R.id.layout_birth)
    RelativeLayout layoutBirth;
    @BindView(R.id.layout_sign)
    RelativeLayout layoutSign;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birth)
    TextView tvBirth;

    private Context mContext;
    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mContext = this;
        initToolbar();
    }

    private void initToolbar() {
        ivBack.setImageResource(R.mipmap.icon_arrow_left);
        toolbarTitle.setText(R.string.my_info);
//        tvSecondTitle.setText(R.string.save);
        boolean is_login = SPUtils.getInstance().getBoolean("is_login");
        initView(is_login);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent loginEvent) {
        initView(loginEvent.isLogin());
    }

    private void initView(boolean login) {
        if (login) {
            UserInfo userInfo = DataSupport.find(UserInfo.class, 1);
            if (userInfo != null) {
                String headimg = userInfo.getHeadimg();
                if (headimg != null) {
                    Glide.with(this)
                            .load(headimg)
                            .into(ivHead);
                }
                String user_name = userInfo.getUser_name();
                if (user_name != null) {
                    tvUserName.setText(user_name);
                }
                String birth = userInfo.getBirth();
                if (birth != null) {
                    tvBirth.setText(birth);
                }
                int sex = userInfo.getSex();
                switch (sex) {
                    case 0:
                        tvSex.setText("未选择");
                        break;
                    case 1:
                        tvSex.setText("男");
                        break;
                    case 3:
                        tvSex.setText("女");
                        break;
                }
                String sign = userInfo.getSign();

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_back, R.id.layout_head, R.id.layout_name, R.id.layout_sex, R.id.layout_birth, R.id.layout_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.layout_head:
                break;
            case R.id.layout_name:
                Intent intent = new Intent(this, ModifyNameActivity.class);
//                intent.putExtra("user_name",)
                startActivity(intent);

                break;
            case R.id.layout_sex:
                break;
            case R.id.layout_birth:
                break;
            case R.id.layout_sign:
                break;
        }
    }

    private void ActionSheetDialog() {
        final String[] stringItems = {"接收消息并提醒", "接收消息但不提醒", "收进群助手且不提醒", "屏蔽群消息"};
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, stringItems, null);
        dialog.title("选择群消息提醒方式\r\n(该群在电脑的设置:接收消息并提醒)")//
                .titleTextSize_SP(14.5f)//
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//                T.showShort(mContext, stringItems[position]);
                dialog.dismiss();
            }
        });
    }

}
