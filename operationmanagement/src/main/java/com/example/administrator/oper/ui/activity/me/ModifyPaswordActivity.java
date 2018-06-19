package com.example.administrator.oper.ui.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.utils.T;

/**
 * 密码修改
 *
 * @author yang
 */
public class ModifyPaswordActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected EditText etOldPwd;
    protected EditText etNewPwd;
    protected EditText etConfirmtPwd;
    protected Button btnSecuritySave;
    private String oldPwd,newPwd,confirmtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_modify_pasword);
        initView();
        mToolbarTitle.setText("修改密码");
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etOldPwd = (EditText) findViewById(R.id.et_oldPwd);
        etNewPwd = (EditText) findViewById(R.id.et_newPwd);
        etConfirmtPwd= (EditText) findViewById(R.id.et_confirmtPwd);
        btnSecuritySave = (Button) findViewById(R.id.btn_securitySave);
        btnSecuritySave.setOnClickListener(ModifyPaswordActivity.this);
    }

    @Override
    public void onClick(View view) {
        oldPwd = etOldPwd.getText().toString();
        newPwd = etNewPwd.getText().toString();
        confirmtPwd = etConfirmtPwd.getText().toString();
        if (view.getId() == R.id.btn_securitySave) {

            if (TextUtils.isEmpty(oldPwd)){
                T.show(this,"请输入原密码",100);
                return;
            }

            if (TextUtils.isEmpty(newPwd)){
                T.show(this,"请输入新密码",100);
                return;
            }

            if (TextUtils.isEmpty(confirmtPwd)){
                T.show(this,"请输入确认密码",100);
                return;
            }

            if (!confirmtPwd.equals(newPwd)){
                T.show(this,"两次密码不一致",100);
                return;
            }
        }
    }
}
