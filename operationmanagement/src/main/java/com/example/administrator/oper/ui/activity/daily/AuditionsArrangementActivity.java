package com.example.administrator.oper.ui.activity.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.activity.edu.SelectCourseclassificationActivity;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：AuditionsArrangementActivity
 */
public class AuditionsArrangementActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView mTvTextRight;
    protected TextView tvDistributionState;
    protected LinearLayout llStudentDistribution;
    protected TextView tvCurriculumClassification;
    protected LinearLayout llCurriculumClassification;
    protected TextView tvAttendClassClass;
    protected LinearLayout llAttendClassClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_auditions_arrangement);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_auditions_arrangement));
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
        tvDistributionState = (TextView) findViewById(R.id.tv_distributionState);
        llStudentDistribution = (LinearLayout) findViewById(R.id.ll_studentDistribution);
        llStudentDistribution.setOnClickListener(AuditionsArrangementActivity.this);

        tvCurriculumClassification = (TextView) findViewById(R.id.tv_curriculumClassification);
        llCurriculumClassification = (LinearLayout) findViewById(R.id.ll_curriculumClassification);
        llCurriculumClassification.setOnClickListener(AuditionsArrangementActivity.this);
        tvAttendClassClass = (TextView) findViewById(R.id.tv_attendClassClass);
        llAttendClassClass = (LinearLayout) findViewById(R.id.ll_attendClassClass);
        llAttendClassClass.setOnClickListener(AuditionsArrangementActivity.this);
    }

    /**
     * 事件处理
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.ll_studentDistribution) {
            intent.setClass(this, SelectStudentsActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.ll_curriculumClassification) {
//            intent.setClass(this, CurriculumClassificationActivity.class);
            intent.setClass(this, SelectCourseclassificationActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.ll_attendClassClass) {
            intent.setClass(this, SelectClassActivity.class);
            startActivity(intent);

        }
    }
}
