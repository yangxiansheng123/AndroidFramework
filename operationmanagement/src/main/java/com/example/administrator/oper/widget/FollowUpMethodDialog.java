package com.example.administrator.oper.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.oper.R;


/**
 * Description:
 * Company    :
 * Author     : dyamail
 * 跟进记录-->跟进方式
 */
public class FollowUpMethodDialog extends Dialog  {


    public FollowUpMethodDialog(Context context) {
        super(context);
    }

    public FollowUpMethodDialog(Context context, int theme) {
        super(context, R.style.MyDialogStyle);
    }


    public static class Builder {
        private Context context;
        private ReturnResult mReturnResult;
        private TextView tvPhoneFollowUp, tvWxFollowUp,tvShopFollowUp,tvOtherFollowUp;
        //输入最多字体
        private int MAX_NUM = 450;


        private Builder(Builder builder) {
        }

        public Builder(Context context) {
            this.context = context;
        }

        public Builder() {
        }

        public Builder context(Context val) {
            context = val;
            return this;
        }

        public Builder mReturnResult(ReturnResult val) {
            mReturnResult = val;
            return this;
        }

        public FollowUpMethodDialog.Builder build() {
            return new Builder(this);
        }


        public FollowUpMethodDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final FollowUpMethodDialog dialog = new FollowUpMethodDialog(context, R.style.MyDialogStyle);
            View layout = inflater.inflate(R.layout.activity_follow_up_dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


            tvPhoneFollowUp = ((TextView) layout.findViewById(R.id.tv_phoneFollowUp));
            tvWxFollowUp = ((TextView) layout.findViewById(R.id.tv_wxFollowUp));
            tvShopFollowUp = ((TextView) layout.findViewById(R.id.tv_shopFollowUp));
            tvOtherFollowUp = ((TextView) layout.findViewById(R.id.tv_otherFollowUp));

            tvPhoneFollowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    mReturnResult.getResult("电话");
                }
            });

            tvWxFollowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    mReturnResult.getResult("短信/微信");
                }
            });
            tvShopFollowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    mReturnResult.getResult("到店接待");
                }
            });
            tvOtherFollowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    mReturnResult.getResult("其他");
                }
            });
            dialog.setContentView(layout);
            return dialog;
        }


    }

    public interface ReturnResult {
        public void getResult(String order);

    }

}
