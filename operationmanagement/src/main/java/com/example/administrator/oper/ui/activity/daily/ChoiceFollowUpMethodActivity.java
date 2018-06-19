package com.example.administrator.oper.ui.activity.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.daily.FollowMethodSelectTypeAdapter;
import com.example.administrator.oper.widget.AddQuickFollowUpDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/18
 * 描述：ChoiceFollowUpMethodActivity 选择跟进方式
 */
public class ChoiceFollowUpMethodActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgRight;
    protected ListView mLvShowTypes;
    private FollowMethodSelectTypeAdapter mFollowMethodSelectTypeAdapter;
    private List<String> mListData = new ArrayList<>();
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_choice_follow_up_method);
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
            AddQuickFollowUpDialog followUpDialog = new AddQuickFollowUpDialog
                    .Builder(this)
                    .mReturnResult(new AddQuickFollowUpDialog.ReturnResult() {
                        @Override
                        public void getResult(String order) {
                            Log.e("order : ",""+order);
                        }
                    })
                    .create();
            followUpDialog.show();
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mFollowMethodSelectTypeAdapter = new FollowMethodSelectTypeAdapter(this);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_choice_follow_up_method));
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
        mImgRight.setOnClickListener(ChoiceFollowUpMethodActivity.this);
        mLvShowTypes = (ListView) findViewById(R.id.lv_showTypes);
    }


    /**
     * 获取数据
     */
    private void initData() {
        if (title.equals("选择跟进方式")) {
            mListData.add("电话打不通！");
            mListData.add("该家长没兴趣！");
            mListData.add("暂时没时间，下去再约！");
            mListData.add("孩子太小暂时不考虑！");
            mListData.add("家长信息有误！");
        }

        mFollowMethodSelectTypeAdapter.setListData(mListData);
        mLvShowTypes.setAdapter(mFollowMethodSelectTypeAdapter);
        mFollowMethodSelectTypeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFollowMethodSelectTypeAdapter != null) {
            mFollowMethodSelectTypeAdapter = null;
        }
        finish();
    }

}
