package com.example.android.bluetoothchat;

/**
 * Created by Shun-Huai.Yao on 6/1/2016.
 */
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncImageLoader extends AsyncTask<String, Integer, String> {


    private onImageLoaderListener mOnImageLoaderListener;

    //    private String downloadUrl = "http://nmsl.cs.nthu.edu.tw/dropbox/Penguin.png";
//    private String filename = "Penguin.png";
    public final String SDStorage = MainActivity.SDStorage;

    public AsyncImageLoader(onImageLoaderListener mOnImageLoaderListener) {
        this.mOnImageLoaderListener = mOnImageLoaderListener;
    }

    /**
     * This is our interface that listens for image download completion.
     */
    public interface onImageLoaderListener {
        /**
         * This callback will be invoked when the image has finished
         * downloading.
         * @param result
         * the network response
         */
        void onImageLoaded(String result);
    }


    @Override
    protected String doInBackground(String... params) {
        String ext=".png";
        boolean isDownloaded=DownloadFile(params[0]+params[1]+ext,
                params[1]+ext);
        if(isDownloaded) {
            return params[1];
        }
        else{
            return Integer.valueOf(-1).toString();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        /**
         * called on the UI thread
         */
        if (mOnImageLoaderListener != null) {
            mOnImageLoaderListener.onImageLoaded(result);
        }
    }




    private boolean storeBitmap(Bitmap savePic,String filename){
        if (savePic != null) {
            try {
                File newDirectory = new File(SDStorage);
                if(!newDirectory .exists()){
                    newDirectory .mkdirs();
                }
                else{
                    Log.d("StoreBitmap:", "Dir already exists");
                }
                // output image to file
                FileOutputStream fos = new FileOutputStream(SDStorage+filename+".png");
                savePic.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        } else {
            Log.i("StoreBitmap:", "Thumbnail image parsing error");
            return false;
        }

    }



    public boolean DownloadFile(String fileURL, String filename) {
        try {
            //String timestamp=Long.valueOf(System.currentTimeMillis()/1000).toString();
            URL u = new URL(fileURL);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.connect();
            //this timeout affects how long it takes for the app to realize there's a connection problem
            int TIMEOUT_CONNECTION = 1000*60*5;//5 mins
            int TIMEOUT_SOCKET = 1000*60*10;//10 mins
            conn.setReadTimeout(TIMEOUT_CONNECTION);
            conn.setConnectTimeout(TIMEOUT_SOCKET);
            FileOutputStream f = new FileOutputStream(new File(SDStorage,filename));
            InputStream in = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
            return true;
        } catch (Exception e) {
            Log.d("DownloadFile:", e.toString()); //`enter code here
            return false;
        }

    }
}
