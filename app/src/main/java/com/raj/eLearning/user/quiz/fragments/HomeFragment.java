package com.raj.eLearning.user.quiz.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raj.eLearning.R;
import com.raj.eLearning.jsonparse.LoadJsonFromAssert;
import com.raj.eLearning.model.CategoryModel;
import com.raj.eLearning.user.quiz.quizactivity.SpinnerActivity;
import com.raj.eLearning.user.quiz.quizadapter.CategoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
private RecyclerView recyclerView;
private CategoryAdapter categoryAdapter;
private List<CategoryModel> categoryModels;
private CategoryModel model;
private TextView spinwheel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.categoryList);
        spinwheel=view.findViewById(R.id.spinwheel);
        getData();
        spinwheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SpinnerActivity.class));
            }
        });

        return view;
    }

    void getData() {
        try{
            LoadJsonFromAssert loadJsonFromAssert=new LoadJsonFromAssert(getContext(),"quiz.json");
            JSONObject obj=new JSONObject(loadJsonFromAssert.getJson());
            JSONArray array=obj.getJSONArray("categories");
            categoryModels=new ArrayList<>();
            for( int i=0;i<array.length();i++){
                JSONObject o=array.getJSONObject(i);
                String id=(o.getString("id"));
                String title=o.getString("name");
                String imageUrl=o.getString("image");
                model=new CategoryModel(id,title,imageUrl);
                categoryModels.add(model);

            }
            categoryAdapter=new CategoryAdapter(getContext(),categoryModels);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
            recyclerView.setAdapter(categoryAdapter);

        }catch (JSONException e){
            e.printStackTrace();

        }
    }
}