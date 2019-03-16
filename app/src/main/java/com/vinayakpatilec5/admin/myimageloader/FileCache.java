package com.vinayakpatilec5.admin.myimageloader;

import android.content.Context;

import java.io.File;

public class FileCache {
    private File cacheDir;

    public FileCache(Context context){
        //Find the dir to save cached images
        cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }

    public File getFile(String url){
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename=String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }
}
