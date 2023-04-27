package com.ldd.transport.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ldd.transport.R;
import com.ldd.transport.common.BaseRecyclerAdapter;
import com.ldd.transport.databinding.MainMenuItemBinding;

import java.util.List;

/**
 * 描述：主页底部菜单
 * 日期: 2022/9/15 10:42
 *
 * @author Zhout
 */
public class MainMenuRvAdapter extends BaseRecyclerAdapter<String, MainMenuItemBinding> {

    public MainMenuRvAdapter(Context context, List<String> data) {
        super(context, data);
    }

    private int selectedPosition = 0;
    private boolean showRedPoint;
    private int redPointPos = -1;

    public void setSelectPosition(int pos) {
        this.selectedPosition = pos;
    }


    public void showRedPoint(int position, boolean show) {
        this.redPointPos = position;
        this.showRedPoint = show;
    }

    @Override
    protected void onBindingData(@NonNull BaseHolder<MainMenuItemBinding> holder, @NonNull String bean, int position) {
        holder.getVb().tvMenuName.setText(bean);

        changeButtonUI(position, holder.getVb().icon, holder.getVb().tvMenuName);

        if (showRedPoint && position == redPointPos) {
            holder.getVb().ivRedPoint.setVisibility(View.VISIBLE);
        } else {
            holder.getVb().ivRedPoint.setVisibility(View.GONE);
        }
    }


    private void changeButtonUI(int position, ImageView icon, TextView tvMenuName) {
        boolean isSelect = selectedPosition == position;
        switch (position) {
            case 0:
                icon.setImageResource(R.drawable.ic_bottom_home);
                break;
            case 1:
                icon.setImageResource(R.drawable.ic_bottom_notifications);
                break;
            case 2:
                icon.setImageResource(R.drawable.ic_bottom_mall);
                break;
            case 3:
                icon.setImageResource(R.drawable.ic_bottom_home);
                break;
            case 4:
                icon.setImageResource(R.drawable.ic_bottom_mine);
                break;
            default:
                break;
        }
        if (isSelect) {
            icon.setColorFilter(getContext().getResources().getColor(R.color.menu_selected));
            tvMenuName.setTextColor(getContext().getResources().getColor(R.color.menu_selected));
        } else {
            icon.setColorFilter(getContext().getResources().getColor(R.color.menu_unselected));
            tvMenuName.setTextColor(getContext().getResources().getColor(R.color.menu_unselected));
        }
    }

    @Override
    protected MainMenuItemBinding onBindingView(@NonNull ViewGroup viewGroup) {
        return MainMenuItemBinding.inflate(LayoutInflater.from(context), viewGroup, false);
    }

}
