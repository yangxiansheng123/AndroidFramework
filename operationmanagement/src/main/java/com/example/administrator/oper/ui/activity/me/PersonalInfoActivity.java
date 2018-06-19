package com.example.administrator.oper.ui.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.activity.daily.AddStudentsSelectTypeActivity;
import com.utils.RxRegTool;
import com.utils.T;

/**
 * 个人信息
 *
 * @author yang
 */
public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvBaseInfo;
    protected TextView tvSex;
    protected EditText etInputName;
    protected EditText etInputPhone;
    protected TextView tvSaveInfo;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initView();
        toolbarHead();

        tvBaseInfo.getPaint().setStrokeWidth(1);
    }

    private void toolbarHead() {
        mToolbarTitle.setText("个人信息");
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tvBaseInfo = (TextView) findViewById(R.id.tv_baseInfo);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        tvSex.setOnClickListener(PersonalInfoActivity.this);
        etInputName = (EditText) findViewById(R.id.et_inputName);
        etInputPhone = (EditText) findViewById(R.id.et_inputPhone);
        tvSaveInfo = (TextView) findViewById(R.id.tv_saveInfo);
        tvSaveInfo.setOnClickListener(PersonalInfoActivity.this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        phone = etInputPhone.getText().toString();
        if (view.getId() == R.id.tv_sex) {
            intent.setClass(PersonalInfoActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "选择性别");
            startActivityForResult(intent, 6000);
        } else if (view.getId() == R.id.tv_saveInfo) {
            if (RxRegTool.isMobile(phone)) {
            } else {
                T.show(PersonalInfoActivity.this, "请输入正确的手机号码", 1000);
                etInputPhone.setText("");
                return;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6000) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                tvSex.setText(type);
            }
        }
    }
}
