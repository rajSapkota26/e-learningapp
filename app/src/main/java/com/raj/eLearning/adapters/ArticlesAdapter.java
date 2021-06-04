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
import com.raj.eLearning.model.ArticlesModel;
import com.raj.eLearning.user.finalview.ArticleOpenViewActivity;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private Context context;
    private List<ArticlesModel> articles;

    public ArticlesAdapter(Context context, List<ArticlesModel> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_articles,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ArticlesModel article=articles.get(position);
        holder.title.setText(article.getTitle());
        holder.desc.setText(article.getDesc());
        Glide.with(context)
                .load(article.getImageUrl()).placeholder(R.drawable.learn) .fitCenter()
                .into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
            Intent i=new Intent(view.getContext(),ArticleOpenViewActivity.class);
            i.putExtra("file",article.getArticleLink());
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.article_title);
            desc=itemView.findViewById(R.id.article_description);
            imageView=itemView.findViewById(R.id.article_image);
        }
    }
}
