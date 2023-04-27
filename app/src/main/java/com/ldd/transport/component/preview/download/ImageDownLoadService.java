package com.ldd.transport.component.preview.download;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * 作者：Zhout
 * 时间：2018/3/14 15:12
 * 描述：图片下载
 */

public class ImageDownLoadService implements Runnable {
    private String imgUrl;
    private Context context;
    private ImageDownLoadCallBack callBack;

    public ImageDownLoadService(Context context, String imgUrl, ImageDownLoadCallBack callBack) {
        this.imgUrl = imgUrl;
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    public void run() {
        File file = null;
        try {
            file = Glide.with(context)
                    .load(imgUrl)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                callBack.onDownLoadSuccess(file);
            } else {
                callBack.onDownLoadFailed();
            }
        }
    }

}