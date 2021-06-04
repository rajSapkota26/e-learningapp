package com.raj.eLearning.user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.raj.eLearning.R;
import com.raj.eLearning.adapters.VideoLectureAdapter;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.jsonparse.LoadJsonFromAssert;
import com.raj.eLearning.model.VideoLectureModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoLActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VideoLectureModel video;
    private List<VideoLectureModel> videos;
    private VideoLectureAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_l);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }
    }
    public void init(){
        recyclerView=findViewById(R.id.vl_rv);
        getData();
    }
    void getData() {
        try{
            LoadJsonFromAssert loadJsonFromAssert=new LoadJsonFromAssert(getApplicationContext(),"videos.json");
            JSONObject obj=new JSONObject(loadJsonFromAssert.getJson());
            JSONArray array=obj.getJSONArray("videos");
            videos=new ArrayList<>();
            for( int i=0;i<array.length();i++){
                JSONObject o=array.getJSONObject(i);
                int id=Integer.parseInt(o.getString("id"));
                String qns=o.getString("title");
                String cover=o.getString("cover");
                String link=o.getString("link");
                video=new VideoLectureModel(id,qns,link,cover);

                videos.add(video);

            }
            adapter=new VideoLectureAdapter(VideoLActivity.this,videos);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            recyclerView.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}