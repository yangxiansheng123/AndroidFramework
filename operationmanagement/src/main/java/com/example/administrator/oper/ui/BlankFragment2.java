package com.example.administrator.oper.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.oper.R;


/**
 *
 */
public class BlankFragment2 extends Fragment {

    protected View rootView;
    protected TextView mTvTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        initView(rootView);
        mTvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ExampleActivity.class));
            }
        });
        return rootView;
    }


    /**
     * 初始化控件
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mTvTest = (TextView) rootView.findViewById(R.id.tv_test);
    }

}
