package com.ldd.transport.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ldd.transport.R;
import com.ldd.transport.common.AppFragment;
import com.ldd.transport.databinding.FragmentHomeBinding;

public class HomeFragment extends AppFragment {
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        initView();
        initData();
        return binding.getRoot();
    }

    private void initView() {

    }

    private void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}