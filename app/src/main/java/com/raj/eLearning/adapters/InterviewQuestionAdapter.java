package com.raj.eLearning.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raj.eLearning.R;
import com.raj.eLearning.model.InterviewQuestionModel;

import java.util.List;

public class InterviewQuestionAdapter extends RecyclerView.Adapter<InterviewQuestionAdapter.ViewHolder> {
    private Context context;
    private List<InterviewQuestionModel> questions;

    public InterviewQuestionAdapter(Context context, List<InterviewQuestionModel> questions) {
        this.context = context;
        this.questions = questions;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_interview_question,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InterviewQuestionModel question=questions.get(position);
        holder.tv.setText(question.getId()+") "+question.getQuestion());
        holder.av.setText("Answer : "+question.getAnswer());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv,av;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.iv_question_title);
            av=itemView.findViewById(R.id.iv_ans_title);
        }
    }
}
