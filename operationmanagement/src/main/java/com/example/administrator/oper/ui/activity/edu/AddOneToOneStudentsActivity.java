package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.BaseToolbarActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.AddOneToOneStudentAdapter;
import com.example.administrator.oper.bean.edu.AddOneToOneStudentClassTimeBean;
import com.example.administrator.oper.ui.widget.MyListView;
import com.utils.ToolbarHelper;
import org.wangchenlong.datescroller.widget.DateScrollerDialog;
import org.wangchenlong.datescroller.widget.data.Type;
import org.wangchenlong.datescroller.widget.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/5
 * 描述：AddOneToOneStudentsActivity 添加1对1学员
 */
public class AddOneToOneStudentsActivity extends BaseToolbarActivity implements AddOneToOneStudentAdapter.UpdateStudentTime, View.OnClickListener {


    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年
    protected TextView tvConfirm;
    protected LinearLayout llPrompt;
    protected TextView tvStartTime;
    protected TextView tvEndTime;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private String birthday;
    private String istinguishdTime;
    protected MyListView mLvShowoneToOneStudent;
    protected TextView tvComplete;
    protected ImageView imgAddClassTime;
    private AddOneToOneStudentAdapter mToOneStudentAdapter;
    private List<AddOneToOneStudentClassTimeBean> listData = new ArrayList<>();
    private int currentPosition;
    private int sClassPosition;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_one_to_one_students;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_add_one_to_one_students);
        initView();
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(getString(R.string.title_activity_add_one_to_one_students));
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


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_complete) {

        } else if (view.getId() == R.id.img_addClassTime) {

            listData.add(new AddOneToOneStudentClassTimeBean("单次课", "选择时间", "教室"));
            mToOneStudentAdapter.setListData(listData);
            mLvShowoneToOneStudent.setAdapter(mToOneStudentAdapter);
            mToOneStudentAdapter.notifyDataSetChanged();
        } else if (view.getId() == R.id.tv_startTime) {

            showDate();
            istinguishdTime = "start";
        } else if (view.getId() == R.id.tv_endTime) {
            showDate();
            istinguishdTime = "end";

        }
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mToOneStudentAdapter = new AddOneToOneStudentAdapter(this, this);
        mLvShowoneToOneStudent = (MyListView) findViewById(R.id.lv_showoneToOneStudent);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(AddOneToOneStudentsActivity.this);
        imgAddClassTime = (ImageView) findViewById(R.id.img_addClassTime);
        imgAddClassTime.setOnClickListener(AddOneToOneStudentsActivity.this);
        tvStartTime = (TextView) findViewById(R.id.tv_startTime);
        tvStartTime.setOnClickListener(AddOneToOneStudentsActivity.this);
        tvEndTime = (TextView) findViewById(R.id.tv_endTime);
        tvEndTime.setOnClickListener(AddOneToOneStudentsActivity.this);

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


    @Override
    public void deleteTime(List<AddOneToOneStudentClassTimeBean> listData, int position) {

        listData.remove(position);
        mToOneStudentAdapter.notifyDataSetChanged();
    }

    @Override
    public void modifyCourseType(List<AddOneToOneStudentClassTimeBean> listData, int position) {

    }

    @Override
    public void modifyCourseYMD(List<AddOneToOneStudentClassTimeBean> listData, int position) {
        currentPosition = position;
        istinguishdTime = "select";
        showDate();

    }

    /**
     * 选择教室
     *
     * @param listData
     * @param position
     */
    @Override
    public void selectClassroom(List<AddOneToOneStudentClassTimeBean> listData, int position) {
        sClassPosition = position;
        Intent intent = new Intent();
        intent.setClass(this, AddClassroomsSelectsActivity.class);
        intent.putExtra("title", "选择教室");
        startActivityForResult(intent, 1001);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //教室
                listData.get(sClassPosition).setClassroom(type);
                mToOneStudentAdapter.notifyDataSetChanged();
            }
        }
    }


    /**
     * 显示日期
     *
     * @param
     */
    public void showDate() {
        // 出生日期
        DateScrollerDialog dialog = new DateScrollerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("请选择上课时间")
                .setMinMilliseconds(System.currentTimeMillis() - HUNDRED_YEARS)
                .setMaxMilliseconds(System.currentTimeMillis())
                .setCurMilliseconds(mLastTime)
                .setCallback(mOnDateSetListener)
                .build();

        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show(getSupportFragmentManager(), "year_month_day");
            }
        }
    }

    private String startTime, endTime;
    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            //回调选中的数据
            birthday = getDateToString(milliseconds);
            if (istinguishdTime.equals("start")) {
                startTime = birthday;
                tvStartTime.setText(startTime);
                tvStartTime.setTextColor(Color.parseColor("#333333"));
            } else if (istinguishdTime.equals("end")) {
                endTime = birthday;
                tvEndTime.setText(endTime);
                tvEndTime.setTextColor(Color.parseColor("#333333"));
            } else {
                listData.get(currentPosition).setClassTime(birthday);
//            mToOneStudentAdapter.setListData(listData);
                mToOneStudentAdapter.notifyDataSetChanged();
            }
        }
    };

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

}
