package com.ldd.transport.ui.goods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ldd.transport.common.AppFragment;
import com.ldd.transport.databinding.FragmentGoodsCenterBinding;

/**
 * 描述：包裹中心
 * 日期: 2023/4/26 17:48
 *
 * @author Zhout
 */
public class GoodsCenterFragment extends AppFragment {
    private FragmentGoodsCenterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGoodsCenterBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
