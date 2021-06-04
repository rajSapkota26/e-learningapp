package com.raj.eLearning.user.quiz.quizadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raj.eLearning.R;
import com.raj.eLearning.model.ScoreBoard;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    private Context context;
    private List<ScoreBoard> scores;
    private String userName;

    public ScoreAdapter(Context context, List<ScoreBoard> scores,String uName) {
        this.context = context;
        this.scores = scores;
        this.userName=uName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_leaderboards,null);
        return new ScoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScoreBoard scoreBoard=scores.get(position);
        holder.index.setText(""+position+1);
        holder.name.setText(userName);
        holder.score.setText(""+scoreBoard.getScore());

    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView index,name,score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            index=itemView.findViewById(R.id.index);
            name=itemView.findViewById(R.id.nameu);
            score=itemView.findViewById(R.id.scores);
        }
    }
}
