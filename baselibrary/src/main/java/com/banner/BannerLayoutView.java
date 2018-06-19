package com.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxl.dropdownmenue.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * banner轮播
 */
public class BannerLayoutView extends RelativeLayout {

    private Context context = null;

    private ViewPager pager;
    // 指示器容器
    private LinearLayout indicatorContainer;

    private Drawable unSelectedDrawable;
    private Drawable selectedDrawable;

    private int WHAT_AUTO_PLAY = 1000;

    private boolean isAutoPlay = true;

    private int itemCount;

    private int selectedIndicatorColor = 0xffff0000;
    private int unSelectedIndicatorColor = 0x88888888;

    private Shape indicatorShape = Shape.oval;
    private int selectedIndicatorHeight = 6;
    private int selectedIndicatorWidth = 6;
    private int unSelectedIndicatorHeight = 6;
    private int unSelectedIndicatorWidth = 6;

    private Position indicatorPosition = Position.centerBottom;
    private int autoPlayDuration = 4000;
    private int scrollDuration = 900;

    private int indicatorSpace = 3;
    private int indicatorMargin = 10;

    private int currentPosition;

    private int index;

    private int index2 = 0;
    private boolean flag1;

    private ImageLoader imageLoader;
    /**
     * 判断轮播是图片or视频
     */
    private boolean isVideo;

    private enum Shape {
        rect, oval
    }

    private enum Position {
        centerBottom, rightBottom, leftBottom, centerTop, rightTop, leftTop
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoPlay();
    }

    private OnBannerItemClickListener onBannerItemClickListener;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT_AUTO_PLAY) {
                if (pager != null && isAutoPlay) {
                    pager.setCurrentItem(pager.getCurrentItem() + 1, true);
                    handler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY,
                            autoPlayDuration);
                }
            }
            return false;
        }
    });

    public BannerLayoutView(Context context) {
        this(context, null);
    }

    public BannerLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerLayoutView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {

        TypedArray array = getContext().obtainStyledAttributes(attrs,
                R.styleable.BannerLayoutStyle, defStyle, 0);
        selectedIndicatorColor = array.getColor(
                R.styleable.BannerLayoutStyle_selectedIndicatorColor,
                selectedIndicatorColor);
        unSelectedIndicatorColor = array.getColor(
                R.styleable.BannerLayoutStyle_unSelectedIndicatorColor,
                unSelectedIndicatorColor);
        isVideo = array.getBoolean(R.styleable.BannerLayoutStyle_isVideo,false);

        int shape = array.getInt(R.styleable.BannerLayoutStyle_indicatorShape,
                Shape.oval.ordinal());
        for (Shape shape1 : Shape.values()) {
            if (shape1.ordinal() == shape) {
                indicatorShape = shape1;
                break;
            }
        }
        selectedIndicatorHeight = (int) array.getDimension(
                R.styleable.BannerLayoutStyle_selectedIndicatorHeight,
                selectedIndicatorHeight);
        selectedIndicatorWidth = (int) array.getDimension(
                R.styleable.BannerLayoutStyle_selectedIndicatorWidth,
                selectedIndicatorWidth);
        unSelectedIndicatorHeight = (int) array.getDimension(
                R.styleable.BannerLayoutStyle_unSelectedIndicatorHeight,
                unSelectedIndicatorHeight);
        unSelectedIndicatorWidth = (int) array.getDimension(
                R.styleable.BannerLayoutStyle_unSelectedIndicatorWidth,
                unSelectedIndicatorWidth);

        int position = array.getInt(
                R.styleable.BannerLayoutStyle_indicatorPosition,
                Position.centerBottom.ordinal());
        for (Position position1 : Position.values()) {
            if (position == position1.ordinal()) {
                indicatorPosition = position1;
            }
        }
        indicatorSpace = (int) array.getDimension(
                R.styleable.BannerLayoutStyle_indicatorSpace, indicatorSpace);
        indicatorMargin = (int) array.getDimension(
                R.styleable.BannerLayoutStyle_indicatorMargin, indicatorMargin);
        autoPlayDuration = array.getInt(
                R.styleable.BannerLayoutStyle_autoPlayDuration,
                autoPlayDuration);
        scrollDuration = array.getInt(
                R.styleable.BannerLayoutStyle_scrollDuration, scrollDuration);
        isAutoPlay = array.getBoolean(R.styleable.BannerLayoutStyle_isAutoPlay,
                isAutoPlay);
        array.recycle();

        // 绘制未选中状态图形
        LayerDrawable unSelectedLayerDrawable;
        LayerDrawable selectedLayerDrawable;
        GradientDrawable unSelectedGradientDrawable;
        unSelectedGradientDrawable = new GradientDrawable();

        // 绘制选中状态图形
        GradientDrawable selectedGradientDrawable;
        selectedGradientDrawable = new GradientDrawable();
        switch (indicatorShape) {
            case rect:
                unSelectedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                selectedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                break;
            case oval:
                unSelectedGradientDrawable.setShape(GradientDrawable.OVAL);
                selectedGradientDrawable.setShape(GradientDrawable.OVAL);
                break;
        }
        unSelectedGradientDrawable.setColor(unSelectedIndicatorColor);
        unSelectedGradientDrawable.setSize(unSelectedIndicatorWidth,
                unSelectedIndicatorHeight);
        unSelectedLayerDrawable = new LayerDrawable(
                new Drawable[]{unSelectedGradientDrawable});
        unSelectedDrawable = unSelectedLayerDrawable;

        selectedGradientDrawable.setColor(selectedIndicatorColor);
        selectedGradientDrawable.setSize(selectedIndicatorWidth,
                selectedIndicatorHeight);
        selectedLayerDrawable = new LayerDrawable(
                new Drawable[]{selectedGradientDrawable});
        selectedDrawable = selectedLayerDrawable;

    }

    // 添加网络图片路径
    public void setViewUrls(List<String> urls) {
        List<View> views = new ArrayList<View>();
        itemCount = urls.size();
        // 主要是解决当item为小于3个的时候滑动有问题，这里将其拼凑成3个以上
        if (itemCount < 1) {// 当item个数0
            // throw new IllegalStateException("item count not equal zero");
        } else if (itemCount < 2) { // 当item个数为1
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(0), 0));
        } else if (itemCount < 3) {// 当item个数为2
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(1), 1));
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(1), 1));
        } else {
            for (int i = 0; i < urls.size(); i++) {
                views.add(getImageView(urls.get(i), i));
            }
        }
        setViews(views);
    }

    private ImageView getImageView(String url, final int position) {
        ImageView imageView = new ImageView(getContext());
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBannerItemClickListener != null) {
                    onBannerItemClickListener.onItemClick(position);
                }
            }
        });
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageLoader.displayImage(url, imageView);
        return imageView;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public ViewPager getPager() {
        if (pager != null) {
            return pager;
        }
        return null;
    }

    // 添加任意View视图
    public void setViews(final List<View> views) {
        // 初始化pager
        pager = new ViewPager(getContext());
        // 添加viewpager到SliderLayout
        addView(pager);
        setSliderTransformDuration(scrollDuration);
        // 初始化indicatorContainer
        indicatorContainer = new LinearLayout(getContext());
        indicatorContainer.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        switch (indicatorPosition) {
            case centerBottom:
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case centerTop:
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
            case leftBottom:
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case leftTop:
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
            case rightBottom:
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case rightTop:
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                break;
        }
        // 设置margin
        params.setMargins(indicatorMargin, indicatorMargin, indicatorMargin,
                indicatorMargin);
        // 添加指示器容器布局到SliderLayout
        addView(indicatorContainer, params);

        // 初始化指示器，并添加到指示器容器布局
        for (int i = 0; i < itemCount; i++) {
            ImageView indicator = new ImageView(getContext());
            indicator.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            indicator.setPadding(indicatorSpace, indicatorSpace,
                    indicatorSpace, indicatorSpace);
            indicator.setImageDrawable(unSelectedDrawable);
            indicatorContainer.addView(indicator);
        }
        if (isVideo) {
            addPlayView("video");
        }else {
            addPlayView("");
        }


        LoopPagerAdapter pagerAdapter = new LoopPagerAdapter(views);
        pager.setAdapter(pagerAdapter);
        // 设置当前item到Integer.MAX_VALUE中间的一个值，看起来像无论是往前滑还是往后滑都是ok的
        // 如果不设置，用户往左边滑动的时候已经划不动了
        int targetItemPosition = 0;
        if (itemCount >= 1) {
            targetItemPosition = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
                    % itemCount;
            currentPosition = targetItemPosition;
        }
        pager.setCurrentItem(targetItemPosition);
        int currentIndicator = 0;
        if (itemCount >= 1) {
            currentIndicator = targetItemPosition % itemCount;
        }
        switchIndicator(currentIndicator);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                int currentIndicator = 0;
                if (itemCount >= 1) {
                    currentIndicator = position % itemCount;
                }
                switchIndicator(currentIndicator);
            }
        });
        if (isAutoPlay) {
            startAutoPlay();
        }

    }

    /**
     * 添加播放按钮
     */
    private void addPlayView(String isVideo) {
        //添加播放按钮
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.mipmap.no_information);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutParams lp = new LayoutParams(

                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);

        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        imageView.setLayoutParams(lp);
        if (isVideo.equals("video")) {
            addView(imageView);
        }else {
            removeView(imageView);
        }
    }

    public void pagerOnPageChangeListener(final Context context,
                                          final String url, final Integer sizes) {

        pager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                index = 0;
                if (itemCount >= 1) {
                    index = position % itemCount;
                }
                switchIndicator(index);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                index = 0;
                if (itemCount >= 1) {
                    index = arg0 % itemCount;
                }
                if (arg2 > index2) {
                    flag1 = true;
                } else {
                    flag1 = false;
                }
                index2 = arg2;
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                if (index == sizes - 1) {
                    if (flag1) {
//						Intent startImageTextInent = new Intent(context,
//								ImageTextDetailActivity.class);
//						startImageTextInent.putExtra("image_text_address", url);
//						context.startActivity(startImageTextInent);
                    }
                }
            }
        });
        // pager.setOnTouchListener(new OnTouchListener() {
        // float startX;
        // float startY;//没有用到
        // float endX;
        // float endY;//没有用到
        // @Override
        // public boolean onTouch(View v, MotionEvent event) {
        // Log.i("TAG","进入了触摸");
        // switch (event.getAction()){
        // case MotionEvent.ACTION_DOWN:
        // startX=event.getX();
        // startY=event.getY();
        // break;
        // case MotionEvent.ACTION_UP:
        // endX=event.getX();
        // endY=event.getY();
        // WindowManager windowManager=
        // (WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //
        // //获取屏幕的宽度
        // Point size = new Point();
        // windowManager.getDefaultDisplay().getSize(size);
        // int width=size.x;
        //
        // //首先要确定的是，是否到了最后一页，然后判断是否向左滑动，并且滑动距离是否符合，我这里的判断距离是屏幕宽度的4分之一（这里可以适当控制）
        // if(c_item==(itemCount-1)&&startX-endX>=(width/4)){
        // Log.i("TAG","进入了触摸");
        // Intent startImageTextInent = new Intent(context,
        // ImageTextDetailActivity.class);
        // startImageTextInent.putExtra("image_text_address",
        // url);
        // context.startActivity(startImageTextInent);
        // }
        // break;
        // }
        // return false;
        // }
        // });
    }

    public void setSliderTransformDuration(int duration) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(
                    pager.getContext(), null, duration);
            mScroller.set(pager, scroller);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 开始自动轮播
     */
    private void startAutoPlay() {
        stopAutoPlay(); // 避免重复消息
        if (isAutoPlay) {
            handler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
        }
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);

        if (visibility == VISIBLE) {
            startAutoPlay();
        } else {
            stopAutoPlay();
        }
    }

    /**
     * 停止自动轮播
     */
    private void stopAutoPlay() {
        if (pager != null) {
            pager.setCurrentItem(pager.getCurrentItem(), false);
        }
        if (isAutoPlay) {
            handler.removeMessages(WHAT_AUTO_PLAY);
            if (pager != null) {
                pager.setCurrentItem(pager.getCurrentItem(), false);
            }
        }
    }

    /**
     * @param autoPlay 是否自动轮播
     */
    public void setAutoPlay(boolean autoPlay) {
        isAutoPlay = autoPlay;
    }

    private int mLastXIntercept;
    private int mLastYIntercept;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);
                stopAutoPlay();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                // 如果是左右滑动
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                getParent().requestDisallowInterceptTouchEvent(true);
                startAutoPlay();
                break;
            }
        }
        mLastXIntercept = x;
        mLastYIntercept = y;
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 切换指示器状态
     *
     * @param currentPosition 当前位置
     */
    private void switchIndicator(int currentPosition) {
        for (int i = 0; i < indicatorContainer.getChildCount(); i++) {
            ((ImageView) indicatorContainer.getChildAt(i))
                    .setImageDrawable(i == currentPosition ? selectedDrawable
                            : unSelectedDrawable);
        }
    }

    public void setOnBannerItemClickListener(
            OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    public interface OnBannerItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        currentPosition = savedState.currentPosition;
        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPosition = currentPosition;
        return savedState;
    }

    private static class SavedState extends BaseSavedState {
        int currentPosition;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPosition = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPosition);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    private class LoopPagerAdapter extends PagerAdapter {
        private List<View> views;

        LoopPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            // Integer.MAX_VALUE = 2147483647
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (views.size() > 0) {
                // position % view.size()是指虚拟的position会在[0，view.size()）之间循环
                View view = views.get(position % views.size());
                if (container.equals(view.getParent())) {
                    container.removeView(view);
                }
                container.addView(view);
                return view;
            }
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }

    public class FixedSpeedScroller extends Scroller {

        private int mDuration = 1000;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator,
                                  int duration) {
            this(context, interpolator);
            mDuration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy,
                                int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

    // public interface ImageLoader extends Serializable {
    // void displayImage(Context context, String path, ImageView imageView);
    // }
}
