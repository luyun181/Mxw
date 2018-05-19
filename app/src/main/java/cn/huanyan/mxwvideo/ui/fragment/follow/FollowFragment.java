package cn.huanyan.mxwvideo.ui.fragment.follow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.base.BaseMainFragment;
import cn.huanyan.mxwvideo.ui.fragment.follow.child.FollowHomeFragment;

public class FollowFragment extends BaseMainFragment {
    public static FollowFragment newInstance() {
        Bundle args = new Bundle();
        FollowFragment fragment = new FollowFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.follow_fragment, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.layout_follow, FollowHomeFragment.newInstance());
        }
    }
}
