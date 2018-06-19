package com.example.administrator.oper.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.oper.R;


/**
 * Description:
 * Company    :
 * Author     : dyamail
 * 跟进记录-->添加快速跟进
 */
public class AddQuickFollowUpDialog extends Dialog  {


    public AddQuickFollowUpDialog(Context context) {
        super(context);
    }

    public AddQuickFollowUpDialog(Context context, int theme) {
        super(context, R.style.MyDialogStyle);
    }


    public static class Builder implements TextWatcher{
        private Context context;
        private ReturnResult mReturnResult;
        private EditText etInputDetailsQuestion;
        private TextView tvNumberRestriction;
        private TextView tvCancel, tvConfirm;
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

        public AddQuickFollowUpDialog.Builder build() {
            return new Builder(this);
        }


        public AddQuickFollowUpDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final AddQuickFollowUpDialog dialog = new AddQuickFollowUpDialog(context, R.style.MyDialogStyle);
            View layout = inflater.inflate(R.layout.activity_add_quick_follow_up_dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            etInputDetailsQuestion = ((EditText) layout.findViewById(R.id.et_inputDetailsQuestion));

            etInputDetailsQuestion.addTextChangedListener(this);

            tvNumberRestriction = ((TextView) layout.findViewById(R.id.tv_numberRestriction));

            //关闭dialog
            tvCancel = ((TextView) layout.findViewById(R.id.tv_cancel));
            tvConfirm = ((TextView) layout.findViewById(R.id.tv_confirm));

            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            tvConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    mReturnResult.getResult(etInputDetailsQuestion.getText().toString());
                }
            });

            dialog.setContentView(layout);
            return dialog;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
            if (s.length() > MAX_NUM) {
                s.delete(MAX_NUM, s.length());
            }
        int num = MAX_NUM - s.length();
        tvNumberRestriction.setText(String.valueOf(s.length()) + "/ 300字");
        }
    }

    public interface ReturnResult {
        public void getResult(String order);

    }

}
