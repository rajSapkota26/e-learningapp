package com.raj.eLearning.jsonparse;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class LoadJsonFromAssert {
    private  Context context;
    String fileName;

    public LoadJsonFromAssert(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public  String getJson(){
        String json=null;
        try{
            InputStream in=context.getAssets().open(fileName);
            int size=in.available();
            byte[] bbuffer=new byte[size];
            in.read(bbuffer);
            in.close();
            json=new String(bbuffer,"UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;

    }
}
