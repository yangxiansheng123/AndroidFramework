package com.example.administrator.oper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.activity.edu.*;

/**
 * 教务
 *
 * @author yang
 */
public class EducationalAdminFragment extends Fragment implements View.OnClickListener {


    protected View rootView;
    protected TextView tvStudentManage;
    protected TextView tvClassManage;
    protected TextView tvTimetable;
    protected TextView tvEmployeeManage;
    protected TextView tvContractManage;
    protected TextView tvClassroomManage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_educational_admin, container, false);
        initView(rootView);
        return rootView;
    }


    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        /**
         * 学员管理
         */
        Intent intent = new Intent();
        if (view.getId() == R.id.tv_studentManage) {
            intent.setClass(getContext(), StudentManagementActivity.class);
            startActivity(intent);
        }
        /**
         *班级管理
         */
        else if (view.getId() == R.id.tv_classManage) {
            intent.setClass(getContext(), ClassManagemenActivity.class);
            startActivity(intent);

        }
        /**
         *总课表
         */
        else if (view.getId() == R.id.tv_timetable) {
            intent.setClass(getContext(), TimetablesActivity.class);
            startActivity(intent);

        }
        /**
         *员工管理
         */
        else if (view.getId() == R.id.tv_employeeManage) {
            intent.setClass(getContext(), EmployeeListActivity.class);
            startActivity(intent);

        }
        /**
         *合同管理
         */
        else if (view.getId() == R.id.tv_contractManage) {
            intent.setClass(getContext(), ContractManagementActivity.class);
            startActivity(intent);

        }
        /**
         *教师管理
         */
        else if (view.getId() == R.id.tv_classroomManage) {
            intent.setClass(getContext(), ClassroomManagementActivity.class);
            startActivity(intent);

        }
    }

    /**
     * 初始化控件
     *
     * @param rootView
     */
    private void initView(View rootView) {
        tvStudentManage = (TextView) rootView.findViewById(R.id.tv_studentManage);
        tvStudentManage.setOnClickListener(EducationalAdminFragment.this);
        tvClassManage = (TextView) rootView.findViewById(R.id.tv_classManage);
        tvClassManage.setOnClickListener(EducationalAdminFragment.this);
        tvTimetable = (TextView) rootView.findViewById(R.id.tv_timetable);
        tvTimetable.setOnClickListener(EducationalAdminFragment.this);
        tvEmployeeManage = (TextView) rootView.findViewById(R.id.tv_employeeManage);
        tvEmployeeManage.setOnClickListener(EducationalAdminFragment.this);
        tvContractManage = (TextView) rootView.findViewById(R.id.tv_contractManage);
        tvContractManage.setOnClickListener(EducationalAdminFragment.this);
        tvClassroomManage = (TextView) rootView.findViewById(R.id.tv_classroomManage);
        tvClassroomManage.setOnClickListener(EducationalAdminFragment.this);
    }
}
