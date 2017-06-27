package com.example.liuyueyue.news_android;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by liuyueyue on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment {
    private View mRoot;
    protected Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(mRoot==null){
            mRoot=LayoutInflater.from(mActivity).inflate(getLayoutRes(),container,false);
            initView();
            initListener();
            initData();
        }
        return mRoot;
    }
    //返回一个布局界面
    public abstract int getLayoutRes();
    //查找布局中的子控件
    protected abstract void initView();
    //设置监听器
    protected abstract void initListener();
    //初始化数据
    protected abstract void initData();

    private Toast mToast;

    private void showToast(String msg){
        if(mToast == null){
            mToast = Toast.makeText(mActivity,"",Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
