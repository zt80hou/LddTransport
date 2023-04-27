package com.ldd.transport.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hzlh.sdk.ui.BaseFragment;
import com.hzlh.sdk.util.YLog;
import com.ldd.transport.util.permission.ApplyPermissionListener;
import com.ldd.transport.util.permission.GrantListener;
import com.ldd.transport.util.permission.PermissionVerifyUtil;

import org.greenrobot.eventbus.Subscribe;


public class AppFragment extends BaseFragment {
    private long lastTimeStamp;// 进来的时候时间
    protected PermissionVerifyUtil permissionVerifyUtil;

    public AppFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        YLog.i("fragment >>> " + this.getClass().getSimpleName());
        lastTimeStamp = System.currentTimeMillis();
        if (permissionVerifyUtil == null) {
            permissionVerifyUtil = new PermissionVerifyUtil(getActivity(), this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Subscribe
    public void onEvent(Object obj) {

    }

    /**
     * 启动一个新的Activity
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }

    /**
     * 申请权限
     */
    protected void applyPermission(int permission, ApplyPermissionListener listener) {
        if (permissionVerifyUtil == null) {
            permissionVerifyUtil = new PermissionVerifyUtil(getActivity(), this);
        }
        // 请求使用单个权限
        permissionVerifyUtil.apply(permission, new GrantListener() {
            @Override
            public void onAgree() {
                YLog.d("AppFragment: 权限已同意");
                if (listener != null) {
                    listener.onPermissionAllowed();
                }
            }

            @Override
            public void onDeny() {
                YLog.d("AppFragment: 权限被拒绝，未点击不再询问");
            }

            @Override
            public void onDenyNotAskAgain() {
                YLog.d("AppFragment: 权限被拒绝，并点击了不再询问");
            }
        });
    }

    /**
     * 请求权限结果回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionVerifyUtil != null) {
            if (requestCode == PermissionVerifyUtil.ONCE_TIME_APPLY) {
                permissionVerifyUtil.onceTimeApplyResult(permissions, grantResults);
            } else {
                permissionVerifyUtil.singleApplyResult(requestCode, grantResults);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
