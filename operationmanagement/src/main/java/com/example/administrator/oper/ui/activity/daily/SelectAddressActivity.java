package com.example.administrator.oper.ui.activity.daily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.utils.T;
import utils.ChangeAddressPopwindow;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/6/5
 * 描述：SelectAddressActivity
 */
public class SelectAddressActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView mTvTextRight;
    protected TextView tvSelectProvice;
    protected TextView tvSelectMayer;
    protected TextView tvSelectArea;
    protected EditText etInputAddress;
    private String PMAAddress;
    private String detailsAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_select_address);
        initView();
    }

    /**
     * 选择省市区
     * @param view
     */
    @Override
    public void onClick(View view) {
        detailsAddress = etInputAddress.getText().toString();
        if (view.getId() == R.id.tv_text_right) {

            if (TextUtils.isEmpty(PMAAddress)){
                T.show(this,"请选择地址",100);
                return;
            }
            if (TextUtils.isEmpty(detailsAddress)){
                T.show(this,"请输入详细地址",100);
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("PMAAddress",PMAAddress);
            intent.putExtra("detailsAddress",detailsAddress);
            setResult(2100, intent);
            finish();
            closeKeybord(etInputAddress,this);

        } else if (view.getId() == R.id.tv_selectProvice) {

            //显示popwindow
            ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(SelectAddressActivity.this);
            mChangeAddressPopwindow.setAddress("北京", "朝阳区", "  ");
            mChangeAddressPopwindow.showAtLocation(tvSelectProvice, Gravity.BOTTOM, 0, 0);
            mChangeAddressPopwindow
                    .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                        @Override
                        public void onClick(String province, String city, String area) {
                            String xiuarea = area.toString().trim();
                            if (xiuarea.equals("") || xiuarea.isEmpty() || xiuarea == null) {
                                tvSelectProvice.setText(province + " " + province + " " + city);
                            } else {
                                tvSelectProvice.setText(province + " " + city + " " + area);
                            }
                            PMAAddress = province + " " + city + " " + area;
                        }
                    });
        }
    }

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_select_address));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTextRight = (TextView) findViewById(R.id.tv_text_right);
        mTvTextRight.setText("确定");
        mTvTextRight.setOnClickListener(SelectAddressActivity.this);
        tvSelectProvice = (TextView) findViewById(R.id.tv_selectProvice);
        tvSelectProvice.setOnClickListener(SelectAddressActivity.this);
        tvSelectMayer = (TextView) findViewById(R.id.tv_selectMayer);
        tvSelectMayer.setOnClickListener(SelectAddressActivity.this);
        tvSelectArea = (TextView) findViewById(R.id.tv_selectArea);
        tvSelectArea.setOnClickListener(SelectAddressActivity.this);
        etInputAddress = (EditText) findViewById(R.id.et_inputAddress);
    }


    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext 上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
  