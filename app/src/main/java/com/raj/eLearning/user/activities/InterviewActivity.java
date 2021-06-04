package com.raj.eLearning.user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.raj.eLearning.R;
import com.raj.eLearning.adapters.InterviewQuestionAdapter;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.jsonparse.LoadJsonFromAssert;
import com.raj.eLearning.model.InterviewQuestionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InterviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<InterviewQuestionModel> questions;
    private InterviewQuestionModel question;
    private InterviewQuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }
    public void init(){
        recyclerView=findViewById(R.id.inter_rv);
        getData();
    }
    void getData() {
        try{
            LoadJsonFromAssert loadJsonFromAssert=new LoadJsonFromAssert(getApplicationContext(),"question.json");
            JSONObject obj=new JSONObject(loadJsonFromAssert.getJson());
            JSONArray array=obj.getJSONArray("ivQuestion");
            questions=new ArrayList<>();
            for( int i=0;i<array.length();i++){
                JSONObject o=array.getJSONObject(i);
                int id=Integer.parseInt(o.getString("id"));
                String qns=o.getString("question");
                String ans=o.getString("answer");
                question=new InterviewQuestionModel(id,qns,ans);

                questions.add(question);

            }
            adapter=new InterviewQuestionAdapter(getApplicationContext(),questions);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}