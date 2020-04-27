package com.example.tiktokslider;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tiktokslider.databinding.ItemViewPagerBinding;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterInner> {

    private ItemViewPagerBinding itemViewPagerBinding;
     private int[] imgs = {R.drawable.img_video_1,R.drawable.img_video_2,R.drawable.img_video_1,R.drawable.img_video_2,R.drawable.img_video_1,R.drawable.img_video_2};
    private int[] videos = {R.raw.video_1,R.raw.video_2,R.raw.video_1,R.raw.video_2,R.raw.video_1,R.raw.video_2};
    private Context context;
    public MyAdapter(Context context) {
         this.context=context;
    }

    @NonNull
    @Override
    public MyAdapterInner onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           itemViewPagerBinding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_view_pager,parent,false);
           return new MyAdapterInner(itemViewPagerBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterInner holder, int position) {
       itemViewPagerBinding.imgThumb.setImageResource(imgs[position]);
        itemViewPagerBinding.videoView.setVideoURI(Uri.parse("android.resource://"+context.getPackageName()+"/"+ videos[position]));
    }


    @Override
    public int getItemCount() {
        return videos.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyAdapterInner extends RecyclerView.ViewHolder {
       /* ImageView img_thumb;
        VideoView videoView;
        ImageView img_play;
        RelativeLayout rootView;*/
        public MyAdapterInner(View itemView) {
            super(itemView);
            /*img_thumb = itemView.findViewById(R.id.img_thumb);
            videoView = itemView.findViewById(R.id.video_view);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);*/
        }
        }
    }

