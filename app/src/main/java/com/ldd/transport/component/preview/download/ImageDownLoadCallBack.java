package com.ldd.transport.component.preview.download;

import java.io.File;

/**
 * 作者：Zhout
 * 时间：2018/3/14 15:11
 * 描述：下载图片回调
 */

public interface ImageDownLoadCallBack {

    void onDownLoadSuccess(File file);

    void onDownLoadFailed();
}
