package com.example.administrator.oper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.activity.daily.*;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：DailyFragment 日常
 */
public class DailyFragment extends Fragment implements View.OnClickListener {


    protected View rootView;
    protected TextView tvCollegeEntry;
    protected TextView tvCollegeDistribution;
    protected TextView tvRenewaReminding;
    protected TextView tvAuditionsArrangement;
    protected TextView tvWaitForClass;
    protected TextView tvStudentAttendance;
    protected TextView tvCreateAJob;
    protected TextView tvToBeCorrected;
    protected TextView tvMyHomework;
    protected TextView tvCollegeFollowUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_daily, container, false);
        initView(rootView);
        return rootView;
    }

    /**
     * 处理点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.tv_collegeEntry) {
            intent.setClass(getContext(), CollegeEntryActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.tv_CollegeDistribution) {
            intent.setClass(getContext(), CollegeDistributionActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_collegeFollowUp) {
            intent.setClass(getContext(), CollegeFollowUpActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.tv_renewaReminding) {
            intent.setClass(getContext(), RenewalRemindingActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_auditionsArrangement) {
            intent.setClass(getContext(), AuditionsArrangementActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_waitForClass) {
            intent.setClass(getContext(), WaitForClassActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_studentAttendance) {
            intent.setClass(getContext(), StudentAttendanceActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_createAJob) {
            intent.setClass(getContext(), CreateAJobActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_toBeCorrected) {
            intent.setClass(getContext(), ToBeCorrectedActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_myHomework) {
            intent.setClass(getContext(), MyHomeworkActivity.class);
            startActivity(intent);

        }
    }

    private void initView(View rootView) {
        tvCollegeEntry = (TextView) rootView.findViewById(R.id.tv_collegeEntry);
        tvCollegeEntry.setOnClickListener(DailyFragment.this);
        tvCollegeDistribution = (TextView) rootView.findViewById(R.id.tv_CollegeDistribution);
        tvCollegeDistribution.setOnClickListener(DailyFragment.this);
        tvRenewaReminding = (TextView) rootView.findViewById(R.id.tv_renewaReminding);
        tvRenewaReminding.setOnClickListener(DailyFragment.this);
        tvAuditionsArrangement = (TextView) rootView.findViewById(R.id.tv_auditionsArrangement);
        tvAuditionsArrangement.setOnClickListener(DailyFragment.this);
        tvWaitForClass = (TextView) rootView.findViewById(R.id.tv_waitForClass);
        tvWaitForClass.setOnClickListener(DailyFragment.this);
        tvStudentAttendance = (TextView) rootView.findViewById(R.id.tv_studentAttendance);
        tvStudentAttendance.setOnClickListener(DailyFragment.this);
        tvCreateAJob = (TextView) rootView.findViewById(R.id.tv_createAJob);
        tvCreateAJob.setOnClickListener(DailyFragment.this);
        tvToBeCorrected = (TextView) rootView.findViewById(R.id.tv_toBeCorrected);
        tvToBeCorrected.setOnClickListener(DailyFragment.this);
        tvMyHomework = (TextView) rootView.findViewById(R.id.tv_myHomework);
        tvMyHomework.setOnClickListener(DailyFragment.this);
        tvCollegeFollowUp = (TextView) rootView.findViewById(R.id.tv_collegeFollowUp);
        tvCollegeFollowUp.setOnClickListener(DailyFragment.this);
    }
}
