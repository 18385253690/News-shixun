package com.example.liuyueyue.news_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

public class StartActivity extends BaseActivity{
    @Override
    public int getLayoutRes() {
        return R.layout.activity_start;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
      new Thread(){


          public void run(){
              SystemClock.sleep(1500);
              boolean firstRun = SharedPrefUtil.getBoolean(getApplicationContext(), "firstRun", true);
              if(firstRun){
                  SharedPrefUtil.saveBoolean(StartActivity.this,
                          "firstRun", false);
                  enterGuideActivity();
              }else {
                  enterGuideActivity();
              }
          }
      }.start();
    }

    private void enterGuideActivity() {
        Intent intent = new Intent(this,GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private static class SharedPrefUtil {
        private static String FILE = "news";

        public static boolean getBoolean(Context context, String key, boolean def) {
            SharedPreferences sp = context.getSharedPreferences(FILE,context.MODE_PRIVATE);
            return sp.getBoolean(key,def);
        }

        public static void saveBoolean(StartActivity context, String key, boolean value) {
            SharedPreferences sp = context.getSharedPreferences(FILE,context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
        }
    }
}
