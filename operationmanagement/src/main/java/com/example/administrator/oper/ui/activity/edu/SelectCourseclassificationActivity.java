package com.example.administrator.oper.ui.activity.edu;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.AllCategoryAdapter;
import com.example.administrator.oper.adapter.edu.CategoryAdapter;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/16
 * 描述：SelectCourseclassificationActivity 添加班级-->选择课程分类
 */
public class SelectCourseclassificationActivity extends BaseActivity {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ListView mLvCategory;
    protected GridView mLvCllCategory;
    protected MultiStateView mMultiStateView;
    private CategoryAdapter cateroryAdapter;
    private AllCategoryAdapter allCateroryAdapter;
    private List<String> listData;
    private List<String> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_select_courseclassification);
        initView();

        mLvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                allCateroryAdapter.setListData(listData);
                mLvCllCategory.setAdapter(allCateroryAdapter);
                allCateroryAdapter.notifyDataSetChanged();
                cateroryAdapter.setSelectionPosition(position);
                cateroryAdapter.notifyDataSetChanged();
            }
        });

        mLvCllCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent mIntent = new Intent();
//                mIntent.putExtra("keyWord", listDataChild.get(position).getCat_name());
//                mIntent.setClass(AllCategoriesActivity.this, ArtForumAllTypeActivity.class);
//                AllCategoriesActivity.this.startActivity(mIntent);
//                Remember.putString("keyWord", listDataChild.get(position).getCat_name());
//                finish();
            }
        });
    }

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_select_class));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mLvCategory = (ListView) findViewById(R.id.lv_category);
        mLvCllCategory = (GridView) findViewById(R.id.lv_cllCategory);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);


        listData = new ArrayList<>();
        listDataChild = new ArrayList<>();
        cateroryAdapter = new CategoryAdapter(this);
        allCateroryAdapter = new AllCategoryAdapter(this);

        cateroryAdapter.setListData(listData);
        mLvCategory.setAdapter(cateroryAdapter);
        /**
         * 第二侧列表
         */
        allCateroryAdapter.setListData(listDataChild);

        for (int i = 0; i < 10; i++) {
            listData.add("pp");
        }
        for (int i = 0; i < 10; i++) {
            listDataChild.add("cc");
        }
        allCateroryAdapter.notifyDataSetChanged();
        allCateroryAdapter.notifyDataSetChanged();
    }
}
