package com.example.administrator.oper.ui.activity.daily;

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

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/10
 * 描述：AddStudentsSelectTypeActivity 添加学员选择不同类型
 */
public class AddStudentsSelectTypeActivity extends BaseActivity {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ListView mLvShowTypes;
    private String title;
    private AddStudentsSelectTypeAdapter mTypeAdapter;
    private List<String> mListData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_students_select_type);
        title = getIntent().getStringExtra("title");
        initView();
        mLvShowTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent();
                intent.putExtra("type", mListData.get(position));
                setResult(2100, intent);
                finish();
            }
        });

        initData();
    }


    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(title);
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
        mTypeAdapter = new AddStudentsSelectTypeAdapter(AddStudentsSelectTypeActivity.this);
    }


    /**
     * 获取数据
     */
    private void initData() {
        if (title.equals("选择家长")){
            mListData.add("爸爸");
            mListData.add("妈妈");
            mListData.add("爷爷");
            mListData.add("奶奶");
            mListData.add("姥姥");
            mListData.add("姥爷");
        }else if (title.equals("选择性别")){
            mListData.add("男");
            mListData.add("女");
        }else if (title.equals("学员来源")){
            mListData.add("机构上门");
            mListData.add("机构上门");
            mListData.add("推广获取");
            mListData.add("电话咨询");
            mListData.add("其他渠道");
        }else if (title.equals("客户评定")){
            mListData.add("暂不评定");
            mListData.add("A");
            mListData.add("B");
            mListData.add("C");
            mListData.add("D");
        }else if (title.equals("作业格式")){
            mListData.add("不限制");
            mListData.add("文字格式");
            mListData.add("图片格式");
            mListData.add("音频格式");
            mListData.add("world格式");
            mListData.add("excle格式");
            mListData.add("视频格式");
        }else if (title.equals("选择上课类型")){
            mListData.add("单次课");
            mListData.add("星期一");
            mListData.add("星期二");
            mListData.add("星期三");
            mListData.add("星期四");
            mListData.add("星期五");
            mListData.add("星期六");
            mListData.add("星期天");
        }

        mTypeAdapter.setListData(mListData);
        mLvShowTypes.setAdapter(mTypeAdapter);
        mTypeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTypeAdapter != null) {
            mTypeAdapter = null;
        }
        finish();
    }
}
