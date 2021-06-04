package com.raj.eLearning.iswifi;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingContent {
    private Context context;
    ProgressDialog progressDialog;

    public LoadingContent(Context context) {
        this.context = context;
        progressDialog= new ProgressDialog(context);    }
    public void showDialogue(){
//         ProgressDialog progressDialog= new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public void hideDialogue(){
        progressDialog.dismiss();
    }
}
