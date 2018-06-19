package com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 *
 * @author yang
 * @date /10/8/0008
 * 艺魔方存储相关信息
 */

public class UpdateBrowseRecordsInformation {
    /**
     * 用户数据信息添加操作
     *
     * @author Administrator
     */
    public static void add(Context context, String user_id, String user_head_portrait, String user_nickname) {
        ArtMoFangSqliteOpenHelper helper = new ArtMoFangSqliteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", user_id);
        values.put("user_head_portrait", user_head_portrait);
        values.put("user_nickname", user_nickname);
        long count = db.insert("update_head_portrait", null, values);
        if (count > 0) {
            Toast.makeText(context, "用户添加成功", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "用户添加失败", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 查询
     *
     * @param context
     * @return
     */
    public static Cursor findAll(Context context, String userId) {
        ArtMoFangSqliteOpenHelper helper = new ArtMoFangSqliteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql;
        Cursor c = null;
        if (userId == null || userId.equals("")) {
            sql = "select * from update_head_portrait where user_id =?";
            c = db.rawQuery(sql, new String[]{userId});
        }

        return c;

    }

    /**
     * 查找历史搜索记录
     *
     * @param context
     * @return
     */
    public static Cursor findHistoryRecords(Context context, String recordsId) {
        ArtMoFangSqliteOpenHelper helper =  ArtMoFangSqliteOpenHelper.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql;
        Cursor c = null;
        if (recordsId != null || !recordsId.equals("")) {
            sql = "select * from search_history_records where recordsId =?";
            c = db.rawQuery(sql, new String[]{recordsId});
        }

        return c;

    }

    /**
     * 存储用户消息搜索历史记录
     */
    public static void addsearchHistoryRecords(Context context, String keyWords, String recordsId) {
        ArtMoFangSqliteOpenHelper helper =  ArtMoFangSqliteOpenHelper.getInstance(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("keyWords", keyWords);
        values.put("recordsId", recordsId);
        long count = db.insert("search_history_records", null, values);
        if (count > 0) {
//            Toast.makeText(context, "历史记录保存成功", Toast.LENGTH_LONG).show();
        } else {
//            Toast.makeText(context, "历史记录保存失败", Toast.LENGTH_LONG).show();
        }
    }

    


    /**
     * 存储存储上课时间设置记录
     */
    public static void artAddsearchHistoryRecords(Context context, String keyWords, String recordsId) {
        ArtMoFangSqliteOpenHelper helper =  ArtMoFangSqliteOpenHelper.getInstance(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("keyWords", keyWords);
        values.put("recordsId", recordsId);
        long count = db.insert("art_search_history_records", null, values);
        if (count > 0) {
//            Toast.makeText(context, "历史记录保存成功", Toast.LENGTH_LONG).show();
        } else {
//            Toast.makeText(context, "历史记录保存失败", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 艺论坛查找历史搜索记录
     *
     * @param context
     * @return
     */
    public static Cursor artFindHistoryRecords(Context context, String recordsId) {
        ArtMoFangSqliteOpenHelper helper =  ArtMoFangSqliteOpenHelper.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql;
        Cursor c = null;
        if (recordsId != null || !recordsId.equals("")) {
            sql = "select * from art_search_history_records where recordsId =?";
            c = db.rawQuery(sql, new String[]{recordsId});
        }

        return c;

    }

    /**
     * 删除用户消息搜索历史记录
     */
    public static int deletehHistoryRecords(Context context) {
        ArtMoFangSqliteOpenHelper helper = ArtMoFangSqliteOpenHelper.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        int state = db.delete("art_search_history_records", null, null);
        return state;
    }


    /**
     * 删除存储上课时间设置记录
     */
    public static int artDeletehHistoryRecords(Context context) {
        ArtMoFangSqliteOpenHelper helper = ArtMoFangSqliteOpenHelper.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        int state = db.delete("art_search_history_records", null, null);
        return state;
    }

    /**
     * 存储用户浏览记录
     */
    public static void addBrowseInfo(Context context, String schoolName, String schoolId, String user_nickname) {
        ArtMoFangSqliteOpenHelper helper = new ArtMoFangSqliteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("11", schoolName);
        values.put("11", schoolId);
        long count = db.insert("browse_records", null, values);
        if (count > 0) {
            Toast.makeText(context, "浏览记录添加成功", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "浏览记录添加失败", Toast.LENGTH_LONG).show();
        }
    }

    //    修改
    public static void updateUserName(Context context, String name, String userId) {
        ArtMoFangSqliteOpenHelper helper = new ArtMoFangSqliteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        //修改SQL语句
        String sql = "update family_member set name = ? where userId=?";
        //执行SQL
        db.execSQL(sql, new Object[]{name, userId});

    }
}
