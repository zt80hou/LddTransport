package com.ldd.transport.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.List;

/**
 * 描述：单布局RecyclerView父类Adapter; 嵌套是子布局使用inflate(LayoutInflater.from(context), viewGroup, false);方式
 * 日期: 2022/4/7 13:14
 *
 * @author Zhout
 */
public abstract class BaseRecyclerAdapter<T, VB extends ViewBinding> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseHolder<VB>> {
    public Context context;
    public List<T> dataList;

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this.context = context;
        this.dataList = data;
    }

    @NonNull
    @Override
    public BaseHolder<VB> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new BaseHolder<VB>(onBindingView(viewGroup));
//        VB vb = null;
//        //利用反射，调用指定ViewBinding中的inflate方法填充视图
//        Type type = this.getClass().getGenericSuperclass();
//        if (type instanceof ParameterizedType) {
//            try {
//                Class<VB> clazz = (Class<VB>) ((ParameterizedType) type).getActualTypeArguments()[1];// [1]对应VB泛型
////                YLog.e("clazz = " +clazz.getName());
//                Method method = clazz.getMethod("inflate", LayoutInflater.class);
//                vb = (VB) method.invoke(null, LayoutInflater.from(context));
//            } catch (Exception e) {
//                e.printStackTrace();
//                YLog.e("Exception = " + e.getMessage());
//            }
//        }
//        return new BaseHolder<>(vb);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<VB> holder, @SuppressLint("RecyclerView") int position) {
        onBindingData(holder, dataList.get(position), position);
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 如果recyclerView添加了header, itemClick使用holder.getAdapterPosition不准确，所以还是使用position
                    itemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    /**
     * 给ViewBinding布局设置数据
     *
     * @param holder   BaseHolder
     * @param bean   数据实体类
     * @param position 位置
     */
    protected abstract void onBindingData(@NonNull BaseHolder<VB> holder, @NonNull T bean, int position);

    /**
     * 绑定View，ViewBinding实现
     *
     * @param viewGroup viewGroup
     * @return VB
     */
    protected abstract VB onBindingView(@NonNull ViewGroup viewGroup);

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public static class BaseHolder<VB extends ViewBinding> extends RecyclerView.ViewHolder {
        final VB vb;

        public BaseHolder(VB viewBinding) {
            super(viewBinding.getRoot());
            this.vb = viewBinding;
        }

        public VB getVb() {
            return vb;
        }

    }

    public Context getContext() {
        return context;
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }










}
