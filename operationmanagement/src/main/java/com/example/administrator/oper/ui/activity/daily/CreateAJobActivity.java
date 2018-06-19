package com.example.administrator.oper.ui.activity.daily;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.base.BaseToolbarActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.UploadingAttachmentsAdapter;
import com.example.administrator.oper.bean.edu.UploadAttachmentsBean;
import com.example.administrator.oper.soundrecording.DBHelper;
import com.example.administrator.oper.soundrecording.PlaybackFragment;
import com.example.administrator.oper.soundrecording.RecordingItem;
import com.example.administrator.oper.soundrecording.RecordingService;
import com.example.administrator.oper.soundrecording.listeners.OnDatabaseChangedListener;
import com.example.administrator.oper.ui.widget.MyListView;
import com.example.dialog.BottomDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.utils.ToolbarHelper;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.wangchenlong.datescroller.widget.DateScrollerDialog;
import org.wangchenlong.datescroller.widget.data.Type;
import org.wangchenlong.datescroller.widget.listener.OnDateSetListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：CreateAJobActivity
 */
public class CreateAJobActivity extends BaseToolbarActivity implements TextWatcher, View.OnClickListener, UploadingAttachmentsAdapter.DeleteAttachments, OnDatabaseChangedListener {

    /**
     * 100年
     */
    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L;
    protected TextView tvClassName;
    protected LinearLayout llSelectClass;
    protected TextView tvUploadFile;
    protected TextView tvSoundRecording;
    /**
     * 显示时间计时器
     */
    protected Chronometer mChronometer;
    protected MyListView mLvShowMyHomework;
    protected LinearLayout llSoundRecording;
    protected TextView tvCommitPreview;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    /**
     * 上次设置的时间
     */
    private long mLastTime = System.currentTimeMillis();
    private String birthday;
    protected EditText etInputDetailsQuestion;
    protected TextView tvNumberRestriction;
    protected TextView tvJobAttachmentFormat;
    protected LinearLayout llJobAttachmentFormat;
    protected TextView tvCreateJobTime;
    protected LinearLayout llCreateJobTime;
    private UploadingAttachmentsAdapter mUploadingAttachmentsAdapter;
    /**
     * 存储上传附件
     */
    private List<UploadAttachmentsBean> listData = new ArrayList<>();
    private List<UploadAttachmentsBean> listVPData = new ArrayList<>();
    UploadAttachmentsBean mUploadAttachmentsBean;

    //输入最多字体
    private int MAX_NUM = 300;
    /**
     * 标记录音状态
     */
    private boolean mStartRecording = true;
    //stores time when user clicks pause button
    long timeWhenPaused = 0;

    private DBHelper mDatabase;
    private RecordingItem item;
    private BottomDialog bootomDialog;
    private int chooseMode;
    private int themeId;
    private List<LocalMedia> selectList = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_create_ajob;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_create_ajob);
        mDatabase = new DBHelper(this);
        DBHelper.setOnDatabaseChangedListener(this);
        initView();
        themeId = R.style.picture_default_style;

        /**
         *play radio
         */
        mLvShowMyHomework.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (listData.get(position).getFileType().equals("audio")) {
                    PlaybackFragment playbackFragment =
                            new PlaybackFragment().newInstance(getItem(position));
                    FragmentTransaction transaction = getSupportFragmentManager()
                            .beginTransaction();
                    playbackFragment.show(transaction, "dialog_playback");
                } else if (listData.get(position).getFileType().equals("pic")) {
                    PictureSelector.create(CreateAJobActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);

                } else if (listData.get(position).getFileType().equals("video")) {
                    PictureSelector.create(CreateAJobActivity.this).externalPictureVideo(listData.get(position).getTitle());

                }
            }
        });

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(CreateAJobActivity.this);
                } else {
                    Toast.makeText(CreateAJobActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(getString(R.string.title_activity_create_ajob));
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
        mUploadingAttachmentsAdapter = new UploadingAttachmentsAdapter(this, this);

        etInputDetailsQuestion = (EditText) findViewById(R.id.et_inputDetailsQuestion);
        tvNumberRestriction = (TextView) findViewById(R.id.tv_numberRestriction);
        etInputDetailsQuestion.addTextChangedListener(this);
        tvJobAttachmentFormat = (TextView) findViewById(R.id.tv_jobAttachmentFormat);
        llJobAttachmentFormat = (LinearLayout) findViewById(R.id.ll_jobAttachmentFormat);
        llJobAttachmentFormat.setOnClickListener(CreateAJobActivity.this);
        tvCreateJobTime = (TextView) findViewById(R.id.tv_createJobTime);
        llCreateJobTime = (LinearLayout) findViewById(R.id.ll_createJobTime);
        llCreateJobTime.setOnClickListener(CreateAJobActivity.this);
        tvClassName = (TextView) findViewById(R.id.tv_className);
        llSelectClass = (LinearLayout) findViewById(R.id.ll_selectClass);
        llSelectClass.setOnClickListener(CreateAJobActivity.this);
        tvUploadFile = (TextView) findViewById(R.id.tv_uploadFile);
        tvUploadFile.setOnClickListener(CreateAJobActivity.this);
        tvSoundRecording = (TextView) findViewById(R.id.tv_soundRecording);
        tvSoundRecording.setOnClickListener(CreateAJobActivity.this);
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mLvShowMyHomework = (MyListView) findViewById(R.id.lv_showMyHomework);
        llSoundRecording = (LinearLayout) findViewById(R.id.ll_soundRecording);

        mUploadingAttachmentsAdapter.setListData(listData);
        mLvShowMyHomework.setAdapter(mUploadingAttachmentsAdapter);
        tvCommitPreview = (TextView) findViewById(R.id.tv_commitPreview);
        tvCommitPreview.setOnClickListener(CreateAJobActivity.this);

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
//        int num = MAX_NUM - s.length();
        tvNumberRestriction.setText(String.valueOf(s.length()) + "/ 300字");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.ll_jobAttachmentFormat) {

            intent.setClass(CreateAJobActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "作业格式");
            startActivityForResult(intent, 3000);
        } else if (view.getId() == R.id.ll_createJobTime) {

            showDate();
        } else if (view.getId() == R.id.ll_selectClass) {
            intent.setClass(this, SelectClassActivity.class);
            startActivityForResult(intent, 3000);
        } else if (view.getId() == R.id.tv_uploadFile) {
            showBootomDialog();
        } else if (view.getId() == R.id.tv_soundRecording) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.RECORD_AUDIO}, 1);
            } else {
                onRecord(mStartRecording);

                mStartRecording = !mStartRecording;
            }
        } else if (view.getId() == R.id.tv_commitPreview) {
            intent.setClass(this, CommitPreviewHomeworkctivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3000) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                tvJobAttachmentFormat.setText(type);
                tvJobAttachmentFormat.setTextColor(Color.parseColor("#333333"));
            } else if (resultCode == 4000) {
                String type = data.getStringExtra("type");
                //选择班级
                tvClassName.setText(type);
                tvClassName.setTextColor(Color.parseColor("#333333"));
            }
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                        if (chooseMode == 1) {
                            String title = media.getPath().substring(media.getPath().length() - 10, media.getPath().length());
                            mUploadAttachmentsBean = new UploadAttachmentsBean(media.getPath(), "pic");
                            listVPData.add(mUploadAttachmentsBean);
                            listData.add(mUploadAttachmentsBean);
                        } else if (chooseMode == 2) {
                            String title = media.getPath().substring(media.getPath().length() - 10, media.getPath().length());
                            mUploadAttachmentsBean = new UploadAttachmentsBean(media.getPath(), "video");
                            listVPData.add(mUploadAttachmentsBean);
                            listData.add(mUploadAttachmentsBean);
                        }
                    }
                    mUploadingAttachmentsAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
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
                .setTitleStringId("请选择截至日期")
                .setMinMilliseconds(System.currentTimeMillis() - HUNDRED_YEARS)
                .setMaxMilliseconds(System.currentTimeMillis())
                .setCurMilliseconds(mLastTime)
                .setCallback(mOnDateSetListener)
                .build();

        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show(getSupportFragmentManager(), "year_month_day");
            }
        }
    }

    /**
     * 数据的回调
     */
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            birthday = getDateToString(milliseconds);
            //回调选中的数据
            tvCreateJobTime.setText(birthday);
            tvCreateJobTime.setTextColor(Color.parseColor("#333333"));
        }
    };

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    /**
     * Recording Start/Stop
     *
     * @param start
     */
    private void onRecord(boolean start) {

        Intent intent = new Intent(this, RecordingService.class);

        if (start) {
            // start recording
            tvSoundRecording.setCompoundDrawablesWithIntrinsicBounds
                    (R.mipmap.tape_1, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvSoundRecording.setBackground(ContextCompat.getDrawable(this, R.drawable.solid_shape_red));
            }
            tvSoundRecording.setTextColor(Color.parseColor("#ffffff"));
            tvSoundRecording.setText("停止录音");
            File folder = new File(Environment.getExternalStorageDirectory() + "/SoundRecorder");
            if (!folder.exists()) {
                //folder /SoundRecorder doesn't exist, create the folder
                folder.mkdir();
            }

            //start Chronometer
            mChronometer.setBase(SystemClock.elapsedRealtime());
            mChronometer.start();
            mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    /**
                     * 为计时器绑定监听事件
                     */
                }
            });
            llSoundRecording.setVisibility(View.VISIBLE);
            //start RecordingService
            this.startService(intent);
            //keep screen on while recording
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        } else {
            //stop recording
            llSoundRecording.setVisibility(View.GONE);
            tvSoundRecording.setCompoundDrawablesWithIntrinsicBounds
                    (R.mipmap.tape_0, 0, 0, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                tvSoundRecording.setBackground(getResources().getDrawable(R.drawable.solid_shape_gray));
            }
            tvSoundRecording.setTextColor(Color.parseColor("#333333"));
            tvSoundRecording.setText("开始录音");
            //mPauseButton.setVisibility(View.GONE);
            mChronometer.stop();
            mChronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenPaused = 0;

            this.stopService(intent);
            //allow the screen to turn off again once recording is finished
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


            listData.clear();
            findSoundRecording();
        }

    }

    /**
     * 查询音频
     */
    private void findSoundRecording() {

        for (int position = 0; position < mDatabase.getCount(); position++) {
            item = getItem(position);
            mUploadAttachmentsBean = new UploadAttachmentsBean(item.getName(), "audio");
            listData.add(mUploadAttachmentsBean);
//            Log.e("count :", mDatabase.getCount() + "");
        }
        for (int i = 0; i < listVPData.size(); i++) {
            mUploadAttachmentsBean = new UploadAttachmentsBean(listVPData.get(i).getTitle(), listVPData.get(i).getFileType());
            listData.add(mUploadAttachmentsBean);
        }

        mUploadingAttachmentsAdapter.notifyDataSetChanged();
    }

    /**
     * 删除附件
     *
     * @param
     * @param position
     */
    @Override
    public void deleteItem(int position) {
        if (listData.get(position).getFileType().equals("audio")) {
            item = getItem(position);
            File file = new File(item.getFilePath());
            file.delete();
            mDatabase.removeItemWithId(item.getId());
            listData.remove(position);
        } else if (listData.get(position).getFileType().equals("pic")) {
            listData.remove(position);
        } else if (listData.get(position).getFileType().equals("video")) {
            listData.remove(position);
        }
        mUploadingAttachmentsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onNewDatabaseEntryAdded() {
        //item added to top of the list
//        listData.clear();
        findSoundRecording();
    }

    @Override
    public void onDatabaseEntryRenamed() {

    }


    public RecordingItem getItem(int position) {
        return mDatabase.getItemAt(position);
    }


    public void showBootomDialog() {
        bootomDialog = (BottomDialog) BottomDialog.create(getSupportFragmentManager())
                .setViewListener(new BottomDialog.ViewListener() {
                    @Override
                    public void bindView(View v) {
                        initViewBottom(v);
                    }
                })
                .setLayoutRes(R.layout.activity_create_ajob_bottom_dialog)
                .setDimAmount(0.5f)
                .setTag("BottomDialog")
                .setCancelOutside(true)
                .show();
    }

    private void initViewBottom(View v) {
        v.findViewById(R.id.ll_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bootomDialog.cancel();
                chooseMode = PictureMimeType.ofImage();
                selectList.clear();
                onAddPicClick();
            }
        });

        v.findViewById(R.id.ll_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bootomDialog.cancel();
                chooseMode = PictureMimeType.ofVideo();
                selectList.clear();
                onAddPicClick();
            }
        });
    }


    public void onAddPicClick() {
        boolean mode = true;
        if (mode) {
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(CreateAJobActivity.this)
                    .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(3)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(true ?
                            PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .previewVideo(true)// 是否可预览视频
                    .enablePreviewAudio(false) // 是否可播放音频
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                    .enableCrop(false)// 是否裁剪
                    .compress(true)// 是否压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true ? false : true)// 是否显示uCrop工具栏，默认不显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(selectList)// 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.videoSecond()//显示多少秒以内的视频or音频也可适用
                    //.recordVideoSecond()//录制视频秒数 默认60s
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        } else {
            // 单独拍照
            PictureSelector.create(CreateAJobActivity.this)
                    .openCamera(chooseMode)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                    .theme(themeId)// 主题样式设置 具体参考 values/styles
                    .maxSelectNum(3)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .selectionMode(true ?
                            PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .previewVideo(true)// 是否可预览视频
                    .enablePreviewAudio(true) // 是否可播放音频
                    .isCamera(true)// 是否显示拍照按钮
                    .enableCrop(false)// 是否裁剪
                    .compress(true)// 是否压缩
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true ? false : true)// 是否显示uCrop工具栏，默认不显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(selectList)// 是否传入已选图片
                    .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 裁剪压缩质量 默认为100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled() // 裁剪是否可旋转图片
                    //.scaleEnabled()// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    //.videoSecond()////显示多少秒以内的视频or音频也可适用
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int position = 0; position < mDatabase.getCount(); position++) {
            File file = new File(mDatabase.getItemAt(position).getFilePath());
            file.delete();
            mDatabase.removeItemWithId(mDatabase.getItemAt(position).getId());
        }

        if (bootomDialog != null) {
            bootomDialog.cancel();
        }
    }


}
