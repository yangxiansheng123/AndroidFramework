package com.example.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


/**
 * Description:界面弹窗 有两个editext输入控件,一个Button控件,两个Textview控件
 * Company    :
 * 有输入框的dialg
 *
 * @author yang
 * 建造者模式
 */
public class EditextInputCenterDialog extends Dialog {


    public EditextInputCenterDialog(Context context) {
        super(context);
    }

    public EditextInputCenterDialog(Context context, int theme) {
        super(context, R.style.MyDialogStyle);
    }

    public static class Builder {
        private Context context;
        private ImageView imageView;
        private ButtonClickedEvent mForumActivitiesEvent;
        private EditText name, phone;
        private TextView mTvtitle;//标题
        private TextView mTvSubTitle;//副标题
         private String title;//标题
        private String SubTitle;//副标题


        public Builder(Context context) {
            this.context = context;
        }

        public Builder() {
        }


        public Builder imageView(ImageView val) {
            imageView = val;
            return this;
        }

        public Builder mForumActivitiesEvent(ButtonClickedEvent val) {
            mForumActivitiesEvent = val;
            return this;
        }

        public Builder name(EditText val) {
            name = val;
            return this;
        }

        public Builder phone(EditText val) {
            phone = val;
            return this;
        }

        public Builder mTitle(String val) {
            title = val;
            return this;
        }

        public Builder mSubTitle(String val) {
            SubTitle = val;
            return this;
        }

        public EditextInputCenterDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final EditextInputCenterDialog dialog = new EditextInputCenterDialog(context, R.style.MyDialogStyle);
            View layout = inflater.inflate(R.layout.edittext_dialog, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            name = ((EditText) layout.findViewById(R.id.et_inputName));
            phone = ((EditText) layout.findViewById(R.id.et_joinPhone));
            mTvtitle = ((TextView) layout.findViewById(R.id.tv_title));
            mTvSubTitle = ((TextView) layout.findViewById(R.id.tv_subTitle));

            ((Button) layout.findViewById(R.id.btn_nextButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(name.getText().toString().trim())) {
                        name.setError("请输内容");
                        return;
                    }
                    if (TextUtils.isEmpty(phone.getText().toString().trim())) {
                        phone.setError("请输内容");
                        return;
                    }
                    mForumActivitiesEvent.btnNextClicked(name.getText().toString().trim(), phone.getText().toString().trim());
                }
            });

            mTvtitle.setText(title);
            mTvSubTitle.setText(SubTitle);
            dialog.setContentView(layout);
            return dialog;
        }


    }

    public interface ButtonClickedEvent {
        public void btnNextClicked(String name, String phnoe);
    }

}
