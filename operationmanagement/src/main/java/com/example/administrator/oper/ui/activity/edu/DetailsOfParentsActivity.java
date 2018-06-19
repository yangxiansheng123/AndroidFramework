package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.BaseToolbarActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.oper.R;
import com.example.administrator.oper.manager.Constants;
import com.example.administrator.oper.ui.utils.CallPhoneUtils;
import com.suke.widget.SwitchButton;
import com.utils.SpanUtils;
import com.utils.ToolbarHelper;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/5
 * 描述：DetailsOfParentsActivity 家长详情
 */
public class DetailsOfParentsActivity extends BaseToolbarActivity implements View.OnClickListener {

    protected ImageView imgParentDetails;
    protected TextView tvParentName;
    protected LinearLayout tvParentDetails;
    protected TextView tvParentPhone;
    protected TextView tvParentType;
    protected TextView tvParentSex;
    protected TextView tvParentAge;
    protected TextView tvParentLearnDegree;
    protected TextView tvDistributionState;
    protected LinearLayout llFollowUpRecord;
    protected TextView tvFollowUpMethod;
    protected TextView tvFollowUpPerson;
    protected TextView tvFollowUpState;
    protected TextView tvFollowUpTime;
    protected TextView tvFollowUpCount;
    protected TextView tvPurchaseCourse;
    protected TextView tvCallPhone;
    protected SwitchButton toggleButton;
    protected LinearLayout llDistributionState;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_details_of_parents;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_details_of_parents);
        initView();
    }

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(getString(R.string.title_activity_details_of_parents));
        Toolbar toolbar = toolbarHelper.getToolbar();
        // 显示导航按钮
        toolbar.setNavigationIcon(R.mipmap.left_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        imgParentDetails = (ImageView) findViewById(R.id.img_parentDetails);
        tvParentName = (TextView) findViewById(R.id.tv_parentName);
        tvParentDetails = (LinearLayout) findViewById(R.id.tv_parentDetails);
        tvParentDetails.setOnClickListener(DetailsOfParentsActivity.this);
        tvParentPhone = (TextView) findViewById(R.id.tv_parentPhone);
        tvParentType = (TextView) findViewById(R.id.tv_parentType);
        tvParentSex = (TextView) findViewById(R.id.tv_parentSex);
        tvParentAge = (TextView) findViewById(R.id.tv_parentAge);
        tvParentLearnDegree = (TextView) findViewById(R.id.tv_parentLearnDegree);

        tvDistributionState = (TextView) findViewById(R.id.tv_distributionState);
        llFollowUpRecord = (LinearLayout) findViewById(R.id.ll_followUpRecord);
        llFollowUpRecord.setOnClickListener(DetailsOfParentsActivity.this);
        tvFollowUpMethod = (TextView) findViewById(R.id.tv_followUpMethod);
        tvFollowUpPerson = (TextView) findViewById(R.id.tv_followUpPerson);
        tvFollowUpState = (TextView) findViewById(R.id.tv_followUpState);
        tvFollowUpTime = (TextView) findViewById(R.id.tv_followUpTime);
        tvFollowUpCount = (TextView) findViewById(R.id.tv_followUpCount);

        initData();
        tvPurchaseCourse = (TextView) findViewById(R.id.tv_purchaseCourse);
        tvPurchaseCourse.setOnClickListener(DetailsOfParentsActivity.this);
        tvCallPhone = (TextView) findViewById(R.id.tv_callPhone);
        tvCallPhone.setOnClickListener(DetailsOfParentsActivity.this);
        toggleButton = (SwitchButton) findViewById(R.id.toggleButton);
        toggleButton.toggle();     //switch state
        toggleButton.toggle(true);//switch without animation
        toggleButton.setShadowEffect(true);//disable shadow effect
        toggleButton.setEnabled(true);//disable button
        toggleButton.setEnableEffect(false);//disable the switch animation
        toggleButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                int i = view.getId();
                if (i == R.id.toggleButton) {
                    Log.e("isChecked", "" + isChecked);

                }
            }
        });
        llDistributionState = (LinearLayout) findViewById(R.id.ll_distributionState);
        llDistributionState.setOnClickListener(DetailsOfParentsActivity.this);
    }


    /**
     * clicklistener
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.tv_parentDetails) {

        } else if (view.getId() == R.id.ll_followUpRecord) {
            intent.setClass(this, FollowUpRecordActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_purchaseCourse) {
            intent.setClass(this, ContractPurchaseCourseActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.tv_callPhone) {
            CallPhoneUtils.actionDial(this, "123456");
        } else if (view.getId() == R.id.ll_distributionState) {

        }
    }

    @Override
    public void onCompleted(int what) {
        super.onCompleted(what);
    }

    @Override
    public void onError(Throwable e, int what) {
        super.onError(e, what);
    }

    @Override
    public void onNext(Object object, int what) {
        super.onNext(object, what);
    }

    /**
     * 初始化数据
     */
    private void initData() {



        RequestOptions options = new RequestOptions()
                .placeholder(com.yalantis.ucrop.R.color.ucrop_color_grey)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(this)
                .load(Constants.URL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(imgParentDetails);

        tvParentName.setText("张晓涵的妈妈");
        tvParentPhone.setText("1585235652");
        tvParentType.setText("A类学员");
        tvParentSex.setText(new SpanUtils()
                .append("性别：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(42)
                .appendSpace(20)
                .append("女")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(42)
                .create());
        tvParentAge.setText(new SpanUtils()
                .append("年龄：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(42)
                .appendSpace(10)
                .append("10岁")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(42)
                .create());
        tvParentLearnDegree.setText(new SpanUtils()
                .append("学习程度：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(42)
                .appendSpace(10)
                .append("初级入门")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(42)
                .create());

        tvFollowUpMethod.setText("跟进方式；沟通结果");

        tvFollowUpPerson.setText(new SpanUtils()
                .append("跟进人员：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(42)
                .appendSpace(10)
                .append("张晓晓")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(42)
                .create());
        tvFollowUpState.setText(new SpanUtils()
                .append("状态：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(42)
                .appendSpace(10)
                .append("已跟进")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(42)
                .create());


        tvFollowUpTime.setText(new SpanUtils()
                .append("时间：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(42)
                .appendSpace(10)
                .append("2018-4-20")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(42)
                .create());
        tvFollowUpState.setText(new SpanUtils()
                .append("跟进次数：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(42)
                .appendSpace(10)
                .append("2次")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(42)
                .create());
    }

}
