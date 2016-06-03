package com.example.android.bluetoothchat;

/**
 * Created by Shun-Huai.Yao on 6/1/2016.
 */
public class ImageObject {

    private int filesize;
    private byte[] bitmap;
    ImageObject(byte[] bitmap){
        this.filesize = bitmap.length;
        this.bitmap = bitmap;
    }
    public byte[] getBitmap(){
        return this.bitmap;
    }
    public int getBitmaplength(){
        return this.filesize;
    }

}
