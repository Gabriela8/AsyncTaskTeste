package android_3bim.ifpb.edu.br.minhaapps;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by IFPB on 09/12/2015.
 */
public class MinhaTask extends AsyncTask<Void,Integer,String> {
    Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;
    MinhaTask(Context context,TextView textView,Button button)
    {
       this.context = context;
       this.textView = textView;
       this.button = button;
    }


    @Override

    protected String doInBackground(Void... params) {
        int i = 0;
        synchronized (this)
        {
            while (i<10)
            {
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


       return "Download...";
    }

    @Override
    protected void onPreExecute() {
      progressDialog = new ProgressDialog(context);
      progressDialog.setTitle("Download");
      progressDialog.setMax(10);
      progressDialog.setProgress(0);
      progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
      progressDialog.show();

    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        button.setEnabled(true);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Download...");
    }
}
