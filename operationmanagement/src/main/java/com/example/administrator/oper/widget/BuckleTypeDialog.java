package com.example.administrator.oper.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.administrator.oper.R;


/**
 * Description:
 * Company    :
 * Author     : dyamail
 * 添加班级-->扣课类型
 */
public class BuckleTypeDialog extends Dialog {


    public BuckleTypeDialog(Context context) {
        super(context);
    }

    public BuckleTypeDialog(Context context, int theme) {
        super(context, R.style.MyDialogStyle);
    }

    public static class Builder {
        private Context context;
        private BuckleType mBuckleType;
        private TextView tvCloseDialog;
        private LinearLayout mLlBukleOrder,mLlBukleStage;


        public Builder(Context context) {
            this.context = context;
//            createChineseQRCode(context);
        }

        public Builder() {
        }

        private Builder(Builder builder) {
        }

        public Builder mBuckleType(BuckleType val) {
            mBuckleType = val;
            return this;
        }

        public Builder mLlBukleOrder(LinearLayout val) {
            mLlBukleOrder = val;
            return this;
        }

        public Builder mLlBukleStage(LinearLayout val) {
            mLlBukleStage = val;
            return this;
        }
        public BuckleTypeDialog.Builder build() {
            return new Builder(this);
        }

        public BuckleTypeDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final BuckleTypeDialog dialog = new BuckleTypeDialog(context, R.style.MyDialogStyle);
            View layout = inflater.inflate(R.layout.activity_buckle_type, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            tvCloseDialog = ((TextView) layout.findViewById(R.id.tv_closeDialog));//关闭dialog
            mLlBukleOrder = ((LinearLayout) layout.findViewById(R.id.ll_bukleOrder));//
            mLlBukleStage = ((LinearLayout) layout.findViewById(R.id.ll_bukleStage));//

            tvCloseDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            mLlBukleOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBuckleType.buckleOrder("order");
                }
            });
            mLlBukleStage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBuckleType.buckleStage("stage");
                }
            });
            dialog.setContentView(layout);
            return dialog;
        }


    }

    public interface BuckleType {
        public void buckleOrder(String order);
        public void buckleStage(String stage);
    }

}
