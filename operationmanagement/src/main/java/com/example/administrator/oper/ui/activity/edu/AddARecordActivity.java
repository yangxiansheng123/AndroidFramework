package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.activity.daily.AddStudentsSelectTypeActivity;
import com.example.administrator.oper.ui.activity.daily.ChoiceFollowUpMethodActivity;
import com.example.administrator.oper.widget.FollowUpMethodDialog;
import com.suke.widget.SwitchButton;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/15
 * 描述：AddARecordActivity
 */
public class AddARecordActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView mTvTextRight;
    protected TextView tvNumberRestriction;
    protected EditText etInputDetailsQuestion;
    protected TextView tvQuickFollowUp;
    protected TextView tvFollowUpMethod;
    protected TextView tvNextFollowUpTime;
    protected TextView tvStudentAssessment;
    //输入最多字体
    private int MAX_NUM = 450;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_arecord);
        initView();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.tv_text_right) {

        } else if (view.getId() == R.id.tv_quickFollowUp) {

            FollowUpMethodDialog followUpDialog = new FollowUpMethodDialog
                    .Builder(this)
                    .mReturnResult(new FollowUpMethodDialog.ReturnResult() {
                        @Override
                        public void getResult(String order) {
                            tvQuickFollowUp.setText(order);
                        }
                    })
                    .create();
            followUpDialog.show();
        } else if (view.getId() == R.id.tv_followUpMethod) {
            intent.setClass(AddARecordActivity.this, ChoiceFollowUpMethodActivity.class);
            intent.putExtra("title", "选择跟进方式");
            startActivityForResult(intent, 2002);

        } else if (view.getId() == R.id.tv_studentAssessment) {

            intent.setClass(AddARecordActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "客户评定");
            startActivityForResult(intent, 2002);

        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle.setText(getString(R.string.title_activity_add_arecord));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTvTextRight = (TextView) findViewById(R.id.tv_text_right);
        mTvTextRight.setOnClickListener(AddARecordActivity.this);
        tvNumberRestriction = (TextView) findViewById(R.id.tv_numberRestriction);
        etInputDetailsQuestion = (EditText) findViewById(R.id.et_inputDetailsQuestion);
        etInputDetailsQuestion.addTextChangedListener(this);
        tvQuickFollowUp = (TextView) findViewById(R.id.tv_quickFollowUp);
        tvQuickFollowUp.setOnClickListener(AddARecordActivity.this);
        tvFollowUpMethod = (TextView) findViewById(R.id.tv_followUpMethod);
        tvFollowUpMethod.setOnClickListener(AddARecordActivity.this);
        tvNextFollowUpTime = (TextView) findViewById(R.id.tv_nextFollowUpTime);
        tvStudentAssessment = (TextView) findViewById(R.id.tv_studentAssessment);
        tvStudentAssessment.setOnClickListener(AddARecordActivity.this);

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        tvNextFollowUpTime.setText("明天");
        tvStudentAssessment.setText("A类学员");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2002) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                tvFollowUpMethod.setText(type);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
        if (s.length() > MAX_NUM) {
            s.delete(MAX_NUM, s.length());
        }
//        int num = MAX_NUM - s.length();
        tvNumberRestriction.setText(String.valueOf(s.length()) + "/ 450字");

    }

}
