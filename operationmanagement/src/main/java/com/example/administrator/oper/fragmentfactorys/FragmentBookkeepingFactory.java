package com.example.administrator.oper.fragmentfactorys;

import android.support.v4.app.Fragment;
import com.example.administrator.oper.ui.fragment.finance.ExpenditureFragment;
import com.example.administrator.oper.ui.fragment.finance.IncomeFragment;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/14 09
 * 邮箱：xxx@.qq.com
 * 添加记账
 */
public class FragmentBookkeepingFactory {
    public static Fragment createForNoExpand(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ExpenditureFragment();//支出
                break;
            case 1:
                fragment = new IncomeFragment();//收入
                break;


        }
        return fragment;
    }
}
