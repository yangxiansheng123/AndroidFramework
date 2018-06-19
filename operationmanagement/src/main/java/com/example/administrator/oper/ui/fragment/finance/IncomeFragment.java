package com.example.administrator.oper.ui.fragment.finance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.utils.T;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/14
 * 描述：IncomeFragment 收入
 */
public class IncomeFragment extends Fragment implements View.OnClickListener {


    protected View rootView;
    protected TextView tvIncomeCategory;
    protected LinearLayout llIncomeCategory;
    protected TextView tvIncomeSubject;
    protected LinearLayout llIncomeSubject;
    protected TextView tvComplete;
    protected EditText etIncomeAmount;
    protected EditText etIncomeRemark;
    private String incomeAmount, incomeRemark;//收入金额

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_income, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ll_incomeCategory) {

        } else if (view.getId() == R.id.ll_incomeSubject) {

        } else if (view.getId() == R.id.tv_complete) {
            if (TextUtils.isEmpty(incomeAmount)) {
                T.show(getActivity(), "请输入收入金额", 100);
                return;
            }
            if (TextUtils.isEmpty(incomeRemark)) {
                T.show(getActivity(), "添加备注", 100);
                return;

            }
        }
    }

    private void initView(View rootView) {
        tvIncomeCategory = (TextView) rootView.findViewById(R.id.tv_incomeCategory);
        llIncomeCategory = (LinearLayout) rootView.findViewById(R.id.ll_incomeCategory);
        llIncomeCategory.setOnClickListener(IncomeFragment.this);
        tvIncomeSubject = (TextView) rootView.findViewById(R.id.tv_incomeSubject);
        llIncomeSubject = (LinearLayout) rootView.findViewById(R.id.ll_incomeSubject);
        llIncomeSubject.setOnClickListener(IncomeFragment.this);
        tvComplete = (TextView) rootView.findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(IncomeFragment.this);
        etIncomeAmount = (EditText) rootView.findViewById(R.id.et_incomeAmount);
        etIncomeRemark = (EditText) rootView.findViewById(R.id.et_incomeRemark);

        incomeAmount = etIncomeAmount.getText().toString();
        incomeRemark = etIncomeRemark.getText().toString();
    }
}
