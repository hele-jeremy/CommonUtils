package com.example.hjm.commonutils.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by AndroidCoder on 2015/6/17.
 * 读取Assets中的资源
 */
public class AssetsUtils {

    public static String readFileFromAeests(Context c, String fileName){
        String result=null;
        InputStream in=null;
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try{
            in=c.getAssets().open(fileName);
            int len=0;
            byte [] buf=new byte[1024];
            while((len=in.read(buf))!=-1){
                out.write(buf,0,len);
            }
            result=new String(out.toByteArray(),"UTF-8");
        }catch (Exception e){
            if (in!=null){
                try {
                    in.close();
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return result;
    }
}
