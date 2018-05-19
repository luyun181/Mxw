package cn.huanyan.mxwvideo.adapter;


import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.UriUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import cn.huanyan.mxwvideo.R;


public class PublishPicAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {
    public PublishPicAdapter(int layoutResId, @Nullable List<LocalMedia> data) {
        super(layoutResId, data);
    }

    public PublishPicAdapter(@Nullable List<LocalMedia> data) {
        super(data);
    }

    public PublishPicAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalMedia item) {
        LogUtils.d("item"+item.getPath());
        Uri uri = Uri.parse(item.getPath());
        Glide.with(mContext).load(uri).into((ImageView) helper.getView(R.id.iv_pic));
    }


}
