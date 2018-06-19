package com.example.administrator.oper.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.BaseFragment;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.finance.FinanceRealTimeBillAdapter;
import com.example.administrator.oper.ui.activity.fiance.*;
import com.example.administrator.oper.ui.utils.SplitTimeUtils;
import com.example.administrator.oper.ui.widget.MyListView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.utils.SpanUtils;
import com.utils.T;
import com.utils.TimeUtil;
import org.wangchenlong.datescroller.singlepicker.OptionPicker;
import org.wangchenlong.datescroller.singlepicker.WheelView;
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
 * 创建日期：2018/5/2
 * 描述：FinanceFragment 财务
 */
public class FinanceFragment extends BaseFragment implements OnChartValueSelectedListener, View.OnClickListener {


    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年
    protected TextView tvFixedCostColor;
    protected TextView tvFixedCost;
    protected TextView tvMarketColor;
    protected TextView tvMarketCost;
    protected TextView tvDailyColor;
    protected TextView tvDailyCost;
    protected TextView tvLearnColor;
    protected TextView tvLearnCost;
    protected TextView tvWageColor;
    protected TextView tvWageExpenditure;
    protected TextView tvChangeRecord;
    protected MyListView mLvRealTimeBill;
    protected LinearLayout llExpenditure;
    protected TextView tvExpdiIncome;
    protected LinearLayout llMarketCost;
    protected LinearLayout llDailyCost;
    protected LinearLayout llLearnCost;
    protected LinearLayout llWageIncomeCost;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private String birthday;

    /**
     * 财务分析
     */
    public static final int[] FINANCE_COLORS = {
            Color.rgb(0, 129, 189), Color.rgb(191, 80, 141), Color.rgb(84, 189, 255),
            Color.rgb(255, 136, 0), Color.rgb(75, 96, 198)
    };
    protected View rootView;
    protected PieChart mPiechart;
    protected String[] mParties = new String[]{
            "固定费用  1万  7%", "营销费用  2万  71%", "日常费用  2万  27%", "学习耗材  2万  87%", "工资支出  8万  7%"
    };
    protected LinearLayout llStudentRefund;
    protected LinearLayout llBookkeeping;
    protected TextView tvPayrolls;
    protected LinearLayout llFixedCost;
    protected TextView tvExpectProfit;
    protected TextView tvFinancYear;
    protected TextView tvFinanceSelectMonth;
    protected LinearLayout llFinanceSelectTime;
    private long systemTime = System.currentTimeMillis();
    private FinanceRealTimeBillAdapter mFinanceRealTimeBillAdapter;
    private List<String> listData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_finance, container, false);
        for (int i = 0; i < 3; i++) {
            listData.add("张三");
        }
        initView(rootView);
        initPiecart("总支出\n360000.00 ", 5);
        initData();

        mLvRealTimeBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent();
                if (position == 0) {
                    intent.setClass(getActivity(), RecordOfExpenditureActivity.class);
                    startActivity(intent);
                } else if (position == 1) {

                    intent.setClass(getActivity(), RefundsRecordActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getActivity(), CoursePurchaseRecordActivity.class);
                    startActivity(intent);

                }
            }
        });
        return rootView;
    }


    /**
     * 初始化控件
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mFinanceRealTimeBillAdapter = new FinanceRealTimeBillAdapter(getActivity());
        mPiechart = (PieChart) rootView.findViewById(R.id.piechart);
        llStudentRefund = (LinearLayout) rootView.findViewById(R.id.ll_studentRefund);
        llStudentRefund.setOnClickListener(FinanceFragment.this);
        llBookkeeping = (LinearLayout) rootView.findViewById(R.id.ll_bookkeeping);
        llBookkeeping.setOnClickListener(FinanceFragment.this);
        tvPayrolls = (TextView) rootView.findViewById(R.id.tv_payrolls);
        tvPayrolls.setOnClickListener(FinanceFragment.this);


        llFixedCost = (LinearLayout) rootView.findViewById(R.id.ll_fixedCost);
        llFixedCost.setOnClickListener(FinanceFragment.this);
        tvExpectProfit = (TextView) rootView.findViewById(R.id.tv_expectProfit);
        tvFinancYear = (TextView) rootView.findViewById(R.id.tv_financYear);
        tvFinanceSelectMonth = (TextView) rootView.findViewById(R.id.tv_financeSelectMonth);
        llFinanceSelectTime = (LinearLayout) rootView.findViewById(R.id.ll_financeSelectTime);
        llFinanceSelectTime.setOnClickListener(FinanceFragment.this);

        /**
         * 获得年,月
         */
        tvFinancYear.setText(SplitTimeUtils.getYear(TimeUtil.formatTimeYMD(systemTime)));
        tvFinanceSelectMonth.setText(SplitTimeUtils.getMonth(TimeUtil.formatTimeYMD(systemTime)) + "月");
        tvFixedCostColor = (TextView) rootView.findViewById(R.id.tv_fixedCostColor);
        tvFixedCost = (TextView) rootView.findViewById(R.id.tv_fixedCost);
        tvMarketColor = (TextView) rootView.findViewById(R.id.tv_marketColor);
        tvMarketCost = (TextView) rootView.findViewById(R.id.tv_marketCost);
        tvDailyColor = (TextView) rootView.findViewById(R.id.tv_dailyColor);
        tvDailyCost = (TextView) rootView.findViewById(R.id.tv_dailyCost);
        tvLearnColor = (TextView) rootView.findViewById(R.id.tv_learnColor);
        tvLearnCost = (TextView) rootView.findViewById(R.id.tv_learnCost);
        tvWageColor = (TextView) rootView.findViewById(R.id.tv_wageColor);
        tvWageExpenditure = (TextView) rootView.findViewById(R.id.tv_wageExpenditure);
        tvChangeRecord = (TextView) rootView.findViewById(R.id.tv_changeRecord);
        mLvRealTimeBill = (MyListView) rootView.findViewById(R.id.lv_RealTimeBill);

        /**
         * 适配器添加数据
         */
        mFinanceRealTimeBillAdapter.setListData(listData);
        mLvRealTimeBill.setAdapter(mFinanceRealTimeBillAdapter);
        llExpenditure = (LinearLayout) rootView.findViewById(R.id.ll_expenditure);
        llExpenditure.setOnClickListener(FinanceFragment.this);
        tvExpdiIncome = (TextView) rootView.findViewById(R.id.tv_expdiIncome);
        llMarketCost = (LinearLayout) rootView.findViewById(R.id.ll_marketCost);
        llMarketCost.setOnClickListener(FinanceFragment.this);
        llDailyCost = (LinearLayout) rootView.findViewById(R.id.ll_dailyCost);
        llDailyCost.setOnClickListener(FinanceFragment.this);
        llLearnCost = (LinearLayout) rootView.findViewById(R.id.ll_learnCost);
        llLearnCost.setOnClickListener(FinanceFragment.this);
        llWageIncomeCost = (LinearLayout) rootView.findViewById(R.id.ll_wageIncomeCost);
        llWageIncomeCost.setOnClickListener(FinanceFragment.this);
    }


    private void initData() {
        tvExpectProfit.setText(new SpanUtils()
                .append("预计年盈利：")
                .setForegroundColor(Color.parseColor("#999999"))
                .setFontSize(32)
                .append("25")
                .setForegroundColor(Color.parseColor("#ff8208"))
                .setFontSize(48)
                .append("万元")
                .setForegroundColor(Color.parseColor("#999999"))
                .setFontSize(32)
                .create());


        tvFixedCost.setText("固定费用：1万   7%");
        tvFixedCost.setTextColor(FINANCE_COLORS[0]);
        tvMarketCost.setText("营销费用：2万   14%");
        tvMarketCost.setTextColor(FINANCE_COLORS[1]);
        tvDailyCost.setText("日常费用:0.5万   14%");
        tvDailyCost.setTextColor(FINANCE_COLORS[2]);
        tvLearnCost.setText("学习耗材：0.3万 14%");
        tvLearnCost.setTextColor(FINANCE_COLORS[3]);
        tvWageExpenditure.setText("工资支出10.5万 14%");
        tvWageExpenditure.setTextColor(FINANCE_COLORS[4]);

    }

    /**
     * 初始化Piecart
     *
     * @param datas
     * @param count
     */
    private void initPiecart(String datas, int count) {
        /**
         * //设置显示成比例
         */
        mPiechart.setUsePercentValues(true);
        mPiechart.getDescription().setEnabled(false);
        mPiechart.setExtraOffsets(5, 10, 5, 5);
        /**
         * 设置隐藏饼图上文字，只显示百分比
         */
        mPiechart.setDrawSliceText(false);
        mPiechart.setDragDecelerationFrictionCoef(0.95f);

//        mPiechart.setCenterTextTypeface(mTfLight);
        mPiechart.setCenterText(generateCenterSpannableText(datas));

        /**
         * 否显示PieChart内部圆环(true:下面属性才有意义)
         */
        mPiechart.setDrawHoleEnabled(true);
        mPiechart.setHoleColor(Color.WHITE);

        mPiechart.setTransparentCircleColor(Color.WHITE);
        mPiechart.setTransparentCircleAlpha(110);

        /**
         * 内半径
         */
        mPiechart.setHoleRadius(58f);
        /**
         * 半透明圈
         */
        mPiechart.setTransparentCircleRadius(61f);

        /**
         * 饼状图中间可以添加文字
         */
        mPiechart.setDrawCenterText(true);

        mPiechart.setRotationAngle(0);
        /**
         * // 可以手动旋转
         */
        mPiechart.setRotationEnabled(true);
        mPiechart.setHighlightPerTapEnabled(true);
        mPiechart.setOnChartValueSelectedListener(this);

        setData(count, 100);

        mPiechart.animateY(1400, Easing.EaseInOutQuad);
        /**
         * 设置方块颜色
         */
        Legend l = mPiechart.getLegend();
        l.setEnabled(false);

        // entry label styling
        mPiechart.setEntryLabelColor(Color.WHITE);
        mPiechart.setEntryLabelTextSize(12f);
    }

    /**
     * @param count 显示数量
     * @param range 用于显示百分比
     */
    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i],
                    getResources().getDrawable(R.mipmap.default_head)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        /**
         * 设置饼状Item之间的间隙
         */
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        /**
         * add a lot of colors 添加颜色
         */
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : FINANCE_COLORS) {
            colors.add(c);
        }

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPiechart.setData(data);
        mPiechart.highlightValues(null);
        mPiechart.invalidate();


        tvFixedCostColor.setBackgroundColor(FINANCE_COLORS[0]);
        tvMarketColor.setBackgroundColor(FINANCE_COLORS[1]);
        tvDailyColor.setBackgroundColor(FINANCE_COLORS[2]);
        tvLearnColor.setBackgroundColor(FINANCE_COLORS[3]);
        tvWageColor.setBackgroundColor(FINANCE_COLORS[4]);
    }

    /**
     * 设置中心字体颜色
     *
     * @param strings
     * @return
     */
    private SpannableString generateCenterSpannableText(String strings) {

//        SpannableString s = new SpannableString("总支出\n360000.000");
        SpannableString s = new SpannableString(strings);
//        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
//        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
//        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.ll_studentRefund) {
            intent.setClass(getContext(), StudentRefundsActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.ll_bookkeeping) {
            intent.setClass(getContext(), BookkeepingActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.tv_payrolls) {
            intent.setClass(getContext(), PayrollsActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.ll_fixedCost) {
            intent.setClass(getContext(), AllExpensesActivity.class);
            intent.putExtra("expensives", "固定费用");
            startActivity(intent);

        } else if (view.getId() == R.id.ll_financeSelectTime) {
            showDate();
        } else if (view.getId() == R.id.ll_expenditure) {
            OptionPicker picker = new OptionPicker(getActivity(), new String[]{"支出", "收入"});
            picker.setCanceledOnTouchOutside(false);
            picker.setDividerRatio(WheelView.DividerConfig.FILL);
            picker.setShadowColor(Color.WHITE, 40);
            picker.setSelectedIndex(0);
            picker.setCycleDisable(true);
            picker.setCancelTextSize(16);
            picker.setTopLineColor(Color.parseColor("#F4F4F4"));
            picker.setTopBackgroundColor(Color.parseColor("#fbd415"));
            picker.setCancelTextColor(Color.BLACK);
            picker.setSubmitTextSize(16);
            picker.setSubmitTextColor(Color.BLACK);
            picker.setTextColor(Color.BLACK);
            picker.setDividerColor(Color.parseColor("#F4F4F4"));
            picker.setTextSize(16);
            picker.setOffset(2);
            picker.setAnimationStyle(R.style.AnimationPicker);
            picker.setLineSpaceMultiplier(3.2f);
            picker.setUseWeight(true);
            picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                @Override
                public void onOptionPicked(int index, String item) {
                    tvExpdiIncome.setText(item);
                    if (item.equals("支出")) {
                        initPiecart("总" + item + "\n360000.00 ", 5);
                        llFixedCost.setVisibility(View.VISIBLE);
                        llMarketCost.setVisibility(View.VISIBLE);
                        llDailyCost.setVisibility(View.VISIBLE);
                        llLearnCost.setVisibility(View.VISIBLE);
                        tvWageExpenditure.setText("工资"+item+"+"+"10.5万 14%");
                    } else {
                        initPiecart("总" + item + "\n760000.00 ", 1);
                        llFixedCost.setVisibility(View.GONE);
                        llMarketCost.setVisibility(View.GONE);
                        llDailyCost.setVisibility(View.GONE);
                        llLearnCost.setVisibility(View.GONE);
                        tvWageExpenditure.setText("工资"+item+"+"+"12.5万 14%");
                    }
                }
            });
            picker.show();
        } else if (view.getId() == R.id.ll_marketCost) {

        } else if (view.getId() == R.id.ll_dailyCost) {

        } else if (view.getId() == R.id.ll_learnCost) {

        } else if (view.getId() == R.id.ll_wageIncomeCost) {

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
                .setTitleStringId("请选择日期")
                .setMinMilliseconds(System.currentTimeMillis() - HUNDRED_YEARS)
                .setMaxMilliseconds(System.currentTimeMillis())
                .setCurMilliseconds(mLastTime)
                .setCallback(mOnDateSetListener)
                .build();

        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show(getChildFragmentManager(), "year_month_day");
            }
        }
    }

    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            birthday = getDateToString(milliseconds);
            String[] times = birthday.split("-");
            tvFinanceSelectMonth.setText(times[1] + "月");//回调选中的数据
            tvFinancYear.setText(times[0] + "");//回调选中的数据
        }
    };

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        T.hideToast();
    }
}
