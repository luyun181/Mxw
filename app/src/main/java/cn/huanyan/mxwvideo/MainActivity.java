package cn.huanyan.mxwvideo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.flyco.dialog.widget.ActionSheetDialog;
import com.werb.permissionschecker.PermissionChecker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.huanyan.mxwvideo.base.BaseMainFragment;
import cn.huanyan.mxwvideo.event.TabSelectedEvent;
import cn.huanyan.mxwvideo.ui.activity.pulish.PublishActivity;
import cn.huanyan.mxwvideo.ui.activity.pulish.VideoRecorderActivity;
import cn.huanyan.mxwvideo.ui.fragment.discover.DiscoverFragment;
import cn.huanyan.mxwvideo.ui.fragment.discover.child.DiscoverHomeFragment;
import cn.huanyan.mxwvideo.ui.fragment.follow.FollowFragment;
import cn.huanyan.mxwvideo.ui.fragment.follow.child.FollowHomeFragment;
import cn.huanyan.mxwvideo.ui.fragment.home.HomeFragment;
import cn.huanyan.mxwvideo.ui.fragment.home.child.HomeListFragment;
import cn.huanyan.mxwvideo.ui.fragment.me.MeFragment;
import cn.huanyan.mxwvideo.ui.fragment.me.child.MeHomeFragment;
import cn.huanyan.mxwvideo.ui.fragment.publish.PublishFragment;
import cn.huanyan.mxwvideo.ui.fragment.publish.child.PublishHomeFragment;
import cn.huanyan.mxwvideo.ui.view.BottomBar;
import cn.huanyan.mxwvideo.ui.view.BottomBarTab;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends SupportActivity implements BaseMainFragment.OnBackToFirstListener {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;
    private int REQUEST_CODE = 0x00;
    private int RESULT_CODE = 0x01;
    private Context mContext;
    private SupportFragment[] mFragments = new SupportFragment[5];

    private BottomBar mBottomBar;
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private PermissionChecker permissionChecker;
    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        mContext = this;
        if (savedInstanceState == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = FollowFragment.newInstance();
            mFragments[THIRD] = PublishFragment.newInstance();
            mFragments[FOURTH] = DiscoverFragment.newInstance();
            mFragments[FIFTH] = MeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(HomeFragment.class);
            mFragments[SECOND] = findFragment(FollowFragment.class);
            mFragments[THIRD] = findFragment(PublishFragment.class);
            mFragments[FOURTH] = findFragment(DiscoverFragment.class);
            mFragments[FIFTH] = findFragment(MeFragment.class);
        }

        initView();
        permissionChecker = new PermissionChecker(this); // initialize，must need

       /* // 可以监听该Activity下的所有Fragment的18个 生命周期方法
        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {

            @Override
            public void onFragmentSupportVisible(SupportFragment fragment) {
                LogUtils.aTag("MainActivity", "onFragmentSupportVisible--->" + fragment.getClass().getSimpleName());
            }
        });
*/
    }


    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_CODE) {
                mBottomBar.setCurrentItem(0);
            }
        }
    }

    private void initView() {
        mBottomBar = findViewById(R.id.bottomBar);

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.icon_home, getString(R.string.tab_home)))
                .addItem(new BottomBarTab(this, R.drawable.icon_follow, getString(R.string.tab_follow)))
                .addItem(new BottomBarTab(this, R.drawable.icon_publish, getString(R.string.tab_publish)))
                .addItem(new BottomBarTab(this, R.drawable.icon_discover, getString(R.string.tab_discover)))
                .addItem(new BottomBarTab(this, R.drawable.icon_me, getString(R.string.tab_me)));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                if (position == 2) {
                  /*  if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                        permissionChecker.requestPermissions();
                    } else {
                        startVideo();
                    }*/
                    Intent intent = new Intent(mContext, PublishActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                if (position == 2) {
                  /*  if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                        permissionChecker.requestPermissions();
                    } else {
                        startVideo();
                    }*/
                    Intent intent = new Intent(mContext, PublishActivity.class);
                    startActivityForResult(intent,REQUEST_CODE);
                }
                SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof HomeFragment) {
                        currentFragment.popToChild(HomeListFragment.class, false);
                    } else if (currentFragment instanceof FollowFragment) {
                        currentFragment.popToChild(FollowHomeFragment.class, false);
                    } else if (currentFragment instanceof PublishFragment) {
                        currentFragment.popToChild(PublishHomeFragment.class, false);
                    } else if (currentFragment instanceof DiscoverFragment) {
                        currentFragment.popToChild(DiscoverHomeFragment.class, false);
                    } else if (currentFragment instanceof MeFragment) {
                        currentFragment.popToChild(MeHomeFragment.class, false);
                    }
                    return;
                }


                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    EventBus.getDefault().post(new TabSelectedEvent(position));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {
                        startVideo();
                } else {
                    permissionChecker.showDialog();
                }
                break;
        }
    }

    private void startVideo(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,VideoRecorderActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }
}
