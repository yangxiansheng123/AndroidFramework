package com.example.administrator.oper.fragmentfactorys;

import android.support.v4.app.Fragment;
import com.example.administrator.oper.ui.fragment.daily.CourseCIAuditioningStuFragment;
import com.example.administrator.oper.ui.fragment.daily.CourseCIReadingStuFragment;
import com.example.administrator.oper.ui.fragment.finance.ExpenditureFragment;
import com.example.administrator.oper.ui.fragment.finance.IncomeFragment;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/14 09
 * 邮箱：xxx@.qq.com
 * 课程签到
 */
public class FragmentCourseCheckInFactory {
    public static Fragment createForNoExpand(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CourseCIReadingStuFragment();//在读学员
                break;
            case 1:
                fragment = new CourseCIAuditioningStuFragment();//试听学员
                break;
            default:
                break;

        }
        return fragment;
    }
}
