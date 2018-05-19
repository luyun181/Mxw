package cn.huanyan.mxwvideo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.entity.FollowInfoBean;
import cn.huanyan.mxwvideo.entity.IndexBean;

public class FollowDetailAdapter extends BaseQuickAdapter<FollowInfoBean.DataBeanX.ListsBean.DataBean, BaseViewHolder> {
    public FollowDetailAdapter(int layoutResId, @Nullable List<FollowInfoBean.DataBeanX.ListsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FollowInfoBean.DataBeanX.ListsBean.DataBean item) {
        String title = item.getTitle();
        if (title != null) {
            helper.setText(R.id.tv_item_title, title);
        }
        String thumb = item.getThumb();
        if (thumb != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.mipmap.home_place_holder)
                    .error(R.mipmap.home_place_holder)
//                    .centerCrop()
//                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(mContext)
                    .load(thumb)
                    .apply(requestOptions)
                    .into((ImageView) helper.getView(R.id.iv_item_top));
        }
        String user_name = item.getUser_name();
        if (user_name != null) {
            helper.setText(R.id.tv_item_name, user_name);
        }
        String headimg = item.getHeadimg();
        if (headimg != null) {
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(mContext).load(headimg).apply(requestOptions).into((ImageView) helper.getView(R.id.iv_item_head));
        }

        String video_like = item.getVideo_like();
        if (video_like != null) {
            helper.setText(R.id.tv_heart_num, video_like + "");
        }

        String video_commit = item.getVideo_commit();
        if (video_commit != null) {
            helper.setText(R.id.tv_msg_num, video_commit + "");
        }

    }
}


