package com.ldd.transport.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;


public class DialogUtils {
    private static Dialog pd;

    public static boolean showWaitDialog(Context context, String str) {
        String NetWorkType = NetWorkUtil.GetNetworkType(context);
        if (NetWorkType == null || NetWorkType.equals("")) {
            Toast.makeText(context, "网络连接已断开", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            pd = MyLoadingDialog.createLoadingDialog(context, str);
            pd.setCancelable(true);
            pd.setCanceledOnTouchOutside(false);
            pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        pd.show();
        return true;
    }

    public static boolean showWaitDialog(Context context, String str, DialogInterface.OnDismissListener listener) {
        String NetWorkType = NetWorkUtil.GetNetworkType(context);
        if (NetWorkType == null || NetWorkType.equals("")) {
            Toast.makeText(context, "网络连接已断开", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            pd = MyLoadingDialog.createLoadingDialog(context, str);
            pd.setCancelable(true);
            pd.setCanceledOnTouchOutside(false);
            pd.show();
            if (listener != null) {
                pd.setOnDismissListener(listener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        pd.show();
        return true;
    }


    public static void dismissDialog() {
        try {
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
                pd = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
