package com.raj.eLearning.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.raj.eLearning.R;
import com.raj.eLearning.model.VideoLectureModel;
import com.raj.eLearning.user.finalview.VideoOpenViewActivity;

import java.util.List;

public class VideoLectureAdapter extends RecyclerView.Adapter<VideoLectureAdapter.ViewHolder> {
    private Context context;
    private List<VideoLectureModel> videos;

    public VideoLectureAdapter(Context context, List<VideoLectureModel> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoLectureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_videos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoLectureAdapter.ViewHolder holder, int position) {
        VideoLectureModel video=videos.get(position);
        holder.tv.setText(video.getTitle());

        Glide.with(context)
                .load(video.getCoverImage()).placeholder(R.drawable.videocover)
                .into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1=new Intent(view.getContext(), VideoOpenViewActivity.class);
                int1.putExtra("vdo",video.getVideoUrl());
                context.startActivity(int1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.video_title);
            iv=itemView.findViewById(R.id.video_image);
        }
    }
}
