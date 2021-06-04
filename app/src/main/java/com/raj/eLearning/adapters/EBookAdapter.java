package com.raj.eLearning.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.raj.eLearning.R;
import com.raj.eLearning.model.EBookModel;
import com.raj.eLearning.user.finalview.BookOpenViewActivity;

import java.util.List;

public class EBookAdapter extends RecyclerView.Adapter<EBookAdapter.ViewHolder> {
    private Context context;
    private List<EBookModel> books;

    public EBookAdapter(Context context, List<EBookModel> books) {
        this.context = context;
        this.books = books;
    }
    @NonNull
    @Override
    public EBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_ebook,parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EBookAdapter.ViewHolder holder, int position) {
        EBookModel book=books.get(position);
        holder.tv.setText(book.getBookName());

        Glide.with(context).load(book.getImageUrl()).placeholder(R.drawable.java).into(holder.iv);

        holder.itemView.setOnClickListener(view -> {
            Intent intent=new Intent(view.getContext(),BookOpenViewActivity.class);
            intent.putExtra("pdfLink",book.getDownloadLink());
            context.startActivity(intent);

        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse(book.getDownloadLink()));
                 context.startActivity(intent);

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.ebook_title);
            iv=itemView.findViewById(R.id.ebook_image);
        }
    }
}
