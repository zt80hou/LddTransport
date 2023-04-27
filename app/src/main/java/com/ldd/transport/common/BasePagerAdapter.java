package com.ldd.transport.common;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 新一代的pageAdapter，用这个可以在fragment的onResume中使用懒加载
 */

public class BasePagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private List<String> nameList;
    private List<Fragment> fragments;

    public BasePagerAdapter(FragmentManager fm, List<String> nameList, List<Fragment> fragments) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fm = fm;
        this.nameList = nameList;
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (nameList == null) {
            return "";
        }
        return nameList.get(position);
    }

    @Override
    public int getCount() {
        if (fragments == null) {
            return 0;
        }
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}