package com.ldd.transport;

import android.app.Application;
import android.text.TextUtils;

import com.hzlh.sdk.YunTingSdk;
import com.hzlh.sdk.util.YLog;
import com.ldd.transport.component.preview.PreviewImageLoader;
import com.ldd.transport.ui.MainActivity;
import com.previewlibrary.ZoomMediaLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App extends Application {
    //退出登录时需要销毁mainActivity 由于mainActivity与application生命周期一致 不存在内存泄露 在此使用静态接管
    public static MainActivity sMainActivity=null;
    @Override
    public void onCreate() {
        super.onCreate();
        YunTingSdk.init(this);
        initPush();
        ZoomMediaLoader.getInstance().init(new PreviewImageLoader()); // 预览大图
    }

    private void initPush() {
        if (isMainProcess()) {

        }
    }


    /**
     * 判断主进程
     *
     * @return
     */
    public boolean isMainProcess() {
        String packageName = getPackageName();
        String processName = getProcessName(android.os.Process.myPid());// 获取当前进程名
        YLog.e("packageName = " + packageName + " , processName = " + processName);
        return packageName.equals(processName);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


}
