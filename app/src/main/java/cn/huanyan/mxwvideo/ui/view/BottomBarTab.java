package cn.huanyan.mxwvideo.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.huanyan.mxwvideo.R;


/**
 * Created by Bing on 2018/3/8.
 */

public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView mTvTitle;
    private Context mContext;
    private int mTabPosition = -1;

    public BottomBarTab(Context context, @DrawableRes int icon, CharSequence title) {
        this(context, null, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon, CharSequence title) {
        this(context, attrs, 0, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, CharSequence title) {
        super(context, attrs, defStyleAttr);
        init(context, icon, title);
    }

    private void init(Context context, int icon, CharSequence title) {


        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        LinearLayout lLContainer = new LinearLayout(context);
        lLContainer.setOrientation(LinearLayout.VERTICAL);
        lLContainer.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams paramsContainer = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsContainer.gravity = Gravity.CENTER;
        lLContainer.setLayoutParams(paramsContainer);

        mIcon = new ImageView(context);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 27, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);

        mIcon.setColorFilter(ContextCompat.getColor(context, R.color.bottom_bar_un));
        lLContainer.addView(mIcon);

        mTvTitle = new TextView(context);
        mTvTitle.setText(title);
        LinearLayout.LayoutParams paramsTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTv.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        mTvTitle.setTextSize(10);
        mTvTitle.setTextColor(ContextCompat.getColor(context, R.color.bottom_bar_un));
        mTvTitle.setLayoutParams(paramsTv);
        lLContainer.addView(mTvTitle);

        addView(lLContainer);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.bottom_bar));
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.bottom_bar));
        } else {
            mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.bottom_bar_un));
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.tab_unselect));
        }
    }

    public void setTabPosition(int position) {
        mTabPosition = position;
        if (position == 0) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }
}
