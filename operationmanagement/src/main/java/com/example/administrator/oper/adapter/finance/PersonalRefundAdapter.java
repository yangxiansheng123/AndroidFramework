package com.example.administrator.oper.adapter.finance;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.finance.RefundRecordBean;

import java.util.List;

/**
 * @author yang
 * 个人退款详情
 */
public class PersonalRefundAdapter extends BaseAdapter {

    private RefundRecordEvent mRecordEvent;
    private List<RefundRecordBean> listData;
    private Context mContext;
    private LayoutInflater inflater;
    private int selectionPosition;


    public PersonalRefundAdapter(Context context, RefundRecordEvent recordEvent) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        mRecordEvent = recordEvent;
    }

    public void setListData(List<RefundRecordBean> listData) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_refunds_records_pattern, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (listData.size() - 1 == position) {
            holder.ll_refundBg.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.solid_shape_white_bootom_lright));
        } else {
            holder.ll_refundBg.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

        holder.tv_responsibilityPerons.setText(listData.get(position).getPersonName());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecordEvent.deleteItem(listData, position);
            }
        });

        return convertView;
    }

    class ViewHolder {
        private final ImageView img_delete;
        private final EditText et_inputAmount;
        TextView tv_responsibilityPerons;
        LinearLayout ll_refundBg;

        public ViewHolder(View view) {
            tv_responsibilityPerons = (TextView) view.findViewById(R.id.tv_responsibilityPerons);
            img_delete = (ImageView) view.findViewById(R.id.img_delete);
            et_inputAmount = (EditText) view.findViewById(R.id.et_inputAmount);
            ll_refundBg = (LinearLayout) view.findViewById(R.id.ll_refundBg);
        }


    }

    public interface RefundRecordEvent {
        public void deleteItem(List<RefundRecordBean> listData, int position);

        public void selectPerson(List<RefundRecordBean> listData, int position);
    }
}
