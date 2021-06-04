package com.raj.eLearning.user.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.raj.eLearning.R;
import com.raj.eLearning.adapters.EBookAdapter;
import com.raj.eLearning.iswifi.CheckingConnection;
import com.raj.eLearning.jsonparse.LoadJsonFromAssert;
import com.raj.eLearning.model.EBookModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EbookActivity extends AppCompatActivity {
    private List<EBookModel> books;
    private EBookModel book;
    private RecyclerView recyclerView;
    private EBookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
        if (! new CheckingConnection(this).getConnectionStatus(this)){

        }else {
            init();
        }

    }
    public void init(){
        recyclerView=findViewById(R.id.book_rv);
        getData();
    }
    void getData() {
        try{
            LoadJsonFromAssert loadJsonFromAssert=new LoadJsonFromAssert(getApplicationContext(),"ebooks.json");
            JSONObject obj=new JSONObject(loadJsonFromAssert.getJson());
            JSONArray array=obj.getJSONArray("ebooks");
            books=new ArrayList<>();
            for( int i=0;i<array.length();i++){
                JSONObject o=array.getJSONObject(i);
                int id=Integer.parseInt(o.getString("id"));
                String title=o.getString("title");
                String imageUrl=o.getString("bookcover");
                String articleLink=o.getString("link");

                book=new EBookModel(id,title,articleLink,imageUrl);
               books.add(book);

            }
            adapter=new EBookAdapter(EbookActivity.this,books);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            recyclerView.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}