package com.hgsil.android.fragementtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class Fragment_1 extends Fragment{
    private View view;
    private TextView mTextView;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_1,container,false);
        mTextView = (TextView)view.findViewById(R.id.fragment1Text1);
        return view;
    }

    public void refresh(String title){
        mTextView.setText(title);
    }
}
