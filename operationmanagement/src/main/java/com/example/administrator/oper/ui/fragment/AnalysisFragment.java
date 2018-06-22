package com.example.administrator.oper.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.BaseFragment;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.utils.ObtainSytemTimeUtil;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.*;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.utils.SpanUtils;
import com.utils.T;
import org.wangchenlong.datescroller.widget.DateScrollerDialog;
import org.wangchenlong.datescroller.widget.data.Type;
import org.wangchenlong.datescroller.widget.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 分析
 *
 * @author yangpf
 */
public class AnalysisFragment extends BaseFragment implements View.OnClickListener, OnChartValueSelectedListener {

    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年
    protected TextView tvAddress;
    protected LinearLayout llSelectAddress;
    protected EditText etIsMobile;
    protected TextView tvAnalysisSelectStartTime;
    /**
     * 学员分析
     */
    protected TextView tvAnalysisIncreNum;
    protected TextView tvAnalysisFollowNum;
    protected TextView tvAnalysisVisitNum;
    protected TextView tvAnalysisAudioNum;
    protected TextView tvAnalysisSiginNum;
    protected TextView tvAnalysisAudioSiginRate;
    protected TextView tvAnalysisVisitRate;
    protected TextView tvAnalysisInviteRate;
    /**
     * 营销费用
     */
    protected TextView tvMarketCostsSignNum;
    protected TextView tvMarketCostsAudioNum;
    protected TextView tvMarketCostsInfoNum;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private String birthday;
    private String startTime, endTime;

    protected View rootView;
    protected PieChart mPiechart;
    protected String[] mParties = new String[]{
            "固定费用  1万  7%", "营销费用  2万  71%", "日常费用  2万  27%", "学习耗材  2万  87%", "工资支出  8万  7%"
    };
    //线性chart
    protected TextView mTvPreProfit;
    protected TextView analysisTime;
    protected PieChart piechartOperationAnalysis;
    protected LinearLayout llFixedCost;
    protected View viewBgHasExpense;
    protected TextView tvHasExpense;
    protected View viewBgNoexpense;
    protected TextView tvTotalClassCost;
    protected TextView tvNoExpense;
    protected TextView tvHasExpensePrice;
    protected TextView tvNoExpensePrice;

    /**
     * -  经营分析
     */
    public static final int[] OPERATOR_COLORS = {
            Color.rgb(32, 232, 209), Color.rgb(251, 228, 75)
    };

    /**
     * -  财务分析
     */
    public static final int[] FINANCE_COLORS = {
            Color.rgb(32, 232, 209), Color.rgb(255, 45, 138), Color.rgb(243, 143, 0),
            Color.rgb(254, 227, 76), Color.rgb(0, 146, 255)
    };
    /**
     * 营销颜色
     */
    protected TextView tvFixedCostColor;
    protected TextView tvMarketColor;
    protected TextView tvDailyColor;
    protected TextView tvLearnColor;
    protected TextView tvWageColor;

    protected TextView tvFixedCost;
    protected TextView tvMarketCost;
    protected TextView tvDailyCost;
    protected TextView tvLearnCost;
    protected TextView tvWageExpenditure;
    protected TextView tvAnalysisSelectTime;
    private String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_analysis, container, false);
        initView(rootView);
        initData();
        initPiecart();
        initPiecartOpert();
        return rootView;
    }


    /**
     * 初始化控件
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mPiechart = (PieChart) rootView.findViewById(R.id.piechart_financeAnalysis);
        mTvPreProfit = (TextView) rootView.findViewById(R.id.tv_pre_profit);
        analysisTime = (TextView) rootView.findViewById(R.id.analysisTime);
        piechartOperationAnalysis = (PieChart) rootView.findViewById(R.id.piechart_operationAnalysis);
        llFixedCost = (LinearLayout) rootView.findViewById(R.id.ll_fixedCost);
        viewBgHasExpense = (View) rootView.findViewById(R.id.view_bgHasExpense);
        tvHasExpense = (TextView) rootView.findViewById(R.id.tv_hasExpense);
        viewBgNoexpense = (View) rootView.findViewById(R.id.view_bgNoexpense);
        tvHasExpense = (TextView) rootView.findViewById(R.id.tv_hasExpense);
        tvTotalClassCost = (TextView) rootView.findViewById(R.id.tv_totalClassCost);
        tvNoExpense = (TextView) rootView.findViewById(R.id.tv_noExpense);
        tvHasExpensePrice = (TextView) rootView.findViewById(R.id.tv_hasExpensePrice);
        tvNoExpensePrice = (TextView) rootView.findViewById(R.id.tv_noExpensePrice);
        tvFixedCostColor = (TextView) rootView.findViewById(R.id.tv_fixedCostColor);
        tvMarketColor = (TextView) rootView.findViewById(R.id.tv_marketColor);
        tvDailyColor = (TextView) rootView.findViewById(R.id.tv_dailyColor);
        tvLearnColor = (TextView) rootView.findViewById(R.id.tv_learnColor);
        tvWageColor = (TextView) rootView.findViewById(R.id.tv_wageColor);
        tvFixedCost = (TextView) rootView.findViewById(R.id.tv_fixedCost);
        tvMarketCost = (TextView) rootView.findViewById(R.id.tv_marketCost);
        tvDailyCost = (TextView) rootView.findViewById(R.id.tv_dailyCost);
        tvLearnCost = (TextView) rootView.findViewById(R.id.tv_learnCost);
        tvWageExpenditure = (TextView) rootView.findViewById(R.id.tv_wageExpenditure);
        tvAnalysisSelectTime = (TextView) rootView.findViewById(R.id.tv_analysisSelectTime);
        tvAnalysisSelectTime.setOnClickListener(AnalysisFragment.this);
        tvAnalysisSelectStartTime = (TextView) rootView.findViewById(R.id.tv_analysisSelectStartTime);
        tvAnalysisSelectStartTime.setOnClickListener(AnalysisFragment.this);

        tvAnalysisSelectStartTime.setText(ObtainSytemTimeUtil.getDateToString(System.currentTimeMillis()));
        tvAnalysisSelectTime.setText(ObtainSytemTimeUtil.getDateToString(System.currentTimeMillis()));
        tvAnalysisIncreNum = (TextView) rootView.findViewById(R.id.tv_analysis_increNum);
        tvAnalysisFollowNum = (TextView) rootView.findViewById(R.id.tv_analysis_followNum);
        tvAnalysisVisitNum = (TextView) rootView.findViewById(R.id.tv_analysis_visitNum);
        tvAnalysisAudioNum = (TextView) rootView.findViewById(R.id.tv_analysis_audioNum);
        tvAnalysisSiginNum = (TextView) rootView.findViewById(R.id.tv_analysis_siginNum);
        tvAnalysisAudioSiginRate = (TextView) rootView.findViewById(R.id.tv_analysis_audioSiginRate);
        tvAnalysisVisitRate = (TextView) rootView.findViewById(R.id.tv_analysis_visitRate);
        tvAnalysisInviteRate = (TextView) rootView.findViewById(R.id.tv_analysis_inviteRate);
        tvMarketCostsSignNum = (TextView) rootView.findViewById(R.id.tv_marketCostsSignNum);
        tvMarketCostsAudioNum = (TextView) rootView.findViewById(R.id.tv_marketCostsAudioNum);
        tvMarketCostsInfoNum = (TextView) rootView.findViewById(R.id.tv_marketCostsInfoNum);
    }

    /**
     * 事件处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.toolbar) {
        } else if (view.getId() == R.id.tv_analysisSelectTime) {
            type = "end";
            showDate();
        } else if (view.getId() == R.id.tv_analysisSelectStartTime) {
            type = "start";
            showDate();

        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        /**
         * 运营分析
         */
        viewBgHasExpense.setBackgroundColor(Color.parseColor("#20e8d1"));
        tvHasExpense.setText(new SpanUtils()
                .append("已消课时费用")
                .setForegroundColor(Color.parseColor("#20e8d1"))
                .setFontSize(32)
                .create());
        tvHasExpensePrice.setText(new SpanUtils()
                .append("4万/200课时")
                .setForegroundColor(Color.parseColor("#20e8d1"))
                .setFontSize(48)
                .create());
        viewBgNoexpense.setBackgroundColor(Color.parseColor("#fbe44b"));

        tvNoExpense.setText(new SpanUtils()
                .append("未消课时费用")
                .setForegroundColor(Color.parseColor("#fbe44b"))
                .setFontSize(32)
                .create());
        tvNoExpensePrice.setText(new SpanUtils()
                .append("0.8万/40课时")
                .setForegroundColor(Color.parseColor("#fbe44b"))
                .setFontSize(48)
                .create());
        tvTotalClassCost.setText("总课时费用：4.8万/240课时");

        mTvPreProfit.setText("预估年盈利：18万元");


        tvFixedCost.setText("固定费用：1万");
        tvFixedCost.setTextColor(FINANCE_COLORS[0]);
        tvMarketCost.setText("营销费用：2万");
        tvMarketCost.setTextColor(FINANCE_COLORS[1]);
        tvDailyCost.setText("日常费用:0.5万");
        tvDailyCost.setTextColor(FINANCE_COLORS[2]);
        tvLearnCost.setText("学具耗材：0.3万");
        tvLearnCost.setTextColor(FINANCE_COLORS[3]);
        tvWageExpenditure.setText("工资支出：10.5万");
        tvWageExpenditure.setTextColor(FINANCE_COLORS[4]);
    }


    /**
     * -  财务分析
     * 初始化Piecart
     */
    private void initPiecart() {
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
        mPiechart.setCenterText(generateCenterSpannableText());

        /**
         * 否显示PieChart内部圆环(true:下面属性才有意义)
         */
        mPiechart.setDrawHoleEnabled(true);
        mPiechart.setHoleColor(Color.parseColor("#00000000"));

        mPiechart.setTransparentCircleColor(Color.WHITE);
        mPiechart.setTransparentCircleAlpha(110);

        /**
         * 内半径
         */
        mPiechart.setHoleRadius(75f);
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
         *  可以手动旋转
         */
        mPiechart.setRotationEnabled(false);
        mPiechart.setHighlightPerTapEnabled(true);
        mPiechart.setOnChartValueSelectedListener(this);
        setData(5, 100);
        mPiechart.animateY(1400, Easing.EaseInOutQuad);

        /**
         * 设置方块不可见
         */
        Legend l = mPiechart.getLegend();
        l.setEnabled(false);
        mPiechart.setEntryLabelColor(Color.WHITE);
        mPiechart.setEntryLabelTextSize(12f);
    }

    /**
     * -  -  经营分析
     * 初始化Piecart
     */
    private void initPiecartOpert() {
        /**
         * //设置显示成比例
         */
        piechartOperationAnalysis.setUsePercentValues(true);
        piechartOperationAnalysis.getDescription().setEnabled(false);
        piechartOperationAnalysis.setExtraOffsets(5, 10, 5, 5);
        /**
         * 设置隐藏饼图上文字，只显示百分比
         */
        piechartOperationAnalysis.setDrawSliceText(false);
        piechartOperationAnalysis.setDragDecelerationFrictionCoef(0.95f);
        piechartOperationAnalysis.setCenterText(getOperateCenterSpannableText());

        /**
         * 否显示PieChart内部圆环(true:下面属性才有意义)
         */
        piechartOperationAnalysis.setDrawHoleEnabled(true);
        piechartOperationAnalysis.setHoleColor(Color.parseColor("#00000000"));

        piechartOperationAnalysis.setTransparentCircleColor(Color.WHITE);
        piechartOperationAnalysis.setTransparentCircleAlpha(110);

        /**
         * 内半径
         */
        piechartOperationAnalysis.setHoleRadius(75f);
        /**
         * 半透明圈
         */
        piechartOperationAnalysis.setTransparentCircleRadius(61f);

        /**
         * 饼状图中间可以添加文字
         */
        piechartOperationAnalysis.setDrawCenterText(true);

        piechartOperationAnalysis.setRotationAngle(0);
        /**
         * // 可以手动旋转
         */
        piechartOperationAnalysis.setRotationEnabled(false);
        piechartOperationAnalysis.setHighlightPerTapEnabled(true);
        piechartOperationAnalysis.setOnChartValueSelectedListener(this);
        setOperatData(2, 100);
        piechartOperationAnalysis.animateY(1400, Easing.EaseInOutQuad);

        /**
         * 设置方块不可见
         */
        Legend l = piechartOperationAnalysis.getLegend();
        l.setEnabled(false);
        piechartOperationAnalysis.setEntryLabelColor(Color.WHITE);
        piechartOperationAnalysis.setEntryLabelTextSize(12f);
    }


    /**
     * 运营分析
     *
     * @param count
     * @param range
     */
    private void setOperatData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i],
                    getResources().getDrawable(R.mipmap.ic_launcher)));
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

        for (int c : OPERATOR_COLORS) {
            colors.add(c);
        }

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(mTfLight);
        piechartOperationAnalysis.setData(data);

        // undo all highlights
        piechartOperationAnalysis.highlightValues(null);

        piechartOperationAnalysis.invalidate();

        tvFixedCostColor.setBackgroundColor(FINANCE_COLORS[0]);
        tvMarketColor.setBackgroundColor(FINANCE_COLORS[1]);
        tvDailyColor.setBackgroundColor(FINANCE_COLORS[2]);
        tvLearnColor.setBackgroundColor(FINANCE_COLORS[3]);
        tvWageColor.setBackgroundColor(FINANCE_COLORS[4]);
    }

    /**
     * -  财务分析
     *
     * @param count 显示数量
     * @param range 用于显示百分比
     */
    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i],
                    getResources().getDrawable(R.mipmap.ic_launcher)));
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
//        data.setValueTypeface(mTfLight);
        mPiechart.setData(data);

        // undo all highlights
        mPiechart.highlightValues(null);

        mPiechart.invalidate();
    }

    /**
     * 设置中心字体颜色
     * -  经营分析
     *
     * @return
     */
    private SpannableString getOperateCenterSpannableText() {

        SpannableString s = new SpannableString("83.3%\n消课率 ");
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#27d9f1")), 0, s.length(), 0);


        return s;
    }

    /**
     * 设置中心字体颜色
     *
     * @return
     */
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("总支出\n360000.00 ");
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#27d9f1")), 0, s.length(), 0);

        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }


    /**
     * 设置样式
     *
     * @param context
     * @param lineChart
     */
    private static void initDataStyle(Context context, LineChart lineChart) {

        //设置图表是否支持触控操作
        lineChart.setTouchEnabled(true);
        //设置图表是否支持缩放操作
        lineChart.setScaleEnabled(false);
        //设置点击折线点时，显示其数值
//        MyMakerView mv = new MyMakerView(context, R.layout.item_mark_layout);
//        combinedChart.setMarkerView(mv);
        //设置折线的描述的样式（默认在图表的左下角）
        Legend title = lineChart.getLegend();
        title.setForm(Legend.LegendForm.SQUARE);
        title.setEnabled(false);


        //设置x轴的样式
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.parseColor("#E1E1E1"));
        xAxis.setAxisLineWidth(2);
        xAxis.setDrawGridLines(false);
        //设置是否显示x轴
        xAxis.setEnabled(true);
        xAxis.setTextColor(Color.WHITE);

        //设置左边y轴的样式
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setTextColor(Color.WHITE);
        yAxisLeft.setAxisLineColor(Color.parseColor("#E1E1E1"));
        yAxisLeft.setAxisLineWidth(2);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setStartAtZero(false);
        yAxisLeft.setAxisMinValue(30);

        yAxisLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "人";
            }
        });

        //设置右边y轴的样式
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        lineChart.setDescription(null);
        List<Integer> nums = new ArrayList<>();
        nums.add(20);
        nums.add(140);
        nums.add(200);
        /**
         * 存储数据
         */
        List<String> datas = new ArrayList<>();
        datas.add("市场合理值");
        datas.add("收支平衡");
        datas.add("");

        /**
         * 存储颜色值
         */
        List<String> colors = new ArrayList<>();
        colors.add("#8986FF");
        colors.add("#FFC730");
        colors.add("#FFBE10");
        limitLine(yAxisLeft, nums, datas, colors);


    }

    /**
     * 画水平线
     *
     * @param yAxisLeft
     * @param nums
     * @param datas
     * @param colors
     */
    private static void limitLine(YAxis yAxisLeft, List<Integer> nums, List<String> datas, List<String> colors) {
        //设置限制线 nums代表某个该轴某个值，也就是要画到该轴某个值上
        for (int i = 0; i < nums.size(); i++) {
            LimitLine limitLine = new LimitLine(nums.get(i));
            //设置限制线的宽
            limitLine.setLineWidth(1f);
            //设置限制线的颜色

            limitLine.setLineColor(Color.parseColor(colors.get(i)));
            //设置基线的位置
            limitLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
            limitLine.setLabel(datas.get(i));
            limitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            //设置限制线为虚线/实线
            if (colors.size() - 1 == i) {
                limitLine.isDashedLineEnabled();
            } else {
                limitLine.enableDashedLine(10f, 10f, 0f);
            }
            //左边Y轴添加限制线
            yAxisLeft.addLimitLine(limitLine);
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
                dialog.show(getFragmentManager(), "year_month_day");
            }
        }
    }

    private int result;
    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            birthday = getDateToString(milliseconds);
            if (type.equals("start")) {
                startTime = birthday;
                //回调选中的数据
                tvAnalysisSelectStartTime.setText(startTime);
            } else {
                endTime = birthday;
                //回调选中的数据
                tvAnalysisSelectTime.setText(endTime);
                result = startTime.compareTo(endTime);

                if (result == 0) {

                } else if (result > 0) {
                    T.show(getActivity(), "结束时间不能小于开始时间", 100);
                    tvAnalysisSelectTime.setText("");
                }
            }
        }
    };

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

}
