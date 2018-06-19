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
 * 描述：ExpenditureFragment 支出
 */
public class ExpenditureFragment extends Fragment implements View.OnClickListener {


    protected View rootView;
    protected TextView tvExpenditureCategoty;
    protected LinearLayout llExpenditureCategoty;
    protected TextView tvExpenditureSubject;
    protected LinearLayout llExpenditureSubject;
    protected EditText etExpenditureAmount;
    protected EditText etTvExpenditureRemark;
    protected TextView tvComplete;
    private String amount, remark;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_expenditure, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onClick(View view) {

        amount = etExpenditureAmount.getText().toString();
        remark = etTvExpenditureRemark.getText().toString();

        if (view.getId() == R.id.ll_expenditureCategoty) {

        } else if (view.getId() == R.id.ll_expenditureSubject) {

        } else if (view.getId() == R.id.tv_complete) {
            if (TextUtils.isEmpty(amount)) {
                T.show(getActivity(), "请输入收入金额", 100);
                return;
            }
            if (TextUtils.isEmpty(remark)) {
                T.show(getActivity(), "添加备注", 100);
                return;

            }
        }
    }

    private void initView(View rootView) {
        tvExpenditureCategoty = (TextView) rootView.findViewById(R.id.tv_expenditureCategoty);
        llExpenditureCategoty = (LinearLayout) rootView.findViewById(R.id.ll_expenditureCategoty);
        llExpenditureCategoty.setOnClickListener(ExpenditureFragment.this);
        tvExpenditureSubject = (TextView) rootView.findViewById(R.id.tv_expenditureSubject);
        llExpenditureSubject = (LinearLayout) rootView.findViewById(R.id.ll_expenditureSubject);
        llExpenditureSubject.setOnClickListener(ExpenditureFragment.this);
        etExpenditureAmount = (EditText) rootView.findViewById(R.id.et_expenditureAmount);
        etTvExpenditureRemark = (EditText) rootView.findViewById(R.id.et_tv_expenditureRemark);
        tvComplete = (TextView) rootView.findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(ExpenditureFragment.this);
    }
}
