package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.daily.AddStudentsSelectTypeAdapter;
import com.example.administrator.oper.adapter.edu.AddContractPackageSelectTypeAdapter;
import com.example.administrator.oper.ui.activity.daily.AddStudentsSelectTypeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/15
 * 描述：CotractPackageSelectCategoryActivity
 */
public class CotractPackageSelectCategoryActivity extends BaseActivity {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ListView mLvShowTypes;
    private AddContractPackageSelectTypeAdapter mTypeAdapter;
    private List<String> mListData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_cotract_package_select_category);
        initView();
        initData();
        mLvShowTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent();
                intent.putExtra("type", mListData.get(position));
                setResult(5100, intent);
                finish();
            }
        });

    }

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_cotract_package_select_category));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mLvShowTypes = (ListView) findViewById(R.id.lv_showTypes);
        mTypeAdapter = new AddContractPackageSelectTypeAdapter(CotractPackageSelectCategoryActivity.this);

    }

    /**
     * 获取数据
     */
    private void initData() {
        mListData.add("合同");
        mListData.add("合同");
        mListData.add("合同");
        mListData.add("合同");
        mListData.add("合同");
        mListData.add("合同");
        mListData.add("合同");
        mListData.add("合同");
        mListData.add("合同");

        mTypeAdapter.setListData(mListData);
        mLvShowTypes.setAdapter(mTypeAdapter);
        mTypeAdapter.notifyDataSetChanged();
    }
}
