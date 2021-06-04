package com.raj.eLearning.user.quiz.quizadapter;

import android.content.Context;
import android.widget.Toast;

import com.raj.eLearning.jsonparse.LoadJsonFromAssert;
import com.raj.eLearning.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoadQuestion {
    private Context context;

    public LoadQuestion(Context context) {
        this.context = context;
    }

    public List<Question> getQuestion(String title){
        List<Question> questions=null;
        try{
            LoadJsonFromAssert loadJsonFromAssert=new LoadJsonFromAssert(context,"quiz.json");
            JSONObject obj=new JSONObject(loadJsonFromAssert.getJson());
            JSONArray array=obj.getJSONArray(title);
            questions=new ArrayList<>();
            for( int i=0;i<array.length();i++){
                JSONObject o=array.getJSONObject(i);
                String qns=o.getString("question");
                String op1=o.getString("option1");
                String op2=o.getString("option2");
                String op3=o.getString("option3");
                String op4=o.getString("option4");
                String ans=o.getString("ans");

                questions.add(new Question(qns,op1,op2,op3,op4,ans));
            }

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
        return  questions;
    }
}
