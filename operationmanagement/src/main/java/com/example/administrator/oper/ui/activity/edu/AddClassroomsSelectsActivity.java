package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.AddClaasSelectTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/10
 * 描述：AddClassroomsSelectsActivity 添加教室选择类型(eg:选择老师)
 */
public class AddClassroomsSelectsActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgRight;
    protected ListView mLvShowTypes;
    private String title;
    private AddClaasSelectTypeAdapter mSelectTypeAdapter;
    private List<String> mListData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_classrooms_selects);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_right) {

        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mSelectTypeAdapter = new AddClaasSelectTypeAdapter(this);
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
        mImgRight = (ImageView) findViewById(R.id.img_right);
        mImgRight.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.add1));
        mImgRight.setOnClickListener(AddClassroomsSelectsActivity.this);
        mLvShowTypes = (ListView) findViewById(R.id.lv_showTypes);
    }

    /**
     * 获取数据
     */
    private void initData() {
        if (title.equals("选择教室")){
            mListData.add("教室1101");
            mListData.add("教室1102");
            mListData.add("教室1103");
            mListData.add("教室1104");
            mListData.add("教室1105");
            mListData.add("教室1106");
        }else if (title.equals("选择老师")){
            mListData.add("李丹老师");
            mListData.add("张静思老师");
            mListData.add("万点节老师");
            mListData.add("李发老师");
            mListData.add("谢社联老师");
        }

        mSelectTypeAdapter.setListData(mListData);
        mLvShowTypes.setAdapter(mSelectTypeAdapter);
        mSelectTypeAdapter.notifyDataSetChanged();
    }

}
