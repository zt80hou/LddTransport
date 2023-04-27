package com.ldd.transport.component.preview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;

import com.hzlh.sdk.util.YToast;
import com.ldd.transport.component.preview.download.ImageDownLoadCallBack;
import com.ldd.transport.component.preview.download.ImageDownLoadService;
import com.ldd.transport.util.FileUtils;
import com.previewlibrary.GPreviewBuilder;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：Zhout
 * 时间：2018/3/7 10:33
 * 描述：图片预览大图工具类
 */

public class PreviewImageUtil {

    private static ExecutorService singleExecutor;


    public static void startActivity(Context context, List<ImgListBean> imgList, int position) {
        startActivity(context, imgList, position, false);
    }

    /**
     * 跳转到预览大图页面GPreviewActivity
     *
     * @param context
     * @param imgList  图片集合
     * @param position 图片索引位置，只有一张传0
     * @param isDrag  是否禁用拖拽
     */
    public static void startActivity(Context context, List<ImgListBean> imgList, int position, boolean isDrag) {
        GPreviewBuilder.from((Activity) context)
                .setData(imgList)
                .setUserFragment(PreviewImageFragment.class)
                .setCurrentIndex(position)
                .setSingleFling(true)
                .setSingleShowType(false)
                .setDuration(300)
                .setDrag(isDrag)
                .setType(GPreviewBuilder.IndicatorType.Dot)
                .start();
    }

    /**
     * 下载图片
     *
     * @param context
     * @param filePath 路径
     * @param imgUrl   图片地址
     */
    public static void downLoadPic(final Context context, final String filePath, String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) {
            return;
        }
        ImageDownLoadService service = new ImageDownLoadService(context,
                imgUrl,
                new ImageDownLoadCallBack() {
                    @Override
                    public void onDownLoadSuccess(File file) {
                        String fileName = System.currentTimeMillis() + ".jpg";
                        // 在这里执行图片保存方法
                        FileUtils.copyFile(file.getPath(), filePath + fileName);
                        // 通知系统更新相册
                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + filePath + fileName)));
                        Looper.prepare();
                        YToast.shortToast(context, "保存成功！");
                        Looper.loop();
                    }

                    @Override
                    public void onDownLoadFailed() {
                        // 图片保存失败
                        Looper.prepare();
                        YToast.shortToast(context, "保存失败，请重试！");
                        Looper.loop();
                    }
                });
        //启动图片下载线程
        if (singleExecutor == null) {
            singleExecutor = Executors.newCachedThreadPool();
        }
        singleExecutor.execute(service);
    }

}
