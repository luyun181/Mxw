package cn.huanyan.mxwvideo.ui.fragment.publish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.base.BaseMainFragment;
import cn.huanyan.mxwvideo.ui.fragment.publish.child.PublishHomeFragment;

public class PublishFragment extends BaseMainFragment {
    public static PublishFragment newInstance() {
        Bundle args = new Bundle();
        PublishFragment fragment = new PublishFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.publish_fragment, container, false);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.publish_container, PublishHomeFragment.newInstance());
        }
    }
}
