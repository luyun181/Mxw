package cn.huanyan.mxwvideo.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
       /* RequestOptions options = new RequestOptions()
                .override(10, 10);*/
        Glide.with(context)
                .load(path)
//                .apply(options)
                .into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {

        return super.createImageView(context);
    }
}
