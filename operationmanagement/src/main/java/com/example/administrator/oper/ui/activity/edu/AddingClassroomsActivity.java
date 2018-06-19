package com.example.administrator.oper.ui.activity.edu;

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
 * 创建日期：2018/5/10
 * 描述：AddingClassroomsActivity 添加事件
 */
public class AddingClassroomsActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected EditText etClassroomName;
    protected EditText etLimitSittingCount;
    protected EditText tvAddClassRemarks;
    protected TextView tvComplete;
    private String classroomName, limitSittingCount, addClassRemarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_adding_classrooms);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_adding_classrooms));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etClassroomName = (EditText) findViewById(R.id.et_classroomName);
        etLimitSittingCount = (EditText) findViewById(R.id.et_limitSittingCount);
        tvAddClassRemarks = (EditText) findViewById(R.id.tv_addClassRemarks);
        initData();
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(AddingClassroomsActivity.this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        classroomName = etClassroomName.getText().toString();
        limitSittingCount = etLimitSittingCount.getText().toString();
        addClassRemarks = tvAddClassRemarks.getText().toString();
    }


    /**
     * 处理点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_complete) {

            if (TextUtils.isEmpty(classroomName)){
                T.show(this,"请输入教室名称",100);
                return;
            }
            if (TextUtils.isEmpty(limitSittingCount)){
                T.show(this,"请输入限坐人员",100);
                return;
            }
            if (TextUtils.isEmpty(addClassRemarks)){
                T.show(this,"请添加备注",100);
                return;
            }
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.hideToast();
    }
}
