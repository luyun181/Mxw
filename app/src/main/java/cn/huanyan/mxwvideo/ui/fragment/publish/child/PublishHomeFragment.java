package cn.huanyan.mxwvideo.ui.fragment.publish.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.huanyan.mxwvideo.MainActivity;
import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.base.BaseFragment;
import cn.huanyan.mxwvideo.event.TabSelectedEvent;


/**
 *
 */
public class PublishHomeFragment extends BaseFragment {


    public static PublishHomeFragment newInstance() {

        Bundle args = new Bundle();

        PublishHomeFragment fragment = new PublishHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.publish_fragment, container, false);
        EventBus.getDefault().register(this);
        return view;
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
    }
}
