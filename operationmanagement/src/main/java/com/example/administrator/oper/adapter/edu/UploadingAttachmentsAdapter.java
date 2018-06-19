package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.edu.UploadAttachmentsBean;
import com.example.administrator.oper.db.ClassTimeSettingBean;

import java.util.List;

/**
 * @author yang
 * 上传附件 展示音频,视频,图片 适配器
 */
public class UploadingAttachmentsAdapter extends BaseAdapter {

    private DeleteAttachments mDeleteAttachments;
    private List<UploadAttachmentsBean> listData;
    private Context mContext;
    private LayoutInflater inflater;
    private String title;


    public UploadingAttachmentsAdapter(Context context, DeleteAttachments deleteAttachments) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        mDeleteAttachments = deleteAttachments;
    }


    public void setListData(List<UploadAttachmentsBean> listData) {
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
            convertView = inflater.inflate(R.layout.activity_upload_attachments_pattern, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (listData.get(position).getFileType().equals("pic")) {
            title = listData.get(position).getTitle().substring(listData.get(position).getTitle().length() - 10, listData.get(position).getTitle().length());
            holder.img_attachmentType.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.work_icon));
            holder.tv_attachmentName.setText(title);
        } else if (listData.get(position).getFileType().equals("video")) {
            title = listData.get(position).getTitle().substring(listData.get(position).getTitle().length() - 10, listData.get(position).getTitle().length());
            holder.img_attachmentType.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.video_icon));
            holder.tv_attachmentName.setText(title);
        } else if (listData.get(position).getFileType().equals("audio")) {
                 holder.img_attachmentType.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.audio));
            holder.tv_attachmentName.setText(listData.get(position).getTitle());
        }
        holder.tv_deleteAttachments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDeleteAttachments.deleteItem(position);

            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView tv_attachmentName;
        TextView tv_deleteAttachments;
        ImageView img_attachmentType;

        public ViewHolder(View view) {
            tv_attachmentName = (TextView) view.findViewById(R.id.tv_attachmentName);
            tv_deleteAttachments = (TextView) view.findViewById(R.id.tv_deleteAttachments);
            img_attachmentType = (ImageView) view.findViewById(R.id.img_attachmentType);
        }
    }

    public interface DeleteAttachments {
        public void deleteItem(int position);

    }
}
