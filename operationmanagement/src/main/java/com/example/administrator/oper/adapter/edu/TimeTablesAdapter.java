package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.edu.TimetablesBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * @author Administrator
 * 适配器  总课表
 */
public class TimeTablesAdapter extends RecyclerView.Adapter<TimeTablesAdapter.ViewHolder> {
    public static final int TYPE_HEADER = 0; //带有Header
    public static final int TYPE_FOOTER = 1; //带有Footer
    public static final int TYPE_NORMAL = 2; //不带有header和footer


    List<TimetablesBean> listData;
    private LayoutInflater inflater;
    private Context mContext;

    /**
     * HeaderView, FooterView
     */
    private View mHeaderView;
    private View mFooterView;

    public TimeTablesAdapter(Context context) {
        mContext = context;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setListData(List<TimetablesBean> listData) {
        this.listData = listData;
    }

    protected View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public View getFooterView() {
        return mFooterView;
    }

    protected void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    @Override
    public TimeTablesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(inflater.inflate(R.layout.activity_timetables_patternc, parent, false));
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }
        View layout = inflater.inflate(R.layout.activity_timetables_patternc, parent, false);
        return new ViewHolder(layout);

    }

    @Override
    public void onBindViewHolder(TimeTablesAdapter.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof ViewHolder) { //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                holder.mClassTitle.setText("10:30-10:30 钢琴室");
                return;
            }
            return;
        } else if (getItemViewType(position) == TYPE_HEADER) {
            holder.mTotalSchedule.setText("12");
            return;
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return listData.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return listData.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return listData.size() + 1;
        } else {
            return listData.size() + 2;
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mClassTitle, mTturriculum, mTClassroom, mTStudying, mTotalSchedule;

        public ViewHolder(View view) {
            super(view);
            if (itemView == mHeaderView) {
                mTotalSchedule = view.findViewById(R.id.tv_totalSchedule);
                return;
            }
            if (itemView == mFooterView) {
                return;
            }
            /**
             * 内容
             */
            mClassTitle = (TextView) view.findViewById(R.id.tv_classTitle);
            mTturriculum = (TextView) view.findViewById(R.id.tv_curriculum);
            mTClassroom = (TextView) view.findViewById(R.id.tv_classroom);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) { //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) { //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }
}
