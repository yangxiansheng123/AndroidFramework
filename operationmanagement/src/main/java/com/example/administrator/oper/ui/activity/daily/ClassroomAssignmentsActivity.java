package com.example.administrator.oper.ui.activity.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.oper.R;
import com.example.administrator.oper.manager.Constants;
import com.example.administrator.oper.ui.activity.edu.AddARecordActivity;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/8
 * 描述：ClassroomAssignmentsActivity 课堂作业
 */
public class ClassroomAssignmentsActivity extends BaseActivity implements View.OnClickListener,TextWatcher {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvHomeworkTitl;
    protected TextView tvIsbatches;
    protected ImageView imgHead;
    protected TextView tvName;
    protected TextView tvFirstSubmission;
    protected TextView tvModifyTime;
    protected TextView tvCorrectionTime;
    protected TextView tvReturnHomework;
    protected TextView tvHomeworkExplain;
    protected EditText etInputDetailsQuestion;
    protected TextView tvNumberRestriction;
    protected TextView tvUploadFile;
    protected TextView tvStartRecording;
    protected TextView tvSubmitCorrections;
    protected TextView tvJobScore;
    //输入最多字体
    private int MAX_NUM = 300;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_classroom_assignments);
        initView();
        initData();
    }


    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_classroom_assignments));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvHomeworkTitl = (TextView) findViewById(R.id.tv_homeworkTitl);
        tvIsbatches = (TextView) findViewById(R.id.tv_isbatches);
        imgHead = (ImageView) findViewById(R.id.img_head);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvFirstSubmission = (TextView) findViewById(R.id.tv_firstSubmission);
        tvModifyTime = (TextView) findViewById(R.id.tv_modifyTime);
        tvCorrectionTime = (TextView) findViewById(R.id.tv_correctionTime);
        tvReturnHomework = (TextView) findViewById(R.id.tv_returnHomework);
        tvReturnHomework.setOnClickListener(ClassroomAssignmentsActivity.this);
        tvHomeworkExplain = (TextView) findViewById(R.id.tv_homeworkExplain);
        etInputDetailsQuestion = (EditText) findViewById(R.id.et_inputDetailsQuestion);
        etInputDetailsQuestion.addTextChangedListener(this);
        tvNumberRestriction = (TextView) findViewById(R.id.tv_numberRestriction);
        tvUploadFile = (TextView) findViewById(R.id.tv_uploadFile);
        tvUploadFile.setOnClickListener(ClassroomAssignmentsActivity.this);
        tvStartRecording = (TextView) findViewById(R.id.tv_startRecording);
        tvStartRecording.setOnClickListener(ClassroomAssignmentsActivity.this);
        tvSubmitCorrections = (TextView) findViewById(R.id.tv_submitCorrections);
        tvSubmitCorrections.setOnClickListener(ClassroomAssignmentsActivity.this);
        tvJobScore = (TextView) findViewById(R.id.tv_jobScore);
        tvJobScore.setOnClickListener(ClassroomAssignmentsActivity.this);
    }


    /**
     * 初始化数据
     */
    private void initData() {
        tvHomeworkTitl.setText("第三节课堂作业");
        tvIsbatches.setText("已批0/未批8");




        RequestOptions options = new RequestOptions()
                .placeholder(com.yalantis.ucrop.R.color.ucrop_color_grey)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(this)
                .load(Constants.URL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(imgHead);

        tvName.setText("苏大牙");
        tvFirstSubmission.setText("首次提交：2018-03-15 21:06");
        tvModifyTime.setText("修改时间：2018-03-16 09:30");
        tvCorrectionTime.setText("批改时间：2018-03-27 17:20");
        tvHomeworkExplain.setText("同样我们全局的icon也采用了线性图标，这种方式轻巧简练，具有一定的想象空间，且不会对界面产生太大的视觉干扰，圆角的处理更符合本次改版的视觉语言，简洁、轻快、活泼。");
    }

    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.tv_returnHomework) {

        } else if (view.getId() == R.id.tv_uploadFile) {

        } else if (view.getId() == R.id.tv_startRecording) {

        } else if (view.getId() == R.id.tv_submitCorrections) {

        } else if (view.getId() == R.id.tv_jobScore) {
            intent.setClass(ClassroomAssignmentsActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "客户评定");
            startActivityForResult(intent, 2002);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2002) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //评分
                tvJobScore.setText(type);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
        if (s.length() > MAX_NUM) {
            s.delete(MAX_NUM, s.length());
        }
//        int num = MAX_NUM - s.length();
        tvNumberRestriction.setText(String.valueOf(s.length()) + "/ 300字");

    }
}
