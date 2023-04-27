package com.ldd.transport.component.preview;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hzlh.sdk.util.YLog;
import com.ldd.transport.R;
import com.ldd.transport.util.Util;
import com.ldd.transport.util.permission.ApplyPermissionListener;
import com.ldd.transport.util.permission.GrantListener;
import com.ldd.transport.util.permission.PermissionVerifyUtil;
import com.previewlibrary.view.BasePhotoFragment;


/**
 * 作者：Zhout
 * 时间：2017/12/22 11:01
 * 描述：自定义图片显示Fragment，增加长按保存图片功能
 */

public class PreviewImageFragment extends BasePhotoFragment {
    private AlertDialog dialog;
    private Context context;
    private PermissionVerifyUtil permissionVerifyUtil;
    private String currentImgUrl; // 正在浏览的图片地址

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (permissionVerifyUtil == null) {
            permissionVerifyUtil = new PermissionVerifyUtil(getActivity(), this);
        }
        final ImgListBean bean = (ImgListBean) getBeanViewInfo();
        currentImgUrl = bean.getUrl();
//        loading.setVisibility(View.GONE);
        ((ProgressBar) loading).getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        imageView.setBackgroundColor(context.getResources().getColor(R.color.black));
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showPicDialog(bean.getUrl());
                return true;
            }
        });
    }

    private void showPicDialog(final String imgUrl) {
        context.setTheme(R.style.hint_ui_bg);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.dialog_save_pic, null);
        // 保存图片
        view.findViewById(R.id.btn_save_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyPermission(PermissionVerifyUtil.WRITE_EXTERNAL_STORAGE, new ApplyPermissionListener() {
                    @Override
                    public void onPermissionAllowed() {
                        PreviewImageUtil.downLoadPic(context, Environment.getExternalStorageDirectory().getAbsolutePath() + "/huimuyun/", imgUrl);
                        dialog.dismiss();
                    }
                });
            }
        });

        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        // 注意：是先显示出来dialog，show()出来，才能再设置dialog窗口的宽高属性
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = Util.dip2px(context, 280);
        params.gravity = Gravity.CENTER_VERTICAL;
        dialog.getWindow().setAttributes(params);

    }

    /**
     * 申请权限
     */
    protected void applyPermission(int permission, ApplyPermissionListener listener) {
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
                YLog.d("Fragment: 权限被拒绝，未点击不再询问");
            }

            @Override
            public void onDenyNotAskAgain() {
                YLog.d("Fragment: 权限被拒绝，并点击了不再询问");
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
    public void onDestroy() {
        super.onDestroy();
    }

}
