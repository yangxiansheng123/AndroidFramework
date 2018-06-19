package com.example.administrator.oper.ui.activity.daily;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/4
 * 描述：CurriculumClassificationActivity
 */
public class CurriculumClassificationActivity extends BaseActivity implements View.OnClickListener {


    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgRight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum_classification);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_right) {

        }
    }

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_curriculum_classification));

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mImgRight = (ImageView) findViewById(R.id.img_right);
        mImgRight.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.add1));
        mImgRight.setOnClickListener(CurriculumClassificationActivity.this);
    }
}
