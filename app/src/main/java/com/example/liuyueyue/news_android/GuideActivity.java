package com.example.liuyueyue.news_android;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class GuideActivity extends BaseActivity {
        private ImageView iv01;
        private Button btnGo;
        private int count = 0;
    private MediaPlayer mMediaPlay;

    private void playBackgroundMusic(){
        try{
            AssetFileDescriptor fileDescriptor = getAssets().openFd("new_version.mp3");
            mMediaPlay = new MediaPlayer();
            mMediaPlay.setDataSource(fileDescriptor.getFileDescriptor(),
                    0L,fileDescriptor.getLength());
            mMediaPlay.setLooping(true);//循环播放
            mMediaPlay.setVolume(1.0f,1.0f);//z左，右声道音量
            mMediaPlay.prepare();//缓冲文件
            mMediaPlay.start();//开始播放

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        playBackgroundMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mMediaPlay != null){
            mMediaPlay.stop();
            mMediaPlay.release();
            mMediaPlay = null;
        }
    }

    private int[] imagesArray = new int[]{

        R.drawable.ad_new_version1_img1,
        R.drawable.ad_new_version1_img2,
        R.drawable.ad_new_version1_img3,
        R.drawable.ad_new_version1_img4,
        R.drawable.ad_new_version1_img5,
        R.drawable.ad_new_version1_img6,
        R.drawable.ad_new_version1_img7,
};
    private Handler mHander = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    startAnimation();
                    break;
            }
        }
    };
    public void initData(){
        startAnimation();
    }
    private void startAnimation() {
        count++;
        count = count % imagesArray.length;
        iv01.setBackgroundResource(imagesArray[count]);

        iv01.setScaleX(1.0f);
        iv01.setScaleY(1.0f);

        iv01.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(3500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mHander.sendEmptyMessageDelayed(0, 1000);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }
        @Override
        public int getLayoutRes () {
            return R.layout.activity_guide;
        }

        @Override
        public void initView () {
            iv01 = (ImageView) findViewById(R.id.iv_01);
            btnGo = (Button) findViewById(R.id.btn_go);
        }

    public void initListener() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterMainActivity();
            }
        });
    }

    private void enterMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}



