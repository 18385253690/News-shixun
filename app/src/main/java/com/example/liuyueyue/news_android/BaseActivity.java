package com.example.liuyueyue.news_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        initView();
        initListener();
        initData();
    }
    //返回一个要显示的布局界面
    public abstract int getLayoutRes();
    //查找子控件
    public abstract void initView();
    //设置监听器
    public abstract void initListener();
    //初始化数据
    public abstract void initData();

    private Toast mToast;

    public void showToast(String msg){
        if(mToast == null){
            mToast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
