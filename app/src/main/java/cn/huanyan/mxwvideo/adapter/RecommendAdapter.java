package cn.huanyan.mxwvideo.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.huanyan.mxwvideo.R;
import cn.huanyan.mxwvideo.entity.RecommendBean;

public class RecommendAdapter extends BaseQuickAdapter<RecommendBean.DataBean, BaseViewHolder> {
    public RecommendAdapter(int layoutResId, @Nullable List<RecommendBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBean.DataBean item) {
        String headimg = item.getHeadimg();
        if (headimg != null) {
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(mContext).load(headimg).apply(requestOptions).into((ImageView) helper.getView(R.id.iv_head_icon));
        }
        String user_name = item.getUser_name();
        if (user_name != null) {
            helper.setText(R.id.tv_name, user_name);
        }
        String signature = item.getSignature();
        if (signature != null) {
            helper.setText(R.id.tv_content, signature);
        }
        String fans = item.getFans();
        helper.setText(R.id.tv_fans_num, "粉丝" + fans);

        List<String> img = item.getImg();
        RecyclerView view = helper.getView(R.id.recycler_content);
        view.setLayoutManager(new GridLayoutManager(mContext, 3));
        view.setAdapter(new ImageContentAdapter(R.layout.item_select_pic, img));
        int status = item.getStatus();
        TextView view1 = helper.getView(R.id.tv_btn_follow);
        helper.addOnClickListener(R.id.tv_btn_follow);
        switch (status) {
            case 10:
                helper.setText(R.id.tv_btn_follow, "已关注");
                helper.setBackgroundColor(R.id.tv_btn_follow, mContext.getResources().getColor(R.color.tab_color_false));
//                view1.setClickable(false);
                break;
            case 20:
                helper.setText(R.id.tv_btn_follow, R.string.add_follow);
                helper.setBackgroundColor(R.id.tv_btn_follow, mContext.getResources().getColor(R.color.bottom_bar));
                break;
        }
    }
}
