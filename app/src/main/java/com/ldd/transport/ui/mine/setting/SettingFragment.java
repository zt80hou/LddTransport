package com.ldd.transport.ui.mine.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hzlh.sdk.net.CallBack;
import com.hzlh.sdk.util.SPUtils;
import com.hzlh.sdk.util.YLog;
import com.hzlh.sdk.util.YToast;
import com.ldd.transport.App;
import com.ldd.transport.api.user.CommonResultBean;
import com.ldd.transport.api.user.UserApi;
import com.ldd.transport.common.Constants;
import com.ldd.transport.databinding.FragmentSettingBinding;
import com.ldd.transport.ui.mine.LoginActivity;

/**
 * 描述：设置页 Fragment
 * 日期: 2022年9月16日
 *
 * @author yukd
 */
public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.include.titleTxt.setText("设置");
        //关闭设置页
        binding.include.backImg.setOnClickListener(v -> getActivity().finish());
        //修改密码
        binding.tvChangePw.setOnClickListener(v -> {

            if (Constants.accessToken.equals("")) {
                YToast.shortToast(getContext(), "请先登录");
                return;
            }
        });
        //退出登录
        binding.btnLogout.setOnClickListener(v -> {

            new UserApi().logout(getContext(),new CallBack<CommonResultBean>(){
                @Override
                public void onResultOk(CommonResultBean result) {
                    super.onResultOk(result);
                    YLog.d(result.toString());
                }
            });
            SPUtils.getInstance(v.getContext()).putString(Constants.SP_KEY_LOGIN_PWD, "");
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            if (App.sMainActivity != null) {
                App.sMainActivity.finish();
            }

            getActivity().finish();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}