package com.raj.eLearning.user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.raj.eLearning.R;
import com.raj.eLearning.adapters.ArticlesAdapter;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.jsonparse.LoadJsonFromAssert;
import com.raj.eLearning.model.ArticlesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity {
    private List<ArticlesModel> articles;
    private ArticlesModel article;
    private ArticlesAdapter adapter;
    private RecyclerView recyclerView;
//    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }

    }
    public void init(){
        recyclerView=findViewById(R.id.article_rv);
        getData();
    }
   public void getData() {
        try{
            LoadJsonFromAssert loadJsonFromAssert=new LoadJsonFromAssert(getApplicationContext(),"articles.json");
            JSONObject obj=new JSONObject(loadJsonFromAssert.getJson());
            JSONArray array=obj.getJSONArray("articles");
            articles=new ArrayList<>();
            for( int i=0;i<array.length();i++){
                JSONObject o=array.getJSONObject(i);
                int id=Integer.parseInt(o.getString("id"));
                String title=o.getString("title");
                String desc=o.getString("desc");
                String imageUrl=o.getString("imageUrl");
                String articleLink=o.getString("articleLink");
                article=new ArticlesModel(id,title,imageUrl,desc,articleLink);
                articles.add(article);

            }
            adapter=new ArticlesAdapter(ArticlesActivity.this,articles);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}