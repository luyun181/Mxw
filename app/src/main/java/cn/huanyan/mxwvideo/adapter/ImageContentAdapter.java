package cn.huanyan.mxwvideo.adapter;


import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import cn.huanyan.mxwvideo.R;


public class ImageContentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ImageContentAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public ImageContentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.iv_pic));
    }
}
