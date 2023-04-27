package com.ldd.transport.view;


import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hzlh.sdk.constant.Screen;
import com.hzlh.sdk.util.StringUtils;
import com.hzlh.sdk.view.YDialog;
import com.ldd.transport.R;
import com.ldd.transport.util.permission.PermissionVerifyUtil;


public class DialogShow {

    public static void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 权限弹框
     *
     * @param context
     */
    public static AlertDialog showPermissionTipDialog(Context context, int permissionType) {
        String content = "";
        String title = "";
        if (permissionType == PermissionVerifyUtil.WRITE_EXTERNAL_STORAGE) { //读写文件权限申请
            content = "用于App写入/下载/保存/读取/修改/删除图片、文件等信息";
            title = "读取SD卡中的内容（读取存储空间/照片权限）";
        } else if (permissionType == PermissionVerifyUtil.RECORD_AUDIO) { //录音权限申请
            content = "用于APP录制声音发表语音评论、录制音频进行剪辑等";
            title = "获取麦克风权限录音";
        } else if (permissionType == PermissionVerifyUtil.ACCESS_FINE_LOCATION) { //定位权限申请
            content = "用于APP根据位置信息推荐相关电台的功能";
            title = "获取定位权限";
        } else if (permissionType == PermissionVerifyUtil.CAMERA) { //拍照权限申请
            content = "用于App拍照修改头像，发表图片评论、录制视频、扫一扫等";
            title = "调用相机拍照或者录制视频";
        }

        if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(content)) {
            final AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.show();

            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            View view = View.inflate(context, R.layout.dialog_permision_tip, null);
            dialog.setContentView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            TextView tvContent = (TextView) view.findViewById(R.id.tvContent);


            tvContent.setText(content);
            tvTitle.setText(title);

            Window win = dialog.getWindow();
            WindowManager.LayoutParams params = win.getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.TOP;
            win.setAttributes(params);
//            win.setBackgroundDrawableResource(R.drawable.shape_round_white_bg);
            return dialog;
        }
        return null;
    }


    /**
     * 开启某功能时的确认弹框（标题 + 内容描述 + 两个按钮）
     *
     * @param context
     * @param title    标题
     * @param callBack
     */
    public static void openFunctionConfirm(Context context, String title, String content, String btn1, String btn2, final ICallBack callBack) {
        DialogShow.dialogShow(context, title, content, btn1, btn2, new ICallBack() {
            @Override
            public void onOkClick() {
                if (callBack != null) {
                    callBack.onOkClick();
                }
            }

            @Override
            public void onCancel() {
                if (callBack != null) {
                    callBack.onCancel();
                }
            }
        });

    }


    /**
     * 两个按钮的弹窗
     *
     * @param context
     * @param title       标题
     * @param content     内容    可为空
     * @param ok_text     确定    取消确定都为空时，显示"确定"。取消有内容确定为空时，确定按钮隐藏。
     * @param cancel_text 取消    取消为空时，显示"取消"
     * @param callBack    回调
     */
    public static void dialogShow(Context context, String title, String content, String
            ok_text, String cancel_text, final ICallBack callBack) {

        YDialog alertDialog = new YDialog(context, R.layout.dialog_common) {
            @Override
            public void initSetting(Window window) {
//                this.setCanceledOnTouchOutside(true);
                window.setBackgroundDrawableResource(R.color.transparent);
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = Screen.width * 260 / 360;
                params.gravity = Gravity.CENTER;
                window.setAttributes(params);
                TextView tvTitle = window.findViewById(R.id.tv_title);
                TextView tvContent = window.findViewById(R.id.tv_content);
                View viewHide = window.findViewById(R.id.viewHide);
                tvContent.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
                if (StringUtils.isNotEmpty(content)) {
                    tvContent.setVisibility(View.VISIBLE);
                    tvContent.setText(content);
                    viewHide.setVisibility(View.GONE);
                } else {
                    viewHide.setVisibility(View.VISIBLE);
                }
                if (StringUtils.isNotEmpty(title)) {
                    tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.setText(title);
                }

                TextView tvCancel = window.findViewById(R.id.tv_cancel);
                TextView tvOk = window.findViewById(R.id.tv_ok);
                tvCancel.setText(cancel_text);
                tvOk.setText(ok_text);
                if (StringUtils.isEmpty(cancel_text)) {
                    tvCancel.setText("取消");
                }
                if (StringUtils.isEmpty(ok_text)) {
                    tvOk.setText("确定");
                }

                //取消按钮有内容，确定按钮没内容，则为单按钮样式，只能取消
                if (StringUtils.isNotEmpty(cancel_text) && StringUtils.isEmpty(ok_text)) {
                    tvOk.setVisibility(View.GONE);
                } else {
                    tvOk.setVisibility(View.VISIBLE);
                }
                tvCancel.setOnClickListener(v -> {
                    dismiss();
                    if (callBack != null) {
                        callBack.onCancel();
                    }
                });

                tvOk.setOnClickListener(v -> {
                    dismiss();
                    if (callBack != null) {
                        callBack.onOkClick();
                    }
                });
            }
        };
        alertDialog.open();

    }


    /**
     * 确定/取消通用
     */
    public interface ICallBack {
        /**
         * 确定
         */
        void onOkClick();

        /**
         * 取消
         */
        void onCancel();
    }


    public interface ISelectPhotoCallback {
        /**
         * 相册
         */
        void clickGallery();

        /**
         * 照相机
         */
        void clickCamera();
    }


}
