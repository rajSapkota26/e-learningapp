package com.raj.eLearning.user.quiz.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raj.eLearning.R;
import com.raj.eLearning.database.SqliteService;
import com.raj.eLearning.model.ScoreBoard;
import com.raj.eLearning.model.User;
import com.raj.eLearning.user.quiz.quizadapter.ScoreAdapter;

import java.util.List;


public class LeaderBoardFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<ScoreBoard> scores;
    private SharedPreferences sharedPreferences;
    private SqliteService service;
    private ScoreAdapter adapter;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_leader_board, container, false);
        //fetch username
        sharedPreferences = getContext().getSharedPreferences("e-learning", getContext().MODE_PRIVATE);
        String username = sharedPreferences.getString("currentUser", null);
        recyclerView=view.findViewById(R.id.scorerecyclerView);
        service = new SqliteService(getContext());
        //get current use
        user = service.getUserByUserName(username);
        scores=service.getScore();


        adapter=new ScoreAdapter(getContext(),scores,user.getFullname());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}