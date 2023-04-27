package com.ldd.transport.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.gyf.immersionbar.ImmersionBar;
import com.hzlh.sdk.ui.BaseActivity;
import com.ldd.transport.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 父类
 *
 * @author 夏吧吧
 * @date 2020/12/7
 */

public abstract class AppBaseActivity<T extends ViewBinding> extends BaseActivity {
    public T vb;
    private RelativeLayout headLayout;
    protected ImageView backImg;
    protected TextView titleTxt;
    protected TextView rightTxt;
    private View titleLine;

    @Subscribe
    public void onEvent(Object obj) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        beforeInitView();
        init();
        initAppView();
    }

    private void init() {
        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            try {
                Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
                Method method = clazz.getMethod("inflate", LayoutInflater.class);
                vb = (T) method.invoke(null, getLayoutInflater());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setContentView(vb.getRoot());
        }
    }

    // 初始化之前，用于设置全屏，无标题等
    protected void beforeInitView() {

    }

    // 初始化
    protected void initAppView() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    protected void initHeader(String title) {
        headLayout = findViewById(R.id.head_layout);
        backImg = findViewById(R.id.back_img);
        titleTxt = findViewById(R.id.title_txt);
        rightTxt = findViewById(R.id.right_txt);
        titleLine = findViewById(R.id.title_line);
        titleTxt.setText(title);
        backImg.setOnClickListener(view -> finish());
    }
}
