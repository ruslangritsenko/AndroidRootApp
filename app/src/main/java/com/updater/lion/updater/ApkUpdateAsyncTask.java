package com.updater.lion.updater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ApkUpdateAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;
    private String url;
    public ApkUpdateAsyncTask(String url, Context context){
        this.context = context;
        this.url = url;
    }

    @Override
    protected String doInBackground(String... urls){

        String path = Environment.getExternalStorageDirectory()+"/test.apk";

        //download the apk from your server and save to sdk card here
        try{
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(path);

            byte data[] = new byte[1024];
            int count;
            int i = 0;
            while ((count = input.read(data)) != -1)
            {
                Log.v("APKUpdateAsyncTask", "i = " + i++);
                Log.v("APKUpdateAsyncTask", "count = " + count);
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        }catch(Exception e){}

        return path;
    }

    @Override
    protected void onPostExecute(String path)
    {
//        Process process = null;
//
//        // call to superuser command, pipe install updated APK without writing over files/DB
//        try
//        {
//            process = Runtime.getRuntime().exec("su");
//            DataOutputStream outs = new DataOutputStream(process.getOutputStream());
//
//            String cmd = "pm install -r "+path;
//
//            outs.writeBytes(cmd+"\\n");
//        }
//        catch (IOException e)
//        {}
//
//        Intent i = new Intent();
//        i.setAction(Intent.ACTION_VIEW);
//        i.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive" );
//        Log.d("Lofting", "About to install new .apk");
//        context.startActivity(i);
    }

}