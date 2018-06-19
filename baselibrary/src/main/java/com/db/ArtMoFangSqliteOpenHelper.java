package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author yang
 * 数据库操作类 FenjixiezuoSqliteOpenHelper
 */
public class ArtMoFangSqliteOpenHelper extends SQLiteOpenHelper {
    private Context mcContext;
    private final static String DB_NAME = "artmofang.db";
    static ArtMoFangSqliteOpenHelper mInstance;

    /**
     * 构造函数
     *
     * @param context
     */

    public ArtMoFangSqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
        mcContext = context;
    }

    public synchronized static ArtMoFangSqliteOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ArtMoFangSqliteOpenHelper(context);
        }
        return mInstance;
    }

    ;

    /**
     * 进行表的创建
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //存储用户消息搜索历史记录(save_search_history_records)
        String save_search_history_records = "create table search_history_records(id integer primary key autoincrement,keyWords text,recordsId text)";
        db.execSQL(save_search_history_records);

        //存储用户浏览记录(save_browse_records)
        String save_browse_records = "create table browse_records(id integer primary key " +
                "autoincrement,user_id text,user_head_portrait text,user_nickname text,telephone text)";
        db.execSQL(save_browse_records);


        //存储上课时间设置(save_search_history_records)
        String art_save_search_history_records = "create table art_search_history_records(id integer primary key autoincrement,keyWords text,recordsId text)";
        db.execSQL(art_save_search_history_records);
    }


    /**
     * 数据库版本更改的时候调用
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // 数据库版本更新时         
        String sql = "drop table if exists " + DB_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

}
