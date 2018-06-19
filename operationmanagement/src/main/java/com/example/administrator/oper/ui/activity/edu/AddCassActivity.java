package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.widget.BuckleTypeDialog;
import com.utils.T;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/5
 * 描述：AddCassActivity 添加班级
 */
public class AddCassActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvSelectClass;
    protected LinearLayout llSelectClass;
    protected TextView tvSelectTeacher;
    protected LinearLayout llSelectTeacher;
    protected TextView tvComplete;
    protected TextView tvSettingClassTime;
    protected LinearLayout llSettingClassTime;
    protected TextView tvCourseCategory;
    protected TextView tvBuckleType;
    protected EditText etInputClassName;
    protected EditText etInputBuckleCount;
    protected EditText etInputOnlineCount;
    protected EditText etInputAudioCount;
    private BuckleTypeDialog isCloseDialog;
    private String className,buckleCount,onlineCount,audioCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_cass);
        initView();


    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_add_cass));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvSelectClass = (TextView) findViewById(R.id.tv_selectClass);
        llSelectClass = (LinearLayout) findViewById(R.id.ll_selectClass);
        llSelectClass.setOnClickListener(AddCassActivity.this);
        tvSelectTeacher = (TextView) findViewById(R.id.tv_selectTeacher);
        llSelectTeacher = (LinearLayout) findViewById(R.id.ll_selectTeacher);
        llSelectTeacher.setOnClickListener(AddCassActivity.this);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(AddCassActivity.this);
        tvSettingClassTime = (TextView) findViewById(R.id.tv_settingClassTime);
        llSettingClassTime = (LinearLayout) findViewById(R.id.ll_settingClassTime);
        llSettingClassTime.setOnClickListener(AddCassActivity.this);
        tvCourseCategory = (TextView) findViewById(R.id.tv_courseCategory);
        tvCourseCategory.setOnClickListener(AddCassActivity.this);
        tvBuckleType = (TextView) findViewById(R.id.tv_buckleType);
        tvBuckleType.setOnClickListener(AddCassActivity.this);
        etInputClassName = (EditText) findViewById(R.id.et_inputClassName);
        etInputBuckleCount = (EditText) findViewById(R.id.et_inputBuckleCount);
        etInputOnlineCount = (EditText) findViewById(R.id.et_inputOnlineCount);
        etInputAudioCount = (EditText) findViewById(R.id.et_inputAudioCount);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        className =etInputClassName.getText().toString();
        buckleCount =etInputBuckleCount.getText().toString();
        onlineCount =etInputOnlineCount.getText().toString();
        audioCount =etInputAudioCount.getText().toString();

        if (view.getId() == R.id.ll_selectClass) {
            intent.setClass(this, AddClassroomsSelectsActivity.class);
            intent.putExtra("title", "选择教室");
            startActivityForResult(intent, 1001);
        } else if (view.getId() == R.id.ll_selectTeacher) {
            intent.setClass(this, AddClassroomsSelectsActivity.class);
            intent.putExtra("title", "选择老师");
            startActivityForResult(intent, 1002);

        } else if (view.getId() == R.id.ll_settingClassTime) {
            intent.setClass(this, ClassTimeSettingActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.tv_complete) {

            if (TextUtils.isEmpty(className)){
                T.show(this,"请输入班级名称",100);
                return;
            }
            if (TextUtils.isEmpty(buckleCount)){
                T.show(this,"请输入扣课数量",100);
                return;
            }
            if (TextUtils.isEmpty(buckleCount)){
                T.show(this,"请输入正式学员上线",100);
                return;
            }
            if (TextUtils.isEmpty(buckleCount)){
                T.show(this,"请输入试听学员",100);
                return;
            }
        } else if (view.getId() == R.id.tv_courseCategory) {
            intent.setClass(this, SelectCourseclassificationActivity.class);
            intent.putExtra("title", "选择分类");
            startActivityForResult(intent, 1003);

        } else if (view.getId() == R.id.tv_buckleType) {

            dialog();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //教室
                tvSelectClass.setText(type);
            }
        } else if (requestCode == 1002) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //老师
                tvSelectTeacher.setText(type);
            }
        }
    }


    /**
     * 扣课类型
     */
    public void dialog() {

        BuckleTypeDialog.Builder dialog = new BuckleTypeDialog.Builder(this)
                .mBuckleType(new BuckleTypeDialog.BuckleType() {

                    @Override
                    public void buckleOrder(String order) {
                        isCloseDialog.dismiss();
                        tvBuckleType.setText(order);
                    }

                    @Override
                    public void buckleStage(String stage) {
                        isCloseDialog.dismiss();
                        tvBuckleType.setText(stage);
                    }
                });
        isCloseDialog = dialog.create();
        isCloseDialog.show();
    }
}
