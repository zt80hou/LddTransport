package com.ldd.transport.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ldd.transport.common.AppFragment;
import com.ldd.transport.databinding.FragmentOrderBinding;

import org.greenrobot.eventbus.EventBus;

/**
 * 描述：订单查询
 * 日期: 2023/4/26 17:48
 *
 * @author Zhout
 */
public class OrderFragment extends AppFragment {
    private FragmentOrderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }
}
