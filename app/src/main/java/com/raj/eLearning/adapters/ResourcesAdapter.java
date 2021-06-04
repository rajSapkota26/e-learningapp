package com.raj.eLearning.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raj.eLearning.R;
import com.raj.eLearning.model.ResourcesModel;

import java.util.List;

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ViewHolder> {
    private Context context;
    private List<ResourcesModel> resources;

    public ResourcesAdapter(Context context, List<ResourcesModel> res) {
        this.context = context;
        this.resources = res;
    }
    @NonNull
    @Override
    public ResourcesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourcesAdapter.ViewHolder holder, int position) {
        ResourcesModel res= resources.get(position);
        holder.tv.setText(res.getId()+" "+res.getTitle());
        holder.dec.setText(res.getLink());
    }

    @Override
    public int getItemCount() {
        return resources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv,dec;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.resource_title);
            dec=itemView.findViewById(R.id.resource_desc);
        }
    }
}
