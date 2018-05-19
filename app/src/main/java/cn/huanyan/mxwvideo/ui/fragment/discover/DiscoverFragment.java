package cn.huanyan.mxwvideo.ui.fragment.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.base.BaseMainFragment;
import cn.huanyan.mxwvideo.ui.fragment.discover.child.DiscoverHomeFragment;

public class DiscoverFragment extends BaseMainFragment {
    public static DiscoverFragment newInstance() {
        Bundle args = new Bundle();
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.discover_container, DiscoverHomeFragment.newInstance());
        }
    }
}
