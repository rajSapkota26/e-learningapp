package com.raj.eLearning.iswifi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.raj.eLearning.R;

public class CheckingConnection {
    private Context context;

    public CheckingConnection(Context context) {
        this.context = context;
    }

    public boolean getConnectionStatus(Activity application) {
        boolean f = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.network_sample);
            dialog.setCancelable(false);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getAttributes().windowAnimations =
                    android.R.style.Animation_Dialog;
            Button button = dialog.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    application.recreate();
                }
            });
            dialog.show();
            f = false;
        } else {
            f = true;
        }
        return f;
    }
}
