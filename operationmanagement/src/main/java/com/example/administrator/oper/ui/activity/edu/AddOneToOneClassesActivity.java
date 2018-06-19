package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.utils.T;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/5
 * 描述：AddOneToOneClassesActivity 添加1对1班级
 */
public class AddOneToOneClassesActivity extends AppCompatActivity implements View.OnClickListener {


    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvSelectCourseCategory;
    protected TextView tvSelectTeacher;
    protected TextView tvBuckleType;
    protected EditText etBuckleCourseNum;
    protected TextView tvComplete;
    private String buckleCourseNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_one_to_one_classes);

        initView();
        initData();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_add_one_to_one_classes));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvSelectCourseCategory = (TextView) findViewById(R.id.tv_selectCourseCategory);
        tvSelectCourseCategory.setOnClickListener(AddOneToOneClassesActivity.this);
        tvSelectTeacher = (TextView) findViewById(R.id.tv_selectTeacher);
        tvSelectTeacher.setOnClickListener(AddOneToOneClassesActivity.this);
        tvBuckleType = (TextView) findViewById(R.id.tv_buckleType);
        tvBuckleType.setOnClickListener(AddOneToOneClassesActivity.this);
        etBuckleCourseNum = (EditText) findViewById(R.id.et_buckleCourseNum);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(AddOneToOneClassesActivity.this);
    }


    /**
     * 初始化数据
     */
    private void initData() {
        buckleCourseNum = etBuckleCourseNum.getText().toString();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        if (view.getId() == R.id.tv_selectCourseCategory) {

        } else if (view.getId() == R.id.tv_selectTeacher) {

        } else if (view.getId() == R.id.tv_buckleType) {

        } else if (view.getId() == R.id.tv_complete) {
            if (TextUtils.isEmpty(buckleCourseNum)) {
                T.show(this,"请输入扣课数量",100);
                return;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.hideToast();
    }
}
