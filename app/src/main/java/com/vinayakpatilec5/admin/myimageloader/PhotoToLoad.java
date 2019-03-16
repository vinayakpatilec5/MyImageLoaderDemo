package com.vinayakpatilec5.admin.myimageloader;

import android.widget.ImageView;

public class PhotoToLoad {
    public String url;
    public ImageView imageView;
    public PhotoToLoad(String u, ImageView i){
        url=u;
        imageView=i;
    }
}
