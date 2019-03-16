package com.vinayakpatilec5.admin.myimageloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    private Context context;
    private ImageView imageView;
    private String url;
    private int placeHolder;
    private int errorIamge;

    FileCache fileCache;
    private Map<ImageView, String> imageViews= Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService;


    public ImageLoader(Context context) {
        this.context = context;

        fileCache=new FileCache(context);
        executorService= Executors.newFixedThreadPool(5);
    }

    public ImageLoader with(String url){
        this.url = url;
        return this;
    }

    public ImageLoader loadInto(ImageView imageView){
        this.imageView = imageView;
        return this;
    }

    public ImageLoader placeHolder(int id){
        this.placeHolder = id;
        return this;
    }

    public ImageLoader onError(int id){
        this.errorIamge = id;
        return this;
    }

    public void load(){
        if((imageView != null)){
            imageViews.put(imageView, url);
            setResourseImage(placeHolder);
            queuePhoto(url, imageView);
        }
    }

    private void queuePhoto(String url, ImageView imageView) {
        PhotoToLoad p=new PhotoToLoad(url, imageView);
        executorService.submit(new PhotosLoader(p));
    }


    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;
        PhotosLoader(PhotoToLoad photoToLoad){
            this.photoToLoad=photoToLoad;
        }

        @Override
        public void run() {
            if(imageViewReused(photoToLoad))
                return;
            File f=fileCache.getFile(url);
            Bitmap b = decodeFile(f);
            if(b == null) {
                if(isNetworkAvailable()) {
                    b = getBitmap(photoToLoad.url);
                }
            }
            final Bitmap bmp=b;
            Activity a=(Activity)photoToLoad.imageView.getContext();
            a.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setImage(bmp);
                }
            });
        }
    }

    private Bitmap getBitmap(String url) {
        File f=fileCache.getFile(url);
        try {
            Bitmap bitmap=null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is=conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            copyStream(is, os);
            os.close();
            bitmap = decodeFile(f);
            return bitmap;
        } catch (Throwable ex){
            ex.printStackTrace();
            return null;
        }
    }

    private void copyStream(InputStream is, OutputStream os){
        final int buffer_size=1024;
        try {
            byte[] bytes=new byte[buffer_size];
            for(;;) {
                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }

    boolean imageViewReused(PhotoToLoad photoToLoad){
        String tag=imageViews.get(photoToLoad.imageView);
        if(tag==null || !tag.equals(photoToLoad.url))
            return true;
        return false;
    }


    //decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f){
        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE=64;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }

            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }


    //check for activity is visible and set image
    private void setResourseImage(int id){
        if((((Activity)imageView.getContext()).getWindow().getDecorView().getRootView().isShown())&&(id>0)) {
            imageView.setImageResource(id);
        }
    }


    //check for activity is visible and set image
    private void setImage(Bitmap bitmap){
        if(((Activity)imageView.getContext()).getWindow().getDecorView().getRootView().isShown()) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                setResourseImage(errorIamge);
            }
        }
    }

    //check for internet connectivity
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
