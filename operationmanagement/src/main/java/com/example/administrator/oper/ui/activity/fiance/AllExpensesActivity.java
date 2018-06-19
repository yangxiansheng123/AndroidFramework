package com.example.administrator.oper.ui.activity.fiance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.base.BaseToolbarActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.widget.MyXFormatter;
import com.example.administrator.oper.widget.XYMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.utils.ToolbarHelper;

import java.util.ArrayList;
import java.util.Random;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：AllExpensesActivity
 */
public class AllExpensesActivity extends BaseToolbarActivity {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected BarChart barchart;
    private String expensive;
    private Random random;//用于产生随机数字
    protected String[] values = new String[]{"房租", "电费", "水费", "物业费"};
    private ToolbarHelper toolbarHelper;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_all_expenses;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expensive = getIntent().getStringExtra("expensives");
        random = new Random();
        initView();
        initBarChart();
        toolbarHelper.setTitle(expensive + "");
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        this.toolbarHelper = toolbarHelper;
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
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        barchart = (BarChart) findViewById(R.id.barchart);
    }


    private void initBarChart() {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 1; i <= 4; i++) {
            yVals1.add(new BarEntry(i, i * 320));
        }

        barchart.getDescription().setEnabled(false);
        //自定义x轴显示
        MyXFormatter formatter = new MyXFormatter(values);
        XAxis xAxis = barchart.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setAxisLineWidth(1f);//设置x轴线宽度
        //显示个数
        xAxis.setLabelCount(4);
        xAxis.setValueFormatter(formatter);
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘


        /**
         * 得到图表的左侧Y轴实例
         */
        YAxis leftAxis = barchart.getAxisLeft();
        YAxis axisRight = barchart.getAxisRight();
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);  //设置Y轴网格线条的虚线，参1 实线长度，参2 虚线长度 ，参3 周期
//        leftAxis.setGranularity(20f); // 网格线条间距
        axisRight.setEnabled(false);   //设置是否使用 Y轴右边的
        leftAxis.setEnabled(true);     //设置是否使用 Y轴左边的
        leftAxis.setGridColor(Color.parseColor("#7189a9"));  //网格线条的颜色
        leftAxis.setDrawLabels(true);        //是否显示Y轴刻度
        leftAxis.setStartAtZero(true);        //设置Y轴数值 从零开始
//        leftAxis.setDrawGridLines(true);      //是否使用 Y轴网格线条
        leftAxis.setTextSize(12f);            //设置Y轴刻度字体
//        leftAxis.setTextColor(Color.WHITE);   //设置字体颜色
//        leftAxis.setAxisLineColor(Color.WHITE); //设置Y轴颜色
        leftAxis.setAxisLineWidth(1.1f);
        leftAxis.setDrawAxisLine(true);//是否绘制轴线
//        leftAxis.setMinWidth(1f);
//        leftAxis.setMaxWidth(3f);
        leftAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return "￥" + (int)value;
            }
        });

        BarDataSet set1;
        if (barchart.getData() != null &&
                barchart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barchart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            barchart.getData().notifyDataChanged();
            barchart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "Data Set");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            barchart.setData(data);
            barchart.setFitBars(true);
        }

        //设置悬浮
        XYMarkerView mv = new XYMarkerView(this, formatter);
        mv.setChartView(barchart);
        barchart.setMarker(mv);

        /**
         * 设置注解的位置在左上方
         */
        barchart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
        barchart.getLegend().setEnabled(false);
        /**
         * 这是左边显示小图标的形状
         */
        barchart.getLegend().setForm(Legend.LegendForm.CIRCLE);

        /**
         * 设置X轴的位置
         */
//        barchart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        /**
         * 不显示网格
         */
//        barchart.getXAxis().setDrawGridLines(false);

        /**
         * 右侧不显示Y轴
         */
        barchart.getAxisRight().setEnabled(false);
        /**
         * 设置Y轴显示最小值，不然0下面会有空隙
         */
        barchart.getAxisLeft().setAxisMinimum(112f);
        barchart.getAxisLeft().setYOffset(0f);

        /**
         * 设置Y轴网格
         */
//        leftAxis.setDrawGridLines(true);
//        leftAxis.setAxisMinimum(0);
//        leftAxis.setAxisMaximum(1500);
//        leftAxis.setStartAtZero(true);
        barchart.setScaleEnabled(false);
        barchart.invalidate();

    }


}
