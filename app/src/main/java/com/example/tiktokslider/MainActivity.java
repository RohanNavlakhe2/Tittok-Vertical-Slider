package com.example.tiktokslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.OrientationHelper;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.tiktokslider.databinding.ActivityMainBinding;
import com.example.tiktokslider.viewpager.OnViewPagerListener;
import com.example.tiktokslider.viewpager.ViewPagerLayoutManager;
//instructions:
  //1. paste videoview and viewpager package into your project
  //2. paste MyAdapter class.
  //3. paste item_view_pager.xml.
  //4. paste all the images and icons from the drawable folder.
  //5. Now crate a raw resource directory and paste your video files into it.
        //(we can put audio and video files into raw directory)
       //To create raw directory,
         //right click res directory->new->android resource directory->select raw from the list->ok
  //5. paste the code from MainActivity and activity_main.xml to your Activity or Fragment
  //   where you want to create slider.
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MyAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView() {
        //configure recyclerview
          //Here ViewPagerLayoutManager is a custom LayoutManger which extends LinearLayoutManger
        mLayoutManager=new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        mAdapter=new MyAdapter(this);
        activityMainBinding.recycler.setLayoutManager(mLayoutManager);
       activityMainBinding.recycler.setAdapter(mAdapter);
    }

    private void initListener(){
        //Listener for viewpager
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext,int position) {
                //Pause video
                int index = 0;
                if (isNext){
                    index = 0;
                }else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position,boolean isBottom) {
                //play video
                playVideo(0);
            }


        });
    }

    private void playVideo(int position) {
        View itemView = activityMainBinding.recycler.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        //starting playing the video
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                //making thumb icon invisible (opacity to 0) with animation
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {
            //making video pasue or play when clicked at the middle of the screen
            boolean isPlaying = true;
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                }else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index){
        //stopping video
        View itemView = activityMainBinding.recycler.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();

        //makin thumb visible(opacity to 1) with animation
        imgThumb.animate().alpha(1).start();
        //making play button invisible (opacity to 0) with animation
        imgPlay.animate().alpha(0f).start();


    }

}
