package cn.huanyan.mxwvideo.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.huanyan.mxwvideo.MainActivity;
import cn.huanyan.mxwvideo.event.LoginEvent;


public class SplashActivity extends AppCompatActivity {
    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);*/
//        setContentView(R.layout.activity_splash);
        EventBus.getDefault().register(this);
        boolean is_login = SPUtils.getInstance().getBoolean("is_login");
        if (is_login){
            EventBus.getDefault().post(new LoginEvent(true));
        }
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
//                startActivity(intent);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();

            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
