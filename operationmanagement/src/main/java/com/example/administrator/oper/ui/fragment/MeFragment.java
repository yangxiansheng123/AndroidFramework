package com.example.administrator.oper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.activity.me.ModifyPaswordActivity;
import com.example.administrator.oper.ui.activity.me.PersonalInfoActivity;
import com.example.administrator.oper.ui.activity.me.SettingManagementActivity;
import com.example.administrator.oper.ui.activity.me.OperationGuideActivity;

/**
 * 我的
 */
public class MeFragment extends Fragment implements View.OnClickListener {


    protected View rootView;
    protected FrameLayout tvEditInfo;
    protected TextView tvSettingMangment;
    protected TextView tvModifyPw;
    protected TextView tvOperationGuide;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_me, container, false);
        initView(rootView);
        return rootView;
    }


    private void initView(View rootView) {
        tvEditInfo = (FrameLayout) rootView.findViewById(R.id.fl_editInfo);
        tvEditInfo.setOnClickListener(MeFragment.this);
        tvSettingMangment = (TextView) rootView.findViewById(R.id.tv_settingMangment);
        tvSettingMangment.setOnClickListener(MeFragment.this);
        tvModifyPw = (TextView) rootView.findViewById(R.id.tv_modifyPw);
        tvModifyPw.setOnClickListener(MeFragment.this);
        tvOperationGuide = (TextView) rootView.findViewById(R.id.tv_operationGuide);
        tvOperationGuide.setOnClickListener(MeFragment.this);
    }

    /**
     * 事件处理
     *
     * @param view
     * @param
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        int i = view.getId();
        if (i == R.id.fl_editInfo) {
            intent.setClass(getContext(), PersonalInfoActivity.class);
            startActivity(intent);

        } else if (i == R.id.tv_modifyPw) {
            intent.setClass(getContext(), ModifyPaswordActivity.class);
            startActivity(intent);

        } else if (i == R.id.tv_settingMangment) {
            intent.setClass(getContext(), SettingManagementActivity.class);
            startActivity(intent);

        } else if (i == R.id.tv_operationGuide) {
            intent.setClass(getContext(), OperationGuideActivity.class);
            startActivity(intent);

        }
    }
}
