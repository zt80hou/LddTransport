package com.ldd.transport.ui;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ldd.transport.App;
import com.ldd.transport.common.AppBaseActivity;
import com.ldd.transport.common.BasePagerAdapter;
import com.ldd.transport.databinding.ActivityMainBinding;
import com.ldd.transport.ui.freight.FreightCalFragment;
import com.ldd.transport.ui.goods.GoodsCenterFragment;
import com.ldd.transport.ui.home.HomeFragment;
import com.ldd.transport.ui.mine.MineFragment;
import com.ldd.transport.ui.order.OrderFragment;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppBaseActivity<ActivityMainBinding> {
    private final Context context = this;
    private final List<String> menuNameList = new ArrayList<>(Arrays.asList("首页", "包裹中心", "订单查询", "运费估算", "我的"));
    private final List<String> titleNameList = new ArrayList<>(Arrays.asList("首页", "包裹中心", "订单中心", "运费计算器", "个人中心"));
    private final List<Fragment> fragmentList = new ArrayList<>();
    private MainMenuRvAdapter menuRvAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void initAppView() {
        super.initAppView();

        App.sMainActivity = this;

        fragmentList.add(new HomeFragment());
        fragmentList.add(new GoodsCenterFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new FreightCalFragment());
        fragmentList.add(new MineFragment());

        vb.viewPager.setOffscreenPageLimit(5);
        vb.viewPager.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), menuNameList, fragmentList));

        vb.menuRv.setLayoutManager(new GridLayoutManager(context, 5));
        menuRvAdapter = new MainMenuRvAdapter(context, menuNameList);
        vb.menuRv.setAdapter(menuRvAdapter);

        vb.tvTitle.setText(menuNameList.get(0));

        menuRvAdapter.setOnItemClickListener((v, position) -> {
            vb.viewPager.setCurrentItem(position);
            vb.tvTitle.setText(titleNameList.get(position));
            menuRvAdapter.setSelectPosition(position);
            menuRvAdapter.notifyDataSetChanged();
            // 隐藏小红点
            menuRvAdapter.showRedPoint(1, false);
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.sMainActivity = null;
    }
}