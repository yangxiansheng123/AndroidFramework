package com.example.administrator.oper.ui.activity.fiance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.base.BaseToolbarActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.fragmentfactorys.FragmentBookkeepingFactory;
import com.example.administrator.oper.ui.widget.TabPageIndicator;
import com.utils.DisplayUtil;
import com.utils.ToolbarHelper;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：BookkeepingActivity
 */
public class BookkeepingActivity extends BaseToolbarActivity {


    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TabPageIndicator indicatorBookkeeping;
    protected ViewPager vpBookkeeping;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_bookkeeping);
        initView();

        BasePagerAdapter adapter = new BasePagerAdapter(this.getSupportFragmentManager());
        vpBookkeeping.setAdapter(adapter);
        indicatorBookkeeping.setViewPager(vpBookkeeping);
        setTabPagerIndicator();

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bookkeeping;
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle(getString(R.string.title_activity_bookkeeping));
        Toolbar toolbar = toolbarHelper.getToolbar();
        // 显示导航按钮
        toolbar.setNavigationIcon(R.mipmap.left_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        indicatorBookkeeping = (TabPageIndicator) findViewById(R.id.indicator_bookkeeping);
        vpBookkeeping = (ViewPager) findViewById(R.id.vp_bookkeeping);

    }


    //导航栏设置
    private void setTabPagerIndicator() {

        indicatorBookkeeping.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
        // 设置模式，一定要先设置模式
//        indicator.setDividerColor(Color.parseColor("#00bbcf"));
// 设置分割线的颜色
//        indicator.setDividerPadding(DisplayUtil.dip2px(this, 0));
        // 设置底部导航线的颜色
        indicatorBookkeeping.setIndicatorColor(Color.parseColor("#fbd415"));
        // 设置底部导航线的高度
        indicatorBookkeeping.setIndicatorHeight(DisplayUtil.dip2px(this, 2));
        // 设置tab标题选中的颜色
        indicatorBookkeeping.setTextColorSelected(Color.parseColor("#fdd23e"));
        // 设置tab标题未被选中的颜色
        indicatorBookkeeping.setTextColor(Color.parseColor("#000000"));

        // 设置字体大小
        indicatorBookkeeping.setTextSize((int) DisplayUtil.sp2px(this, 18));
        // 设置标题栏和viewPager的分割线
        indicatorBookkeeping.setUnderlineHeight(DisplayUtil.dip2px(this, 0));
    }

    //适配器 管理fragment
    class BasePagerAdapter extends FragmentPagerAdapter {
        String[] titles;

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles = getResources().getStringArray(R.array.bookkeeping);

        }

        @Override
        public Fragment getItem(int position) {
            return FragmentBookkeepingFactory.createForNoExpand(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
