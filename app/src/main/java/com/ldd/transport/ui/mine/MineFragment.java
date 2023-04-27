package com.ldd.transport.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ldd.transport.common.AppFragment;
import com.ldd.transport.databinding.FragmentMineBinding;

import org.greenrobot.eventbus.EventBus;

public class MineFragment extends AppFragment implements View.OnClickListener {
    private static final String TAG = "MineFragment";
    private FragmentMineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);

        binding.ivHead.setOnClickListener(this);
        binding.tvName.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

    }


}