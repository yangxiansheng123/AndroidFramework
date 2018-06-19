package com.example.administrator.oper.adapter.finance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.oper.R;

import java.util.List;

/**
 * @author yang
 * 实时账单
 */
public class FinanceRealTimeBillAdapter extends BaseAdapter {

    private List<String> listData;
    private Context mContext;
    private LayoutInflater inflater;
    private int selectionPosition;


    public FinanceRealTimeBillAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }


    @Override
    public int getCount() {
        return listData.size() > 0 ? listData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_finance_real_time_bill_pattern, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_rl_name.setText(listData.get(position));



        return convertView;
    }

    class ViewHolder {
        TextView tv_rl_name;

        public ViewHolder(View view) {
            tv_rl_name = (TextView) view.findViewById(R.id.tv_rl_name);
        }
    }

}
